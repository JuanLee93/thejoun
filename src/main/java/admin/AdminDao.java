package admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import admin.AdminVo;

@Repository
public class AdminDao {

	@Autowired
	SqlSessionTemplate sst;
	
	//로그인
	public AdminVo login(AdminVo vo) {
		return sst.selectOne("admin.login", vo);
	}
	
	public int count(AdminVo vo) {
		return sst.selectOne("admin.count", vo);
	}
	
	public List<AdminVo> selectList(AdminVo vo){
		return sst.selectList("admin.selectList", vo);
	}
	
	//등록
	public int insert(AdminVo vo) {
		return sst.insert("admin.insert", vo);
	}
	
	//수정
	public int update(AdminVo vo) {
		return sst.update("admin.update", vo);
	}
	
	//삭제
	public int delete(AdminVo vo) {
		return sst.delete("admin.delete", vo.getAdmin_no());
	}
	
	public AdminVo selectOne(int admin_no) {
		return sst.selectOne("admin.selectOne", admin_no);
	}

}
