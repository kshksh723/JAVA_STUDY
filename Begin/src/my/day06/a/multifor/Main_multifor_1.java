package my.day06.a.multifor;

public class Main_multifor_1 {

	public static void main(String[] args) {
		// 단일 for문으로 아래와 같이 나오도록 한다
		/*
		 * abcdfg 3행 7열 hijklmn opqrstu
		 */

//		 char ch = 'a';
//		 
//		for(int i=0; i<3; i++) { // 바깥 for문 => 행[암기]
//		
//			for(int j=0; j<7; j++) { // 내부 for문 => 열
//				System.out.print(ch++);
//			}// end of for -----------------
//			/*
//			 * String add = ((i+1)%7 == 0)? "\n":"";//7의 배수
//			 */				
////			System.out.print(ch++ + add); //ch=(char)(ch+1);
//			
//			System.out.print("\n");
//		} // end of for ------------- //abcdefghijklmnopqrstu
//		/*
//		 * //abcdefg hijklmn opqrstu
//		 */
//
//		System.out.print("\n~~~~~~~~~~~~~~\n");
//		/*
//		 [0.0] [0.1] [0.2]
//		 [1.0] [1.1] [1.2]
//		 [2.0] [2.1] [2.2]
//		 [3.0] [3.1] [3.2]
//		 */
//		// 단일 for문을 사용하여 출력해본다
//		// 3번의 4줄 -> 12번을 반복ㅎ나다
//		for (int i = 0; i <12; i++) {
//			int m = 0, n=0;
//			System.out.print("["+m+","+n+"]");
//			if((i+1)%3 == 0) {
//				System.out.println("\n");
//			}
//			/*
//			 * //[0,0][0,0][0,0]
//			 * 
//			 * [0,0][0,0][0,0]
//			 * 
//			 * [0,0][0,0][0,0]
//			 * 
//			 * [0,0][0,0][0,0]
//			 */
//				
//			
//			
//			
//			
//			
//		}//end of for
//		System.out.print("\n~~~~~~~~~~~~~~\n");
//		// 이중 for문을 사용하여 출력해본다
//		/*
//		 [0.0] [0.1] [0.2]	4행 3열
//		 [1.0] [1.1] [1.2]
//		 [2.0] [2.1] [2.2]
//		 [3.0] [3.1] [3.2]
//		 */
//		for(int i = 0; i < 4; i++) { // 4행
//			for (int j=0; j<3; j++) { //3열
//				System.out.print("["+i+","+j+"]");
//			}
//			System.out.println("\n"); //for문을 나간 다음에 줄바꿈
//		}
//		
//		System.out.print("\n~~~~~~~~~~~~~~\n");
//		
//		/*
//		 [0.0] [0.1] [0.2]	4행 3열
//		 [1.0] [1.1] [1.2]
//		 [2.0] [2.1] [2.2] => 스킵
//		 [3.0] [3.1] [3.2]
//		 */
//		for(int i = 0; i < 4; i++) { // 4행
//			
//			if(i==2) continue; //
//
//			/* [0,0][0,1][0,2]
//			 * [1,0][1,1][1,2]
//			 * [3,0][3,1][3,2] -> 스킵 되어짐
//			 */
//			for (int j=0; j<3; j++) { //3열
//				System.out.print("["+i+","+j+"]");
//			}
//			System.out.println("\n"); //for문을 나간 다음에 줄바
//		
//			
//			
//			System.out.print("\n~~~~~~~~~~~~~~\n");
//			
//			for(int i1 = 0; i1 < 4; i1++) { // 4행
//				for (int j=0; j<3; j++) { //3열
//					if(j==1) continue;
//					System.out.print("["+i1+","+j+"]");
//				}
//				System.out.println("\n"); //for문을 나간 다음에 줄바꿈
//			}

		System.out.print("\n~~~~~~~~~~~~~~\n");

		System.out.println("안녕하세요\t내일\t또뵐께요~");
		/*
		 * 501호 502호 503호 505호 301호 302호 303호 305호 201호 202호 203호 205호 101호 102호 103호
		 * 105호
		 */
		/*
		 * for(int floor = 5; floor >= 1; floor--) { // 4행 for (int room = 1; room <=5;
		 * room++) { //4열 if(room == 4) continue; //4 스킵 System.out.print(floor + "0"
		 * +room +"호" +"\t"); } System.out.println(""); }
		 */
		System.out.println("\n>>프로그램 종료<<\n");

		// === 단일 for문을 사용하여 출력해본다 ===
		/*
		 * int m = 0; for (int i =0; i< i++) {
		 * 
		 * i ==> 0~2 이라면 m => 0 i ==> 3~5 이라면 m => 1 i ==> 6~8 이라면 m => 3
		 * 
		 * if(i>0 && i%3==0) { m++; } System.out.println("["+m+"]","+ n++ + "]"); }
		 */

	}// end of main()------------------

}
