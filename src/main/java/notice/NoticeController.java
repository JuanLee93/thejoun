package notice;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import admin.AdminVo;
import freeboard.FreeBoardVo;
import user.UserVo;
import util.CommonUtil;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	@GetMapping("/notice/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, NoticeVo vo) {
		vo.setIs_user('Y');
		int totCount = noticeService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;

		List<NoticeVo> list = noticeService.selectList(vo);

		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		
		return "notice/index";
	}
	
	@GetMapping("/notice/view.do")
	public String view(Model model, @RequestParam int notice_no, HttpServletRequest req, NoticeVo vo) {
		model.addAttribute("data", noticeService.view(notice_no));
		
		vo.setNotice_no(notice_no);
		int Rownum = noticeService.getRownum(vo);
		vo.setRownum(Rownum);
		NoticeVo prev = noticeService.getPrev(vo);
		NoticeVo next = noticeService.getNext(vo);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return "notice/view";
	}
	
	@GetMapping("/admin/notice/index.do")
	public String adminIndex(Model model, HttpServletRequest req, HttpSession sess, NoticeVo vo) {
		
		int totCount = noticeService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;

		List<NoticeVo> list = noticeService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		
		return "admin/notice/index";
	}
	
	@GetMapping("/admin/notice/view.do")
	public String adminView(Model model, @RequestParam int notice_no) {
		model.addAttribute("data", noticeService.view(notice_no));
		
		return "admin/notice/view";
	}
	
	@RequestMapping("/admin/notice/write.do")
	public String write() {
		return "admin/notice/write";
	}
	
	@PostMapping("/admin/notice/insert.do")
	public String insert(NoticeVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) {
		vo.setAdmin_no(((AdminVo)sess.getAttribute("adminInfo")).getAdmin_no());
		// 파일 저장
		String filename = "";
		String filename_real = "";
		
		if (!file.isEmpty()) {//관리자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf("."));
				filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		
		vo.setFilename_org(filename);
		vo.setFilename_real(filename_real);
		
		int r = noticeService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/return";

	}
	
	@GetMapping("/admin/notice/edit.do")
	public String edit(Model model, @RequestParam int notice_no) {
		model.addAttribute("data", noticeService.edit(notice_no));
		return "admin/notice/edit";
	}
	
	@PostMapping("/admin/notice/update.do")
	public String update(Model model, NoticeVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			NoticeVo bv = noticeService.edit(vo.getNotice_no());
			File f = new File(req.getRealPath("/upload/")+bv.getFilename_real());
			f.delete();//실제 서버에서 삭제처리
			vo.setFilename_org("");
			vo.setFilename_real("");
		}
		if (file != null && !file.isEmpty()) {
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				String ext = filename.substring(filename.lastIndexOf("."));
				String filename_real = System.currentTimeMillis() + ext;
				
				file.transferTo(new File(path+filename_real));
				vo.setFilename_org(filename);
				vo.setFilename_real(filename_real);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = noticeService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?notice_no="+vo.getNotice_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?notice_no="+vo.getNotice_no());
		}
		return "include/return";
	}
	
	@GetMapping("/admin/notice/delete.do")
	public String delete(Model model, NoticeVo vo) {
		int r = noticeService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?notice_no="+vo.getNotice_no());
		}
		return "include/return";
	}
	
	@GetMapping("/admin/notice/deleteAjax.do")
	public String deleteAjax(Model model, NoticeVo vo) {
		model.addAttribute("result", noticeService.delete(vo));
		return "include/result";
	}
	
	@PostMapping("/admin/notice/boardDeleteAjax.do")
	public String adminBoardDeleteAjax(HttpServletRequest req, Model model) {
		String[] deleteArray = req.getParameterValues("no");
		for (int i=0; i<deleteArray.length; i++) {
			NoticeVo vo = new NoticeVo();
			vo.setNotice_no(Integer.parseInt(deleteArray[i]));
			noticeService.delete(vo);
		}
		model.addAttribute("msg", "정상적으로 삭제되었습니다.");
		model.addAttribute("url", "index.do");
		
		return "include/return";
	}
	
}
