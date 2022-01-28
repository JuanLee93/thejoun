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
	private int admin_no;//관리자페이지-자유게시판 공지등록하는 경우 사용
	private String title;
	private String contents;
	private Timestamp regdate;
	private int readcount;
	private String filename_org;
	private String filename_real;
	private String noticeYN;//공지여부
	private String useYN;//관리자페이지-자유게시판 등록(공지-공개여부)
	private String id;
	private String name;
	private String admin_name;
	private String nickname;
	private int c_count;
	private int l_count;
	private String orderby;
	private String direction;
	private int state;//상태-정상/신고된 글
	private int rownum;//이전글/다음글에서 사용
	private int new_time;//새로운 글(new) 표시
	private char is_user;//이 값이 'Y'이고, useYN = 'Y'이면 사용자화면에서 공개
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
	
	public FreeBoardVo() {
		this.orderby = "regdate";
		this.direction = "DESC";
	}
}
