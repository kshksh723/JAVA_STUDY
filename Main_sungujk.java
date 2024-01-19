package my.day04.b.sungjuk;

import java.util.Scanner;

public class Main_sungujk {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Sungjuk sj = new Sungjuk(); // 기본생성자 
		
		System.out.print("1. 학번 : ");
		sj.hakbun = sc.nextLine(); //  "091234"
		
		System.out.print("2. 성명 : ");
		sj.name = sc.nextLine(); // "이순신"
		
		try {
			System.out.print("3. 국어 : ");
			
			// === *** 유효성 검사하기(올바른 데이터인지 틀린 데이터인지 검사하는 것) *** === // 
			byte kor = Byte.parseByte(sc.nextLine()); // 90
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
			
			byte eng = Byte.parseByte(sc.nextLine()); // 100
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
			
			byte math = Byte.parseByte(sc.nextLine()); // 80
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
			
		} catch(NumberFormatException e) {
			
		}
		
		sc.close();
	}

}
