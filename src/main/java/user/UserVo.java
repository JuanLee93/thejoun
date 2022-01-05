package user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
	private int userno;
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String gender;
	private Timestamp regdate;
	private String tel;
	private Timestamp moddate;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String email;
}
