/////*
//// * package my.day11.c.abstraction;
//// * 
//// * import java.util.Scanner;
//// * 
//// * public class Main_gujikja {
//// * 
//// * Gujikja[] gu_arr = new Gujikja[5]; //////////////////////////////// Gujikja
//// * gu1 = new Gujikja(); gu1.userid = "eomjh"; gu1.passwd = "qWer1234$"; gu1.name
//// * = "엄정화"; gu1.jubun = "9610202"; gu_arr[Gujikja.count++] = gu1;
//// * //Gujikja.count -> 0인데 증감
//// * 
//// * Gujikja gu2 = new Gujikja(); gu2.userid = "leess"; gu2.passwd = "abCd12345$";
//// * gu2.name = "이순신"; gu2.jubun = "9209201"; gu_arr[Gujikja.count++] = gu2;
//// * 
//// * Gujikja gu3 = new Gujikja(); gu3.userid = "chaew"; gu3.passwd = "aSdf1234$";
//// * gu3.name = "차은우"; gu3.jubun = "0106203"; gu_arr[Gujikja.count++] = gu3;
//// * 
//// * //////////////////////////////////////
//// * 
//// * Scanner sc = new Scanner(System.in); // Scanner는 메모리 참조 변수 Ctrl_gujikja ctrl
//// * = new Ctrl_gujikja();
//// * 
//// * 
//// * String str_menuno = ""; do { ctrl.main_menu(); str_menuno = sc.nextLine();
//// * 
//// * 
//// * switch(str_menuno) { case "1": //구직자 회원가입
//// * 
//// * }
//// * 
//// * }while(!("4".equals(str_menuno))); // end of do ~ while
//// * 
//// * 
//// * sc.close(); System.out.println("\n >> 프로그램 종료 <<< ");
//// * 
//// * 
//// * 
//// * 
//// * } // end of main()
//// * 
//// * package my.day11.c.abstraction;
//// * 
//// * import java.util.Scanner;
//// * 
//// * public class Main_gujikja {
//// * 
//// * public static void main(String[] args) {
//// * 
//// * Gujikja[] gu_arr = new Gujikja[5]; // Gujikja 클래스를 쓸 수 있음 5개를 만듬
//// * 
//// * /////////////////////////////////////////////////////////// Gujikja gu1 = new
//// * Gujikja(); // 기본 생성자 gu1.userid = "eomjh"; gu1.passwd = "qWer1234$"; gu1.name
//// * = "엄정화"; gu1.jubun = "9610202"; gu_arr[Gujikja.count++] = gu1; // 구직자 클래스에 가서
//// * static count 선언하기, Gujikja.count= 0
//// * 
//// * Gujikja gu2 = new Gujikja(); gu2.userid = "leess"; gu2.passwd = "abCd12345$";
//// * gu2.name = "이순신"; gu2.jubun = "9209201"; gu_arr[Gujikja.count++] = gu2;
//// * 
//// * Gujikja gu3 = new Gujikja(); gu3.userid = "chaew"; gu3.passwd = "aSdf1234$";
//// * gu3.name = "차은우"; gu3.jubun = "0106203"; gu_arr[Gujikja.count++] = gu3; //
//// * 3명으로 나옴, 3명이 저장되어있다! // Gujikja.count보면 몇개 까지 줬는지 알 수 있다
//// * 
//// * Gujikja gu4 = new Gujikja(); gu4.userid = "chaew"; gu4.passwd = "aSdf1234$";
//// * gu4.name = "차은우"; gu4.jubun = "0106203"; gu_arr[Gujikja.count++] = gu4;
//// * Gujikja gu5 = new Gujikja(); gu5.userid = "chaew"; gu5.passwd = "aSdf1234$";
//// * gu5.name = "차은우"; gu5.jubun = "0106203"; gu_arr[Gujikja.count++] = gu5; //>>>
//// * 정원 5명이 꽉차서 구직자 회원가입이 불가합니다.!! <<
//// * 
//// * ////////////////////////////////////////////////////////////////////////
//// * 
//// * Scanner sc = new Scanner(System.in); Ctrl_gujikja ctrl = new Ctrl_gujikja();
//// * 
//// * String str_menuno =""; do { ctrl.main_menu(); str_menuno = sc.nextLine();
//// * switch (str_menuno) { case "1": // 구직자 회원가입 ctrl.register(sc, gu_arr); break;
//// * case "2": // 구직자 모두보기 break; case "3": // 검색하기 break; case "4": break;
//// * default: // 보기에 없는 경우 System.out.println("[경고] 메뉴에 없는 번호입니다.\n"); continue; }
//// * } while (!("4".equals(str_menuno))); // end of
//// * do~while------------------------ //키보드에 입력해준 값이 4일 때 빠져나온다 sc.close();
//// * System.out.println("\n>>> 프로그램 종료 <<<");
//// * 
//// * } // end of main()-----------------------
//// * 
//// * }
//// */
////
////
////
//////은닉화
////package my.day13.a.inheritance;
////
////import java.util.Scanner;
////
////public class Main_gujikja {
////
////	public static void main(String[] args) {
////		
////		Gujikja[] gu_arr = new Gujikja[5];
////		
////		///////////////////////////////////////////////
////		Gujikja gu1 = new Gujikja();
////		gu1.setId("eomjh ");	// 메소드의 접근제한자
////		gu1.setPasswd("aaaaaaaaaaaaaaaa");
////		gu1.setName("Eom Jung Hwa");
////		gu1.setJubun("8610022");
////		gu_arr[Gujikja.count++] = gu1;
////		
////		Gujikja gu2 = new Gujikja();
////		gu2.setId("leess");
////		gu2.setPasswd("abCd12345$");
////		gu2.setName("이순신");
////		gu2.setJubun("9209201");
////		gu_arr[Gujikja.count++] = gu2;
////		
////		Gujikja gu3 = new Gujikja();
////		gu3.setId("chaew");
////		gu3.setPasswd("aSdf1234$");
////		gu3.setName("차은우");
////		gu3.setJubun("0106203");
////		gu_arr[Gujikja.count++] = gu3;
////        ///////////////////////////////////////////////
////		
////		Company[] cp_arr = new Company[3];
////		
////		Company cp1 = new Company();
////		cp1.setId("SAMSUNG");
////		cp1.setPasswd("sdsdsd");
////		cp1.setName("삼성");
////		
////		
////		Company cp2 = new Company();
////		cp1.setId("lg");
////		cp1.setPasswd("Abcd1234$");
////		cp1.setName("LG");
////		
////		
////		
////	  
////        ///////////////////////////////////////////////
////		Scanner sc = new Scanner(System.in);
////		Ctrl_gujikja ctrl = new Ctrl_gujikja();
////		
////		String str_menuno = "";
////		do {
////			ctrl.main_menu();
////			str_menuno = sc.nextLine();
////			
////			switch (str_menuno) {
////				case "1": // 구직자 회원가입
////					ctrl.register(sc, gu_arr);
////					break;
////					
////				case "2": // 구직자 모두보기
////					ctrl.view_all_info(gu_arr);
////					break;
////					
////				case "3": // 검색하기
////					ctrl.search_menu(sc, gu_arr);
////					break;	
////					
////				case "4": // 프로그램종료
////					
////					break;						
////	
////				default:
////					System.out.println("[경고] 메뉴에 없는 번호 입니다.\n");
////					break;
////			}// end of switch()---------------
////			
////		} while (!("4".equals(str_menuno)));
////		// end of do~while-----------------------
////		
////		sc.close();
////		System.out.println("\n>>> 프로그램 종료 <<<");
////		
////	}// end of main()--------------------------------
////
////}
//package my.day13.a.inheritance;
//
//import java.util.Scanner;
//
//public class Main_gujikja_company {
//
//	public static void main(String[] args) {
//		
//		Gujikja[] gu_arr = new Gujikja[5];
//		
//		
//		///////////////////////////////////////////////
//		Gujikja gu1 = new Gujikja();
//		gu1.setId("eomjh");
//		gu1.setPasswd("qWer1234$");
//		gu1.setName("엄정화");
//		gu1.setJubun("8610202");
//		gu_arr[Gujikja.count++] = gu1;
//		
//		Gujikja gu2 = new Gujikja();
//		gu2.setId("leess");
//		gu2.setPasswd("abCd12345$");
//	    gu2.setName("이순신");
//	    gu2.setJubun("8601201");
//		gu_arr[Gujikja.count++] = gu2;
//		
//		Gujikja gu3 = new Gujikja();
//		gu3.setId("chaew");
//		gu3.setPasswd("aSdf1234$");
//		gu3.setName("차은우");
//		gu3.setJubun("0106203");
//		gu_arr[Gujikja.count++] = gu3;
//        ///////////////////////////////////////////////
//		
//		Company[] cp_arr = new Company[3];
//		
//		Company cp1 = new Company();
//		cp1.setId("samsung");
//		cp1.setPasswd("Abcd1234$");
//		cp1.setName("삼성");
//		cp1.setBusiness_number("wsfljslfj");
//		cp1.setJob_type("제조업");
//		cp1.setSeed_money(80000000L);
//		cp_arr[Company.count++] = cp1;
//		
//		Company cp2 = new Company();
//		cp2.setId("lg");
//		cp2.setPasswd("Abcd007$");
//		cp2.setName("엘지");
//		cp2.setBusiness_number("46486456");
//		cp2.setJob_type("IT");
//		cp2.setSeed_money(7000000000L);
//		cp_arr[Company.count++] = cp2;
//		///////////////////////////////////////////////
//		
//		Scanner sc = new Scanner(System.in);
//		Ctrl_Common ctrl_Common = new Ctrl_Common();
//		Ctrl_gujikja ctrl_gu = new Ctrl_gujikja();
//		Ctrl_company ctrl_cp = new Ctrl_company();
//		
//		String str_menuno = "";
//		do {
//			ctrl_gu.main_menu();
//			str_menuno = sc.nextLine();
//			
//			switch (str_menuno) {
//				case "1": // 구직자 회원가입
//					ctrl_gu.register(sc, gu_arr);
//					break;
//					
//				case "2": // 구인회사 회원가입
//					ctrl_gu.register(sc, cp_arr);
//					break;
//					
//				case "3": // 구직자 로그인
//					Gujikja login_gu = ctrl_gu.login(sc, gu_arr);
//					
//					if(login_gu != null) {
//						System.out.println(">> 구직자" + login_gu.getName()+ "님 로그인 성공^^ << \n");
//						
//						ctrl_gu.gu_menu(sc, login_gu, cp_arr);// 구직자 전용메뉴
//					}
//					else {
//						System.out.println(">> 구직자로 로그인 실패 ㅠㅜㅠㅠ << \n");
//					}
//					
//					break;	
//					
//				case "4": // 구인회사 로그인
//					
//					break;						
//				case "5": // 프로그램 종료
//					
//					break;
//	
//				default:
//					System.out.println("[경고] 메뉴에 없는 번호 입니다.\n");
//					break;
//			}// end of switch()---------------
//			
//		} while (!("5".equals(str_menuno)));
//		// end of do~while-----------------------
//		
//		sc.close();
//		System.out.println("\n>>> 프로그램 종료 <<<");
//		
//	}// end of main()--------------------------------
//
//}
package my.day13.a.inheritance;

