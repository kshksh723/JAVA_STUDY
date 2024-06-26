package my.day05.e.For;

public class Main_for_2_break_continue {

	public static void main(String[] args) {
		System.out.println(">> 1. break <<");

		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				break; // 반복문에서 break;를 만나면 가장 가까운 반복문을 벗어나는 것
			}
			System.out.println(i + 1);
		} // end of for-----

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		int cnt = 0; // cnt = count 횟수
		for (;;) {
			System.out.println(++cnt + "번째 반복");
			if (cnt == 5)
				break; // 5번만 찍어준다음 빠져나옴, 이게 없으면 오류남 // 반복문에서 break;를 만나면 가장 가까운 반복문을 벗어나는 것
			// 조건은 무조건 적어줘야함
		}

		/*
		 * 1번째 반복 2번째 반복 3번째 반복 4번째 반복 5번째 반복
		 */

		System.out.println(">> 2. countinue <<");
		for (int i = 0; i < 10; i++) {
			if ((i + 1) % 2 == 0) {// 출력하고자 하는 값이 짝수이라면
				continue; // 반복문에서 continue;를 만나면 실행순서가 밑으로 내려가지 않고 가장 가까운 반복문의 증감식으로 이동시켜준다
			}
			System.out.println((i + 1) + " ");
		}
		// i ==> 0 (i+1)==>1
		// i==> 1 (i+1)==>2 continue;
		// i==> 2 (i+1)==>3
		// i==> 3 (i+1)==>4 continue; i++
		// i ==> 4 (i+1)==>5
		// i==> 5 (i+1)==>6 continue; i++
		// i==> 6 (i+1)==>7
		// i==> 7 (i+1)==>8 continue; i++
		// i ==> 8 (i+1)==> 9
		// i ==> 9 (i+1)==> 10
		// i ==> 10 i<10; ==> 거짓
		// 1 3 5 7 9

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (int i = 0; i < 10; i++) {
			/*
			 * if((i+1)%2 == 0) { continue; }
			 */
			/*
			 * if (i < 9) { System.out.print((i + 1) + ","); } else { System.out.print((i +
			 * 1)); }
			 */	
			String str = (i<9)?",":"";
			System.out.print((i + 1)+ str);		

		}
		// 1,2,3,4,5,6,7,8,9,10
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (int i = 0; i < 10; i++) {
		if ((i + 1) % 2 == 0) //출력하고자 하는 값이 짝수라면
			continue; 	//continue;를 만나면 실행순서가 밑으로 
		
		
			String str = (i+1 < 9)? ",": ""; //삼항연산자
			System.out.print((i+1) + str);
		
				}
		
		// 1,3,5,7,9
		// end of for
	
	System.out.println("\n >> 프로그램 종료 <<");

	}// end of public static void main(String[] args)-----
}

/*
 * System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
 * 
 * for(;;) { System.out.println("번째 반복"); // 조건은 무조건 적어줘야함 } - > 무한반복
 */