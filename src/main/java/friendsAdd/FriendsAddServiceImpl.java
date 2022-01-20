package friendsAdd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.UserVo;

@Service
public class FriendsAddServiceImpl implements FriendsAddService{

	@Autowired
	FriendsAddDao fad;
	
	@Override
	public List<UserVo> selectList(int userno){
		return fad.selectList(userno);
	}
	
	@Override
	public int addButton(FriendsAddVo add) {
		return fad.addButton(add);
	}
	
	@Override
	public int friendsAddCheck(FriendsAddVo fav) {
		return fad.friendsAddCheck(fav);
	}
	
	@Override
	public int friendsAddCheck2(FriendsAddVo fav) {
		return fad.friendsAddCheck2(fav);
	}
	
	@Override
	public int friendsUse(FriendsAddVo fav) {
		return fad.friendsUse(fav);
	}
}
