/*
   어떤 프로그램(예: 쇼핑몰 프로그램)을 생성하려면 먼저 설계도면이 있어야 하는데
   자바(Java)라는 프로그램 개발언어를 사용하여 만드는 설계도면을 "클래스" 라고 부른다.  
   
   ==== **** CLASS(클래스) 의 구조 **** ====
   
   1. 패키지 선언문
      ==> 패키지란 클래스가 저장되어진 디렉토리(폴더) 경로라고 보면 된다.
    
      package 패키지명;  ==> 이때 패키지명은 반드시 소문자로 시작해야 한다.
      
      예>  package my.day01;   ==> 정상
          package My.day01;   ==>  꽝!!
          package 3y.day01;   ==> 꽝!!
          package mY.DAY01;   ==> 정상
          
          
   2. import 문
   
      예> 
      
          
   3. 클래스 선언문
          
   4. 컴파일(compile)하기
      ==> 고급언어를 저급언어로 바꾸는 과정을 컴파일(compile)이라고 부른다. 
      고급언어란? 사람(개발자)이 알아 볼 수 있는 언어
      저급언어란? 기계(컴퓨터)가 알아 들을 수 있는 언어
                Binary Digit(== Bit, 이진수 0,1)
                
      javac 를 사용하여 컴파일을 하면 .java 파일이 .class 로 생성된다.
      .java 파일은 고급언어로 구성된 자바소스 파일이고,
      .class 파일은 기계(컴퓨터)가 알아 들을 수 있는 저급언어이며 실행되어지는 파일이다.           
 */

package my.day01;

// import java.lang.*;  // * 의 뜻은 모든것을 의미한다.
                        // 즉, java.lang 패키지속에 있는 모든 클래스들을 사용하겠다는 말이다.
// 기본적으로 import java.lang.*; 은 생략되어져 있다.

import java.util.Date;

// 패키지명은 반드시 소문자로 시작해야 한다.

public class HelloTest {
// 클래스명의 첫글자는 대문자로 해야 한다.
// 또한 저장시 파일명은 클래스명과 동일하게 해야 하며, 확장자는 .java 이다.
// class body(클래스 본체)는 { 로 시작해서 } 로 끝난다.	

	public static void main(String[] args) {
		
		// 1줄 주석문
		
		/*
		   여러줄 
		   주석문  
		*/
		
		/**
		   문서화 주석
		   ==> 어떤 클래스나 어떤 메소드 생성시 javadoc 를 이용해서 API 문서를 만들때 사용한다.    
		*/
		
		System.out.print("Hello World!!~~~");
		// System.out 은 모니터 라고 보면 된다.
		/*
		   System.out.print("출력하고자 하는 문자열"); 은 
		   모니터에 "출력하고자 하는 문자열" 을 출력해라는 말이다. 
		*/ 
		
		System.out.print("안녕하세요~~^^\n\n\n\n\n");
		// System.out.print("\n"); 은 줄바꿈을 말한다. 
				
		System.out.println("반갑습니다~~ㅎㅎ");
		
		System.out.println("이군요\n");
		
		System.out.println("조금 뒤 자기소개를 할 예정입니다. ㅎㅎㅎ");

		Date now = new Date(); // new Date(); 은 컴퓨터의 현재시각을 알아오는 것이다. 
		System.out.println("컴퓨터에서 알려주는 현재시각 : " + now);
		
	}

}
