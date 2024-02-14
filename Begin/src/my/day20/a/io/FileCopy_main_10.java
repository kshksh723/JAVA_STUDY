package my.day20.a.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
	※ Data Source (File, 키보드, 원격 컴퓨터)
	: 데이터의 근원
	
	※ Data Destination (파일, 모니터, 프린터, 메모리)
	: 데이터가 최종적으로 도착하는 곳
	
	Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
	             입력스트림          출력스트림
	           InputStream       OutputStream          
	
	  
	  === 파일로 부터 입력받은 것을 읽어서 파일에 출력(기록)하는 예제 ===
	              
	  1. 데이터소스   : 특정 파일로 부터 읽어들임     (노드스트림:FileInputStream) 
	  2. 데이터목적지  : 결과물을 특정 파일에 출력함 (노드스트림: FileOutputStream)
	  
   
	           
	*/
public class FileCopy_main_10 {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print(">> 복사할 원본 파일명(절대 경로)을 입력 => ");
		String src_fileName =  sc.nextLine();
		System.out.print(">> 목적 파일명(절대 경로)을 입력 => ");
		String target_fileName =  sc.nextLine();
		
		System.out.println("소스파일 : " + src_fileName );
		System.out.println("목적파일 : " + target_fileName );
		
		byte[] data_arr = new byte[4096]; // 4096 byte == 4kb
		
		int input_length = 0;
		int totalByte = 0; //byte 수 누적용도
		int cnt = 0;		// while문의 반복회수를 알기 위한 것
		
		try {
			// 소스 File 객체 생성하기
			// String 타입인 src_fileName(파일경로명/파일명)을 실제 File 클래스의 객체로 만든다
			File src_file = new File(src_fileName);
			
			long src_file_size = src_file.length(); // 파일의 크기를 알려준다
			System.out.println(">> 원본 파일 (" + src_fileName + ")  크기 : " + src_file_size+ "byte");
			
			long max_size = 1025*1024*10;	// 10 mb
			
			if(src_file_size > max_size) {
				// 원본 파일의 크기가 10mb 초과한 경우
				System.out.println(">> 원본 파일의 크기가 10mb 초과했으므로 복사할 수 없습니다 << ");
				sc.close();
				return; // main() 메소드 종료 
			} //  if(src_file_size > max_size) { -> 이게 중요함
			
			FileInputStream fist = new FileInputStream(src_file);
			
			File target_file = new File(target_fileName);
			
			FileOutputStream fost = new FileOutputStream(target_file);
		/*	
			while((input_length = fist.read(data_arr)) != -1) {
				fost.write(data_arr, 0, input_length); // 파일에 쓰기
				fost.flush();
				
				totalByte += input_length;
				cnt++; // 반복회수
			} // end of while -- 
			*/
			fist.close();
			fost.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(target_fileName + "에 쓰기 완료!!" + totalByte + "byte 씀");
		System.out.println("반복회수 : " + cnt + "번 반복함");
		
		sc.close();
	} // end of main

}
