package comment;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo {

	private int comment_no;
	private int board_no;
	private String content;
	private int like;
	private Timestamp regdate;
	private int gno;
	private int ono;
	private int nested;
	private int tablename;
	private int userno;
	private String name;
}
