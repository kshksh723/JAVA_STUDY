package my.day17.a.exception;

public class Exception_test {


	public static void main(String[] args) {
		System.out.println("\n== 1. \"ArrayIndexOutOfBoundsException\" =="); 
	      
	      // ArrayIndexOutOfBoundsException
	      // ==> 배열의 크기가 오버가 되어지면 발생하는 익셉션
	      
	      String[] subject_arr = {"자바","오라클","JSP"};

	      try {
	      for(int i=0; i <= subject_arr.length; i++) {
	    	  System.out.println(subject_arr[i]);
	      }// end of for
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	    	  System.out.println(">> 배열의 인덱스 범위가 초과되었습니다 <<");
	    	  
	 //   	  e.printStackTrace(); // 어디가 오류인지를 추적해서 알려준다
	  //    System.out.println(e.getMessage()); // Index 3 out of bounds for length 3
//e.getMessage()은 오류메시지를 알려주는 것이다
	      }
	      System.out.println("\n== 2. \"ArithmeticException\" ==");
	      
	      // ArithmeticException 
	      // ==> 분모에 0 이 들어가는 경우에 발생하는 익셉션
	     try { 
	      int num = 10;
	      for(int i = 2; i>=0; i--) {
	    	  System.out.println(num/i);      /// by zero : 0으로 나눌 수 없다
	      }
	     } catch(ArithmeticException e) {
	    	 System.out.println(">> 분모에는 0이 올 수 없습니다");
	  //  	 e.printStackTrace(); // 어디가 오류인지를 추적해서 알려준다
	   	  //    System.out.println(e.getMessage()); // Index 3 out of bounds for length 3
	   //e.getMessage()은 오류메시지를 알려주는 것이다
	     }
	      
	     System.out.println("\n====3. ================"); 
	      int [] num_arr= {10,20,30};
	     try {
	    	
	    	 for(int i=3; i>=0; i--) {
	    		 int val =  num_arr[i]/i;
	    		 System.out.println(val);
	    	 }// end of for
	     } catch(ArrayIndexOutOfBoundsException e) {
	    	  System.out.println(">> 배열의 인덱스 범위가 초과되었습니다 <<");
	     }
	      
	      
	     System.out.println("\n====4. ================"); 
	      
	    
	    for(int i=3; i>=0; i--) {
	    	 
	     try {
	    	 int val =  num_arr[i]/i;
	    		 System.out.println(val);
	     } catch(ArrayIndexOutOfBoundsException e) {
	    	  System.out.println(">> 배열의 인덱스 범위가 초과되었습니다 <<");
	     } catch(ArithmeticException e) {
	    	 System.out.println(">> 분모에는 0이 올 수 없습니다");
	     }
	    }
	    
	    
   System.out.println("\n====5. ================"); 
	      
	    
	    for(int i=3; i>=0; i--) {
	    		 
	     try {
	    	 int val =  num_arr[i]/i;;
	    		 System.out.println(val);
	     } catch(ArrayIndexOutOfBoundsException | ArithmeticException e) {
	    	  System.out.println("에러 메시지 : " + e.getMessage());
	     }
	    }
 System.out.println("\n====6. ================"); 
	      
	    
	    for(int i=3; i>=0; i--) {
	    		 
	     try {
	    	 int val =  num_arr[i]/i;;
	    		 System.out.println(val);
	     } catch (Exception e){
	    	  System.out.println("에러 메시지 : " + e.getMessage());
	     }
	    }
	      
	    System.out.println("\n====7. ================"); 
	      
	 //   String[] str_arr = {"10", "스물", "30"};
	    
	/*  아래의 것은 오류가 발생한다 (근데 이거 잘못적음 ...)앓ㅎㅎㅎㅎㅎㅎㅎㅎㅎ  
	 *  for(int i=3; i>=0; i--) {
    try {
	     int val = Integer.parseInt(str_arr[i])/i; // str_arr[3]/3 str_arr
	    		 System.out.println(val);
    } catch(Exception e) {
    }
	     } catch (ArrayIndexOutOfBoundsExceptione){
	    	  System.out.println("배열의 인덱스 번호 : " + e.getMessage()+ "이 존재하지 않습니다");
	     } catch(ArithmeticException e) {
	    	 System.out.println(">> 분모에는 0이 올 수 없습니다");
	     } catch (Exception e){
	    	  System.out.println("에러 메시지 : " + e.getMessage());
	     }
	    -> 오류남 */
	    /* === [!!! 중요 !!!] ===
	    	      익셉션 처리시 부모클래스의 익셉션이 맨 아래에 나와야 한다.
	    	      왜냐하면 익셉션 처리는 위에서 처리되면서 위의것이 처리가 안되면
	    	      아래로 내려가라는 말인데 부모 클래스의 익셉션이 먼저 나오고 
	    	      자식 클래스의 익셉션이 아래에 나오면 부모클래스 익셉션이 처리를 못한것을
	    	      자식클래스 익셉션이 처리를 해라는 것은 모순이기 때문이다.
	  */  
	 //   } // end of for
	    System.out.println("\n====8. ================"); 
	    
	   // String[] str_arr2 = {"10","스물","30", "40"};
		/*
		 * int cnt = 0; for(int i =0; i<str_arr2.length; i++) { int val =
		 * Integer.parseInt(str_arr2[i])+1; System.out.println(val);
		 * 
		 * System.out.println("[필수]>>" + ++cnt + "번 반복함 <<"); } // end of for
		 * // 11
		 * [필수 ] >> 1번 반복함 <<
		 * java.lang.number
		 * 
		 */
	    
		/*
		 * int cnt = 0; for(int i =0; i<str_arr2.length; i++) { try { int val =
		 * Integer.parseInt(str_arr2[i])+1; System.out.println(val); } finally {
		 * System.out.println("[필수]>>" + ++cnt + "번 반복함 <<"); } }
		 */// end of for	
		
	    
	    
	    // 11
	//  [필수 ] >> 1번 반복함 <<
	//  [필수 ] >> 2번 반복함 <<
	// java.lang.NumberFormatException
	   	
	// finally 은 try 블럭부분에 오류가 발생하지 않아도 finally 블럭부분을 실행하고,
    // try 블럭부분에 오류가 발생하면 finally 블럭부분을 실행한 후에 오류가 발생한다.
    // 즉, finally 부분은 오류가 발생하든지 또는 오류가 발생하지 않든지 관계없이 무조건 실행한다는 것이다. 


	
	  	System.out.println("\n====9. ================"); 
	    
	    String[] str_arr3 = {"10","스물","30"};
	    int count = 0;
	    
	    for(int i =3; i>=0; i--) {
	    	try{
	    		int val = Integer.parseInt(str_arr3[i])/i;
	    	} catch(ArrayIndexOutOfBoundsException e) {
	    		System.out.println("배열의 인덱스번호" + e.getMessage() + "이 존재하지 않습니다");
	    	} catch(ArithmeticException e) {
	    		System.out.println(">> 분모에는 0이 올 수 없습니다");
	    	}catch(Exception e) {
	    		System.out.println("에러 메시지 : " + e.getMessage());
	    	} finally {
	    		//finally은 오류가 발생하든지 관계없이 무조건 실행해야 하는 것들은 finally 부분에 기술해주면 된다
	    	
	    	System.out.println("[필수] >>" + ++count + "번 반복함 <<");
	    	
	    	
	    }
	} // end of main() ------------------ 

	}
} //public class