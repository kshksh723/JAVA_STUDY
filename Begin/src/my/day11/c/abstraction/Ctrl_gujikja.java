/*
 * package my.day11.c.abstraction;
 * 
 * public class Ctrl_gujikja {
 * 
 * public static void main(String[] args) { // 여기서 메뉴를 만든다 // == 메인 메뉴를 보여주는 메소드
 * 생성하기 == // void main_menu() { System.out.println("\n=== 메인메뉴====\n"
 * +"1.구직자 회원가입 2. 구직자 모두보기 3. 검색하기 4. 프로그램 종료 \n" );
 * System.out.println("ㅁ 메뉴번호 선택 : "); }// end of void main
 * 
 * 
 * // == 구직자(GUJIKJA 신규 회원 가입시 // Gujikja클래스의 fieldㅢ 요구사항에 모두 맞으면
 * 
 * void register(Scanner sc, Gujikja[] gu_arr) { if (Gujikja.count <
 * gu_arr.length) {
 * 
 * } else { System.out.println(">> 정원 " + gu_arr.length +
 * "명이 꽉차서 구직자 회원가입이 불가합니다.!!<< \n"); } }
 * 
 * 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
 * 
 * }
 * 
 * }
 */
package my.day11.c.abstraction;

import java.util.Scanner;

public class Ctrl_gujikja {

	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
	void main_menu() {
		System.out.println("\n === 메인메뉴 ===\n"
							+ "1. 구직자 회원가입  2. 구직자 모두보기  3.검색하기  4.프로그램종료\n");
		System.out.print("▷ 메뉴번호 선택 : ");
	}	// end of void main_menu()----------------------
	
	// == 구직자(Gujikja) 신규 회원가입시
	//	Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	//	Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
	
	void register(Scanner sc, Gujikja[] gu_arr) {
		
		if(Gujikja.count < gu_arr.length) {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
			System.out.println("신규회원 가입합니다.");
			
		} else {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
			System.out.println(">>> 정원 " + gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
		}
		
	}	// end of void register(Scanner sc, Gujikja[] gu_arr)-------------
	
}
