package like;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int likeCount(LikeVo vo) {
		return sqlSessionTemplate.selectOne("like.likeCount",vo);
	}
	
	
	public int likeInsert(LikeVo vo) {
		return sqlSessionTemplate.insert("like.likeInsert",vo);
	}
	
	public int likeDelete(LikeVo vo) {
		return sqlSessionTemplate.delete("like.likeDelete",vo);
	}
}
