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
	
	public int deleteFriendsAddList(FriendsAddVo fav) {
		return sst.delete("friends.deleteFriendsAddList", fav);
	}
	
	public int friendsInsert(FriendsAddVo fav) {
		//친구추가하면 신청한사람이랑 신청받은사람 둘다 친구가 되어야하기때문에 2개넣음
		
		return sst.insert("friends.friendsInsert", fav);
	}
	
	public List<UserVo> findFriendsList(int userno){
		return sst.selectList("findFriendsList", userno); 
	}
	
}
