package myPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.UserVo;

@Service
public class MyPageService {

	@Autowired
	MyPageDao mpd;
	
	public int count(UserVo vo) {
		return mpd.count(vo); 
	}
	
	public List<MyPageVo> selectList(UserVo vo){
		return mpd.selectList(vo);
	}
}
