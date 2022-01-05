package concernboard;

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
public class ConcernBoardController {

	@Autowired
	ConcernBoardService concernBoardService;
	
	@Autowired
	CommentService cService;
	
	@GetMapping("/concernboard/index.do")
	public String index(Model model, HttpServletRequest req, HttpSession sess, ConcernBoardVo vo) {
		
		int totCount = concernBoardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수
		if (totCount % 10 > 0) totPage++;
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);

		int pageRange = 10;
		int startPage = (vo.getPage()-1)/pageRange*pageRange+1; // 시작페이지
		int endPage = startPage + pageRange - 1;// 종료페이지
		if (endPage > totPage) endPage = totPage;
		
		List<ConcernBoardVo> list = concernBoardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totCount", totCount);
		model.addAttribute("pageArea", CommonUtil.getPageArea("index.do", vo.getPage(), totPage, 10));
		return "concernboard/index";
	}
	
	@RequestMapping("/concernboard/write.do")
	public String write() {
		return "concernboard/write";
	}
	
	@PostMapping("/concernboard/insert.do")
	public String insert(ConcernBoardVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) {
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
		
		int r = concernBoardService.insert(vo);
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록 오류가 발생하였습니다.");
			req.setAttribute("url", "write.do");
		} 
		
		return "/include/return";
	}
	
	@GetMapping("/concernboard/view.do")
	public String view(Model model, @RequestParam int board_no) {
		model.addAttribute("data", concernBoardService.view(board_no));
		CommentVo cv = new CommentVo();
		cv.setBoard_no(board_no);
		cv.setTablename(2);
		model.addAttribute("cList", cService.selectList(cv));
		return "concernboard/view";
	}
	
	@GetMapping("/concernboard/edit.do")
	public String edit(Model model, @RequestParam int board_no) {
		model.addAttribute("data", concernBoardService.edit(board_no));
		return "concernboard/edit";
	}
	
	@PostMapping("/concernboard/update.do")
	public String update(Model model, ConcernBoardVo vo, MultipartFile file, HttpServletRequest req) {
		if ("1".equals(req.getParameter("delCheck"))) {
			ConcernBoardVo bv = concernBoardService.edit(vo.getConcern_board_no());
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
		
		int r = concernBoardService.update(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getConcern_board_no());
		} else {
			model.addAttribute("msg", "수정 오류가 발생하였습니다.");
			model.addAttribute("url", "edit.do?board_no="+vo.getConcern_board_no());
		}
		return "include/return";
	}
	
	@GetMapping("/concernboard/delete.do")
	public String delete(Model model, ConcernBoardVo vo) {
		int r = concernBoardService.delete(vo);
		if ( r > 0 ) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
		} else {
			model.addAttribute("msg", "삭제 오류가 발생하였습니다.");
			model.addAttribute("url", "view.do?board_no="+vo.getConcern_board_no());
		}
		return "include/return";
	}
	
	@GetMapping("/concernboard/deleteAjax.do")
	public String deleteAjax(Model model, ConcernBoardVo vo) {
		model.addAttribute("result", concernBoardService.delete(vo));
		return "include/result";
	}
}
