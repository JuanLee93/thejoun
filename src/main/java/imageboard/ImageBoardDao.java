package imageboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comment.CommentVo;

@Repository
public class ImageBoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(ImageBoardVo vo) {
		return sqlSessionTemplate.selectOne("imageboard.count", vo);
	}
	
	public List<ImageBoardVo> selectList(ImageBoardVo vo){
		return sqlSessionTemplate.selectList("imageboard.selectList", vo);
	}
	
	public int insert(ImageBoardVo vo) {
		int r = -1;
		try {
			r = sqlSessionTemplate.insert("imageboard.insert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	public ImageBoardVo selectOne(int image_board_no) {
		return sqlSessionTemplate.selectOne("imageboard.selectOne", image_board_no);
	}
	
	public int updateReadcount(int image_board_no) {
		return sqlSessionTemplate.update("imageboard.updateReadcount", image_board_no);
	}
	
	public int update(ImageBoardVo vo) {
		return sqlSessionTemplate.update("imageboard.update", vo);
	}
	
	public int delete(ImageBoardVo vo) {
		return sqlSessionTemplate.delete("imageboard.delete", vo.getImage_board_no());
	}
	
	public int updateAnnounce(CommentVo cv) {
		return sqlSessionTemplate.update("imageboard.updateAnnounce", cv);
	}
}
