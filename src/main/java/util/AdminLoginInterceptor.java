package util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminLoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {//사용자페이지

		HttpSession sess = req.getSession();
		if (sess.getAttribute("adminInfo") == null) {//로그인이 안 된 상태
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용가능합니다.')");
			out.println("location.href='"+req.getContextPath()+"/admin/index.do';");
			out.println("</script>");
			return false;
		} else { //로그인된 상태
			return true;
		}
		
	}
	
}
