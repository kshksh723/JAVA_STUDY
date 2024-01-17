package my.day01;

public class Member_main {

		// 콘솔프로그램의 실행은 main() 메소드에서 실행한다.
	public static void main(String[] args) {
			
		
			// 1명의 새로운 회원이 필요하다.
		Member lssMbr = new Member();  // Member 클래스 파일을 읽었다가 메모리가 로딩
					  // Member 클래스 인스턴스(== 객체)화 했다라고 부른다.
		//Member_main 클래스와 Member 클래스는 동일한 패키지에 저장된다.
		// 단어와 단어 사이를 연결을 지을 때 대소문자로 구분짓는 것을 camel 기법
		// 예 > lssmbr라고 사용하지 않고 lssMbr와 같이 쓰는 것.
		
		System.out.println(lssMbr);
		// my.day01.Member@73a28541
		// 패키지명.클래스명@메모리(RAM)상에 올라온 주소값
		// lssMbr를 사용하여 my.day01.Member@73a28541을 제어가능 
		// 그래서 lssMbr은 메모리 참조변수라고 부른다
		
		// 또  1명의 새로운 회원이 필요하다.
		Member eom_mbr = new Member(); // Member 클래스 파일을 읽었다가 메모리가 로딩
									   // Member 클래스를 인스턴스(== 객체)화 했다라고 부른다
		// 단어와 단어 사이를 연결을 지을 때 _로 구분짓는 것을 snake 기법
		// 예 > eommbr라고 사용하지 않고 eom_mbr와 같이 쓰는 것.
		System.out.println(eom_mbr);
		// my.day01.Member@6f75e721
		// 패키지명.클래스명@메모리(RAM)상에 올라온 주소값
		// eom_mbr를 사용하여 my.day01.Member@6f75e721을 제어할 수 있다
		// 그래서 eom_mbr은 메모리 참조변수라고 부른다
		
	      lssMbr.id = "leesss"; // = 은 같다가 아니라 대입해준다는 뜻
	      lssMbr.pwd = "abcd"; 
	      lssMbr.name = "이순신"; 
	      lssMbr.email = "leess@naver.com"; 
		
		/*lssMbr.address = "서울시 강남구"; 이렇게 사용해도되지만, static 변수는 아래와 같이 클래스명.변수명으로 사용할 것을 권장함 */
	      Member.address = "서울시 강남구";
		
		System.out.println("== lssMbr이라는 인스턴스(객체)에 저장된 필드 값 출력하기 1번째 == ");
		
		System.out.println("1. 아이디 : " + lssMbr.id + "\n" + 
				"2. 비밀번호 : " + lssMbr.pwd + "\n" + 
				"3. 성명 : " + lssMbr.name + "\n" +
				"4. 이메일 :" + lssMbr.email + "\n" +
				"5. 주소(비추) : " + lssMbr.address + "\n");

		
		
	System.out.println("\n--------------------\n");
	
	eom_mbr.id = "eomjh"; // = 은 같다가 아니라 대입해준다 
	eom_mbr.pwd = "qwer1234"; // 
	eom_mbr.name = "엄정화";
	eom_mbr.email = "eomjh@naver.com";
	/* eom_mbr.school = "대졸";  -> 오류 -- school은 member 클래스에서 존재하지 않는 필드이므로 불가함*/
	

	System.out.println("== eom_mbr 이라는 인스턴스(객체)에 저장된 필드 값 출력하기 1번째 == ");
	System.out.println("1. 아이디 : " + eom_mbr.id + "\n" + 
						"2. 비밀번호 : " + eom_mbr.pwd + "\n" + 
						"3. 성명 : " + eom_mbr.name + "\n" +
						"4. 이메일 :" + eom_mbr.email + "\n" +
						"5. 주소(비추) : " + eom_mbr.address + "\n" +
						"6. 주소(권장) : " + Member.address + "\n");
	
	
		
	}

}
