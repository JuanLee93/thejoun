package comment;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import freeboard.FreeBoardVo;


@Repository
public class CommentDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	// 화면
	public List<CommentVo> selectList(CommentVo cv) {
		List<CommentVo> list = sst.selectList("comment.selectList", cv);
		return list;
	}
	
	// 등록
	public int insert(CommentVo cv) {
		return sst.insert("comment.insert", cv);
	}
	
	// 선택된 댓글 조회
	public CommentVo selectOne(int comment_no) {
		return sst.selectOne("comment.selectOne", comment_no);
	}
	
	// 수정
	public int update(CommentVo cv) {
		return sst.update("comment.update", cv);
	}
	
	// 답변 -> 답변의 답변(정렬, 들여쓰기)
	public int updateGno(int comment_no) {
		return sst.update("comment.updateGno", comment_no);
	}
	
	public int updateOno(CommentVo cv) {
		return sst.update("comment.updateOno", cv);
	}
	
	public int insertCommentReply(CommentVo cv) {
		return sst.insert("comment.insertCommentReply", cv);
	}
		
	// 삭제
	public int delete(int comment_no) {
		return sst.delete("comment.delete", comment_no);
	}
}
