package friends;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import friendsAdd.FriendsAddVo;
import user.UserVo;

@Service
public class FriendsServiceImpl implements FriendsService{
	
	@Autowired
	FriendsDao fd;
	
	@Override
	public List<UserVo> selectFriendsAddList(FriendsAddVo fav){
		return fd.selectFriendsAddList(fav);
	}
	
	@Override
	public List<UserVo> selectAddFriendsList(FriendsAddVo fav){
		return fd.selectAddFriendsList(fav);
	}
	
	@Override
	public int deleteFriendsAddList(FriendsAddVo fav) {
		return fd.deleteFriendsAddList(fav);
	}
	
	@Override
	public int friendsInsert(FriendsAddVo fav) {
		return fd.friendsInsert(fav);
	}
	
	@Override
	public List<UserVo> findFriendsList(int userno){
		return fd.findFriendsList(userno);
	}
}
