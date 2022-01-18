package friendsAdd;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendsAddVo {

	private int friendsaddno;
	private int from_userno;
	private int to_userno;
	private Timestamp regdate;
	private String pageUrl;    //친구추가시 해당 게시판 주소로 이동하게하기위해서 만듬
}
