package freeboard;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class FreeBoardVo extends CommonVo {

	private int board_no;
	private int userno;
	private String title;
	private String content;
	private Timestamp regdate;
	private int readcount;
	private String filename_org;
	private String filename_real;
	private String id;
	private String name;
	private String nickname;
	private int c_count;
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
}
