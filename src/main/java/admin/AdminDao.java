package admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	@Autowired
	SqlSessionTemplate sst;
	
	//로그인
	public AdminVo login(AdminVo vo) {
		return sst.selectOne("admin.login", vo);
	}
	
	//등록
	public int insert(AdminVo vo) {
		return sst.insert("admin.insert", vo);
	}
	
	//삭제
	public int delete(AdminVo vo) {
		return sst.delete("admin.delete", vo);
	}
}
