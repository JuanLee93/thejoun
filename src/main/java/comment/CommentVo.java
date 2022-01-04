package comment;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo {

	private int comment_no;
	private int board_no;
	private int userno;
	private String content;
	private int like;
	private Timestamp regdate;
	private int gno;
	private int ono;
	private int nested;
	private String tablename;
	private int concern_board_no;
	private int image_board_no;
	private int video_board_no;
}
