package concernboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comment.CommentVo;
import freeboard.FreeBoardVo;

@Service
public class ConcernServiceImpl implements ConcernBoardService {
	
	@Autowired
	ConcernBoardDao concernBoardDao;
	
	@Override
	public int count(ConcernBoardVo vo) {
		return concernBoardDao.count(vo);
	}
	
	@Override
	public List<ConcernBoardVo> selectList(ConcernBoardVo vo) {
		return concernBoardDao.selectList(vo);
	}
	@Override
	public List<ConcernBoardVo> mainSelectList(ConcernBoardVo vo) {
		return concernBoardDao.mainSelectList(vo);
	}

	@Override
	public int insert(ConcernBoardVo vo) {
		return concernBoardDao.insert(vo);
	}

	@Override
	public ConcernBoardVo view(int board_no) {
		concernBoardDao.updateReadcount(board_no);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return concernBoardDao.selectOne(board_no);
	}
	
	@Override
	public ConcernBoardVo edit(int board_no) {
		return concernBoardDao.selectOne(board_no);
	}
	
	@Override
	public int update(ConcernBoardVo vo) {
		return concernBoardDao.update(vo);
	}

	@Override
	public int delete(ConcernBoardVo vo) {
		return concernBoardDao.delete(vo);
	}
	
	@Override
	public int updateAnnounce(CommentVo cv) {
		return concernBoardDao.updateAnnounce(cv);
	}
	@Override
	public int updateNotice(ConcernBoardVo vo) {
		return concernBoardDao.updateNotice(vo);
	}

	@Override
	public int getRownum(ConcernBoardVo vo) {
		return concernBoardDao.getRownum(vo);
	}

	@Override
	public ConcernBoardVo getNext(ConcernBoardVo vo) {
		return concernBoardDao.getNext(vo);
	}

	@Override
	public ConcernBoardVo getPrev(ConcernBoardVo vo) {
		return concernBoardDao.getPrev(vo);
	}
	
	@Override
	public int adminInsert(ConcernBoardVo vo) {
		return concernBoardDao.noticeInsert(vo);
	}
}
