package imageboard;

import java.util.List;

public interface ImageBoardService {

	int count(ImageBoardVo vo);
	List<ImageBoardVo> selectList(ImageBoardVo vo);
	int insert(ImageBoardVo vo);
	ImageBoardVo view(int board_no);
	ImageBoardVo edit(int board_no);
	int update(ImageBoardVo vo);
	int delete(ImageBoardVo vo);
	
}
