package concernboard;

import java.util.List;

import comment.CommentVo;
import freeboard.FreeBoardVo;

public interface ConcernBoardService {

	List<ConcernBoardVo> selectList(ConcernBoardVo vo);
	List<ConcernBoardVo> mainSelectList(ConcernBoardVo vo);
	int count(ConcernBoardVo vo);
	int insert(ConcernBoardVo vo);
	ConcernBoardVo view(int board_no);
	ConcernBoardVo edit(int board_no);
	int update(ConcernBoardVo vo);
	int delete(ConcernBoardVo vo);
	int updateAnnounce(CommentVo cv);
	int updateNotice(ConcernBoardVo vo);
	int getRownum(ConcernBoardVo vo);
	ConcernBoardVo getNext(ConcernBoardVo vo);
	ConcernBoardVo getPrev(ConcernBoardVo vo);
}
