package adminLog;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class AdminLogVo extends CommonVo {
	
	private int log_no;
	private int admin_no;
	private String msg;
	private Timestamp date;
	private String login_IP;
	private String name;

}
