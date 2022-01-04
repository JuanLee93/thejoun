package freeboard;

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

import comment.CommentService;
import comment.CommentVo;
import util.CommonUtil;

@Controller
public class FreeBoardController {

	@Autowired
	FreeBoardService freeBoardService;
	
	@Autowired
	CommentService cService;
	
	@GetMapping("/freeboard/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, FreeBoardVo vo) {
		
		int totCount = freeBoardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		List<FreeBoardVo> list = freeBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		return "freeboard/index";
	}
	
	@RequestMapping("/freeboard/write.do")
	public String write() {
		return "freeboard/write";
	}
	
	@PostMapping("/freeboard/insert.do")
	public String insert(FreeBoardVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) {
//		vo.setUserno(((UserVo)sess.getAttribute("userInfo")).getUserno());
		// 파일 저장
		if (!file.isEmpty()) {//사용자가 파일을 첨부했다면
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
		
		int r = freeBoardService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/return";
	}
	
	@GetMapping("/freeboard/view.do")
	public String view(Model model, @RequestParam int boardno) {
		model.addAttribute("data", freeBoardService.view(boardno));
		CommentVo cv = new CommentVo();
		cv.setBoard_no(boardno);
		cv.setTablename("board");
		model.addAttribute("cList", cService.selectList(cv));
		return "freeboard/view";
	}
	
	@GetMapping("/freeboard/edit.do")
	public String edit(Model model, @RequestParam int boardno) {
		model.addAttribute("data", freeBoardService.edit(boardno));
		return "freeboard/edit";
	}
	
	@PostMapping("/freeboard/update.do")
	public String update(Model model, FreeBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			FreeBoardVo bv = freeBoardService.edit(vo.getBoard_no());
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
		
		int r = freeBoardService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?boardno="+vo.getBoard_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?boardno="+vo.getBoard_no());
		}
		return "include/return";
	}
	
	@GetMapping("/freeboard/delete.do")
	public String delete(Model model, FreeBoardVo vo) {
		int r = freeBoardService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?boardno="+vo.getBoard_no());
		}
		return "include/return";
	}
	
	@GetMapping("/freeboard/deleteAjax.do")
	public String deleteAjax(Model model, FreeBoardVo vo) {
		model.addAttribute("result", freeBoardService.delete(vo));
		return "include/result";
	}
}