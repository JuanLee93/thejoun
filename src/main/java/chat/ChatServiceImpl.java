package chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.UserVo;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	ChatDao cd;
	
	@Override
	public List<ChatVo> findChattingList(ChatVo cs){
		return cd.findChattingList(cs);
	}
	
	@Override
	public UserVo findOtherUser(int userno) {
		return cd.findOtherUser(userno);
	}
	
	@Override
	public int sendMessage(ChatVo cv) {
		return cd.sendMessage(cv);
	}
	
	@Override
	public List<UserVo> findFriendsList(int userno){
		return cd.findFriendsList(userno);
	}
	
	@Override
	public int messageUpdate(ChatVo cv) {
		return cd.messageUpdate(cv);
	}
	
}
