package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyUtil {
	
	// === 현재 시각을 출력해주는  static 메소드를 생성한다. === //
	public static void current_time_print() {
		// static이 없으면 instance 메소드
		
		Date now = new Date(); // 현재 시각
		
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("== 현재시각 : " + sdfmt.format(now));
		
		
	}
	
	// == 소문자 3개와 숫자 4개로 이루어진 랜덤한 인증키 만들기 ==//
			// 예> asa9040	txa2356

	public static String certification_key() {
	
		
		 // 문자열을 retrun
		 Random rnd = new Random();
		 String certification_key="";
		 
		 for(int i=0; i<7; i++) {
				
				if(i<3) { //랜덤한 소문자를 발생시킨다
					char ch = (char)(rnd.nextInt('z' - 'a' +1)+ 'a');
					certification_key += ch;
				}
				
				else { // 랜덤한 숫자 0부터 9가지 발생시킨다
					int n = rnd.nextInt(9 - 0 +1)+ 0;
					certification_key+= n;
				}
			}return certification_key;
			
		
	}		
}	

