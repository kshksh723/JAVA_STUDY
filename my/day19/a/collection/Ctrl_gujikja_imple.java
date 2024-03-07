package my.day19.a.collection;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ctrl_gujikja_imple extends Ctrl_common_imple implements Ctrl_gujikja {
	
	// == 구직자(Gujikja) 신규 회원가입시
	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	//    List<Gujikja_imple> gu_list 와 Map<String, Gujikja_imple> gu_map 에 저장시켜주는 메소드 생성하기 ==
	@Override
	public void register(Scanner sc, List<Gujikja_imple> gu_list, Map<String, Gujikja_imple> gu_map) {
		    
			Gujikja_imple gu = new Gujikja_imple();
			
			String id = null;
			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
			outer:
			do {
				System.out.print("1.아이디 : ");
				id = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				if(gu_map.get(id) != null) {
					System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
					continue outer;
				}
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
			
			gu_list.add(gu);
			gu_map.put(id, gu);
			
			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
		
	}// end of void register(Scanner sc, List<Gujikja_imple> gu_list, Map<String, Gujikja_imple> gu_map)------------

	
	// == 구직자 로그인 메소드 생성하기 == //
	@Override
	public Gujikja_imple login(Scanner sc, Map<String, Gujikja_imple> gu_map) {
		
		System.out.print("▷ 구직자 ID : ");
		String id = sc.nextLine();
			
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
			
		if( gu_map.get(id) != null && passwd.equals( gu_map.get(id).getPasswd() ) ) {
			return gu_map.get(id);
		}
		else {
			return null;
		}
			
	}// end of public CommonMember login(Scanner sc, Map<String, Gujikja_imple> gu_map)-------
	
	
	
	// == 구직자 전용메뉴 메소드 생성하기 == //
	@Override
	public void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Company_imple> cp_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list) {
		
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
					view_company_info(cp_list); // 메소드의 오버로딩(overloading)
					break;	
					
				case "4": // 구인회사검색하기
					view_company_info(sc, cp_list); // 메소드의 오버로딩(overloading)
					break;
					
					
				case "5":	// 모든채용공고조회      
					view_all_recruit_info(rc_list);
					break;
					
				case "6":   // 채용응모하기
					input_recruitApply(sc, login_gu, rc_list, rcapply_list); 
					break;
					
				case "7":   // 채용응모한것조회 	
					view_my_recruitApply(login_gu, rcapply_list);
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
		
	}// end of public void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Company_imple> cp_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list)--------------
	


	// 내정보 보기
	private void view_myInfo(Gujikja_imple login_gu) {
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
	private void update_myInfo(Scanner sc, Gujikja_imple login_gu) {
	
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
	private void view_company_info(List<Company_imple> cp_list) {
		
		if(cp_list.size() == 0) {
			System.out.println(">> 구인회사로 등록된 회사가 한개도 없습니다. << \n");
		}
		
		else {
			title_company();
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<cp_list.size(); i++) {
				sb.append(cp_list.get(i).getInfo()+"\n");
			}// end of for--------------
			
			System.out.println(sb.toString());
		}
		
	}// end of private void view_company_info(List<Company_imple> cp_list)------


	private void title_company() {
		
		System.out.println("=".repeat(70));
		System.out.println(" 회사명   업종   사업자등록번호   자본금");
		System.out.println("=".repeat(70));
		
	}// end of private void title_company()-------------------------------
	
	
	// 구인회사검색하기
	private void view_company_info(Scanner sc, List<Company_imple> cp_list) {
		
		String str_menuno = "";		
		
		do {
			///////////////////////////////////////////////////////////////////
			System.out.println(">>> 구인회사 검색메뉴 <<<\n"
					         + "1. 업종검색    2.자본금검색    3.구직자메뉴로 돌아가기"); 
			System.out.print("▷ 검색메뉴번호 입력 : ");
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 업종검색
					search_jobtype_company(sc, cp_list);
					break;
					
				case "2": // 자본금검색
					search_seedmoney_company(sc, cp_list);
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
	private void search_jobtype_company(Scanner sc, List<Company_imple> cp_list) {
		
		System.out.print("▷ 업종명 : ");
		String job_type_name = sc.nextLine().toLowerCase();  
		
		StringBuilder sb = new StringBuilder();
		
		boolean is_existence = false;
		
		for(Company_imple cp : cp_list) {
			if( cp.getJob_type().toLowerCase().contains(String.join("", job_type_name.split("\\ "))) ) {
				is_existence = true;
				sb.append(cp.getInfo()+"\n");
			}
		}// end of for----------------------------
		
		if(is_existence) {
			title_company();
			System.out.println(sb.toString());
		}
		else {
			System.out.println(">> 검색하시려는 "+ job_type_name +"업종에 해당하는 회사는 없습니다.!!\n");
		}
		
	}// end of private void search_jobtype_company(Scanner sc, List<Company_imple> cp_list)------

	
	// 자본금검색
	private void search_seedmoney_company(Scanner sc, List<Company_imple> cp_list) {
		
		System.out.print("▷ 자본금 : ");
		String str_search_seed_money = sc.nextLine();
		
		try {
			long search_seed_money = Long.parseLong(str_search_seed_money);
			
			StringBuilder sb = new StringBuilder();
			boolean is_existence = false;
			
			for(Company_imple cp : cp_list) {
				if( search_seed_money <= cp.getSeed_money() ) {
					is_existence = true;
					sb.append(cp.getInfo()+"\n");
				}
			}// end of for---------------------------
			
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
		
	}// end of private void search_seedmoney_company(Scanner sc, List<Company_imple> cp_list)------


	// 모든채용공고조회 (채용마감일자가 오늘보다 이전인것은 보여주면 안된다!!)
	private void view_all_recruit_info(List<Recruit_imple> rc_list) {
		
		if(rc_list.size() == 0) {
			System.out.println(">> 채용공고가 1개도 없습니다. <<\n");
		}
		
		else {
			
			StringBuilder sb = new StringBuilder();
			
			try {
				Date now = new Date();
				SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMdd");
				Date today = sdft.parse(sdft.format(now)); // "20240207" ==> 20240207 00:00:00
				
				for(Recruit_imple rc : rc_list) {
					
					Date date_finish_day = sdft.parse(rc.getFinish_day());
					
					if(!date_finish_day.before(today)) {
						sb.append(rc.getRecruit_no()+"\t\t"+
								  rc.getCp().getName()+"\t"+
								  rc.getCp().getJob_type()+"\t"+
								  new DecimalFormat("#,###").format(rc.getCp().getSeed_money())+"원\t"+
								  rc.getWork_type()+"\t"+
								  rc.getCnt()+"\t"+
								  rc.getRegister_day().substring(0,4)+"-"+rc.getRegister_day().substring(4,6)+"-"+rc.getRegister_day().substring(6)+"\t"+  
								  rc.getFinish_day().substring(0,4)+"-"+rc.getFinish_day().substring(4,6)+"-"+rc.getFinish_day().substring(6)+"\n");
					}
					
				}// end of for---------------------
				
			} catch(ParseException e) {
				
			}
			
			System.out.println("-".repeat(90));
			System.out.println("채용공고순번      회사명   회사직종타입  자본금   채용분야(근무형태)  채용인원  등록일자  채용마감일자");
			System.out.println("-".repeat(90));
		    System.out.println(sb.toString());
			System.out.println("[채용공고가 "+ rc_list.size() +"건이 있습니다]\n");
		}
		
	}// end of private void view_all_recruit_info(List<Recruit_imple> rc_list)------------	
	
	

	// 채용응모하기
	private void input_recruitApply(Scanner sc, Gujikja_imple login_gu, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list) {
		
		// 구직자 자신이 응모한 채용공고 번호를 알아오자.
		String str_my_recruit_no = "";
		
		for(RecruitApply rc_apply : rcapply_list) {
			if(rc_apply.getGu().getId().equals(login_gu.getId())) {
				str_my_recruit_no += rc_apply.getRc().getRecruit_no()+","; 
			}
		}// end of for-----------------------
		//  str_my_recruit_no = "1,3,2,";
		
		if(str_my_recruit_no.length() > 0) { // 응모한 경우가 있는 경우라면
			str_my_recruit_no = str_my_recruit_no.substring(0, str_my_recruit_no.length()-1); 
			//  "1,3,2"
			
			String[] my_recruit_no_arr = str_my_recruit_no.split("\\,");
			// {"1","3","2"}
			
			if( my_recruit_no_arr.length == Recruit_imple.count ) {
				System.out.println(">> 이미 모든 채용공고에 응모하셨기에 더 이상 응모할 채용공고번호가 없습니다. \n");
				return; // 메소드 종료 
			}
		}
		
		// == 채용공고번호는 채용공고로 올라온 번호만 입력해야 한다. == //
		boolean is_existence, is_duplicate_recruit_no;
		Recruit_imple rc = null;
		do {
			is_existence = false;
			is_duplicate_recruit_no = false;
						
			System.out.print("▷ 채용공고번호 : ");
			String input_recruit_no = sc.nextLine();  // "1"  "3"  "2"  "1435"  "강아지"
			
			for(Recruit_imple recruit : rc_list) {
				if(input_recruit_no.equals( String.valueOf(recruit.getRecruit_no()) ) ) {
					is_existence = true;
					rc = recruit;
					break;
				}
			}// end of for------------------
			
			if(!is_existence) {
				System.out.println(">> 입력하신 "+ input_recruit_no +"번은 채용공고에 존재하지 않습니다.!!\n");
			}
			
			else {
					// == 채용공고번호는 채용공고로 올라온 번호이지만 이미 응모한 채용공고번호는 입력하면 안된다. == //
				    for(RecruitApply rcapply : rcapply_list) {
				    	// rcapply.getRc().getRecruit_no() ==> 채용공고번호(int)
				    	// rcapply.getGu().getId() ==> 채용공고에 지원한 구직자ID
				    	
				    	if( String.valueOf(rcapply.getRc().getRecruit_no()).equals(input_recruit_no) &&
				    		rcapply.getGu().getId().equals(login_gu.getId()) ) {
				    		   is_duplicate_recruit_no = true;
							   break;
				    	}
				    	
				    }// end of for--------------------
					
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
								
								rcapply_list.add(rc_apply);
								
								break;
							}
							
						} while(true);
					}
					
				}// end of if~else--------------------------------
				
		} while(!(is_existence && !is_duplicate_recruit_no));
		
	}// end of private void input_recruitApply(Scanner sc, Gujikja_imple login_gu, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list)-------------
	
	

	// 채용응모한것조회
	private void view_my_recruitApply(Gujikja_imple login_gu, List<RecruitApply> rcapply_list) {
		
		boolean is_existence = false;
		
		for(RecruitApply rcapply : rcapply_list) {
			if( login_gu.getId().equals(rcapply.getGu().getId()) ) {
				is_existence = true;
				System.out.print(rcapply.getRc().recruit_info());
				System.out.println("▣ 지원동기 : " + rcapply.getApply_motive() + "\n\n");
			}
		}// end of for----------------------
		
		if(!is_existence) {
			System.out.println(">> 채용공고에 응모한 내역이 없습니다. <<\n");
		}
		
	}// end of private void view_my_recruitApply(Gujikja login_gu, RecruitApply[] rcApply_arr)--------	
	
}
