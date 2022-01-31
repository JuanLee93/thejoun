package main;
   
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import announce.AnnounceService;
import announce.AnnounceUserVo;
import concernboard.ConcernBoardService;
import concernboard.ConcernBoardVo;
import freeboard.FreeBoardService;
import freeboard.FreeBoardVo;
import imageboard.ImageBoardService;
import imageboard.ImageBoardVo;
import notice.NoticeService;
import notice.NoticeVo;
import user.UserVo;
import videoboard.VideoBoardService;
import videoboard.VideoBoardVo;

@Controller
public class MainController {
	@Autowired
	FreeBoardService freeBoardService;	
	@Autowired
	ConcernBoardService concernBoardService;
	@Autowired
	VideoBoardService videoBoardService;
	@Autowired
	AnnounceService as;
	@Autowired
	NoticeService noticeService;
	@Autowired
	ImageBoardService imageBoardService;
	
	@GetMapping("/index.do")
	public String index(FreeBoardVo vo , Model model , ConcernBoardVo vo2 , VideoBoardVo vo3 ,NoticeVo vo4 , ImageBoardVo vo5) {
		List<FreeBoardVo> list = freeBoardService.mainSelectList(vo);
		List<ConcernBoardVo> concernlist = concernBoardService.mainSelectList(vo2);
		List<VideoBoardVo> videolist = videoBoardService.mainSelectList(vo3);
		List<NoticeVo> noticelist = noticeService.mainSelectList(vo4);
		List<ImageBoardVo> imagelist = imageBoardService.mainSelectList(vo5);
		model.addAttribute("list", list);
		model.addAttribute("concernlist", concernlist);
		model.addAttribute("videolist", videolist);
		model.addAttribute("noticelist", noticelist);
		model.addAttribute("imagelist", imagelist);
		return "index";
	}


	@GetMapping("/company/index.do")
	public String companyIndex() {
		return "company/index";
	}
	
	@GetMapping("/include/notice.do")
	public String notice(HttpSession sess, Model model) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		List<AnnounceUserVo> userVoList = as.userVoList(uv.getUserno());
		model.addAttribute("userVoList", userVoList);
		int announceCount = as.announceCount(uv.getUserno());
		model.addAttribute("announceCount", announceCount);
		return "include/notice";
	}
	
	@GetMapping("/include/chatMain.do")
	public String chatMain(HttpSession sess, Model model) {
		if(sess.getAttribute("userInfo") != null) {
			UserVo uv = (UserVo)sess.getAttribute("userInfo");
			int chatCountMain = as.chatCountMain(uv.getUserno());
			model.addAttribute("chatCountMain", chatCountMain);
		}
		return "include/chatMain";
	}
	
	@PostMapping("/include/deleteAnnounce.do")
	public String deleteAnnounce(HttpSession sess, Model model) {
		UserVo uv = (UserVo)sess.getAttribute("userInfo");
		int acceptDelete = as.deleteAnnounce(uv.getUserno());
		model.addAttribute("result", acceptDelete);
		return "include/result";
	}


}
