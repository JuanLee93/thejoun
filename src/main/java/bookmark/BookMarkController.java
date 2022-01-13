package bookmark;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookMarkController {

	@Autowired
	BookMarkService bookmarkservice;
	
	@GetMapping("/bookmarkupdate")
	public String likeupdate(Model model, HttpServletRequest req, BookMarkVo vo){
		
	int r = bookmarkservice.bookmarkUpdate(vo);
	req.setAttribute("result", r);

	return "/include/result";
	}
	
}
