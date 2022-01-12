package bookmark;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookMarkDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int bookmarkCount(BookMarkVo vo) {
		return sqlSessionTemplate.selectOne("bookmark.bookmarkCount",vo);
	}
	
	public int bookmarkInsert(BookMarkVo vo) {
		return sqlSessionTemplate.insert("bookmark.bookmarkInsert",vo);
	}
	
	public int bookmarkDelete(BookMarkVo vo) {
		return sqlSessionTemplate.delete("bookmark.bookmarkDelete",vo);
	}
}
