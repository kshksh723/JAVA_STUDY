package my.day05.e.For;

public class Main_for_3_sum_1 {

	public static void main(String[] args) {
		
		int sum=0; // sum은 누적의 합을 저장시키는 변수
		for(int i =1; i<=10; i++) {
			sum += i; //sum = sum + i;와 같은 뜻이다
		}
		//sum = 0+1+2+3+4+5+6+7+8+9+10;
		
		//sum = 0+1; ==> 1번째 반복
		//sum = 1+2; ==> 2번째 반복
		//sum = 1+2+3; ==> 3번째 반복
		//....................
		//sum = 0+1+2+3+4+5+6+7+8+9+10;
		System.out.println("1부터 10까지의 누적의 합계: " + sum);
		// 1부터 10까지의 누적의 합계 : 55
		
		sum = 0; // sum은 누적의 합을 저장시키는 변수
		String str = "";
		for(int i = 1; i<=10; i++) {
			if(i<10)
			str += i+"+"; 	// str = str + i + "+"; (문자열 결합) i가 맨처음엔
				 	 		// str =	"" + 1+"+";
							// 	str	= "1+"+"2+"+";
							// 	str = "1+2+"+3+"+";
							// ..............
							// str = "1+2+3+4+5+6+7+8+9+"+9+"+";
			
			else 
				str+=i;	//기존 str="1+2+3+4+5+6+7+8+9+"
						//i가 10되어지면 i만 더해준다 str="1+2+3+4+5+6+7+8+9+"+10(문자열 결합)
						//즉 str = "1+2+3+4+5+6+7+8+9+10"
			//10번을 반복
			sum += i;
		} // for문을 빠져나오면 //end of for-------------------
		System.out.println(str+"="+sum);
		// 1+2+3+4+5+6+7+8+9+10 = 55
		
	
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		// 삼항연산자
		sum = 0;
		str = ""; //str 초기화
		
		for(int i=1; i<=10; i++) {
			String add = (i<10)?"+":"=";
			str += i+add; //문자열 결함 
			sum += i; // 숫자
	
		} // end of for---------
		
			
			
		System.out.println(str+sum);	
			
		}
		// 조건식 ? 반환값1 : 반환값2
		// 1+2+3+4+5+6+7+8+9+10 = 55

		
	

}
