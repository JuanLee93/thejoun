package chat;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatVo {
	private int chatno;
	private int from_id;
	private int to_id;
	private String chatcontent;
	private Timestamp chatregdate;
	private int checkmessage;
	
}
