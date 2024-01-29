package my.day05.e.For;

import java.util.Scanner;

/*
	>> 누적해야할 시작 숫자 => 1
	>> 누적해야할 마지막 숫자 => 10
	[실행결과] 1+2+3+4+5+6+7+8+9+10=55 
	
	>> 누적해야할 시작 숫자 => 3
	>> 누적해야할 마지막 숫자 => 5
	[실행결과] 3+4+5=12
	
	>> 누적해야할 시작 숫자 => 똘똘이
	[경고] 올바른 정수만 입력하세요!!
	>> 누적해야할 시작 숫자 => 3
	>> 누적해야할 마지막 숫자 => 345234543643643543
	[경고] 올바른 정수만 입력하세요!!
	>> 누적해야할 마지막 숫자 => 6
	[실행결과] 3+4+5+6=18
*/

public class Main_for_5_sum_3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;     // 누적의 합을 저장하는 변수로써 0 으로 초기화함.
		String str = ""; // 누적되어질 숫자와 +를 문자열로 결합해서 저장하는 변수로써 "" 으로 초기화함.     

		for(;;) {
			
			try {
				System.out.print(">> 누적해야할 시작 숫자 => ");
				int start_no = Integer.parseInt(sc.nextLine());
				//  start_no => 1     3    3
			    //  start_no => 똘똘이  3000000000
				
				System.out.print(">> 누적해야할 마지막 숫자 => ");
				int end_no = Integer.parseInt(sc.nextLine());
			    //  start_no => 10    5    6
			    //  start_no => 이순신  4000000000
				
				for(int i=start_no; i<=end_no; i++) {
				/*
					if(i<end_no)
						str += i+"+";
					else
					    str += i+"=";
				*/
				//  또는
					String add = (i<end_no)?"+":"=";
					str += i+add;
					
					sum += i;
				}// end of for------------------
				
			} catch(NumberFormatException e) {
				System.out.println("[경고] 올바른 정수만 입력하세요!!"); 
				continue;
			}
			
			sc.close();	
			break; // for문을 빠져나간다.
		}// end of for-------------------------------
		
		System.out.println(str+sum);
		
		System.out.println("\n=== 프로그램 종료 ===");
		
	}// end of public static void main(String[] args)--------

}
