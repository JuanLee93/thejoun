package adminLog;

import java.util.List;

public interface AdminLogService {
	
	int count(AdminLogVo vo);
	List<AdminLogVo> selectList(AdminLogVo vo);
	int insert(AdminLogVo vo);
	AdminLogVo view(int log_no);
	
}
