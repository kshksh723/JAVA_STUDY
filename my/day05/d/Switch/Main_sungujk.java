package my.day05.d.Switch;

import java.util.Scanner;

public class Main_sungujk {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Sungjuk sj = new Sungjuk(); // 기본생성자 
		
		System.out.print("1. 학번 : ");
		sj.hakbun = sc.nextLine(); //  "091234"
		
		System.out.print("2. 성명 : ");
		sj.name = sc.nextLine(); // "이순신"
		
		String input_str = "";
		
		try {
			System.out.print("3. 국어 : ");
			
			// === *** 유효성 검사하기(올바른 데이터인지 틀린 데이터인지 검사하는 것) *** === // 
			input_str = sc.nextLine();
			byte kor = Byte.parseByte(input_str); // 90
	                                              // 101
	                                              // 2343242
	                                              // "똘똘이" 
			if( !sj.check_jumsu(kor) ) {
				sc.close();
				return; // return 을 만나면 해당 메소드가 종료가 된다. 
			}
			else {
			    sj.kor = kor;	
			}
		  
			
			System.out.print("4. 영어 : ");
			input_str = sc.nextLine();
			byte eng = Byte.parseByte(input_str); // 100
			                                      // -20
			                                      // 3943242
	                                              // "호호하하하"
			if( !sj.check_jumsu(eng) ) {
				sc.close();
				return; // return 을 만나면 해당 메소드가 종료가 된다. 
			}
			else {
			    sj.eng = eng;	
			}
			
			
			System.out.print("5. 수학 : ");
			input_str = sc.nextLine();
			byte math = Byte.parseByte(input_str); // 80
			                                       // 128
			                                       // -7843242
	                                               // "헤헤헤"
			if( !sj.check_jumsu(math) ) {
				sc.close();
				return; // return 을 만나면 해당 메소드가 종료가 된다. 
			}
			else {
			    sj.math = math;	
			}
						
			
			// 성적출력하기
			sj.sungjuk_print();
			/*
				=== 이순신님의 성적결과 ===
				1. 학번 : 091234
				2. 성명 : 이순신
				3. 국어 : 90
				4. 영어 : 80
				5. 수학 : 78
				6. 총점 : 248
				7. 평균 : 82.7
				8. 학점 : B 
			*/
			
		} catch(NumberFormatException e) {
		 // e.printStackTrace();
		 //	System.out.println(e.getMessage());
			
			System.out.println(">> 입력하신 " + input_str + " 는 올바른 데이터가 아닙니다.<<");
			System.out.println("점수는 0 ~ 100 까지의 정수만 입력하세요!!"); 
		}
		
		sc.close();
	}

}
