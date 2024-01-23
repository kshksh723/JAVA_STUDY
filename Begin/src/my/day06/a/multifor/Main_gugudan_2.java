package my.day06.a.multifor;

import java.util.Scanner;

/*
>> 몇단볼래? => 8엔터

=== 8단 ===
8*1=8
8*2=16 
8*3=24
8*4=32
8*5=40
8*6=48
8*7=56
8*8=64
8*9=72 

>> 또 하시겠습니까?[Y/N] => y엔터 또는 Y엔터

>> 몇단볼래? => 1.34엔터 또는 똘똘이엔터
>>> 2단부터 9단까지만 가능합니다 <<<

>> 몇단볼래? => 345엔터
>>> 2단부터 9단까지만 가능합니다 <<<

>> 몇단볼래? => 3엔터

=== 3단 ===
3*1=3
3*2=6 
3*3=9
3*4=12
3*5=15
3*6=18
3*7=21
3*8=24
3*9=27
   
>> 또 하시겠습니까?[Y/N] => s엔터 또는 S엔터
>>> Y 또는 N 만 가능합니다!! <<<

>> 또 하시겠습니까?[Y/N] => n엔터 또는 N엔터

== 프로그램종료 ==   
*/   
public class Main_gugudan_2 {

	public static void main(String[] args) {
		 	
		Scanner sc = new Scanner(System.in);
		// 무한 반복, n 값을 하면 빠져 나올 것
		for(;;) {
			
			System.out.print("몇단 볼래? =>");
			
//			sc.nextLine();
			// 단은 정수로 본다
			try {
				int dan = Integer.parseInt(sc.nextLine());
				
				if(2 <= dan && dan <= 9) {
					
				System.out.println("====="+ dan +"단 ====" );
				for(int i = 0; i<9; i++) {
					System.out.println(dan+"*"+(i+1)+"="+ dan*(i+1));
					
				} // end of for 
				// 해당하는 단을 출력하기//
				}
				else {
					System.out.println(">>> 2단부터 9단까지만 가능합니다 <<<");
				}
				break; // 정상일 때만 빠져 나가야 함
		} catch(NumberFormatException e) {
				System.out.println(">>> 2단부터 9단까지만 가능합니다 <<<");
			}
			
				
		}//end of for
		System.out.println("==프로그램 종료 ==");

	}// end of main() ------

}
