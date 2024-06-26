package my.day07.b.While;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main_primenumber_3 {
	// === 소수(primenumber)란? === 
	   // 소수란? 1과 자기 자신밖에 나누어지지 않는 1 이외의 정수 
	   // 예> 1 부터 10까지의 소수를 나타내면 (2,3,5,7) 
	   //       2%2 ==> 0   2 는 소수
	   //       3%3 ==> 0   3 는 소수
	   //       4%2 ==> 0   4 는 소수가 아님
	   //       5%5 ==> 0   5 는 소수
	   //       6%2 ==> 0   6 는 소수가 아님
	   //       7%7 ==> 0   7     는 소수
	   //       8%2 ==> 0   8 는 소수가 아님
	   //       9%3 ==> 0   9 는 소수가 아님
	   //      10%2 ==> 0  10 는 소수가 아님 
	/*
    >>>>> 실행결과 <<<<<
    
    ▷시작 자연수 : 1엔터
    ▷끝 자연수 : 10엔터 
    1 부터 10 까지의 소수는?
    2,3,5,7
    1부터 10 까지의 소수의 개수? 4개  
    1부터 10 까지의 소수들의 합? 17 
    
    === 프로그램 종료 ===
    
    >>>>> 실행결과 <<<<<
    
    ▷시작 자연수 : 1엔터
    ▷끝 자연수 : 똘똘이엔터 
    >> [경고] 자연수만 입력하세요!! 
    
    ▷시작 자연수 : 1엔터
    ▷끝 자연수 : 20엔터 
    1 부터 20 까지의 소수는?
    2,3,5,7,11,13,17,19 -> 문자열
    1부터 20 까지의 소수의 개수? 8개  
    1부터 20 까지의 소수들의 합? 77 
    
    === 프로그램 종료 ===
          
*/
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		
		do {
			try {
				System.out.println("▷시작 자연수 :");
				int start_no = sc.nextInt(); //1엔터 또는 1.234 엔터 또는 똘똘이 엔터
				// 정수가 되어야한다
				
				System.out.println("▷끝 자연수 :");
				int end_no = sc.nextInt(); //10엔터 또는 2.234 엔터 또는 똘똘이 엔터
				
		
				/*소수란 1과 자기 자신의 수로만 나누었을 대 나머지가 0인 1 이외의 정수를 말한다*/
				String str_result = "";
				int cnt=0, sum=0;
				
				for(int i = start_no; i<=end_no; i++) {
					if(i==1) //1은 소수가 아니므로 소수인지 아닌지 검사할 필요가 없기에 continue;를 한다(건너뛴다)
						continue;
					
					/*
					 나누기를 했을 때 나머지가 얼마가 되는 지 일일이 검사를 한다
					 만약에 i가 2이라면 ==> 2는 소수이다 
					 만약에 i가 3이라면 ==> 3는 소수이다 3%2 != 0 3는 소수이다
					 만약에 i가 4이라면 ==> 4%2 == 0 4%3(검사할 필요가 없다) 4는 소수가 아니다
					 만약에 i가 5이라면 ==> 5%2 != 0 5%3 != 0 5%4 !=0 5는 소수가 아니다
					 만약에 i가 6이라면 ==> 6%2 != 0 6%3(검사할 필요가 없다) 6는 소수가 아니다
					 만약에 i가 7이라면 ==> 7%2 != 0 7%3 != 0 7%4 !=0 7%5 !=0 7%6 !=0 7는 소수이다
					 만약에 i가 8이라면 ==> 8%2 != 0 8%3 (검사할 필요가 없다) 8는 소수이다
					 만약에 i가 9이라면 ==> 9%2 != 0 9%3 ==0 9%4 (검사할 필요가 없다) 9는 소수이다
					 만약에 i가 10이라면 ==> 10%2 != 0 10%3(검사할 필요가 없다) 10는 소수이다
					 */
					boolean isSosu = true;
					 
					
					for(int j = 2; j < i; j++) { //분모에 들어갈 값
						if(i%j == 0) {
							isSosu = false; //이 boolean이 소수가 아니다
							break; // 소수이면 이 for문을 빠져나감
							
						}
					}// end of for
					
					if(isSosu) { //검사 대상인 이 i가 소수이라면 

						cnt++; // 소수의 개수
						
						sum+=i; // 소수들의 누적 합계 SUM = SUM + i
						
						String add = (cnt==1)?"":","; //  첫번째 소수는 소수 앞에 ""을 붙여주고, 두번째 이후부터 나오는 소수는 ","를 붙여준다
						
						str_result +=  add + i;

					
					}
					
				}// end of for --------
					
				System.out.println(start_no + "부터" + end_no+"까지의 소수는?\n"+ str_result);
				System.out.println(start_no + "부터" + end_no+"까지의 소수는?"+cnt+"개");
				System.out.println(start_no + "부터" + end_no+"까지의 소수들의 합?"+ sum);
				
				
				sc.close();
				break;
		}  catch(InputMismatchException e) {
			System.out.println("[경고] 자연수만 입력하세요!!\n");
			// 스캐너에 담겨져있는 것을 없애버려야한다 -> sc.nextLine
			sc.nextLine(); //
		} 
		}
		while(true);
		//end of do~while-------------------
		
		
		System.out.println("\n=== 프로그램 종료 ===");
		
		
		}
}
	


