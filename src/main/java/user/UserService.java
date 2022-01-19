package user;

import java.util.List;

import javax.servlet.http.HttpSession;

import question.QuestionVo;



public interface UserService {
	//관리자페이지 회원관리 페이징처리에서 사용 
	int count(UserVo vo);
	
	List<UserVo> selectList(UserVo vo);
	boolean login(UserVo vo, HttpSession sess);
	int idCheck(String id);
	int insert(UserVo vo);
	
}
