package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
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
	
	
	
}
