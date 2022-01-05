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
	
	// 수정
	public int update(CommentVo cv) {
		return sst.update("comment.update", cv);
	}
	
	// 삭제
	public int delete(int comment_no) {
		return sst.delete("comment.delete", comment_no);
	}
}
