package my.day13.a.inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recruit {

	//field 
	private Company cp;				// 회사 
	private String work_type;		// 모집분야 (근무 형태)
	private int cnt;				// 모집인원
	private String register_day;	// 모집공고 등록일자
	private String finish_day;		// 모집 마감일자
	
	static int count; //Recruit 객체(인스턴스)의 개수를 알아오려는 용도,모집 공고등록 번호
	
	// 기본 생성자
	public Recruit() {
		Date now = new Date(); // 현재시각
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		register_day = df.format(now);
	}
// method
	public Company getCp() {
		return cp;
	}

	public void setCp(Company cp) {
		this.cp = cp;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getRegister_day() {
		return register_day;
	}

	public void setRegister_day(String register_day) {
		this.register_day = register_day;
	}

	public String getFinish_day() {
		return finish_day;
	}
	public void setFinish_day(String string) {
		// TODO Auto-generated method stub
		
	}
	
	// method
	
	
	
	
	
}
