package my.day05.d.Switch;

public class Sungjuk {

	// === field ===
	String hakbun;  // "091234"
	String name;
	byte kor;       // byte -128 ~ 127        0 ~ 100 로 제한
	byte eng;       // byte -128 ~ 127        0 ~ 100 로 제한
	byte math;      // byte -128 ~ 127        0 ~ 100 로 제한
	
	
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
	
	
	void sungjuk_print() {
		
		float avg = (kor+eng+math)/3.0F;
		
		char hakjum = ' ';
		
	/*	
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
	*/	
	//	byte key = 0;      ==> 가능함.
	//  short key = 0;     ==> 가능함.
	//  int key = 0;       ==> 가능함.
	//	long key = 0;      ==> 불가함.
	//  char key = ' ';    ==> 가능함. 
	//	float key = 0.0F;  ==> 불가함.
	//	double key = 0.0;  ==> 불가함.
	//  String key = "";   ==> 가능함.
		switch ( ((byte)avg)/10 ) { // switch 에 들어올 비교대상값은 정수형중에 long 을 제외한 나머지인 byte, short, int 만 가능하다.!!! 
		                            // switch 에 들어올 비교대상값은 char 타입도 가능하다.
		                            // switch 에 들어올 비교대상값으로 float 및 double 은 불가하다.
		                            // switch 에 들어올 비교대상값은 String 타입도 가능하다.
			case 10:
			case 9:  // ((byte)avg)/10 값이 10 또는 9 이라면 
				     //  90     91     92     99     100
				     // 90/10  91/10  92/10  99/10  100/10
				     //   9      9      9      9      10
				hakjum = 'A';
				break;
			
			case 8:  // ((byte)avg)/10 값이 8 이라면
				hakjum = 'B';
				break;
				
			case 7:  // ((byte)avg)/10 값이 7 이라면
				hakjum = 'C';
				break;
				
			case 6:  // ((byte)avg)/10 값이 6 이라면
				hakjum = 'D';
				break;	
	
			default: // ((byte)avg)/10 값이 10,9,8,7,6 을 제외한 다른값 이라면 
				hakjum = 'F';
				break;
		}// end of switch ( ((byte)avg)/10 )--------------------
		
		String gift = "";
		
		switch (hakjum) {
			case 'A':
				gift += "놀이공원이용권,";  // gift = gift + "놀이공원이용권,";
				                         // gift =  "" + "놀이공원이용권,";
				
			case 'B':
				gift += "치킨,";          // gift = gift + "치킨,"
				
			
			case 'C':
				gift += "피자,";          // gift = gift + "피자,"
				
			
			case 'D':
				gift += "아이스크림";      // gift = gift + "아이스크림"
				break;  // break; 를 만나야만 switch case 문을 빠져나간다. 
				
			default:
				gift = "꿀밤3대";
				break;
		}// end of switch (hakjum)-----------------
		
		
		System.out.println("\n=== "+name+"님의 성적결과 ===\n"
				         + "1. 학번 : "+hakbun+"\n"
				         + "2. 성명 : "+name+"\n"
				         + "3. 국어 : "+kor+"\n"
				         + "4. 영어 : "+eng+"\n"
				         + "5. 수학 : "+math+"\n"
				         + "6. 총점 : "+(kor+eng+math)+"\n"
				         + "7. 평균 : "+ Math.round(avg*10)/10.0 +"\n"
				         + "8. 학점 : "+ hakjum +"\n"
				         + "9. 선물 : "+ gift +"\n"
				         );
		
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
			9. 선물 : 치킨,피자,아이스크림
		 */
		
	}// end of void sungjuk_print()--------------------
	
}
