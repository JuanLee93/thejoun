package concernboard;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import freeboard.FreeBoardVo;
import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class ConcernBoardVo extends CommonVo {
	private int concern_board_no;
	private int userno;
	private int admin_no;//관리자페이지-고민게시판 공지등록하는 경우 사용
	private String title;
	private String contents;
	private Timestamp regdate;
	private int readcount;
	private String filename_org;
	private String filename_real;
	private int category;
	private String id;
	private String name;
	private String nickname;
	private int c_count;
	private int l_count;
	private String orderby;
	private String direction;
	private int state;
	private int rownum;//이전글/다음글에서 사용
	private String noticeYN;
	private int new_time;//새로운 글(new) 표시
	private String isopen;//관리자페이지-고민게시판 등록(공개/비공개-radio name)
	private char is_user;//이 값이 'Y'이면 사용자화면에서 공개(useYN = 'Y')	
	private String useYN;//관리자페이지-고민게시판 등록(공지-공개여부)
	private String admin_name;
	public String getRegdate() {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}  
	
	public ConcernBoardVo() {
		this.orderby = "regdate";
		this.direction = "DESC";
		
	}
	
}
