package report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	
	@Autowired
	ReportDao reportDao;
	
	public int reportUpdate(ReportVo vo) {
		int r = 0;
		if (reportDao.reportCount(vo) > 0) {// 이미 신고DB에 있는 경우
			r = 1;
			
		} else {// 신고DB에 없는 경우 -> 추가
			reportDao.reportInsert(vo);
			
			r = 2;
		}
		return r;
	}
	
}
