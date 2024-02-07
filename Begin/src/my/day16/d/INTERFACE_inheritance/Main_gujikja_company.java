package my.day16.d.INTERFACE_inheritance;

import java.util.Scanner;

public class Main_gujikja_company {

	public static void main(String[] args) {
		
		CommonMember[] cmbr_arr = new CommonMember[9];
		
		///////////////////////////////////////////////
		Gujikja_imple gu1 = new Gujikja_imple();
		gu1.setId("eomjh");
		gu1.setPasswd("qWer1234$");
		gu1.setName("엄정화");
		gu1.setJubun("8610202");
		cmbr_arr[CommonMember.count++] = gu1;
		
		Gujikja_imple gu2 = new Gujikja_imple();
		gu2.setId("leess");
		gu2.setPasswd("abCd12345$");
	    gu2.setName("이순신");
	    gu2.setJubun("8601201");
	    cmbr_arr[CommonMember.count++] = gu2;
		
		Gujikja_imple gu3 = new Gujikja_imple();
		gu3.setId("chaew");
		gu3.setPasswd("aSdf1234$");
		gu3.setName("차은우");
		gu3.setJubun("0106203");
		cmbr_arr[CommonMember.count++] = gu3;
        ///////////////////////////////////////////////
			
		
		Company_imple cp1 = new Company_imple();
		cp1.setId("samsung");
		cp1.setPasswd("Abcd1234$");
		cp1.setName("삼성");
		cp1.setBusiness_number("8123456789");
		cp1.setJob_type("제조업");
		cp1.setSeed_money(8000000000L);
		cmbr_arr[CommonMember.count++] = cp1;
		
		Company_imple cp2 = new Company_imple();
		cp2.setId("lg");
		cp2.setPasswd("Abcd007$");
		cp2.setName("엘지");
		cp2.setBusiness_number("7123456789");
		cp2.setJob_type("IT");
		cp2.setSeed_money(7000000000L);
		cmbr_arr[CommonMember.count++] = cp2; 
		
		///////////////////////////////////////////////
		
		Recruit[] rc_arr = new Recruit_imple[10];
		
		Recruit_imple rc1 = new Recruit_imple();
		rc1.setCp(cp1);
		rc1.setSubject("성실한 사무직원을 채용합니다.");
		rc1.setWork_type("사무직");
		rc1.setCnt(2);
		rc1.setYearpay(4000);
		rc1.setFinish_day("20241208");
		rc_arr[Recruit_imple.count++] = rc1;
		
		
		Recruit_imple rc2 = new Recruit_imple();
		rc2.setCp(cp2);
		rc2.setSubject("우수한 기술직원을 채용합니다.");
		rc2.setWork_type("기술직");
		rc2.setCnt(5);
		rc2.setYearpay(4500);
		rc2.setFinish_day("20241207");
		rc_arr[Recruit_imple.count++] = rc2;
		
		
		Recruit_imple rc3 = new Recruit_imple();
		rc3.setCp(cp2);
		rc3.setSubject("참신한 영업직원을 채용합니다.");
		rc3.setWork_type("영업직");
		rc3.setCnt(10);
		rc3.setYearpay(5000);
		rc3.setFinish_day("20241202");
		rc_arr[Recruit_imple.count++] = rc3;
		
        ///////////////////////////////////////////////////
	//	CommonMember cmbr = new CommonMeber();
	//  오류!! 왜냐하면 미완성클래스(추상클래스)는 객체화(인스턴스화) 할 수 없기 때문이다.!!!	
		
		///////////////////////////////////////////////////
		
		RecruitApply[] rcApply_arr = new RecruitApply[20];
		
		RecruitApply rcapply1 = new RecruitApply();
		rcapply1.setRc(rc1);
		rcapply1.setGu(gu1);
		rcapply1.setApply_motive("엄정화는 사무에 최선을 다합니다.");
		rcApply_arr[RecruitApply.count++] = rcapply1;
		
		RecruitApply rcapply2 = new RecruitApply();
		rcapply2.setRc(rc1);
		rcapply2.setGu(gu2);
		rcapply2.setApply_motive("이순신은 최고의 사무직 인재입니다.");
		rcApply_arr[RecruitApply.count++] = rcapply2;
		
		RecruitApply rcapply3 = new RecruitApply();
		rcapply3.setRc(rc2);
		rcapply3.setGu(gu3);
		rcapply3.setApply_motive("차은우는 최고의 기술을 가진 엔지니어 입니다.");
		rcApply_arr[RecruitApply.count++] = rcapply3;
		
		RecruitApply rcapply4 = new RecruitApply();
		rcapply4.setRc(rc3);
		rcapply4.setGu(gu1);
		rcapply4.setApply_motive("엄정화는 영업의 달인 입니다.");
		rcApply_arr[RecruitApply.count++] = rcapply4;
		
		RecruitApply rcapply5 = new RecruitApply();
		rcapply5.setRc(rc3);
		rcapply5.setGu(gu2);
		rcapply5.setApply_motive("이순신은 판매왕 입니다.");
		rcApply_arr[RecruitApply.count++] = rcapply5;
		
		///////////////////////////////////////////////////////////////////
		
		
		Scanner sc = new Scanner(System.in);
		Ctrl_common ctrl_common = new Ctrl_common_imple();
		Ctrl_gujikja ctrl_gu = new Ctrl_gujikja_imple();
		Ctrl_company ctrl_cp = new Ctrl_company_imple();
				
		String str_menuno = "";
		do {
			ctrl_common.main_menu(); // 메인메뉴
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 구직자 회원가입
					ctrl_gu.register(sc, cmbr_arr);
					break;
					
				case "2": // 구인회사 회원가입
					ctrl_cp.register(sc, cmbr_arr);
					break;
					
				case "3": // 구직자 로그인
					CommonMember login_gu = ctrl_common.login(sc, cmbr_arr, 1);
					
					if(login_gu != null) {
						System.out.println(">> 구직자 "+ login_gu.getName() +"님 로그인 성공^^ << \n"); 
						
						ctrl_gu.gu_menu(sc, (Gujikja_imple)login_gu, cmbr_arr, rc_arr, rcApply_arr); // 구직자 전용메뉴
					}
					else {
						System.out.println(">> 구직자로 로그인 실패 ㅜㅜ <<\n");
					}
					
					break;	
					
				case "4": // 구인회사 로그인
					CommonMember login_cp = ctrl_common.login(sc, cmbr_arr, 2);
					
					if(login_cp != null) {
						System.out.println(">> 구인회사 "+ login_cp.getName() +"기업 로그인 성공^^ << \n"); 
						
						ctrl_cp.cp_menu(sc, (Company_imple)login_cp, cmbr_arr, rc_arr, rcApply_arr); // 구인회사 전용메뉴
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
