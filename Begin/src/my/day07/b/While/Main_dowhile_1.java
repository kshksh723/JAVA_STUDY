package my.day07.b.While;

public class Main_dowhile_1 {

	public static void main(String[] args) {
		/*
	      === do ~ while() ===
	      
	      변수초기화;
	      
	      do{
	          반복해서 실행할 명령문;
	          증감식;
	      } while(조건식);
	      
	      while 문의 경우 조건식이 true 일때만 반복 실행하지만,
	      do ~ while 문의 경우는 조건식이 false 일지라도
	      무조건 do{ } 속에 있는 명령문은 1번은 실행하고서 반복문을 벗어난다.
	          
	      그러므로 do ~ while 문은 무조건 do{ } 속에 있는 명령문을 실행하고서
	      while(조건식) 속의 조건에 따라 참(true)이라면 계속 반복하고,
	      조건이 거짓(false)이라면 중지한다.  
	   */
		/*
		  while(false) { System.out.println("안녕 자바~~"); // Unreachable code }
		 */
		do {
			System.out.println("안녕 자바~~");
		} while (false);
		// 안녕 자바~~
		
		System.out.println("\n~~~~~~~~~~~~\n");
		int cnt=5, loop=0;
		do {
			System.out.println(++loop+".Hello Java~~");
		} while (loop < cnt);// 일단 loop에서 찍어주고 비교 한다 
		// 			1 < 5	참
		//			2 < 5	참
		//			3 < 5	참
		//			4 < 5	참
		//			5 < 5	거짓
		// 그러면 cnt 5까지 도달하는 것 까지 찍어주는 건지
		
		
		
		System.out.println("\n~~~~~~~~~~~~\n");
		/*
		 1. Hello 자바~~~~~
		 2. 
		 3
		 4
		 5
		 
		 */
		 cnt=5; loop=0;
			do {
				System.out.println(++loop+".Hello 자바~~");
			} while (!(loop == 5)); //탈출조건( false가 되어지만 빠져나온다 괄호가 true가 되어져야함), 찍어준 값이 5가 되었니? 그만해 이런 것임
		
			
			
			
			
		System.out.println("\n==== 프로그램 종료 ===");

	}

}
