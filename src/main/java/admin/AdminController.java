package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;
	
	@GetMapping("/admin/index.do")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("/admin/board/index.do")
	public String index2() {
		return "admin/board/index";
	}
	 
	@GetMapping("/admin/board/view.do")
	public String view() {
		return "admin/board/view";
	}
	
	@GetMapping("/admin/board/write.do")
	public String write() {
		return "admin/board/write";
	}	
	
	@GetMapping("/admin/login.do")
	public String login() {
		return "admin/index";
	}
	
	@PostMapping("/admin/login.do")
	public String loginProcess(Model model, AdminVo vo, HttpSession sess) {
		if(adminService.login(vo,sess)) {
			//vo.setLog(); 로그인 시, 관리자 로그 남기기
			return "redirect:/admin/board/index.do";
		}else {
			model.addAttribute("msg", "아이디, 비밀번호를 확인해주세요.");
			return "include/return";
		}
	}
	
	@GetMapping("/admin/logout.do")
	public String logout(Model model, HttpSession sess) {
		sess.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/thejoun/admin/index.do");
		return "include/return";
	}

	@PostMapping("/admin/insert.do")
	public String insert(AdminVo vo,HttpServletRequest req) {
		if(adminService.insert(vo) > 0) {
			req.setAttribute("msg", "정상적으로 관리자를 등록했습니다.");
			req.setAttribute("url", "/thejoun/admin/admin/index.do");
		}else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
		}
		return "include/return";
	}
	
}
