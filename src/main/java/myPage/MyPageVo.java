package myPage;

import java.sql.Timestamp;

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
	
}
