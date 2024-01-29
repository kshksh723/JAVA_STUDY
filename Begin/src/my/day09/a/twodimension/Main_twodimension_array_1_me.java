package my.day09.a.twodimension;

public class Main_twodimension_array_1_me {

	public static void main(String[] args) {
		// ==== 1차원 배열 ==== //
		int[] subject_arr = new int[5];
		/*
		 	   		-----------------------
		 값 ==> 		| 0 | 0 | 0 | 0 | 0 |
		 	   		-----------------------
		 index ==>	 0    1   2   3   4
		 	   
		 * */
		subject_arr[0] = 100;
		subject_arr[1] = 90;
		subject_arr[2] = 95;
		subject_arr[3] = 80;
		subject_arr[4] = 88;
		
		// ==== 2차원 배열 ==== //
		int[][] point_arr = new int[4][3]; // 4행 3열
		/*
		 * 값 ==> 
		      		--------------
		      		| 0 | 0 | 0 |
					--------------
				    | 0 | 0 | 0 |
					--------------
				    | 0 | 0 | 0 |
					--------------
					| 0 | 0 | 0 |
					--------------
					
		   index ==> //4행 3열, 4=세로 3=가로
		   		  -----------------------------
		   		  | [0][0] | [0][1] | [0][2] |
		   		  -----------------------------
		   		  | [1][0] | [1][1] | [1][2] |
		   		  -----------------------------
		   		  | [2][0] | [2][1] | [2][2] |
				  -----------------------------
				  | [3][0] | [3][1] | [3][2] |
		*/
		
		point_arr[0][0] = 10;
		point_arr[0][1] = 20;
		point_arr[0][2] = 30;
		
		point_arr[1][0] = 40;
		point_arr[1][1] = 50;
		point_arr[1][2] = 60;
		
		point_arr[2][0] = 70;
		point_arr[2][1] = 80;
		point_arr[2][2] = 90;
		
		point_arr[3][0] = 100;
		point_arr[3][1] = 100;
		point_arr[3][2] = 100;
		
		System.out.println("point_arr.length=" + point_arr.length); // 세로수 (행수)
		// point_arr.length : 4
		// 2차원배열명.length는 행의 길이가 나온다. 
		
		System.out.println("point_arr[0].length=" + point_arr[0].length);
		// point_arr.length : 3
		// 2차원배열명[행의인덱스].length는 그 행에 존재하는 열의 길이(크기)가 나온다.
		
		System.out.println("point_arr[1].length=" + point_arr[1].length);
		System.out.println("point_arr[2].length=" + point_arr[2].length);
		System.out.println("point_arr[3].length=" + point_arr[3].length);
		
		System.out.println("===============================--==\n");
		
		for(int i = 0; i < point_arr.length; i++) { // 행
			for(int j = 0; j < point_arr[i].length; j++) {
				String add = (j < point_arr[i].length -1)?",":"\n";
				System.out.printf("%3d%s", point_arr[i][j],add);
			}
			
		}

		/*
		      10, 20, 30
		      40, 50, 60
		      70, 80, 90
		      100, 100, 100
		 * */
		System.out.println("===== 성적결과 ===== \n");
							// 국어 영어 수학
		int[][] jumsu_arr = { {90,80,70}, // 이순신
							  {80,85,76}, // 엄정화
							  {80,85,76}, // 홍길동
							  {85,70,90}, // 공유
							  {60,80,50}  // 아이유
							};
		
		int[] total_arr = new int[jumsu_arr.length];
		// 각 학생별 총점을 저장시켜 두는 곳이다.
		
		System.out.println("\n-------------------------------");
		System.out.println("국어\t 영어\t 수학\t 총점\t 평균\t 학점\t 등수");
		System.out.println("\n-------------------------------");
		
		
		
		int[] rank = new int[4];
		for(int i = 0; i<jumsu_arr.length; i++) {
			String result = "";
			int sum = 0;
			
			int[] ranknum = new int[4];
			
			for(int j = 0; j<jumsu_arr[i].length; j++) {
				result += jumsu_arr[i][j] + "\t"; 
				sum += jumsu_arr[i][j];
			} 
			rank[i] = sum;
			
			for(int j=0; j<jumsu_arr.length; j++) {		
				for(int k=0; k<jumsu_arr.length; k++) {
					if(rank[i] < rank[k]) {
						ranknum[i]++;
					}
				}
			}
			// end of for --------------------------


			double avg = Math.round((double)sum/jumsu_arr[i].length * 10)/10.0;
			
			char grade = ' ';
			
			
			switch ((int)avg/10) { // byte,short,int,char,String만 들어옴
				case 10:
				case 9:
					grade = 'A';
					break;
					
				case 8:
					grade = 'B';
					break;
					
				case 7:
					grade = 'C';
					break;
					
				case 6:
					grade = 'D';
					break;
					
				default:
					grade = 'F';
					break;
			}// end of switch()---------------
			
			
			
			result += sum + "\t" + avg + "\t" + grade + "\t" + ranknum[i];
			System.out.println(result);
			
		} // end of for--------------------------
		
		/*
		 ---------------------
		 국어 영어 수학 총점 평균 학점 등수
		 ---------------------
		 90  80  70  
		 80  85  76
		 85  70  90
		 60  80  50
		 
		 * */
		
	} // end of main

}
