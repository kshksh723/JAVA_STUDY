package my.day20.a.io;

import java.io.IOException;

/*
※ Data Source (File, 키보드, 원격 컴퓨터)
: 데이터의 근원

※ Data Destination (파일, 모니터, 프린터, 메모리)
 : 데이터가 최종적으로 도착하는 곳

Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
          입력스트림           출력스트림
          InputStream       OutputStream          


>>>>> System.in :
         부모클래스가 추상클래스 InputStream(기본 입력 스트림) 타입인 것으로서 
         접속점(빨대)이 키보드인 입력 스트림이다.
         
-- Node(접속점)가 키보드인 입력스트림이다.
-- 1 byte 기반 스트림이다.
-- 주요메소드 :
      public int read() throws IOException
      ==> 1byte 씩 데이터를 읽어서
          1byte 씩 반환하고
          입력받은 키보드가 Ctrl+C(윈도우), Ctrl+D(유닉스,리눅스,맥) 이라면 -1 을 반환해주는 메소드이다.
          read() 메소드의 리턴타입은 byte 가 아니라 int 이다.
          데이터 입력의 끝을 나타내는 것으로 -1 을 사용하는데
          Ctrl+C(윈도우), Ctrl+D(유닉스,리눅스,맥)을 사용하면 된다.
                      
          또한 IOException 이 발생할수도 있으므로 반드시 예외처리를 꼭 해주어야 한다.              
          그래서 현재 우리는 윈도우를 사용하고 있으므로 InputStream 작업을 
          강제로 종료하려면  Ctrl+C(윈도우) 하면 된다.


>>>>> System.out :
          부모클래스가 추상클래스인 OutputStream(기본 출력 스트림) 타입인 것으로서
          접속점(빨대)이 콘솔화면(모니터)인 출력 스트림(PrintStream)이다.
               
-- Node(접속점)가 콘솔화면(모니터)인 출력스트림이다.
-- 1byte 기반 스트림이다.
-- 주요 메소드 : println(String str),
              print(String str),
              write(int b)             
*/      
public class InputStream_main_04 {

	public static void main(String[] args) throws IOException {
		
	// 키보드에서 "대한민국서울시홍대입구엔터" 했다라면
		byte[] data_arr = new byte[10];
		
		while() {
			
		}// end of while ~~~
	
	}// end of main

     
}
//      /*  
// * 키보드로 윈도우 + r 해서 cmd 엔터한다
// * 그러면 아래와 같이 명령 프롬프트가 나온다 
// * 
// * C:\Users\ user\Desktop\NCS\workspace_java\Begin\bin>java my.day20.a.io.InputStream_main_02>>영문자르 입력하세요[
//
//종료하려면 x를 입력하세요]<<ksh
//
//===
//키보드로 부터
//데이터 입력받기===화면에 출력[
//println 을
//사용한 input]:107 화면에 출력[
//write 을
//사용한 input]:
//k 화면에 출력[Println을 사용한 (char)input] : k
//
// === 키보드로 부터 데이터 입력받기 ===
//화면에 출력 [println 을 사용한 input] : 115
//화면에 출력 [write 을 사용한 input] : s
//화면에 출력 [Println을 사용한 (char)input] : s
//
// === 키보드로 부터 데이터 입력받기 ===
//화면에 출력 [println 을 사용한 input] : 104
//화면에 출력 [write 을 사용한 input] : h
//화면에 출력 [Println을 사용한 (char)input] : h
//
// === 키보드로 부터 데이터 입력받기 ===
//화면에 출력 [println 을 사용한 input] : 13
//화면에 출력 [write 을 사용한 input] :
//화면에 출력 [Println을 사용한 (char)input] :
//
// === 키보드로 부터 데이터 입력받기 ===
//화면에 출력 [println 을 사용한 input] : 10
//화면에 출력 [write 을 사용한 input] :
//
//화면에 출력 [Println을 사용한 (char)input] :






