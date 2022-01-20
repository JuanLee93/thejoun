package admin;

import lombok.Getter;
import lombok.Setter;
import util.CommonVo;

@Getter
@Setter
public class AdminVo extends CommonVo {
	
	private int admin_no;
	private String id;
	private String pwd;
	private String name;

}
