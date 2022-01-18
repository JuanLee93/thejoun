package question;

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
import user.UserVo;
import util.CommonUtil;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("/admin/question/index.do")
	public String adminIndex(Model model, HttpServletRequest req, HttpSession sess, QuestionVo vo) {
		
		int totCount = questionService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;

		List<QuestionVo> list = questionService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		
		return "admin/question/index";
	}
	
	@GetMapping("/admin/question/view.do")
	public String adminView(Model model, @RequestParam int qna_no) {
		model.addAttribute("data", questionService.view(qna_no));
		
		return "admin/question/view";
	}
	
	@GetMapping("/admin/question/replyUpdate.do")
	public String replyUpdateAjax(HttpSession sess, Model model, QuestionVo vo, @RequestParam int qna_no, @RequestParam String reply) {
		
		vo.setQna_no(qna_no);
		vo.setReply(reply);
		vo.setAdmin_no(((AdminVo)sess.getAttribute("adminInfo")).getAdmin_no());
		model.addAttribute("result", questionService.replyUpdate(vo));
		return "include/result";
	}
	
	@GetMapping("/question/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, QuestionVo vo) {

		int totCount = questionService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;

		List<QuestionVo> list = questionService.selectList(vo);

		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		
		return "question/index";
	}
	
	@RequestMapping("/question/write.do")
	public String write() {
		return "question/write";
	}
	
	@PostMapping("/question/insert.do")
	public String insert(QuestionVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) {
		vo.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		// 파일 저장
		String filename = "";
		String filename_real = "";
		
		if (!file.isEmpty()) {//사용자가 파일을 첨부했다면
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
		
		int r = questionService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/return";

	}

	@GetMapping("/question/view.do")
	public String view(Model model, @RequestParam int qna_no) {
		model.addAttribute("data", questionService.view(qna_no));
		
		return "question/view";
	}
	
	@GetMapping("/question/edit.do")
	public String edit(Model model, @RequestParam int qna_no) {
		model.addAttribute("data", questionService.edit(qna_no));
		return "question/edit";
	}
	
	@PostMapping("/question/update.do")
	public String update(Model model, QuestionVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			QuestionVo bv = questionService.edit(vo.getQna_no());
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
		
		int r = questionService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?qna_no="+vo.getQna_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?qna_no="+vo.getQna_no());
		}
		return "include/return";
	}
	
	@GetMapping("/question/delete.do")
	public String delete(Model model, QuestionVo vo) {
		int r = questionService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?qna_no="+vo.getQna_no());
		}
		return "include/return";
	}
	
	@GetMapping("/question/deleteAjax.do")
	public String deleteAjax(Model model, QuestionVo vo) {
		model.addAttribute("result", questionService.delete(vo));
		return "include/result";
	}
	
}
