package freeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comment.CommentVo;

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
	public List<FreeBoardVo> mainSelectList(FreeBoardVo vo) {
		return freeBoardDao.mainSelectList(vo);
	}

	@Override
	public int insert(FreeBoardVo vo) {
		return freeBoardDao.insert(vo);
	}

	@Override
	public FreeBoardVo view(int board_no) {
		freeBoardDao.updateReadcount(board_no);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return freeBoardDao.selectOne(board_no);
	}
	
	@Override
	public FreeBoardVo edit(int board_no) {
		return freeBoardDao.selectOne(board_no);
	}
	
	@Override
	public int update(FreeBoardVo vo) {
		return freeBoardDao.update(vo);
	}

	@Override
	public int delete(FreeBoardVo vo) {
		return freeBoardDao.delete(vo);
	}
	
	@Override
	public int updateAnnounce(CommentVo cv) {
		return freeBoardDao.updateAnnounce(cv);
	}

	@Override
	public int updateNotice(FreeBoardVo vo) {
		return freeBoardDao.updateNotice(vo);
	}

	@Override
	public int getRownum(FreeBoardVo vo) {
		return freeBoardDao.getRownum(vo);
	}

	@Override
	public FreeBoardVo getNext(FreeBoardVo vo) {
		return freeBoardDao.getNext(vo);
	}

	@Override
	public FreeBoardVo getPrev(FreeBoardVo vo) {
		return freeBoardDao.getPrev(vo);
	}

	@Override
	public int adminInsert(FreeBoardVo vo) {
		return freeBoardDao.noticeInsert(vo);
	}
	
	@Override
	public FreeBoardVo adminEdit(int board_no) {
		return freeBoardDao.selectOne(board_no);
	}
	
	@Override
	public int adminUpdate(FreeBoardVo vo) {
		return freeBoardDao.adminUpdate(vo);
	}

	@Override
	public int updateNotNotice(FreeBoardVo vo) {
		return freeBoardDao.updateNotNotice(vo);
	}
}
