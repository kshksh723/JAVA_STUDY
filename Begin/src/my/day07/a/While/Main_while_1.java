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
		}
	
}

