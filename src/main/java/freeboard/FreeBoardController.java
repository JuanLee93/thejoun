package freeboard;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import admin.AdminVo;
import announce.AnnounceService;
import announce.AnnounceVo;
import comment.CommentService;
import comment.CommentVo;
import friends.FriendsService;
import notice.NoticeVo;
import user.UserVo;
import util.CommonUtil;

@Controller
public class FreeBoardController {

	@Autowired
	FreeBoardService freeBoardService;
	
	@Autowired
	CommentService cService;
	//여기아래 오토와이어드부터 알림창 insert 하는데 필요함
	@Autowired
	FriendsService fs;
	
	@Autowired
	AnnounceService as;
	
	@GetMapping("admin/freeboard/index.do")
	public String adminIndex(Model model, HttpServletRequest req, HttpSession sess, FreeBoardVo vo) {
		
		int totCount = freeBoardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		String param = "&orderby="+vo.getOrderby();
		
		List<FreeBoardVo> list = freeBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10, param));
		return "admin/freeboard/index";
	}
	
	@GetMapping("/admin/freeboard/view.do")
	public String adminView(Model model, @RequestParam int board_no) {
		model.addAttribute("data", freeBoardService.view(board_no));
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(1);
		model.addAttribute("cList", cService.selectList(cv));

		return "admin/freeboard/view";
	}
	
	@RequestMapping("/admin/freeboard/write.do")
	public String adminWrite() {
		return "admin/freeboard/write";
	}
	
	@GetMapping("/admin/freeboard/edit.do")
	public String adminEdit(Model model, @RequestParam int board_no) {
		model.addAttribute("data", freeBoardService.adminEdit(board_no));
		return "admin/freeboard/edit";
	}
	
	@PostMapping("/admin/freeboard/update.do")
	public String adminUpdate(Model model, FreeBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			FreeBoardVo bv = freeBoardService.adminEdit(vo.getBoard_no());
			File f = new File(req.getRealPath("/upload/")+bv.getFilename_real());
			f.delete();//실제 서버에서 삭제처리
			vo.setFilename_org("");
			vo.setFilename_real("");
		}
		if (!file.isEmpty()) {
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf("."));
				String filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real));
				vo.setFilename_org(filename);
				vo.setFilename_real(filename_real);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = freeBoardService.adminUpdate(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getBoard_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?board_no="+vo.getBoard_no());
		}
		return "include/return";
	}
	
	@PostMapping("/admin/freeboard/insert.do")
	public String adminInsert(FreeBoardVo vo,AnnounceVo av, HttpServletRequest req, MultipartFile file, HttpSession sess) {
		vo.setAdmin_no(((AdminVo)sess.getAttribute("adminInfo")).getAdmin_no());
		// 파일 저장
		if (file == null || !file.isEmpty()) {//사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf("."));
				String filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real));
				vo.setFilename_org(filename);
				vo.setFilename_real(filename_real);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = freeBoardService.adminInsert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
			
			
			//여기까지 알림임
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/return";
	}
	
	@GetMapping("/admin/freeboard/delete.do")
	public String adminFreeboardDelete(Model model, FreeBoardVo vo) {
		int r = freeBoardService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getBoard_no());
		}
		return "include/return";
	}
	
	@GetMapping("/admin/freeboard/deleteAjax.do")
	public String adminFreeboardDeleteAjax(Model model, FreeBoardVo vo) {
		model.addAttribute("result", freeBoardService.delete(vo));
		return "include/result";
	}

	@PostMapping("/admin/freeboard/boardDeleteAjax.do")
	public String adminBoardDeleteAjax(HttpServletRequest req, Model model) {
		String[] deleteArray = req.getParameterValues("no");
		for (int i=0; i<deleteArray.length; i++) {
			FreeBoardVo vo = new FreeBoardVo();
			vo.setBoard_no(Integer.parseInt(deleteArray[i]));
			freeBoardService.delete(vo);
		}
		model.addAttribute("msg", "정상적으로 삭제되었습니다.");
		model.addAttribute("url", "index.do");
		
		return "include/return";
	}
	
	@PostMapping("/admin/freeboard/noticeUpdateAjax.do")
	public String adminNoticeUpdateAjax(HttpServletRequest req) {
		String[] updateArray = req.getParameterValues("no");
		for (int i=0; i<updateArray.length; i++) {
			FreeBoardVo vo = new FreeBoardVo();
			vo.setBoard_no(Integer.parseInt(updateArray[i]));
			freeBoardService.updateNotice(vo);
		}
		return "include/result";
	}
	
	@GetMapping("/freeboard/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, FreeBoardVo vo) {
		vo.setIs_user('Y');
		int totCount = freeBoardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		String param = "&orderby="+vo.getOrderby();
		
		List<FreeBoardVo> list = freeBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10, param));
		return "freeboard/index";
	}
	
	@RequestMapping("/freeboard/write.do")
	public String write() {
		return "freeboard/write";
	}
	
	@PostMapping("/freeboard/insert.do")
	public String insert(FreeBoardVo vo,AnnounceVo av, HttpServletRequest req, MultipartFile file, HttpSession sess) {
		vo.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		// 파일 저장
		if (!file.isEmpty()) {//사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf("."));
				String filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real));
				vo.setFilename_org(filename);
				vo.setFilename_real(filename_real);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = freeBoardService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
			
			//여기부터 알림임
			av.setBoardno(vo.getBoard_no());
			av.setCommentno(0);
			av.setFriends_userno(((UserVo)sess.getAttribute("userInfo")).getUserno());
			av.setBoard_or_comment(0);
			//FriendsService 에 메서드로 가서 내 userno기준으로 친구된 사람 리스트로 받아와야함
			List<Integer> my_userno = fs.getFriendsUserno(av);
			for(int i=0;i<my_userno.size();i++) {
				av.setMy_userno(my_userno.get(i));
				int announceCheck = as.announceInsert1(av);
			}
			//여기까지 알림임
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/return";
	}
	
	@GetMapping("/freeboard/view.do")
	public String view(Model model, HttpSession sess, @RequestParam int board_no, HttpServletRequest req, FreeBoardVo vo) {
		model.addAttribute("data", freeBoardService.view(board_no));
		
		vo.setBoard_no(board_no);
		int Rownum = freeBoardService.getRownum(vo);
		vo.setRownum(Rownum);
		FreeBoardVo prev = freeBoardService.getPrev(vo);
		FreeBoardVo next = freeBoardService.getNext(vo);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(1);
		model.addAttribute("cList", cService.selectList(cv));
		//이 아래로 알림 지우기
		if(sess.getAttribute("userInfo") != null) {
			int num = ((UserVo)sess.getAttribute("userInfo")).getUserno();
			cv.setUserno(num);
			freeBoardService.updateAnnounce(cv);
		}
		return "freeboard/view";
	}
	
	@GetMapping("/freeboard/edit.do")
	public String edit(Model model, @RequestParam int board_no) {
		model.addAttribute("data", freeBoardService.edit(board_no));
		return "freeboard/edit";
	}
	
	@PostMapping("/freeboard/update.do")
	public String update(Model model, FreeBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			FreeBoardVo bv = freeBoardService.edit(vo.getBoard_no());
			File f = new File(req.getRealPath("/upload/")+bv.getFilename_real());
			f.delete();//실제 서버에서 삭제처리
			vo.setFilename_org("");
			vo.setFilename_real("");
		}
		if (file != null && !file.isEmpty()) {
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf("."));
				String filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real));
				vo.setFilename_org(filename);
				vo.setFilename_real(filename_real);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = freeBoardService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getBoard_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?board_no="+vo.getBoard_no());
		}
		return "include/return";
	}
	
	@GetMapping("/freeboard/delete.do")
	public String delete(Model model, FreeBoardVo vo) {
		int r = freeBoardService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getBoard_no());
		}
		return "include/return";
	}
	
	@GetMapping("/freeboard/deleteAjax.do")
	public String deleteAjax(Model model, FreeBoardVo vo) {
		model.addAttribute("result", freeBoardService.delete(vo));
		return "include/result";
	}
	
}
