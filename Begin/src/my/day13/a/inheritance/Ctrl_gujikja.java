///////*///*
//////// * package my.day11.c.abstraction;
//////// * 
//////// * public class Ctrl_gujikja {
//////// * 
//////// * public static void main(String[] args) { // 여기서 메뉴를 만든다 // == 메인 메뉴를 보여주는 메소드
//////// * 생성하기 == // void main_menu() { System.out.println("\n=== 메인메뉴====\n"
//////// * +"1.구직자 회원가입 2. 구직자 모두보기 3. 검색하기 4. 프로그램 종료 \n" );
//////// * System.out.println("ㅁ 메뉴번호 선택 : "); }// end of void main
//////// * 
//////// * 
//////// * // == 구직자(GUJIKJA 신규 회원 가입시 // Gujikja클래스의 fieldㅢ 요구사항에 모두 맞으면
//////// * 
//////// * void register(Scanner sc, Gujikja[] gu_arr) { if (Gujikja.count <
//////// * gu_arr.length) {
//////// * 
//////// * } else { System.out.println(">> 정원 " + gu_arr.length +
//////// * "명이 꽉차서 구직자 회원가입이 불가합니다.!!<< \n"); } }
//////// * 
//////// * 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//////// * 
//////// * }
//////// * 
//////// * }
//////// 
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
////////							+ "1. 구직자 회원가입  2. 구직자 모두보기  3.검색하기  4.프로그램종료\n");
////////		System.out.print("▷ 메뉴번호 선택 : ");
////////	}	// end of void main_menu()----------------------
////////	
////////	// == 구직자(Gujikja) 신규 회원가입시
////////	//	Gujikja 클래스의 field 의 요구사항에 모두 맞으면
////////	//	Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
////////	
////////	void register(Scanner sc, Gujikja[] gu_arr) {
////////		
////////			if(Gujikja.count < gu_arr.length) {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
////////				//System.out.println("신규회원 가입합니다.");
////////				
////////				String userid;
////////				String passwd;
////////				String name;
////////				
////////				boolean isUse_userid;
////////				do {
////////						isUse_userid = true;		// 초기화
////////						
////////						System.out.println("1. 아이디 : ");
////////						String userid = sc.nextLine();	// "eomjh" "leess" "chaew" 현재 사용중인 아이디
////////												// "" 또는"				"공백만으로는 입력불가
////////				
////////				
////////				
////////				
////////				
////////				// == 가입된 회원들에 대해 중복 아이디 검사하기 ==//
////////				// if(userid.trim().isEmpty()) //JDK 1.8
////////				if(userid.isBlank()) {			// JDK 11
////////					System.out.println(">> 아이디는 필수 입력사항입니다. << \n");
////////					isUse_userid = false;
////////				}
////////				
////////				else {
////////					
////////					for(int i = 0; i<Gujikja.count; i++) {
////////						if(userid.equals(gu_arr[i].userid)) {
////////							System.out.println(">> 이미 사용중인 아이디입니다. <<\n");
////////							isUse_userid = false;
////////							break;
////////						
////////					
////////						
////////					// isUse_userid = true;
////////						}
////////				} while(!isUse_userid);
////////				
////////				
////////				// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을 때 까지 반복해야한다
////////				boolean isUse_passwd; // 초기화
////////				do {
////////					isUse_passwd = true; //  초기화
////////					System.out.print("2.비밀번호 : ");
////////					String passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$" 
////////					
////////					if ( !MyUtil.isCheckPasswd(passwd));{
////////						System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자");
////////						isUse_passwd = false;
////////					}
////////				} while(!isUse_passwd);
////////				// end of do~while---------
////////			
////////			
////////				
////////				// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////////				 // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다
////////				boolean isUse_name; // 초기화
////////				do {
////////					isUse_passwd = true; //  초기화
////////					System.out.print("3.성명 : ");
////////					String name = sc.nextLine(); // ""  "           "  "감 강 찬" "강" "김수한무거북이와두루미" "강KamC"
////////												// "강감찬"
////////					if(name.isBlank()) {
////////						isUse_name = false;
////////						
////////					}
////////					else {
////////						char[] ch_arr
////////					}
////////					
////////					char[] ch_arr = name.toCharArray();
////////					
////////					if(2 <= ch_arr.length && ch_arr.length <= 6) {
////////						for(int i = 0; i<ch_arr.length; i++) {
////////							if(!('가' <= ch_arr[i] && ch_arr[i] <= '힣' ) ) {
////////								isUse_name = false;
////////								break;
////////							}
////////						}//end of for -----
////////					}
////////					
////////				else {
////////					isUse_name = false;
////////				}
////////					
////////					
////////				if(!isUse_name) {
////////					System.out.println("[경고] 성명은 공백 없이 한글로만 2글자 이상 6글자 이하이어야 합니다");
////////				}
////////			} while			
////////					
////////					
////////					else{
////////						
////////						String (name.isBlank()) {
////////						System.out.println("[경고] 성명은 공백 없이 한글로만 2글자 이상 6글자 이하이어야 합니다");
////////					}
////////					
////////					if ( !MyUtil.isCheckPasswd(passwd));{
////////						System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자");
////////						isUse_passwd = false;
////////					}
////////				// 주민번호는  필수 입력사항이면서 주민번호 조건에 맞을 때까지 반복해야 한다.
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					
////////					} while(!isUse_passwd);
////////				// end of do~while---------
////////				
////////				
////////				
////////				
////////				}
////////				else {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
////////				System.out.println(">>> 정원 " + gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
////////				
////////				}
////////				
////////				}	// end of void register(Scanner sc, Gujikja[] gu_arr)-------------
////////*/
////////
////////
////////
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
////////				jubun = sc.nextLine(); //"9610021" "9610022" 
////////										// "0010023" "0010024"
////////										// "9604311" "9604312"	"0004313"
////////										// "9610025" "0010025"
////////				
////////				
////////				if( !MyUtil.isCheckJubun(jubun) ) { // ischeckjubun에서 creat 눌르기!
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
////////	}	
////////		// 구직자 모두보기
////////		 void view_all_info(Gujikja[] gu_arr) {
/////////*
//////// * ---------------------------------------------------------------------------
//////// * 		아이디			비밀번호		성명		 생년월일 		성별		현재나이		가입일자
//////// *----------------------------------------------------------------------------
//////// *		eomjh		qWe*****	엄정화		961020		여		  29		2024-01-31 10:30:40
//////// *		leess		abC*****	이순신 	960120		남		  29		2024-01-31 10:30:40
//////// *		chaew		aSd*****	차은우		010620		남		  22		2024-01-31 10:30:40
//////// *------------------------------------------------------------------------------
//////// */
////////			 
////////	if(Gujikja.count == 0) {
////////		System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다 <<\n");
////////	}
////////	else {
////////		title();	
////////		StringBuilder sb = new StringBuilder();
////////		
////////		for(int i = 0; i<Gujikja.count; i++)
////////		{
////////			
////////		 sb.append(gu_arr[i].getInfo()+"\n");//꺼내오기, 전체 인스턴스 정보를 가져오는 것,sb에 쌓아둬야한다, getInfo에 creat 누르기
////////	}
////////		 System.out.println(sb.toString());
////////	
////////	}
////////	
////////	
////////}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
////////		void title() {
////////			System.out.println("-".repeat(60)+"\n"+"아이디			비밀번호		성명		 생년월일 		성별		현재나이		가입일자\n" 
////////		+ " "+"-".repeat(60));
////////		}
////////
////////		// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
////////		void search_menu(Scanner sc, Gujikja[] gu_arr) {
////////			
////////			String str_menuno = "";
////////			do {
////////			System.out.println("\n === 검색메뉴 ===\n"
////////						         + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");  
////////				System.out.print("▷ 검색 메뉴번호 선택 : ");
////////				
////////				str_menuno = sc.nextLine();
////////				switch (str_menuno) {
////////				case "1": // 연령대 검색
////////					search_ageLine(sc, gu_arr); // creat 만들기
////////					
////////					break;
////////				case "2": // 성별 검색
////////					search_gender(sc, gu_arr);
////////					break;
////////					
////////				case "3": // 연령대 및 성별 검색
////////					break;
////////					
////////				case "4": // 메인메뉴로 돌아가기
////////					
////////					break;
////////					
////////				default:
////////					System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!! \n");
////////					break;
////////				}// end of switch (key), switch ctrl+space key누르면 됌
////////				
////////			} while(!"4".equals(str_menuno));
////////				
////////			
////////		}//end of void search_menu(Scanner sc, Gugikja
////////
////////		
////////	
//////////== 연령대 검색해주는 메소드 == //
////////
////////		void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
////////			if(Gujikja.count == 0) { // 구직자가 없는 경우
////////				System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다 <<");
////////			}
////////			else {// 구직자가 존재하는 경우
////////				boolean isUse_ageLine = false;
////////				do {
////////					System.out.println("> 검색하고자하는 연령대[예: 20] :");
////////					String str_ageLine = sc.nextLine(); //"0" "10""20""30""40""50""60""70""80" -- 정상 
////////													// "25", "강아지" "-20" --> 비정상
////////				switch (str_ageLine) {
////////				case "0":
////////				case "10":					
////////				case "20":					
////////				case "30":					
////////				case "40":					
////////				case "50":					
////////				case "60":					
////////				case "70":					
////////				case "80":
////////					isUse_ageLine = true;
////////					break; //swith문의 break
////////
////////				default:
////////					System.out.println("[경고] 올바른 연령대를 입력하세요!\n");
////////					break;
////////				}// end of switch 
////////			} while(!isUse_ageLine);
////////				// == 입력 받은 연령대에 해당하는 구직자 찾기 ==
////////				
////////				StringBuilder sb = new StringBuilder();
////////				boolean isSearch = false;
////////				for(int i = 0; i <Gujikja.count; i++) {
////////					int ageLine = gu_arr[i].getAge()/10*10;
////////					//				나이/10*10
////////					//				26/10*10 =  => 20
////////					//				23/10*10 ==> 20
////////					if(Integer.parseInt(str_ageLine) == ageLine) {
////////						isSearch = true;
////////						sb.append(gu_arr[i].getInfo()+"\n");
////////					
////////					
////////				
////////				}// end for
////////				if(isSearch)
////////					title();
////////				System.out.println(sb.toString());
////////			}
////////				else {
////////					System.out.println("[검색결과 연령대" + str_ageLine + "대인 구직자는 없습니다]\n");
////////				}
////////			}//end of switch(str_ageLine)------------------
////////		}// end of void search_ageLine(Scanner sc, Gujjikja[] gu_arr)
////////		// 성별 검색
////////				 void search_gender(Scanner sc, Gujikja[] gu_arr) {
////////					
////////					
////////				}// end of	
////////			/*
////////			 * boolean isUse_ageLine = false; do {
////////			 * System.out.println("> 검색하고자하는 연령대[예: 20] :"); String str_ageLine =
////////			 * sc.nextLine(); //"0" "10""20""30""40""50""60""70""80" -- 정상 // "25", "강아지"
////////			 * "-20" --> 비정상 switch (str_ageLine) { case "0":
////////			 * 
////////			 * case "10":
////////			 * 
////////			 * case "20":
////////			 * 
////////			 * case "30":
////////			 * 
////////			 * case "40":
////////			 * 
////////			 * case "50":
////////			 * 
////////			 * case "60":
////////			 * 
////////			 * case "70":
////////			 * 
////////			 * case "80": isUse_ageLine = true; break; //swith문의 break
////////			 * 
////////			 * default: System.out.println("[경고] 올바른 연령대를 입력하세요!\n"); break; }// end of
////////			 * switch } while(!isUse_ageLine);
////////			 */
////////			
////////			
////////			
////////			
////////			
////////			
////////			
////////}
////////	
////////
//////package my.day11.c.abstraction;
//////
//////import java.util.Scanner;
//////
//////import my.util.MyUtil;
//////
//////public class Ctrl_gujikja {
//////
//////	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
//////	void main_menu() {
//////		System.out.println("\n === 메인메뉴 ===\n" + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");
//////		System.out.print("▷ 메뉴번호 선택 : ");
//////	}// end of void main_menu()---------------------------
//////
//////	// == 구직자(Gujikja) 신규 회원가입시
//////	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
//////	// Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
//////	void register(Scanner sc, Gujikja[] gu_arr) {
//////
//////		if (Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//////
//////			String userid;
//////			String passwd;
//////			String name;
//////			String jubun;
//////
//////			boolean isUse_userid;
//////			do {
//////				isUse_userid = true; // 초기화
//////
//////				System.out.print("1.아이디 : ");
//////				userid = sc.nextLine(); // "eomjh" "leess" "chaew" 현재 사용중인 아이디 이므로 입력불가!!
//////										// "" 또는 " " 공백만으로는 입력불가!!
//////
//////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
//////				// if(userid.trim().isEmpty()) // JDK 1.8
//////				if (userid.isBlank()) { // JDK 11
//////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
//////					isUse_userid = false;
//////				} else {
//////
//////					for (int i = 0; i < Gujikja.count; i++) {
//////						if (userid.equals(gu_arr[i].userid)) {
//////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
//////							isUse_userid = false;
//////							break;
//////						}
//////					} // end of for---------------------
//////				}
//////			} while (!isUse_userid);
//////			// end of do~while--------------------------------
//////
//////			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
//////			boolean isUse_passwd;
//////			do {
//////				isUse_passwd = true; // 초기화
//////
//////				System.out.print("2.비밀번호 : ");
//////				passwd = sc.nextLine(); // "Qw12$" "Qwer1234sdfsdfdsfsfsdf$" "qwer1234$" "qWer1234$"
//////
//////				if (!MyUtil.isCheckPasswd(passwd)) {
//////					System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n");
//////					isUse_passwd = false;
//////				}
//////
//////			} while (!isUse_passwd);
//////			// end of do~while--------------------------------
//////
//////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
//////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
//////			boolean isUse_name;
//////			do {
//////				isUse_name = true; // 초기화
//////
//////				System.out.print("3.성명 : ");
//////				name = sc.nextLine(); // "" " " "강 감 찬" "강" "김수한무거북이와두루미" "강KamC"
//////										// "강감찬"
//////
//////				if (name.isBlank()) {
//////					isUse_name = false;
//////				} else {
//////					char[] ch_arr = name.toCharArray();
//////
//////					if (2 <= ch_arr.length && ch_arr.length <= 6) {
//////
//////						for (int i = 0; i < ch_arr.length; i++) {
//////							if (!('가' <= ch_arr[i] && ch_arr[i] <= '힣')) {
//////								isUse_name = false;
//////								break;
//////							}
//////						} // end of for---------------------
//////
//////					} else {
//////						isUse_name = false;
//////					}
//////				}
//////
//////				if (!isUse_name) {
//////					System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
//////				}
//////
//////			} while (!isUse_name);
//////			// end of do~while--------------------------------
//////
//////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
//////			boolean isUse_jubun;
//////			do {
//////				isUse_jubun = true; // 초기화
//////
//////				System.out.print("4.주민번호 : ");
//////				jubun = sc.nextLine(); // "9610021" "9610022" 정상
//////										// "0010023" "0010024" 정상
//////										// "9604311" "9604312" "0004313" "0004314" 비정상
//////										// "9610025" "0010026" 비정상
//////
//////				if (!MyUtil.isCheckJubun(jubun)) {
//////					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n");
//////					isUse_jubun = false;
//////				}
//////
//////			} while (!isUse_jubun);
//////			// end of do~while--------------------------------
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
//////		} else { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
//////			System.out.println(">> 정원 " + gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다.!! <<\n");
//////		}
//////
//////	}// end of void register(Scanner sc, Gujikja[] gu_arr)------------
//////
//////	// === 구직자 모두보기 메소드 ===
//////	void view_all_info(Gujikja[] gu_arr) {
//////		
//////		
//////		    -----------------------------------------------------------------------------
//////		      아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
//////		    -----------------------------------------------------------------------------
//////		      eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
//////		      leess   abC*******   이순신    960120   남     28        2024-01-31 10:30:40
//////		      chaew   aSd******    차은우    010620   남     22        2024-01-31 10:30:40 
//////		    -----------------------------------------------------------------------------  
//////		 
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
//////	void title() {
//////		System.out.println(
//////				"-".repeat(70) + "\n" + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n" + "-".repeat(70));
//////	}// end of void title()----------------------------
//////
//////	// == 검색하기 메뉴를 보여주는 메소드 생성하기 == //
//////	void search_menu(Scanner sc, Gujikja[] gu_arr) {
//////
//////		String str_menuno = "";
//////		do {
//////			System.out.println("\n === 검색메뉴 ===\n" + "1.연령대검색   2.성별검색   3.연령대 및 성별 검색하기   4.메인메뉴로 돌아가기\n");
//////			System.out.print("▷ 검색메뉴번호 선택 : ");
//////
//////			str_menuno = sc.nextLine();
//////
//////			switch (str_menuno) {
//////			case "1": // 연령대검색
//////				search_ageLine(sc, gu_arr);
//////				break;
//////
//////			case "2": // 성별검색
//////				search_gender(sc, gu_arr);
//////				break;
//////
//////			case "3": // 연령대 및 성별 검색하기
//////
//////				break;
//////
//////			case "4": // 메인메뉴로 돌아가기
//////
//////				break;
//////
//////			default:
//////				System.out.println("[경고] 검색메뉴에 존재하는 번호만 입력하세요!!\n");
//////				break;
//////			}// end of switch (key)--------------------------
//////
//////		} while (!"4".equals(str_menuno));
//////		// end of do~while------------------------
//////
//////	}// end of void search_menu(Scanner sc, Gujikja[] gu_arr)-----------------
//////
//////	// == 연령대 검색해주는 메소드 == //
//////	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
//////
//////		if (Gujikja.count == 0) { // 구직자가 없는 경우
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
//////				str_ageLine = sc.nextLine(); // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
//////												// "25" "강아지" "-20" --> 비정상
//////
//////				switch (str_ageLine) {
//////				case "0":
//////				case "10":
//////				case "20":
//////				case "30":
//////				case "40":
//////				case "50":
//////				case "60":
//////				case "70":
//////				case "80":
//////					isUse_ageLine = true;
//////					break;
//////
//////				default:
//////					System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
//////					break;
//////				}// end of switch (str_ageLine)-----------------------
//////
//////			} while (!isUse_ageLine);
//////			// end of do~while------------------------
//////
//////			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
//////			StringBuilder sb = new StringBuilder();
//////			boolean isSearch = false;
//////
//////			for (int i = 0; i < Gujikja.count; i++) {
//////				int ageLine = gu_arr[i].getAge() / 10 * 10;
//////				// 나이/10*10
//////				// 26/10*10 ==> 20
//////				// 23/10*10 ==> 20
//////
//////				if (Integer.parseInt(str_ageLine) == ageLine) {
//////					isSearch = true;
//////					sb.append(gu_arr[i].getInfo() + "\n");
//////				}
//////
//////			} // end of for--------------------------
//////
//////			if (isSearch) {
//////				title();
//////				System.out.println(sb.toString());
//////			} else {
//////				System.out.println("[검색결과 연령대 " + str_ageLine + "대인 구직자는 없습니다]\n");
//////			}
//////
//////		} // end of if~else----------------------------------
//////
//////	}// end of void search_ageLine(Scanner sc, Gujikja[] gu_arr)--------------
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
//////				 if(input_gender.equals(gu_arr[i].getGender()) {
//////					 isSearch = true;
//////					 
//////				 }
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
//////	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
//////
//////}*/
////package my.day11.c.abstraction;
////
////import java.util.Scanner;
////
////import my.util.MyUtil;
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
////			String userid;
////			String passwd;
////			String name;
////			String jubun;
////			
////			boolean isUse_userid;
////			do {
////				isUse_userid = true; // 초기화 
////				
////				System.out.print("1.아이디 : ");
////				userid = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!! 
////				                        // "" 또는 "        " 공백만으로는 입력불가!!
////				
////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
////			//	if(userid.trim().isEmpty()) // JDK 1.8
////				if(userid.isBlank()) {	    // JDK 11
////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
////					isUse_userid = false;
////				}
////				else {
////					
////					for(int i=0; i<Gujikja.count; i++) {
////						if( userid.equals(gu_arr[i].userid) ) {
////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
////							isUse_userid = false;
////							break;
////						}
////					}// end of for---------------------
////				}
////			} while(!isUse_userid);
////			// end of do~while--------------------------------
////			
////			
////			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
////			boolean isUse_passwd;
////			do {
////				isUse_passwd = true; // 초기화
////				
////				System.out.print("2.비밀번호 : ");
////				passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
////				
////				if( !MyUtil.isCheckPasswd(passwd) ) {
////					System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n"); 
////					isUse_passwd = false;
////				}
////				
////			} while (!isUse_passwd);
////			// end of do~while--------------------------------
////			
////			
////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
////			boolean isUse_name;
////			do {
////				isUse_name = true; // 초기화
////				
////				System.out.print("3.성명 : ");
////				name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
////				                      // "강감찬"
////				
////				if(name.isBlank()) {
////					isUse_name = false;
////				}
////				else {
////					char[] ch_arr = name.toCharArray();
////					
////					if(2 <= ch_arr.length && ch_arr.length <= 6) {
////						
////						for(int i=0; i<ch_arr.length; i++) {
////							if( !('가' <= ch_arr[i] && ch_arr[i] <= '힣') ) {
////								isUse_name = false;
////								break;
////							}
////						}// end of for---------------------
////						
////					}
////					else {
////						isUse_name = false;
////					}
////				}
////								
////				if(!isUse_name) {
////				   System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
////				}
////				
////			} while (!isUse_name);
////			// end of do~while--------------------------------
////			
////			
////			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
////			boolean isUse_jubun;
////			do {
////				isUse_jubun = true; // 초기화
////				
////				System.out.print("4.주민번호 : ");
////				jubun = sc.nextLine(); // "9610021" "9610022"  정상
////				                       // "0010023" "0010024"  정상
////				                       // "9604311" "9604312"  "0004313"  "0004314" 비정상
////				                       // "9610025" "0010026"  비정상
////				
////			
////				if( !MyUtil.isCheckJubun(jubun) ) {
////					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n"); 
////					isUse_jubun = false;
////				}
////				
////			} while (!isUse_jubun);
////			// end of do~while--------------------------------
////			
////			
////			Gujikja gu = new Gujikja();
////			gu.userid = userid;
////			gu.passwd = passwd;
////			gu.name = name;
////			gu.jubun = jubun;
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
////				
////					
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
////	// == 연령대 및 성별 검색해주는 메소드
////	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
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
////			boolean is_ageline_Search = false;
////			
////			for(int i=0; i<Gujikja.count; i++) {
////				int ageLine = gu_arr[i].getAge()/10*10;
////				//                  나이/10*10
////				//                  26/10*10 ==> 20
////                //                  23/10*10 ==> 20
////				
////				if(Integer.parseInt(str_ageLine) == ageLine) {
////					is_ageline_Search = true;
////					break;
////				}
////				
////			}// end of for--------------------------
////			
////			if(!is_ageline_Search) { // ! 존재하지 않는다, 검색하고자하는 연령대에 해당하는 구직자가 존재하지 않는 경우
////				System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
////				title();
////				System.out.println(sb.toString());
////			}
////			else { //검색하고자하는 연령대에 해당하는 구직자가 존재하는 경우
////				// == 성별 검색시작!
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
////				// == 입력받은 성별에 해당하는 구직자 찾기 == //
////				StringBuilder sb = new StringBuilder();
////				boolean is_agelin_gender_Search = false;
////				// == 성별 검색끝!
////		
////				for(int i=0; i<Gujikja.count; i++) {
////				
////				 if(Integer.parseInt(str_ageLine) == gu_arr[i].getAge()/10*10
////						 && input_gender.equals(gu_arr[i].getGender()){
////					 					 
////				 }
////				
////				 }//end of 
////		
////	}// end of void search_ageLine_gender
////	
////	
////		
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
////				 if(input_gender.equals(gu_arr[i].getGender())) {
////					 isSearch = true;
////					 
////				 }
////				
////			}// end of for--------------------------
////			
////			
////
////			
////			
////			
////			if(isSearch) {
////				title();
////				System.out.println(sb.toString());
////			}
////			else {
////				System.out.println("[검색결과 연령대 "+input_gender+"인 구직자는 없습니다]\n");
////			}
////			
////		}// end of if~else----------------------------------
////		
////	}// end of void search_gender(Scanner sc, Gujikja[] gu_arr)------------
////	
////	}
////
//package my.day12.a.capsulation;
//
//import java.util.Scanner;
//
//import my.util.MyUtil;
//
//public class Ctrl_gujikja {
//
//	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
//	void main_menu() {
//		System.out.println("\n === 메인메뉴 ===\n"
//				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
//		System.out.print("▷ 메뉴번호 선택 : ");
//	}// end of void main_menu()--------------------------- 
//
//	
//	// == 구직자(Gujikja) 신규 회원가입시
//	//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
//	//    Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기 ==
//	void register(Scanner sc, Gujikja[] gu_arr) {
//		
//		if(Gujikja.count < gu_arr.length) { // 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
//			
//			String passwd;
//			String name;
//			String jubun;
//		
//			Gujikja gu = new Gujikja();
//			
//			do {
//			System.out.println("1. 아이디 :");
//			String userid = sc.nextLine(); //null이 될 수 없음
//			
//			// == 중복 아이디 검사하기 시작= =
//			for (int i = 0; i < Gujikja.count; i++) {
//				
//				if(userid.equals(gu_arr[i].getUserid()) ) //내가 입력한 userid와 같냐는 뜻이다
//					System.out.println(">> 이미 사용중인 아이디이므로 다른 아이디값을 입력하세요!!\n");
//					continue outer;
//			}
//			
//			} // end of for 
//			//== 중복 아이디 검사 끝
//			gu.setUserid(userid); // 계속 null 값을 유지
//			
//			
//			} while(!(gu.getUserid() != null)); 
//			
//			do {
//				System.out.println("2. 비밀번호 :");
//				String passwd1 = sc.nextLine();
//				
//				gu.setPasswd(); // 계속 null 값을 유지
//				} while(!(gu.getPasswd() != null));
//			
//			
//			//field 생성
////			String userid;
////			String passwd;
////			String name;
////			String jubun;
////			
////			boolean isUse_userid;
////			do {
////				isUse_userid = true; // 초기화 
////				
////				System.out.print("1.아이디 : ");
////				userid = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!! 
////				                        // "" 또는 "        " 공백만으로는 입력불가!!
////				
////				// == 가입된 회원들에 대해 중복아이디 검사하기 == //
////			//	if(userid.trim().isEmpty()) // JDK 1.8
////				if(userid.isBlank()) {	    // JDK 11
////					System.out.println(">> 아이디는 필수 입력사항 입니다. <<\n");
////					isUse_userid = false;
////				}
////				else {
////					
////					for(int i=0; i<Gujikja.count; i++) {
////						if( userid.equals(gu_arr[i].getUserid()) ) { 
////							System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
////							isUse_userid = false;
////							break;
////						}
////					}// end of for---------------------
////				}
////			} while(!isUse_userid);
////			// end of do~while--------------------------------
////			
////			
//		// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
////		boolean isUse_passwd;
////		do {
////				isUse_passwd = true; // 초기화
////				
////				System.out.print("2.비밀번호 : ");
////			passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
////			
////		if( !MyUtil.isCheckPasswd(passwd) ) {
////				System.out.println("[경고] 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n"); 
////				isUse_passwd = false;
////			}
////			
//////		} while (!isUse_passwd);
////			// end of do~while--------------------------------
////			
////			
////			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
////			// 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
//			boolean isUse_name;
//			do {
//				isUse_name = true; // 초기화
//				
//				System.out.print("3.성명 : ");
//				name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
//				                      // "강감찬"
//				
//				if(name.isBlank()) {
//					isUse_name = false;
//				}
//				else {
//					char[] ch_arr = name.toCharArray();
//					
//					if(2 <= ch_arr.length && ch_arr.length <= 6) {
//						
//						for(int i=0; i<ch_arr.length; i++) {
//							if( !('가' <= ch_arr[i] && ch_arr[i] <= '힣') ) {
//								isUse_name = false;
//								break;
//							}
//						}// end of for---------------------
//						
//				}
//					else {
//						isUse_name = false;
//					}
//				}
//								
//				if(!isUse_name) {
//				   System.out.println("[경고] 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.");
//				}
//				
//			} while (!isUse_name);
//			// end of do~while--------------------------------
//			
//			
//			// 주민번호는 필수 입력사항이면서 주민번호 조건에 맞을때 까지 반복해야 한다.
//			boolean isUse_jubun;
//			do {
//				isUse_jubun = true; // 초기화
//				
//				System.out.print("4.주민번호 : ");
//				jubun = sc.nextLine(); // "9610021" "9610022"  정상
//				                       // "0010023" "0010024"  정상
//				                       // "9604311" "9604312"  "0004313"  "0004314" 비정상
//				                       // "9610025" "0010026"  비정상
//				
//			
//				if( !MyUtil.isCheckJubun(jubun) ) {
//					System.out.println("[경고] 올바른 주민번호를 입력하세요!!\n"); 
//					isUse_jubun = false;
//				}
//				
//			} while (!isUse_jubun);
//			// end of do~while--------------------------------
//			
//			
//			Gujikja gu = new Gujikja();
//			gu.userid = userid;
//			gu.passwd = passwd;
//			gu.name = name;
//			gu.jubun = jubun;
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
//			return;
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
//				return; // 해당 메소드는 search_ageLine_gender이다
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
//				
//				}
//				// === !! 성별 검색 끝 !! === //
//			}
//			
//			
//		}// end of if~else----------------------------------
//		
//	}// end of void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr)------	
//	
//}
package my.day13.a.inheritance;

import java.util.Scanner;

public class Ctrl_gujikja {

	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
	void main_menu() {
		System.out.println("\n === 메인메뉴 ===\n"
				         + "1.구직자 회원가입   2.구직자 모두보기   3.검색하기   4.프로그램종료\n");  
		System.out.print("▷ 메뉴번호 선택 : ");
	}// end of void main_menu()--------------------------- 

	
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
				String userid = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				for(int i=0; i<Gujikja.count; i++) {
					
					if( userid.equals(gu_arr[i].getUserid()) ) {
						System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
						continue outer;
					}
					
				}// end of for-------------------
				
				// == 중복 아이디 검사하기 끝 == //
				
				gu.setUserid(userid);
				
			} while(!(gu.getUserid() != null));
			
			
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
	
}
