package concernboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
