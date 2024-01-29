package my.day07.a.While;

public class Main_while_1 {

	public static void main(String[] args) {

		/*
		   === while 문 형식 ===
			
			변수의 초기화;
			   
			while(조건식) {
				조건식이 참(true)이라면 반복해서 실행할 명령문을 실행하고,
				조건식이 거짓(false)이라면 while의 { } 이부분을 빠져나간다. 
				
				반복해서 실행할 명령문;
				증감식;
			}
		*/
		
		int cnt=5, loop=0;
		while(loop < cnt) {
			System.out.println((loop+1)+".안녕 자바~~");
			loop++;
		}// end of while------------------
		/*
		    1.안녕 자바~~
		    2.안녕 자바~~
		    3.안녕 자바~~
		    4.안녕 자바~~
		    5.안녕 자바~~ 
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		cnt=5; loop=0;
		while(loop++ < cnt) {
			System.out.println(loop+".Hello Java~~");
		}// end of while------------------
		/*
			1.Hello Java~~
			2.Hello Java~~
			3.Hello Java~~
			4.Hello Java~~
			5.Hello Java~~ 
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		cnt=5; loop=0;
		while(loop < cnt) {
			System.out.println(++loop +".Hi Eclipse~~");
		}// end of while------------------
		/*
			1.Hi Eclipse~~
			2.Hi Eclipse~~
			3.Hi Eclipse~~
			4.Hi Eclipse~~
			5.Hi Eclipse~~ 
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		loop=0;
		while(true) {
			System.out.println(++loop + ".Hi 이클립스~~");
			if(loop == 5)
				break;
		}// end of while-----------------
		
		/*
			1.Hi 이클립스~~
			2.Hi 이클립스~~
			3.Hi 이클립스~~
			4.Hi 이클립스~~
			5.Hi 이클립스~~ 
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		loop=0;
		while( !(++loop > 5) ) {
			System.out.println(loop+".안녕 오라클~~");
		}// end of while----------------
		
		/*
			1.안녕 오라클~~
			2.안녕 오라클~~
			3.안녕 오라클~~
			4.안녕 오라클~~
			5.안녕 오라클~~
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		loop=0;
		while(true) {
			
			if(++loop > 10)  // 탈출조건
				break;
			
			if(loop%2 == 0)
				continue;  // 아래로 내려가지 않고 while()의 괄호()속의 조건식으로 이동한다. 
			
			System.out.println(loop+".Hi Oracle~~");
		}// end of while----------------------------
		/*
			1.Hi Oracle~~
			3.Hi Oracle~~
			5.Hi Oracle~~
			7.Hi Oracle~~
			9.Hi Oracle~~ 
		*/
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println("\n=== 5단 ===");
		
		loop=0;
		while(++loop < 10) {
			System.out.println("5*"+loop+"="+(5*loop));
		}
		/*
		    === 5단 ===
		    5*1=5
		    5*2=10
		    5*3=15
		    5*4=20
		    5*5=25
		    5*6=30
		    5*7=35
		    5*8=40
		    5*9=45
		 */
		
		System.out.println("\n=== 6단 ===");
		
		loop=0;
		while(true) {
			if(++loop > 9)
				break;
			System.out.println("6*"+loop+"="+(6*loop));
		}
		/*
			=== 6단 ===
			6*1=6
			6*2=12
			6*3=18
			6*4=24
			6*5=30
			6*6=36
			6*7=42
			6*8=48
			6*9=54 
		 */
		
		System.out.println("\n=== 7단 ===");
		
		loop=0;
		while(!(++loop > 9)) { // while문의 탈출조건을 쓴다.
			System.out.println("7*"+loop+"="+(7*loop));
		}
		/*
			=== 7단 ===
			7*1=7
			7*2=14
			7*3=21
			7*4=28
			7*5=35
			7*6=42
			7*7=49
			7*8=56
			7*9=63  		
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
	    System.out.printf("%35s\n","==== 구구단 ====");
	    
	    // 9행   8열
	    // jul  dan
	    int jul=0, dan=1;
	    
	    while(!(++jul > 9)) { // 9행
	    	
	    	while(!(++dan > 9)) { // 8열 
	    		String add = (dan<9)?"\t":"\n";
	    		System.out.print(dan+"*"+jul+"="+(dan*jul)+add);
	    	}// end of while--------------------
	    	
	    	// 위의 while 문 빠져나올때 dan 의 값은 10 이다.
	    	// 그러므로 아래와 같이 dan 의 값을 1 로 초기화 해주어야 한다.
	    	dan=1;
	    	
	    }// end of while-------------------------
/*	    
	                      ==== 구구단 ====
	2*1=2	3*1=3	4*1=4	5*1=5	6*1=6	7*1=7	8*1=8	9*1=9
	2*2=4	3*2=6	4*2=8	5*2=10	6*2=12	7*2=14	8*2=16	9*2=18
	2*3=6	3*3=9	4*3=12	5*3=15	6*3=18	7*3=21	8*3=24	9*3=27
	2*4=8	3*4=12	4*4=16	5*4=20	6*4=24	7*4=28	8*4=32	9*4=36
	2*5=10	3*5=15	4*5=20	5*5=25	6*5=30	7*5=35	8*5=40	9*5=45
	2*6=12	3*6=18	4*6=24	5*6=30	6*6=36	7*6=42	8*6=48	9*6=54
	2*7=14	3*7=21	4*7=28	5*7=35	6*7=42	7*7=49	8*7=56	9*7=63
	2*8=16	3*8=24	4*8=32	5*8=40	6*8=48	7*8=56	8*8=64	9*8=72
	2*9=18	3*9=27	4*9=36	5*9=45	6*9=54	7*9=63	8*9=72	9*9=81	    
*/		
		System.out.println("\n>>> 프로그램 종료 <<<");

	}// end of main()----------------------------

}
