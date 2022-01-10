package like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LikeController {

	@Autowired
	LikeService likeservice;
	
	@GetMapping("/likeupdate")
	public String likeupdate(Model model, LikeVo vo){
		
		int r = likeservice.likeUpdate(vo);
		model.addAttribute("result", r);
		return "/include/result";
	}
	
}
