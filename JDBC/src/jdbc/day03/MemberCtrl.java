package jdbc.day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MemberCtrl {
	
	// field, attribute, property, 속성
	MemberDAO mdao = new MemberDAO_imple();
	
	
	// method, operation, 기능
	// ***  시작메뉴를 보여주는 메소드(기능) *** //
		public void menu_Start(Scanner sc) {
			
			MemberDTO member = null; 
			String s_Choice = "";
			boolean isLogin = false;
			
			
			if(isLogin == false) { // 로그인 하기 전 
			do {
				/////////////////////////////////////////////////////
			System.out.println("\n>> -- 시작메뉴 ----<< \n"
								+ "1. 회원가입	     2.로그인      3.프로그램종료 \n"
								+ "------------------------------------------\n");
			System.out.print("> 메뉴번호 선택 : ");
				s_Choice = sc.nextLine();
	
	
								switch ( s_Choice ) {
									case "1": // 회원가입
										memberRegister(sc);
										break;
									case "2": // 로그인
										member = login(sc); // 로그인 시도하기 
										if(member != null) {
											isLogin = true;
											
										}
										break;
									
									case "3": // 프로그램 종료 --> 3번을 치면 프로그램 종료로 들어간다, 메소드 자체를 종료해야한다 
									 return;	//  menu_Start(Scanner sc)  메소드 종료함 ,, 메소드 종료가 return이다 
										
										
							
									default:
									System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요!! <<<");
										break;
								} // end of switch ( s_choice )
							///////////////////////
						
				} while(!("2".equals(s_Choice) && isLogin == true));
		
		}// end of if(isLogin == false)
		
			System.out.println("~~ 로그인 성공되어진 후 화면 보여주기 ");
			
	} // end of public void menu_start


		
							// ** 회원가입을 해주는 메소드
							

								private void memberRegister(Scanner sc) {
									System.out.println("\n >>> -- 회원가입 --- <<<<");
									System.out.print("1. 아이디 :");
									String userid = sc.nextLine();
									
									System.out.print("2. 비밀번호 :");
									String passwd = sc.nextLine();
									
									System.out.print("3. 회원명 :");
									String name = sc.nextLine();
									
									System.out.print("4. 연락처(휴대폰) :");
									String mobile = sc.nextLine();
									
									MemberDTO member = new MemberDTO(); // 
									member.setUserid(userid);
									member.setPasswd(passwd);
									member.setName(name);
									member.setMobile(mobile);
									
									int n = mdao.memberRegister(member);
								
								if (n == 1) {
									System.out.println("\n >>> 회원가입을 축하드립니다 << ");
								}
								else {
									System.out.println(">> 회원가입이 실패되었습니다 <<");
								}
		
	} //end of private void memberRegister
								
								
			// 로그인 시도하기
			private MemberDTO login(Scanner sc) {
				 
				MemberDTO member = null;
				
				System.out.println("\n >>> -- 로그인 -- <<<");
				
				System.out.print(">  아이디 :");
				String userid = sc.nextLine();
				
				System.out.print(">  비밀번호 :");
				String passwd = sc.nextLine();
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("userid", userid);
				paraMap.put("passwd", passwd);
				
				member = mdao.login(paraMap);
				
				if(member != null) {
					System.out.println("\n >> 로그인 성공!! << \n");
				}
				else {
					System.out.println("\n >> 로그인 실패ㅠㅜ << \n");
				}
			return member;
			} // end of 
										
}
