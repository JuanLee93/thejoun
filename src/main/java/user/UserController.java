package user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.CommonUtil;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
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
	
}
