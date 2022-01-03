package chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@GetMapping("/chat/index.do")
	public String chatMain() {
		return "chat/index";
	}
}
