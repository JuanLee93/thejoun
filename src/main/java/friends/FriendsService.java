package friends;

import java.util.List;

import announce.AnnounceVo;
import friendsAdd.FriendsAddVo;
import user.UserVo;

public interface FriendsService {

	List<UserVo> selectFriendsAddList(FriendsAddVo fav);
	List<UserVo> selectAddFriendsList(FriendsAddVo fav);
	int deleteFriendsAddList(FriendsAddVo fav);
	int friendsInsert(FriendsAddVo fav);
	List<UserVo> findFriendsList(int userno);
	//아래꺼는 게시판 insert에 들어갈 메서드
	//게시판 인서트에서 이거 불러와서 친구들 userno List로 받아와야함
	List<Integer> getFriendsUserno(AnnounceVo av);
	
}
