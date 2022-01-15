package chat;

import java.util.List;

import user.UserVo;

public interface ChatService {
	List<ChatVo> findChattingList(ChatVo cv);
	UserVo findOtherUser(int userno);
	int sendMessage(ChatVo cv);
	List<UserVo> findFriendsList(int userno);
	int messageUpdate(ChatVo cv);
}
