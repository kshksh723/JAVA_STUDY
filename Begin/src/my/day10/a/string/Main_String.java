package my.day10.a.string;

public class Main_String {

	public static void main(String[] args) {
		// === 1. "문자열".charAt(int index) === 
	      //        "안녕하세요".charAt(2) ==> '하' 
	        // index => 01234	
	      
	      char ch = "안녕하세요".charAt(2);
	      System.out.println("ch => " + ch);
	      // ch => 하
	      
	      String str = "안녕하세요";
	     //    index =>  01234
	    
	      String result = "";
	      for(int i = str.length()-1; i>=0; i--) {
	    	  result += str.charAt(i);
	      }
		
	      System.out.println(result);
		
		// === 2. "문자열".toCharArray() ===
	      // "안녕하세요".toCharArray()  ==> char 타입의 배열로 만들어준다.
	      // ------------------------
	      // |'안'|'녕'|'하'|'세'|'요'|
	      // ------------------------
	      //   0    1    2   3    4   <== index
		
		char [] ch_arr = str.toCharArray();
		result = "";
		
		for(int i = ch_arr.length-1; i>=0; i--) {
			result += ch_arr[i];
		}
		System.out.println(result);
		
		
		// === 3. "문자열".substring(int 시작인덱스, int 끝인덱스) ===
	      // "안녕하세요".substring(1, 4); 
	      // ==> "안녕하세요" 에서 시작인덱스가 1인 글자("녕") 부터
	      //     "안녕하세요" 에서 끝인덱스 4인 글자("요") 앞(인덱스 3)에까지("세")만 뽑아온다.
		
		str = "안녕하세요".substring(1, 4); //5 앞까지
		System.out.println(str);
		// 녕하세
		
		str = "안녕하세요 오늘도 행복하시고 좋은 하루 되세요~~^^".substring(2,str.length()); //2부터 끝까지는 index번호가 str.length()-> 글자길이 앞까지
		System.out.println(str);
		//substring : 일부분만 지운다
		
		
		// === 4. "문자열".substring(int 시작인덱스) ===
	      // "안녕하세요".substring(2); 
	      // ==> "안녕하세요" 에서 시작인덱스가 2인 글자("하") 부터 끝까지 뽑아온다.
		str = "안녕하세요".substring(2);
		System.out.println(str);
		// 하세요
		
		
		// === 5. "문자열".indexOf("찾을문자열") ====
	      //        "문자열" 에서 최초로 나오는 "찾을문자열"의 인덱스(int)를 알려준다.
		int index = "시작하라 안녕하세요 건강하세요".indexOf("하");
		// 012
		System.out.println(index); //2
		
		index = "시작하라 안녕하세요 건강하세요".indexOf("하세요");
		System.out.println(index); //7
		
		index = "시작하라 안녕하세요 건강하세요".indexOf("A");
		System.out.println(index); // -1 찾고자하는 문자열이 없으면 -1이 나온다
		
		
		// ==== [퀴즈] ==== //
	      System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
	      
	      String[] pathFileNameArr = {"C:/mydocument/resume/나의이력서.hwp",
	                                "D:/mymusic.mp3",
	                                "C:/photo/내얼굴.jpg"};
	      
	      for(int i= 0; i<pathFileNameArr.length; i++) {
	    	  System.out.println(pathFileNameArr[i]);
	      }
		/* "C:/mydocument/resume/나의이력서.hwp",
	                                "D:/mymusic.mp3",
	                                "C:/photo/내얼굴.jpg"
	                                */
	      System.out.println("\n 파일명만 뽑아온 결과물 ==");
	      
	      for(int i =0; i<pathFileNameArr.length; i++) {
	    	  char[] chArr = pathFileNameArr[i].toCharArray();
	    	  
	    	  String str_reverse = "";
	    	  for(int j=chArr.length-1; j>=0; j--) {
	    		  str_reverse += chArr[j];
	    	  }
	      }
	      
	      
	      // system.out.println(str_reverse);
	      /*
	       pwh.서력의 나/emuser/tnemucodym/:C
	       3pm.cisumym/:D
	       gpj.굴얼내/otohp/:C
	       
	       */
	      
	      /*
	       *나의이력서.hwp",
	        mymusic.mp3",
	      	내얼굴.jpg"
	       
	       */
	      // str_reverse에서 처음부터 최초로 "/"가 나오는 앞까지만 추출하면 된다
	      // str_reverse.substring
			/*
			 * if(idx != -1) { str_reverse = }
			 * 
			 * for(int i = 0; i<pathFileNameArr.length; i++) { //행
			 * 
			 * for(int j = 0; j<pathFileNameArr.lengh; j++) { //열 for(int j= chArr)
			 * str_result += chArr[j]; } System.out.printf(str_result);
			 * 
			 * }
			 */
	      
	   // === 6. "문자열".lastIndexOf("찾을문자열") ====
	      //        "문자열" 에서 마지막으로 나오는 "찾을문자열"의 인덱스(int)를 알려준다.
	      index = "시작하라 안녕하세요 건강하세요".lastIndexOf("하");
			// 012
			System.out.println(index); //13
			
			index = "시작하라 안녕하세요 건강하세요".lastIndexOf("하세요");//01234567
			System.out.println(index); //13
			
			index = "시작하라 안녕하세요 건강하세요".lastIndexOf("A");
			System.out.println(index); // -1 찾고자하는 문자열이 없으면 -1이 나온다
			
			// ==== [퀴즈] ==== //	
			  System.out.println("\n 파일명만 뽑아온 결과물 ==");
			/*  String[] pathFileNameArr =
			 *  {"C:/mydocument/resume/나의이력서.hwp",
                      "D:/mymusic.mp3",
                      "C:/photo/내얼굴.jpg"};
                      */
			  for(int i = 0; i<pathFileNameArr.length; i++) {
				  int idx = pathFileNameArr[i].lastIndexOf("/");
				  if(idx != -1) {
					  System.out.println(pathFileNameArr[i].substring(idx+1));
				  // pathFileNameArr[i].substring(idx+1)은 마지막으로 나오는 "/"의 인덱스 그 다음부터 끝까지 뽑아낸다
				  }
				  
			  }
	
	}
}
