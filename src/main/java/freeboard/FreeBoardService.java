package freeboard;

import java.util.List;

public interface FreeBoardService {

	int count(FreeBoardVo vo);
	List<FreeBoardVo> selectList(FreeBoardVo vo);
	int insert(FreeBoardVo vo);
	FreeBoardVo view(int board_no);
	FreeBoardVo edit(int board_no);
	int update(FreeBoardVo vo);
	int delete(FreeBoardVo vo);
	
}
