package my.day16.a.Final;

public class Final_test_parent_1 {
	
	//field
	String id;
	String name;
	
	//field에 final를 붙이면 더 이상 새로운 값으로 할당할 수 없다
	
	final double PI = 3.14592; // 상수변ㅇ수 (대문자로 PI적어야함)
	
	// method
	void greeting() {
		System.out.println("~~~~~~안녕하세요 ~~~~~~~~~");
	}

}
