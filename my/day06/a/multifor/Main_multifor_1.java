package my.day06.a.multifor;

public class Main_multifor_1 {

	public static void main(String[] args) {
		
		// 단일 for 문으로 아래와 같이 나오도록 한다.
		
		/*
		    abcdefg
		    hijklmn
		    opqrstu 
		*/
		
		char ch = 'a';
		for(int i=0; i<'u'-'a'+1; i++) {
			String add = ((i+1)%7==0)?"\n":"";
			System.out.print(ch++ + add); // ch=(char)(ch+1);
		}// end of for--------------------

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// 이중 for 문으로 아래와 같이 나오도록 한다.
		
		/*
		    abcdefg     3행 7열
		    hijklmn
		    opqrstu 
		*/
		ch = 'a';
		
		for(int i=0; i<3; i++) { // 바깥 for 문  ==> 행 
			
			for(int j=0; j<7; j++) { // 내부 for 문 ==> 열 
				System.out.print(ch++);
			}// end of for---------------
			
			System.out.print("\n");
		}// end of for-------------------
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		/*
		    [0,0][0,1][0,2]
		    [1,0][1,1][1,2]
		    [2,0][2,1][2,2]
		    [3,0][3,1][3,2] 
		*/
		
		// === 단일 for 문을 사용하여 출력해본다. ===
		int m=0, n=0;
		for(int i=0; i<12; i++) {
			/*
			    i ==> 0~2  이라면 m => 0   n => 0 1 2
			    i ==> 3~5  이라면 m => 1   n => 0 1 2
			    i ==> 6~8  이라면 m => 2   n => 0 1 2
			    i ==> 9~11 이라면 m => 3   n => 0 1 2
			*/
			if(i>0 && i%3==0) {
				m++;
				n=0;
			}
			
			System.out.print("["+m+","+ n++ +"]");
			
			if((i+1)%3==0) {
				System.out.print("\n");
			}
		}// end of for--------------------
	
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// 이중 for 문을 사용하여 출력해본다.
		
		/*
		    [0,0][0,1][0,2]   4행 3열
		    [1,0][1,1][1,2]
		    [2,0][2,1][2,2]
		    [3,0][3,1][3,2] 
		*/
		
		for(int i=0; i<4; i++) {     // 4행
			
			for(int j=0; j<3; j++) { // 3열
				System.out.print("["+i+","+j+"]");
			}// end of for------------
			
			System.out.print("\n");
		}// end of for----------------
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		/*
		    [0,0][0,1][0,2]   4행 3열
		    [1,0][1,1][1,2]
		    [2,0][2,1][2,2]  ==> 스킵
		    [3,0][3,1][3,2] 
		*/
		
		for(int i=0; i<4; i++) {     // 4행
			
			if(i==2) continue;
				
			for(int j=0; j<3; j++) { // 3열
				System.out.print("["+i+","+j+"]");
			}// end of for------------
			
			System.out.print("\n");
		}// end of for----------------
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
	/*
	          스킵
	    [0,0][0,1][0,2]   4행 3열
	    [1,0][1,1][1,2]
	    [2,0][2,1][2,2]  ==> 스킵
	    [3,0][3,1][3,2] 
	*/
		for(int i=0; i<4; i++) {     // 4행
			
			for(int j=0; j<3; j++) { // 3열
				if(j==1) continue;
				System.out.print("["+i+","+j+"]");
			}// end of for------------
			
			System.out.print("\n");
		}// end of for----------------
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println("안녕하세요\t내일\t또뵐께요~~\n");
		/*
		                             스킵 
		    501호	502호	503호	504호	505호
		    401호	402호	403호	404호	405호  ==> 스킵
		    301호	302호	303호	304호	305호
		    201호	202호	203호	204호	205호
		    101호	102호	103호	104호	105호 
		*/
		//  5행 5열
		for(int i=5; i>0; i--) {  // 5행
		    
			if(i==4) continue; 
			for(int j=1; j<=5; j++) { // 5열
				if(j==4) continue;
				System.out.print(i+"0"+j+"호\t");
			}// end of for----------
			
			System.out.print("\n");
		}// end of for-----------------------
		
		
		System.out.println("\n>> 프로그램 종료 <<");
		
	}// end of main()------------------------------

}
