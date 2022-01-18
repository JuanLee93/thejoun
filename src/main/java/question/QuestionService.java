package question;

import java.util.List;

public interface QuestionService {

	int count(QuestionVo vo);
	List<QuestionVo> selectList(QuestionVo vo);
	int insert(QuestionVo vo);
	QuestionVo view(int qna_no);
	QuestionVo edit(int qna_no);
	int update(QuestionVo vo);
	int replyUpdate(QuestionVo vo);
	int delete(QuestionVo vo);
	
}
