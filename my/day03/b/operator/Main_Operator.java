package my.day03.b.operator;

public class Main_Operator {

	public static void main(String[] args) {
		
		//  #### 1.산술연산자 ####
		//       + - * / %(나머지)  
		
		System.out.println("==== 1.산술연산자 + - * / %(나머지) ==== ");  
		
		int n = 10;
		System.out.println("n+3 = " + (n+3)); // n+3 = 13
		System.out.println("n-3 = " + (n-3)); // n-3 = 7
		System.out.println("n*3 = " + (n*3)); // n*3 = 30
		System.out.println("n/3 = " + (n/3)); // n/3 = 3  정수/정수=정수(몫)
		System.out.println("(double)n/3 = " + ((double)n/3)); // (double)n/3 = 3.3333333333333335 
		System.out.println("n%3 = " + (n%3)); // n%3 = 1  10을 3으로 나누었을때의 나머지 1 
		
		
		
	    //  #### 2. 증감연산자 ####
		//       ++  -- 
		
		System.out.println("\n==== 2.증감연산자 ++  --  ==== ");
	
	/*
		int a=7;
		int b=3;
	*/
		// 또는
	    int a=7, b=3;
	    System.out.println("a => " + a);  // a => 7
	    
	 // a = a+1;
	 // 또는
	    a++;
	    System.out.println("a => " + a);  // a => 8
	    
	    ++a;
	    System.out.println("a => " + a);  // a => 9
	    
	    /////////////////////////////////////////////
	    
	    System.out.println("b => " + b);  // b => 3
	    
	 // b = b-1;
	 // 또는
	    b--;
	    System.out.println("b => " + b);  // b => 2
	    
	    --b;
	    System.out.println("b => " + b);  // b => 1
	    
	    
	 // !!!! 꼭 암기하세요 !!!! //
	 // 후위증감연산자(a++; b--;)는 다른 연산을 다 마친 이후에 1씩 증감한다.
	 // 전위증감연산자(++a; --b;)는 맨먼저 1씩 증감을 마친 이후에 다른 연산을 한다.
	    int x=10, y=10;
	    int z=++x; // x=x+1; int z=x; 와 같다.
	    System.out.println("z => " + z);  // z => 11
	    System.out.println("x => " + x);  // x => 11 
	    
	    z=y++;  // z=y; y=y+1; 와 같다
	    System.out.println("z => " + z);  // z => 10 
	    System.out.println("y => " + y);  // y => 11
	    
	    System.out.println("x => " + x++); // System.out.println("x => " + x); x=> 11   x=x+1;
	    System.out.println("x => " + x);   // x => 12
	    
	    System.out.println("y => " + ++y); // y=y+1;  System.out.println("y => " + y);  y => 12 
	    System.out.println("y => " + y);   // y => 12

	    
	    //  #### 3. 논리 부정 연산자 ####
		//          !
	    System.out.println("\n==== 3. 논리 부정 연산자 !  ==== "); 
	    boolean bool = false;
	    System.out.println("bool => " + bool);   // bool => false 
	    System.out.println("!bool => " + !bool); // !bool => true 
	    
	    
	    //  #### 4. 논리 연산자 ####
	    //       &  |  &&  ||
	    System.out.println("\n==== 4. 논리 연산자 &  |  &&  ||  ==== "); 
	    /*
	        수학에서는 T ∧ T ∧ F  ==>  F
	        수학에서는 T ∧ T ∧ T  ==>  T
	        
	        수학에서는 T ∨ T ∨ F  ==>  T
	        수학에서는 T ∨ T ∨ T  ==>  T
	        수학에서는 F ∨ F ∨ F  ==>  F
	    */
	    int c=50, d=60, e=70;
	    
	    bool = (c > d) && (d < e) && (c == e);
	         // false && 스킵 
	    System.out.println("bool => " + bool); // bool => false 
	    
	    bool = (c > d) & (d < e) & (c == e);
             // false  &  true   &   false
        System.out.println("bool => " + bool); // bool => false
	    
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
	    
	    bool = (c > d) || (d < e) || (c == e);
             // false  ||  true   || 스킵
        System.out.println("bool => " + bool); // bool => true
        
        bool = (c > d) | (d < e) | (c == e);
             // false  |  true   |  false
        System.out.println("bool => " + bool); // bool => true
	    
        
	    /*
		     if(조건) {
		               실행해야할 명령;     
		     }
		     ==> 조건이 참일때만 실행해야할 명령; 을 구동한다.
		     ==> 조건이 거짓이라면 실행해야할 명령; 을 구동안한다.
		*/
		int n1=10;
		
		if(n1 < 20) {
		 // true
			System.out.println(">> n1은 20보다 작습니다.");
		}
        
        
		System.out.println("\n~~~~~~ 퀴즈1 ~~~~~~\n");
		int i=1;  
		int j=i++;  // int j=i;  i=i+1;
		            // j=1; i=2;
		
		if( (i > ++j) & (i++ == j) ) { 
		/*
		     j=j+1;  j==>2
		    (i > j)	
		    (2 > 2)
		      false  &  (i == j)   i=i+1;
		      false  &  (2 == 2)
		      false  &   true      
		      ----------------
		           false           i==>3
		 */
			
			i=i+j;
		}
		
		System.out.println("i="+i);  // i=3
		
		i=1;    // i==>1
		j=i++;  // j=i;  i=i+1;
		        // j==>1  i==>2   
		
		if( (i > ++j) && (i++ == j) ) {
		/*
		    j=j+1;  j==>2 
		    (i > j)
		    (2 > 2)  &&  스킵
		     false 	
		 */
			
			i=i+j;
		}
		
		System.out.println("i="+i);  // i=2 
        
		
		System.out.println("\n~~~~~~ 퀴즈2 ~~~~~~\n");
		
		int m1=0;
		n1=1;
		
		if( (m1++ == 0) | (n1++ == 2) ) { 
		/*
		    (m1 == 0)
		      true	  m1=m1+1;   m1==>1
		      
		      true   |   (n1 == 2)  n1=n1+1;
		                    false   n1==>2
		 */
			
			m1=42;  // m1==>42
		}
	    
		System.out.println("m1=>"+m1 + ", n1=>"+n1); // m1=>42 , n1=>2  
		
		m1=0;
		n1=1;
		if( (m1++ == 0) || (n1++ == 2) ) { 
		/*
		    (m1 == 0)
		       true   m1=m1+1;  m1==>1
		       
		       true     ||  스킵
		 */
			m1=42;  // m1==>42
		}
		
		System.out.println("m1=>"+m1 + ", n1=>"+n1); // m1=>42 , n1=>1 
		
		
	    //  #### 5. 비교 연산자 ####
		/*
		    == 같다,  != 같지않다
		    > 크다,  < 작다,  >= 같거나 크다,   <= 같거나 작다     
		*/
		
		
	    //  #### 6. 할당 연산자(연산후 대입 연산자) ####
		//          +=   -=   *=   /=   %= 
		System.out.println("\n==== 6. 할당 연산자(연산후 대입 연산자) ====");
		
		int no = 1;
		no+=3;  // no=no+3; 와 같은 것이다.
		System.out.println("no="+no);  // no=4
		
		no-=2;  // no=no-2; 와 같은 것이다.
		System.out.println("no="+no);  // no=2
		
		no*=5;  // no=no*5; 와 같은 것이다.
		System.out.println("no="+no);  // no=10
		
		no/=4;  // no=no/4; 와 같은 것이다.
		System.out.println("no="+no);  // no=2
		
		no%=3;  // no=no%3; 와 같은 것이다.
		System.out.println("no="+no);  // no=2
		
		
		
	    //  #### 7. 삼항 연산자 ####
		/*
		    변수선언 = (조건식)?값1:값2;
		    
		    변수를 선언하고 나서 값을 부여하고자 할때 사용되어지는데,
		    조건식이 참 이라면 변수에 값1 을 대입해주고,
		    만약에 조건식이 거짓 이라면 변수에 값2 를 대입해준다.
		*/
		
		System.out.println("\n==== 7. 삼항 연산자 ====");
		int num1=50, num2=60;
		int num3=(num1 > num2)?num1:num2;
		
		System.out.println("num3 => " + num3);
		// num3 => 60
	}

}
