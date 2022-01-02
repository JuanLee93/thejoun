package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
		
	@GetMapping("/index.do")
	public String index() {
		return "index";
	}
	@GetMapping("/index2.do")
	public String index2() {
		return "index2";
	}


}
