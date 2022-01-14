package imageboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageBoardServiceImpl implements ImageBoardService {
	
	@Autowired
	ImageBoardDao imageBoardDao;
	
	@Override
	public int count(ImageBoardVo vo) {
		return imageBoardDao.count(vo);
	}
	
	@Override
	public List<ImageBoardVo> selectList(ImageBoardVo vo) {
		return imageBoardDao.selectList(vo);
	}

	@Override
	public int insert(ImageBoardVo vo) {
		return imageBoardDao.insert(vo);
	}

	@Override
	public ImageBoardVo view(int image_board_no) {
		imageBoardDao.updateReadcount(image_board_no);// 상세페이지 클릭했을 때 조회수가 올라가도록.
		return imageBoardDao.selectOne(image_board_no);
	}
	
	@Override
	public ImageBoardVo edit(int image_board_no) {
		return imageBoardDao.selectOne(image_board_no);
	}
	
	@Override
	public int update(ImageBoardVo vo) {
		return imageBoardDao.update(vo);
	}

	@Override
	public int delete(ImageBoardVo vo) {
		return imageBoardDao.delete(vo);
	}
}
