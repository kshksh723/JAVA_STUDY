package my.day08.a.spaceDelete;

public class Main_space_delete {

	public static void main(String[] args) {
		
		
		String str = "  korea   seou l 쌍용 강북 교육센터  ";
		System.out.println("시작"+str+"끝");
		//시작  korea   seou l 쌍용 강북 교육센터  끝
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String str_result_for = "";
		
		for(int i=0; i<str.length(); i++) {
			if (str.charAt(i) !=' ') {
				str_result_for += str.charAt(i);
			}
		}
		//시작koreaseoul쌍용강북교육센터끝 -> 공백 없음
		
		System.out.println("시작"+str_result_for+"끝");
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		String str_result_while = "";
		
		//String str_result_for = "  korea   seou l 쌍용 강북 교육센터  "; 
		//			index => 01234							글자길이 -1
		// i가 무엇인지 정해줘야함 i는 지역변수
		int i = 0;
		while(i < str.length()) {
			if(str.charAt(i) != ' ') {
				 str_result_while += str.charAt(i);
			}
			i++;
		}//end of while
	
		System.out.println("시작"+str_result_for+"끝");
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String str_result_dowhile = "";
		System.out.println("시작"+str_result_dowhile+"끝");
		
		i = 0;
		do {
			if(str.charAt(i) != ' ') {
				str_result_dowhile += str.charAt(i);
			}
			i++;
			
		} while (i < str.length());
		
		
		
		
		
		
		
		
		
		
		
	}// end of main

}
