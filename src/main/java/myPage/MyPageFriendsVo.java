package myPage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageFriendsVo {
	
	private int userno;
	private String nickname;
	private Timestamp regdate;
	
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
}
