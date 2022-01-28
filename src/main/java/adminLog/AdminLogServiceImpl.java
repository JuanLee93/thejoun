package adminLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLogServiceImpl implements AdminLogService {

	@Autowired
	AdminLogDao dao;
	
	@Override
	public int insert(AdminLogVo vo) {
		return dao.insert(vo);
	}

	@Override
	public int count(AdminLogVo vo) {
		return dao.count(vo);
	}

	@Override
	public List<AdminLogVo> selectList(AdminLogVo vo) {
		return dao.selectList(vo);
	}

}
