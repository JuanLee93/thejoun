package announce;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnounceUserVo {
	
	private int announceno;
	private int my_userno;
	private int friends_userno;
	private int boardno;
	private int tablename;
	private int board_or_comment;
	private String nickname;
	private Timestamp regdate;
	private String content;
	
}
