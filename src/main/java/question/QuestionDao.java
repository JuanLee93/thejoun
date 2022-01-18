package question;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(QuestionVo vo) {
		return sqlSessionTemplate.selectOne("question.count", vo);
	}
	
	public List<QuestionVo> selectList(QuestionVo vo){
		return sqlSessionTemplate.selectList("question.selectList", vo);
	}
	
	
	public int insert(QuestionVo vo) {
		int r = 0;
		try {
			r = sqlSessionTemplate.insert("question.insert", vo);
		} catch (Exception e) {
			r = -1;
			System.out.println(e.getMessage());
		}
		return r;
	}

	public QuestionVo selectOne(int qna_no) {
		return sqlSessionTemplate.selectOne("question.selectOne", qna_no);
	}
	
	public int updateReadcount(int qna_no) {
		return sqlSessionTemplate.update("question.updateReadcount", qna_no);
	}
	
	public int update(QuestionVo vo) {
		return sqlSessionTemplate.update("question.update", vo);
	}
	
	public int delete(QuestionVo vo) {
		return sqlSessionTemplate.delete("question.delete", vo.getQna_no());
	}
	
	public int replyUpdate(QuestionVo vo) {
		return sqlSessionTemplate.update("question.replyUpdate", vo);
	}
}
