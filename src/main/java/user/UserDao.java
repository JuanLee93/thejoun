package user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import concernboard.ConcernBoardVo;
import question.QuestionVo;

@Repository
public class UserDao {

	@Autowired
	SqlSessionTemplate sst;
	//관리자 페이지에서 사용할 user정보
	public List<UserVo> selectList(UserVo vo){
		return sst.selectList("user.selectList", vo);
	}
	//관리자 페이지 페이징처리
	public int count(UserVo vo) {
		return sst.selectOne("user.count", vo);
	}
	
	//로그인
	public UserVo login(UserVo vo) {
		return sst.selectOne("user.login", vo);
	}
	
	//id 체크
	public int idCheck(String id) {
		return sst.selectOne("user.idCheck",id);
	}
	
	//등록
	public int insert(UserVo vo) {
		return sst.insert("user.insert", vo);
	}
	
	//아이디 찾기
	public UserVo searchId(UserVo vo) {
		return sst.selectOne("user.searchId", vo);
	}
	
	//비밀번호 찾기
	public UserVo searchPwd(UserVo vo) {
		return sst.selectOne("user.searchPwd", vo);
	}
	//이메일로 새로운 비밀번호 전송
	public int updateTempPwd(UserVo vo) {
		return sst.update("user.updateTempPwd", vo);
	}

}
