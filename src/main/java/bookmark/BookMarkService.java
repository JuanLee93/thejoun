package bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMarkService {
	
	@Autowired
	BookMarkDao bookMarkDao;
	
	public int bookmarkUpdate(BookMarkVo vo) {
		int r = 0;
		if (bookMarkDao.bookmarkCount(vo) > 0) {// 이미 북마크에 있는 경우
			r = 1;
			
		} else {// 북마크에 없는 경우 -> 추가
			bookMarkDao.bookmarkInsert(vo);
			r = 2;
		}
		return r;
	}
	
}
