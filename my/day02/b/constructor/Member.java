package my.day02.b.constructor;

public class Member {

	// field, attribute, property, 속성
	String userid;
	String passwd;
	String name;
	String email;
	int age;
	int point;
	
	// constructor(생성자)는 2가지가 있는데
	// 기본생성자( 파라미터(==매개변수)가 없는 생성자 )와 
	// 파라미터(==매개변수)가 있는 생성자로 나뉘어진다.
	
	
	// 기본생성자 ( 파라미터(==매개변수)가 없는 생성자 )
	Member() {} // 파라미터(==매개변수)가 있는 생성자와 함께 기본생성자를 사용할 경우라면 반드시 기본생성자를 선언해주어야 한다.!!! 
	
	// !!!! 중요암기 !!!! //
	// 클래스를 생성할 때 생성자를 표기한 것이 없는 경우라면 
	// 위와 같은 기본생성자인 Member() {} 이 생략되어 있는 것이다.!!!!
	// 그런데 파라미터(==매개변수)가 있는 생성자를 선언해버리면 기본생성자는 자동적으로 삭제가 되어져 버린다.!!!!  
	
	// 파라미터(==매개변수)가 있는 생성자
	Member(String userid, String passwd, String name, int age, int point) { 
		/*
		   지역변수명과 멤버변수명(instance 변수 와 static 변수 모두를 지칭하는 것)이 
		   일치할 경우에는 지역변수가 더 우선된다.   
		*/
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.point = point;
	}
	
	
	Member(String userid, String passwd, String name, String email, int age, int point){
		this(userid, passwd, name, age, point); // 위에서 정의한 파라미터가 있는 생성자를 호출한다는 것이다. 
		this.email = email;
	}
	
	
	// behavior, 행위, 기능, method
	void info_print() {
		System.out.println("=== "+ name +"님의 회원정보 ===\n"
						  + "1. 아이디 : " + userid + "\n"
						  + "2. 비밀번호 : " + passwd + "\n"
						  + "3. 성명 : " + name + "\n"
						  + "4. 나이 : " + age + "\n"
						  + "5. 포인트 : " + point + "\n"
						  + "6. 이메일 : " + email + "\n"); 
	}
	
	// == 퀴즈 == //
	void update(String userid, String passwd, String name, String email, int age, int point) {
		
		System.out.println(">>> 변경전 정보 <<<");
		info_print();
		
		System.out.println(">>> 변경후 정보 <<<");
	/*	
		this(userid, passwd, name, email, age, point);
		// 생성자로 사용되는 this() 또는 this(파라미터, 파라미터, ....) 은 오로지 생성자 내에서만 사용이 가능하다.!!!
	*/	
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
		this.age = age;
		this.point = point;
		
		info_print();
	}
	
	
	
	
}
