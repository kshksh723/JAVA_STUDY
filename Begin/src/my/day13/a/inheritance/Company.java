package my.day13.a.inheritance;

public class Company extends CommonMember {
	// company 클래스는 commonMember 클래스에 생성되어진 field 및 method 및 생성자를 상속 받아온다 
	// extends 다음 나오는 commonMember클래스는 Gujikja 클래스의 부모 클래수가 되어지고
	// gujikja클래스는 commonMember 클래스의 자식 클래스가 되어진다
	// 상속성은 중복을 제거하는데 교집합을 생각하면 된다
	public static void main(String[] args) {
		// field 생성
		// field 의 캡슐화(EnCapsulation == 은닉화)
	
		private String business_number;    // 사업자 등록번호
		private String job_type;          // 회사직종타입(제조업, 서비스업, IT, ...)
		private long seed_money;          // 자본금,long 타입(개수) 
		
		static int count;  // company 객체(인스턴스)의 개수를 알아오려는 용도
		// 기본생성자는 생략되어진다. 
		
	}
}
