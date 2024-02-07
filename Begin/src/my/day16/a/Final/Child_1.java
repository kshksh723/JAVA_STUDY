package my.day16.a.Final;

public class Child_1 extends Final_test_parent_1 {
	int age;
	
	void my_test() {
		System.out.println(">>연습입니다<<");
	}

	// 메소드 오버라이딩
	@Override
	void greeting() {
		System.out.println("\n==== Hi Hello===\n");
	}
	
	// final 메소드는 메소드의 오버라이딩(재정의)을 할 수 없으므로 오류이다
	/*
	 * @Override void rule() 
	 * { System.out.println("\n==== Honesty===\n"); }
	 */
}