import java.util.Scanner;

public class Main_gujikja_company {

	public static void main(String[] args) {
		
		Gujikja[] gu_arr = new Gujikja[5];
		
		///////////////////////////////////////////////
		Gujikja gu1 = new Gujikja();
		gu1.setId("eomjh");
		gu1.setPasswd("qWer1234$");
		gu1.setName("엄정화");
		gu1.setJubun("8610202");
		gu_arr[Gujikja.count++] = gu1;
		
		Gujikja gu2 = new Gujikja();
		gu2.setId("leess");
		gu2.setPasswd("abCd12345$");
	    gu2.setName("이순신");
	    gu2.setJubun("8601201");
		gu_arr[Gujikja.count++] = gu2;
		
		Gujikja gu3 = new Gujikja();
		gu3.setId("chaew");
		gu3.setPasswd("aSdf1234$");
		gu3.setName("차은우");
		gu3.setJubun("0106203");
		gu_arr[Gujikja.count++] = gu3;
        ///////////////////////////////////////////////
		
		Company[] cp_arr = new Company[3];
		
		Company cp1 = new Company();
		cp1.setId("samsung");
		cp1.setPasswd("Abcd1234$");
		cp1.setName("삼성");
		cp1.setBusiness_number("8123456789");
		cp1.setJob_type("제조업");
		cp1.setSeed_money(8000000000L);
		cp_arr[Company.count++] = cp1;
		
		Company cp2 = new Company();
		cp2.setId("lg");
		cp2.setPasswd("Abcd007$");
		cp2.setName("엘지");
		cp2.setBusiness_number("7123456789");
		cp2.setJob_type("IT");
		cp2.setSeed_money(7000000000L);
		cp_arr[Company.count++] = cp2; 
		
		///////////////////////////////////////////////
		
		Scanner sc = new Scanner(System.in);
		Ctrl_common  ctrl_common = new Ctrl_common();
		Ctrl_gujikja ctrl_gu = new Ctrl_gujikja();
		Ctrl_company ctrl_cp = new Ctrl_company();
		
		
		
		String str_menuno = "";
		do {
			ctrl_common.main_menu(); // 메인메뉴
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 구직자 회원가입
					ctrl_gu.register(sc, gu_arr);
					break;
					
				case "2": // 구인회사 회원가입
					ctrl_cp.register(sc, cp_arr);
					break;
					
				case "3": // 구직자 로그인
					Gujikja login_gu = ctrl_gu.login(sc, gu_arr);
					
					if(login_gu != null) {
						System.out.println(">> 구직자 "+ login_gu.getName() +"님 로그인 성공^^ << \n"); 
						
						ctrl_gu.gu_menu(sc, login_gu, cp_arr); // 구직자 전용메뉴
					}
					else {
						System.out.println(">> 구직자로 로그인 실패 ㅜㅜ <<\n");
					}
					
					break;	
					
				case "4": // 구인회사 로그인
					 Company login_cp =  ctrl_cp.login(sc, cp_arr);
					 

						if(login_cp != null) {
							System.out.println(">> 구직회사 "+ login_cp.getName() +"기업 로그인 성공^^ << \n"); 
						
							ctrl_gu.cp_menu(sc, login_cp, gu_arr); // 구인회사 전용메뉴 -> 구인회사는 사람을 찾아야함
						}
						else {
							System.out.println(">> 구인회사 로그인 실패 ㅜㅜ <<\n");
						}
					 
					break;	
					
				case "5": // 프로그램종료
					
					break;						
	
				default:
					System.out.println("[경고] 메뉴에 없는 번호 입니다.\n");
					break;
			}// end of switch()---------------
			
		} while (!("5".equals(str_menuno)));
		// end of do~while-----------------------
		
		sc.close();
		System.out.println("\n>>> 프로그램 종료 <<<");
		
	}// end of main()--------------------------------

}
