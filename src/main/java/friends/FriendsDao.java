package friends;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import friendsAdd.FriendsAddVo;
import user.UserVo;

@Repository
public class FriendsDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public List<UserVo> selectFriendsAddList(FriendsAddVo fav){
		return sst.selectList("friends.selectFriendsAddList", fav); 
	}
	
	public List<UserVo> selectAddFriendsList(FriendsAddVo fav){
		return sst.selectList("friends.selectAddFriendsList", fav);
	}
	
	public int deleteFriendsAddList(FriendsAddVo fav) {
		return sst.delete("friends.deleteFriendsAddList", fav);
	}
	
	public int friendsInsert(FriendsAddVo fav) {
		return sst.insert("friends.friendsInsert", fav);
	}
	
	public List<UserVo> findFriendsList(int userno){
		return sst.selectList("findFriendsList", userno); 
	}
	
}
