package friendsAdd;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import user.UserVo;

@Repository
public class FriendsAddDao {

	@Autowired
	SqlSessionTemplate sst;
	
//	public List<FriendsAddVo> selectList(FriendsAddVo vo){
//		System.out.println("my number: " + vo.getFrom_userno());
//		return sst.selectList("friendsAdd.selectList", vo);
//	}
	
	
	public List<UserVo> selectList(int userno){
		return sst.selectList("friendsAdd.selectList", userno);
	}
	
	public int addButton(FriendsAddVo add) {
		return sst.insert("friendsAdd.insert", add);
	}
	
	public int friendsAddCheck(FriendsAddVo fav) {
		return sst.selectOne("friendsAdd.friendsAddCheck", fav);
	}
}
