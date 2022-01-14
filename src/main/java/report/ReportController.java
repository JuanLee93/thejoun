package report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

	@Autowired
	ReportService reportservice;
	
	@GetMapping("/report")
	public String reportupdate(Model model, HttpServletRequest req, ReportVo vo){
		
		int r = reportservice.reportUpdate(vo);
		req.setAttribute("result", r);
	
		return "/include/result";
	}
	
}
