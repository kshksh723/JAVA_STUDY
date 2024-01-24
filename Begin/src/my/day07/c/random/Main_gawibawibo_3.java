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
			System.out.print("선택하세요!");
			
			choice_num = Integer.parseInt(sc.nextLine()); 
			
			
		} while (!(choice_num == 4)); //while (condition);
	 // end of main()-----
	
		System.out.println("\n==== 프로그램 종료 ===");
	}
}
