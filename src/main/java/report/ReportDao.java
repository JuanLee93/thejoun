package report;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import like.LikeVo;

@Repository
public class ReportDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int reportCount(ReportVo vo) {
	
		return sqlSessionTemplate.selectOne("report.reportCount",vo);
	}
	
	public int reportInsert(ReportVo vo) {
		return sqlSessionTemplate.insert("report.reportInsert",vo);
	}
	
	public int reportDelete(ReportVo vo) {
		return sqlSessionTemplate.delete("report.likeDelete",vo);
	}
	public int reportUpdate(ReportVo vo) {
		return sqlSessionTemplate.update("report.reportUpdate",vo);
	}
}
