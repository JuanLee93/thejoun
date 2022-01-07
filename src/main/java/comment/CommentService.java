package comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freeboard.FreeBoardVo;

@Service
public class CommentService {
	
	@Autowired
	CommentDao dao;
	
	public List<CommentVo> selectList(CommentVo vo) {// 화면
		return dao.selectList(vo);
	}
	
	public int insert(CommentVo vo) {// 등록
		return dao.insert(vo);
	}
	
	public CommentVo selectOne(int comment_no) {// 선택된 댓글 조회
		return dao.selectOne(comment_no);
	}
	
	public int update(CommentVo vo) {// 수정
		return dao.update(vo);
	}
	
	public int delete(int comment_no) {// 삭제
		return dao.delete(comment_no);
	}
	
	public int insertCommentReply(CommentVo vo) {
		dao.updateOno(vo);// 같은 그룹 ono의 값을 +1
		// 아래는 insert 하기 위해서 부모의 ono, nested보다 크게 하는 것
		vo.setOno(vo.getOno()+1);
		vo.setNested(vo.getNested()+1);
		return dao.insertCommentReply(vo);
	}
	
}
