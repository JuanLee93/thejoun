package myPage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class MyPageVo extends CommonVo{

	private int board_no;
	private String title;
	private String nickname;
	private Timestamp regdate;
	private int readcount;
	private int userno;
	private int tablename;
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
}
