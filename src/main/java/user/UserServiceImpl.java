package user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concernboard.ConcernBoardVo;
import question.QuestionVo;
import util.SendMail;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	//관리자 페이지-회원관리 
	@Override
	public int count(UserVo vo) {		
		return dao.count(vo);
	}
	
	@Override
	public List<UserVo> selectList(UserVo vo) {
		return dao.selectList(vo);
	}
	
	
	@Override
	public boolean login(UserVo vo, HttpSession sess) {
		UserVo uv = dao.login(vo);
		if(uv != null) {
			sess.setAttribute("userInfo", uv);
			return true;
		}
		return false;
	}
	
	@Override
	public int insert(UserVo vo) {
		return dao.insert(vo);
	}
	
	@Override
	public int idCheck(String id) {
		return dao.idCheck(id);
	}
	
	//id 찾기
	@Override
	public UserVo searchId(UserVo vo) {
		return dao.searchId(vo);
	}
	
	//비밀번호 찾기
	@Override
	public UserVo searchPwd(UserVo vo) {
		// 이메일과 이름으로 DB에서 조회
		UserVo uv = dao.searchPwd(vo);
		// 조회한 결과가 있으면 임시비밀번호 생성, 임시비밀번호로 변경, 이메일 발송
		if (uv != null) {
			// 임시비밀번호 생성
			String tempPwd = "";
			for (int i=0; i<3; i++) {
				tempPwd += (char)((Math.random()*26)+65);
			}
			for (int i=0; i<3; i++) {
				tempPwd += (int)((Math.random()*9));
			}
			// 임시비밀번호 변경
			vo.setPwd(tempPwd);
			dao.updateTempPwd(vo);
			// 이메일 발송
			SendMail.sendMail("withsky999@naver.com",
								uv.getEmail(), 
								"[더조은]임시비밀번호입니다.", 
								"임시비밀번호:<span style='color:red;'>"+tempPwd+"</span>");
		}
		return uv;
	}

}
