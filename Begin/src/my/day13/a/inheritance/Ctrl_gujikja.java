///////////*///*
//////////// * package my.day11.c.abstraction;
//////////// * 
//////////// * public class Ctrl_gujikja {
//////////// * 
//////////// * public static void main(String[] args) { // 여기서 메뉴를 만든다 // == 메인 메뉴를 보여주는 메소드
//////////// * 생성하기 == // void main_menu() { System.out.println("\n=== 메인메뉴====\n"
//////////// * +"1.구직자 회원가입 2. 구직자 모두보기 3. 검색하기 4. 프로그램 종료 \n" );
//////////// * System.out.println("ㅁ 메뉴번호 선택 : "); }// end of void main
//////////// * 
//////////// * 
//////////// * // == 구직자(GUJIKJA 신규 회원 가입시 // Gujikja클래스의 fieldㅢ 요구사항에 모두 맞으면
//////////// * 
//////////// * void register(Scanner sc, Gujikja[] gu_arr) { if (Gujikja.count <
//////////// * gu_arr.length) {
//////////// * 
//////////// * } else { System.out.println(">> 정원 " + gu_arr.length +
//////////// * "명이 꽉차서 구직자 회원가입이 불가합니다.!!<< \n"); } }
//////////// * 
//////////// * 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//////////// * 
//////////// * }
//////////// * 
//////////// * }
//////////// 
////////////package my.day11.c.abstraction;
////////////
////////////import java.util.Scanner;
////////////
////////////import my.util.MyUtil;
////////////
////////////public class Ctrl_gujikja {
////////////
////////////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
////////////	void main_menu() {
////////////		System.out.println("\n === 메인메뉴 ===\n"
////////////							+ "1. 구직자 회원가입  2. 구직자 모두보기  3.검색하기  4.프로그램종료\n");
////////////		System.out.print("▷ 메뉴번호 선택 : ");
////////////	}	// end of void main_menu()----------------------
////////////	
////////////	// == 구직자(Gujikja) 신규 회원가입시
////////////	//	Gujikja 클래스의 field 의 요구사항에 모두 맞으면
////////////	//	Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
////////////	
////////////	void register(Scanner sc, Gujikja[] gu_arr) {
////////////		
////////////			if(Gujikja.count < gu_arr.length) {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
////////////				//System.out.println("신규회원 가입합니다.");
////////////				
////////////				String userid;
////////////				String passwd;
////////////				String name;
////////////				
////////////				boolean isUse_userid;
////////////				do {
////////////						isUse_userid = true;		// 초기화
////////////						
////////////						System.out.println("1. 아이디 : ");
////////////						String userid = sc.nextLine();	// "eomjh" "leess" "chaew" 현재 사용중인 아이디
////////////												// "" 또는"				"공백만으로는 입력불가
////////////				
////////////				
////////////				
////////////				
////////////				
////////////				// == 가입된 회원들에 대해 중복 아이디 검사하기 ==//
////////////				// if(userid.trim().isEmpty()) //JDK 1.8
////////////				if(userid.isBlank()) {			// JDK 11
////////////					System.out.println(">> 아이디는 필수 입력사항입니다. << \n");
////////////					isUse_userid = false;
////////////				}
////////////				
////////////				else {
////////////					
////////////					for(int i = 0; i<Gujikja.count; i++) {
////////////						if(userid.equals(gu_arr[i].userid)) {
////////////							System.out.println(">> 이미 사용중인 아이디입니다. <<\n");
////////////							isUse_userid = false;
////////////							break;
////////////						
////////////					
////////////						
////////////					// isUse_userid = true;
////////////						}
////////////				} while(!isUse_userid);
////////////				
////////////				
////////////				// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을 때 까지 반복해야한다
////////////				boolean isUse_passwd; // 초기화
////////////				do {
////////////					isUse_passwd = true; //  초기화
////////////					System.out.print("2.비밀번호 : ");
////////////					String passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$" 
////////////					
////////////					if ( !MyUtil.isCheckPasswd(passwd));{
////////////						System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자");
////////////						isUse_passwd = false;
////////////					}
////////////				} while(!isUse_passwd);
////////////				// end of do~while---------
////////////			
////////////			
////////////				
////////////				// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////////////				 // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다
////////////				boolean isUse_name; // 초기화
////////////				do {
////////////					isUse_passwd = true; //  초기화
////////////					System.out.print("3.성명 : ");
////////////					String name = sc.nextLine(); // ""  "           "  "감 강 찬" "강" "김수한무거북이와두루미" "강KamC"
////////////												// "강감찬"
////////////					if(name.isBlank()) {
////////////						isUse_name = false;
////////////						
////////////					}
////////////					else {
////////////						char[] ch_arr
////////////					}
////////////					
////////////					char[] ch_arr = name.toCharArray();
////////////					
////////////					if(2 <= ch_arr.length && ch_arr.length <= 6) {
////////////						for(int i = 0; i<ch_arr.length; i++) {
////////////							if(!('가' <= ch_arr[i] && ch_arr[i] <= '힣' ) ) {
////////////								isUse_name = false;
////////////								break;
////////////							}
////////////						}//end of for -----
////////////					}
////////////					
////////////				else {
////////////					isUse_name = false;
////////////				}
////////////					
////////////					
////////////				if(!isUse_name) {
////////////					System.out.println("[경고] 성명은 공백 없이 한글로만 2글자 이상 6글자 이하이어야 합니다");
////////////				}
////////////			} while			
////////////					
////////////					
////////////					else{
////////////						
////////////						String (name.isBlank()) {
////////////						System.out.println("[경고] 성명은 공백 없이 한글로만 2글자 이상 6글자 이하이어야 합니다");
////////////					}
////////////					
////////////					if ( !MyUtil.isCheckPasswd(passwd));{
////////////						System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자");
////////////						isUse_passwd = false;
////////////					}
////////////				// 주민번호는  필수 입력사항이면서 주민번호 조건에 맞을 때까지 반복해야 한다.
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					
////////////					} while(!isUse_passwd);
////////////				// end of do~while---------
////////////				
////////////				
////////////				
////////////				
////////////				}
////////////				else {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
////////////				System.out.println(">>> 정원 " + gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
////////////				
////////////				}
////////////				
////////////				}	// end of void register(Scanner sc, Gujikja[] gu_arr)-------------
////////////*/
////////////
////////////
////////////
////////////package my.day11.c.abstraction;
////////////
////////////import java.util.Scanner;
////////////
////////////import my.util.MyUtil;
////////////
////////////public class Ctrl_gujikja {
////////////
////////////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
////////////	void main_menu() {
////////////		System.out.println("\n === 메인메뉴 ===\n"
////////////				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
////////////		System.out.print("▷ 메뉴번호 선택 : ");
////////////	}// end of void main_menu()--------------------------- 
////////////
////////////	
////////////	// == 구직자(Gujikja) 신규 회원가입시
////////////	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
////////////	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
////////////	void register(Scanner sc, Gujikja[] gu_arr) {
////////////		
////////////		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
////////////		    
////////////			String userid;
////////////			String passwd;
////////////			String name;
////////////			String jubun;
////////////			
////////////			boolean isUse_userid;
////////////			do {
////////////				isUse_userid = true; // 초기화 
////////////				
////////////				System.out.print("1.아이디 : ");
////////////				userid = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!! 
////////////				                        // "" 또는 "        " 공백만으로는 입력불가!!
////////////				
////////////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
////////////			//	if(userid.trim().isEmpty()) // JDK 1.8
////////////				if(userid.isBlank()) {	    // JDK 11
////////////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
////////////					isUse_userid = false;
////////////				}
////////////				else {
////////////					
////////////					for(int i=0; i<Gujikja.count; i++) {
////////////						if( userid.equals(gu_arr[i].userid) ) {
////////////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
////////////							isUse_userid = false;
////////////							break;
////////////						}
////////////					}// end of for---------------------
////////////				}
////////////			} while(!isUse_userid);
////////////			// end of do~while--------------------------------
////////////			
////////////			
////////////			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
////////////			boolean isUse_passwd;
////////////			do {
////////////				isUse_passwd = true; // 초기화
////////////				
////////////				System.out.print("2.비밀번호 : ");
////////////				passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
////////////				
////////////				if( !MyUtil.isCheckPasswd(passwd) ) {
////////////					System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n"); 
////////////					isUse_passwd = false;
////////////				}
////////////				
////////////			} while (!isUse_passwd);
////////////			// end of do~while--------------------------------
////////////			
////////////			
////////////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////////////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
////////////			boolean isUse_name;
////////////			do {
////////////				isUse_name = true; // 초기화
////////////				
////////////				System.out.print("3.성명 : ");
////////////				name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
////////////				                      // "강감찬"
////////////				
////////////				if(name.isBlank()) {
////////////					isUse_name = false;
////////////				}
////////////				else {
////////////					char[] ch_arr = name.toCharArray();
////////////					
////////////					if(2 <= ch_arr.length && ch_arr.length <= 6) {
////////////						
////////////						for(int i=0; i<ch_arr.length; i++) {
////////////							if( !('가' <= ch_arr[i] && ch_arr[i] <= '힣') ) {
////////////								isUse_name = false;
////////////								break;
////////////							}
////////////						}// end of for---------------------
////////////						
////////////					}
////////////					else {
////////////						isUse_name = false;
////////////					}
////////////				}
////////////								
////////////				if(!isUse_name) {
////////////				   System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
////////////				}
////////////				
////////////			} while (!isUse_name);
////////////			// end of do~while--------------------------------
////////////			
////////////			
////////////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
////////////			boolean isUse_jubun;
////////////			do {
////////////				isUse_jubun = true; // 초기화
////////////				
////////////				System.out.print("4.주민번호 : ");
////////////				jubun = sc.nextLine(); //"9610021" "9610022" 
////////////										// "0010023" "0010024"
////////////										// "9604311" "9604312"	"0004313"
////////////										// "9610025" "0010025"
////////////				
////////////				
////////////				if( !MyUtil.isCheckJubun(jubun) ) { // ischeckjubun에서 creat 눌르기!
////////////					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n"); 
////////////					isUse_jubun = false;
////////////				}
////////////			
////////////			} while (!isUse_jubun);
////////////			// end of do~while--------------------------------
////////////			
////////////			
////////////			Gujikja gu = new Gujikja();
////////////			gu.userid = userid;
////////////			gu.passwd = passwd;
////////////			gu.name = name;
////////////			gu.jubun = jubun;
////////////			
////////////			gu_arr[Gujikja.count++] = gu;
////////////			
////////////			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
////////////			
////////////		}
////////////		else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
////////////			System.out.println(">> 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
////////////		}
////////////	}	
////////////		// 구직자 모두보기
////////////		 void view_all_info(Gujikja[] gu_arr) {
/////////////*
//////////// * ---------------------------------------------------------------------------
//////////// * 		아이디			비밀번호		성명		 생년월일 		성별		현재나이		가입일자
//////////// *----------------------------------------------------------------------------
//////////// *		eomjh		qWe*****	엄정화		961020		여		  29		2024-01-31 10:30:40
//////////// *		leess		abC*****	이순신 	960120		남		  29		2024-01-31 10:30:40
//////////// *		chaew		aSd*****	차은우		010620		남		  22		2024-01-31 10:30:40
//////////// *------------------------------------------------------------------------------
//////////// */
////////////			 
////////////	if(Gujikja.count == 0) {
////////////		System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다 <<\n");
////////////	}
////////////	else {
////////////		title();	
////////////		StringBuilder sb = new StringBuilder();
////////////		
////////////		for(int i = 0; i<Gujikja.count; i++)
////////////		{
////////////			
////////////		 sb.append(gu_arr[i].getInfo()+"\n");//꺼내오기, 전체 인스턴스 정보를 가져오는 것,sb에 쌓아둬야한다, getInfo에 creat 누르기
////////////	}
////////////		 System.out.println(sb.toString());
////////////	
////////////	}
////////////	
////////////	
////////////}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
////////////		void title() {
////////////			System.out.println("-".repeat(60)+"\n"+"아이디			비밀번호		성명		 생년월일 		성별		현재나이		가입일자\n" 
////////////		+ " "+"-".repeat(60));
////////////		}
////////////
////////////		// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
////////////		void search_menu(Scanner sc, Gujikja[] gu_arr) {
////////////			
////////////			String str_menuno = "";
////////////			do {
////////////			System.out.println("\n === 검색메뉴 ===\n"
////////////						         + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
////////////				System.out.print("▷ 검색 메뉴번호 선택 : ");
////////////				
////////////				str_menuno = sc.nextLine();
////////////				switch (str_menuno) {
////////////				case "1": // 연령대 검색
////////////					search_ageLine(sc, gu_arr); // creat 만들기
////////////					
////////////					break;
////////////				case "2": // 성별 검색
////////////					search_gender(sc, gu_arr);
////////////					break;
////////////					
////////////				case "3": // 연령대 및 성별 검색
////////////					break;
////////////					
////////////				case "4": // 메인메뉴로 돌아가기
////////////					
////////////					break;
////////////					
////////////				default:
////////////					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!! \n");
////////////					break;
////////////				}// end of switch (key), switch ctrl+space key누르면 됌
////////////				
////////////			} while(!"4".equals(str_menuno));
////////////				
////////////			
////////////		}//end of void search_menu(Scanner sc, Gugikja
////////////
////////////		
////////////	
//////////////== 연령대 검색해주는 메소드 == //
////////////
////////////		void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
////////////			if(Gujikja.count == 0) { // 구직자가 없는 경우
////////////				System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다 <<");
////////////			}
////////////			else {// 구직자가 존재하는 경우
////////////				boolean isUse_ageLine = false;
////////////				do {
////////////					System.out.println("> 검색하고자하는 연령대[예: 20] :");
////////////					String str_ageLine = sc.nextLine(); //"0" "10""20""30""40""50""60""70""80" -- 정상 
////////////													// "25", "강아지" "-20" --> 비정상
////////////				switch (str_ageLine) {
////////////				case "0":
////////////				case "10":					
////////////				case "20":					
////////////				case "30":					
////////////				case "40":					
////////////				case "50":					
////////////				case "60":					
////////////				case "70":					
////////////				case "80":
////////////					isUse_ageLine = true;
////////////					break; //swith문의 break
////////////
////////////				default:
////////////					System.out.println("[경고] 올바른 연령대를 입력하세요!\n");
////////////					break;
////////////				}// end of switch 
////////////			} while(!isUse_ageLine);
////////////				// == 입력 받은 연령대에 해당하는 구직자 찾기 ==
////////////				
////////////				StringBuilder sb = new StringBuilder();
////////////				boolean isSearch = false;
////////////				for(int i = 0; i <Gujikja.count; i++) {
////////////					int ageLine = gu_arr[i].getAge()/10*10;
////////////					//				나이/10*10
////////////					//				26/10*10 =  => 20
////////////					//				23/10*10 ==> 20
////////////					if(Integer.parseInt(str_ageLine) == ageLine) {
////////////						isSearch = true;
////////////						sb.append(gu_arr[i].getInfo()+"\n");
////////////					
////////////					
////////////				
////////////				}// end for
////////////				if(isSearch)
////////////					title();
////////////				System.out.println(sb.toString());
////////////			}
////////////				else {
////////////					System.out.println("[검색결과 연령대" + str_ageLine + "대인 구직자는 없습니다]\n");
////////////				}
////////////			}//end of switch(str_ageLine)------------------
////////////		}// end of void search_ageLine(Scanner sc, Gujjikja[] gu_arr)
////////////		// 성별 검색
////////////				 void search_gender(Scanner sc, Gujikja[] gu_arr) {
////////////					
////////////					
////////////				}// end of	
////////////			/*
////////////			 * boolean isUse_ageLine = false; do {
////////////			 * System.out.println("> 검색하고자하는 연령대[예: 20] :"); String str_ageLine =
////////////			 * sc.nextLine(); //"0" "10""20""30""40""50""60""70""80" -- 정상 // "25", "강아지"
////////////			 * "-20" --> 비정상 switch (str_ageLine) { case "0":
////////////			 * 
////////////			 * case "10":
////////////			 * 
////////////			 * case "20":
////////////			 * 
////////////			 * case "30":
////////////			 * 
////////////			 * case "40":
////////////			 * 
////////////			 * case "50":
////////////			 * 
////////////			 * case "60":
////////////			 * 
////////////			 * case "70":
////////////			 * 
////////////			 * case "80": isUse_ageLine = true; break; //swith문의 break
////////////			 * 
////////////			 * default: System.out.println("[경고] 올바른 연령대를 입력하세요!\n"); break; }// end of
////////////			 * switch } while(!isUse_ageLine);
////////////			 */
////////////			
////////////			
////////////			
////////////			
////////////			
////////////			
////////////			
////////////}
////////////	
////////////
//////////package my.day11.c.abstraction;
//////////
//////////import java.util.Scanner;
//////////
//////////import my.util.MyUtil;
//////////
//////////public class Ctrl_gujikja {
//////////
//////////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
//////////	void main_menu() {
//////////		System.out.println("\n === 메인메뉴 ===\n" + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");
//////////		System.out.print("▷ 메뉴번호 선택 : ");
//////////	}// end of void main_menu()---------------------------
//////////
//////////	// == 구직자(Gujikja) 신규 회원가입시
//////////	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
//////////	// Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
//////////	void register(Scanner sc, Gujikja[] gu_arr) {
//////////
//////////		if (Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//////////
//////////			String userid;
//////////			String passwd;
//////////			String name;
//////////			String jubun;
//////////
//////////			boolean isUse_userid;
//////////			do {
//////////				isUse_userid = true; // 초기화
//////////
//////////				System.out.print("1.아이디 : ");
//////////				userid = sc.nextLine(); // "eomjh" "leess" "chaew" 현재 사용중인 아이디 이므로 입력불가!!
//////////										// "" 또는 " " 공백만으로는 입력불가!!
//////////
//////////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
//////////				// if(userid.trim().isEmpty()) // JDK 1.8
//////////				if (userid.isBlank()) { // JDK 11
//////////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
//////////					isUse_userid = false;
//////////				} else {
//////////
//////////					for (int i = 0; i < Gujikja.count; i++) {
//////////						if (userid.equals(gu_arr[i].userid)) {
//////////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
//////////							isUse_userid = false;
//////////							break;
//////////						}
//////////					} // end of for---------------------
//////////				}
//////////			} while (!isUse_userid);
//////////			// end of do~while--------------------------------
//////////
//////////			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
//////////			boolean isUse_passwd;
//////////			do {
//////////				isUse_passwd = true; // 초기화
//////////
//////////				System.out.print("2.비밀번호 : ");
//////////				passwd = sc.nextLine(); // "Qw12$" "Qwer1234sdfsdfdsfsfsdf$" "qwer1234$" "qWer1234$"
//////////
//////////				if (!MyUtil.isCheckPasswd(passwd)) {
//////////					System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n");
//////////					isUse_passwd = false;
//////////				}
//////////
//////////			} while (!isUse_passwd);
//////////			// end of do~while--------------------------------
//////////
//////////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
//////////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
//////////			boolean isUse_name;
//////////			do {
//////////				isUse_name = true; // 초기화
//////////
//////////				System.out.print("3.성명 : ");
//////////				name = sc.nextLine(); // "" " " "강 감 찬" "강" "김수한무거북이와두루미" "강KamC"
//////////										// "강감찬"
//////////
//////////				if (name.isBlank()) {
//////////					isUse_name = false;
//////////				} else {
//////////					char[] ch_arr = name.toCharArray();
//////////
//////////					if (2 <= ch_arr.length && ch_arr.length <= 6) {
//////////
//////////						for (int i = 0; i < ch_arr.length; i++) {
//////////							if (!('가' <= ch_arr[i] && ch_arr[i] <= '힣')) {
//////////								isUse_name = false;
//////////								break;
//////////							}
//////////						} // end of for---------------------
//////////
//////////					} else {
//////////						isUse_name = false;
//////////					}
//////////				}
//////////
//////////				if (!isUse_name) {
//////////					System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
//////////				}
//////////
//////////			} while (!isUse_name);
//////////			// end of do~while--------------------------------
//////////
//////////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
//////////			boolean isUse_jubun;
//////////			do {
//////////				isUse_jubun = true; // 초기화
//////////
//////////				System.out.print("4.주민번호 : ");
//////////				jubun = sc.nextLine(); // "9610021" "9610022" 정상
//////////										// "0010023" "0010024" 정상
//////////										// "9604311" "9604312" "0004313" "0004314" 비정상
//////////										// "9610025" "0010026" 비정상
//////////
//////////				if (!MyUtil.isCheckJubun(jubun)) {
//////////					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n");
//////////					isUse_jubun = false;
//////////				}
//////////
//////////			} while (!isUse_jubun);
//////////			// end of do~while--------------------------------
//////////
//////////			Gujikja gu = new Gujikja();
//////////			gu.userid = userid;
//////////			gu.passwd = passwd;
//////////			gu.name = name;
//////////			gu.jubun = jubun;
//////////
//////////			gu_arr[Gujikja.count++] = gu;
//////////
//////////			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
//////////
//////////		} else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
//////////			System.out.println(">> 정원 " + gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
//////////		}
//////////
//////////	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
//////////
//////////	// === 구직자 모두보기 메소드 ===
//////////	void view_all_info(Gujikja[] gu_arr) {
//////////		
//////////		
//////////		    -----------------------------------------------------------------------------
//////////		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
//////////		    -----------------------------------------------------------------------------
//////////		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
//////////		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
//////////		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
//////////		    -----------------------------------------------------------------------------  
//////////		 
//////////		
//////////		if(Gujikja.count == 0) {
//////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////////		}
//////////		
//////////		else {
//////////			title();
//////////			StringBuilder sb = new StringBuilder();
//////////			
//////////			for(int i=0; i<Gujikja.count; i++) {
//////////				sb.append(gu_arr[i].getInfo()+"\n");   
//////////			}// end of for-------------------------
//////////			
//////////			System.out.println(sb.toString()); 
//////////		}
//////////		
//////////	}// end of void view_all_info(Gujikja[] gu_arr)------------------
//////////
//////////	void title() {
//////////		System.out.println(
//////////				"-".repeat(70) + "\n" + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n" + "-".repeat(70));
//////////	}// end of void title()----------------------------
//////////
//////////	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
//////////	void search_menu(Scanner sc, Gujikja[] gu_arr) {
//////////
//////////		String str_menuno = "";
//////////		do {
//////////			System.out.println("\n === 검색메뉴 ===\n" + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");
//////////			System.out.print("▷ 검색메뉴번호 선택 : ");
//////////
//////////			str_menuno = sc.nextLine();
//////////
//////////			switch (str_menuno) {
//////////			case "1": // 연령대검색
//////////				search_ageLine(sc, gu_arr);
//////////				break;
//////////
//////////			case "2": // 성별검색
//////////				search_gender(sc, gu_arr);
//////////				break;
//////////
//////////			case "3": // 연령대 및 성별 검색하기
//////////
//////////				break;
//////////
//////////			case "4": // 메인메뉴로 돌아가기
//////////
//////////				break;
//////////
//////////			default:
//////////				System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
//////////				break;
//////////			}// end of switch (key)--------------------------
//////////
//////////		} while (!"4".equals(str_menuno));
//////////		// end of do~while------------------------
//////////
//////////	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
//////////
//////////	// == 연령대 검색해주는 메소드 == //
//////////	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
//////////
//////////		if (Gujikja.count == 0) { // 구직자가 없는 경우
//////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////////		}
//////////
//////////		else { // 구직자가 존재하는 경우
//////////
//////////			String str_ageLine = "";
//////////			boolean isUse_ageLine = false;
//////////
//////////			do {
//////////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
//////////				str_ageLine = sc.nextLine(); // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
//////////												// "25" "강아지" "-20" --> 비정상
//////////
//////////				switch (str_ageLine) {
//////////				case "0":
//////////				case "10":
//////////				case "20":
//////////				case "30":
//////////				case "40":
//////////				case "50":
//////////				case "60":
//////////				case "70":
//////////				case "80":
//////////					isUse_ageLine = true;
//////////					break;
//////////
//////////				default:
//////////					System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
//////////					break;
//////////				}// end of switch (str_ageLine)-----------------------
//////////
//////////			} while (!isUse_ageLine);
//////////			// end of do~while------------------------
//////////
//////////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
//////////			StringBuilder sb = new StringBuilder();
//////////			boolean isSearch = false;
//////////
//////////			for (int i = 0; i < Gujikja.count; i++) {
//////////				int ageLine = gu_arr[i].getAge() / 10 * 10;
//////////				// 나이/10*10
//////////				// 26/10*10 ==> 20
//////////				// 23/10*10 ==> 20
//////////
//////////				if (Integer.parseInt(str_ageLine) == ageLine) {
//////////					isSearch = true;
//////////					sb.append(gu_arr[i].getInfo() + "\n");
//////////				}
//////////
//////////			} // end of for--------------------------
//////////
//////////			if (isSearch) {
//////////				title();
//////////				System.out.println(sb.toString());
//////////			} else {
//////////				System.out.println("[검색결과 연령대 " + str_ageLine + "대인 구직자는 없습니다]\n");
//////////			}
//////////
//////////		} // end of if~else----------------------------------
//////////
//////////	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------
//////////
//////////	// == 성별 검색해주는 메소드 == //
//////////	void search_gender(Scanner sc, Gujikja[] gu_arr) {
//////////		
//////////		if(Gujikja.count == 0) { // 구직자가 없는 경우
//////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////////		}
//////////		
//////////		else { // 구직자가 존재하는 경우
//////////			
//////////			String input_gender = "";
//////////			boolean isUse_input_gender = false;			
//////////			do {
//////////				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
//////////				input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
//////////			                                   // ""  "      " "강아지" --> 비정상
//////////				
//////////				switch (input_gender.trim()) {
//////////					case "남": 
//////////					case "여": 
//////////						isUse_input_gender = true;
//////////						input_gender = input_gender.trim();
//////////						break;
//////////		
//////////					default:
//////////						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
//////////						break;
//////////				}// end of switch (str_ageLine)-----------------------
//////////				
//////////			} while(!isUse_input_gender);
//////////			// end of do~while------------------------
//////////			
//////////			
//////////			// == 입력받은 성별에 해당하는 구직자 찾기 == //
//////////			StringBuilder sb = new StringBuilder();
//////////			boolean isSearch = false;
//////////			
//////////			for(int i=0; i<Gujikja.count; i++) {
//////////				
//////////				 if(input_gender.equals(gu_arr[i].getGender()) {
//////////					 isSearch = true;
//////////					 
//////////				 }
//////////				
//////////			}// end of for--------------------------
//////////			
//////////			if(isSearch) {
//////////				title();
//////////				System.out.println(sb.toString());
//////////			}
//////////			else {
//////////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
//////////			}
//////////			
//////////		}// end of if~else----------------------------------
//////////		
//////////	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
//////////
//////////}*/
////////package my.day11.c.abstraction;
////////
////////import java.util.Scanner;
////////
////////import my.util.MyUtil;
////////
////////public class Ctrl_gujikja {
////////
////////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
////////	void main_menu() {
////////		System.out.println("\n === 메인메뉴 ===\n"
////////				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
////////		System.out.print("▷ 메뉴번호 선택 : ");
////////	}// end of void main_menu()--------------------------- 
////////
////////	
////////	// == 구직자(Gujikja) 신규 회원가입시
////////	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
////////	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
////////	void register(Scanner sc, Gujikja[] gu_arr) {
////////		
////////		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
////////		    
////////			String userid;
////////			String passwd;
////////			String name;
////////			String jubun;
////////			
////////			boolean isUse_userid;
////////			do {
////////				isUse_userid = true; // 초기화 
////////				
////////				System.out.print("1.아이디 : ");
////////				userid = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!! 
////////				                        // "" 또는 "        " 공백만으로는 입력불가!!
////////				
////////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
////////			//	if(userid.trim().isEmpty()) // JDK 1.8
////////				if(userid.isBlank()) {	    // JDK 11
////////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
////////					isUse_userid = false;
////////				}
////////				else {
////////					
////////					for(int i=0; i<Gujikja.count; i++) {
////////						if( userid.equals(gu_arr[i].userid) ) {
////////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
////////							isUse_userid = false;
////////							break;
////////						}
////////					}// end of for---------------------
////////				}
////////			} while(!isUse_userid);
////////			// end of do~while--------------------------------
////////			
////////			
////////			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
////////			boolean isUse_passwd;
////////			do {
////////				isUse_passwd = true; // 초기화
////////				
////////				System.out.print("2.비밀번호 : ");
////////				passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
////////				
////////				if( !MyUtil.isCheckPasswd(passwd) ) {
////////					System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n"); 
////////					isUse_passwd = false;
////////				}
////////				
////////			} while (!isUse_passwd);
////////			// end of do~while--------------------------------
////////			
////////			
////////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
////////			boolean isUse_name;
////////			do {
////////				isUse_name = true; // 초기화
////////				
////////				System.out.print("3.성명 : ");
////////				name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
////////				                      // "강감찬"
////////				
////////				if(name.isBlank()) {
////////					isUse_name = false;
////////				}
////////				else {
////////					char[] ch_arr = name.toCharArray();
////////					
////////					if(2 <= ch_arr.length && ch_arr.length <= 6) {
////////						
////////						for(int i=0; i<ch_arr.length; i++) {
////////							if( !('가' <= ch_arr[i] && ch_arr[i] <= '힣') ) {
////////								isUse_name = false;
////////								break;
////////							}
////////						}// end of for---------------------
////////						
////////					}
////////					else {
////////						isUse_name = false;
////////					}
////////				}
////////								
////////				if(!isUse_name) {
////////				   System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
////////				}
////////				
////////			} while (!isUse_name);
////////			// end of do~while--------------------------------
////////			
////////			
////////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
////////			boolean isUse_jubun;
////////			do {
////////				isUse_jubun = true; // 초기화
////////				
////////				System.out.print("4.주민번호 : ");
////////				jubun = sc.nextLine(); // "9610021" "9610022"  정상
////////				                       // "0010023" "0010024"  정상
////////				                       // "9604311" "9604312"  "0004313"  "0004314" 비정상
////////				                       // "9610025" "0010026"  비정상
////////				
////////			
////////				if( !MyUtil.isCheckJubun(jubun) ) {
////////					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n"); 
////////					isUse_jubun = false;
////////				}
////////				
////////			} while (!isUse_jubun);
////////			// end of do~while--------------------------------
////////			
////////			
////////			Gujikja gu = new Gujikja();
////////			gu.userid = userid;
////////			gu.passwd = passwd;
////////			gu.name = name;
////////			gu.jubun = jubun;
////////			
////////			gu_arr[Gujikja.count++] = gu;
////////			
////////			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
////////			
////////		}
////////		else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
////////			System.out.println(">> 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
////////		}
////////		
////////	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
////////
////////
////////	// === 구직자 모두보기 메소드 ===
////////	void view_all_info(Gujikja[] gu_arr) {
////////		
////////		/*
////////		    -----------------------------------------------------------------------------
////////		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
////////		    -----------------------------------------------------------------------------
////////		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
////////		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
////////		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
////////		    -----------------------------------------------------------------------------  
////////		 */
////////		
////////		if(Gujikja.count == 0) {
////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////////		}
////////		
////////		else {
////////			title();
////////			StringBuilder sb = new StringBuilder();
////////			
////////			for(int i=0; i<Gujikja.count; i++) {
////////				sb.append(gu_arr[i].getInfo()+"\n");   
////////			}// end of for-------------------------
////////			
////////			System.out.println(sb.toString()); 
////////		}
////////		
////////	}// end of void view_all_info(Gujikja[] gu_arr)------------------
////////	
////////	
////////	void title() {
////////		System.out.println("-".repeat(70)+"\n"
////////				         + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
////////				         + "-".repeat(70));
////////	}// end of void title()----------------------------
////////
////////
////////	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
////////	void search_menu(Scanner sc, Gujikja[] gu_arr) {
////////		
////////		String str_menuno = "";
////////		do {
////////			System.out.println("\n === 검색메뉴 ===\n"
////////			                + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
////////			System.out.print("▷ 검색메뉴번호 선택 : ");
////////			
////////			str_menuno = sc.nextLine();
////////			
////////			switch (str_menuno) {
////////				case "1": // 연령대검색
////////					search_ageLine(sc, gu_arr);
////////					break;
////////					
////////				case "2": // 성별검색
////////					search_gender(sc, gu_arr);
////////					break;
////////					
////////				case "3": // 연령대 및 성별 검색하기
////////					search_ageLine_gender(sc, gu_arr);
////////				
////////					
////////					break;
////////					
////////				case "4": // 메인메뉴로 돌아가기
////////					
////////					break;					
////////	
////////				default:
////////					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
////////					break;
////////			}// end of switch (key)--------------------------
////////			
////////			
////////		} while(!"4".equals(str_menuno));
////////		// end of do~while------------------------
////////		
////////	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
////////
////////	// == 연령대 및 성별 검색해주는 메소드
////////	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
////////		if(Gujikja.count == 0) { // 구직자가 없는 경우
////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////////		}
////////		
////////		else { // 구직자가 존재하는 경우
////////		
////////			String str_ageLine = "";
////////			boolean isUse_ageLine = false;
////////			
////////			do {
////////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
////////				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
////////			                                         // "25" "강아지" "-20" --> 비정상
////////				
////////				switch (str_ageLine) {
////////					case "0": 
////////					case "10": 
////////					case "20": 
////////					case "30": 
////////					case "40": 
////////					case "50": 
////////					case "60":
////////					case "70":
////////					case "80":
////////						isUse_ageLine = true;
////////						break;
////////		
////////					default:
////////						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
////////						break;
////////				}// end of switch (str_ageLine)-----------------------
////////				
////////			} while(!isUse_ageLine);
////////			// end of do~while------------------------
////////			
////////			
////////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
////////			boolean is_ageline_Search = false;
////////			
////////			for(int i=0; i<Gujikja.count; i++) {
////////				int ageLine = gu_arr[i].getAge()/10*10;
////////				//                  나이/10*10
////////				//                  26/10*10 ==> 20
////////                //                  23/10*10 ==> 20
////////				
////////				if(Integer.parseInt(str_ageLine) == ageLine) {
////////					is_ageline_Search = true;
////////					break;
////////				}
////////				
////////			}// end of for--------------------------
////////			
////////			if(!is_ageline_Search) { // ! 존재하지 않는다, 검색하고자하는 연령대에 해당하는 구직자가 존재하지 않는 경우
////////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
////////				title();
////////				System.out.println(sb.toString());
////////			}
////////			else { //검색하고자하는 연령대에 해당하는 구직자가 존재하는 경우
////////				// == 성별 검색시작!
////////				String input_gender = "";
////////				boolean isUse_input_gender = false;			
////////				do {
////////					System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
////////					input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
////////				                                   // ""  "      " "강아지" --> 비정상
////////					
////////					switch (input_gender.trim()) {
////////						case "남": 
////////						case "여": 
////////							isUse_input_gender = true;
////////							input_gender = input_gender.trim();
////////							break;
////////			
////////						default:
////////							System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
////////							break;
////////					}// end of switch (str_ageLine)-----------------------
////////					
////////				} while(!isUse_input_gender);
////////				// end of do~while------------------------
////////				
////////				
////////				// == 입력받은 성별에 해당하는 구직자 찾기 == //
////////				StringBuilder sb = new StringBuilder();
////////				boolean is_agelin_gender_Search = false;
////////				// == 성별 검색끝!
////////		
////////				for(int i=0; i<Gujikja.count; i++) {
////////				
////////				 if(Integer.parseInt(str_ageLine) == gu_arr[i].getAge()/10*10
////////						 && input_gender.equals(gu_arr[i].getGender()){
////////					 					 
////////				 }
////////				
////////				 }//end of 
////////		
////////	}// end of void search_ageLine_gender
////////	
////////	
////////		
////////			
////////
////////	
////////	// == 연령대 검색해주는 메소드 == //
////////	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
////////		
////////		if(Gujikja.count == 0) { // 구직자가 없는 경우
////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////////		}
////////		
////////		else { // 구직자가 존재하는 경우
////////		
////////			String str_ageLine = "";
////////			boolean isUse_ageLine = false;
////////			
////////			do {
////////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
////////				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
////////			                                         // "25" "강아지" "-20" --> 비정상
////////				
////////				switch (str_ageLine) {
////////					case "0": 
////////					case "10": 
////////					case "20": 
////////					case "30": 
////////					case "40": 
////////					case "50": 
////////					case "60":
////////					case "70":
////////					case "80":
////////						isUse_ageLine = true;
////////						break;
////////		
////////					default:
////////						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
////////						break;
////////				}// end of switch (str_ageLine)-----------------------
////////				
////////			} while(!isUse_ageLine);
////////			// end of do~while------------------------
////////			
////////			
////////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
////////			StringBuilder sb = new StringBuilder();
////////			boolean isSearch = false;
////////			
////////			for(int i=0; i<Gujikja.count; i++) {
////////				int ageLine = gu_arr[i].getAge()/10*10;
////////				//                  나이/10*10
////////				//                  26/10*10 ==> 20
////////                //                  23/10*10 ==> 20
////////				
////////				if(Integer.parseInt(str_ageLine) == ageLine) {
////////					isSearch = true;
////////					sb.append(gu_arr[i].getInfo()+"\n");
////////				}
////////				
////////			}// end of for--------------------------
////////			
////////			if(isSearch) {
////////				title();
////////				System.out.println(sb.toString());
////////			}
////////			else {
////////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
////////			}
////////			
////////		}// end of if~else----------------------------------
////////		
////////	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------
////////
////////	
////////	// == 성별 검색해주는 메소드 == //
////////	void search_gender(Scanner sc, Gujikja[] gu_arr) {
////////		
////////		if(Gujikja.count == 0) { // 구직자가 없는 경우
////////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////////		}
////////		
////////		else { // 구직자가 존재하는 경우
////////			
////////			String input_gender = "";
////////			boolean isUse_input_gender = false;			
////////			do {
////////				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
////////				input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
////////			                                   // ""  "      " "강아지" --> 비정상
////////				
////////				switch (input_gender.trim()) {
////////					case "남": 
////////					case "여": 
////////						isUse_input_gender = true;
////////						input_gender = input_gender.trim();
////////						break;
////////		
////////					default:
////////						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
////////						break;
////////				}// end of switch (str_ageLine)-----------------------
////////				
////////			} while(!isUse_input_gender);
////////			// end of do~while------------------------
////////			
////////			
////////			// == 입력받은 성별에 해당하는 구직자 찾기 == //
////////			StringBuilder sb = new StringBuilder();
////////			boolean isSearch = false;
////////			
////////			for(int i=0; i<Gujikja.count; i++) {
////////				
////////				 if(input_gender.equals(gu_arr[i].getGender())) {
////////					 isSearch = true;
////////					 
////////				 }
////////				
////////			}// end of for--------------------------
////////			
////////			
////////
////////			
////////			
////////			
////////			if(isSearch) {
////////				title();
////////				System.out.println(sb.toString());
////////			}
////////			else {
////////				System.out.println("[검색결과 연령대 "+input_gender+"인 구직자는 없습니다]\n");
////////			}
////////			
////////		}// end of if~else----------------------------------
////////		
////////	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
////////	
////////	}
////////
//////package my.day12.a.capsulation;
//////
//////import java.util.Scanner;
//////
//////import my.util.MyUtil;
//////
//////public class Ctrl_gujikja {
//////
//////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
//////	void main_menu() {
//////		System.out.println("\n === 메인메뉴 ===\n"
//////				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
//////		System.out.print("▷ 메뉴번호 선택 : ");
//////	}// end of void main_menu()--------------------------- 
//////
//////	
//////	// == 구직자(Gujikja) 신규 회원가입시
//////	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
//////	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
//////	void register(Scanner sc, Gujikja[] gu_arr) {
//////		
//////		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//////			
//////			String passwd;
//////			String name;
//////			String jubun;
//////		
//////			Gujikja gu = new Gujikja();
//////			
//////			do {
//////			System.out.println("1. 아이디 :");
//////			String userid = sc.nextLine(); //null이 될 수 없음
//////			
//////			// == 중복 아이디 검사하기 시작= =
//////			for (int i = 0; i < Gujikja.count; i++) {
//////				
//////				if(userid.equals(gu_arr[i].getUserid()) ) //내가 입력한 userid와 같냐는 뜻이다
//////					System.out.println(">> 이미 사용중인 아이디이므로 다른 아이디값을 입력하세요!!\n");
//////					continue outer;
//////			}
//////			
//////			} // end of for 
//////			//== 중복 아이디 검사 끝
//////			gu.setUserid(userid); // 계속 null 값을 유지
//////			
//////			
//////			} while(!(gu.getUserid() != null)); 
//////			
//////			do {
//////				System.out.println("2. 비밀번호 :");
//////				String passwd1 = sc.nextLine();
//////				
//////				gu.setPasswd(); // 계속 null 값을 유지
//////				} while(!(gu.getPasswd() != null));
//////			
//////			
//////			//field 생성
////////			String userid;
////////			String passwd;
////////			String name;
////////			String jubun;
////////			
////////			boolean isUse_userid;
////////			do {
////////				isUse_userid = true; // 초기화 
////////				
////////				System.out.print("1.아이디 : ");
////////				userid = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!! 
////////				                        // "" 또는 "        " 공백만으로는 입력불가!!
////////				
////////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
////////			//	if(userid.trim().isEmpty()) // JDK 1.8
////////				if(userid.isBlank()) {	    // JDK 11
////////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
////////					isUse_userid = false;
////////				}
////////				else {
////////					
////////					for(int i=0; i<Gujikja.count; i++) {
////////						if( userid.equals(gu_arr[i].getUserid()) ) { 
////////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
////////							isUse_userid = false;
////////							break;
////////						}
////////					}// end of for---------------------
////////				}
////////			} while(!isUse_userid);
////////			// end of do~while--------------------------------
////////			
////////			
//////		// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
////////		boolean isUse_passwd;
////////		do {
////////				isUse_passwd = true; // 초기화
////////				
////////				System.out.print("2.비밀번호 : ");
////////			passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
////////			
////////		if( !MyUtil.isCheckPasswd(passwd) ) {
////////				System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n"); 
////////				isUse_passwd = false;
////////			}
////////			
//////////		} while (!isUse_passwd);
////////			// end of do~while--------------------------------
////////			
////////			
////////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
//////			boolean isUse_name;
//////			do {
//////				isUse_name = true; // 초기화
//////				
//////				System.out.print("3.성명 : ");
//////				name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
//////				                      // "강감찬"
//////				
//////				if(name.isBlank()) {
//////					isUse_name = false;
//////				}
//////				else {
//////					char[] ch_arr = name.toCharArray();
//////					
//////					if(2 <= ch_arr.length && ch_arr.length <= 6) {
//////						
//////						for(int i=0; i<ch_arr.length; i++) {
//////							if( !('가' <= ch_arr[i] && ch_arr[i] <= '힣') ) {
//////								isUse_name = false;
//////								break;
//////							}
//////						}// end of for---------------------
//////						
//////				}
//////					else {
//////						isUse_name = false;
//////					}
//////				}
//////								
//////				if(!isUse_name) {
//////				   System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
//////				}
//////				
//////			} while (!isUse_name);
//////			// end of do~while--------------------------------
//////			
//////			
//////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
//////			boolean isUse_jubun;
//////			do {
//////				isUse_jubun = true; // 초기화
//////				
//////				System.out.print("4.주민번호 : ");
//////				jubun = sc.nextLine(); // "9610021" "9610022"  정상
//////				                       // "0010023" "0010024"  정상
//////				                       // "9604311" "9604312"  "0004313"  "0004314" 비정상
//////				                       // "9610025" "0010026"  비정상
//////				
//////			
//////				if( !MyUtil.isCheckJubun(jubun) ) {
//////					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n"); 
//////					isUse_jubun = false;
//////				}
//////				
//////			} while (!isUse_jubun);
//////			// end of do~while--------------------------------
//////			
//////			
//////			Gujikja gu = new Gujikja();
//////			gu.userid = userid;
//////			gu.passwd = passwd;
//////			gu.name = name;
//////			gu.jubun = jubun;
//////			
//////			gu_arr[Gujikja.count++] = gu;
//////			
//////			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
//////			
//////		}
//////		else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
//////			System.out.println(">> 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
//////		}
//////		
//////	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
//////
//////
//////	// === 구직자 모두보기 메소드 ===
//////	void view_all_info(Gujikja[] gu_arr) {
//////		
//////		/*
//////		    -----------------------------------------------------------------------------
//////		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
//////		    -----------------------------------------------------------------------------
//////		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
//////		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
//////		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
//////		    -----------------------------------------------------------------------------  
//////		 */
//////		
//////		if(Gujikja.count == 0) {
//////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////		}
//////		
//////		else {
//////			title();
//////			StringBuilder sb = new StringBuilder();
//////			
//////			for(int i=0; i<Gujikja.count; i++) {
//////				sb.append(gu_arr[i].getInfo()+"\n");   
//////			}// end of for-------------------------
//////			
//////			System.out.println(sb.toString()); 
//////		}
//////		
//////	}// end of void view_all_info(Gujikja[] gu_arr)------------------
//////	
//////	
//////	void title() {
//////		System.out.println("-".repeat(70)+"\n"
//////				         + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
//////				         + "-".repeat(70));
//////	}// end of void title()----------------------------
//////
//////
//////	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
//////	void search_menu(Scanner sc, Gujikja[] gu_arr) {
//////		
//////		String str_menuno = "";
//////		do {
//////			System.out.println("\n === 검색메뉴 ===\n"
//////			                + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
//////			System.out.print("▷ 검색메뉴번호 선택 : ");
//////			
//////			str_menuno = sc.nextLine();
//////			
//////			switch (str_menuno) {
//////				case "1": // 연령대검색
//////					search_ageLine(sc, gu_arr);
//////					break;
//////					
//////				case "2": // 성별검색
//////					search_gender(sc, gu_arr);
//////					break;
//////					
//////				case "3": // 연령대 및 성별 검색하기
//////					search_ageLine_gender(sc, gu_arr);
//////					break;
//////					
//////				case "4": // 메인메뉴로 돌아가기
//////					
//////					break;					
//////	
//////				default:
//////					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
//////					break;
//////			}// end of switch (key)--------------------------
//////			
//////			
//////		} while(!"4".equals(str_menuno));
//////		// end of do~while------------------------
//////		
//////	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
//////	
//////
//////
//////	// == 연령대 검색해주는 메소드 == //
//////	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
//////		
//////		if(Gujikja.count == 0) { // 구직자가 없는 경우
//////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////		}
//////		
//////		else { // 구직자가 존재하는 경우
//////		
//////			String str_ageLine = "";
//////			boolean isUse_ageLine = false;
//////			
//////			do {
//////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
//////				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
//////			                                         // "25" "강아지" "-20" --> 비정상
//////				
//////				switch (str_ageLine) {
//////					case "0": 
//////					case "10": 
//////					case "20": 
//////					case "30": 
//////					case "40": 
//////					case "50": 
//////					case "60":
//////					case "70":
//////					case "80":
//////						isUse_ageLine = true;
//////						break;
//////		
//////					default:
//////						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
//////						break;
//////				}// end of switch (str_ageLine)-----------------------
//////				
//////			} while(!isUse_ageLine);
//////			// end of do~while------------------------
//////			
//////			
//////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
//////			StringBuilder sb = new StringBuilder();
//////			boolean isSearch = false;
//////			
//////			for(int i=0; i<Gujikja.count; i++) {
//////				int ageLine = gu_arr[i].getAge()/10*10;
//////				//                  나이/10*10
//////				//                  26/10*10 ==> 20
//////                //                  23/10*10 ==> 20
//////				
//////				if(Integer.parseInt(str_ageLine) == ageLine) {
//////					isSearch = true;
//////					sb.append(gu_arr[i].getInfo()+"\n");
//////				}
//////				
//////			}// end of for--------------------------
//////			
//////			if(isSearch) {
//////				title();
//////				System.out.println(sb.toString());
//////			}
//////			else {
//////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
//////			}
//////			
//////		}// end of if~else----------------------------------
//////		
//////	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------
//////
//////	
//////	// == 성별 검색해주는 메소드 == //
//////	void search_gender(Scanner sc, Gujikja[] gu_arr) {
//////		
//////		if(Gujikja.count == 0) { // 구직자가 없는 경우
//////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////		}
//////		
//////		else { // 구직자가 존재하는 경우
//////			
//////			String input_gender = "";
//////			boolean isUse_input_gender = false;			
//////			do {
//////				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
//////				input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
//////			                                   // ""  "      " "강아지" --> 비정상
//////				
//////				switch (input_gender.trim()) {
//////					case "남": 
//////					case "여": 
//////						isUse_input_gender = true;
//////						input_gender = input_gender.trim();
//////						break;
//////		
//////					default:
//////						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
//////						break;
//////				}// end of switch (str_ageLine)-----------------------
//////				
//////			} while(!isUse_input_gender);
//////			// end of do~while------------------------
//////			
//////			
//////			// == 입력받은 성별에 해당하는 구직자 찾기 == //
//////			StringBuilder sb = new StringBuilder();
//////			boolean isSearch = false;
//////			
//////			for(int i=0; i<Gujikja.count; i++) {
//////				
//////				 if( input_gender.equals(gu_arr[i].getGender()) ) {
//////					 isSearch = true;
//////					 sb.append( gu_arr[i].getInfo() + "\n");
//////				 }
//////				
//////			}// end of for--------------------------
//////			
//////			if(isSearch) {
//////				title();
//////				System.out.println(sb.toString());
//////			}
//////			else {
//////				System.out.println("[검색결과 성별 "+ input_gender +"자 구직자는 없습니다]\n");
//////			}
//////			
//////		}// end of if~else----------------------------------
//////		
//////	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
//////
//////	
//////	// == 연령대 및 성별 검색해주는 메소드 == //
//////	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
//////		
//////		if(Gujikja.count == 0) { // 구직자가 없는 경우
//////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//////			return;
//////		}
//////		
//////		else { // 구직자가 존재하는 경우
//////		
//////			String str_ageLine = "";
//////			boolean isUse_ageLine = false;
//////			
//////			do {
//////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
//////				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
//////			                                  // "25" "강아지" "-20" --> 비정상
//////				
//////				switch (str_ageLine) {
//////					case "0": 
//////					case "10": 
//////					case "20": 
//////					case "30": 
//////					case "40": 
//////					case "50": 
//////					case "60":
//////					case "70":
//////					case "80":
//////						isUse_ageLine = true;
//////						break;
//////		
//////					default:
//////						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
//////						break;
//////				}// end of switch (str_ageLine)-----------------------
//////				
//////			} while(!isUse_ageLine);
//////			// end of do~while------------------------
//////			
//////			
//////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
//////			boolean is_ageline_search = false;
//////			
//////			for(int i=0; i<Gujikja.count; i++) {
//////				int ageLine = gu_arr[i].getAge()/10*10;
//////				//                  나이/10*10
//////				//                  26/10*10 ==> 20
//////                //                  23/10*10 ==> 20
//////				
//////				if(Integer.parseInt(str_ageLine) == ageLine) {
//////					is_ageline_search = true;
//////					break;
//////				}
//////				
//////			}// end of for--------------------------
//////			
//////			if(!is_ageline_search) { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우 
//////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n"); 
//////				return; // 해당 메소드는 search_ageLine_gender이다
//////			}
//////
//////			else { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하는 경우
//////				
//////				// === !! 성별 검색 시작 !! === //
//////				String input_gender = "";
//////				boolean isUse_input_gender = false;			
//////				do {
//////					System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
//////					input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
//////				                                   // ""  "      " "강아지" --> 비정상
//////					
//////					switch (input_gender.trim()) {
//////						case "남": 
//////						case "여": 
//////							isUse_input_gender = true;
//////							input_gender = input_gender.trim();
//////							break;
//////			
//////						default:
//////							System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
//////							break;
//////					}// end of switch (str_ageLine)-----------------------
//////					
//////				} while(!isUse_input_gender);
//////				// end of do~while------------------------
//////				
//////				
//////				// == 입력받은 연령대 및 성별에 해당하는 구직자 찾기 == //
//////				StringBuilder sb = new StringBuilder();
//////				boolean is_ageline_gender_Search = false;
//////				
//////				for(int i=0; i<Gujikja.count; i++) {
//////					
//////					 if( Integer.parseInt(str_ageLine) == gu_arr[i].getAge()/10*10   
//////						&& input_gender.equals(gu_arr[i].getGender()) ) {
//////						 
//////						 is_ageline_gender_Search = true;
//////						 sb.append( gu_arr[i].getInfo() + "\n");
//////					 }
//////					
//////				}// end of for--------------------------
//////				
//////				if(is_ageline_gender_Search) {
//////					title();
//////					System.out.println(sb.toString());
//////				}
//////				else {
//////					System.out.println("[검색결과 연령대가 "+ str_ageLine + "대인 "+ input_gender +"자 구직자는 없습니다]\n"); 
//////				
//////				}
//////				// === !! 성별 검색 끝 !! === //
//////			}
//////			
//////			
//////		}// end of if~else----------------------------------
//////		
//////	}// end of void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr)------	
//////	
//////}
////package my.day13.a.inheritance;
////
////import java.util.Scanner;
////
////public class Ctrl_gujikja {
////
////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
////	void main_menu() {
////		System.out.println("\n === 메인메뉴 ===\n"
////				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
////		System.out.print("▷ 메뉴번호 선택 : ");
////	}// end of void main_menu()--------------------------- 
////
////	
////	// == 구직자(Gujikja) 신규 회원가입시
////	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
////	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
////	void register(Scanner sc, Gujikja[] gu_arr) {
////		
////		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
////		    
////			Gujikja gu = new Gujikja();
////			
////			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
////			outer:
////			do {
////				System.out.print("1.아이디 : ");
////				String userid = sc.nextLine();
////				
////				// == 중복 아이디 검사하기 시작 == //
////				for(int i=0; i<Gujikja.count; i++) {
////					
////					if( userid.equals(gu_arr[i].getUserid()) ) {
////						System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
////						continue outer;
////					}
////					
////				}// end of for-------------------
////				
////				// == 중복 아이디 검사하기 끝 == //
////				
////				gu.setUserid(userid);
////				
////			} while(!(gu.getUserid() != null));
////			
////			
////			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
////			do {
////				System.out.print("2.비밀번호 : ");
////				String passwd = sc.nextLine();
////				
////				gu.setPasswd(passwd);
////				
////			} while(!(gu.getPasswd() != null));
////			
////			
////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////		    // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
////			do {
////				System.out.print("3.성명 : ");
////				String name = sc.nextLine();
////				
////				gu.setName(name);
////				
////			} while(!(gu.getName() != null));
////			
////			
////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
////			do {
////				System.out.print("4.주민번호 : ");
////				String jubun = sc.nextLine();
////				
////				gu.setJubun(jubun);
////				
////			} while(!(gu.getJubun() != null));
////			
////			////////////////////////////////////////////////
////			
////			gu_arr[Gujikja.count++] = gu;
////			
////			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
////			
////		}
////		else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
////			System.out.println(">> 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
////		}
////		
////	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
////
////
////	// === 구직자 모두보기 메소드 ===
////	void view_all_info(Gujikja[] gu_arr) {
////		
////		/*
////		    -----------------------------------------------------------------------------
////		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
////		    -----------------------------------------------------------------------------
////		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
////		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
////		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
////		    -----------------------------------------------------------------------------  
////		 */
////		
////		if(Gujikja.count == 0) {
////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////		}
////		
////		else {
////			title();
////			StringBuilder sb = new StringBuilder();
////			
////			for(int i=0; i<Gujikja.count; i++) {
////				sb.append(gu_arr[i].getInfo()+"\n");   
////			}// end of for-------------------------
////			
////			System.out.println(sb.toString()); 
////		}
////		
////	}// end of void view_all_info(Gujikja[] gu_arr)------------------
////	
////	
////	void title() {
////		System.out.println("-".repeat(70)+"\n"
////				         + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
////				         + "-".repeat(70));
////	}// end of void title()----------------------------
////
////
////	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
////	void search_menu(Scanner sc, Gujikja[] gu_arr) {
////		
////		String str_menuno = "";
////		do {
////			System.out.println("\n === 검색메뉴 ===\n"
////			                + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
////			System.out.print("▷ 검색메뉴번호 선택 : ");
////			
////			str_menuno = sc.nextLine();
////			
////			switch (str_menuno) {
////				case "1": // 연령대검색
////					search_ageLine(sc, gu_arr);
////					break;
////					
////				case "2": // 성별검색
////					search_gender(sc, gu_arr);
////					break;
////					
////				case "3": // 연령대 및 성별 검색하기
////					search_ageLine_gender(sc, gu_arr);
////					break;
////					
////				case "4": // 메인메뉴로 돌아가기
////					
////					break;					
////	
////				default:
////					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
////					break;
////			}// end of switch (key)--------------------------
////			
////			
////		} while(!"4".equals(str_menuno));
////		// end of do~while------------------------
////		
////	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
////	
////
////
////	// == 연령대 검색해주는 메소드 == //
////	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
////		
////		if(Gujikja.count == 0) { // 구직자가 없는 경우
////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////		}
////		
////		else { // 구직자가 존재하는 경우
////		
////			String str_ageLine = "";
////			boolean isUse_ageLine = false;
////			
////			do {
////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
////				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
////			                                         // "25" "강아지" "-20" --> 비정상
////				
////				switch (str_ageLine) {
////					case "0": 
////					case "10": 
////					case "20": 
////					case "30": 
////					case "40": 
////					case "50": 
////					case "60":
////					case "70":
////					case "80":
////						isUse_ageLine = true;
////						break;
////		
////					default:
////						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
////						break;
////				}// end of switch (str_ageLine)-----------------------
////				
////			} while(!isUse_ageLine);
////			// end of do~while------------------------
////			
////			
////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
////			StringBuilder sb = new StringBuilder();
////			boolean isSearch = false;
////			
////			for(int i=0; i<Gujikja.count; i++) {
////				int ageLine = gu_arr[i].getAge()/10*10;
////				//                  나이/10*10
////				//                  26/10*10 ==> 20
////                //                  23/10*10 ==> 20
////				
////				if(Integer.parseInt(str_ageLine) == ageLine) {
////					isSearch = true;
////					sb.append(gu_arr[i].getInfo()+"\n");
////				}
////				
////			}// end of for--------------------------
////			
////			if(isSearch) {
////				title();
////				System.out.println(sb.toString());
////			}
////			else {
////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
////			}
////			
////		}// end of if~else----------------------------------
////		
////	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------
////
////	
////	// == 성별 검색해주는 메소드 == //
////	void search_gender(Scanner sc, Gujikja[] gu_arr) {
////		
////		if(Gujikja.count == 0) { // 구직자가 없는 경우
////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////		}
////		
////		else { // 구직자가 존재하는 경우
////			
////			String input_gender = "";
////			boolean isUse_input_gender = false;			
////			do {
////				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
////				input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
////			                                   // ""  "      " "강아지" --> 비정상
////				
////				switch (input_gender.trim()) {
////					case "남": 
////					case "여": 
////						isUse_input_gender = true;
////						input_gender = input_gender.trim();
////						break;
////		
////					default:
////						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
////						break;
////				}// end of switch (str_ageLine)-----------------------
////				
////			} while(!isUse_input_gender);
////			// end of do~while------------------------
////			
////			
////			// == 입력받은 성별에 해당하는 구직자 찾기 == //
////			StringBuilder sb = new StringBuilder();
////			boolean isSearch = false;
////			
////			for(int i=0; i<Gujikja.count; i++) {
////				
////				 if( input_gender.equals(gu_arr[i].getGender()) ) {
////					 isSearch = true;
////					 sb.append( gu_arr[i].getInfo() + "\n");
////				 }
////				
////			}// end of for--------------------------
////			
////			if(isSearch) {
////				title();
////				System.out.println(sb.toString());
////			}
////			else {
////				System.out.println("[검색결과 성별 "+ input_gender +"자 구직자는 없습니다]\n");
////			}
////			
////		}// end of if~else----------------------------------
////		
////	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
////
////	
////	// == 연령대 및 성별 검색해주는 메소드 == //
////	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
////		
////		if(Gujikja.count == 0) { // 구직자가 없는 경우
////			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
////			return; // 해당 메소드(지금은 search_ageLine_gender())를 종료하는 것이다.
////		}
////		
////		else { // 구직자가 존재하는 경우
////		
////			String str_ageLine = "";
////			boolean isUse_ageLine = false;
////			
////			do {
////				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
////				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
////			                                  // "25" "강아지" "-20" --> 비정상
////				
////				switch (str_ageLine) {
////					case "0": 
////					case "10": 
////					case "20": 
////					case "30": 
////					case "40": 
////					case "50": 
////					case "60":
////					case "70":
////					case "80":
////						isUse_ageLine = true;
////						break;
////		
////					default:
////						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
////						break;
////				}// end of switch (str_ageLine)-----------------------
////				
////			} while(!isUse_ageLine);
////			// end of do~while------------------------
////			
////			
////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
////			boolean is_ageline_search = false;
////			
////			for(int i=0; i<Gujikja.count; i++) {
////				int ageLine = gu_arr[i].getAge()/10*10;
////				//                  나이/10*10
////				//                  26/10*10 ==> 20
////                //                  23/10*10 ==> 20
////				
////				if(Integer.parseInt(str_ageLine) == ageLine) {
////					is_ageline_search = true;
////					break;
////				}
////				
////			}// end of for--------------------------
////			
////			if(!is_ageline_search) { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우 
////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
////				return; // 해당 메소드(지금은 search_ageLine_gender())를 종료하는 것이다.
////			}
////
////			else { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하는 경우
////				
////				// === !! 성별 검색 시작 !! === //
////				String input_gender = "";
////				boolean isUse_input_gender = false;			
////				do {
////					System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
////					input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
////				                                   // ""  "      " "강아지" --> 비정상
////					
////					switch (input_gender.trim()) {
////						case "남": 
////						case "여": 
////							isUse_input_gender = true;
////							input_gender = input_gender.trim();
////							break;
////			
////						default:
////							System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
////							break;
////					}// end of switch (str_ageLine)-----------------------
////					
////				} while(!isUse_input_gender);
////				// end of do~while------------------------
////				
////				
////				// == 입력받은 연령대 및 성별에 해당하는 구직자 찾기 == //
////				StringBuilder sb = new StringBuilder();
////				boolean is_ageline_gender_Search = false;
////				
////				for(int i=0; i<Gujikja.count; i++) {
////					
////					 if( Integer.parseInt(str_ageLine) == gu_arr[i].getAge()/10*10   
////						&& input_gender.equals(gu_arr[i].getGender()) ) {
////						 
////						 is_ageline_gender_Search = true;
////						 sb.append( gu_arr[i].getInfo() + "\n");
////					 }
////					
////				}// end of for--------------------------
////				
////				if(is_ageline_gender_Search) {
////					title();
////					System.out.println(sb.toString());
////				}
////				else {
////					System.out.println("[검색결과 연령대가 "+ str_ageLine + "대인 "+ input_gender +"자 구직자는 없습니다]\n"); 
////				}
////				// === !! 성별 검색 끝 !! === //
////			}
////			
////		}// end of if~else----------------------------------
////		
////	}// end of void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr)------	
////	
////}
//package my.day13.a.inheritance;
//
//import java.util.Scanner;
//
//public class Ctrl_gujikja extends Ctrl_Common_ {
//
////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
////	void main_menu() {
////		System.out.println("\n === 메인메뉴 ===\n"
////				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
////		System.out.print("▷ 메뉴번호 선택 : ");
////	}// end of void main_menu()--------------------------- 
//
//	
//	// == 구직자(Gujikja) 신규 회원가입시
//	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
//	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
//	void register(Scanner sc, Gujikja[] gu_arr) {
//		
//		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//		    
//			Gujikja gu = new Gujikja();
//			
//			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
//			outer:
//			do {
//				System.out.print("1.아이디 : ");
//				String userid = sc.nextLine();
//				
//				// == 중복 아이디 검사하기 시작 == //
//				for(int i=0; i<Gujikja.count; i++) {
//					
//					if( userid.equals(gu_arr[i].getId()) ) {
//						System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
//						continue outer;
//					}
//					
//				}// end of for-------------------
//				// == 중복 아이디 검사하기 끝 == //
//				
//				gu.setId(userid);
//				
//			} while(!(gu.getId() != null));
//			
//			
//			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
//			do {
//				System.out.print("2.비밀번호 : ");
//				String passwd = sc.nextLine();
//				
//				gu.setPasswd(passwd);
//				
//			} while(!(gu.getPasswd() != null));
//			
//			
//			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
//		    // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
//			do {
//				System.out.print("3.성명 : ");
//				String name = sc.nextLine();
//				
//				gu.setName(name);
//				
//			} while(!(gu.getName() != null));
//			
//			
//			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
//			do {
//				System.out.print("4.주민번호 : ");
//				String jubun = sc.nextLine();
//				
//				gu.setJubun(jubun);
//				
//			} while(!(gu.getJubun() != null));
//			
//			////////////////////////////////////////////////
//			
//			gu_arr[Gujikja.count++] = gu;
//			
//			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
//			
//		}
//		else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
//			System.out.println(">> 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
//		}
//		
//	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
//
//
//	// === 구직자 모두보기 메소드 ===
//	void view_all_info(Gujikja[] gu_arr) {
//		
//		/*
//		    -----------------------------------------------------------------------------
//		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
//		    -----------------------------------------------------------------------------
//		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
//		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
//		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
//		    -----------------------------------------------------------------------------  
//		 */
//		
//		if(Gujikja.count == 0) {
//			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//		}
//		
//		else {
//			title();
//			StringBuilder sb = new StringBuilder();
//			
//			for(int i=0; i<Gujikja.count; i++) {
//				sb.append(gu_arr[i].getInfo()+"\n");   
//			}// end of for-------------------------
//			
//			System.out.println(sb.toString()); 
//		}
//		
//	}// end of void view_all_info(Gujikja[] gu_arr)------------------
//	
//	
//	void title() {
//		System.out.println("-".repeat(70)+"\n"
//				         + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
//				         + "-".repeat(70));
//	}// end of void title()----------------------------
//
//
//	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
//	void search_menu(Scanner sc, Gujikja[] gu_arr) {
//		
//		String str_menuno = "";
//		do {
//			System.out.println("\n === 검색메뉴 ===\n"
//			                + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
//			System.out.print("▷ 검색메뉴번호 선택 : ");
//			
//			str_menuno = sc.nextLine();
//			
//			switch (str_menuno) {
//				case "1": // 연령대검색
//					search_ageLine(sc, gu_arr);
//					break;
//					
//				case "2": // 성별검색
//					search_gender(sc, gu_arr);
//					break;
//					
//				case "3": // 연령대 및 성별 검색하기
//					search_ageLine_gender(sc, gu_arr);
//					break;
//					
//				case "4": // 메인메뉴로 돌아가기
//					
//					break;					
//	
//				default:
//					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
//					break;
//			}// end of switch (key)--------------------------
//			
//			
//		} while(!"4".equals(str_menuno));
//		// end of do~while------------------------
//		
//	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
//	
//
//
//	// == 연령대 검색해주는 메소드 == //
//	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
//		
//		if(Gujikja.count == 0) { // 구직자가 없는 경우
//			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//		}
//		
//		else { // 구직자가 존재하는 경우
//		
//			String str_ageLine = "";
//			boolean isUse_ageLine = false;
//			
//			do {
//				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
//				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
//			                                         // "25" "강아지" "-20" --> 비정상
//				
//				switch (str_ageLine) {
//					case "0": 
//					case "10": 
//					case "20": 
//					case "30": 
//					case "40": 
//					case "50": 
//					case "60":
//					case "70":
//					case "80":
//						isUse_ageLine = true;
//						break;
//		
//					default:
//						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
//						break;
//				}// end of switch (str_ageLine)-----------------------
//				
//			} while(!isUse_ageLine);
//			// end of do~while------------------------
//			
//			
//			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
//			StringBuilder sb = new StringBuilder();
//			boolean isSearch = false;
//			
//			for(int i=0; i<Gujikja.count; i++) {
//				int ageLine = gu_arr[i].getAge()/10*10;
//				//                  나이/10*10
//				//                  26/10*10 ==> 20
//                //                  23/10*10 ==> 20
//				
//				if(Integer.parseInt(str_ageLine) == ageLine) {
//					isSearch = true;
//					sb.append(gu_arr[i].getInfo()+"\n");
//				}
//				
//			}// end of for--------------------------
//			
//			if(isSearch) {
//				title();
//				System.out.println(sb.toString());
//			}
//			else {
//				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
//			}
//			
//		}// end of if~else----------------------------------
//		
//	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------
//
//	
//	// == 성별 검색해주는 메소드 == //
//	void search_gender(Scanner sc, Gujikja[] gu_arr) {
//		
//		if(Gujikja.count == 0) { // 구직자가 없는 경우
//			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//		}
//		
//		else { // 구직자가 존재하는 경우
//			
//			String input_gender = "";
//			boolean isUse_input_gender = false;			
//			do {
//				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
//				input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
//			                                   // ""  "      " "강아지" --> 비정상
//				
//				switch (input_gender.trim()) {
//					case "남": 
//					case "여": 
//						isUse_input_gender = true;
//						input_gender = input_gender.trim();
//						break;
//		
//					default:
//						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
//						break;
//				}// end of switch (str_ageLine)-----------------------
//				
//			} while(!isUse_input_gender);
//			// end of do~while------------------------
//			
//			
//			// == 입력받은 성별에 해당하는 구직자 찾기 == //
//			StringBuilder sb = new StringBuilder();
//			boolean isSearch = false;
//			
//			for(int i=0; i<Gujikja.count; i++) {
//				
//				 if( input_gender.equals(gu_arr[i].getGender()) ) {
//					 isSearch = true;
//					 sb.append( gu_arr[i].getInfo() + "\n");
//				 }
//				
//			}// end of for--------------------------
//			
//			if(isSearch) {
//				title();
//				System.out.println(sb.toString());
//			}
//			else {
//				System.out.println("[검색결과 성별 "+ input_gender +"자 구직자는 없습니다]\n");
//			}
//			
//		}// end of if~else----------------------------------
//		
//	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
//
//	
//	// == 연령대 및 성별 검색해주는 메소드 == //
//	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
//		
//		if(Gujikja.count == 0) { // 구직자가 없는 경우
//			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
//			return; // 해당 메소드(지금은 search_ageLine_gender())를 종료하는 것이다.
//		}
//		
//		else { // 구직자가 존재하는 경우
//		
//			String str_ageLine = "";
//			boolean isUse_ageLine = false;
//			
//			do {
//				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
//				str_ageLine = sc.nextLine();  // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
//			                                  // "25" "강아지" "-20" --> 비정상
//				
//				switch (str_ageLine) {
//					case "0": 
//					case "10": 
//					case "20": 
//					case "30": 
//					case "40": 
//					case "50": 
//					case "60":
//					case "70":
//					case "80":
//						isUse_ageLine = true;
//						break;
//		
//					default:
//						System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
//						break;
//				}// end of switch (str_ageLine)-----------------------
//				
//			} while(!isUse_ageLine);
//			// end of do~while------------------------
//			
//			
//			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
//			boolean is_ageline_search = false;
//			
//			for(int i=0; i<Gujikja.count; i++) {
//				int ageLine = gu_arr[i].getAge()/10*10;
//				//                  나이/10*10
//				//                  26/10*10 ==> 20
//                //                  23/10*10 ==> 20
//				
//				if(Integer.parseInt(str_ageLine) == ageLine) {
//					is_ageline_search = true;
//					break;
//				}
//				
//			}// end of for--------------------------
//			
//			if(!is_ageline_search) { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우 
//				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
//				return; // 해당 메소드(지금은 search_ageLine_gender())를 종료하는 것이다.
//			}
//
//			else { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하는 경우
//				
//				// === !! 성별 검색 시작 !! === //
//				String input_gender = "";
//				boolean isUse_input_gender = false;			
//				do {
//					System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
//					input_gender = sc.nextLine();  // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
//				                                   // ""  "      " "강아지" --> 비정상
//					
//					switch (input_gender.trim()) {
//						case "남": 
//						case "여": 
//							isUse_input_gender = true;
//							input_gender = input_gender.trim();
//							break;
//			
//						default:
//							System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
//							break;
//					}// end of switch (str_ageLine)-----------------------
//					
//				} while(!isUse_input_gender);
//				// end of do~while------------------------
//				
//				
//				// == 입력받은 연령대 및 성별에 해당하는 구직자 찾기 == //
//				StringBuilder sb = new StringBuilder();
//				boolean is_ageline_gender_Search = false;
//				
//				for(int i=0; i<Gujikja.count; i++) {
//					
//					 if( Integer.parseInt(str_ageLine) == gu_arr[i].getAge()/10*10   
//						&& input_gender.equals(gu_arr[i].getGender()) ) {
//						 
//						 is_ageline_gender_Search = true;
//						 sb.append( gu_arr[i].getInfo() + "\n");
//					 }
//					
//				}// end of for--------------------------
//				
//				if(is_ageline_gender_Search) {
//					title();
//					System.out.println(sb.toString());
//				}
//				else {
//					System.out.println("[검색결과 연령대가 "+ str_ageLine + "대인 "+ input_gender +"자 구직자는 없습니다]\n"); 
//				}
//				// === !! 성별 검색 끝 !! === //
//			}
//			
//		}// end of if~else----------------------------------
//		
//	}// end of void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr)------	
//
//	// 구직자 로그인 메소드 생성하기
//	public Gujikja login(Scanner sc, Gujikja[] gu_arr) {
//	System.out.println("> 구직자 ID : ");
//	String id = sc.nextLine();
//	
//	System.out.println("> 비밀번호 : ");
//	String passwd = sc.nextLine();
//	
//	for(int i = 0; i <Gujikja.count; i++) {
//		if(id.equals(gu_arr[i].getId()) && passwd.equals(gu_arr[i].getPasswd())) {
//			return gu_arr[i];
//		}
//	}// end of for------------
//		return null;
//	} // end of public Gujikja login(Scanner sc, Gujikja[] gu_arr) 
//
//	
//	
//
//	public void register(Scanner sc, Company[] cp_arr) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void gu_menu(Scanner sc, Gujikja login_gu, Company[] cp_arr) {
//		System.out.println("\n === 구직자  로그인 중)===");
//		+"1. 내 정보보기  2. 내 정보 수정 3. 모든 구인회사 조회 4. 로그아웃"
//		
//		
//		
//	}
//	
//}
package my.day13.a.inheritance;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Ctrl_gujikja extends Ctrl_common {
	
	// == 구직자(Gujikja) 신규 회원가입시
	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
	void register(Scanner sc, Gujikja[] gu_arr) {
		
		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
		    
			Gujikja gu = new Gujikja();
			
			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
			outer:
			do {
				System.out.print("1.아이디 : ");
				String id = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				for(int i=0; i<Gujikja.count; i++) {
					
					if( id.equals(gu_arr[i].getId()) ) {
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
			
			gu_arr[Gujikja.count++] = gu;
			
			System.out.println(">> 구직자 회원가입 성공 !! <<\n");
			
		}
		else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
			System.out.println(">> 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
		}
		
	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------


	// === 구직자 모두보기 메소드 ===
	void view_all_info(Gujikja[] gu_arr) {
		
		/*
		    -----------------------------------------------------------------------------
		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
		    -----------------------------------------------------------------------------
		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
		    -----------------------------------------------------------------------------  
		 */
		
		if(Gujikja.count == 0) {
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		
		else {
			title();
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<Gujikja.count; i++) {
				sb.append(gu_arr[i].getInfo()+"\n");   
			}// end of for-------------------------
			
			System.out.println(sb.toString()); 
		}
		
	}// end of void view_all_info(Gujikja[] gu_arr)------------------
	
	
	void title() {
		System.out.println("-".repeat(70)+"\n"
				         + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
				         + "-".repeat(70));
	}// end of void title()----------------------------


	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
	void search_menu(Scanner sc, Gujikja[] gu_arr) {
		
		String str_menuno = "";
		do {
			System.out.println("\n === 검색메뉴 ===\n"
			                + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
			System.out.print("▷ 검색메뉴번호 선택 : ");
			
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 연령대검색
					search_ageLine(sc, gu_arr);
					break;
					
				case "2": // 성별검색
					search_gender(sc, gu_arr);
					break;
					
				case "3": // 연령대 및 성별 검색하기
					search_ageLine_gender(sc, gu_arr);
					break;
					
				case "4": // 메인메뉴로 돌아가기
					
					break;					
	
				default:
					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
					break;
			}// end of switch (key)--------------------------
			
			
		} while(!"4".equals(str_menuno));
		// end of do~while------------------------
		
	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
	


	// == 연령대 검색해주는 메소드 == //
	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
		
		if(Gujikja.count == 0) { // 구직자가 없는 경우
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
			
			for(int i=0; i<Gujikja.count; i++) {
				int ageLine = gu_arr[i].getAge()/10*10;
				//                  나이/10*10
				//                  26/10*10 ==> 20
                //                  23/10*10 ==> 20
				
				if(Integer.parseInt(str_ageLine) == ageLine) {
					isSearch = true;
					sb.append(gu_arr[i].getInfo()+"\n");
				}
				
			}// end of for--------------------------
			
			if(isSearch) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
			}
			
		}// end of if~else----------------------------------
		
	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------

	
	// == 성별 검색해주는 메소드 == //
	void search_gender(Scanner sc, Gujikja[] gu_arr) {
		
		if(Gujikja.count == 0) { // 구직자가 없는 경우
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
			
			for(int i=0; i<Gujikja.count; i++) {
				
				 if( input_gender.equals(gu_arr[i].getGender()) ) {
					 isSearch = true;
					 sb.append( gu_arr[i].getInfo() + "\n");
				 }
				
			}// end of for--------------------------
			
			if(isSearch) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 성별 "+ input_gender +"자 구직자는 없습니다]\n");
			}
			
		}// end of if~else----------------------------------
		
	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------

	
	// == 연령대 및 성별 검색해주는 메소드 == //
	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
		
		if(Gujikja.count == 0) { // 구직자가 없는 경우
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
			
			for(int i=0; i<Gujikja.count; i++) {
				int ageLine = gu_arr[i].getAge()/10*10;
				//                  나이/10*10
				//                  26/10*10 ==> 20
                //                  23/10*10 ==> 20
				
				if(Integer.parseInt(str_ageLine) == ageLine) {
					is_ageline_search = true;
					break;
				}
				
			}// end of for--------------------------
			
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
				
				for(int i=0; i<Gujikja.count; i++) {
					
					 if( Integer.parseInt(str_ageLine) == gu_arr[i].getAge()/10*10   
						&& input_gender.equals(gu_arr[i].getGender()) ) {
						 
						 is_ageline_gender_Search = true;
						 sb.append( gu_arr[i].getInfo() + "\n");
					 }
					
				}// end of for--------------------------
				
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
		
	}// end of void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr)------	


	// == 구직자 로그인 메소드 생성하기 == // 
	public Gujikja login(Scanner sc, Gujikja[] gu_arr) {
		
		System.out.print("▷ 구직자 ID : ");
		String id = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		for(int i=0; i<Gujikja.count; i++) {
			
			if(id.equals(gu_arr[i].getId()) &&
			   passwd.equals(gu_arr[i].getPasswd()) ) {
				  return gu_arr[i];
			}
			
		}// end of for----------------------
		
		return null;
	}// end of public Gujikja login(Scanner sc, Gujikja[] gu_arr)------------


	// == 구직자 전용메뉴 메소드 생성하기 == //
	public void gu_menu(Scanner sc, Gujikja login_gu, Company[] cp_arr) {
		
		String str_menuno;
		do {
			System.out.println("\n === 구직자 전용메뉴("+ login_gu.getName() +"님 로그인중) ===\n"
			                 + "1.내정보 보기   2.내정보 수정   3.모든구인회사 조회 4.구인회사검색하기   5.로그아웃\n");  
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
					view_all_company_info(cp_arr);
					break;	
					
				case "4": // 구인회사검색하기
					search_company(sc, cp_arr);
					break;					
	
				case "5" :
					
					break;
				default:
					System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. <<\n");
					break;
			}// end of switch (str_menuno)-----------------------
			
		} while(!"5".equals(str_menuno));
		// end of do~while-----------------------------
		
		System.out.println(">> 로그아웃 되었습니다. <<\n");
		
	}// end of public void gu_menu(Scanner sc, Gujikja login_gu, Company[] cp_arr)--------------





	
	// 구인회사검색하기
		private void search_company(Scanner sc, Company[] cp_arr) {
			
			String str_menuno = "";		
			
			do {
				///////////////////////////////////////////////////////////////////
				System.out.println(">>> 구인회사 검색메뉴 <<<\n"
						         + "1. 업종검색    2.자본금검색    3.구직자메뉴로 돌아가기"); 
				System.out.print("▷ 검색메뉴번호 입력 : ");
				str_menuno = sc.nextLine();
				
				switch (str_menuno) {
					case "1": // 업종검색
						search_jobtype_company(sc, cp_arr);
						break;
						
					case "2": // 자본금검색
						search_seedmoney_company(sc, cp_arr);
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
		private void search_jobtype_company(Scanner sc, Company[] cp_arr) {
			
			System.out.print("▷ 업종명 : ");
			String job_type_name = sc.nextLine().toLowerCase();
			
			StringBuilder sb = new StringBuilder();
			boolean is_existence = false;
			for(int i=0; i<Company.count; i++) {
				if(cp_arr[i].getJob_type().toLowerCase().contains(String.join("", job_type_name.split("\\ ")))) {
					is_existence = true;
					sb.append(cp_arr[i].getInfo()+"\n");
				}
			}// end of for----------------------------
			
			if(is_existence) {
				title_company();
				System.out.println(sb.toString());
			}
			else {
				System.out.println(">> 검색하시려는 "+ job_type_name +"업종에 해당하는 회사는 없습니다.!!\n");
			}
			
		}// end of private void search_jobtype_company(Company[] cp_arr)------

		
		// 자본금검색
		private void search_seedmoney_company(Scanner sc, Company[] cp_arr) {
			
			System.out.print("▷ 자본금 : ");
			String str_search_seed_money = sc.nextLine();
			
			try {
				long search_seed_money = Long.parseLong(str_search_seed_money);
				
				StringBuilder sb = new StringBuilder();
				boolean is_existence = false;
				
				for(int i=0; i<Company.count; i++) {
					if( search_seed_money <= cp_arr[i].getSeed_money() ) {
						is_existence = true;
						sb.append(cp_arr[i].getInfo()+"\n");
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
			
		}// end of private void search_seedmoney_company(Company[] cp_arr)------


	private void view_myInfo(Gujikja login_gu) {
		System.out.println(">>> " + login_gu.getName() + "님의 정보 <<<\n"
				+ "-".repeat(40)+"\n아이디\t비밀번호\t   성명\t주민번호\n"+ "-".repeat(40));
		System.out.println(login_gu.getId() + " ".repeat(3) + login_gu.getPasswd()+" ".repeat(1)
				+ login_gu.getName() + " ".repeat(2) + login_gu.getJubun());
	
	} // 외부 클래스는 못들어옴, 보고 싶으면  gu_menu에서만 볼 수 있음
// end of private void view_myInfo(Gujikja login_gu)---------------------
	
	/*
	     
	     >>> 엄정화님의 정보 <<<
	     -------------------------------------
	       아이디    비밀번호      성명    주민번호  
	     -------------------------------------  
	       eomjh   qWer1234$   엄정화   8610202
	*/
	
	private void update_myInfo(Scanner sc, Gujikja login_gu) {
		
	view_myInfo(login_gu);
	System.out.println("\n>>[주의사항] 변경하지 않고 예전의 데이터값을 그대로 사용하시려면 그냥 엔터하세요!\\n");
	
	boolean exit_ok = false;
	
	do {
///////////////////////////////////////////////////////////////////////////////////////////		
		System.out.println("1. 비밀번호 :");
		String new_passwd = sc.nextLine(); // 기존 비밀번호인 qWer1234$을 qWer0070$으로 변경하려고 한다
										   // 기존 비밀번호인 qWer1234$을 qWer0070$으로 변경하려면 기존 암호와 동일하므로 변경이 불가합니다
											// 기존 비밀번호인 qWer1234$을 변경하기 싫다
											// 기존 비밀번호는 qWer1234$을 abcd로 변경하고자 할 때는 비밀번호 정책에 맞지 않으므로 안된다
		
		
		if(!new_passwd.equals("")) { // 입력한 비밀번호가 엔터가 아닐 경우 
			
			if(login_gu.getPasswd().equals(new_passwd)) { // 입력한 비밀번호가 기존 비밀번호와 같을 경우
				System.out.println(">> 기존암호와 동일하므로 변경이 불가합니다!!");
			}
			else {
				login_gu.setPasswd(new_passwd);	
				if(login_gu.getPasswd().equals(new_passwd)) {
					exit_ok = true;
				} 
			}
	
		} else { //입력한 비밀번호가 엔터일 경우에
				exit_ok = true;
			}
		////////////////////////////////////////
	} while(!exit_ok);
	// end of do~while
	
	exit_ok = false;
	System.out.print("2. 성명 : ");
	String new_name = sc.nextLine();  // 기존 성명인 엄정화를 엄화정으로 변경하려고 한다
										// 기존 성명인 엄정화을 엄정화로 변경하려면 기존 암호와 동일하므로 변경이 불가합니다
										// 기존 성명인 엄정화을 변경하기 싫다
										// 기존 성명인 엄JungHwa로 변경하고자 할 때는 성명 정책에 맞지 않으므로 안된다
	do {
		
		if(!new_name.equals("")) {// 입력한 성명이 엔터가 아닐 경우 
		
			if(login_gu.getName().equals(new_name)) { // 입력한 성명이 기존 성명과 같을 경우
				System.out.println(">> 기존성명과 동일하므로 변경이 불가합니다!!");
			}
			else {
				login_gu.setName(new_name);	
				if(login_gu.getName().equals(new_name)) {
					exit_ok = true;
				} 
			} 
			
		} else { //입력한 비밀번호가 엔터일 경우에
			exit_ok = true;
			}	
		
	} while(!exit_ok);
	////////////////////////////////////////////////////////////
	exit_ok = false;
	System.out.print("3. 주민번호 :");
	do {
		//////////////////////////////////
		String new_jubun = sc.nextLine();
		if(!new_jubun.equals("")) {
			if(login_gu.getJubun().equals(new_jubun)) {
				System.out.println(">>");
			} else {
			login_gu.setName(new_name);	
			if(login_gu.getName().equals(new_name)) {
				exit_ok = true;
			} 
		}
			
	} else {//입렵한 주민번호가 엔터일 경우
			exit_ok = true;
	}
	} while(!exit_ok);
	
	}	
	
	
	// 모든구인회사 조회
		private void view_all_company_info(Company[] cp_arr) {
			if(Company.count == 0) { 
			System.out.println("\n>> 구인회사로 등록된 회사가 한개도 없습니다.\n");
			}
			else {
				title_company(); // 회사 정보를 보여주는 타이틀
				StringBuilder sb = new StringBuilder();
				for(int i =0; i<Company.count; i++) {
					sb.append(cp_arr[i].getInfo()+"\n");
								//cp_arr[i].getInfo()
				}// end for
				System.out.println(sb.toString());
			}
		} // end of private void view_all_company_info(Company[] cp_arr) --


		private void title_company() {
			System.out.println("=".repeat(70));
			System.out.println("회사명 업종 사업자등록번호 자본금" );
			System.out.println("=".repeat(70));
		}// end of void title_company

		// == 구인회사 전용메뉴 ==
		   public void cp_menu(Scanner sc, Company login_cp, Gujikja[] gu_arr) {

		      String str_menuno;
		      do {
		         System.out.println("=== 구인회사 전용메뉴(삼성 기업 로그인중) ===\n"
		                        + "1.우리회사정보 보기     2.우리회사정보 수정        3.모든구직자 조회     4.구직자 성별검색\n" 
		                        + "5.구직자 연령대검색     6.구직자 연령대 및 성별검색    7.로그아웃 8. 모집공고지원자 조회 9. 로그아웃");
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
		               view_all_info(gu_arr);
		               break;
		               
		            case "4": // 구직자 성별검색
		               search_gender(sc, gu_arr);
		               break;
		               
		            case "5": // 구직자 연령대검색
		               search_ageLine(sc, gu_arr); 
		               break;
		               
		            case "6": // 구직자 연령대 및 성별검색
		               search_ageLine_gender(sc, gu_arr);
		               break;
		               
		            case "7": // 사원모집공고	
		               login_cp = null;
		               break;               
		   
		            case "8": // 모집공고 지원
		            	break;
		            	
		            case "9": // 로그아웃
		            default:
		               System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. <<\n"); 
		               break;
		         }// end of switch (str_menuno)------------------------
		         
		      } while(!"7".equals(str_menuno));
		      
		   }// end of public void cp_menu(Scanner sc, Company login_cp, Gujikja[] gu_arr)--------------------
		// == 우리회사정보 보기 ==
		   private void view_myInfo(Company login_cp) {
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
		
} //end of class

