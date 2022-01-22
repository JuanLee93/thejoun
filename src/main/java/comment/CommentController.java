package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import announce.AnnounceService;
import announce.AnnounceVo;

@Controller
public class CommentController {

	@Autowired
	CommentService service;
	
	@Autowired
	AnnounceService as;
	
	@PostMapping("/comment/insert.do")
	public String insert(Model model, CommentVo vo, AnnounceVo av) {
		model.addAttribute("result", service.insert(vo));
		//여기부터 알림
		av.setFriends_userno(vo.getUserno());
		av.setBoardno(vo.getBoard_no());
		av.setBoard_or_comment(1);
		if(av.getMy_userno() != av.getFriends_userno()) {
			if(av.getTablename() == 1) {
				as.announceInsert1(av);
			}else if(av.getTablename() == 2) {
				as.announceInsert2(av);
			}else if(av.getTablename() == 3) {
				as.announceInsert3(av);
			}else if(av.getTablename() == 4) {
				as.announceInsert4(av);
			}
		}
		//여기까지 알림
		return "include/result";
	}
	
	@GetMapping("/comment/list.do")
	public String list(Model model, CommentVo vo) {
		model.addAttribute("cList", service.selectList(vo));
		return "include/commentList";
	}
	
	@GetMapping("/comment/update.do")
	public String update(Model model, CommentVo vo) {
		model.addAttribute("result", service.update(vo));
		return "include/result";
	}
	
	@PostMapping("/comment/insertCommentReply.do")
	public String insertCommentReply(Model model, CommentVo vo, AnnounceVo av, HttpServletRequest req, HttpSession sess) {
		//vo.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		int r = service.insertCommentReply(vo);
		model.addAttribute("result",r);
		
		//여기부터 알림임
		//내가쓴 글에달린 대댓글인것들 insert
		av.setBoardno(vo.getBoard_no());
		av.setFriends_userno(vo.getUserno());
		av.setBoard_or_comment(1);
		if(av.getMy_userno() != av.getFriends_userno()) {
			if(av.getTablename() == 1) {
				as.announceInsert1(av);
			}else if(av.getTablename() == 2) {
				as.announceInsert2(av);
			}else if(av.getTablename() == 3) {
				as.announceInsert3(av);
			}else if(av.getTablename() == 4) {
				as.announceInsert4(av);
			}
		}
		
		//댓글에 대댓글이 달렸을때 알림기준
		av.setBoard_or_comment(2);
		int my_userno = as.findMakeComment(vo.getGno());
		av.setMy_userno(my_userno);
		if(av.getMy_userno() != av.getFriends_userno()) {
			if(av.getTablename() == 1) {
				as.announceInsert1(av);
			}else if(av.getTablename() == 2) {
				as.announceInsert2(av);
			}else if(av.getTablename() == 3) {
				as.announceInsert3(av);
			}else if(av.getTablename() == 4) {
				as.announceInsert4(av);
			}
		}
		//여기까지 알림임
		return "/include/result";
	}
	
	@GetMapping("/comment/delete.do")
	public String delete(Model model, CommentVo vo) {
		model.addAttribute("result", service.delete(vo.getComment_no()));
		return "include/result";
	}
}
