package announce;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnounceVo {
	private int announceno;
	private int my_userno;
	private int friends_userno;
	private int boardno;
	private int tablename;
	private int board_or_comment;
	private int read_check;
	private Timestamp regdate;
}
