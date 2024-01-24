package my.day07.a.While;

public class Main_while_1 {
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
	
	/* 
	 * 1. 안녕 자바~~~
	 * 2. 안녕 자바~~~
	 * 3. 안녕 자바~~~
	 * 4. 안녕 자바~~~
	 * 5. 안녕 자바~~~
	 */
	
	public static void main(String[] args) {
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
			
		int cnt =5, loop=0;
			while(loop++ <cnt) {
				System.out.println((loop+1)+".안녕 자바~~~"); //반복이 필요할 때마다 증감이 필요함
				loop++;
				//거짓이면 빠져나옴
				
				System.out.println("\n>>프로그램 종료<<<<");
			}
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		
		/*
		 * 1.Hi Eclipse~~
		 * 2.Hi Eclipse~~
		 * 3.Hi Eclipse~~
		 * 4.Hi Eclipse~~
		 * 5.Hi Eclipse~~
		 */
	
		
		cnt =5; loop=0;
		while(loop <cnt) {
			System.out.println(++loop+".Hi Eclipse~~"); //반복이 필요할 때마다 증감이 필요함
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		/*
		 * 1.Hi 이클립스~~
		 * 2.Hi 이클립스~~
		 * 3.Hi 이클립스~~
		 * 4.Hi 이클립스~~
		 * 5.Hi 이클립스~~
		 */
		
		loop = 0;
		while(true) {
			// 무한 반복
			System.out.println(++loop+".Hi 이클립스~~");
			//5찍고 그만 하라 하고 싶을 때
			if(loop == 5)
				break;
		} //end of while~~~
		System.out.println("\n>>프로그램 종료<<<<");
	
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		loop = 0;
		while(!(++loop > 5)) {
			System.out.println(loop+".안녕 오라클");
		}// end of while----
		
		/*
		 * 1.안녕 오라클~~
		 * 2.안녕 오라클~~
		 * 3.안녕 오라클~~
		 * 4.안녕 오라클~~
		 * 5.안녕 오라클~~
		 */
						//증가되어진 값이 6이 되어지면 빠져나온다 -> 5보다 커야함
						//!(++loop > 5)가 false가 되어지면 빠져나온다 -> !빠진 나머지, 반복문을 빠져나갈 조건
		System.out.println("\n>>프로그램 종료<<<<");
	
	
	
	
	
	}
	
}

