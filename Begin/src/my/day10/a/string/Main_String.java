package my.day10.a.string;

import java.text.DecimalFormat;

import my.util.MyUtil;

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
			  
			  System.out.println("\n~~~~~~~~~~\n");
			  
			  String money_1 = "$2,000,000";
			  String money_2 = "$500,000";
			  //$2,500,000
			  long sum_money = MyUtil.delete_character(money_1) + MyUtil.delete_character(money_2);
			  System.out.println(sum_money);
			  // 2500000
			  // 892500000
			  
			   // System.out.println(money_1.substring(0, 0) + money_1.substring(1));//2,000,000
			  // 2000000
			 
			  System.out.println("$"+MyUtil.append_comma(sum_money));
			  //	5				5	글자길이 1글자(3의배수아님)		콤마의 개수는 1/3 		==> 0
			  //	50				50	글자길이 2글자(3의배수아님)		콤마의 개수는 2/3 		==> 0
			  //	500				50	글자길이 3글자(3의배수임)		콤마의 개수는 3/3 		==> 1-1	
			  //	500,000 	500000	글자길이 6글자(3의 배수임)		콤마의 개수는 6/3		==> 2-1
			  //  2,500,000		2500000	글자길이 7글자(3의 배수아님)		콤마의 개수는 7/3		==> 2
			  // 892,500,000  892500000	글자길이 9글자(3의 배수임)		콤마의 개수는 9/3		==> 3-1
			  
			  
			  ////////////////////////////////////////////////
			 System.out.println("\n==========================\n");
			  money_1 = "$2,000,000";
			  money_2 = "$500,000";
			  sum_money = MyUtil.delete_character(money_1) + MyUtil.delete_character(money_2);
			  System.out.println(sum_money);
			  // 2500000
			  
			  DecimalFormat df = new DecimalFormat("#,###"); // 숫자를 3자리마다 콤마(,)를 찍어주는 것이다
			  System.out.println("$"+df.format(sum_money));
			  
			  
			  
			// === 7. "문자열".split("구분자") ====
		      //        "문자열"을 "구분자"로 잘라서 String 타입의 배열로 돌려주는 것이다.  // 쪼개어버리는 것
			  System.out.println("\n 1.===============================\n");
			  String food = "제육볶음,볶음밥,닭가슴살,함박스테이크,소고기덮밥";				//웹에서 많이 쓰임
			  String[] food_arr = food.split(",");
			  // {"제육볶음","볶음밥","닭가슴살","함박스테이크","소고기덮밥"}
			  for (int i = 0; i<food_arr.length; i++) {
				  System.out.println((i+1)+"."+food_arr[i]);
			  }// end of for-
				/*
				 *  1.제육볶음 2.볶음밥 3.닭가슴살 4.함박스테이크 5.소고기덮밥
				 */
			  
			  
			  System.out.println("------------------");
			  
			  for(String fd : food_arr ) {
				  System.out.println(fd);
			  } // end of for------------
				/*
				 * 제육볶음 볶음밥 닭가슴살 함박스테이크 소고기덮밥
				 */
			  
			  
			  System.out.println("\n 2.===============================\n");
			  food = "제육볶음 볶음밥 닭가슴살 함박스테이크 소고기덮밥";	
			  food_arr = food.split("\t");
			  // {"제육볶음","볶음밥","닭가슴살","함박스테이크","소고기덮밥"}
			  for (int i = 0; i<food_arr.length; i++) {
				  System.out.println((i+1)+"."+food_arr[i]);
			  }
			  
			  
			  System.out.println("\n 3.===  split 사용시 구분자로 .이나 | / 등 특수문자를 사용하려고 하는 경우 ===\n");
			  food = "제육볶음.볶음밥.닭가슴살.함박스테이크.소고기덮밥"; //배열 길이가 5가 나와야한다	
			  food_arr = food.split(".");
			  System.out.println("food_arr.length => " + food_arr.length); //못쪼갠다
			  // food_arr.length => 0 : 잘못나온 것
			  // ==> 0 이 나옴. 즉 . 단독만으로는 구분자로 인식을 못함.
		      // split 사용시 구분자로 . | / 등 특수문자를 사용하려고 할 경우에는 구분자로 인식을 못할 경우가 많으므로
		      // 구분자 앞에 \\ 를 붙이거나 구분자를 [] 로 씌워주면 된다.
		      // 즉, \\구분자  이거나  [구분자] 이렇게 해야 한다.
			  
			  food_arr = food.split("\\.");
			  // {"제육볶음","볶음밥","닭가슴살","함박스테이크","소고기덮밥"}
			  // 또는 
			  food_arr = food.split("[.]");
			  // {"제육볶음","볶음밥","닭가슴살","함박스테이크","소고기덮밥"}
			  for (int i = 0; i<food_arr.length; i++) {
				  System.out.println((i+1)+"."+food_arr[i]);
			  }
				/*
				 * 1.제육볶음 2.볶음밥 3.닭가슴살 4.함박스테이크 5.소고기덮밥
				 */
			  System.out.println("\n 4.===  split 사용시 구분자로 .이나 | / 등 특수문자를 사용하려고 하는 경우 ===\n");
			  food = "제육볶음,볶음밥.닭가슴살|함박스테이크,소고기덮밥 라면";// 배열로 해보자
			  food_arr = food.split("\\,|\\.|\\||\\\t");
			  for (int i = 0; i<food_arr.length; i++) {
				  System.out.println((i+1)+"."+food_arr[i]);
			  }
				/*
				 * 1.제육볶음 2.볶음밥 3.닭가슴살 4.함박스테이크 5.소고기덮밥
				 */
			  
			  
			  
			  System.out.println("\n 5.===  split 사용시 구분자로 .이나 | / 등 특수문자를 사용하려고 하는 경우 ===\n"); //4번과 5번 똑같음
			  food = "제육볶음,볶음밥.닭가슴살|함박스테이크,소고기덮밥 라면";// 배열로 해보자
			  food_arr = food.split("[,.|\t]");
			  for (int i = 0; i<food_arr.length; i++) {
				  System.out.println((i+1)+"."+food_arr[i]);
			  }
				/*
				 * 1.제육볶음 2.볶음밥 3.닭가슴살 4.함박스테이크 5.소고기덮밥 6.라면 
				 */
			  
			 System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
			 
			 // === [참고] \를  escape 문자라고 부른다 ===
			 System.out.println("나의 이름은 \"이순신\"입니다.");
			 // 나의 이름은 "이순신"입니다.
			 // 자바에서 쌍따옴표는 역슬래시로 한다
			 
			 System.out.println("C:\\NCS\\workspace_java"); //역슬래시는 두개로 해야 하나로 인식한다
			 // C:\NCS\workspace_java

			// === 8. String.join("합칠구분자", 문자열배열명) ====
		      //        문자열배열을 "합칠구분자"로 합쳐서 String 타입으로 돌려주는 것이다.
			 
			 String[] name_arr = {"한석규","두석규","세석규","네석규","오석규"};
			 String names = String.join("-", name_arr);
			 System.out.println(names);
			 // 한석규-두석규-세석규-네석규-오석규
			 // 	 String names = String.join(".", name_arr); => 한석규.두석규.세석규.네석규.오석규
			 
			 String str_1 = "시작하세요 안녕하세요 건강하세요";
			 str_1 = String.join("", str_1.split("하세요")); // {"시작"," 안녕"," 건강"}
			 
			
			 System.out.println(str_1);
			 // 시작 안녕 건강
			 
			 String bank_book_no = "103-23-523-009"; //bank_book_no : 하나의 문자열
			 String.join("",  bank_book_no.split("[-]")); // {"103","23","523","009"}
			System.out.println(bank_book_no);// 103-23-523-009
			//10323523009
			
			 System.out.println("\n==========================\n");
			  money_1 = "$2,000,000";
			  money_2 = "$500,000";
			
			  
			  
			  money_1 = String.join("", money_1.split("[$]")); // {"2,000,000"}
			  
			  money_1 = String.join("", money_1.split("[,]")); // {"2","000","000"}
			  
			  money_2 = String.join("", money_2.split("[$]")); // {"500,000"}
			  money_2 = String.join("", money_2.split("[,]")); // {"500","000"}
			  sum_money = Integer.parseInt(money_1) + Integer.parseInt(money_2);
			  
			//  money_1.split("[,]"); // {"2","000","000"}
			  // money_1.split("[$]"); // {"2,000,000}
			  
			  
			  // System.out.println(sum_money);
			  // 2500000
			  
			  df = new DecimalFormat("#,###"); // 숫자를 3자리마다 콤마(,)를 찍어주는 것이다
			  System.out.println("$"+df.format(sum_money));
			
			  
			  //split, join 기억하기
			
			// $2,500,000
			  
	}
}
