package videoboard;

import java.util.List;

public interface VideoBoardService {

	List<VideoBoardVo> selectList(VideoBoardVo vo);
	int count(VideoBoardVo vo);
	int insert(VideoBoardVo vo);
	VideoBoardVo view(int board_no);
	VideoBoardVo edit(int board_no);
	int update(VideoBoardVo vo);
	int delete(VideoBoardVo vo);
	
}
