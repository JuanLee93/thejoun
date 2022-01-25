package report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	
	@Autowired
	ReportDao reportDao;
	
	public int reportUpdate(ReportVo vo) {
		
		int r = 0;
		
		if (reportDao.reportCount(vo) > 0) {// 이미 신고한 경우
			r = 1;
			
		} else {// 새로 신고하는 경우
			reportDao.reportInsert(vo);
			r = 2;
		}

		return r;
	}

}
