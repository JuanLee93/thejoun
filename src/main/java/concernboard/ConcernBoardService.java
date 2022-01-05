package concernboard;

import java.util.List;

public interface ConcernBoardService {

	int count(ConcernBoardVo vo);
	List<ConcernBoardVo> selectList(ConcernBoardVo vo);
	int insert(ConcernBoardVo vo);
	ConcernBoardVo view(int board_no);
	ConcernBoardVo edit(int board_no);
	int update(ConcernBoardVo vo);
	int delete(ConcernBoardVo vo);
	
}
