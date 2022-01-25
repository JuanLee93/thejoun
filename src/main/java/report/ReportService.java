package report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	
	@Autowired
	ReportDao reportDao;
	
	public int reportUpdate(ReportVo vo) {
		int r = 0;
		
		int rv = reportDao.searchReport(vo);
		
		if (rv > 0) {//이미 신고DB에 있는 경우
			if (reportDao.reportCount(vo) > 0) {// 내가 해당 게시글을 신고한 경우
				r = 1;
				
			} else {// 내가 해당 게시글을 신고하지 않은 경우
				r = 1;
			}
		
		} else {// 신고DB에 없는 경우
			reportDao.reportInsert(vo);
			r = 2;
		}
		
		return r;
	}

}
