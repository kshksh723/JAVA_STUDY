package my.day04.b.sungjuk;

public class Sungjuk {
	
	
	
	String hakbun; // "091234"
	String name;
	byte kor; // byte -128 ~ 127 0 ~ 100로 제한
	byte eng; //  byte -128 ~ 127 0 ~ 100로 제한
	byte math; //  byte -128 ~ 127  0 ~ 100로 제한
	short age; // short -32768 ~ 32767 20 ~ 50로 제한
	byte total;
	
	// ==== method ===
	boolean check_jumsu(byte jumsu) {
		if( 0 <= jumsu && jumsu <= 100 ) {
			return true; // return을 만나면 해당 메소드가 종료가 된다
					
		}
		else {
			System.out.println("[경고] 입력하시는 점수는 0 이상 100 이하 이어야만 합니다.\n");
			return false; // return을 만나면 해당 메소드가 종료가 된다
		}
	}
		
	boolean check_age(short age) {
		if ( 20 <= age &&  age <= 50 ) {
				return true;
		}
			else {
				System.out.println("[경고] 입력하시는 나이는 20이상 50이상으로 입력");
				return false;
			}
			}
	
	

	
			
	void sungjuk_print() {
		float avg = (kor + eng + math) / 3.0F;
		char hakjum = 'F';
		
		if(avg >= 90 ) {
			hakjum = 'A';
		}
		else if ( avg >= 80) {
			hakjum = 'B';
			}
		else if ( avg >= 70) {
			hakjum = 'C';
		}
		else if ( avg >= 60) {
			hakjum = 'D';	
		}
		
		System.out.println("\n=== " + name + "====\n"
							+ "1. 학번 : " + hakbun + "\n" 
							+ "2. 성명 : " + name + "\n"
							+ "3. 국어 : " + kor + "\n"
							+ "4. 영어 : " + eng + "\n"
							+ "5. 수학 : " + math + "\n"
							+ "6. 총점 : " + (kor + eng + math) + "\n"
							+ "7. 평균 : " + avg + "\n"
							+ "8. 학점 : " + hakjum + "\n");
	
	}
	}
	// end of
	
	
		
	
	
	
	
		
	 //end of boolean check_jumsu(byte jumsu)-------
	
	
	
	
	
	
	
	
	
	

