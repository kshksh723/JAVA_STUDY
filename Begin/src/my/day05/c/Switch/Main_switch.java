package my.day05.c.Switch;

import java.util.Scanner;

public class Main_switch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		
		String input_str = "";
		
		try {
			System.out.println("■ 첫번째 정수 입력 =>");
			input_str = sc.nextLine();
			int num1 = Integer.parseInt(input_str); //문자열을 정수로 형변환시킨다
														// 10
														// 30000000
														// 똘똘이
			
			
			System.out.println("■ 두번째 정수 입력 =>");	
			input_str = sc.nextLine();
			int num2 = Integer.parseInt(input_str); //문자열을 정수로 형변환시킨다
														// 4
														// 50000000
														// 헤헤헤
			
			System.out.println("■ 사칙연산을 선택하세요(+ - */) : "); // + - * /
															// 우하하하
																// # 
																// 7
			
			 String operator = 	sc.nextLine();
			 String result = " ";
			 double calc_result = 0;
			
/*			 
			 if("+".equals(operator)) {
				 // num1 + num2;
				 calc_result = num1 + num2;
			 }
			 
			 else if ("-".equals(operator)) {
			 
				 calc_result = num1 - num2;
			 }	
			 else if("*".equals(operator)) {	
				 calc_result = num1 * num2;
			 }
			 else if("/".equals(operator)) {
				 calc_result = (double) num1 / num2;
				 
			 }

			 else {
				 // 사칙연산 선택시 + - * / 를 제외한 다른 것을 입력한 경우
				 System.out.println("[경고] 사칙연산 선택은 + - * /만 입력하세요!!");
				 sc.close();
				 return; //종료
			 }
			
		
			 // 10 + 4 = 14
			  */
			 // switch문 열때 ctrl + space key
			 switch (operator) {  //oprator는 비교 대상, operator과 string에 쓴 것과 과 같냐는 뜻
			case "+" : // operator 값이 "+"와 같다라면 
				calc_result = num1 + num2;
				break; // switch문에서 빠져나오는 것, switch문장에서 break;를 만나면 switch(operator) { } 부분을 빠져나가라는 말이다

			case "-": // operator 값이 "-"와 같다라면 
				calc_result = num1 + num2;
				break;
				
			case "/": // operator 값이 "/"와 같다라면 
				calc_result = (double)num1 / num2;
				break;
			
			default:
					 // 사칙연산 선택시 + - * / 를 제외한 다른 것을 입력한 경우
					 System.out.println("[경고] 사칙연산 선택은 + - * /만 입력하세요!!");
					 sc.close();
					 return; //종료(메소드를 종료한다)
					// break; // 도달할 수 없는 코드, 그래서 필요가 없음 쓰면 안됌			
					 } // end of switch(operator) -----
			 
						/*
						 * if("/".equals(operator)) { if(num1 % num2 == 0)
						 * 
						 * result = num1 + operator + num2 + "=" + calc_result; //10/4=2.5 }
						 * 
						 * 
						 * 
						 * else {
						 *  result = num1 + operator + num2 + "=" + calc_result; }
						 */
			 
			 //switch문 문법 기억하기!!!!
			 	switch (operator) {
				case "/":
					 if(num1 % num2 == 0) //switch문에 if 써도 됌
						 
						 result = num1 + operator + num2 + "=" + (int)calc_result; //10/4=2.5 }
						 
						 
						 
						  else {
						   result = num1 + operator + num2 + "=" + calc_result; }
					break; // break가 나오면 switch문을 빠져나옴

				default: //if의 else가 default
					result = num1 + operator + num2 + "=" + (int) calc_result; // (int) calc_result 소수부 절단
					break;
				} // end of switch(operator)
			 
			 //operator 스프링type
			 
			 
			 	System.out.println(result);
			 	
			 	
			 	
			 	/* 
			 	 * 10 + 4 = 14
			 	 * 10 - 4 = 6
			 	 * 10 * 4 = 40
			 	 * 10 / 4 = 2.5
			 	 * */
			 	
			 	

		} catch(NumberFormatException e ) {
			System.out.println(">>> " + input_str + "은 올바른 데이터가 아닙니다");
			
		} catch(ArithmeticException e) {
			System.out.println(">>> 0으로 나눌 수 없습니다 <<< ");
		}
		
		sc.close();
		
	}

}
