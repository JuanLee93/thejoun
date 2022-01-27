package notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import notice.NoticeVo;

@Repository
public class NoticeDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(NoticeVo vo) {
		return sqlSessionTemplate.selectOne("notice.count", vo);
	}
	
	public List<NoticeVo> selectList(NoticeVo vo){
		return sqlSessionTemplate.selectList("notice.selectList", vo);
	}
	
	public List<NoticeVo> mainSelectList(NoticeVo vo){
		return sqlSessionTemplate.selectList("notice.mainSelectList", vo);
	}
	
	public int insert(NoticeVo vo) {
		int r = 0;
		try {
			r = sqlSessionTemplate.insert("notice.insert", vo);
		} catch (Exception e) {
			r = -1;
			System.out.println(e.getMessage());
		}
		return r;
	}

	public NoticeVo selectOne(int notice_no) {
		return sqlSessionTemplate.selectOne("notice.selectOne", notice_no);
	}
	
	public int updateReadcount(int notice_no) {
		return sqlSessionTemplate.update("notice.updateReadcount", notice_no);
	}
	
	public int update(NoticeVo vo) {
		return sqlSessionTemplate.update("notice.update", vo);
	}
	
	public int delete(NoticeVo vo) {
		return sqlSessionTemplate.delete("notice.delete", vo.getNotice_no());
	}
	
	
	public int replyUpdate(NoticeVo vo) {
		return sqlSessionTemplate.update("notice.replyUpdate", vo);
	}
	
	public int getRownum(NoticeVo vo) {
		return sqlSessionTemplate.selectOne("notice.getRownum", vo);
	}
	
	public NoticeVo getNext(NoticeVo vo) {
		return sqlSessionTemplate.selectOne("notice.getNext", vo);
	}
	
	public NoticeVo getPrev(NoticeVo vo) {
		return sqlSessionTemplate.selectOne("notice.getPrev", vo);
	}
}
