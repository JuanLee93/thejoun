package user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import concernboard.ConcernBoardService;
import freeboard.FreeBoardService;
import freeboard.FreeBoardVo;
import imageboard.ImageBoardService;
import util.CommonUtil;
import videoboard.VideoBoardService;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	FreeBoardService freeboardService;
	@Autowired
	ConcernBoardService concernBoardService;
	@Autowired
	ImageBoardService imageBoardService;
	@Autowired
	VideoBoardService videoBoardService;
	
	//관리자페이지의 회원관리
	@GetMapping("/admin/member/index.do")
	public String userIndex(Model model, HttpServletRequest req, HttpSession sess, UserVo vo) {
		
		int totCount = userService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;


		
		List<UserVo> list = userService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		return "admin/member/index";

	}
	
	
	
	@GetMapping("/user/login.do")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/user/login.do")
	public String loginProcess(Model model, UserVo vo, HttpSession sess) {
		if(userService.login(vo,sess)) {
			return "redirect:/index.do";
		}else {
			model.addAttribute("msg", "아이디, 비밀번호를 확인해주세요");
			return "include/return";
		}
	}
	
	@PostMapping("/user/infoEdit.do")
	public String infoEditDetails(Model model, UserVo vo, HttpSession sess) {
		if(userService.login(vo,sess)) {
			return "/user/infoEditDetails";
		}else {
			model.addAttribute("msg", "아이디, 비밀번호를 확인해주세요");
			return "include/return";
		}
	}
	
	@GetMapping("/user/logout.do")
	public String logout(Model model, HttpSession sess) {
		sess.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/thejoun/index.do");
		return "include/return";
	}
	
	@GetMapping("/user/join.do")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/user/insert.do")
	public String insert(UserVo vo,HttpServletRequest req) {
		if(userService.insert(vo) > 0) {
			req.setAttribute("msg", "정상적으로 가입되었습니다");
			req.setAttribute("url", "/thejoun/index.do");
		}else {
			req.setAttribute("msg", "가입 오류");
		}
		return "include/return";
	}
	
	@GetMapping("/user/idCheck.do")
	public String idCheck(Model model, @RequestParam String id) {
		model.addAttribute("result", userService.idCheck(id));
		return "include/result";
	}
	
	@GetMapping("/user/mypage.do")
	public String mypage() {
		return "user/mypage";
	}
	
	//테스트용
	@GetMapping("/user/test.do")
	public String test() {
		return "user/test";
	}
	@RequestMapping(value="/user/searchId.do", method=RequestMethod.GET)
	public String searchId() {
		return "user/searchId";
	}
	
	@RequestMapping(value="/user/searchId.do", method=RequestMethod.POST)
	public String searchId(Model model, UserVo vo) {
		UserVo uv = userService.searchId(vo);
		//String email = "";
		//if (uv != null) email = uv.getEmail();
		model.addAttribute("result", uv == null ? "" : uv.getEmail());
		return "include/result";
	}
	
	@RequestMapping(value="/user/searchPwd.do", method=RequestMethod.GET)
	public String searchPwd() {
		return "user/searchPwd";
	}
	@RequestMapping(value="/user/searchPwd.do", method=RequestMethod.POST)
	public String searchPwd(Model model, UserVo vo) {
		UserVo uv = userService.searchPwd(vo);
		//String email = "";
		//if (uv != null) email = uv.getEmail();
		model.addAttribute("result", uv == null ? "" : "ok");
		return "include/result";
	}
	
	@GetMapping("/user/myinfo.do")
	public String myinfo(HttpSession sess) {
		return "user/myinfo";
	}
	
	@GetMapping("/user/infoEdit.do")
	public String infoEdit() {
		return "user/infoEdit";
	}
	
	@GetMapping("/user/pwdChange.do")
	public String pwdChange() {
		return "user/pwdChange";
	}
	
	@GetMapping("/user/infoEditDetails.do")
	public String infoEditDetails() {
		return "user/infoEditDetails";
	}
	
	@PostMapping("/user/infoUpdate.do")
	public String infoUpdate(HttpServletRequest req, UserVo vo) {
		if(userService.infoUpdate(vo) > 0) {
			req.setAttribute("msg", "정상적으로 수정되었습니다");
			req.setAttribute("url", "/thejoun/user/mypage.do");
		}else {
			req.setAttribute("msg", "수정 오류");
		}
		return "include/return";
	}
	
	@PostMapping("/user/pwdChange.do")
	public String pwdChange(HttpServletRequest req, UserVo vo) {
		if(userService.pwdChange(vo) > 0) {
			req.setAttribute("msg", "비밀번호가 수정되었습니다");
			req.setAttribute("url", "/thejoun/user/mypage.do");
		}else {
			req.setAttribute("msg", "비밀번호를 확인해주세요");
		}
		return "include/return";
	}
	
	//자유게시판에 쓴 자신의 글 목록을 출력
	@GetMapping("/user/myBoardConfirm.do")
	public String myBoardConfirm(Model model, HttpServletRequest req, FreeBoardVo fbv, HttpSession sess) {
		fbv.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		
		int totCount = freeboardService.count(fbv); //총 개수
		int totPage = totCount / 10; //총 페이지수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage:"+totPage);
		
		int startIdx = (fbv.getPage() -1) * 10;
		fbv.setStartIdx(startIdx);
		
		List<FreeBoardVo> list = freeboardService.selectList(fbv); 
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", fbv.getPage(), totPage, 10));
		return "user/myBoardConfirm";
	}
	
	@GetMapping("/user/myFriendsConfirm.do")
	public String myFriendsConfirm() {
		return "user/myFriendsConfirm";
	}
	
	@GetMapping("/user/bookmark.do")
	public String bookmark() {
		return "user/bookmark";
	}
	
	@GetMapping("/user/blockFriends.do")
	public String blockFriends() {
		return "user/blockFriends";
	}
	
	@GetMapping("/user/myInquiry.do")
	public String myInquiry() {
		return "user/myInquiry";
	}
	
}
