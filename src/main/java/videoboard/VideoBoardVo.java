package videoboard;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;
@Getter
@Setter	

public class VideoBoardVo extends CommonVo{
	private int video_board_no;
	private int userno;
	private String title;
	private String content;
	private Timestamp regdate;
	private int readcount;
	private String filename_org;
	private String filename_real;
	private String url;
	private String nickname;
	private String id;
	private String name;
	private int c_count;
	private int l_count;
	private String orderby;
	private String direction;	
	private int state;
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
	
	public VideoBoardVo() {
		this.orderby = "regdate";
		this.direction = "DESC";
	}
}
