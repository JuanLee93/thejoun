package concernboard;

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
import freeboard.FreeBoardVo;
import friends.FriendsService;
import user.UserVo;
import util.CommonUtil;

@Controller
public class ConcernBoardController {

	@Autowired
	ConcernBoardService concernBoardService;
	
	@Autowired
	CommentService cService;
	//여기아래 오토와이어드부터 알림창 insert 하는데 필요함
	@Autowired
	FriendsService fs;
	
	@Autowired
	AnnounceService as;
	
	@GetMapping("/concernboard/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, ConcernBoardVo vo) {
		vo.setIs_user('Y');
		int totCount = concernBoardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		String param = "&orderby="+vo.getOrderby()+"&category="+vo.getCategory();
		
		List<ConcernBoardVo> list = concernBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10, param));
		return "concernboard/index";
	}
	//새로넣은 관리자 페이지 맵핑
	@GetMapping("/admin/concernboard/index.do")
	public String adminIndex(Model model, HttpServletRequest req, HttpSession sess, ConcernBoardVo vo) {
		
		int totCount = concernBoardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);
		
		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		String param = "&orderby="+vo.getOrderby()+"&category="+vo.getCategory(); 
		
		List<ConcernBoardVo> list = concernBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10, param));
		return "admin/concernboard/index";
	}

	@PostMapping("/admin/concernboard/insert.do")
	public String adminInsert(ConcernBoardVo vo,AnnounceVo av, HttpServletRequest req, MultipartFile file, HttpSession sess) {
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
		
		int r = concernBoardService.adminInsert(vo);
		
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
		
	
	//관리자에서 게시물 view 확인
	@GetMapping("admin/concernboard/view.do")
	public String adminView(Model model, @RequestParam int board_no) {
		model.addAttribute("data", concernBoardService.view(board_no));
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(2);
		model.addAttribute("cList", cService.selectList(cv));
		return "admin/concernboard/view";
	}
	
	@RequestMapping("/admin/concernboard/write.do")
	public String adminWrite() {
		return "admin/concernboard/write";
	}
	
	@GetMapping("/admin/concernboard/edit.do")
	public String adminEdit(Model model, @RequestParam int board_no) {
		model.addAttribute("data", concernBoardService.adminEdit(board_no));
		return "admin/concernboard/edit";
	}
		
	@PostMapping("/admin/concernboard/update.do")
	public String adminUpdate(Model model, ConcernBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			ConcernBoardVo bv = concernBoardService.adminEdit(vo.getConcern_board_no());
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
		
		int r = concernBoardService.adminUpdate(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getConcern_board_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?board_no="+vo.getConcern_board_no());
		}
		return "include/return";
	}
	@PostMapping("/admin/concernboard/boardDeleteAjax.do")
	public String adminBoardDeleteAjax(HttpServletRequest req, Model model) {
		String[] deleteArray = req.getParameterValues("no");
		for (int i=0; i<deleteArray.length; i++) {
			ConcernBoardVo vo = new ConcernBoardVo();
			vo.setConcern_board_no(Integer.parseInt(deleteArray[i]));
			concernBoardService.delete(vo);
		}
		model.addAttribute("msg", "정상적으로 삭제되었습니다.");
		model.addAttribute("url", "index.do");
		
		return "include/return";
	}
	
	@PostMapping("/admin/concernboard/noticeUpdateAjax.do")
	public String adminNoticeUpdateAjax(HttpServletRequest req) {
		String[] updateArray = req.getParameterValues("no");
		for (int i=0; i<updateArray.length; i++) {
			ConcernBoardVo vo = new ConcernBoardVo();
			vo.setConcern_board_no(Integer.parseInt(updateArray[i]));
			concernBoardService.updateNotice(vo);
		}
		return "include/result";
	}
	
	@RequestMapping("/concernboard/write.do")
	public String write() {
		return "concernboard/write";
	}
	
	@PostMapping("/concernboard/insert.do")
	public String insert(ConcernBoardVo vo,AnnounceVo av, HttpServletRequest req, MultipartFile file, HttpSession sess) {
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
		
		int r = concernBoardService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
			//여기부터 알림임
			av.setBoardno(vo.getConcern_board_no());
			av.setCommentno(0);
			av.setFriends_userno(((UserVo)sess.getAttribute("userInfo")).getUserno());
			av.setBoard_or_comment(0);
			//FriendsService 에 메서드로 가서 내 userno기준으로 친구된 사람 리스트로 받아와야함
			List<Integer> my_userno = fs.getFriendsUserno(av);
			for(int i=0;i<my_userno.size();i++) {
				av.setMy_userno(my_userno.get(i));
				int announceCheck = as.announceInsert2(av);
			}
			//여기까지 알림임
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
			return "/include/return";
		}
	
	@GetMapping("/concernboard/view.do")
	public String view(Model model, HttpSession sess, @RequestParam int board_no, HttpServletRequest req, ConcernBoardVo vo) {
		model.addAttribute("data", concernBoardService.view(board_no));
		
		vo.setIs_user('Y');
		vo.setConcern_board_no(board_no);
		int Rownum = concernBoardService.getRownum(vo);
		vo.setRownum(Rownum);
		ConcernBoardVo prev = concernBoardService.getPrev(vo);
		ConcernBoardVo next = concernBoardService.getNext(vo);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(2);
		model.addAttribute("cList", cService.selectList(cv));
		//이 아래로 알림 지우기
		if(sess.getAttribute("userInfo") != null) {
			int num = ((UserVo)sess.getAttribute("userInfo")).getUserno();
			cv.setUserno(num);
			concernBoardService.updateAnnounce(cv);
		}
		return "concernboard/view";
	}
	
	
	@GetMapping("/concernboard/edit.do")
	public String edit(Model model, @RequestParam int board_no) {
		model.addAttribute("data", concernBoardService.edit(board_no));
		return "concernboard/edit";
	}
	
	@PostMapping("/concernboard/update.do")
	public String update(Model model, ConcernBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			ConcernBoardVo bv = concernBoardService.edit(vo.getConcern_board_no());
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
		
		int r = concernBoardService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getConcern_board_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?board_no="+vo.getConcern_board_no());
		}
		return "include/return";
	}
	
	@GetMapping("/concernboard/delete.do")
	public String delete(Model model, ConcernBoardVo vo) {
		int r = concernBoardService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getConcern_board_no());
		}
		return "include/return";
	}
	
	@GetMapping("/concernboard/deleteAjax.do")
	public String deleteAjax(Model model, ConcernBoardVo vo) {
		model.addAttribute("result", concernBoardService.delete(vo));
		return "include/result";
	}
	@GetMapping("/admin/concernboard/deleteAjax.do")
	public String adminDeleteAjax(Model model, ConcernBoardVo vo) {
		model.addAttribute("result", concernBoardService.delete(vo));
		return "include/result";
	}
}
