package user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import question.QuestionVo;
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
	
	//이거 2개는 db에 안들어가있음 불러오기 편할라고 집어넣었어요
	private int messagecount;  // 확인안한 메세지 카운트할라고 만듬
	private Timestamp chatregdate; // 마지막으로 온 채팅 시간 확인용
}
