package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adminLog.AdminLogServiceImpl;
import adminLog.AdminLogVo;
import freeboard.FreeBoardVo;
import user.UserVo;
import util.CommonUtil;

@Controller
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	AdminLogServiceImpl adminLogService;
	
	@GetMapping("/admin/index.do")
	public String index() {
		return "admin/index";
	}
	
	// 아래 board는 삭제예정
	@GetMapping("/admin/board/index.do")
	public String index2() {
		return "admin/board/index";
	}
	 
	@GetMapping("/admin/board/view.do")
	public String view() {
		return "admin/board/view";
	}
	
	@GetMapping("/admin/board/write.do")
	public String write() {
		return "admin/board/write";
	}	
	
	@GetMapping("/admin/login.do")
	public String login() {
		return "admin/index";
	}
	
	@GetMapping("/admin/admin/idCheck.do")
	public String idCheck(Model model, @RequestParam String id) {
		model.addAttribute("result", adminService.idCheck(id));
		return "include/result";
	}
	
	@PostMapping("/admin/login.do")
	public String loginProcess(Model model, AdminVo vo, HttpSession sess, HttpServletRequest req) {
		if(adminService.login(vo,sess)) {
			
			AdminLogVo logvo = new AdminLogVo();
			logvo.setAdmin_no(((AdminVo)sess.getAttribute("adminInfo")).getAdmin_no());
			logvo.setMsg(((AdminVo)sess.getAttribute("adminInfo")).getName()+", 관리자 로그인");
			logvo.setLogin_IP(req.getRemoteAddr());
			adminLogService.insert(logvo);
			
			return "redirect:/admin/board/index.do";
		}else {
			model.addAttribute("msg", "아이디, 비밀번호를 확인해주세요.");
			return "include/return";
		}
	}
	
	@GetMapping("/admin/logout.do")
	public String logout(Model model, HttpSession sess) {
		sess.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/thejoun/admin/index.do");
		return "include/return";
	}
	
	@GetMapping("admin/admin/index.do")
	public String adminIndex(Model model, HttpServletRequest req, HttpSession sess, AdminVo vo) {
		
		int totCount = adminService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		List<AdminVo> list = adminService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		return "admin/admin/index";
	}
	
	@GetMapping("/admin/admin/view.do")
	public String adminView(Model model, @RequestParam int admin_no) {
		model.addAttribute("data", adminService.view(admin_no));

		return "admin/admin/view";
	}
	
	@GetMapping("/admin/admin/adminAdd.do")
	public String join() {
		return "admin/admin/adminAdd";
	}
	
	@PostMapping("/admin/admin/insert.do")
	public String insert(AdminVo vo, HttpServletRequest req) {
		if(adminService.insert(vo) > 0) {
			req.setAttribute("msg", "정상적으로 관리자를 등록했습니다.");
			req.setAttribute("url", "/thejoun/admin/admin/index.do");
		}else {
			req.setAttribute("msg", "관리자 등록 중 오류가 발생하였습니다.");
		}
		return "include/return";
	}
	
	@GetMapping("/admin/admin/edit.do")
	public String edit(Model model, @RequestParam int admin_no) {
		model.addAttribute("data", adminService.edit(admin_no));
		return "admin/admin/edit";
	}
	
	@PostMapping("/admin/admin/update.do")
	public String update(Model model, AdminVo vo, HttpServletRequest req) {
		
		int r = adminService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?admin_no="+vo.getAdmin_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?admin_no="+vo.getAdmin_no());
		}
		return "include/return";
	}
	
	@GetMapping("/admin/admin/delete.do")
	public String adminadminDelete(Model model, AdminVo vo) {
		int r = adminService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?admin_no="+vo.getAdmin_no());
		}
		return "include/return";
	}
	
	@GetMapping("/admin/admin/deleteAjax.do")
	public String adminadminDeleteAjax(Model model, AdminVo vo) {
		model.addAttribute("result", adminService.delete(vo));
		return "include/result";
	}
	
	@PostMapping("/admin/admin/boardDeleteAjax.do")
	public String adminBoardDeleteAjax(HttpServletRequest req, Model model) {
		String[] deleteArray = req.getParameterValues("no");
		for (int i=0; i<deleteArray.length; i++) {
			AdminVo vo = new AdminVo();
			vo.setAdmin_no(Integer.parseInt(deleteArray[i]));
			adminService.delete(vo);
		}
		model.addAttribute("msg", "정상적으로 삭제되었습니다.");
		model.addAttribute("url", "index.do");
		
		return "include/return";
	}
	
}
