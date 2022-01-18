package chat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import friends.FriendsService;
import user.UserVo;

@Controller
public class ChatController {
	
	@Autowired
	ChatService cs;
		
	@GetMapping("/chat/index.do")
	public String chatMain() {
		
		
		return "chat/index";
	}
	
	@PostMapping("/chat/chatFriendsList.do")
	public String chatFriendsList(Model model, HttpSession sess) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		List<UserVo> friendsList = cs.findFriendsList((int)uv.getUserno());
		model.addAttribute("friendsList", friendsList);
		
		return "chat/chatFriendsList";
		
	}
	
	@PostMapping("/chat/findChattingTab.do")
	public String findChatting(HttpSession sess, Model model, ChatVo cv) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		cv.setFrom_id(uv.getUserno());
		int num = cs.messageUpdate(cv);
		UserVo otherUserno = cs.findOtherUser((int)cv.getTo_id());
		List<ChatVo> findChattingList = cs.findChattingList(cv);
		model.addAttribute("findChatting", findChattingList);
		model.addAttribute("otherUserno", otherUserno);
		
		return "chat/message";
		
	}
	
	@PostMapping("/chat/sendMessage.do")
	public String sendMessage(Model model, HttpSession sess, ChatVo cv) {
		int num = cs.messageUpdate(cv);
		int sendMessage = cs.sendMessage(cv);
		List<ChatVo> receiveMessage = cs.findChattingList(cv);
		UserVo otherUserno = cs.findOtherUser((int)cv.getTo_id());
		model.addAttribute("findChatting", receiveMessage);
		model.addAttribute("otherUserno", otherUserno);
		
		return "chat/message";
	}


}
