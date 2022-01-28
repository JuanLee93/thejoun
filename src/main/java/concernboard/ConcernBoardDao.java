package concernboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comment.CommentVo;
import freeboard.FreeBoardVo;

@Repository
public class ConcernBoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(ConcernBoardVo vo) {
		return sqlSessionTemplate.selectOne("concernboard.count", vo);
	}
	
	public List<ConcernBoardVo> selectList(ConcernBoardVo vo){
		return sqlSessionTemplate.selectList("concernboard.selectList", vo);
	}
	
	public List<ConcernBoardVo> mainSelectList(ConcernBoardVo vo){
		return sqlSessionTemplate.selectList("concernboard.mainSelectList", vo);
	}
	
	public int insert(ConcernBoardVo vo) {
	int r = -1;
	try {
		r = sqlSessionTemplate.insert("concernboard.insert", vo);
	} catch (Exception e) {
		r = 0;
		System.out.println(e.getMessage());
	}
	return r;
	}
	
	public ConcernBoardVo selectOne(int board_no) {
		return sqlSessionTemplate.selectOne("concernboard.selectOne", board_no);
	}
	
	public int updateReadcount(int board_no) {
		return sqlSessionTemplate.update("concernboard.updateReadcount", board_no);
	}
	
	public int update(ConcernBoardVo vo) {
		return sqlSessionTemplate.update("concernboard.update", vo);
	}
	
	public int delete(ConcernBoardVo vo) {
		return sqlSessionTemplate.delete("concernboard.delete", vo.getConcern_board_no());
	}
	
	public int updateAnnounce(CommentVo cv) {
		return sqlSessionTemplate.update("concernboard.updateAnnounce", cv);
	}


	public int updateNotice(ConcernBoardVo vo) {
		return sqlSessionTemplate.update("concernboard.updateNotice", vo);
	}
	public int updateNotNotice(ConcernBoardVo vo) {
		return sqlSessionTemplate.update("concernboard.updateNotNotice", vo);
	}
	
	public int getRownum(ConcernBoardVo vo) {
		return sqlSessionTemplate.selectOne("concernboard.getRownum", vo);
	}
	
	public ConcernBoardVo getNext(ConcernBoardVo vo) {
		return sqlSessionTemplate.selectOne("concernboard.getNext", vo);
	}
	
	public ConcernBoardVo getPrev(ConcernBoardVo vo) {
		return sqlSessionTemplate.selectOne("concernboard.getPrev", vo);
	}
	public int noticeInsert(ConcernBoardVo vo) {
		int r = -1;
		try {
			r = sqlSessionTemplate.insert("concernboard.noticeInsert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	public int adminUpdate(ConcernBoardVo vo) {
		return sqlSessionTemplate.update("concernboard.adminUpdate", vo);
	}
}
