package notice;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class NoticeVo extends CommonVo {

	private int notice_no;
	private int admin_no;
	private String title;
	private String contents;
	private Timestamp regdate;
	private int readcount;
	private int noticename;//공지사항 여부
	private String filename_org;
	private String filename_real;
	private int newnotice;//새로운 글
	private String orderby;
	private String direction;
	private String id;
	private String name; 
	private int rownum;
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
	
	public NoticeVo() {
		this.orderby = "regdate";
		this.direction = "DESC";
	}
}

