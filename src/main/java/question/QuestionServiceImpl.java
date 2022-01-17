package question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	@Override
	public int count(QuestionVo vo) {
		return questionDao.count(vo);
	}
	
	@Override
	public List<QuestionVo> selectList(QuestionVo vo) {
		return questionDao.selectList(vo);
	}

	@Override
	public int insert(QuestionVo vo) {
		return questionDao.insert(vo);
	}

	@Override
	public QuestionVo view(int qna_no) {
		questionDao.updateReadcount(qna_no);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return questionDao.selectOne(qna_no);
	}
	
	@Override
	public QuestionVo edit(int qna_no) {
		return questionDao.selectOne(qna_no);
	}
	
	@Override
	public int update(QuestionVo vo) {
		return questionDao.update(vo);
	}

	@Override
	public int delete(QuestionVo vo) {
		return questionDao.delete(vo);
	}

}
