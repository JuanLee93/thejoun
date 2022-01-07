package chat;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comment.CommentVo;

@Repository
public class ChatDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	public List<ChatVo> selectList(ChatVo cv){
		List<ChatVo> list = sst.selectList("chat.selectList", cv);
		return list;
	}
	
	// 등록
	public int insert(ChatVo cv) {
		return sst.insert("chat.insert", cv);
	}

	// 선택된 댓글 조회
	public ChatVo selectOne(int cv) {
		return sst.selectOne("chat.selectOne", cv);
	}

	// 수정
	public int update(CommentVo cv) {
		return sst.update("chat.update", cv);
	}

	// 삭제
	public int delete(int cv) {
		return sst.delete("chat.delete", cv);
	}
}
