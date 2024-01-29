package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyUtil {

	// === 현재시각을 출력해주는 static 메소드를 생성한다. === //
	public static void current_time_print() {
		
		Date now = new Date(); // 현재시각
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("== 현재시각 : " + sdfmt.format(now));
	}// end of public static void current_time_print()------
	
	
	// === 소문자3개와 숫자4개로 이루어진 랜덤한 인증키 만들기 === //
	// 예> asa9040   txa2356 
	public static String certification_key() {
		
		Random rnd = new Random();
		// 처음얼마부터 마지막얼마까지 랜덤한 정수
		// ==> rnd.nextInt(마지막수 - 처음수 + 1) + 처음수;
		
		String certification_key = "";
		
		for(int i=0; i<7; i++) {
			
			if(i<3) { // 랜덤한 소문자를 발생시킨다.
				char ch = (char)(rnd.nextInt('z' - 'a' + 1) + 'a'); 
				certification_key += ch;
			}
			
			else { // 랜덤한 숫자 0 부터 9 까지 발생시킨다.
				int n = rnd.nextInt(9 - 0 + 1) + 0;
				certification_key += n;
			}
		}// end of for----------------------
		
		return certification_key;
	}// end of public static String certification_key()--------


	// 입력받은 문자열에서 공백을 제거해주는 메소드 생성하기 
	public static String space_delete(String input_str) {
		
		String result = null;
		
		if(input_str != null) {
			result = "";
			for(int i=0; i<input_str.length(); i++) {
				char ch = input_str.charAt(i);
				if(ch != ' ') 
					result += ch; 
			}// end of for-------------------------
		}
		
		return result;
	}// end of public static String space_delete(String input_str)------
	
	
	// == 비밀번호가 규칙에 맞는지 틀리는지 알려주는 static 메소드 생성하기 == //
	/*
	   비밀번호 규칙은 비밀번호의 길이는 8글자 이상 15글자 이하이어야 하고,
	   또한 비밀번호는 영문대문자, 영문소문자, 숫자, 특수기호가 혼합되어야만 한다.
	   우리는 규칙에 맞으면 true 를 리턴해주고, 규칙에 틀리면 false 를 리턴하도록 만든다.  
	*/
	public static boolean isCheckPasswd(String passwd) {
		
		if(passwd == null)
			return false;
		
		// 예를들어서
		// 입력받은 passwd 가 C5d# 이라면 dsfsdf2342@#$@kfjsASFSFDF 이라면 
		int length = passwd.length();
		if( length < 8 || length > 15 ) {
			return false;
		}
				
		// 이제부터는 입력받은 passwd 의 글자수가 8글자 이상 15글자 이하인 경우이다.
		// 예를들어서
		// 입력받은 passwd 가 C5d#하하호호 이라면  C5d#하하s@! 이라면
		// 또는 
		// 입력받은 passwd 가 C5dawxab 이라면   c5dawxab# 이라면
		// 입력받은 passwd 가 C5dawxab@ 이라면  c5dawxab#T 이라면

		boolean flag_upper   = false; // 영문대문자 표식을 위한 용도
		boolean flag_lower   = false; // 영문소문자 표식을 위한 용도
		boolean flag_number  = false; // 숫자 표식을 위한 용도
		boolean flag_special = false; // 특수문자 표식을 위한 용도
		
		for(int i=0; i<length; i++) {
			// 암호에 한글이 들어가 있는지 알아본다.
			char ch = passwd.charAt(i);
			if('가' <= ch && ch <= '힣') {
				return false;
			}
			 
			if(Character.isUpperCase(ch)) // 영문대문자일 경우
				flag_upper = true;
			
			else if(Character.isLowerCase(ch)) // 영문소문자일 경우
				flag_lower = true;
			
			else if(Character.isDigit(ch) ) // 숫자일 경우
				flag_number = true;
			
			else // 특수문자일 경우  
				flag_special = true;
			
		}// end of for-------------------------------
		
		return flag_upper && flag_lower && flag_number && flag_special;
		
	}// end of public static boolean isCheckPasswd(String passwd)------
	
	
	
}
