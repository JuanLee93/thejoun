package friends;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import friendsAdd.FriendsAddVo;
import user.UserVo;



// 이미 친구가 된 부류 여기다 쓰셈
@Controller
public class FriendsController {
	
	@Autowired
	FriendsService fs;

	@PostMapping("friends/getFriendsList.do")
	public String friendsIndex(UserVo vo, FriendsAddVo fav, HttpSession sess, Model model) {
		
		//세션에 저장되있는 로그인정보
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		fav.setTo_userno(uv.getUserno());
		List<UserVo> getFriendsList = fs.selectFriendsAddList(fav);
		model.addAttribute("getFriendsList", getFriendsList);
		List<UserVo> addFriendsList = fs.selectAddFriendsList(fav);
		model.addAttribute("addFriendsList", addFriendsList);
		
		return "chat/addFriendsList";
	}
	
	@PostMapping("friends/getAccept.do")
	public String getAccept(UserVo vo, FriendsAddVo fav, HttpSession sess, Model model) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		fav.setTo_userno(uv.getUserno());
		int deleteFriendsAddList = fs.deleteFriendsAddList(fav);
		if(deleteFriendsAddList > 0) {
//			vo.setUserno(fav.getFrom_userno());         //uservo 쓸 필요 없을것같아서 주석처리함
			int addFriends = fs.friendsInsert(fav);
			if(addFriends > 0) {
				model.addAttribute("result", 2);
			}else{
				model.addAttribute("result", 1);
			}
		}else{
			model.addAttribute("result", 0);
		}
		return "include/result";
	}
	
	//채팅창에서 친구요청온거 거절할때 부르는 컨트롤러
	@PostMapping("friends/deleteFriends.do")
	public String deleteFriends(FriendsAddVo fav, HttpSession sess, Model model) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		fav.setTo_userno(uv.getUserno());
		int deleteFriendsAddList = fs.deleteFriendsAddList(fav);
		if(deleteFriendsAddList > 0) {
			model.addAttribute("result", "이거는 오류가 날수가 없는데.. 왜났지");
		}else{
			model.addAttribute("result", "이 유저에게 친구신청을 받은적이 없어요");
		}
		return "include/result";
	}
	
	@GetMapping("/friends/friendsList.do")
	public String friendsList(UserVo vo, FriendsVo fv, Model model, HttpSession sess) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		List<UserVo> friendsList = fs.findFriendsList(uv.getUserno());
		model.addAttribute("friendsList", friendsList);
		return "friends/friendsList";
	}
			
}












