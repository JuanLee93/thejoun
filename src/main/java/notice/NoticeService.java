package notice;

import java.util.List;

public interface NoticeService {

	int count(NoticeVo vo);
	List<NoticeVo> selectList(NoticeVo vo);
	List<NoticeVo> mainSelectList(NoticeVo vo);
	int insert(NoticeVo vo);
	NoticeVo view(int notice_no);
	NoticeVo edit(int notice_no);
	int update(NoticeVo vo);
	int replyUpdate(NoticeVo vo);
	int delete(NoticeVo vo);
	
}
