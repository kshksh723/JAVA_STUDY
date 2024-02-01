//package my.day13.a.inheritance;
//
//public class Company extends CommonMember {
//	// company 클래스는 commonMember 클래스에 생성되어진 field 및 method 및 생성자를 상속 받아온다 
//	// extends 다음 나오는 commonMember클래스는 Gujikja 클래스의 부모 클래수가 되어지고
//	// gujikja클래스는 commonMember 클래스의 자식 클래스가 되어진다
//	// 상속성은 중복을 제거하는데 교집합을 생각하면 된다
//	public static void main(String[] args) {
//		// field 생성
//		// field 의 캡슐화(EnCapsulation == 은닉화)
//	
//		private String business_number;    // 사업자 등록번호
//		private String job_type;          // 회사직종타입(제조업, 서비스업, IT, ...)
//		private long seed_money;          // 자본금,long 타입(개수) 
//		
//		static int count;  // company 객체(인스턴스)의 개수를 알아오려는 용도
//		// 기본생성자는 생략되어진다. 
//		
//		public String getBusiness_number(String business_number) {
//			// 사업자 등록번호는 오로지 숫자롬나 들어와야 하며
//			// 첫글자는 0이 들어로올 수 없다
//			// 또한 사업자 등록번호의 길이는 10글자가 되어야 한다
//			
//			this. businessnumber
//		}
//	}
//
//}
package my.day13.a.inheritance;


public class Company extends CommonMember {
//  Company 클래스는 CommonMember 클래스에 생성되어진 field 및 method 및 생성자를 상속 받아온다. 
//  CommonMember 클래스는 Company 클래스의 부모클래스 가 되어지고,
//  Company 클래스는 CommonMember 클래스의 자식클래스 가 되어진다.
	
	// field 생성
	// field 의 캡슐화(EnCapsulation == 은닉화)
	
	private String business_number;   // 사업자등록번호 
	private String job_type;          // 회사직종타입(제조업, 서비스업, IT, ...)
	private long seed_money;          // 자본금 
	
	static int count;     // Company 객체(인스턴스)의 개수를 알아오려는 용도	

	// 기본생성자 생략됨!!
		public Company() {
			// super(); //company 클래스의 부모클래스인 COMMONMEMBER클래스의 기본생성자이다
		}

		public String getBusiness_number() {
		return business_number;
	}

	public void setBusiness_number(String business_number) {
		
		// 사업자등록번호는 오로지 숫자로만 들어와야 하며
		// 첫글자는 0 이 들어올 수 없다.
		// 또한 사업자 등록번호의 길이는 10글자가 되어야 한다.
		
		
		this.business_number = business_number;
	}

	public String getJob_type() {
		return job_type;
	}

	public void setJob_type(String job_type) {
		
		this.job_type = job_type;
	}

	public long getSeed_money() {
		return seed_money;
	}

	public void setSeed_money(long seed_money) {
		
		this.seed_money = seed_money;
	}
	
	
}
