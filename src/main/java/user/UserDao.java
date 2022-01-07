package user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	SqlSessionTemplate sst;
	
	//로그인
	public UserVo login(UserVo vo) {
		return sst.selectOne("user.login", vo);
	}
	
	//id 체크
	public int idCheck(String id) {
		return sst.selectOne("user.idCheck",id);
	}
	
	//등록
	public int insert(UserVo vo) {
		return sst.insert("user.insert", vo);
	}
	

}
