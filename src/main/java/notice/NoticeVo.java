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
	private String filename_org;
	private String filename_real;
	private String useYN;//관리자페이지- 글의 공개/비공개여부
	private String orderby;
	private String direction;
	private String id;
	private String name; 
	private int rownum;
	private int new_time;//새로운 글(new) 표시
	private char is_user;//이 값이 'Y'이고, useYN = 'Y'이면 사용자화면에서 공개
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
	
	public NoticeVo() {
		this.orderby = "regdate";
		this.direction = "DESC";
	}
}

