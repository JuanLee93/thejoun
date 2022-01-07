package user;

import javax.servlet.http.HttpSession;

public interface UserService {
	
	boolean login(UserVo vo, HttpSession sess);
	int idCheck(String id);
	int insert(UserVo vo);
	
}
