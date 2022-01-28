package chat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		for(int i=0;i<friendsList.size();i++) {
			long nowDate = friendsList.get(i).getRegdateCheck();
			if(nowDate == 0) {
				friendsList.get(i).setCcc("");
			}else if(nowDate >= 86400) {
				System.out.println(nowDate);
				nowDate = nowDate / 86400;
				friendsList.get(i).setCcc((int)nowDate+"일전");
			}else if(nowDate >= 3600) {
				nowDate = nowDate / 3600;
				friendsList.get(i).setCcc((int)nowDate+"시간전");
			}else if(nowDate >= 60) {
				nowDate = nowDate / 60;
				friendsList.get(i).setCcc((int)nowDate+"분전");
			}else {
				friendsList.get(i).setCcc((int)nowDate+"초전");
			}
			
		}
		
		// Timestamp 형식 바꾸기용 할지안할지 모름 ㅎ
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//        System.out.println(sdf.format(timestamp)); // format을 사용해 출력
		
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
