package imageboard;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class ImageBoardVo extends CommonVo {

	private int image_board_no;
	private int userno;
	private String title;
	private String content;
	private Timestamp regdate;
	private int readcount;
	private int category;
	private String id;
	private String name;
	private String nickname;
	private int c_count;
	private int l_count;
	private String orderby;
	private String direction;
	private int state;
	private int rownum;
	private int new_time;//새로운 글(new) 표시
	
	public String getRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.regdate);
	}
	
	public ImageBoardVo() {
		this.orderby = "regdate";
		this.direction = "DESC";
	}
}
