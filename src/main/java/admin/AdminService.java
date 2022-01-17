package admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
	
	boolean login(AdminVo vo, HttpSession sess);
	int insert(AdminVo vo);
	int delete(AdminVo vo);
	
}
