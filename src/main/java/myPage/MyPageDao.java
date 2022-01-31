package myPage;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import friends.FriendsVo;
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
	
	public List<MyPageFriendsVo> findFriendsList(int userno){
		return sst.selectList("myPage.findFriendsList", userno);
	}
	
	public int deleteFriend(FriendsVo fv) {
		return sst.delete("myPage.deleteFriend", fv);
	}
}
