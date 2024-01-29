package my.day09.a.twodimension;

public class Main_twodimension_array_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] num_arr = new int[4][]; //4행 null 열
		
		/*
		num_arr[0][0] = 10;
		num_arr[0][1] = 20;
		num_arr[0][2] = 30;
		
		num_arr[1][0] = 40;
		num_arr[1][1] = 50;
		num_arr[1][2] = 60;

		*/
		
		num_arr[0] = new int[3]; // 0행은 3열로 설정함.(가로)
		num_arr[1] = new int[2]; // 1행은 2열로 설정함
		num_arr[2] = new int[4]; // 2행은 4열로 설정함
		num_arr[3] = new int[3]; // 3행은 3열로 설정함
		
		num_arr[0][0] = 10;
		num_arr[0][1] = 20;
		num_arr[0][2] = 30;
		
		num_arr[1][0] = 40;
		num_arr[1][1] = 50;
//		num_arr[1][2] = 60; // java.lang.ArrayIndexOutOfBoundsException
		
		num_arr[2][0] = 70;
		num_arr[2][2] = 90;

		for(int i = 0; i<num_arr.length; i++) { //행
			
			for(int j = 0; j<num_arr[i].length; j++) { //열
				String add = (j<num_arr[i].length-1)?",":"\n";
				System.out.printf("%2d%s", num_arr[i][j], add);
			}// end of for
			
		}//end of for
		
		System.out.println("=============================");
		
		int[][] num_arr_2 = {
				{10,20,30},
				{40,50},
				{70,0,90,100},
				{0,0,0}
		};
		
		
		for(int i = 0; i<num_arr_2.length; i++) { //행
			
			for(int j = 0; j<num_arr_2[i].length; j++) { //열
				String add = (j<num_arr_2[i].length-1)?",":"\n";
				System.out.printf("%2d%s", num_arr_2[i][j], add);
			}// end of for
			
		}//end of for

		
		
		
	}// end of main

}
