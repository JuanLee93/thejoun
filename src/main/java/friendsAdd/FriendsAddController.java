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

	
	//친구추가할라고 이가없으면 잇몸으로해야하니까 게시판에서 친구추가하는데
	//게시판이 없으니 그냥 한 페이지에다가 나를 제외한 다른 모든 유저리스트를 받아서
	//거기서 친구추가할라고 만듬 
	//나를 제외한 모든 유저를 출력시키게하는 매서드?
//	@PostMapping("/friendsAdd/addFriends.do")
//	public String friendsAddIndex(UserVo vo, HttpServletRequest req, Model model, HttpSession sess) {
//		
//		UserVo uv = (UserVo)sess.getAttribute("userInfo");
//		List<UserVo> list = fas.selectList((int)uv.getUserno());
//		model.addAttribute("list", list);
//		
//		
//		return "friendsAdd/index";
//	}
	
	@PostMapping("/friendsAdd/addFriendsButton.do")
	public String addButton(UserVo userVo, FriendsAddVo fav, Model model, HttpSession sess) {
		
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		fav.setFrom_userno(uv.getUserno());
		int complete2 = fas.friendsAddCheck(fav);
		if(complete2 > 0) {
			model.addAttribute("msg", "이미 요청한 유저입니다.");
		}else {
			int complete = fas.addButton(fav);
			if(complete > 0){
				model.addAttribute("msg", "친구신청 완료");
			}else {
				model.addAttribute("msg", "신청오류");
			}
		}
		return "include/return";
	}
	
	
	
}
