package announce;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnnounceDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public int announceInsert2(AnnounceVo av) {
		return sst.insert("announce.announceInsert2", av);
	}
}
