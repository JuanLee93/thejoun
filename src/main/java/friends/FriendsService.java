package friends;

import java.util.List;

import friendsAdd.FriendsAddVo;
import user.UserVo;

public interface FriendsService {

	List<UserVo> selectFriendsAddList(FriendsAddVo fav);
	int deleteFriendsAddList(FriendsAddVo fav);
	int friendsInsert(FriendsAddVo fav);
	List<UserVo> findFriendsList(int userno);
}
