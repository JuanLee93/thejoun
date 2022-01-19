package notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDao questionDao;
	
	@Override
	public int count(NoticeVo vo) {
		return questionDao.count(vo);
	}
	
	@Override
	public List<NoticeVo> selectList(NoticeVo vo) {
		return questionDao.selectList(vo);
	}

	@Override
	public int insert(NoticeVo vo) {
		return questionDao.insert(vo);
	}

	@Override
	public NoticeVo view(int notice_no) {
		questionDao.updateReadcount(notice_no);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return questionDao.selectOne(notice_no);
	}
	
	@Override
	public NoticeVo edit(int notice_no) {
		return questionDao.selectOne(notice_no);
	}
	
	@Override
	public int update(NoticeVo vo) {
		return questionDao.update(vo);
	}

	@Override
	public int delete(NoticeVo vo) {
		return questionDao.delete(vo);
	}

	@Override
	public int replyUpdate(NoticeVo vo) {
		return questionDao.replyUpdate(vo);
	}

}
