package my.day04.b.sungjuk;

public class Sungjuk {

	// === field ===
	String hakbun;  // "091234"
	String name;
	byte kor;       // byte -128 ~ 127        0 ~ 100 로 제한
	byte eng;       // byte -128 ~ 127        0 ~ 100 로 제한
	byte math;      // byte -128 ~ 127        0 ~ 100 로 제한
	short age;      // short -32768 ~ 32767  20 ~ 50 로 제한
	
	
	// === method ===
	boolean check_jumsu(byte jumsu) {
		
		if(0 <= jumsu && jumsu <= 100) {
			return true;  // return 을 만나면 해당 메소드가 종료가 된다.
		}
		else {
			System.out.println("[경고] 입력하시는 점수는 0 이상 100 이하 이어야만 합니다.\n"); 
			return false; // return 을 만나면 해당 메소드가 종료가 된다.
		}
	}// end of boolean check_jumsu(byte jumsu)-----------
	
	
	boolean check_age(short age) {
		
		if(20 <= age && age <= 50) {
			return true;  // return 을 만나면 해당 메소드가 종료가 된다.
		}
		else {
			System.out.println("[경고] 입력하시는 나이는 20 이상 50 이하 이어야만 합니다.\n"); 
			return false; // return 을 만나면 해당 메소드가 종료가 된다.
		}
	}// end of boolean check_age(short age)-----------
	
	
	void sungjuk_print() {
		
		float avg = (kor+eng+math)/3.0F;
		
		char hakjum = 'F';
		
		if(avg >= 90) {
			hakjum = 'A';
		}
		else if(avg >= 80) {
			hakjum = 'B';
		}
		else if(avg >= 70) {
			hakjum = 'C';
		}
		else if(avg >= 60) {
			hakjum = 'D';
		}
		
		System.out.println("=== "+name+"님의 성적결과 ===\n"
				         + "1. 학번 : "+hakbun+"\n"
				         + "2. 성명 : "+name+"\n"
				         + "3. 국어 : "+kor+"\n"
				         + "4. 영어 : "+eng+"\n"
				         + "5. 수학 : "+math+"\n"
				         + "6. 총점 : "+(kor+eng+math)+"\n"
				         + "7. 평균 : "+avg+"\n"
				         + "8. 학점 : "+hakjum+"\n"
				         + "9. 나이 : "+age+"\n");
		
		/*
	 		=== 이순신님의 성적결과 ===
			1. 학번 : 091234
			2. 성명 : 이순신
			3. 국어 : 90
			4. 영어 : 80
			5. 수학 : 78
			6. 총점 : 248
			7. 평균 : 82.66666666666667
			8. 학점 : B 
			9. 나이 : 26 
		 */
		
	}// end of void sungjuk_print()--------------------
	
}
