package my.day05.f.For.quiz;

import java.util.Scanner;

public class Main_quiz_3 {

	public static void main(String[] args) {
		
		// ▣ 첫번째 정수 : 1
		// ▣ 두번째 정수 : 10
		
		// >> 결과 <<
		// 1 부터 10 까지의 홀수의 합 : 25
		// 1 부터 10 까지의 짝수의 합 : 30
		
	//	-----------------------------------
		
		// ▣ 첫번째 정수 : 2
		// ▣ 두번째 정수 : 10
		
		// >> 결과 <<
		// 2 부터 10 까지의 홀수의 합 : 24
		// 2 부터 10 까지의 짝수의 합 : 30
		
		
		// ▣ 첫번째 정수 : 2.3453
		// [경고] 올바른 정수를 입력하세요!!
		
		// ▣ 첫번째 정수 : 1
		// ▣ 두번째 정수 : 똘똘이
		// [경고] 올바른 정수를 입력하세요!!
		
		// ▣ 첫번째 정수 : 1
		// ▣ 두번째 정수 : 10
		// >> 결과 <<
		// 1 부터 10 까지의 홀수의 합 : 25
		// 1 부터 10 까지의 짝수의 합 : 30
		
		
		Scanner sc = new Scanner(System.in);

		for(;;) {
			
			try {
				System.out.print("▣ 첫번째 정수 : ");
				int first_no = Integer.parseInt(sc.nextLine()); 
				
				System.out.print("▣ 두번째 정수 : ");
				int second_no = Integer.parseInt(sc.nextLine());
			
				int sum_holsu = 0;  // 홀수의 누적의 합계를 저장하는 변수
				int sum_jjaksu = 0; // 짝수의 누적의 합계를 저장하는 변수
				
				int holsu=0, jjaksu=0; // 2씩 증가하는 용도
				
				//  first_no  => 1   또는  first_no  => 2
				//  second_no => 10  또는  second_no => 10
				if(first_no%2 != 0) {  // 첫번째 입력받은 값이 홀수 이라면 
					holsu = first_no;
					jjaksu = first_no+1; 
				}
				else { // 첫번째 입력받은 값이 짝수 이라면
					holsu = first_no+1;
					jjaksu = first_no;
				}
				 
				// === 홀수 및 짝수의 합을 구한다. === //
				for(;;) {
					
					sum_holsu += holsu;
					sum_jjaksu += jjaksu;
					
					holsu += 2;
					jjaksu += 2;
					
					if( ) {
						break;
					}
					
				}// end of for-------------------
				
				System.out.println(">> 결과 <<");
				System.out.println("1 부터 10 까지의 홀수의 합 : 25 \n" 
					         + "1 부터 10 까지의 짝수의 합 : 30"); 
			
				break;
				
			} catch(NumberFormatException e) {
				System.out.println("[경고] 올바른 정수를 입력하세요!!");
			}
			
		}// end of for----------------------

		System.out.println(">> 프로그램 종료 <<");
		
	}// end of public static void main(String[] args)----------

}
