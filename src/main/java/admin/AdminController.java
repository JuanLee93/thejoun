package admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminController {

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

}
