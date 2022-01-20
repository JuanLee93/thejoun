package friendsAdd;

import java.util.List;

import user.UserVo;

public interface FriendsAddService {

	//List<FriendsAddVo> selectList(FriendsAddVo vo);
	
	List<UserVo> selectList(int userno);
	int addButton(FriendsAddVo add);
	int friendsAddCheck(FriendsAddVo fav);
	int friendsAddCheck2(FriendsAddVo fav);
	int friendsUse(FriendsAddVo fav);
}
