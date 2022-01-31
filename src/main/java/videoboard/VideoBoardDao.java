package videoboard;
  
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comment.CommentVo;
import imageboard.ImageBoardVo;

@Repository
public class VideoBoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(VideoBoardVo vo) {
		return sqlSessionTemplate.selectOne("videoboard.count", vo);
	}
	
	public List<VideoBoardVo> selectList(VideoBoardVo vo){
		return sqlSessionTemplate.selectList("videoboard.selectList", vo);
	}
	public List<VideoBoardVo> mainSelectList(VideoBoardVo vo){
		return sqlSessionTemplate.selectList("videoboard.mainSelectList", vo);
	}
	
	public int insert(VideoBoardVo vo) {
		int r = -1;
		try {
			r = sqlSessionTemplate.insert("videoboard.insert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	public VideoBoardVo selectOne(int board_no) {
		return sqlSessionTemplate.selectOne("videoboard.selectOne", board_no);
	}
	
	public int updateReadcount(int board_no) {
		return sqlSessionTemplate.update("videoboard.updateReadcount", board_no);
	}
	
	public int update(VideoBoardVo vo) {
		return sqlSessionTemplate.update("videoboard.update", vo);
	}
	
	public int delete(VideoBoardVo vo) {
		return sqlSessionTemplate.delete("videoboard.delete", vo.getVideo_board_no());
	}
	
	public int updateAnnounce(CommentVo cv) {
		return sqlSessionTemplate.update("videoboard.updateAnnounce", cv);
	}
	public int getRownum(VideoBoardVo vo) {
		return sqlSessionTemplate.selectOne("videoboard.getRownum", vo);
	}
	
	public VideoBoardVo getNext(VideoBoardVo vo) {
		return sqlSessionTemplate.selectOne("videoboard.getNext", vo);
	}
	
	public VideoBoardVo getPrev(VideoBoardVo vo) {
		return sqlSessionTemplate.selectOne("videoboard.getPrev", vo);
	}
}
