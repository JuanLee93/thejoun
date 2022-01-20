package friendsAdd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import user.UserVo;



//아직 친구 안된사람관련은 여기다가 쓰셈
@Controller
public class FriendsAddController {
	
	@Autowired
	FriendsAddService fas;

	@PostMapping("/friendsAdd/addFriendsButton.do")
	public String addButton(UserVo userVo, FriendsAddVo fav, Model model, HttpSession sess) {
		
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		fav.setFrom_userno(uv.getUserno());
		int friendsUse = fas.friendsUse(fav);
		if(friendsUse > 0) {
		model.addAttribute("result", 0);
		}else {
			
			int complete3 = fas.friendsAddCheck2(fav);
			if(complete3 > 0) {
				model.addAttribute("result", 1);
			}else {
				int complete2 = fas.friendsAddCheck(fav);
				if(complete2 > 0) {
					model.addAttribute("result", 2);
				}else {
					int complete = fas.addButton(fav);
					if(complete > 0){
						model.addAttribute("result", 4);
					}else {
						model.addAttribute("result", 3);
					}
				}
			}
		}
		return "include/result";
	}
	
	
	
}
