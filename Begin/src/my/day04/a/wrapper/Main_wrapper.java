package my.day04.a.wrapper;

import java.util.Scanner;

/*
=== *** wrapper 클래스 *** ===

------------------------------------------------
  기본자료형(원시형, primitive)     wrapper 클래스
------------------------------------------------
 byte                           Byte 
 short                          Short
 int                            Integer  
 long                           Long
 char                           Character
 float                          Float
 double                         Double
 boolean                        Boolean 
 ------------------------------------------------
 
  기본자료형(원시형)은 데이터 저장 및 4칙연산 에서만 사용하는 것이고, 
  wrapper 클래스는  데이터 저장 및 4칙연산 뿐만아니라 
  아주 다양한 기능의 메소드가 제공되므로 다방면으로 사용되어진다. 
*/

public class Main_wrapper {

	public static void main(String[] args) {

		char ch = 'a';
		System.out.println("ch=>" + ch); // ch => a
		System.out.println((ch + 1)); // 98
		System.out.println((char) (ch - 32)); // (int)ch-32 97-32 = 65 형변환 -> A

		/* Character chr = new Character('a'); */

		// 캐릭터 char
		// 생성자 Character(char)는 더 이상 사용되지 않으며 제거 대상으로 표시되었습니다.
		Character chr = Character.valueOf('a'); // 권장사항
		System.out.println("chr = > " + chr); // chr => a

		System.out.println("\n ~~~~~~~~~~ \n");

		System.out.println((char) ('a' - 32)); // A
		System.out.println(Character.toUpperCase('a')); // 대문자 return type도 char가 들어옴
		System.out.println(Character.toLowerCase('A')); // 소문자
		System.out.println(Character.toUpperCase(97)); // int가 65
		System.out.println(Character.toUpperCase(65)); // 97

		System.out.println("\n ~~~~~~~~~~~~~~~~~~~~~~ \n");

		char ch2 = 'c';

		if ('A' <= ch2 && ch2 <= 'z') { // 대문자 인지 알아본다.
			System.out.println(ch2 + "는 대문자입니다.");
		}

		else if ('a' <= ch2 && ch2 <= 'z') { // 소문자 인지 알아본다.
			System.out.println(ch2 + "는 소문자입니다.");
		}

		else if ('0' <= ch2 && ch2 <= '9') { // 숫자 인지 알아본다
			System.out.println(ch2 + "는 숫자입니다");
		}

		else {
			System.out.println(ch2 + "는 특수문자입니다.");
		}

		System.out.println("\n ~~~~~~~~~~~~~~~~~~~~~~ \n");

		if (Character.isUpperCase(ch2)) { // 대문자 인지 알아본다.
			System.out.println(ch2 + "는 대문자입니다.");
		}

		else if (Character.isLowerCase(ch2)) { // 소문자 인지 알아본다.
			System.out.println(ch2 + "는 소문자입니다.");
		}

		else if (Character.isDigit(ch2)) { // 숫자 인지 알아본다
			System.out.println(ch2 + "는 숫자입니다");
		}

		else {
			System.out.println(ch2 + "는 특수문자입니다.");
		}

		System.out.println("\n=============\n");

		String str = "Baby";
		// index는 항상 0부터 시작합 0,1,2,3 -> 암기
		char ch1 = str.charAt(0); // 'B' // 글자 한개만 가져옴
		System.out.println(ch1); // B

		ch1 = str.charAt(3); // 'y'
		System.out.println(ch1); // y

		System.out.print(">> 글자 1개만 입력하세요 => ");

		Scanner sc = new Scanner(System.in);

		System.out.println(">> [퀴즈] 한글을 포함한 문장을 입력하세요<<");

		String input_str = sc.nextLine();
		// I am a boy
		// superman
		// 8080 tomcat
		// @gmail.com
		// 안녕하세요 goodmoring

		// 첫글자 I 은(는) 대문자 입니다.
		// 첫글자 s 은(는) 소문자 입니다.
		// 첫글자 8 은(는) 숫자 입니다.
		// 첫글자 @ 은(는) 기타문자 입니다.
		// 첫글자 안 은(는) 한글 입니다.

		char q = input_str.charAt(0);

		if (Character.isUpperCase(q)) { // 대문자 인지 알아본다.
			System.out.println("첫글자" + q + "는 대문자입니다.");
		}

		else if (Character.isDigit(q)) { // 숫자 인지 알아본다
			System.out.println("첫글자" + q +  "는 숫자입니다");
		}
		
		else if ('가' <= q && q <= '힣') {
			//한글인지 알아본다
			System.out.println("첫글자 " + q + "는 공백입니다");
		}
		
		else if (q == ' ' ) { //공백인지 알아본다
			System.out.println( "첫글자"+ q + "기타문자 입니다.");
		}
		
		else {
			System.out.println("첫글자" + q + "특수문자 입니다.");
		}
		  
		 
		
		
		
		/*
		 * String input_str = sc.nextLine(); // return type : string // JAVA
		 */
		char c = input_str.charAt(0); // return type : char

		if (Character.isUpperCase(c)) { // 대문자 인지 알아본다.
			System.out.println(c + "는 대문자입니다.");
		}

		else if (Character.isLowerCase(c)) { // 소문자 인지 알아본다.
			System.out.println(c + "는 소문자입니다.");
		}

		else if (Character.isDigit(c)) { // 숫자 인지 알아본다
			System.out.println(c + "는 숫자입니다");
		}

		else {
			System.out.println(c + "는 특수문자입니다.");
		}

		sc.close();

		// valueof(char c) : static 메소드

		System.out.println("/n ~~~~~~~~~~~~~~ \n");
		
		sc.close();
		
		System.out.println("== 오토박싱, 오토언박싱 에 대해서 알아봅니다.==");   
	      
	      // Boxing(박싱, 포장을 하는것) 이란 ?
	      // ==> 기본자료형(boolean, byte, short, int, long, char, float, double)으로 되어진 변수를 
	      //     객체타입인 Wrapper 클래스(Boolean, Byte, Short, Integer, Long, Character, Float, Double)   
	      //    class  타입의 객체로 만들어주는 것을 말한다.
			int a1 = 10;
			
			// Integer a2 =  new Integer(a1); //Boxing(박싱)
			Integer a2 = Integer.valueOf(a1); //Boxing(박싱)
			System.out.println("a2 => " + a2); //a2 => 10
			
			int b1 = 10;
			// Integer b2 = b1; //Auto Boxing(박싱)
		    // System.out.println("b2 => " + b2);  b2 => 10
			
			// UnBoxing(언박싱, 포장을 푸는것) 이란?   
		      // ==> Wrapper 클래스(Boolean, Byte, Short, Integer, Long, Character, Float, Double)로 
		      //     되어져 있는 객체를 기본자료형(boolean, byte, short, int, long, char, float, double)으로 
		      //     만들어주는 것을 말한다.
			
			Integer a3 = Integer.valueOf(20);
			int a4 = a3.intValue(); //unBOXING(언박싱)
			System.out.println("a4 => " + a4 ); // a4 => 20
	
			 
			int a5 = Integer.valueOf(20); // Auto Unboxing(언박싱)
						
			
			System.out.println("a5 =>" + a5); // a5 => 20
			
			sc.close();
			

	}

}
