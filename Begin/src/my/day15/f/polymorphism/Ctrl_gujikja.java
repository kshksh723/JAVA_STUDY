package my.day15.f.polymorphism;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ctrl_gujikja extends Ctrl_common {
	
	// == 구직자(Gujikja) 신규 회원가입시
	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	//    CommonMember[] cmbr_arr 에 저장시켜주는 메소드 생성하기 ==
	void register(Scanner sc, CommonMember[] cmbr_arr) {
		
		if(CommonMember.count < cmbr_arr.length) { // 지금까지 생성된 구직자 회원수가 cmbr_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
		    
			Gujikja gu = new Gujikja();
			
			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
			outer:
			do {
				System.out.print("1.아이디 : ");
				String id = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				for(int i=0; i<CommonMember.count; i++) {
					
					if(cmbr_arr[i].getType() == 1 && id.equals(cmbr_arr[i].getId()) ) {
						System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
						continue outer;
					}
					
				}// end of for-------------------
				// == 중복 아이디 검사하기 끝 == //
				
				gu.setId(id);
				
			} while(!(gu.getId() != null));
			
			
			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
			do {
				System.out.print("2.비밀번호 : ");
				String passwd = sc.nextLine();
				
				gu.setPasswd(passwd);
				
			} while(!(gu.getPasswd() != null));
			
			
			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
		    // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
			do {
				System.out.print("3.성명 : ");
				String name = sc.nextLine();
				
				gu.setName(name);
				
			} while(!(gu.getName() != null));
			
			
			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
			do {
				System.out.print("4.주민번호 : ");
				String jubun = sc.nextLine();
				
				gu.setJubun(jubun);
				
			} while(!(gu.getJubun() != null));
			
			////////////////////////////////////////////////
			
			cmbr_arr[CommonMember.count++] = gu;
			
			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
			
		}
		else { // 지금까지 생성된 구직자 회원수가 cmbr_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
			System.out.println(">> 정원 "+ cmbr_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
		}
		
	}// end of void register(Scanner sc, CommonMember[] cmbr_arr)------------

	
	// == 구직자 전용메뉴 메소드 생성하기 == //
	public void gu_menu(Scanner sc, Gujikja login_gu, CommonMember[] cmbr_arr, Recruit[] rc_arr, RecruitApply[] rcApply_arr) {
		
		String str_menuno;
		do {
			System.out.println("\n === 구직자 전용메뉴("+ login_gu.getName() +"님 로그인중) ===\n"
			                 + "1.내정보 보기   2.내정보 수정   3.모든구인회사 조회    4.구인회사검색하기\n"  
			                 + "5.모든채용공고조회    6.채용응모하기    7.채용응모한것조회    8.로그아웃\n");  
			System.out.print("▷ 메뉴번호 선택 : ");
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 내정보 보기
					view_myInfo(login_gu);
					break;
					
				case "2": // 내정보 수정
					update_myInfo(sc, login_gu);
					break;	
					
				case "3": // 모든구인회사 조회
					view_company_info(cmbr_arr); // 메소드의 오버로딩(overloading)
					break;	
					
				case "4": // 구인회사검색하기
					view_company_info(sc, cmbr_arr); // 메소드의 오버로딩(overloading)
					break;
					
					
				case "5":	// 모든채용공고조회      
					view_all_recruit_info(rc_arr);
					break;
					
				case "6":   // 채용응모하기
					input_recruitApply(sc, login_gu, rc_arr, rcApply_arr); 
					break;
					
				case "7":   // 채용응모한것조회 	
					view_my_recruitApply(login_gu, rcApply_arr);
					break;
					
				case "8": // 로그아웃
					login_gu = null;
					break;					
	
				default:
					System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. <<\n");
					break;
			}// end of switch (str_menuno)-----------------------
			
		} while(!"8".equals(str_menuno));
		// end of do~while-----------------------------
		
		System.out.println(">> 로그아웃 되었습니다. <<\n");
		
	}// end of public void gu_menu(Scanner sc, Gujikja login_gu, CommonMember[] cmbr_arr, Recruit[] rc_arr, RecruitApply[] rcApply_arr)--------------
	


	// 내정보 보기
	private void view_myInfo(Gujikja login_gu) {
	/*
	     
	     >>> 엄정화님의 정보 <<<
	     -------------------------------------
	       아이디    비밀번호      성명    주민번호  
	     -------------------------------------  
	       eomjh   qWer1234$   엄정화   8610202
	*/
		
		System.out.println(">>> "+ login_gu.getName() +"님의 정보 <<<");
		System.out.println("-".repeat(50));
		System.out.println("  아이디    비밀번호      성명    주민번호 ");
		System.out.println("-".repeat(50));
		System.out.println(login_gu.getId()+"\t"+
				           login_gu.getPasswd()+"\t"+
				           login_gu.getName()+"\t"+
				           login_gu.getJubun());
		
	}// end of private void view_myInfo(Gujikja login_gu)-----------------
	
	
	// 내정보 수정
	private void update_myInfo(Scanner sc, Gujikja login_gu) {
	
		view_myInfo(login_gu);
		
		System.out.println("\n>> [주의사항] 변경하지 않고 예전의 데이터값을 그대로 사용하시려면 그냥 엔터하세요!!\n");
		
		boolean exit_ok = false;
		do {
			//////////////////////////////////////////////////////////////
			System.out.print("1.비밀번호 : ");
			String new_passwd = sc.nextLine();  // 기존비밀번호인 qWer1234$ 을 qWer0070$ 으로 변경하려고 한다.
			                                    // 기존비밀번호인 qWer1234$ 을 qWer1234$ 으로 변경하려고 하려면 기존암호와 동일하므로 변경이 불가합니다. 
			                                    // 기존비밀번호인 qWer1234$ 을 변경하기 싫다. 
			                                    // 기존비밀번호인 qWer1234$ 을 abcd 로 변경하고자 할때는 비밀정책에 맞지 않으므로 안된다.!! 
			
			if(!new_passwd.equals("")) { // 입력한 비밀번호가 엔터가 아닐 경우 
				
				if(login_gu.getPasswd().equals(new_passwd) ) { // 입력한 비밀번호가 기존 비밀번호와 같을 경우 
					System.out.println(">> 기존암호와 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_gu.setPasswd(new_passwd);
					
					if(login_gu.getPasswd().equals(new_passwd)) {
						exit_ok = true;
					}
				}
				
			}
			
			else { // 입력한 비밀번호가 엔터일 경우
				exit_ok = true;
			}
		  //////////////////////////////////////////////////////////////
		} while(!exit_ok);
		// end of do~while-------------------------------------------
		
		
		exit_ok = false;
		do {
			////////////////////////////////////////////////////////////////////
			System.out.print("2.성명 : ");
			String new_name = sc.nextLine(); // 기존성명인 엄정화 를 엄화정 으로 변경하려고 한다.
	                                         // 기존성명인 엄정화 를 엄정화로 변경하려고 하려면 기존성명과 동일하므로 변경이 불가합니다. 
	                                         // 기존성명인 엄정화 을 변경하기 싫다. 
	                                         // 기존성명인 엄정화를 엄JungHwa 로 변경하고자 할때는 성명정책에 맞지 않으므로 안된다.!!
			
			if(!new_name.equals("")) { // 입력한 성명이 엔터가 아닐 경우 
				
				if(login_gu.getName().equals(new_name) ) { // 입력한 성명이 기존 성명과 같을 경우 
					System.out.println(">> 기존성명과 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_gu.setName(new_name);
					
					if(login_gu.getName().equals(new_name)) {
						exit_ok = true;
					}
				}
				
			}
			
			else { // 입력한 성명이 엔터일 경우
				exit_ok = true;
			}
          ////////////////////////////////////////////////////////////////////
		} while(!exit_ok);
        
		
		
		exit_ok = false;
		do {
			////////////////////////////////////////////////////////////////////
			System.out.print("3.주민번호 : ");
			
			String new_jubun = sc.nextLine(); 
	
			if(!new_jubun.equals("")) {  
	
				if(login_gu.getJubun().equals(new_jubun) ) {  
					System.out.println(">> 기존주민번호와 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_gu.setJubun(new_jubun);
					
					if(login_gu.getJubun().equals(new_jubun)) {
						exit_ok = true;
					}
				}
	
			}
			
			else { // 입력한 주민번호가 엔터일 경우
				exit_ok = true;
			}
			/////////////////////////////////////////////////////////////////	
		} while(!exit_ok);
		
		
	}// end of private void update_myInfo(Scanner sc, Gujikja login_gu)------	
	
	
	// 모든구인회사 조회
	private void view_company_info(CommonMember[] cmbr_arr) {
		
		int cnt = 0;
		for(int i=0; i<CommonMember.count; i++) {
			if(cmbr_arr[i].getType() == 2) {
				cnt++;
			}
		}// end of for--------------------------
		
		if(cnt == 0) {
			System.out.println(">> 구인회사로 등록된 회사가 한개도 없습니다. << \n");
		}
		
		else {
			title_company();
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<CommonMember.count; i++) {
				if(cmbr_arr[i].getType() == 2) {
					sb.append(cmbr_arr[i].getInfo()+"\n");
				}
			}// end of for--------------
			
			System.out.println(sb.toString());
		}
		
	}// end of private void view_company_info(CommonMember[] cmbr_arr)------


	private void title_company() {
		
		System.out.println("=".repeat(70));
		System.out.println(" 회사명   업종   사업자등록번호   자본금");
		System.out.println("=".repeat(70));
		
	}// end of private void title_company()-------------------------------
	
	
	// 구인회사검색하기
	private void view_company_info(Scanner sc, CommonMember[] cmbr_arr) {
		
		String str_menuno = "";		
		
		do {
			///////////////////////////////////////////////////////////////////
			System.out.println(">>> 구인회사 검색메뉴 <<<\n"
					         + "1. 업종검색    2.자본금검색    3.구직자메뉴로 돌아가기"); 
			System.out.print("▷ 검색메뉴번호 입력 : ");
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 업종검색
					search_jobtype_company(sc, cmbr_arr);
					break;
					
				case "2": // 자본금검색
					search_seedmoney_company(sc, cmbr_arr);
					break;
					
				case "3": // 구직자메뉴로 돌아가기
					
					break;				
		
				default:
					System.out.println(">>[경고] 구인회사 검색메뉴없는 번호 입니다.<<\n");
					break;
			}// end of switch(str_menuno)-------------------------------
	        ///////////////////////////////////////////////////////////////////
		} while(!"3".equals(str_menuno));
		
	}// end of private void search_company(Scanner sc, Company[] cp_arr)----------	

	
	// 업종검색
	private void search_jobtype_company(Scanner sc, CommonMember[] cmbr_arr) {
		
		System.out.print("▷ 업종명 : ");
		String job_type_name = sc.nextLine().toLowerCase();
		
		StringBuilder sb = new StringBuilder();
		
		boolean is_existence = false;
		
		for(int i=0; i<CommonMember.count; i++) {
			
			if(cmbr_arr[i] instanceof Company && 
			   ((Company) cmbr_arr[i]).getJob_type().toLowerCase().contains(String.join("", job_type_name.split("\\ "))) ) {
			 // cmbr_arr[i] instanceof Company 은 cmbr_arr[i] 객체(인스턴스)가 Company 객체이라면 이다. 
				is_existence = true;
				sb.append(cmbr_arr[i].getInfo()+"\n");
			}
			
		}// end of for----------------------------
		
		if(is_existence) {
			title_company();
			System.out.println(sb.toString());
		}
		else {
			System.out.println(">> 검색하시려는 "+ job_type_name +"업종에 해당하는 회사는 없습니다.!!\n");
		}
		
	}// end of private void search_jobtype_company(Scanner sc, CommonMember[] cmbr_arr)------

	
	// 자본금검색
	private void search_seedmoney_company(Scanner sc, CommonMember[] cmbr_arr) {
		
		System.out.print("▷ 자본금 : ");
		String str_search_seed_money = sc.nextLine();
		
		try {
			long search_seed_money = Long.parseLong(str_search_seed_money);
			
			StringBuilder sb = new StringBuilder();
			boolean is_existence = false;
			
			for(int i=0; i<CommonMember.count; i++) {
				if( cmbr_arr[i] instanceof Company &&
					search_seed_money <= ((Company) cmbr_arr[i]).getSeed_money() ) {
					is_existence = true;
					sb.append(cmbr_arr[i].getInfo()+"\n");
				}
			}// end of for----------------------------
			
			if(is_existence) {
				title_company();
				System.out.println(sb.toString());
			}
			else {
				System.out.println(">> 검색하시려는 자본금이 "+ str_search_seed_money +"원 이상인 회사는 없습니다.!!\n");
			}
			
		} catch(NumberFormatException e) {
			System.out.println(">>[경고] 자본금은 정수로만 입력하세요!!<< \n");
		}
		
	}// end of private void search_seedmoney_company(Scanner sc, CommonMember[] cmbr_arr)------


	// 모든채용공고조회(채용마감일자가 오늘보다 이전인 것은 보여주면 안된다)
	
	private void view_all_recruit_info(Recruit[] rc_arr) {
		
		if(Recruit.count == 0) {
			System.out.println(">> 채용공고가 1개도 없습니다. <<\n");
		}
		
		else {
			StringBuilder sb = new StringBuilder();
			try {
				
				Date now = new Date(); // util로 import
				SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMdd");
				Date today = sdft.parse( sdft.format(now)); // "20240207"
				
				for(int i=0; i<Recruit.count; i++) {
					 
				
					Date date_finish_day = sdft.parse( rc_arr[i].getFinish_day()); 
					
					
				if(!date_finish_day.before(today)) {
				
					sb.append(rc_arr[i].getRecruit_no()+"\t\t"+
							  rc_arr[i].getCp().getName()+"\t"+
							  rc_arr[i].getCp().getJob_type()+"\t"+
							  new DecimalFormat("#,###").format(rc_arr[i].getCp().getSeed_money())+"원\t"+
							  rc_arr[i].getWork_type()+"\t"+
							  rc_arr[i].getCnt()+"\t"+
							  rc_arr[i].getRegister_day().substring(0,4)+"-"+rc_arr[i].getRegister_day().substring(4,6)+"-"+rc_arr[i].getRegister_day().substring(6)+"\t"+  
							  rc_arr[i].getFinish_day().substring(0,4)+"-"+rc_arr[i].getFinish_day().substring(4,6)+"-"+rc_arr[i].getFinish_day().substring(6)+"\n");
				 	}
			 }// end of for-----------------------------------
			} catch(ParseException e) {
				
			}
			System.out.println("-".repeat(90));
			System.out.println("채용공고순번      회사명   회사직종타입  자본금   채용분야(근무형태)  채용인원  등록일자  채용마감일자");
			System.out.println("-".repeat(90));
		    System.out.println(sb.toString());
			System.out.println("[채용공고가 "+ Recruit.count +"건이 있습니다]\n");
		}
		
	}// end of private void view_all_recruit_info(Recruit[] rc_arr)------------	
	
	

	// 채용응모하기
	private void input_recruitApply(Scanner sc, Gujikja login_gu, Recruit[] rc_arr, RecruitApply[] rcApply_arr) {
		
		// 구직자 자신이 응모한 채용공고 번호를 알아오자.
		String str_my_recruit_no = "";
		for(int i=0; i<RecruitApply.count; i++) {
			if(rcApply_arr[i].getGu().getId().equals(login_gu.getId())) {
				str_my_recruit_no += rcApply_arr[i].getRc().getRecruit_no()+","; 
			}
		}// end of for-------------------------------
		//  str_my_recruit_no = "1,3,2,";
		
		if(str_my_recruit_no.length() > 0) { // 응모한 경우가 있는 경우라면
		str_my_recruit_no = str_my_recruit_no.substring(0, str_my_recruit_no.length()-1); 
		//  "1,3,2"
		
		String[] my_recruit_no_arr = str_my_recruit_no.split("\\,");
		// {"1","3","2"}
		
		if( my_recruit_no_arr.length == Recruit.count ) {
			System.out.println(">> 이미 모든 채용공고에 응모하셨기에 더 이상 응모할 채용공고번호가 없습니다. \n");
			return; // 메소드 종료 
		}
		
		}
		// == 채용공고번호는 채용공고로 올라온 번호만 입력해야 한다. == //
		boolean is_existence, is_duplicate_recruit_no;
		Recruit rc = null;
		do {
			is_existence = false;
			is_duplicate_recruit_no = false;
						
			System.out.print("▷ 채용공고번호 : ");
			String input_recruit_no = sc.nextLine();  // "1"  "3"  "2"  "1435"  "강아지"
			
			for(int i=0; i<Recruit.count; i++) {
				if( input_recruit_no.equals( String.valueOf(rc_arr[i].getRecruit_no()) ) ) {
					is_existence = true;
					rc = rc_arr[i];
					break;
				}
			}// end of for--------------------
			
			if(!is_existence) {
				System.out.println(">> 입력하신 "+ input_recruit_no +"번은 채용공고에 존재하지 않습니다.!!\n");
			}
			
			else {
					// == 채용공고번호는 채용공고로 올라온 번호이지만 이미 응모한 채용공고번호는 입력하면 안된다. == //
					for(int i=0; i<RecruitApply.count; i++) {
						// rcApply_arr[i].getRc().getRecruit_no() ==> 채용공고번호(int) 
						// rcApply_arr[i].getGu().getId()  ==> 채용공고에 지원한 구직자ID
						
						if(String.valueOf(rcApply_arr[i].getRc().getRecruit_no()).equals(input_recruit_no) &&  
						   rcApply_arr[i].getGu().getId().equals(login_gu.getId()) ) {
							 is_duplicate_recruit_no = true;
							 break;
						}
					}// end of for-----------------------------
					
					if(is_duplicate_recruit_no) {
						System.out.println(">> 입력하신 채용공고번호 "+ input_recruit_no + "번은 이미 응모하신 번호 입니다. \n"); 
					}
					
					else {
						do {
							System.out.print("▷ 지원동기 : ");
							String apply_motive = sc.nextLine();
							if(apply_motive.isBlank()) {
								System.out.println("[경고] 지원동기는 필수로 입력하셔야 합니다.!!\n");
							}
							else {
								RecruitApply rc_apply = new RecruitApply();
								rc_apply.setRc(rc);
								rc_apply.setGu(login_gu);
								rc_apply.setApply_motive(apply_motive);
								
								rcApply_arr[RecruitApply.count++] = rc_apply; 
								break;
							}
						} while(true);
					}
					
				}// end of if~else--------------------------------
				
		} while(!(is_existence && !is_duplicate_recruit_no));
		
	}// end of private void input_recruitApply(Scanner sc, Gujikja login_gu, Recruit[] rc_arr, RecruitApply[] rcApply_arr)-------------
	
	

	// 채용응모한것조회
	private void view_my_recruitApply(Gujikja login_gu, RecruitApply[] rcApply_arr) {
		
		boolean is_existence = false;
		
		for(int i=0; i<RecruitApply.count; i++) {
			if( login_gu.getId().equals(rcApply_arr[i].getGu().getId()) ) {
				is_existence = true;
				System.out.print(rcApply_arr[i].getRc().recruit_info());
				System.out.println("▣ 지원동기 : " + rcApply_arr[i].getApply_motive() + "\n\n");
			}
		}// end of for----------------------------------
		
		if(!is_existence) {
			System.out.println(">> 채용공고에 응모한 내역이 없습니다. <<\n");
		}
		
	}// end of private void view_my_recruitApply(Gujikja login_gu, RecruitApply[] rcApply_arr)--------	
	
}
