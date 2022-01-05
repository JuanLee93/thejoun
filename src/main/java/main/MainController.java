package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
		
	@GetMapping("/index.do")
	public String index() {
		return "index";
	}

	@GetMapping("/company/index.do")
	public String companyIndex() {
		return "company/index";
	}


}
