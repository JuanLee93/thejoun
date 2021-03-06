package user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class UserVo extends CommonVo {
	private int userno;
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private int gender;
	private Timestamp regdate;
	private String tel;
	private Timestamp moddate;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String email;
	private String newPwd;
	private String filename_org;
	private String filename_real;
	private int board_no;
	private int tablename;
	private String noticeYN;
	private int new_time;
	private int l_count;
	
	//이거 2개는 db에 안들어가있음 불러오기 편할라고 집어넣었어요
	private int messagecount;  // 확인안한 메세지 카운트할라고 만듬
	private Timestamp chatregdate; // 마지막으로 온 채팅 시간 확인용
	private long regdateCheck; // 친구창에 마지막메세지 몇초전 몇분전 이렇게 나오게할라고 추가함
	private String ccc;
		
}
