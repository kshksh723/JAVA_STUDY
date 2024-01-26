package my.day04.c.equals;

public class Main_equals {

	public static void main(String[] args) {
		
		String str1 = "안녕하세요";
		String str2 = "행복하세요";
		
		if(str1 == str2) {
			System.out.println("안녕하세요 와 행복하세요 문자열이 같습니다.");
		}
		
		String str3 = "안녕하세요";
		
		if(str1 == str3) {
			System.out.println("안녕하세요 와 안녕하세요 문자열이 같습니다.");
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String str4 = new String("안녕하세요");
		String str5 = new String("안녕하세요");
		
		System.out.println("str4 : " + str4);  // 안녕하세요
		System.out.println("str5 : " + str5);  // 안녕하세요
		
		if(str4 == str5) {
			System.out.println("안녕하세요 와 안녕하세요 의 메모리 주소가 같습니다.");
		}
		
		if(str4.equals(str5)) {
			System.out.println("str4 의 문자열값과 str5의 문자열값은 같습니다.");
		}
		
		String str6 = "건강하세요";
		String str7 = "건강하세요";
		String str8 = new String("부자되세요");
		String str9 = new String("부자되세요");
		
		if(str6.equals(str7)) {
			System.out.println("str6 과 str7 의 문자열값은 같습니다.");
		}
		
		if(str8.equals(str9)) {
			System.out.println("str8 과 str9 의 문자열값은 같습니다.");
		}

	}

}
