package adminLog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.CommonUtil;

@Controller
public class AdminLogController {

	@Autowired
	AdminLogServiceImpl adminService;
	
	@GetMapping("/admin/loginInfo/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, AdminLogVo vo) {
		
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
		
		List<AdminLogVo> list = adminService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		return "admin/loginInfo/index";
	}
	
	@GetMapping("/admin/loginInfo/view.do")
	public String adminView(Model model, @RequestParam int admin_no) {
		model.addAttribute("data", adminService.view(admin_no));

		return "admin/loginInfo/view";
	}
	
	@PostMapping("/admin/loginInfo/insert.do")
	public String insert(AdminLogVo vo, HttpServletRequest req) {
		if(adminService.insert(vo) > 0) {
			req.setAttribute("msg", "정상적으로 로그인 내역을 등록했습니다.");
			req.setAttribute("url", "/thejoun/admin/loginInfo/index.do");
		}else {
			req.setAttribute("msg", "관리자 로그인 내역 등록 중 오류가 발생하였습니다.");
		}
		return "include/return";
	}
	
}
