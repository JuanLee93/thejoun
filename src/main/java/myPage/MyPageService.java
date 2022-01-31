package myPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import friends.FriendsVo;
import user.UserVo;

@Service
public class MyPageService {

	@Autowired
	MyPageDao mpd;
	
	public int count(UserVo vo) {
		return mpd.count(vo); 
	}
	
	public List<MyPageVo> selectList(UserVo vo){
		return mpd.selectList(vo);
	}
	
	public List<MyPageFriendsVo> findFriendsList(int userno){
		return mpd.findFriendsList(userno);
	}
	
	public int deleteFriend(FriendsVo fv) {
		return mpd.deleteFriend(fv);
	}
}
