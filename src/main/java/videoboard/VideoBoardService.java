package videoboard;

import java.util.List;

import comment.CommentVo;

public interface VideoBoardService {

	List<VideoBoardVo> selectList(VideoBoardVo vo);
	int count(VideoBoardVo vo);
	int insert(VideoBoardVo vo);
	VideoBoardVo view(int board_no);
	VideoBoardVo edit(int board_no);
	int update(VideoBoardVo vo);
	int delete(VideoBoardVo vo);
	int updateAnnounce(CommentVo cv);
	
}
