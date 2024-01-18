package my.day03.c.scanner;

import java.util.Scanner;

public class Main_scanner_4 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		String str_no = ""; // 반드시 초기화해야함  -> try {} 괄호에서 벗어났으므로, 지역변수를 밖으로 빼줌, 지역변수를 초기화 시켜줘야 함.. 
		
		
		
		try {
			/*
			 * System.out.println("1. 첫번째 정수 입력 :");
			 * 
			 * try { String inputStr = sc.nextLine(); 
			 * int n = Short.parseShort(inputStr);
			 * 
			 * 
			 * System.out.println("2. 두번째 정수 입력 :"); 
			 * String inputStr1 = sc.nextLine(); 
			 * int y = Short.parseShort(inputStr1); // 1. 첫번째 정수 입력 : 10 // 2. 두번째 정수 입력 : 20 //
			 * >> 10 + 20 = 30
			 * 
			 * // ---------------------
			 * 
			 * // 1. 첫번째 정수 입력 : 10 // 2. 두번째 정ㅇ수 입력 : 똘똘이 // >> [경고] 똘똘이는 정수가 아닙니다. 모두 숫자를
			 * 입력해주세요!
			 * 
			 * 
			 * 
			 * System.out.println("1. 첫번째 정수 입력 :" + n); System.out.println("2. 두번째 정수 입력 :"
			 * + y); System.out.println(n + y);
			 * 
			 * } catch(NumberFormatException e) {
			 * 
			 * System.out.println("[경고]=>>" + e + "는 정수가 아닙니다. 정수만 입력하세요!!<<"); }
			 * 
			 * sc.close();
			 */
			System.out.println("1. 첫번째 정수 입력 :");
			 str_no = sc.nextLine();
			int first_no = Integer.parseInt(str_no);
			
			
			
			System.out.println("2. 두번째 정수 입력 :");
			str_no = sc.nextLine();
			int second_no = Integer.parseInt(str_no);
			
			System.out.println(">>" + first_no + "+" + second_no + "=" + (first_no + second_no) );
			
		} // 좌,우 괄호를 벗어나면 소멸된다
		catch (NumberFormatException e) {
			System.out.println(">> [경고]" + str_no  +  "는 정수가 아닙니다 ");
		}
	
	
		sc.close();	
	} 
			
}


