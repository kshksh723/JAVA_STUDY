package my.day02;

import my.util.MyUtil;

//=== static 메소드에 대해서 알아본다. === //
// 독립적으로 사용
public class Main_03 {

	public static void main(String[] args) {
		System.out.println(">> 여기는 Main_03 클래스 입니다. << ");
		
		// 현재 시각을 출력해본다.
		
		MyUtil util = new MyUtil();
		util.current_time_print();
	}

}
