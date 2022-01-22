package announce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comment.CommentVo;

@Service
public class AnnounceService {
	
	@Autowired
	AnnounceDao ad;
	
	public int announceInsert1(AnnounceVo av) {
		return ad.announceInsert1(av);
	}
	
	public int announceInsert2(AnnounceVo av) {
		return ad.announceInsert2(av);
	}
	
	public int announceInsert3(AnnounceVo av) {
		return ad.announceInsert3(av);
	}
	
	public int announceInsert4(AnnounceVo av) {
		return ad.announceInsert4(av);
	}
	
	public int findMakeComment(int vo) {
		return ad.findMakeComment(vo);
	}
}
