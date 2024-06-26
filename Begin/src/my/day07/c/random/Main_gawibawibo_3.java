package my.day07.c.random;

import java.util.Random;
import java.util.Scanner;

public class Main_gawibawibo_3 {
	/*
	   ============= 메뉴 ==============
	   1.가위 2.바위 3.보 4.게임종료
	   ================================
	   >> 선택하세요 => 5엔터
	   [경고] 메뉴에 존재하지 않는 번호입니다!!
	   
	   >> 선택하세요 => 똘똘이엔터
	   [경고] 정수로만 입력하세요!!
	   
	   >> 선택하세요 => 2
	   >> 사용자님이 이겼습니다!!^^   이긴경우
	   >> 사용자님이 졌습니다ㅜㅜ     진경우
	   >> 비겼습니다~~             비긴경우
	   
	   ============= 메뉴 ==============
	   1.가위 2.바위 3.보 4.게임종료
	   ================================
	   >> 선택하세요 => 4
	   
	   >> 프로그램 종료 <<
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		int choice_num = 0;
		
		System.out.println("\n>> 프로그램 종료<<\n");
		do {
			
			System.out.println("======메뉴 =====\n"+"1.가위\t2.바위\t3.게임종료\n");
			System.out.print(">> 선택하세요=> ");
			try {
			choice_num = Integer.parseInt(sc.nextLine()); // 5
														// 똘똘이
														// 1~4
				if(!(1 <= choice_num && choice_num <= 4)) {
					System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다!!");
					continue; //  continue를 적어주면 아래로 내려가지 않고 조건에 다시 돌아간다.(5,6,7을 해준다면 sysout으로 가면서 continue로 빠져 나간다, 4번 일 때만 아래로 빠져나간다
				}
				
				if(choice_num !=4) {//메뉴번호를 1~4중에 한개를 선택한 경우
					// 4를 제외한 나머지, 여기는 4번이 포함되면 안된다, 사용자가 1 또는 2 또는 3을 내는 경우 -> 가위바위보에 들어간다 -> else가 아니라 if로 들어가줘야 한다
					/*
					 pc도 1또는 2또는 3중에 하나를 랜덤하게 내야한다		
					 // 처음얼마부터 마지막 얼마까지 랜덤한 정수
					 // ==> rnd.nextInt(마지막- 처음수 +1)+ 창,ㅁ스
					  
					 */
					int pc_num = rnd.nextInt(3-1+1) +1;
					String msg = "";
					// 사용자가 이긴 경우
					if((choice_num == 1 && pc_num == 3)||
							(choice_num == 2 && pc_num == 1)||
							(choice_num == 3 && pc_num == 2)) {
						
						msg=">>사용자님이 이겼습니다!!^^\n";
					}
					// 사용자가 진 경우
					else if((choice_num == 1 && pc_num == 2)||
							(choice_num == 2 && pc_num == 3)||
							(choice_num == 3 && pc_num == 1)) {
						
						msg=">>사용자님이 겼습니다ㅜㅜ\n";
					}
					
			
					// 사용자와 pc와 비긴 경우
					else {
						msg =">> 비겼습니다~~\n";
					}
					System.out.println(msg);
					
				}
			
			} catch(NumberFormatException e) {
				System.out.println("[경고] 정수로만 입력하세요!!\n");
			}
		} while (!(choice_num == 4)); //while (condition); // 4번일경우 여기로 내려온다
	 // end of main()-----
		sc.close();
		System.out.println("\n==== 프로그램 종료 ===");
	}
}
