package freeboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	FreeBoardDao freeBoardDao;
	
	@Override
	public int count(FreeBoardVo vo) {
		return freeBoardDao.count(vo);
	}
	
	@Override
	public List<FreeBoardVo> selectList(FreeBoardVo vo) {
		return freeBoardDao.selectList(vo);
	}

	@Override
	public int insert(FreeBoardVo vo) {
		return freeBoardDao.insert(vo);
	}

	@Override
	public FreeBoardVo view(int boardno) {
		freeBoardDao.updateReadcount(boardno);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return freeBoardDao.selectOne(boardno);
	}
	
	@Override
	public FreeBoardVo edit(int boardno) {
		return freeBoardDao.selectOne(boardno);
	}
	
	@Override
	public int update(FreeBoardVo vo) {
		return freeBoardDao.update(vo);
	}

	@Override
	public int delete(FreeBoardVo vo) {
		return freeBoardDao.delete(vo);
	}
}
