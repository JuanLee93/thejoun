package myPage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class MyPageVo extends CommonVo{
// 이vo는 계정에서 북마크한거 리스트로 가져오기위해서 만듦
	private int board_no;
	private String title;
	private String nickname;
	private Timestamp regdate;
	private int readcount;
	private int userno;
	private int tablename;
	private String noticeYN;
	private int new_time;
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
}
