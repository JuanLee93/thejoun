package like;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
	
	@Autowired
	LikeDao likedao;
	
	
	public int likeUpdate(LikeVo vo) {
		int r = 0;
		if (likedao.likeCount(vo) > 0) {
			likedao.likeDelete(vo);
			r = 1;
		} else {
			likedao.likeInsert(vo);
			r = 2;
		}
		return r;
	}
	
}
