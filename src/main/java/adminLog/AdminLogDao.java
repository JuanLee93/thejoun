package adminLog;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import admin.AdminVo;

@Repository
public class AdminLogDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public int count(AdminLogVo vo) {
		return sst.selectOne("adminLog.count", vo);
	}
	
	public List<AdminLogVo> selectList(AdminLogVo vo){
		return sst.selectList("adminLog.selectList", vo);
	}
	
	//등록
	public int insert(AdminLogVo vo) {
		return sst.insert("adminLog.insert", vo);
	}

}
