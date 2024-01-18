package my.day03.c.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main_scanner_3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("정수를 입력하세요 =>");
		
		
	
		String inputStr =  sc.nextLine(); // 103
										// "똘똘이"
		
		try {
			
	
			
			// 문자열 (string)을  byte 타입으로 형변환 시켜주는 메소드가 있다
			/*
			 * short n = (short)(Short.parseShort(inputStr) + 10); // "123" ==> 123+10
			 * 
			 */
			
						/*
						 * // 문자열 (string)을 byte 타입으로 형변환 시켜주는 메소드가 있다
						 * 
						 * byte n = (byte)(Byte.parseByte(inputStr) + 10); // "123" ==> 123+10 // 사칙 연산,
						 * int로 된 걸 byte로 강제 형변환 시킨다
						 */	
			
			// 문자열 (string)을  int 타입으로 형변환 시켜주는 메소드가 있다
			
			int n = Integer.parseInt(inputStr) + 10 ; // "123" ==> 123 + 10
			 
			//Integer.parseInt(inputStr) -> int
			
			
			
			/*
			 * // 문자열 (string)을 long 타입으로 형변환 시켜주는 메소드가 있다 long n = Long.parseLong(inputStr)
			 * + 10; // "30000000000" ==> 30000000000 + 10 // 그냥 숫자는 int // parseLong ->
			 * long type // parseLONG이 없으면 30000000L로 표기해야함
			 * 
			 * System.out.println("입력한 정수" + inputStr + "에 10을 더한 값 : " + n); sc.close();
			 */
		System.out.println("입력한 정수" + inputStr + "에 10을 더한 값 : " + n); 
		} catch(NumberFormatException e) {
			
			System.out.println("[경고]=>>" + inputStr + "는 정수가 아닙니다. 정수만 입력하세요!!<<");
		}
		
		
		
		
		sc.close();
		

		
	}

}