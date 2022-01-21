package admin;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface AdminService {
	
	boolean login(AdminVo vo, HttpSession sess);
	int count(AdminVo vo);
	List<AdminVo> selectList(AdminVo vo);
	int insert(AdminVo vo);
	AdminVo view(int admin_no);
	AdminVo edit(int admin_no);
	int update(AdminVo vo);
	int delete(AdminVo vo);
	
}
