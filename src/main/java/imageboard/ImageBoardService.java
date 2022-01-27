package imageboard;

import java.util.List;

import comment.CommentVo;
import freeboard.FreeBoardVo;

public interface ImageBoardService {

	int count(ImageBoardVo vo);
	List<ImageBoardVo> selectList(ImageBoardVo vo);
	int insert(ImageBoardVo vo);
	ImageBoardVo view(int image_board_no);
	ImageBoardVo edit(int image_board_no);
	int update(ImageBoardVo vo);
	int delete(ImageBoardVo vo);
	int updateAnnounce(CommentVo cv);
	int getRownum(ImageBoardVo vo);
	ImageBoardVo getNext(ImageBoardVo vo);
	ImageBoardVo getPrev(ImageBoardVo vo);
}
