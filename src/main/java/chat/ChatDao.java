package chat;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import user.UserVo;

@Repository
public class ChatDao {
	@Autowired
	SqlSessionTemplate sst;
	
	public List<ChatVo> findChattingList(ChatVo cv){
		return sst.selectList("chat.findChattingList", cv);
	}
	
	public UserVo findOtherUser(int userno) {
		return sst.selectOne("chat.findOtherUser", userno);
	}
	
	public int sendMessage(ChatVo cv) {
		return sst.insert("chat.sendMessage", cv);
	}
	
	public List<UserVo> findFriendsList(int userno){
		return sst.selectList("chat.findFriendsList", userno);
	}
	
	public int messageUpdate(ChatVo cv) {
		return sst.update("chat.messageUpdate", cv);
	}
}
