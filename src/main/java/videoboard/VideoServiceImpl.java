package videoboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comment.CommentVo;

@Service
public class VideoServiceImpl implements VideoBoardService {
	
	@Autowired
	VideoBoardDao videoBoardDao;
	
	@Override
	public int count(VideoBoardVo vo) {
		return videoBoardDao.count(vo);
	}
	
	@Override
	public List<VideoBoardVo> selectList(VideoBoardVo vo) {
		return videoBoardDao.selectList(vo);
	}
	
	@Override
	public List<VideoBoardVo> mainSelectList(VideoBoardVo vo) {
		return videoBoardDao.selectList(vo);
	}

	@Override
	public int insert(VideoBoardVo vo) {
		return videoBoardDao.insert(vo);
	}

	@Override
	public VideoBoardVo view(int board_no) {
		videoBoardDao.updateReadcount(board_no);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return videoBoardDao.selectOne(board_no);
	}
	
	@Override
	public VideoBoardVo edit(int board_no) {
		return videoBoardDao.selectOne(board_no);
	}
	
	@Override
	public int update(VideoBoardVo vo) {
		return videoBoardDao.update(vo);
	}

	@Override
	public int delete(VideoBoardVo vo) {
		return videoBoardDao.delete(vo);
	}
	
	@Override
	public int updateAnnounce(CommentVo cv) {
		return videoBoardDao.updateAnnounce(cv);
	}
}
