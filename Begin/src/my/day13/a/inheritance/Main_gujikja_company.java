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
		
		Recruit[] rc_arr = new Recruit[10];
		
		Recruit rc1 = new Recruit();
		rc1.setCp(cp1);
		rc1.setWork_type("연구직");
		rc1.setCnt(5);
		rc1.setFinish_day("2024-02-08");
		rc_arr[Recruit.count++] = rc1;
		
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
					Company login_cp = ctrl_cp.login(sc, cp_arr);
					
					if(login_cp != null) {
						System.out.println(">> 구인회사 "+ login_cp.getName() +"기업 로그인 성공^^ << \n"); 
						
						ctrl_cp.cp_menu(sc, login_cp, gu_arr, rc_arr); // 구인회사 전용메뉴
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
