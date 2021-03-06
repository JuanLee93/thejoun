package user;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import concernboard.ConcernBoardService;
import freeboard.FreeBoardService;
import freeboard.FreeBoardVo;
import friends.FriendsVo;
import imageboard.ImageBoardService;
import myPage.MyPageFriendsVo;
import myPage.MyPageService;
import myPage.MyPageVo;
import question.QuestionService;
import question.QuestionVo;
import util.CommonUtil;
import videoboard.VideoBoardService;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	FreeBoardService freeboardService;
	@Autowired
	ConcernBoardService concernBoardService;
	@Autowired
	ImageBoardService imageBoardService;
	@Autowired
	VideoBoardService videoBoardService;
	@Autowired
	QuestionService questionService;
	
	@Autowired
	MyPageService mps;
	
	//관리자페이지의 회원관리
	@GetMapping("/admin/member/index.do")
	public String userIndex(Model model, HttpServletRequest req, HttpSession sess, UserVo vo) {
		
		int totCount = userService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;


		
		List<UserVo> list = userService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		return "admin/member/index";

	}
	
	
	
	@GetMapping("/user/login.do")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/user/login.do")
	public String loginProcess(Model model, UserVo vo, HttpSession sess) {
		if(userService.login(vo,sess)) {
			return "redirect:/index.do";
		}else {
			model.addAttribute("msg", "아이디, 비밀번호를 확인해주세요");
			return "include/return";
		}
	}
	
	@PostMapping("/user/infoEdit.do")
	public String infoEditDetails(Model model, UserVo vo, HttpSession sess) {
		if(userService.login(vo,sess)) {
			return "/user/infoEditDetails";
		}else {
			model.addAttribute("msg", "아이디, 비밀번호를 확인해주세요");
			return "include/return";
		}
	}
	
	@GetMapping("/user/logout.do")
	public String logout(Model model, HttpSession sess) {
		sess.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/thejoun/index.do");
		return "include/return";
	}
	
	@GetMapping("/user/join.do")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/user/insert.do")
	public String insert(UserVo vo,HttpServletRequest req) {
		if(userService.insert(vo) > 0) {
			req.setAttribute("msg", "정상적으로 가입되었습니다");
			req.setAttribute("url", "/thejoun/index.do");
		}else {
			req.setAttribute("msg", "가입 오류");
		}
		return "include/return";
	}
	
	@GetMapping("/user/idCheck.do")
	public String idCheck(Model model, @RequestParam String id) {
		model.addAttribute("result", userService.idCheck(id));
		return "include/result";
	}
	
	@GetMapping("/user/mypage.do")
	public String mypage() {
		return "user/mypage";
	}
	
	//테스트용
	@GetMapping("/user/test.do")
	public String test() {
		return "user/test";
	}
	@RequestMapping(value="/user/searchId.do", method=RequestMethod.GET)
	public String searchId() {
		return "user/searchId";
	}
	
	@RequestMapping(value="/user/searchId.do", method=RequestMethod.POST)
	public String searchId(Model model, UserVo vo) {
		UserVo uv = userService.searchId(vo);
		//String email = "";
		//if (uv != null) email = uv.getEmail();
		model.addAttribute("result", uv == null ? "" : uv.getEmail());
		return "include/result";
	}
	
	@RequestMapping(value="/user/searchPwd.do", method=RequestMethod.GET)
	public String searchPwd() {
		return "user/searchPwd";
	}
	@RequestMapping(value="/user/searchPwd.do", method=RequestMethod.POST)
	public String searchPwd(Model model, UserVo vo) {
		UserVo uv = userService.searchPwd(vo);
		//String email = "";
		//if (uv != null) email = uv.getEmail();
		model.addAttribute("result", uv == null ? "" : "ok");
		return "include/result";
	}
	
	@GetMapping("/user/myinfo.do")
	public String myinfo(HttpSession sess) {
		return "user/myinfo";
	}
	
	@GetMapping("/user/infoEdit.do")
	public String infoEdit() {
		return "user/infoEdit";
	}
	
	@GetMapping("/user/pwdChange.do")
	public String pwdChange() {
		return "user/pwdChange";
	}
	
	@GetMapping("/user/infoEditDetails.do")
	public String infoEditDetails() {
		return "user/infoEditDetails";
	}
	
	@PostMapping("/user/infoUpdate.do")
	public String infoUpdate(HttpServletRequest req, UserVo vo, HttpSession sess, MultipartFile file) {
		vo.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno()); 
		//파일저장
		if (!file.isEmpty()) { // 사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf(".")); // 확장자 (.jpg)
				String filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real)); // 경로에 파일을 저장
				vo.setFilename_org(filename); // vo에 set
				vo.setFilename_real(filename_real);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = userService.infoUpdate(vo);
		
		if(r > 0) {
			req.setAttribute("msg", "정상적으로 수정되었습니다. 다시 로그인 해주세요.");
			req.setAttribute("url", "/thejoun/user/login.do");
			sess.invalidate();
		}else {
			req.setAttribute("msg", "수정 오류");
		}
		return "include/return";
	}
	
	@PostMapping("/user/pwdChange.do")
	public String pwdChange(HttpServletRequest req, UserVo vo) {
		if(userService.pwdChange(vo) > 0) {
			req.setAttribute("msg", "비밀번호가 수정되었습니다");
			req.setAttribute("url", "/thejoun/user/mypage.do");
		}else {
			req.setAttribute("msg", "비밀번호를 확인해주세요");
		}
		return "include/return";
	}
	
	//자유게시판에 쓴 자신의 글 목록을 출력
	@GetMapping("/user/myBoardConfirm.do")
	public String myBoardConfirm(Model model, FreeBoardVo fbv, HttpSession sess, UserVo vo) {
		fbv.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		
		int totCount = userService.countMyBoard(fbv); //총 개수
		int totPage = totCount / 10; //총 페이지수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage:"+totPage);
		
		int startIdx = (fbv.getPage() -1) * 10;
		fbv.setStartIdx(startIdx);
		
		int pageRange = 10;
		int startPage = (fbv.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		List<UserVo> list = userService.selectMyBoard(fbv);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("myBoardConfirm.do", vo.getPage(), totPage, 10));
		return "user/myBoardConfirm";
	}
	
	@GetMapping("/user/myFriendsConfirm.do")
	public String myFriendsConfirm(HttpSession sess, Model model) {
		
		List<MyPageFriendsVo> list = mps.findFriendsList(((UserVo)sess.getAttribute("userInfo")).getUserno());
		model.addAttribute("list", list);
		return "user/myFriendsConfirm";
	}
	
	@PostMapping("/user/deleteFriend.do")
	public String deleteFriend(FriendsVo fv, Model model, HttpSession sess) {
		fv.setMy_userno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		int num = mps.deleteFriend(fv);
		if(num > 0) {
			model.addAttribute("msg", "친구목록에서 삭제되었습니다.");
			model.addAttribute("url", "/thejoun/user/myFriendsConfirm.do");
		}else {
			model.addAttribute("msg", "삭제오류.");
		}
		return "include/return";
	}
	
	@GetMapping("/user/bookmark.do")
	public String bookmark(Model model, HttpSession sess, UserVo vo) {
		vo.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		int totCount = mps.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;


		
		List<MyPageVo> list = mps.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("bookmark.do", vo.getPage(), totPage, 10));
		return "user/bookmark";
	}
	
	@GetMapping("/user/blockFriends.do")
	public String blockFriends() {
		return "user/blockFriends";
	}
	
	// 자신이 쓴 1:1문의 글 목록을 출력
	@GetMapping("/user/myInquiry.do")
	public String myInquiry(Model model, QuestionVo qv, HttpSession sess, @RequestParam(value="pageCount",required=false, defaultValue = "10") Integer pageCount) {
		qv.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		
		if (pageCount == null) {
			pageCount = 10;
		}
		
		int totCount = questionService.count(qv); //총 개수
		int totPage = totCount / pageCount; //총 페이지 수
		if (totCount % pageCount > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (qv.getPage()-1) * pageCount;
		qv.setStartIdx(startIdx);
		qv.setPageCount(pageCount);

		int pageRange = 10;
		int startPage = (qv.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		List<QuestionVo> list = questionService.selectList(qv); 
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("myInquiry.do", qv.getPage(), totPage, 10));
		return "user/myInquiry";
	}

}
