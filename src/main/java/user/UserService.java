package user;

import java.util.List;

import javax.servlet.http.HttpSession;



public interface UserService {
	
	List<UserVo> selectList(UserVo vo);
	boolean login(UserVo vo, HttpSession sess);
	int idCheck(String id);
	int insert(UserVo vo);
	
}
