package report;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportVo {

	private int report_no;
	private int userno;
	private int tablename;
	private int board_no;
	private Timestamp date;
	private int state;
}
