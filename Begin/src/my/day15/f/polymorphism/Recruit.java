package my.day15.f.polymorphism;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recruit {

	// field
	private int recruit_no; // 채용 공고 순번  
	private Company cp;           // 회사 
	private String subject;		  // 채용제목
	private String work_type;     // 채용분야(근무형태)
	private int pay;              // 연봉
	private int cnt;              // 모집인원 수
	private String register_day;  // 채용 공고 등록일자
	private String finish_day;    // 채용 마감일자
	private Object yearpay;
	
	public static int count; // Recruit 객체(인스턴스)의 개수를 알아오려는 용도, 
	
	// rc_arr[count++] = rc1;
	// 기본생성자
	public Recruit() {
		recruit_no = count + 1; //set 하고 있음
		
		Date now = new Date(); // 현재시각
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		register_day = df.format(now); // set 하고 있음
	}

	// method 
	public int getRecruit_no() {
		return recruit_no;
	}
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if(subject != null && ! subject.isBlank()) {
			// 텅 비어이어있으면 참 ! subject.isBlank() -> 텅 비어있지 않다
			this.subject = subject;
		}
		
		else {
			System.out.println("[경고] 채용하려면 반드시 회사 정보를 입력하세요!!\n");
		}
	}

	public Company getCp() {
		return cp;
	}

	public void setCp(Company cp) {
		if(cp != null) {
			this.cp = cp;
		}
		else {
			System.out.println("[경고] 채용하려면 반드시 회사 정보를 입력하세요!!\n");
		}
		
		this.cp = cp; // null 값이 들어오면 안됌
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		if(work_type != null && !work_type.isBlank()) {
		this.work_type = work_type;
	}
	else {
		System.out.println("[경고] 채용하려면 반드시 회사 정보를 입력하세요!!\n");
	}
	}
	

	public int getCnt() {
		return cnt;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		if(pay > 0 ) {
			this.pay = pay;
		}
		this.pay = pay;
	}

	public void setCnt(int cnt) {
		if(cnt > 0  ) {
			this.cnt = cnt;
		}
		else { System.out.println("채용인원은 0보다 커야합니다");
		}
	}
	public String getFinish_day() {
	
			return finish_day;
	}

	public void setFinish_day(String finish_day) { 
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false); 	// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는 값일 경우 오류가 발생한다.
	      						// 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다.  
	//df.parse(finish_day); try.catch 누르기
		
		try {
			// 날짜 비교 하기
			Date date_register_day = df.parse(register_day);
			// 채용 공고 등록일자 문자열값을 날짜타입으로 변환
			Date date_finish_day  =  df.parse(finish_day); // 비교하려고 string 타입을 배열로 바꿈 
			// 채용마감 일자 문자열값을 날짜 타입으로 변환
			
			if(date_finish_day.after(date_register_day)) { // date_finish_day.after(date_register_day) 마감일자가 공고일자보다 뒤에 있어야 한다
			
				this.finish_day = finish_day;
				
			} else {
				System.out.println("[경고] 채용마감일자는 현재일자 보다 이후인 날짜 이어야 합니다 \n");
			}
		} catch (ParseException e) {
		System.out.println("[경고] 채용 마감일자는 달력에 존재하는 날짜이어야합니다 \n");
		
		}
	}	
	

	public String getRegister_day() {
		return register_day;
	}

	// 채용공고를 알려주는 메소드
	public String recruit_info() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("=".repeat(50)+"\n");
		sb.append("1. 채용 번호 :" + recruit_no + "\n");
		sb.append("2. 채용 제목 :" +subject + "\n");
		sb.append("3. 채용 분야 :" +work_type + "\n");
		sb.append("4. 채용 인원 :" +cnt + "\n");
		sb.append("5. 연봉 : " + new DecimalFormat("#,###").format(yearpay)+ "만원 \n");
		sb.append("6. 등록일자 :" + register_day.substring(0, 4)+"-"+register_day.substring(4, 6)+"-"+register_day.substring(6) + "\n"); 
		sb.append("7. 채용마감일자 :" + finish_day.substring(0, 4)+"-"+finish_day.substring(4, 6)+"-"+finish_day.substring(6)+ "\n");
		sb.append("=".repeat(50)+"\n");
		
		return sb.toString();
	}// end of public String recruit_info() 
	
	
}
