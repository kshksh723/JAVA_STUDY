package my.day19.a.collection;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ctrl_company_imple extends Ctrl_common_imple implements Ctrl_company {

	// == 구인회사(Company) 신규 회원가입시
	//    Company 클래스의 field 의 요구사항에 모두 맞으면
	//    List<Company_imple> cp_list 와 Map<String, Company_imple> cp_map 저장시켜주는 메소드 생성하기 ==
	@Override
	public void register(Scanner sc, List<Company_imple> cp_list, Map<String, Company_imple> cp_map) {
		    
			Company_imple cp = new Company_imple();
			
			String id = null;
			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
			outer:
			do {
				System.out.print("1.아이디 : ");
				id = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				if(cp_map.get(id) != null){
					System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
					continue outer;
				}
				// == 중복 아이디 검사하기 끝 == //
				
				cp.setId(id);
				
			} while(!(cp.getId() != null));
			
			
			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
			do {
				System.out.print("2.비밀번호 : ");
				String passwd = sc.nextLine();
				
				cp.setPasswd(passwd);
				
			} while(!(cp.getPasswd() != null));
			
			
			// 회사명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
		    // 회사명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
			do {
				System.out.print("3.회사명 : ");
				String name = sc.nextLine();
				
				cp.setName(name);
				
			} while(!(cp.getName() != null));
			
			
			// 사업자등록번호는 필수 입력사항이면서 사업자등록번호 조건에 맞을때 까지 반복해야 한다.
			do {
				System.out.print("4.사업자등록번호 : ");
				String business_number = sc.nextLine();
				
				cp.setBusiness_number(business_number);
				
			} while(!(cp.getBusiness_number() != null));
			
			////////////////////////////////////////////////
			
			cp_list.add(cp);
			cp_map.put(id, cp);
			
			System.out.println(">> 구인회사 회원가입 성공 !! <<\n");
				
	}// end of public void register(Scanner sc, List<Company_imple> cp_list, Map<String, Company_imple> cp_map)-----------

	
	// == 구인회사 로그인 메소드 생성하기 == //
	@Override
	public Company_imple login(Scanner sc, Map<String, Company_imple> cp_map) {
			
		System.out.print("▷ 구인회사 ID : ");
		String id = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
			
		if( cp_map.get(id) != null && passwd.equals(cp_map.get(id).getPasswd() ) ) {
			return cp_map.get(id);
		}
		else {
			return null;
		}
			
	}// end of public CommonMember login(Map<String, Company_imple> cp_map, Scanner sc)-------
	

	// == 구인회사 전용메뉴 ==
	@Override
	public void cp_menu(Scanner sc, Company_imple login_cp, List<Gujikja_imple> gu_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list) {

		String str_menuno;
		do {
			System.out.println("=== 구인회사 전용메뉴("+ login_cp.getName() +"기업 로그인중) ===\n"
					         + "1.우리회사정보 보기     2.우리회사정보 수정    3.모든구직자 조회    4.구직자 성별검색    5.구직자 연령대검색\n" 
					         + "6.구직자 연령대 및 성별검색  7.채용공고입력하기   8.우리회사 채용공고 조회   9.우리회사 채용공고 지원자 조회\n"
					         + "10.로그아웃");
			System.out.print("▷ 메뉴번호 선택 : ");
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 우리회사정보 보기
					view_myInfo(login_cp);
					break;
					
				case "2": // 우리회사정보 수정
					update_myInfo(sc, login_cp);
					break;
					
				case "3": // 모든구직자 조회
					view_all_info(gu_list);
					break;
					
				case "4": // 구직자 성별검색
					search_gender(sc, gu_list);
					break;
					
				case "5": // 구직자 연령대검색
					search_ageLine(sc, gu_list); 
					break;
					
				case "6": // 구직자 연령대 및 성별검색
					search_ageLine_gender(sc, gu_list);
					break;
					
				case "7": // 채용공고입력하기   
					register_recruit(sc, login_cp, rc_list);
					break;
					
				case "8": // 우리회사 채용공고 조회	
					view_recruit_mycompany(login_cp, rc_list);
					break;
					
				case "9": // 우리회사 채용공고 지원자 조회
					view_gujikja_my_recruitApply(login_cp, rcapply_list);
					break;
					
				case "10": // 로그아웃
					login_cp = null;
					break;					
	
				default:
					System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. <<\n"); 
					break;
			}// end of switch (str_menuno)------------------------
			
		} while(!"10".equals(str_menuno));
		
	}// end of public void cp_menu(Scanner sc, Company_imple login_cp, List<Gujikja_imple> gu_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list)--------------------
	


	// == 우리회사정보 보기 ==
	private void view_myInfo(Company_imple login_cp) {
	/*
	     
	     >>> 삼성 기업의 정보 <<<
	     ----------------------------------------------------------------------------------
	       아이디     비밀번호      회사명   가입일자      사업자등록번호  직종타입   자본금
	     ----------------------------------------------------------------------------------  
	       samsung  Abcd1234$   삼성     2024-02-02  8123456789   제조업    8,000,000,000원
	*/
		
		System.out.println("\n>>> "+ login_cp.getName() +" 기업의 정보 <<<");
		System.out.println("-".repeat(80));
		System.out.println("  아이디     비밀번호      회사명   가입일자      사업자등록번호  직종타입   자본금 ");
		System.out.println("-".repeat(80));
		System.out.println(login_cp.getId()+"\t"+
				           login_cp.getPasswd()+"\t"+
				           login_cp.getName()+"\t"+
				           login_cp.getRegister_day().substring(0, 10)+"\t"+
				           login_cp.getBusiness_number()+"\t"+
				           login_cp.getJob_type()+"\t"+
				           new DecimalFormat("#,###").format(login_cp.getSeed_money())+ "원\n" 
				          );		
	}// end of private void view_myInfo(Company login_cp)------------
	
	
	// == 우리회사정보 수정 ==
	private void update_myInfo(Scanner sc, Company_imple login_cp) {
		
		view_myInfo(login_cp);
		
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
				
				if(login_cp.getPasswd().equals(new_passwd) ) { // 입력한 비밀번호가 기존 비밀번호와 같을 경우 
					System.out.println(">> 기존암호와 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_cp.setPasswd(new_passwd);
					
					if(login_cp.getPasswd().equals(new_passwd)) {
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
			System.out.print("2.회사명 : ");
			String new_name = sc.nextLine(); // 기존회사명인 삼성 을 삼성전자 로 변경하려고 한다.
	                                         // 기존회사명인 삼성 을 삼성 으로 변경하려고 하려면 기존회사명과 동일하므로 변경이 불가합니다. 
	                                         // 기존회사명인 삼성 을 변경하기 싫다. 
	                                         // 기존회사명인 삼성 을 삼sung 으로 변경하고자 할때는 회사명정책에 맞지 않으므로 안된다.!!
			
			if(!new_name.equals("")) { // 입력한 회사명이 엔터가 아닐 경우 
				
				if(login_cp.getName().equals(new_name) ) { // 입력한 회사명이 기존 회사명과 같을 경우 
					System.out.println(">> 기존의 회사명과 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_cp.setName(new_name);
					
					if(login_cp.getName().equals(new_name)) {
						exit_ok = true;
					}
				}
				
			}
			
			else { // 입력한 회사명이 엔터일 경우
				exit_ok = true;
			}
          ////////////////////////////////////////////////////////////////////
		} while(!exit_ok);
        
		
		exit_ok = false;
		do {
			////////////////////////////////////////////////////////////////////
			System.out.print("3.사업자등록번호 : ");
			
			String new_business_number = sc.nextLine(); 
	
			if(!new_business_number.equals("")) {  
	
				if(login_cp.getBusiness_number().equals(new_business_number) ) {  
					System.out.println(">> 기존의 사업자등록번호와 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_cp.setBusiness_number(new_business_number);
					
					if(login_cp.getBusiness_number().equals(new_business_number)) {
						exit_ok = true;
					}
				}
	
			}
			
			else { // 입력한 사업자등록번호가 엔터일 경우
				exit_ok = true;
			}
			/////////////////////////////////////////////////////////////////	
		} while(!exit_ok);		
		
		
		exit_ok = false;
		do {
			////////////////////////////////////////////////////////////////////
			System.out.print("4.회사직종타입 : ");
			
			String new_job_type = sc.nextLine(); 
	
			if(!new_job_type.equals("")) {  
	
				if(login_cp.getJob_type().equals(new_job_type) ) {  
					System.out.println(">> 기존의 직종타입과 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_cp.setJob_type(new_job_type); 
					
					if(login_cp.getJob_type().equals(new_job_type)) {
						exit_ok = true;
					}
				}
	
			}
			
			else { // 입력한 직종타입이 엔터일 경우
				exit_ok = true;
			}
			/////////////////////////////////////////////////////////////////	
		} while(!exit_ok);	
		
	
		exit_ok = false;
		do {
			////////////////////////////////////////////////////////////////////
			System.out.print("5.자본금 : ");
			
			String str_new_seed_money = sc.nextLine(); 
			
			if(!str_new_seed_money.equals("")) {  
	
				long new_seed_money = 0;
				
				try {
					 new_seed_money = Long.parseLong(str_new_seed_money);
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 자본금은 정수로만 입력하셔야 합니다.!! \n"); 
					continue;
				}
				
				if(login_cp.getSeed_money() == new_seed_money) {  
					System.out.println(">> 기존의 자본금과 동일하므로 변경이 불가합니다.!!");
				}
				else {
					login_cp.setSeed_money(new_seed_money); 
					
					if(login_cp.getSeed_money() == new_seed_money) {
						exit_ok = true;
					}
				}
	
			}
			
			else { // 입력한 자본금이 엔터일 경우
				exit_ok = true;
			}
			/////////////////////////////////////////////////////////////////	
		} while(!exit_ok);		
		
		
	}// end of private void update_myInfo(Scanner sc, Company login_cp)--------

	
	// === 모든구직자 조회 ===
	private void view_all_info(List<Gujikja_imple> gu_list) {
		
		/*
		    -----------------------------------------------------------------------------
		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
		    -----------------------------------------------------------------------------
		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
		    -----------------------------------------------------------------------------  
		 */
		
		if(gu_list.size() == 0) {
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		
		else {
			title();
			StringBuilder sb = new StringBuilder();
			
			for(Gujikja_imple gu : gu_list) {
				sb.append(gu.getInfo()+"\n");
			}// end of for-------------------------
			
			System.out.println(sb.toString()); 
		}
		
	}// end of view_all_info(List<Gujikja_imple> gu_list)------------------
	
	
	private void title() {
		System.out.println("-".repeat(70)+"\n"
				         + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
				         + "-".repeat(70));
	}// end of void title()----------------------------	
	

	// === 구직자 성별검색 ===
	private void search_gender(Scanner sc, List<Gujikja_imple> gu_list) {
		
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		
		else { // 구직자가 존재하는 경우
			
			String input_gender = "";
			boolean isUse_input_gender = false;			
			do {
				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
				input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
			                                   // ""  "      " "강아지" --> 비정상
				
				switch (input_gender.trim()) {
					case "남": 
					case "여": 
						isUse_input_gender = true;
						input_gender = input_gender.trim();
						break;
		
					default:
						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
						break;
				}// end of switch (str_ageLine)-----------------------
				
			} while(!isUse_input_gender);
			// end of do~while------------------------
			
			
			// == 입력받은 성별에 해당하는 구직자 찾기 == //
			StringBuilder sb = new StringBuilder();
			boolean isSearch = false;
			
			for(Gujikja_imple gu : gu_list) {
				if( input_gender.equals(gu.getGender()) ) {
					isSearch = true;
					sb.append( gu.getInfo() + "\n");
				}	 
			}// end of for-----------------------	
			
			if(isSearch) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 성별 "+ input_gender +"자 구직자는 없습니다]\n");
			}
			
		}// end of if~else----------------------------------
		
	}// end of void search_gender(Scanner sc, List<Gujikja_imple> gu_list)------------	
	


	// === 구직자 연령대검색 === //
	private void search_ageLine(Scanner sc, List<Gujikja_imple> gu_list) {
		
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		
		else { // 구직자가 존재하는 경우
		
			String str_ageLine = "";
			boolean isUse_ageLine = false;
			
			do {
				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
			                                         // "25" "강아지" "-20" --> 비정상
				
				switch (str_ageLine) {
					case "0": 
					case "10": 
					case "20": 
					case "30": 
					case "40": 
					case "50": 
					case "60":
					case "70":
					case "80":
						isUse_ageLine = true;
						break;
		
					default:
						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
						break;
				}// end of switch (str_ageLine)-----------------------
				
			} while(!isUse_ageLine);
			// end of do~while------------------------
			
			
			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
			StringBuilder sb = new StringBuilder();
			boolean isSearch = false;
			
			for(Gujikja_imple gu : gu_list) {
				int ageLine = gu.getAge()/10*10;
				//                    나이/10*10
				//                     26/10*10 ==> 20
                //                     23/10*10 ==> 20
				
				if(Integer.parseInt(str_ageLine) == ageLine) {
					isSearch = true;
					sb.append(gu.getInfo()+"\n");
				}
			}// end of for-----------------------------
			
			if(isSearch) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
			}
			
		}// end of if~else----------------------------------
		
	}// end of search_ageLine(Scanner sc, List<Gujikja_imple> gu_list)--------------	
	
	
	
	// === 구직자 연령대 및 성별검색 === //
	private void search_ageLine_gender(Scanner sc, List<Gujikja_imple> gu_list) {
		
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
			return; // 해당 메소드(지금은 search_ageLine_gender())를 종료하는 것이다.
		}
		
		else { // 구직자가 존재하는 경우
		
			String str_ageLine = "";
			boolean isUse_ageLine = false;
			
			do {
				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
			                                  // "25" "강아지" "-20" --> 비정상
				
				switch (str_ageLine) {
					case "0": 
					case "10": 
					case "20": 
					case "30": 
					case "40": 
					case "50": 
					case "60":
					case "70":
					case "80":
						isUse_ageLine = true;
						break;
		
					default:
						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
						break;
				}// end of switch (str_ageLine)-----------------------
				
			} while(!isUse_ageLine);
			// end of do~while------------------------
			
			
			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
			boolean is_ageline_search = false;
			
			for(Gujikja_imple gu : gu_list) {
				int ageLine = gu.getAge()/10*10;
				//                    나이/10*10
				//                     26/10*10 ==> 20
                //                     23/10*10 ==> 20
				
				if(Integer.parseInt(str_ageLine) == ageLine) {
					is_ageline_search = true;
					break;
				}
			}// end of for------------------------------
						
			if(!is_ageline_search) { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우 
				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
				return; // 해당 메소드(지금은 search_ageLine_gender())를 종료하는 것이다.
			}

			else { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하는 경우
				
				// === !! 성별 검색 시작 !! === //
				String input_gender = "";
				boolean isUse_input_gender = false;			
				do {
					System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
					input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
				                                   // ""  "      " "강아지" --> 비정상
					
					switch (input_gender.trim()) {
						case "남": 
						case "여": 
							isUse_input_gender = true;
							input_gender = input_gender.trim();
							break;
			
						default:
							System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
							break;
					}// end of switch (str_ageLine)-----------------------
					
				} while(!isUse_input_gender);
				// end of do~while------------------------
				
				
				// == 입력받은 연령대 및 성별에 해당하는 구직자 찾기 == //
				StringBuilder sb = new StringBuilder();
				boolean is_ageline_gender_Search = false;
				
				for(Gujikja_imple gu : gu_list) {
					if( Integer.parseInt(str_ageLine) == gu.getAge()/10*10   
					    && input_gender.equals( gu.getGender()) ) {
							 
						   is_ageline_gender_Search = true;
						   sb.append( gu.getInfo() + "\n");
					}
				}// end of for---------------------------
				
				if(is_ageline_gender_Search) {
					title();
					System.out.println(sb.toString());
				}
				else {
					System.out.println("[검색결과 연령대가 "+ str_ageLine + "대인 "+ input_gender +"자 구직자는 없습니다]\n"); 
				}
				// === !! 성별 검색 끝 !! === //
			}
			
		}// end of if~else----------------------------------
		
	}// end of void search_ageLine_gender(Scanner sc, List<Gujikja_imple> gu_list)------		

	
	
	// == 채용공고 입력하기 == 
	private void register_recruit(Scanner sc, Company_imple login_cp, List<Recruit_imple> rc_list) {
		
		System.out.println("======= " + login_cp.getName() + " 채용공고 등록 ======="); 
		
		Recruit_imple rc = new Recruit_imple();
		
		do {
			System.out.print("1.채용제목 : ");
			String subject = sc.nextLine();
			rc.setSubject(subject);
		} while ( !(rc.getSubject() != null) );
		
		
		do {
			System.out.print("2.채용분야[예> 사무직] : ");
			String work_type = sc.nextLine();
			rc.setWork_type(work_type);
		} while ( !(rc.getWork_type() != null) );
		
		
		do {
			System.out.print("3.채용인원 : ");
			String str_cnt = sc.nextLine();
			
			try {
				int cnt = Integer.parseInt(str_cnt);
				if(cnt <= 0) {
					System.out.println("[경고] 1 이상 입력하세요!!");
				}
				else {
					rc.setCnt(cnt);
				}
			} catch(NumberFormatException e) {
				System.out.println("[경고] 1 이상 입력하세요!!");
			}
			
		} while ( !(rc.getCnt() > 0) );
		
		
		do {
			System.out.print("4.연봉[단위 만원] : ");
			String str_yearpay = sc.nextLine();
			
			try {
				int yearpay = Integer.parseInt(str_yearpay);
				if(yearpay <= 0) {
					System.out.println("[경고] 1 이상 입력하세요!!");
				}
				else {
					rc.setYearpay(yearpay);
				}
			} catch(NumberFormatException e) {
				System.out.println("[경고] 1 이상 입력하세요!!");
			}
			
		} while ( !(rc.getYearpay() > 0) );
		
		
		do {
			System.out.print("5.채용마감일자[예> 20240208] : ");
			String finish_day = sc.nextLine();
			rc.setFinish_day(finish_day);
			
		} while ( !(rc.getFinish_day() != null) );
		
		System.out.println("");
		
		rc.setCp(login_cp); 
		
		rc_list.add(rc);
		
	}// end of private void register_recruit(Scanner sc, Company_imple login_cp, List<Recruit_imple> rc_list)------	
	
	
	// == 우리회사 채용공고 조회 == 	
	private void view_recruit_mycompany(Company_imple login_cp, List<Recruit_imple> rc_list) {
		
		boolean is_existence = false;
		
		for(Recruit_imple rc : rc_list) {
			if( login_cp.getId().equals( rc.getCp().getId()) ) {
				is_existence = true;
				System.out.println(rc.recruit_info());
			}
		}// end of for-----------------------
		
		if(!is_existence) {
			System.out.println(">> 채용공고를 낸 것이 없습니다.<<\n");
		}
		
	}// end of private void view_recruit_mycompany(Company_imple login_cp, List<Recruit_imple> rc_list)--------
	
	
	
	// == 우리회사 채용공고 지원자 조회 ==
	private void view_gujikja_my_recruitApply(Company_imple login_cp, List<RecruitApply> rcapply_list) {
		
		boolean is_existence = false;
		StringBuilder sb = new StringBuilder();
		
		for(RecruitApply rcapply : rcapply_list) {
			if( login_cp.getId().equals( rcapply.getRc().getCp().getId() ) ) {
				is_existence = true;
				sb.append(rcapply.getRc().getRecruit_no()+"\t"+
						  rcapply.getRc().getSubject()+"\t"+
						  rcapply.getGu().getName()+"\t"+
						  rcapply.getGu().getGender()+"\t"+
						  rcapply.getGu().getAge()+"\t"+
						  rcapply.getApply_motive()+"\n");
			}
		}// end of for-------------------------
		
		if(is_existence) {
			System.out.println("=".repeat(100));
			System.out.println("채용공고번호  채용제목                 지원자명   성별  나이   지원동기 ");
			System.out.println("=".repeat(100));
			System.out.println(sb.toString());
		}
		else {
			System.out.println(">> 채용공고에 지원자가 없습니다. <<\n");
		}
		
	}// end of private void view_gujikja_my_recruitApply(Company_imple login_cp, List<RecruitApply> rcapply_list)--------	
	
}
