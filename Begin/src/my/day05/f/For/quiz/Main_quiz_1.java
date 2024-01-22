package my.day05.f.For.quiz;

public class Main_quiz_1 {

	public static void main(String[] args) {
		
		String word = "Abz3김a0#$T";
		//			   0123456789
		
		System.out.println(word + "문자열의 길이(글자수): " + word.length() );
		//Abz3김a0#$T문자열의 길이(글자수): 10
		
		System.out.println(word.charAt(0)); //'A'
		System.out.println(word.charAt(2)); //'z'
		System.out.println(word.charAt(3)); //'3'
		System.out.println(word.charAt(4)); //'김'
		System.out.println(word.charAt(7)); //'#'
		
		System.out.println("\n~~~~~~~~~~~~~~~~\n");
	
		int upper_cnt=0, lower_cnt=0, number_cnt=0, hangul_cnt=0, special_cnt=0;

		for(int i=0; i<word.length(); i++) { //문자 길이만큼 반복한다(word.length) 
			char ch = word.charAt(i);
			
			if(Character.isUpperCase(ch)) { //대문자인지 검사
				upper_cnt++;
			} else if(Character.isLowerCase(ch)) { //소문자인지 검사
				lower_cnt++;
			} else if(Character.isDigit(ch)) {//숫자인지 검사
				number_cnt++;
			} else if('가'<= ch && ch <='힣') { //한글인지 검사
				hangul_cnt++;	
			} else {
				special_cnt++;
			}
	
		}
		
		System.out.println(word+"\n"
				+ "대문자 개수 : " + upper_cnt+"\n"
				+ "소문자 개수 : " + lower_cnt+"\n"
				+ "숫자 개수 : " + number_cnt+"\n"
				+ "한글 개수 : " + hangul_cnt+"\n"
				+ "특수문자 : " + special_cnt+"\n"
				);
	}
}

// d오늘 배운거 중요 for 정리 다시해야함
