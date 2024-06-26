package my.day07.b.While;

import java.util.Scanner;

public class Main_factorial_2 {

	public static void main(String[] args) {
		
			/// == 입사 문제 === ///
		/*
		 ▣ 알고 싶은 팩토리얼 수 입력 => 5엔터
		 >> 결과 : 5! = 120
		 >> 또 할래?[y/n] => y
		 
		 5! ==> 5*4*3*2*1
		 7! ==> 7*6*5*4*3*2*1
		 팩토리얼은 자연수만 되어진다
		 
		▣ 알고 싶은 팩토리얼 수 입력 => 0엔터
		>> [경고] 자연수만 입력하세요!
		
		▣ 알고 싶은 팩토리얼 수 입력 => -5엔터
		>> [경고] 자연수만 입력하세요!!  
		
		▣ 알고 싶은 팩토리얼 수 입력 => 1.25엔터
		>> [경고] 정수만 입력하세요!!
		
		 ▣ 알고 싶은 팩토리얼 수 입력 => 똘똘이
		>> [경고] 정수만 입력하세요!! 
		
		▣ 알고 싶은 팩토리얼 수 입력 => 4엔터
		>> 결과 : 4! = 24
		>> 또 할래?[Y/N] => s 엔터
		>> [경고] Y 또는 n만 입력하세요
		
		
		>>> 프로그램 종료 <<<
		 
		 */
		
		Scanner sc = new Scanner(System.in);
		outer :
		do {
			try {
				System.out.println( "▣ 알고 싶은 팩토리얼 수 입력 =>");
				int num = Integer.parseInt(sc.nextLine());
				
				if(num <= 0) {  //0을 포함한 자연수
						System.out.println("[경고] 자연수만 입력하세요!");
						continue; //continue를 만나면 밑으로 안떨어짐, do~while(조건식);의 조건식으로 들어간다
					}
					// == 팩토리얼 구하기 시작 ==//
					// 5엔터 => 5*4*3*2*1
				int fac = 1;
				for(int i = num; i>0; i--) {//마지막 1씩 가감
					fac *= i; // fac = fac * i; 와 같다
							  // fac =  1 * 5
							  // fac = 5*4
							 // fac = 5 * 4 * 3
							// fac = 5 * 4 * 3 * 2
							// fac = 5 * 4 * 3 * 2 * 1
							
					
				}// end of for
				
				
				System.out.println(num + "!=" + fac);
					// == 팩토리얼 구하기 끝 ==//
					// 반복문 앞에 lable 넣어야함 ->  outer -> 이 반복문을 빠져 나왔다
			
				
				do {
					System.out.print(">> 또 할래?[y/n] =>");
					String yn = sc.nextLine();
					// 반복해야함
					/*}*/ /*while(true);*/
					// end of do~while------
					
					if("y".equalsIgnoreCase(yn)){
						break;
						
					}
					else if("n".equalsIgnoreCase(yn)) {
						sc.close();
						break outer; // break 만나면 팩토리얼 구하기 끝
									//break outer는 팩토리얼 
					} else {
						System.out.println("[경고] y 또는 n만 입력하세요!!");
					}
				} while(true);
				
			} catch(NumberFormatException e) {
				System.out.println("[경고] 정수만 입력하세요!!");
			}
			
			
			
		} while (true); //무조건 참이기 때문에 올라간다
			//end of do while----
		
		System.out.println("\n==== 프로그램 종료 ===");

				
	} // end of main()--------
}