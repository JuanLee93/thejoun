package bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookMarkController {

	@Autowired
	BookMarkService bookmarkservice;
	
	@GetMapping("/bookmarkupdate")
	public String likeupdate(Model model, BookMarkVo vo){
		
		int r = bookmarkservice.bookmarkUpdate(vo);
		model.addAttribute("result", r);
		return "/include/result";
	}
	
}
