package videoboard;

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

import announce.AnnounceService;
import announce.AnnounceVo;
import comment.CommentService;
import comment.CommentVo;
import friends.FriendsService;
import user.UserVo;
import util.CommonUtil;

@Controller
public class VideoBoardController {
	@Autowired
	VideoBoardService videoBoardService;
	
	@Autowired
	CommentService cService;
	//여기아래 오토와이어드부터 알림창 insert 하는데 필요함
	@Autowired
	FriendsService fs;
	
	@Autowired
	AnnounceService as;
	
	@GetMapping("/videoboard/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, VideoBoardVo vo) {
		
		int totCount = videoBoardService.count(vo); //총 개수
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
		
		List<VideoBoardVo> list = videoBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10, param));
		return "videoboard/index";
	}
	
	//관리자 페이지에 index 추가
	@GetMapping("/admin/videoboard/index.do")
	public String adminIndex(Model model, HttpServletRequest req, HttpSession sess, VideoBoardVo vo) {
		
		int totCount = videoBoardService.count(vo); //총 개수
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
		
		List<VideoBoardVo> list = videoBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10, param));
		return "admin/videoboard/index";
	}
	//여기까지 관리자 페이지에 추가
	
	//관리자 페이지에 view 추가
	@GetMapping("/admin/videoboard/view.do")
	public String adminView(Model model, @RequestParam int board_no) {
		model.addAttribute("data", videoBoardService.view(board_no));
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(4);
		model.addAttribute("cList", cService.selectList(cv));
		return "admin/videoboard/view";
	}
	
	@GetMapping("/admin/videoboard/deleteAjax.do")
	public String adminDeleteAjax(Model model, VideoBoardVo vo) {
		model.addAttribute("result", videoBoardService.delete(vo));
		return "include/result";
	}
	//여기까지 관리자 페이지에 추가
	
	@RequestMapping("/videoboard/write.do")
	public String write() {
		return "videoboard/write";
	}
	
	@PostMapping("/videoboard/insert.do")
	public String insert(VideoBoardVo vo, AnnounceVo av, HttpServletRequest req, MultipartFile file, HttpSession sess) {
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
		if (vo.getUrl() != null && vo.getUrl().indexOf("v=") >= 0)
			vo.setUrl("https://www.youtube.com/embed/" + vo.getUrl().split("v=")[1]);
		int r = videoBoardService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
			//여기부터 알림임
			av.setBoardno(vo.getVideo_board_no());
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
	
	@GetMapping("/videoboard/view.do")
	public String view(Model model, @RequestParam int board_no) {
		model.addAttribute("data", videoBoardService.view(board_no));
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(4);
		model.addAttribute("cList", cService.selectList(cv));
		return "videoboard/view";
	}
	
	@GetMapping("/videoboard/edit.do")
	public String edit(Model model, @RequestParam int board_no) {
		model.addAttribute("data", videoBoardService.edit(board_no));
		return "videoboard/edit";
	}
	
	@PostMapping("/videoboard/update.do")
	public String update(Model model, VideoBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			VideoBoardVo bv = videoBoardService.edit(vo.getVideo_board_no());
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
		if (vo.getUrl() != null && vo.getUrl().indexOf("v=") >= 0)
			vo.setUrl("https://www.youtube.com/embed/" + vo.getUrl().split("v=")[1]);
		int r = videoBoardService.update(vo);

		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getVideo_board_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?board_no="+vo.getVideo_board_no());
		}
		return "include/return";
	}
	
	@GetMapping("/videoboard/delete.do")
	public String delete(Model model, VideoBoardVo vo) {
		int r = videoBoardService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getVideo_board_no());
		}
		return "include/return";
	}
	
	@GetMapping("/videoboard/deleteAjax.do")
	public String deleteAjax(Model model, VideoBoardVo vo) {
		model.addAttribute("result", videoBoardService.delete(vo));
		return "include/result";
	}
}
