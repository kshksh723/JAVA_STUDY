package my.day08.b.array;

public class Main_array_1 {

	public static void main(String[] args) {
		
		/*
        === 배열(array)이란 ? ===
          동일한 데이터타입을 가지는 여러개의 데이터를 저장할 수 있는 데이터 타입을 말한다.
          그리고 `배열` 또한 `객체`라는 것을 꼭 기억하도록 하자!!!  [암기]
     */
	// 1. == 배열의 선언 ==	
	//	int[] subject; 	//작명이 중요함,대부분 앞에 arr을 붙여씀 ex. arr_subject
		// 또는
		 int[] arr_subject;
		
		// 2. == 선언되어진 배열을 메모리에 크기 할당을 해준다 ==
		arr_subject = new int[7];
		/*
		 --------------------------
		 		|0|1|2|3|4|5|6|
		 --------------------------
		 	위의 숫자는 배열의 인덱스(index)를 가리키는 번호로써 0부터 시작하여 1씩 증가한다
		 */

	} // end of main()-------------------

}
