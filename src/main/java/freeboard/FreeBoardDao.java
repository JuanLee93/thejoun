package freeboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	public FreeBoardVo selectOne(int boardno) {
		return sqlSessionTemplate.selectOne("freeboard.selectOne", boardno);
	}
	
	public int updateReadcount(int boardno) {
		return sqlSessionTemplate.update("freeboard.updateReadcount", boardno);
	}
	
	public int update(FreeBoardVo vo) {
		return sqlSessionTemplate.update("freeboard.update", vo);
	}
	
	public int delete(FreeBoardVo vo) {
		return sqlSessionTemplate.delete("freeboard.delete", vo.getBoard_no());
	}
}
