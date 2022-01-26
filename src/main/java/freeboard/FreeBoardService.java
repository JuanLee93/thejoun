package freeboard;

import java.util.List;

import comment.CommentVo;

public interface FreeBoardService {

	int count(FreeBoardVo vo);
	List<FreeBoardVo> selectList(FreeBoardVo vo);
	List<FreeBoardVo> mainSelectList(FreeBoardVo vo);
	int insert(FreeBoardVo vo);
	FreeBoardVo view(int board_no);
	FreeBoardVo edit(int board_no);
	int update(FreeBoardVo vo);
	int delete(FreeBoardVo vo);
	int updateAnnounce(CommentVo cv);
	int insertNotice(FreeBoardVo vo);
	int updateNotice(FreeBoardVo vo);
	
}
