package my.day03.c.scanner;

import java.util.Scanner;

public class Main_scanner_1 {

	public static void main(String[] args) {
		/* System.in은 입력장치(키보드)를 말한다 */
		/* System.out은 출력장치(모니터)를 말한다 */

	Scanner sc = new Scanner(System.in);
	// Scanner는 키보드에서 입력된 문자열을 읽어들이는 객체이다

	
	System.out.print("1.문장을 입력하세요 => ");
	String inputStr = sc.nextLine(); //string 타입의 문장을 연결시켜주겠다
	
	/* sc.nextLine(); 은 키보드로부터 입력받은 문장을 읽어들이는 것인데, 엔터(종결신호)까지
	 * 모두 읽어들인 후 스캐너 버퍼에 아무것도 남기지 않는다. */
	
	System.out.println("입력한 문장 => " + inputStr);
	
// 저장된 것을 출력해보라
	
	System.out.println("\n~~~~~~~~~~~~~~\n");
	
	
	System.out.print("2. 정수를 입력하세요 => ");
	/*
	 * int inputNum = sc.nextInt(); // return 타입이 int
	 */
	// byte inputNum1 = sc.nextByte();
	//short inputNum2 = sc.nextShort();
	int inputNum = sc.nextInt();
	//long inputNum4 = sc.nextLong(); 
	 /* sc.nextInt();는 정수(int)를 읽어들이는 것인데 
	  * 종결자가 공백 또는 엔터이다 종결자(공백 또는 엔터) 앞가지의 입력해준 실수를 읽어온다
	  * sc.nextInt();를 사용하면 스캐너(sc) 버퍼에는 종결자(공백 또는 엔터)가 삭제되는 것이 아니라 그대로 남아 있게 된다.
	  * sc.nextDouble();를 사용하면 스캐너(sc) 버퍼에는 종결자(공백 또는 엔터)가 삭제되는 것이 아니라 그대로 남아 있게 된다.
	  * 	 */	
	
	
	sc.nextLine(); // 버퍼에 남아있는 것이 없다 , 스캐너(sc) 버퍼에 남아있는 것을 비우는(제거하는) 효과를 가진다. 
	
	
	System.out.println("입력한 정수 =>" + inputNum);
	
	
	
	System.out.print("3. 실수를 입력하세요 => ");
	// float inputFlt = sc.nextFloat();
	 double inputDb1 = sc.nextDouble();
	
	System.out.println("입력한 실수 =>" + inputDb1);
	
	
	System.out.println("\n~~~~~~~~~~~~~~\n");
	System.out.println("4. 단어를 입력하세요 =>");
	String input_word = sc.next();
	
	/*
	 sc.next(); 은 단어를 읽어들이는 것인데
	 종결자가 공백 또는 엔터이다 종결자(공백 또는 엔터) 앞가지의 입력해준 실수를 읽어온다
	
	 * sc.nextDouble();를 사용하면 스캐너(sc) 버퍼에는 종결자(공백 또는 엔터)가 삭제되는 것이 아니라 그대로 남아 있게 된다.
	 
	 */
	
	System.out.println("입력한 단어 => " + input_word);
	
	
	System.out.println("\n~~~~~~~~~~~~~~\n");
	
	System.out.println("5. 새로운 문장을 입력하세요 => ");
	String input_str2 = sc.nextLine(); //enter까지 입력 됌
	
	System.out.println("입력한 문장 => " + input_str2);
	
	sc.close();
	
	}

}
