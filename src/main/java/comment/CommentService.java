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
	
	public int delete(int comment_no) {// 삭제
		return dao.delete(comment_no);
	}
}
