package my.day17.b.user_define_exception;

import my.day17.Product_imple;

public class Main {
	
	public static void main(String[] args) {
		Product_imple p1 = new Product_imple();
		p1.setProd_name("새우깡");
		p1.setJango(100);
		
		/*
		 * // == product_imple 클래스에서 toString() 메소드를 오버라이딩(재정의) 하기 전 ===//
		 * System.out.println(p1); // my.day17.Product_imple@6f75e721 <- 여기 페키지에서 만든
		 * 메모리상의 주소 System.out.println(p1.toString()); //
		 * my.day17.Product_imple@6f75e721 // 둘다 똑같음
		 */
		// == product_imple 클래스에서 toString() 메소드를 오버라이딩(재정의) 한 이후 ===//
		//System.out.println(p1);
		
		//1. 제품명 : 새우깡 2. 잔고량 : 100개
		//System.out.println(p1.toString());
		//1. 제품명 : 새우깡 2. 잔고량 : 100개

		
		Product_imple p2 = new Product_imple();
		p2.setProd_name("감자깡");
		p2.setJango(50);
		
		
		
		Product_imple p3 = new Product_imple();
		p3.setProd_name("양파링");
		p3.setJango(150);
		
		Product[] prod_arr = new Product[3];
		prod_arr[0] = p1;
		prod_arr[1] = p2;
		prod_arr[2] = p3;
		
		for (Product prod : prod_arr) {
			System.out.println(prod.toString());
		} // end of for
		
		/*
		 * 1. 제품명 : 새우깡 2. 잔고량 : 100개
		 * 
		 * 1. 제품명 : 감자깡 2. 잔고량 : 50개
		 * 
		 * 1. 제품명 : 양파링 2. 잔고량 : 150개
		 */
		System.out.println("\n ==================== \n");
		try {
			prod_arr[0].order(30);
		} catch (Jango_lack_Exception e) {
			
			e.printStackTrace();
		} // 인셉션을 유발 시킴 
		// 새우깡제품을30개를 주문하셨습니다
		/*
		 * 새우깡 제품을 30 개를 주문하셨습니다
		 *  1. 제품명 : 새우깡
		 *   2. 잔고량 : 70개
		 */
		System.out.println("\n ================================= \n");
		try {
			prod_arr[1].order(80);    //prod_arr[0].order(80);개는 안됌
		} catch (Jango_lack_Exception e) {
		System.out.println("예외메시지 : " + e.getMessage());	
	//		e.printStackTrace();
		// 예외메시지 : >> 잔고량이 주문량보다 적으므로 주문이 불가합니다.<<
		}
		
		System.out.println("\n ================================= \n");
		try {
			prod_arr[2].jumun(200);    //prod_arr[0].order(80);개는 안됌
		} catch (Jango_lack_Exception e) {
		System.out.println("예외메시지 : " + e.getMessage());
		}
		
	} //  Main
}