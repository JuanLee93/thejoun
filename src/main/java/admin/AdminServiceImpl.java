package admin;

import java.util.List;

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

	@Override
	public int count(AdminVo vo) {
		return dao.count(vo);
	}

	@Override
	public List<AdminVo> selectList(AdminVo vo) {
		return dao.selectList(vo);
	}

	@Override
	public AdminVo view(int admin_no) {
		return dao.selectOne(admin_no);
	}

	@Override
	public AdminVo edit(int admin_no) {
		return dao.selectOne(admin_no);
	}

	@Override
	public int update(AdminVo vo) {
		return dao.update(vo);
	}
	
	@Override
	public int idCheck(String id) {
		return dao.idCheck(id);
	}
	
}
