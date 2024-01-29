package my.day08.b.array;

import java.util.Random;

public class Main_lotto_2 {

	public static void main(String[] args) {
		
	/*
	    int ball ==>   1,   2,   3,   4,   5,   6, ....  43,  44,  45 
	             ==> 101, 102, 103, 104, 105, 106, .... 143, 144, 145
	             
	    int[] arr_ball = new int[45];
	    -------------------------------------------------
	    | 0 | 1 | 2 | 3 | 4 | 5 | ...... | 42 | 43 | 44 | 
	    -------------------------------------------------           	
	 */
		int[] arr_ball = new int[45];
		int[] arr_temp = new int[5];  // 기존에 뽑았던 방번호를 기억시켜두는 저장소 
	 /*
	           arr_temp
	           --------------------------
	   값 ==>  | -1 | -1 | -1 | -1 | -1 |
	           --------------------------
	   index     0   1   2   3   4
	   
	           ------------------------
	   값 ==>  | 5 | 0 | 12 | 7 | 34 |
	           ------------------------   	
	  */
		
		// 데이터값 초기화 하기
		for(int i=0; i<arr_ball.length; i++) {
			arr_ball[i] = i+1; // 배열의 방마다 데이터값 입력하기
			                   // 데이터값은 1 ~ 45
		}// end of for----------------------
		
		// 기존에 뽑았던 방번호를 기억시켜두는 저장소 초기화 하기 
		for(int i=0; i<arr_temp.length; i++) {
			arr_temp[i] = -1;
		}// end of for----------------------
		/*
	      공을 꺼내어 오면 공의 방번호(index 번호)를 기억하는 곳으로 사용되는데
	      꺼내온 공의 방번호(index 번호)가 0(실제값은 1번볼)이 들어올 수 있으므로
	      데이터값은  방번호(index 번호)로 사용하지 않는 -1 로 모두(5개) 초기화 한다.
	    */ 
		
		
		// 공의 방번호(배열의 index)를 꺼내는 작업을 6번 반복해야 한다.
		// 즉, 0번 방 부터 44번 방 까지 랜덤하게 6번 뽑아야 한다.
		// 즉, 0 ~ 44 까지 난수를 발생.
		
		Random rnd = new Random();
		String result = "";
		
		outer:
		for(int i=0; i<6; i++) {
			// 처음얼마부터 마지막얼마까지 랜덤한 정수
			// ==> rnd.nextInt(마지막수 - 처음수 + 1) + 처음수;
			
			int idx = rnd.nextInt(44 - 0 + 1) + 0; // 0 ~ 44 
			//  5
		    //  5  0  
		    //  5  0  12  
		    //  5  0  12  
		    //  5  0  12  7  
		    //  5  0  12  7  34
		    //  5  0  12  7  34  20 
			
			for(int j=0; j<arr_temp.length; j++) {
				if(idx == arr_temp[j]) {
					// 새로이 뽑아온 방번호(idx)가 기존에 뽑은것(arr_temp[j])과 같다라면 다시 새로이 뽑아야 한다. 
					// 즉, 0 ~ 44 중에 새로이 또 뽑아야 한다는 말이다. 
				    i--;
					continue outer;
				}
			}// end of for----------------------
			
			if(i<5) {
				arr_temp[i] = idx;
				// 뽑은 방번호(idx)를 저장시켜둔다.
			}
			
			String add = (i<5)?",":"";
			result += arr_ball[idx]+add;
		}// end of for----------------------
		
		System.out.println("\n로또 1등 당첨번호 : " + result);

	}// end of main()--------------------------

}
