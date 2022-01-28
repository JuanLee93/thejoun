package myPage;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import user.UserVo;

@Repository
public class MyPageDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public int count(UserVo vo) {
		return sst.selectOne("myPage.count", vo);
	}
	
	public List<MyPageVo> selectList(UserVo vo){
		return sst.selectList("myPage.selectList", vo);
	}
}
