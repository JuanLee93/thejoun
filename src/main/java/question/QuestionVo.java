package question;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class QuestionVo extends CommonVo {

	private int qna_no;
	private int userno;
	private int admin_no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int readcount;
	private String reply;
	private String filename_org;
	private String filename_real;
	private Timestamp updatedate;
	private String state;
	private String secret;
	private String nickname;
	private String noticeYN;
	private int new_time;
	private int c_count;
	private int l_count;
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
	
	public String getUpdatedate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.updatedate);
	}  
}

