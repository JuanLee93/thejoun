package freeboard;

import java.util.List;

public interface FreeBoardService {

	int count(FreeBoardVo vo);
	List<FreeBoardVo> selectList(FreeBoardVo vo);
	int insert(FreeBoardVo vo);
	FreeBoardVo view(int boardno);
	FreeBoardVo edit(int boardno);
	int update(FreeBoardVo vo);
	int delete(FreeBoardVo vo);
	
}
