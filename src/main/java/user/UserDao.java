package user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public UserVo login(UserVo vo) {
		return sst.selectOne("user.login", vo);
	}
	
	public int insert(UserVo vo) {
		return sst.insert("user.insert", vo);
	}
	
	public int idCheck(String id) {
		return sst.selectOne("user.idCheck",id);
	}
}
