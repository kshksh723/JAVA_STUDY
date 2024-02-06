package my.day15.a.overriding;

public class Main {

	public static void main(String[] args) {
	Child cd = new Child(); // 자식 클래스
	
	cd.id = "leess";
	cd.passwd = "Qwer1234$";
	
	
		// 자식클래스인 Child 클래스에서 info() 메소드를 오버라이딩(overriding) 하기 전
	// System.out.println(cd.info());
	//leess , Qwer1234$
	

	// 자식클래스인 Child 클래스에서 info() 메소드를 오버라이딩(overriding) 한 이 후
	System.out.println(cd.info());
	
	/*
	 * 아이디 : leess 비밀번호 : Qwer1234$
	 */


	}

}
