package admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao dao;
	
	@Override
	public boolean login(AdminVo vo, HttpSession sess) {
		AdminVo adv = dao.login(vo);
		if(adv != null) {
			sess.setAttribute("adminInfo", adv);
			return true;
		}
		return false;
	}
	
	@Override
	public int insert(AdminVo vo) {
		return dao.insert(vo);
	}

	@Override
	public int delete(AdminVo vo) {
		return dao.delete(vo);
	}
	
	
}
