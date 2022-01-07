package chat;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatVo {

	private int chatID;
	private String fromID;
	private String toID;
	private String content;
	private Timestamp regdate ;
	private int read;
	
}
