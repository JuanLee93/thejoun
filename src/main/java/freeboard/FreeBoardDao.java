package freeboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comment.CommentVo;

@Repository
public class FreeBoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(FreeBoardVo vo) {
		return sqlSessionTemplate.selectOne("freeboard.count", vo);
	}
	
	public List<FreeBoardVo> selectList(FreeBoardVo vo){
		return sqlSessionTemplate.selectList("freeboard.selectList", vo);
	}
	
	public List<FreeBoardVo> mainSelectList(FreeBoardVo vo){
		return sqlSessionTemplate.selectList("freeboard.mainSelectList", vo);
	}
	
	public int insert(FreeBoardVo vo) {
		int r = -1;
		try {
			r = sqlSessionTemplate.insert("freeboard.insert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	public FreeBoardVo selectOne(int board_no) {
		return sqlSessionTemplate.selectOne("freeboard.selectOne", board_no);
	}
	
	public int updateReadcount(int board_no) {
		return sqlSessionTemplate.update("freeboard.updateReadcount", board_no);
	}
	
	public int update(FreeBoardVo vo) {
		return sqlSessionTemplate.update("freeboard.update", vo);
	}
	
	public int delete(FreeBoardVo vo) {
		return sqlSessionTemplate.delete("freeboard.delete", vo.getBoard_no());
	}
	
	public int updateAnnounce(CommentVo cv) {
		return sqlSessionTemplate.update("freeboard.updateAnnounce", cv);
	}
	
	public int updateNotice(FreeBoardVo vo) {
		return sqlSessionTemplate.update("freeboard.updateNotice", vo);
	}
	
	public int getRownum(FreeBoardVo vo) {
		return sqlSessionTemplate.selectOne("freeboard.getRownum", vo);
	}
	
	public FreeBoardVo getNext(FreeBoardVo vo) {
		return sqlSessionTemplate.selectOne("freeboard.getNext", vo);
	}
	
	public FreeBoardVo getPrev(FreeBoardVo vo) {
		return sqlSessionTemplate.selectOne("freeboard.getPrev", vo);
	}
	
	public int noticeInsert(FreeBoardVo vo) {
		int r = -1;
		try {
			r = sqlSessionTemplate.insert("freeboard.noticeInsert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	public int adminUpdate(FreeBoardVo vo) {
		return sqlSessionTemplate.update("freeboard.adminUpdate", vo);
	}
}
