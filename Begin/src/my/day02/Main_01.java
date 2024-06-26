package my.day02;
// === 멤버변수와 local(지역) 변수의 차이점에 대해서 알아본다. === //
// 멤버 변수 ==> instance 변수와 static 변수를 합친 것을 멤버변수라고 부른다



public class Main_01 {
	// instance 변수
	
	
	
	String id;  //문자열 타입
	String pwd; 
	String name;
	String email;
	int age; // int는 정수 타입
	double height; // double은 실수타입
	char grade ; // char는 문자형 타입
	
	
	/*
	 멤버변수( instance 변수와 static 변수를 합친 것)는 
	 즉,  instance 변수와 static 변수는 초기화를 하지 않더라도 자동적으로 초기화가 된다
	 -> 필수 암기
	 정수형인 데이터타입(byte, short, int, long)은 자동적으로 0으로 초기화가 되고, 
	 실수형인 데이터타입(float, double)은 자동적으로 0.0 으로 초기화가 된다
	 문자형인 데이터타입(char)는 자동적으로 ' '으로 초기화가 되고, 
	 String을 포함한 클래스 타입은 자동적으로 null로 초기화가 된다
	 
	 * 초기화란? 변수에 어떤 값을 부여하는 것을 초기화라고 부른다.
	 -> 암기
	 
	 
	 */
	
	// instance 메소드
	void info_print() {
		
		int hope_money = 5000;
		String address = "서울시 마포구";
		
		System.out.println("=== 회원정보 ===\r\n"
				+ "		 1. 아이디 : "  + id + "\n"
				+ "		 2. 비밀번호 : " + pwd + "\n"
				+ "		 3. 성명 : " + name + "\n"
				+ "		 4. 이메일 : " + email + "\n"
				+ "		 5. 나이 : " + age + "\n"
				+ "		 6. 신장 : " + height + "\n"
				+ "		 7. 등급 : " + grade + "\n"
				+ "		 8. 희망급여 : " + hope_money + "\n"
				+ "		 9. 주소 : " + address + "\n");
		
	}
		/* String address = null; -> 초기화가 되어진 것 */
		// 반드시 초기화를 해줘야 한다.
		
		
		//지역변수 (local variable)는 반드시 초기화 (== 변수에 어떤 값을 부여하는 것을 초기화라고 부른다)를 꼭 해주어야한다.!!!!
		// 지역변수는 { }  내에서만 사용되는 것으로 {} 을 벗어나는 순간 지역변수는 자동적으로 메모리(RAM)에서 삭제가 되어진다
		
		// {{1}2} 1번 부터 안까지 클래스가 다 쓰임, 2번까지 쓰임
		// 가독성을 위해 hope_money라고 했음
		//hopeMoney : 단어 + 단어 사이에 대문자 : 카멜기법
		
		
		
		
		
		void info_view() {
			//지역변수 (local variable)는 반드시 초기화 (== 변수에 어떤 값을 부여하는 것을 초기화라고 부른다)를 꼭 해주어야한다.!!!!
			// 지역변수는 { }  내에서만 사용되는 것으로 {} 을 벗어나는 순간 지역변수는 자동적으로 메모리(RAM)에서 삭제가 되어진다
			
			int hope_money = 5000;
			String address = "경기도 군포시";
			
		
		
		System.out.println("=== 회원정보 ===\r\n"
				+ "		 1. 아이디 : "  + id + "\n"
				+ "		 2. 비밀번호 : " + pwd + "\n"
				+ "		 3. 성명 : " + name + "\n"
				+ "		 4. 이메일 : " + email + "\n"
				+ "		 5. 나이 : " + age + "\n"
				+ "		 6. 신장 : " + height + "\n"
				+ "		 7. 등급 : " + grade + "\n"
				+ "		 8. 희망급여 : " + hope_money + "\n"
				+ "		 9. 주소 : " + address + "\n");
		
		/*
		 문자열과 문자열 사이의 +는 문자열 결합을 뜻하는 것이고, 
		 숫자와 숫자 사이의 +는 더하기(PLUS)를 뜻하는 것이고,
		 문자열과 숫자 사이의 +는 문자열 결합을 뜻하는 것이다.
		 
		 */
		
	}
	public static void main(String[] args) {
		Main_01 ma1 = new Main_01();
		// 인스턴스화(== 객체 생성)
		ma1.id = "leess";
		ma1.pwd = "qwer1234";
		ma1.name = "이순신";
		
		ma1.info_print();
		ma1.info_view();
		
		//정보를 꺼내고 싶을 때
		
		/*
		 === 회원정보 ===
		 1. 아이디 : leess
		 2. 비밀번호 : qwer1234
		 3. 성명 : 이순신
		 */
		
		
		
	}

}
