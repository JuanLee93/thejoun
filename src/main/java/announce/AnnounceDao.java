package announce;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnnounceDao {

	@Autowired
	SqlSessionTemplate sst;
	
	public int announceInsert1(AnnounceVo av) {
		return sst.insert("announce.announceInsert1", av);
	}
	
	public int announceInsert2(AnnounceVo av) {
		return sst.insert("announce.announceInsert2", av);
	}
	
	public int announceInsert3(AnnounceVo av) {
		return sst.insert("announce.announceInsert3", av);
	}
	
	public int announceInsert4(AnnounceVo av) {
		return sst.insert("announce.announceInsert4", av);
	}
	
	public int findMakeComment(int vo) {
		return sst.selectOne("announce.findMakeComment", vo);
	}
	
	public int chatCountMain(int num) {
		return sst.selectOne("announce.chatCountMain", num);
	}
	
	public List<AnnounceUserVo> userVoList(int userno){
		return sst.selectList("announce.userVoList", userno);
	}
	
	public int announceCount(int userno) {
		return sst.selectOne("announce.announceCount", userno);
	}
}
