package my.day04.b.sungjuk;

public class Sungjuk {

	// === field ===
	String hakbun;  // "091234"
	String name;
	byte kor;       // 0 ~ 100 로 제한
	byte eng;       // 0 ~ 100 로 제한
	byte math;      // 0 ~ 100 로 제한
	
	
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
	
	
}
