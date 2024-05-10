<<<<<<< HEAD
package member.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Member_Controller {

	
	public void mem_login(Scanner sc) {
		
		String choice_menu = "";
		exit_login:
		do {
			System.out.println("--------------------------- [구직자 로그인] ---------------------------\n"
					         + "  1. 아이디, 비밀번호 입력.     2. 아이디, 비밀번호 찾기.    3.메인메뉴로 이동.\n"
					         + "--------------------------------------------------------------------");
			System.out.print("▶ 메뉴 번호 선택 : ");
			choice_menu = sc.nextLine();
			
			switch (choice_menu) {
			case "1": // 아이디 비밀번호 입력
				MemberDAO mdao = new MemberDAO_imple();
				boolean isLogin = false; 
				
			    System.out.println("\n >>> --- 로그인 --- <<<");
			    
			    System.out.print("▷ 아이디 : ");
			    String userid = sc.nextLine();
			    System.out.print("▷ 비밀번호 : ");
			    String passwd = sc.nextLine();
			    
			    // mdao.login(userid, passwd);   가능하지만 spring 은 놉! => map 활용하기
			    Map<String, String> paraMap = new HashMap<>();      // key : string / values : string
			    paraMap.put("pk_user_id", userid);
			    paraMap.put("pass_wd", passwd);
			    
			    MemberDTO member = mdao.login(paraMap);
			    
			    if("0".equals(member.getUSER_STATUS())){
			    	System.out.println("[경고] 탈퇴한 회원입니다.");
			    }
			    else if(member.getPK_USER_ID()!=null) {
			     System.out.println(member.getUSER_NAME()+"님이 로그인 하셨습니다");
			     isLogin = true;
			    }
			    else {
			     System.out.println("[경고] 아이디 혹은 비밀번호가 일치하지 않습니다.");
			    }
			    
			    
			    ///////////////////////////////////////////////////////////////
			    String s_choice = ""; 
			    do {  
				if(isLogin == true) {
			    	  System.out.println("---------------------------------------구직자 메뉴 [" + member.getUSER_NAME() +"님 로그인중]--------------------------------\r\n"
			    	  				   + "        1.구인회사 검색    2.모집공고 검색      3.개인정보 조회     4.이력서 메뉴      5.로그아웃\r\n"
			    	  				   + "-----------------------------------------------------------------------------------------------\r\n"
			    	  						);
			    	  
			    	  System.out.print("▷ 메뉴번호 선택 : ");
		             
			    	  s_choice = sc.nextLine();
			    	  
			    	  switch (s_choice) {
		             case "1":   // 구인회사 검색
		                 search_com(member,sc);
		                 break;
		              case "2": // 모집공고검색
		            	  recruit_notice(member,sc); 
		           	     break;
		              case "3":   // 개인정보조회
		              	 view_member_info(member,sc);
		          
		              	 break;
		               
		              case "4":	// 이력서 조회
		            	  resume_menu(member,sc);
		              	 break;
		              	
		              case "5":	// 로그아웃
		                  System.out.println(">>> 로그아웃 되었습니다. <<<\n");
		                  break exit_login;
		                 
		              default:
		              	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
		              	break;                 
		              }   // end of switch (s_Choice)--------------------
			    	  
		           }   // end of if(isLogin == true)----------------------------
				
				else {
					break;
				}
					
				} while(!"5".equals(s_choice));   // return 프로그램 종료일 때만 나갈 수 있게 하기
				break;
			case "2": // 아이디 비밀번호 찾기
				mdao = new MemberDAO_imple();
				String find_menu_no = "";
				boolean find_menu_exit = false;
				do {
					find_menu_exit = true;
					System.out.println("-------------- [어떤 정보를 찾으시겠습니까??] --------------\n"
							         + "  1. 아이디 찾기     2. 비밀번호 찾기     3.로그인 메뉴로 이동\n"
							         + "----------------------------------------------------");
					System.out.print("▶ 메뉴번호 선택 : ");
					find_menu_no = sc.nextLine();
					switch (find_menu_no) {
					case "1": // 아이디 찾기 
						MemberDTO mdto = new MemberDTO();
						boolean exit_jubun = true;
						String jubun = "";
						do {
							exit_jubun = true;
							
							System.out.println("\n[안내] 주민등록번호는 '-' 를 제외한 13자리의 숫자로 입력하세요");
							System.out.print("▷ 주민등록번호 : ");
							jubun = sc.nextLine();
							
							// 주민번호가 정확히 13자리인지 확인.
							if(jubun.length() == 13) {
								
								char[] jubun_arr = jubun.toCharArray();
								
								// 주민번호가 숫자로만 이루어져있는지 확인하기위한 반복문 
								for(int i = 0; i< jubun_arr.length; i++) {
									if(!('0' <= jubun_arr[i] && jubun_arr[i] <= '9')) {
										exit_jubun = false;
										System.out.println("[경고] 주민등록번호는 반드시 숫자로만 이루어져야합니다.");
										break;
									}
								}
							}
							else {
								System.out.println("[경고] 주민등록번호는 반드시 13자리의 숫자로 이루어져야합니다.");
								exit_jubun = false;
							}
							
							if(exit_jubun) {
							// 위의 조건을 모두 충족했을 때 주민번호 앞자리가 달력에 존재하는 값인지, 뒷자리의 첫번째 자리가 1,2,3,4 중 하나인지 확인하는 유효성 검사.
								mdto.setUSER_JUBUN(jubun);
								
								if(mdto.getUSER_JUBUN() != null) {
									jubun = jubun.substring(0,6) + "-" + jubun.substring(6);
									break;
								}
								else {
									exit_jubun = false;
								}
							}
						}while(!exit_jubun);
						
						System.out.print("▷ 비밀번호 : ");
						passwd = sc.nextLine();
						
						Map<String,String> find_id_map = new HashMap<>();
						find_id_map.put("user_jubun", jubun);
						find_id_map.put("user_passwd", passwd);
						
						
						String user_id = mdao.find_user_id(find_id_map);
						
						if(user_id != null) {
							System.out.println("▶ 아이디 : " + user_id);
						}
						else {
							System.out.println("[경고] 입력하신 정보가 일치하지 않습니다.");
						}
						break;
					case "2": // 비밀번호 찾기
						mdto = new MemberDTO();
						
						System.out.print("▷ 아이디 : ");
						user_id = sc.nextLine();
						
						exit_jubun = true;
						jubun = "";
						do {
							exit_jubun = true;
							
							System.out.println("\n[안내] 주민등록번호는 '-' 를 제외한 13자리의 숫자로 입력하세요");
							System.out.print("▷ 주민등록번호 : ");
							jubun = sc.nextLine();
							
							// 주민번호가 정확히 13자리인지 확인.
							if(jubun.length() == 13) {
								
								char[] jubun_arr = jubun.toCharArray();
								
								// 주민번호가 숫자로만 이루어져있는지 확인하기위한 반복문 
								for(int i = 0; i< jubun_arr.length; i++) {
									if(!('0' <= jubun_arr[i] && jubun_arr[i] <= '9')) {
										exit_jubun = false;
										System.out.println("[경고] 주민등록번호는 반드시 숫자로만 이루어져야합니다.");
										break;
									}
								}
							}
							else {
								System.out.println("[경고] 주민등록번호는 반드시 13자리의 숫자로 이루어져야합니다.");
								exit_jubun = false;
							}
							
							if(exit_jubun) {
							// 위의 조건을 모두 충족했을 때 주민번호 앞자리가 달력에 존재하는 값인지, 뒷자리의 첫번째 자리가 1,2,3,4 중 하나인지 확인하는 유효성 검사.
								mdto.setUSER_JUBUN(jubun);
								
								if(mdto.getUSER_JUBUN() != null) {
									jubun = jubun.substring(0,6) + "-" + jubun.substring(6);
									break;
								}
								else {
									exit_jubun = false;
								}
							}
						}while(!exit_jubun);
						
						Map<String,String> find_user_passwd_map = new HashMap<>();
						find_user_passwd_map.put("jubun", jubun);
						find_user_passwd_map.put("user_id", user_id);
						
						
						int is_change_user_passwd = mdao.find_user_passwd(find_user_passwd_map);
						
						if ( is_change_user_passwd == 1) {
							System.out.println("[안내] 비밀번호가 성공적으로 변경되었습니다.");
						}
						else if (is_change_user_passwd == 0) {
							System.out.println("[안내] 비밀번호 변경을 취소하였습니다.");
						}
						else {
							System.out.println("[안내] 입력하신 정보가 일치하지 않습니다.");
						}
						
						
						break;
					case "3": // 메인 메뉴로 이동
						
						break;
					default:
						System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.\n");
						find_menu_exit = false;
						break;
					}
				} while(!find_menu_exit);
				
				break;
			case "3":
				
				break;
	
			default:
				System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.\n");
				break;
			}
		} while(!("3".equals(choice_menu))); // end of do_while -----------------
		
		
		
	} // end of public void mem_login(Scanner sc)
	
	
	private void recruit_notice(MemberDTO member, Scanner sc) {
		  String s_choice = null;
		do {
		System.out.println("-------------------모집공고 검색 [" + member.getUSER_NAME()   + "님 로그인중]-----------------\r\n"
							+ " 1.모든공고목록보기     2.공고명검색      3.직위검색      4.연봉검색      5.근무형태검색      6.기업규모검색    7.공고지원      8.이전메뉴로 \r\n"
							+ "--------------------------------------------------------------------------\r\n"
							);


				System.out.print("▶ 메뉴번호 선택 :");
				
				
				s_choice = sc.nextLine(); 
				
				MemberDAO Mdao = new MemberDAO_imple();
				switch (s_choice) {
				
				
				
				
				case "1":
					
					view_all_recruit_notice(); // 모든 공고 목록을 보여주는 메소드 생성
					
					
					break;
				case "2":   // 공고명 
				
				System.out.println("▷ 검색어를 입력하세요 : ");
				String recruit_notice_name = sc.nextLine();
				if(recruit_notice_name.isBlank()) {
					System.out.println("[경고] 공백은 입력할 수 없습니다.");
					break;
				}
				else {
					Map<String, String>  paraMap  = new HashMap<>();
					paraMap.put("NOTICE_NAME", recruit_notice_name );
					
					List<Map<String, String>> mapList =Mdao.recruit_notice_name(paraMap);
					
					
					
					
					StringBuilder sb = new StringBuilder();
					
					for(Map<String, String> map : mapList) {
					sb.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
							+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
							+ map.get("COMPANY_NAME") + "\t"
							+ map.get("LOCATION_NAME") + "\t"
							+ map.get("YEARSAL") + "\t"
							+ map.get("WORKTYPE_NAME") + "\t"
							+ map.get("POSITION_NAME") + "\t"
							+ map.get("RECRUIT_FINISH_DAY") + "\n");
					}
					if(sb.toString().length()!=0) {
						System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
								+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉           근무형태         공고마감일      \r\n"
								+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
								
								);

						System.out.println(sb.toString()+"\n");
					}
					else {
						System.out.println("[경고] 해당하는 모집공고가 존재하지 않습니다.");
					}
					break;
				}
				
				
				
				
				
				case "3": // 직종
					System.out.println("▷ 검색어를 입력하세요 : ");
					String position_name = sc.nextLine();
					
					if(position_name.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String, String>  paraMap1  = new HashMap<>();
						paraMap1.put("PN", position_name);
						List<Map<String, String>> mapList1 =Mdao.position_name(paraMap1);
						
						StringBuilder sb1 = new StringBuilder();
						
						for(Map<String, String> map : mapList1) {
							sb1.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
									+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
									+ map.get("COMPANY_NAME") + "\t\t"
									+ map.get("WORKTYPE_NAME") + "\t\t"
									+ map.get("LOCATION_NAME") + "\t\t"
									+ map.get("YEARSAL") + "\t\t"
									+ map.get("POSITION_NAME") + "\t\t"
									+ map.get("RECRUIT_FINISH_DAY") + "\t\n");
							}
						
						
						
						if(mapList1.size()!=0) {
							System.out.println("------------------------------------------------------[공고 목록]---------------------------------------------------------------------------------------------------------------\r\n"
									+ "    공고번호 			   공고명                                         기업명           직위         근무지역                   연봉           근무형태         공고마감일      \r\n"
									+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
							System.out.println(sb1.toString());
						}
						else {
							System.out.println("[경고] 입력하신 사항에 해당하는 모집공고가 존재하지 않습니다. ");
						}
							
					}
					
					
						
					break;
					
				 

				case "4":   // 연봉
					
					String search_yearsal = "";
					System.out.println("[안내] 연봉을 입력하세요 입력하신 연봉보다 많은 공고는 모두 표시됩니다.");
					do {
						System.out.println("▶ 연봉 [6,000만원 > 6000 입력] : ");
						search_yearsal = sc.nextLine();
						
						if(search_yearsal.isBlank()) {
							System.out.println("[경고] 공백은 입력할 수 없습니다.");
							break;
						}
						else {
							boolean is_all_num = true;
							char[] search_yearsal_arr = search_yearsal.toCharArray();
							for(int i = 0; i < search_yearsal_arr.length; i++) {
								if(!('0'<=search_yearsal_arr[i] && search_yearsal_arr[i] <= '9')) {
									is_all_num = false;
								}
							}
							if(!is_all_num) {
								System.out.println("[경고] 반드시 숫자만 입력하세요");
							}
							else {
								Mdao.search_yearsal(search_yearsal); // 입력한 연봉보다 큰 모집공고를 모두 보여주는 메소드 생성
								break;
							}
						}
					
					} while(true);
					
				
					
					break;
				
				
				case "5" : // 근무형태
					System.out.println("▷ 검색어를 입력하세요 : ");
					String work_tp = sc.nextLine();
					
					if(work_tp.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String, String> paraMap11 = new HashMap<>();
						paraMap11.put("WT", work_tp);
						List<Map<String, String>> mapList11 = Mdao.work_tp(paraMap11);
						
						
						StringBuilder sb11 = new StringBuilder();
						
						for(Map<String, String> map : mapList11) {
							sb11.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
									+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
									+ map.get("COMPANY_NAME") + "\t"
									+ map.get("LOCATION_NAME") + "\t"
									+ map.get("YEARSAL") + "\t"
									+ map.get("WORKTYPE_NAME") + "\t"
									+ map.get("POSITION_NAME") + "\t"
									+ map.get("RECRUIT_FINISH_DAY") + "\n");
							}
						if(mapList11.size()!=0) {
							System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
									+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉           근무형태         공고마감일      \r\n"
									+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
							System.out.println(sb11.toString());
						}
						else {
							System.out.println("[경고] 검색된 공고가 존재하지 않습니다.");
						}
					}
					
					break;
					
				case "6" : // 기업규모
					System.out.println("▷ 검색어를 입력하세요 : ");
					String com_size = sc.nextLine();
					
					if(com_size.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String, String> paraMap111 = new HashMap<>();
						paraMap111.put("CS", com_size);
						List<Map<String, String>> mapList111 = Mdao.com_size(paraMap111);
						
						StringBuilder sb111 = new StringBuilder();
						
						for(Map<String, String> map : mapList111) {
							sb111.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
									+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
									+ map.get("COMPANY_NAME") + "\t"
									+ map.get("LOCATION_NAME") + "\t"
									+ map.get("YEARSAL") + "\t"
									+ map.get("COM_SIZE") + "\t"
									+ map.get("WORKTYPE_NAME") + "\t"
									+ map.get("POSITION_NAME") + "\t"
									+ map.get("RECRUIT_FINISH_DAY") + "\n");
							}
						if(mapList111.size()!=0) {
							System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
									+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉        기업규모    근무형태         공고마감일      \r\n"
									+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
									
									);
							System.out.println(sb111.toString());
						}
						else {
							System.out.println("[경고] 검색된 정보가 없습니다. 다시한번 확인해주세요.");
						}
							
					}
					
					break;
				
				case "7" : // 공고 지원하기
					
					join_recruit_notice(member,sc); // 모집공고에 지원할 수 있게 해주는 메소드 생성.
					
					break;
				case "8" : // 이전메뉴로
					
					break;
				
				default:
				 
					System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
					break; 
				
				} //END OF  switch (s_choice) 
				} while(!"8".equals(s_choice));
			
		}
	
	
	
	
	// == 이력서 메뉴를 실행해주는 메소드 생성
		private void resume_menu(MemberDTO member, Scanner sc) {
			
			
			MemberDAO mdao = new MemberDAO_imple();
			
			String select_resume = "";
			do {
				System.out.println("------------------- 이력서 메뉴 [" + member.getUSER_NAME()  + "님 로그인중]-------------------\n"
						 + "  1.내 이력서 보기    2. 이력서 등록    3. 이력서 수정    4. 이력서 삭제    5. 이전메뉴로 \n"
						 + "--------------------------------------------------------------------------\n"
							);
				System.out.print("▶ 번호 선택 : ");
				select_resume = sc.nextLine();
				
				switch (select_resume) {
				
					case "1":// 내 이력서 목록보기
						
			            System.out.println("\n[이력서 목록]");
						System.out.println("-".repeat(80));
						System.out.println(" 이력서번호      이력서제목                        희망연봉             작성일자");
						System.out.println("-".repeat(80));
						
			            List<Map<String,String>> list_map = mdao.view_my_all_resume(member);
			            
			            
			            StringBuilder sb = new StringBuilder();
			            
			            for(Map<String, String> map : list_map) {
			            	sb.append("    "+map.get("pk_resume_code") + "\t");
				           	sb.append(map.get("self_introduce") + "\t");
				           	sb.append(map.get("want_yearsal") + "\t\t");
				           	sb.append(map.get("resume_date") + "\t");
			            }
			            System.out.println(sb.toString());
			            
			            //////////////////////////////////////////////////////////////////////
			            System.out.print("\n▶ 열람할 이력서 번호 선택 : ");
			            String choice_resume_no = sc.nextLine();
			            
			            mdao.view_resume_detail(choice_resume_no);  // tbl_resume 의 정보를 다 보여주는 DAO imple 메소드로 이동
			            
			            break;
				          
			           
					case "2": // 이력서 등록
		                  System.out.println("\n[이력서 등록 메뉴]");
		                  
		                  
		                  
		                  // 이력서명 입력
		                  String resume_name = "";
		                  do {
		                     System.out.print("▶ 이력서명 : ");
		                     resume_name = sc.nextLine();
		                     if( !(resume_name.isEmpty() )) {
		                        break;
		                     }
		                     else {
		                        System.out.println("<경고> 이력서명은 공백을 제외하고 입력해주세요.");
		                     }
		                  } while(true);
		                  
		                  
		                  
		                  // 병역정보 입력
		                  System.out.println("\n"+">> [병역정보 목록] << ");
		                  System.out.println("   1.미필");
		                  System.out.println("   2.군필");
		                  System.out.println("   3.면제");
		                  System.out.println("   4.해당사항 없음");
		                  
		                  String get_army_name = "";
		                  outer_army:
		                  do {
		                	  System.out.print("▶ 번호를 선택하세요 : ");
		                	  String yn = sc.nextLine();
		                	  switch (yn) {
							case "1":
								get_army_name = "미필";
								break outer_army;
							case "2":
								get_army_name = "군필";
								break outer_army;
							case "3":
								get_army_name = "면제";
								break outer_army;
							case "4":
								get_army_name = "해당없음";
								break outer_army;

							default:
								System.out.println("[경고] 메뉴에 없는 번호입니다.");
								break;
							}
		                	  
		                	  
		                	  
		                	  
		                  }while(true);
		                        
		                  
		                    
		                    
		                  
		                  // 희망연봉 입력
		                  String want_yearsal = "";
		                  do {
		                     
		                     System.out.println("\n[안내] 희망 연봉은 숫자로만 입력해주세요.(단위:\"만원\")");
		                     System.out.print("▶ 희망 연봉 : ");
		                     want_yearsal = sc.nextLine();
		                     
		                     if(want_yearsal.isEmpty() || !(Pattern.matches("^[0-9]*$",want_yearsal))) {
		                        System.out.println("<경고> 연봉 입력 형태를 확인하고 다시 입력해주세요.");
		                     }
		                     
		                     else {
		                         DecimalFormat decFormat = new DecimalFormat("###,###");
		                         want_yearsal = decFormat.format(Integer.parseInt(want_yearsal));
		                            break;
		                     }
		                     
		                  } while(true);
		                  
		               
		                  
		                  // 자격증 입력
		                  System.out.println("\n"+">> [자격증 목록] << ");
		                      int i = 0;
		                      List<Map<String,String>> list_map_5 = mdao.view_certifi_type();
		                     
		                      List<String> list_map_no_5 = new ArrayList<>();
		                        for(Map<String,String> map:list_map_5) {
		                           
		                            list_map_no_5.add(i, i+1 + "." + map.get("job_certifi_name")); 
		                            System.out.println( list_map_no_5.get(i));
		                            i++;
		                        }
		                        
		                        
		                        String get_certifi_name = null;
		                        outer:
		                        do {
		                           System.out.print("▶ 자격증 번호 선택 : ");
		                            String choice_num5 = sc.nextLine();
		                            
		                           for(int j = 0; j < list_map_no_5.size(); j++) {
		                              if(!(list_map_no_5.get(j).substring(0,list_map_no_5.get(j).indexOf(".")).equals(choice_num5))) {
		                              }
		                              else {
		                                 get_certifi_name = list_map_no_5.get(j).substring(list_map_no_5.get(j).indexOf(".")+1);
		                                 break outer;
		                              }
		                           }
		                           if(get_certifi_name == null) {
		                              System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
		                           }
		                        }while(true);
		                        
		                   String get_certifi_code = mdao.search_certifi_code(get_certifi_name);
		                  
		                  
		                  // 직무형태 입력
		                     //*** 직무형태를 입력하는 메소드 *** //
		                      System.out.println("\n"+">> [직무형태 목록] << ");
		                     i = 0;
		                      List<Map<String,String>> list_map_3 = mdao.view_job_type();
		                     
		                      List<String> list_map_no_3 = new ArrayList<>();
		                        for(Map<String,String> map:list_map_3) {
		                           
		                            list_map_no_3.add(i, i+1 + "." + map.get("job_type_name")); 
		                            System.out.println( list_map_no_3.get(i));
		                            i++;
		                        }
		                        
		                        
		                        String get_jobtype_name = null;
		                        outer:
		                        do {
		                           System.out.print("▶ 희망 직무형태 번호 선택 : ");
		                            String choice_num3 = sc.nextLine();
		                            
		                           for(int j = 0; j < list_map_no_3.size(); j++) {
		                              if(!(list_map_no_3.get(j).substring(0,list_map_no_3.get(j).indexOf(".")).equals(choice_num3))) {
		                              }
		                              else {
		                                 get_jobtype_name = list_map_no_3.get(j).substring(list_map_no_3.get(j).indexOf(".")+1);
		                                 break outer;
		                              }
		                           }
		                           if(get_jobtype_name == null) {
		                              System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
		                           }
		                        }while(true);
		                        
		                    String get_job_type_code = mdao.search_jobtype_code(get_jobtype_name);
		                   
		                     
		                  
		                  // 수상 및 활동내용 입력
		                  String award_act_list = "";
		                  do {
		                     System.out.print("\n▶ 수상 및 활동내용 입력 : ");
		                     award_act_list = sc.nextLine();
		                     
		                     if(award_act_list.isEmpty()) {
		                        System.out.println("<경고> 수상 및 활동내용은 공백을 제외하고 입력해주세요.");
		                     }
		                     else {
		                        break;
		                     }
		                  } while(true);
		               
		                  
		                  
		                  
		                  LocalDate now = LocalDate.now();
		                  
		                  System.out.println("\n[입력한 이력서 내용]");
		                  System.out.println("▷ 이력서명 : " + resume_name + "\n"
		                               + "▷ 병역정보 : " + get_army_name + "\n"
		                               + "▷ 희망연봉 : " + want_yearsal+ "만원" + "\n"
		                               + "▷ 취득한 자격증 : " + get_certifi_name + "\n"
		                               + "▷ 희망 직무형태 : " + get_jobtype_name + "\n"
		                               + "▷ 수상 및 활동내용 : " + award_act_list + "\n"
		                               + "▷ 작성일자 : " + now + "\n"
		                                 );
		                  
		                  do {
		                     System.out.println("▶ 위의 내용으로 이력서를 등록하시겠습니까? [Y/N] : ");
		                     String yn = "";
		                     yn = sc.nextLine();
		                     Map<String,String> map = new HashMap<>();
		                     if("y".equalsIgnoreCase(yn)) {
		                        System.out.println(">> 이력서 등록에 성공했습니다. <<");
		                        map.put("resume_name", resume_name);
		                        map.put("get_army_name", get_army_name);
		                        map.put("want_yearsal", want_yearsal);
		                        map.put("get_certifi_code", get_certifi_code);
		                        map.put("award_act_list", award_act_list);
		                        map.put("get_job_type_code", get_job_type_code);
		                        
		                       mdao.insert_resume(member,map); // 입력한 정보를 토대로 이력서를 작성하는 코드 생성
				                   
				                   
		                        
		                        
		                        
		                        break;
		                     }
		                     else if("n".equalsIgnoreCase(yn)){
		                        System.out.println(">> 이력서 등록을 취소했습니다. <<");
		                        break;
		                     }
		                     else {
		                        System.out.println("<경고> 입력값은 공백을 제외한 Y 또는 N 으로만 입력해주세요.");
		                     }
		                  } while(true);
		                  
		                  
		                  break;
						
						
					case "3": // 이력서 수정
						
						System.out.println("\n[이력서 수정 메뉴]");
		                  
		                  
						Map<String,String> paraMap = mdao.get_resume(member); // 원래 있던 이력서를 가지고와서 보여주는 메소드 생성.
						
						
						System.out.println("=== 기존 이력서 ===");
						System.out.println("1. 이력서 제목 : " + paraMap.get("self_introduce"));
						System.out.println("2. 병역정보 : " + paraMap.get("army"));
						System.out.println("3. 희망 연봉 : " + paraMap.get("want_yearsal"));
						System.out.println("4. 수상및 활동내역 : " + paraMap.get("award_act_list"));
						System.out.println("5. 자격증 명 : " + paraMap.get("certifi_name"));
						
						
						
						
						System.out.println("=== 이력서 수정 === ");
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						System.out.print("▶ 수정할 이력서 제목 : ");
						String change_self_introduce = sc.nextLine();
						
						if(change_self_introduce.isBlank()) {
							change_self_introduce = paraMap.get("self_introduce");
						}
						
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						
						System.out.println("== 병역정보 목록 == ");
						System.out.println("  1.  군필."
										 + "  2.  미필."
										 + "  3.  면제."
										 + "  4.  해당없음.");
						String change_army = "";
						outer:
						do {
							System.out.print("▶ 수정할 병역 정보 : ");
							change_army = sc.nextLine();
							
							if(change_army.isBlank()) {
								change_army = paraMap.get("army");
								break;
							}
							else {
								switch (change_army) {
								case "1":
									change_army = "군필";
									break outer;
								case "2":
									change_army = "미필";
									break outer;
								case "3":
									change_army =  "면제";
									break outer;
								case "4":
									change_army = "해당없음";
									break outer;

								default:
									System.out.println("[경고] 메뉴에 없는 번호입니다.");
									break;
								}
							}
							
							
						}while(true);
						
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						String change_want_yearsal = "";
						do {
							System.out.print("▶ 수정할 희망 연봉 [연봉은 반드시 숫자로만 입력하세요 예 5,000만원 > 5000] : ");
							change_want_yearsal = sc.nextLine();
							
							if(change_want_yearsal.isBlank()) {
								change_want_yearsal = paraMap.get("want_yearsal");
								break;
							}
							else {
								boolean is_all_number = true;
								for(int f = 0 ; f < change_want_yearsal.length(); f++) {
									if(!('0'<= change_want_yearsal.charAt(f) && change_want_yearsal.charAt(f) <= '9')) {
										System.out.println("[경고] 반드시 숫자만 입력하세요.");
										is_all_number = false;
										break;
									}
								}
								
								if(is_all_number) {
									
									int n_want_yearsal = Integer.parseInt(change_want_yearsal);
					            	DecimalFormat df = new DecimalFormat("#,###");
					            	change_want_yearsal = df.format(n_want_yearsal);
									change_want_yearsal += "만원";
									break;
								}
							}
							
						}while(true);
						
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						System.out.print("▶ 수정할 수상 및 활동내역 : ");
						
						String change_award_act_list = sc.nextLine();
						
						if(change_award_act_list.isBlank()) {
							change_award_act_list = paraMap.get("award_act_list");
						}
						
						
						// == 자격증 변경 == // 
						String certifi_code = "";
						outer:
						do {
							
							System.out.println("======== 자격증 목록 ========");
			            	  List<String> certifi_list = mdao.view_all_certifi_type_list(); // 모든 자격증 목록을 list에 담아주는 메소드 생성.
			            	  
			            	  List<String> no_certifi_list = new ArrayList<>();
			            	  for(int f = 0; f < certifi_list.size(); f++) {
			            		  String s = f+1+". "+certifi_list.get(f);
			            		  no_certifi_list.add(s);
			            		  System.out.println(no_certifi_list.get(f));
			            	  }
			            	  boolean is_all_number = true;
			            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
			            	  
			            	  do {
			            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 정보처리기사 > 4] : ");
			  	            	  String choice_no = sc.nextLine();
			  	            	  
			  	            	  if(choice_no.isBlank()) {
			  	            		certifi_code = mdao.get_certifi_code(paraMap.get("certifi_name")); // 입력한 자격증명과 일치하는 코드를 반환.
			  	            		  break outer;
			  	            	  }
			  	            	  else {
			  	            		  is_all_number = true;
			  	            		for(int f = 0;f < choice_no.length(); f++) {
				  	            		  if(!('0'<=choice_no.charAt(f) && choice_no.charAt(f) <= '9')) {
				  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
				  	            			  is_all_number = false;
				  	            			  break;
				  	            		  }
				  	            	  }
			  	            		String certifi_name = "";
			  	            		if(is_all_number) {
			  	            			boolean is_can_number = false;
			  	            			for(int f = 0;f<no_certifi_list.size();f++) {
			  	            				if(choice_no.equals(no_certifi_list.get(f).substring(0,no_certifi_list.get(f).indexOf(".")))) {
			  	            					certifi_name = no_certifi_list.get(f).substring(no_certifi_list.get(f).indexOf(".")+2);
			  	            					is_can_number = true;
			  	            					break;
			  	            				}
			  	            			}
			  	            			
			  	            			
			  	            			if(is_can_number) {
			  	            				certifi_code = mdao.get_certifi_code(certifi_name);
			  	            				break outer;
			  	            			}
			  	            			else {
			  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
			  	            			}
			  	            		}
			  	            		
			  	            		
			  	            	  }
			            	  }while(true);
							
						}while(true);
						
						
						
						
						
						
						paraMap.put("change_self_introduce", change_self_introduce);
						paraMap.put("change_army", change_army);
						paraMap.put("change_want_yearsal", change_want_yearsal);
						paraMap.put("change_award_act_list", change_award_act_list);
						paraMap.put("certifi_code", certifi_code);
						paraMap.put("my_certifi_no_1", paraMap.get("my_certifi_no_1"));
						
						
						
						int num = mdao.update_certifi(member,paraMap);
						
						if(num == 1) {
							System.out.println("[안내] 이력서 수정을 성공적으로 완료했습니다.");
						}
						else if( num == 0 ) {
							System.out.println("[안내] 이력서 수정을 취소했습니다.");
						}
						else {
							System.out.println("[경고] 이력서 수정을 실패했습니다. ");
						}
						
						
						
						
						break;
					case "4": // 이력서 삭제
						
						System.out.println("\n[이력서 목록]");
						System.out.println("-".repeat(80));
						System.out.println(" 이력서번호      이력서제목                        희망연봉             작성일자");
						System.out.println("-".repeat(80));
						
						list_map = mdao.view_my_all_resume(member);
			            
			            sb = new StringBuilder();
			            
			            for(Map<String, String> map : list_map) {
			            	sb.append("    "+map.get("pk_resume_code") + "\t");
				           	sb.append(map.get("self_introduce") + "\t");
				           	sb.append(map.get("want_yearsal") + "\t\t");
				           	sb.append(map.get("resume_date") + "\t");
			            }
			            System.out.println(sb.toString());
			            
			            //////////////////////////////////////////////////////////////////////
			            System.out.print("\n▶ 삭제할 이력서 번호 선택 : ");
			            choice_resume_no = sc.nextLine();
			            
			            mdao.view_resume_detail(choice_resume_no);  // tbl_resume 의 정보를 다 보여주는 DAO imple 메소드로 이동
			            
			            int n = mdao.delete_resume(choice_resume_no,member); // 선택한 이력서를 지워주는 메소드 생
						
			            if(n == 1) {
			            	System.out.println("[안내] 이력서를 성공적으로 삭제했습니다.");
			            }
			            else if (n == 0) {
			            	System.out.println("[안내] 이력서 삭제를 취소했습니다.");
			            }
			            else {
			            	System.out.println("[경고] 이력서 삭제를 실패했습니다.");
			            }
			            
			            
					case "5": // 이전 메뉴로
						
						break;
						
					default:
						System.out.println("[경고] 메뉴에 없는 번호입니다. 다시 선택하세요. \n");
						break;
						
				}// end of switch (select_resume)---------------------------
				
			} while(!("5".equals(select_resume))); // end of do~while(select_resume)---------------
				
			
			
			
			// 위에서 얻어온 정보들을 Map에 저장후 (insert)을 하게해주는 메소드 생성 
			MemberDTO mdto = new MemberDTO();
	        Map<String,String> paraMap = new HashMap<>();
	        
	        paraMap.put("PK_USER_ID",mdto.getPK_USER_ID() );
	        paraMap.put("USER_PASSWD",mdto.getUSER_PASSWD() );
	        paraMap.put("USER_NAME", mdto.getUSER_NAME());
	        paraMap.put("USER_EMAIL", mdto.getUSER_EMAIL() );
	        paraMap.put("USER_TEL", mdto.getUSER_TEL());
	        paraMap.put("USER_JUBUN", mdto.getUSER_JUBUN());
	        	        
			
		}// end of private void resume_menu(MemberDTO member, Scanner sc)
		
	
	
	
	
	
	// 모든 공고 목록을 보여주는 메소드 생성.
	private void view_all_recruit_notice() {
		
		MemberDAO mdao = new MemberDAO_imple();
		
		mdao.view_all_recruit_notice();
		
	}


	// 모집공고에 지원할 수 있게 해주는 메소드 생성.
	private void join_recruit_notice(MemberDTO member, Scanner sc) {
		
		MemberDAO mdao = new MemberDAO_imple();
		outer:
		do {
			System.out.print("▶ 지원할 공고 번호를 입력하세요 : ");
			String want_recruit_notice_no = sc.nextLine();
			
			boolean is_all_number = true;
			for(int i = 0; i < want_recruit_notice_no.length(); i++) {
				if(!('0'<=want_recruit_notice_no.charAt(i)&&want_recruit_notice_no.charAt(i)<='9')) {
					is_all_number = false;
					System.out.println("[경고] 반드시 숫자만 입력하세요");
					break;
				}
			}
			if(is_all_number) {
				
				Map<String,String> map = mdao.view_one_recruit_notice(want_recruit_notice_no); // 원하는 공고를 조회하는 메소드 생성.
				
				if(map.get("recruit_notice_name")!=null) {
          		  System.out.println("1. 공고명 : " + map.get("recruit_notice_name"));
          		  System.out.println("2. 연봉  : " + map.get("yearsal"));
          		  System.out.println("3. 직무  : " + map.get("job_type_name"));
          		  System.out.println("4. 자격요건 : " + map.get("quail"));
          		  System.out.println("5. 우대사항 : " + map.get("woodae"));
          		  System.out.println("6. 사내복지 : " + map.get("benefit"));
          		  System.out.println("7. 직책 : " + map.get("position_name"));
          		  System.out.println("8. 채용형태 : " + map.get("worktype_name"));
          		  System.out.println("9. 근무지역 : " + map.get("location_name"));
          		  
          		  for(int i = 0; i < 5; i++) {
          			  String recruit_step_name_ = "";
          			  recruit_step_name_ = "recruit_step_name_" + (i+1);
          			  if(map.get(recruit_step_name_)!=null) {
          				  System.out.println(10+i + ". " + (i+1) +"차전형 : " + map.get(recruit_step_name_));
          			  }
          			  else {
          				  break;
          			  }
          		  } // end of for =============
          		  System.out.println("== 공고 마감 일자 : " + map.get("recruit_finish_day"));
          		  
          		  
          		  do {
          			  System.out.print(">>> 위 공고에 지원하시겠습니까??? [Y/N] : ");
            		  String yn = sc.nextLine();
            		  if("y".equalsIgnoreCase(yn)) {
            			  int n = mdao.join_recruit_notice(member,want_recruit_notice_no); // 공고에 지원해주는 메소드 생성.
            			  
            			  if(n != 1) {
            				  System.out.println("[경고] 지원에 실패했습니다.");
            			  }
            			  break outer;
            		  }
            		  else if("n".equalsIgnoreCase(yn)) {
            			  System.out.println("[안내] 지원을 취소했습니다.");
            			  break outer;
            		  }
            		  else {
            			  System.out.println("[경고] 반드시 Y 혹은 N 두개중에 선택하세요.");
            		  }
          		  }while(true);
          		  
          		  
          		  
          		  
          		  
          		  
          		  
          	  }
          	  else {
          		  System.out.println("[경고] 해당 공고번호는 없는 공고입니다 다시한번 확인해주세요.");
          		  
          	  }
				
			}
			
			
			
		}while(true);
		
	}


	// 개인정보를 조회하게 해주는 메소드 생성.
	private void view_member_info(MemberDTO member, Scanner sc) {
		String view_member_info_no = "";
		outer_view_member:
		do {
			System.out.println("-------------------개인정보 조회 [" + member.getUSER_NAME()  + "님 로그인중]------------\n"
							 + "  1.개인정보 보기    2. 개인정보 수정    3. 회원탈퇴    4. 이전메뉴로 \n"
							 + "--------------------------------------------------------------------------\n"
								);
			
			System.out.print("▶ 메뉴번호 선택 : ");
			view_member_info_no = sc.nextLine();
			
			switch (view_member_info_no) {
			case "1": // 개인정보 보기
				System.out.println("===[개인정보 조회]===");
				System.out.println("▶ 비밀번호를 입력하세요 : ");
				String member_passwd = sc.nextLine();
				
				MemberDAO mdao = new MemberDAO_imple();
				
				Map<String,String> paraMap = new HashMap<>();
				paraMap.put("pk_user_id", member.getPK_USER_ID());
				paraMap.put("pass_wd", member_passwd);
				
				MemberDTO mdto = mdao.login(paraMap);
				if(mdto.getPK_USER_ID()!=null) { // 만약 가져온 값이 null이 아니라면.
					
					mdao.view_member_info(paraMap); // 회원 정보를 보여주는 메소드 생성.
					
				}
				else {
					System.out.println("[경고] 잘못된 비밀번호 입니다 비밀번호를 확인하세요.");
				}
				break;
			case "2": // 개인정보 수정
				System.out.println("===[개인정보 수정]===");
				System.out.println("▶ 비밀번호를 입력하세요 : ");
				member_passwd = sc.nextLine();
				
				mdao = new MemberDAO_imple();
				
				paraMap = new HashMap<>();
				paraMap.put("pk_user_id", member.getPK_USER_ID());
				paraMap.put("pass_wd", member_passwd);
				
				mdto = mdao.login(paraMap);
				if(mdto.getPK_USER_ID()!=null) { // 만약 가져온 값이 null이 아니라면.
					
					mdto = mdao.view_member_info(paraMap); // 회원 정보를 보여주는 메소드
					
					System.out.println("[안내] 수정하지 않고 기존의 값을 계속 유지하려면 그냥 '엔터'를 누르세요 ");
					
					// == 이름 수정 == // 
					String change_name = "";
					do {
						System.out.print("▶ 변경할 이름 : ");
						change_name = sc.nextLine();
						
						String before_name = mdto.getUSER_NAME();
						if(change_name.isBlank()) {
							change_name = mdto.getUSER_NAME();
							break;
						}
						else {
							mdto.setUSER_NAME(change_name);
							if(!mdto.getUSER_NAME().equals(before_name)) {
								break;
							}
							else {
								System.out.println("[경고] 기존과 동일한 이름입니다.");
							}
						}
					
					} while(true);
					
					// == 비밀번호 수정 == // 
					System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
					String change_passwd = "";
					do {
						System.out.print("▷ 비밀번호 : ");
						change_passwd = sc.nextLine();
						
						if(change_passwd.isBlank()) {
							change_passwd = mdto.getUSER_PASSWD();
							break;
						}
						else {
							mdto.setUSER_PASSWD(change_passwd); // 비밀번호 유효성 검사 
							if(!(mdto.getUSER_PASSWD() == null || mdto.getUSER_PASSWD().isBlank())) {
								break;
							}
							else {
								System.out.println("[경고] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요. ");
							}
						}
						
					}while(true); // end of do_while passwd
					
					
					// == email 수정 == // 
					
					System.out.println("\n[안내] 이메일 앞자리는 5~15 글자의 영문 대,소문자 및 숫자로만 이루어져야 합니다.");
					String change_email  = "";
					String before_email = mdto.getUSER_EMAIL();
					do {
						System.out.print("▶ 변경할 이메일 [이메일은 '@' 앞까지만 입력하세요] : ");
						change_email  = sc.nextLine();
						
						if(change_email.isBlank()) {
							change_email = mdao.getemail(paraMap);
							break;
						}
						else {
							// 이메일 앞자리가 영문과 숫자로만 이루어져있는지 확인.
							mdto.setUSER_EMAIL(change_email);
							if(!mdto.getUSER_EMAIL().equals(before_email)) {
								
								boolean is_exit = false;
								do {
									is_exit = false;
									System.out.println("[이메일 선택]");
									System.out.println("1. @naver.com\n"
											         + "2. @daum.net\n"
											         + "3. @gmail.com\n"
											         + "4. @kakao.com\n"
											         + "5. @saramin.co.kr\n");
									System.out.print("▷ 이메일 번호를 선택하세요 : ");
									String email_choice = sc.nextLine();
									
									// 메뉴에 있는번호만 선택할 수 있게 do_while문과 switch문 이용.
									switch (email_choice) {
									case "1":
										change_email += "@naver.com";
										break;
									case "2":
										change_email += "@daum.net";
										break;
									case "3":
										change_email += "@gmail.com";
										break;
									case "4":
										change_email += "@kakao.com";
										break;
									case "5":
										change_email += "@saramin.co.kr";
										break;
					
									default:
										is_exit = true;
										break;
									}
									if(is_exit) {
										System.out.println("[경고] 메뉴에 없는 번호입니다.");
									}
								}while(is_exit);// end of do_while
								
							break;	
								
							}
							else {
							}
						}
						
					}while(true);
					
					
					// == 지역코드 수정 == 
					
					String change_location_name = "";
					outer:
					do {
						System.out.println("\n▶ 변경할 주소 검색 : ");
						change_location_name = sc.nextLine();
						if(change_location_name.isBlank()) {
							change_location_name = mdto.getLOCATION_CODE();
							break outer;
						}
						else {
							List<String> address_list = mdao.search_address(change_location_name);
							
							System.out.println("--------------------------------------");
							System.out.println("    [" + change_location_name + "]이(가) 포함된 지역 목록");
							System.out.println("--------------------------------------");
							
							// select 해온 값을 담은 address_list를 하나하나 뽑아서 앞에 번호를 붙여주기위한 list생성. 
							List<String> address_list_no = new ArrayList<>();
							if(address_list.size()!=0) {
								for(int i = 0; i < address_list.size() ; i++) {
									// 1부터 순서대로 번호부여.
									address_list_no.add( i + 1 + ". "+address_list.get(i));
									System.out.println(address_list_no.get(i));
								}
								
								do {
									System.out.print("\n▷ 해당 지역에서 선택하시려면 1번 지역검색으로 돌아가시려면 2번을 선택하세요 : ");
									String no = sc.nextLine();
									
									switch (no) {
									case "1":
										
										System.out.print("▷ 거주하고있는 지역의 번호를 입력하세요 : ");
										String s_address_no = sc.nextLine();
										
										if(s_address_no.isBlank()) {
											System.out.println("[경고] 공백은 입력할 수 없습니다.");
											break;
										}
										
										change_location_name = "";
										// 뽑아온 list중에서 선택한 번호의 지역명을 번호없이 다시 지역명만 뽑아내는 for문.
										for(int i = 0;i<address_list_no.size();i++) {
											
											// 위에서 만든 숫자 + . + 지역명에서 . 앞까지의 숫자와 내가 입력한 숫자가 일치한다면
											if(address_list_no.get(i).substring(0,address_list_no.get(i).indexOf('.')).equals(s_address_no)) {
												
												// 숫자 + . + 지역명에서 . 뒷부분인 지역명만 따로 빼내 address 라는 변수에 다시 저장. 
												change_location_name = address_list_no.get(i).substring(address_list_no.get(i).indexOf('.')+2); 
												// 위에서 마지막에 +2가 붙은 이유는 . 뒤에 ' '(공백) 이 붙어있기 때문. 
												
												break;
											}
										}
										
										if(change_location_name.contains("전체")) {
											System.out.println("\n[경고] '전체' 가 들어있는 지역은 선택할 수 없습니다. 상세주소를 선택해주세요.");
											break;
										}
										else if (change_location_name.isBlank()) {
											System.out.println("[경고] 목록에 없는 번호입니다 다시한번 확인해주세요");
											continue outer;
										}
										else {
											change_location_name = mdao.search_address_code(change_location_name);
										}
										
										
										
										break outer;
										
									case "2":
										continue outer;
	
									default:
										System.out.println("[경고] 메뉴에 없는 번호 입니다.");
										break;
									}
									
								}while(true);
							}
							else {
								System.out.println("[경고] 해당 지역명은 존재하지 않습니다.");
							}
						}
					} while(true);
					
					
					
					// == 전화번호 입력 //
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					System.out.println("\n[안내] 전화번호는 '-'를 제외한 11자리의 숫자로만 입력하세요");
					String change_tel = "";
					boolean exit_tel = true;
					do {
						exit_tel = true;
						
						System.out.print("▶ 변경할 전화번호 : ");
						change_tel = sc.nextLine();
						
						if(change_tel.isBlank()) {
							change_tel = mdto.getUSER_TEL();
							break;
						}
						else {
							// 전화번호가 정확히 11자리인지 검사.
							if(change_tel.length() == 11) {
								
								char[] tel_arr = change_tel.toCharArray();
								
								// 입력한 전화번호가 모두 숫자로 이루어져있는지 확인하는 for문. 
								for(int i = 0; i< tel_arr.length; i++) {
									if(!('0' <= tel_arr[i] && tel_arr[i] <= '9')) {
										
										System.out.println("[경고] 전화번호는 반드시 숫자로만 이루어져야합니다.");
										exit_tel = false;
										break;
									}
								}
							}
							else {
								System.out.println("[경고] 전화번호는 반드시 11자리의 숫자로 이루어져야합니다.");
								exit_tel = false;
							}
							
							// 휴대폰번호 사이사이에 '-' 삽입.
							if(exit_tel) {
								change_tel = change_tel.substring(0,3) + "-" + change_tel.substring(3,7) + "-" + change_tel.substring(7);
							}
						}
							
					}while(!exit_tel);
					
					
					paraMap.put( "change_name" , change_name );
					paraMap.put( "change_passwd" , change_passwd );
					paraMap.put( "change_email" , change_email );
					paraMap.put( "change_location_name" , change_location_name );
					paraMap.put( "change_tel" , change_tel );
					
					// 위의 변경한 값을 토대로 업데이트를 진행해주는 메소드 생성
					int n = mdao.change_member_info(paraMap);
					
					if( n == 1) {
						System.out.println("[안내] 개인정보 수정이 성공적으로 완료되었습니다.");
					}
					else if( n == 0) {
						System.out.println("[안내] 개인정보 수정을 취소했습니다.");
					}
					else {
						System.out.println("[경고] 개인정보 수정을 실패했습니다.");
					}
					
				}
				else {
					System.out.println("[경고] 잘못된 비밀번호 입니다 비밀번호를 확인하세요.");
				}
				
				break;
				
			case "3": // 회원탈퇴
				mdao = new MemberDAO_imple();
				System.out.println("======[회원탈퇴]=====");
				System.out.print("▶ 비밀번호를 입력하세요 : ");
				String user_passwd = sc.nextLine();
				
				paraMap = new HashMap<>();
				paraMap.put("user_id", member.getPK_USER_ID());
				paraMap.put("user_passwd", user_passwd);
				
				int is_quit = mdao.quit_member(paraMap); // 비밀번호가 일치하면 회원탈퇴를 할 수 있게 해주는 메소드 생성.
				if(is_quit==1) {
					System.out.println("[안내] 회원 탈퇴가 성공적으로 이루어졌습니다.");
					break outer_view_member;
				}
				else if(is_quit == 0) {
					System.out.println("[안내] 회원 탈퇴를 취소했습니다.");
				}
				else {
				}
				
				
				break;
			case "4": // 이전메뉴로
				
				break;
	
			default:
				System.out.println("[경고] 메뉴에 없는 번호입니다. \n");
				break;
			}
		
		}while(!"4".equals(view_member_info_no));
		
		
		
	} // end of private void view_member_info(MemberDTO member, Scanner sc)


	
	
	// 구인회사 검색 메뉴
	 private void search_com(MemberDTO member, Scanner sc) {
		 String s_choice = null;
		 do {
		 	System.out.println("-------------------------구인회사 검색 [" + member.getUSER_NAME()  + "님 로그인중]------------------------\r\n"
		 						+ "  1. 모든기업 조회    2. 기업명 검색    3. 매출액 검색    4. 업종검색    5. 이전메뉴로 \r\n"
		 						+ "--------------------------------------------------------------------------\r\n"
		 						);
		 	
		 	
		 	System.out.print("▶ 메뉴번호 선택 :");
		 	 
		 	
		 	 s_choice = sc.nextLine(); 
		 	 
		 	MemberDAO Mdao = new MemberDAO_imple();
		      switch (s_choice) {
		      case "1":
		    	  
		    	  Mdao.view_all_company(); // 모든 회사를 보여주는 메소드 생성
		    	  
		    	  break;
		      case "2":   // 기업명 검색 
		    	  
		     	 System.out.println("▷ 검색어를 입력하세요 : ");
		     	 String search_com_name = sc.nextLine();
		     	 
		     	 if(search_com_name.isBlank()) {
		     		 System.out.println("[경고] 공백은 입력할 수 없습니다");
		     	 }
		     	 else {
		     		Map<String, String> paraMap = new HashMap<>();
			 		paraMap.put("COMPANY_NAME", search_com_name );
			 		
			 	List<Map<String, String>> mapList =Mdao.search_comname(paraMap);
			 	
			 		
			 		
			 StringBuilder sb = new StringBuilder();

			 for(Map<String, String> map : mapList) {
			 	sb.append(map.get("COMPANY_NAME") + "\t" 
			 			+ map.get("SALES") + "\t"
			 			+ map.get("NUMBER_OF_EMPLOYEE") + "\t"
			 			+ map.get("HOMEPAGE") + "\t"
			 			+ map.get("BUSS_TYPE_NAME") + "\t"
			 			+ map.get("COMPANY_ADDRESS") + "\t"
			 			+ map.get("COM_SIZE") + "\n");
			 }
			 
			 	  if(!sb.toString().isBlank()) {
			 		 System.out.println("--------------------------------------------------------------기업명에 ["+ search_com_name +"] 가 포함된 모든 기업정보 ----------------------------------------------------------------------\r\n"
				 				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			\r\n"
				 				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				 				);
			 		System.out.println(sb.toString());
			 	  }
			 	  else {
			 		  System.out.println("[경고] 기업명이 [" +  search_com_name  + "] 인 기업이 존재하지 않습니다.");
			 	  }
		     	 }
		      

		 		break;
		 		
		         
		       case "3": // 매출액 검색
		    	   System.out.println("[안내] 검색한 매출액 이상의 모든 회사를 보여줍니다 , 매출액은 반드시 숫자로만 입력하세요.");
		    	   do {
		    		   boolean is_all_number = true;
		    		   System.out.println("▷ 매출액을 검색하세요 : ");
		    		   String search_sales = sc.nextLine();
		    		   if(search_sales.isBlank()) {
		    			   System.out.println("[경고] 공백은 입력할 수 없습니다.");
		    		   }
		    		   else {
		    			   char[] search_sales_arr = search_sales.toCharArray();
			    		   for(int i = 0;i < search_sales_arr.length; i++) {
			    			   if(!('0'<=search_sales_arr[i] && search_sales_arr[i] <= '9')) {
			    				   System.out.println("[경고] 반드시 숫자만 입력하세요.");
			    				   is_all_number = false;
			    				   break;
			    			   }
			    		   }
			    		   
			    		   if(is_all_number) {
			    			   
			    			   MemberDAO mdao = new MemberDAO_imple();
			    			   mdao.search_company_sales(search_sales); // 입력한 매출액 이상의 모든 회사를 조회하는 메소드 생성
			    			   
			    			   break;
			    		   }
		    		   }
		           
		    	   }while(true);
		    	  
		    	   
		    	   break;
		       case "4":   // 업종검색
		     	System.out.println("▷ 검색어를 입력하세요 : ");
		     	String search_BUSS = sc.nextLine();
		     	
		     	if(search_BUSS.isBlank()) {
		     		System.out.println("[경고] 공백은 입력할 수 없습니다.");
		     	}
		     	else {
		     		Map<String, String> paraMap1 = new HashMap<>();
			 		paraMap1.put("BUSS_TYPE_NAME", search_BUSS.toUpperCase() );
			 		
			 		
			 		List<Map<String, String>> mapList1 =Mdao.search_buss_type(paraMap1);
			 		
			 		
			 		
			 		
			 		StringBuilder sb1 = new StringBuilder();

			 		for(Map<String, String> map : mapList1) {
			 			sb1.append(map.get("COMPANY_NAME") + "\t" 
			 					+ map.get("SALES") + "\t"
			 					+ map.get("NUMBER_OF_EMPLOYEE") + "\t"
			 					+ map.get("HOMEPAGE") + "\t"
			 					+ map.get("BUSS_TYPE_NAME") + "\t"
			 					+ map.get("COMPANY_ADDRESS") + "\t"
			 					+ map.get("COM_SIZE") + "\n");
			 		}
			 		     if(sb1.toString().length()>0) {
			 		    	System.out.println("--------------------------------------------------------------"+search_BUSS +"에 대한 기업정보 ----------------------------------------------------------------------\r\n"
					   				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			\r\n"
					   				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
					   				);

				 		     System.out.println(sb1.toString());
			 		     }
			 		     else {
			 		    	 System.out.println("[안내] 검색된 기업 정보가 존재하지 않습니다.");
			 		     }
			 		     
		     	}
		 		
		       	break;
		        
		       case "5":	// 이전 메뉴로 
		       	break;
		         
		       default:
		    	   
		        	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
		       	break; 
		       
		      } //END OF  switch (s_choice) 
		      } while(!"5".equals(s_choice));
		 
	} // end of private void search_com(MemberDTO member, Scanner sc)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// == 구직자 회원가입을 할 수 있게 해주는 메소드 생성
	public void mem_join(Scanner sc) {
		
		MemberDTO mdto = new MemberDTO();
		MemberDAO mdao = new MemberDAO_imple();
		
		
		
		System.out.println("\n[구직자 회원가입]\n");
		
		
		// == 아이디 입력 == //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("[안내] 아이디는 5~20자의 영문, 숫자의 조합으로 입력하세요.");
		outer:
		do {
			System.out.print("▷ 아이디 : ");
			String userid = sc.nextLine();
			
			mdto.setPK_USER_ID(userid);
			
			if(!(mdto.getPK_USER_ID()==null || mdto.getPK_USER_ID().isBlank())) {
				
				// 입력한 아이디가 이미 존재하는 아이디인지 확인하기위한 dbao 메소드 생성
				int n = mdao.check_userid(userid);
				if( n == 1 ) {
					System.out.println("[경고] 이미 존재하는 아이디 입니다. 새로운 아이디를 입력해주세요");
					continue outer;
				}
				else {
					
				}
				break;
			}
			else {
			}
			
		} while(true); // end of do_while userid ====================
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 비밀번호 입력 == //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
		do {
			System.out.print("▷ 비밀번호 : ");
			String passwd = sc.nextLine();
			mdto.setUSER_PASSWD(passwd); // 비밀번호 유효성 검사 
			if(!(mdto.getUSER_PASSWD() == null || mdto.getUSER_PASSWD().isBlank())) {
				break;
			}
			else {
				System.out.println("[경고] 비밀번호 조건을 확인하여 다시 입력하여 주십시오.");
			}
		}while(true); // end of do_while passwd
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 이름 입력 == //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 성함은 2글자 이상 6글자 이하의 한글로만 이루어져야 합니다.");
		do {
			System.out.print("▷ 성함 : ");
			String name = sc.nextLine();
			
			mdto.setUSER_NAME(name); // 이름 유효성 검사 
			if(!(mdto.getUSER_NAME() == null || mdto.getUSER_NAME().isBlank())) {
				break;
			}
			else {
			}
			
		} while(true);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 주민번호 입력 == // 
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 주민등록번호는 '-' 를 제외한 13자리의 숫자로 입력하세요");
		
		boolean exit_jubun = true;
		do {
			exit_jubun = true;
			
			System.out.print("▷ 주민등록번호 : ");
			String jubun = sc.nextLine();
			
			// 주민번호가 정확히 13자리인지 확인.
			if(jubun.length() == 13) {
				
				char[] jubun_arr = jubun.toCharArray();
				
				// 주민번호가 숫자로만 이루어져있는지 확인하기위한 반복문 
				for(int i = 0; i< jubun_arr.length; i++) {
					if(!('0' <= jubun_arr[i] && jubun_arr[i] <= '9')) {
						exit_jubun = false;
						System.out.println("[경고] 주민등록번호는 반드시 숫자로만 이루어져야합니다.");
						break;
					}
				}
			}
			else {
				System.out.println("[경고] 주민등록번호는 반드시 13자리의 숫자로 이루어져야합니다.");
				exit_jubun = false;
			}
			
			if(exit_jubun) {
			// 위의 조건을 모두 충족했을 때 주민번호 앞자리가 달력에 존재하는 값인지, 뒷자리의 첫번째 자리가 1,2,3,4 중 하나인지 확인하는 유효성 검사.
				mdto.setUSER_JUBUN(jubun);
				
				if(mdto.getUSER_JUBUN() != null) {
					break;
				}
				else {
					System.out.println("[경고] 주민번호가 잘못 입력되었습니다. 올바르게 입력해주세요.");
					exit_jubun = false;
				}
			}
		}while(!exit_jubun);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// == 이메일 입력 == // 
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 이메일 앞자리는 5~15 글자의 영문 대,소문자 및 숫자로만 이루어져야 합니다.");
		String email_front  = "";
		do {
			System.out.print("▷ 이메일 [이메일은 '@' 앞까지만 입력하세요] : ");
			email_front  = sc.nextLine();
			
			// 이메일 앞자리가 영문과 숫자로만 이루어져있는지 확인.
			mdto.setUSER_EMAIL(email_front);
			if(mdto.getUSER_EMAIL() != null) {
				
				
				boolean is_exit = false;
				do {
					is_exit = false;
					System.out.println("[이메일 선택]");
					System.out.println("1. @naver.com\n"
							         + "2. @daum.net\n"
							         + "3. @gmail.com\n"
							         + "4. @kakao.com\n"
							         + "5. @saramin.co.kr\n");
					System.out.print("▷ 이메일 번호를 선택하세요 : ");
					String email_choice = sc.nextLine();
					
					// 메뉴에 있는번호만 선택할 수 있게 do_while문과 switch문 이용.
					switch (email_choice) {
					case "1":
						email_front += "@naver.com";
						break;
					case "2":
						email_front += "@daum.net";
						break;
					case "3":
						email_front += "@gmail.com";
						break;
					case "4":
						email_front += "@kakao.com";
						break;
					case "5":
						email_front += "@saramin.co.kr";
						break;
	
					default:
						is_exit = true;
						break;
					}
					if(is_exit) {
						System.out.println("[경고] 메뉴에 없는 번호입니다.");
					}
				}while(is_exit);// end of do_while
				
			break;	
				
			}
			else {
			}
		}while(true);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 전화번호 입력 //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 전화번호는 '-'를 제외한 11자리의 숫자로만 입력하세요");
		String tel = "";
		boolean exit_tel = true;
		do {
			exit_tel = true;
			
			System.out.print("▷ 전화번호 : ");
			tel = sc.nextLine();
			
			// 전화번호가 정확히 11자리인지 검사.
			if(tel.length() == 11) {
				
				char[] tel_arr = tel.toCharArray();
				
				// 입력한 전화번호가 모두 숫자로 이루어져있는지 확인하는 for문. 
				for(int i = 0; i< tel_arr.length; i++) {
					if(!('0' <= tel_arr[i] && tel_arr[i] <= '9')) {
						
						System.out.println("[경고] 전화번호는 반드시 숫자로만 이루어져야합니다.");
						exit_tel = false;
						break;
					}
				}
			}
			else {
				System.out.println("[경고] 전화번호는 반드시 11자리의 숫자로 이루어져야합니다.");
				exit_tel = false;
			}
			
			// 휴대폰번호 사이사이에 '-' 삽입.
			if(exit_tel) {
				
				if(tel.substring(0,3).equals("010")) {
					tel = tel.substring(0,3) + "-" + tel.substring(3,7) + "-" + tel.substring(7);
				}
				else {
					System.out.println("[경고] 잘못된 전화번호 입니다 다시 확인해주세요.");
					exit_tel = false;
				}
				
				
			}
			
			
		}while(!exit_tel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 지역 입력 == // 
		//- 지역 : 서울시
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String address_code = "";
		System.out.println("\n[안내] 지역은 시 혹은 구 까지만 입력하세요.");
		outer:
		do {
			System.out.print("▷ 거주지역 : ");
			String address = sc.nextLine();
			
			
			//mdao를 이용하여 검색한 지역이 포함되어있는 모든 지역명을 select해오기 위한 search_address 메소드 생성.
			List<String> address_list = mdao.search_address(address);
			
			System.out.println("--------------------------------------");
			System.out.println("    [" + address + "]이(가) 포함된 지역 목록");
			System.out.println("--------------------------------------");
			
			// select 해온 값을 담은 address_list를 하나하나 뽑아서 앞에 번호를 붙여주기위한 list생성. 
			List<String> address_list_no = new ArrayList<>();
			if(address_list.size()!=0) {
				for(int i = 0; i < address_list.size() ; i++) {
					// 1부터 순서대로 번호부여.
					address_list_no.add( i + 1 + ". "+address_list.get(i));
					System.out.println(address_list_no.get(i));
				}
				
				do {
					System.out.print("\n▷ 해당 지역에서 선택하시려면 1번 지역검색으로 돌아가시려면 2번을 선택하세요 : ");
					String no = sc.nextLine();
					
					switch (no) {
					case "1":
						
						System.out.print("▷ 거주하고있는 지역의 번호를 입력하세요 : ");
						String address_no = sc.nextLine();
						if(address_no.isBlank()) {
							System.out.println("[경고] 공백은 입력할 수 없습니다.");
							break;
						}
						
						address = "";
						// 뽑아온 list중에서 선택한 번호의 지역명을 번호없이 다시 지역명만 뽑아내는 for문.
						for(int i = 0;i<address_list_no.size();i++) {
							
							// 위에서 만든 숫자 + . + 지역명에서 . 앞까지의 숫자와 내가 입력한 숫자가 일치한다면
							if(address_list_no.get(i).substring(0,address_list_no.get(i).indexOf('.')).equals(address_no)) {
								
								// 숫자 + . + 지역명에서 . 뒷부분인 지역명만 따로 빼내 address 라는 변수에 다시 저장. 
								address = address_list_no.get(i).substring(address_list_no.get(i).indexOf('.')+2); 
								// 위에서 마지막에 +2가 붙은 이유는 . 뒤에 ' '(공백) 이 붙어있기 때문. 
								
								break;
							}
						}
						if(address.isBlank()) {
							System.out.println("[경고] 목록에 없는 번호입니다.");
							break;
						}
						if(address.contains("전체")) {
							System.out.println("\n[경고] '전체' 가 들어있는 지역은 선택할 수 없습니다. 상세주소를 선택해주세요.");
							break;
						}
						
						// 위에서 뽑아온 지역명을 이용해서 다시한번 해당 지역명이 가지는 코드를 select해오고 지역코드를 address_code에 저장.
						address_code = mdao.search_address_code(address);
						
						break outer;
						
					case "2":
						continue outer;

					default:
						System.out.println("[경고] 메뉴에 없는 번호 입니다.");
						break;
					}
					
				}while(true);
			}
			else {
				System.out.println("[경고] 해당 지역명은 존재하지 않습니다.");
			}
			
		} while(true); // end of do_while ----------
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// 위에서 얻어온 정보들을 Map에 저장후 회원가입(insert)을 하게해주는 join_member 메소드 생성 
		Map<String,String> paraMap = new HashMap<>();
		
		paraMap.put("user_id",mdto.getPK_USER_ID() );
		paraMap.put("passwd",mdto.getUSER_PASSWD() );
		paraMap.put("name", mdto.getUSER_NAME());
		paraMap.put("jubun", mdto.getUSER_JUBUN());
		paraMap.put("email", email_front);
		paraMap.put("tel", tel);
		paraMap.put("address_code", address_code);
		
		
		// 리턴값으로 회원가입 유무를 알 수 있게 int로 지정.
		int n = mdao.join_member(paraMap);
		
		if(n == 1) {
			System.out.println(">> 축하드립니다 회원가입에 성공하셨습니다 <<");
		}
		else if(n == 0) {
			System.out.println(">> 회원가입을 취소하셨습니다 <<");
		}
		else {
			System.out.println(">> 회원가입을 실패했습니다 <<");
		}
		
	} // end of public void mem_join(Scanner sc) ---------------------------------------------------------

}
=======
package member.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Member_Controller {

	
	public void mem_login(Scanner sc) {
		
		String choice_menu = "";
		exit_login:
		do {
			System.out.println("--------------------------- [구직자 로그인] ---------------------------\n"
					         + "  1. 아이디, 비밀번호 입력.     2. 아이디, 비밀번호 찾기.    3.메인메뉴로 이동.\n"
					         + "--------------------------------------------------------------------");
			System.out.print("▶ 메뉴 번호 선택 : ");
			choice_menu = sc.nextLine();
			
			switch (choice_menu) {
			case "1": // 아이디 비밀번호 입력
				MemberDAO mdao = new MemberDAO_imple();
				boolean isLogin = false; 
				
			    System.out.println("\n >>> --- 로그인 --- <<<");
			    
			    System.out.print("▷ 아이디 : ");
			    String userid = sc.nextLine();
			    System.out.print("▷ 비밀번호 : ");
			    String passwd = sc.nextLine();
			    
			    // mdao.login(userid, passwd);   가능하지만 spring 은 놉! => map 활용하기
			    Map<String, String> paraMap = new HashMap<>();      // key : string / values : string
			    paraMap.put("pk_user_id", userid);
			    paraMap.put("pass_wd", passwd);
			    
			    MemberDTO member = mdao.login(paraMap);
			    
			    if("0".equals(member.getUSER_STATUS())){
			    	System.out.println("[경고] 탈퇴한 회원입니다.");
			    }
			    else if(member.getPK_USER_ID()!=null) {
			     System.out.println(member.getUSER_NAME()+"님이 로그인 하셨습니다");
			     isLogin = true;
			    }
			    else {
			     System.out.println("[경고] 아이디 혹은 비밀번호가 일치하지 않습니다.");
			    }
			    
			    
			    ///////////////////////////////////////////////////////////////
			    String s_choice = ""; 
			    do {  
				if(isLogin == true) {
			    	  System.out.println("---------------------------------------구직자 메뉴 [" + member.getUSER_NAME() +"님 로그인중]--------------------------------\r\n"
			    	  				   + "        1.구인회사 검색    2.모집공고 검색      3.개인정보 조회     4.이력서 메뉴      5.로그아웃\r\n"
			    	  				   + "-----------------------------------------------------------------------------------------------\r\n"
			    	  						);
			    	  
			    	  System.out.print("▷ 메뉴번호 선택 : ");
		             
			    	  s_choice = sc.nextLine();
			    	  
			    	  switch (s_choice) {
		             case "1":   // 구인회사 검색
		                 search_com(member,sc);
		                 break;
		              case "2": // 모집공고검색
		            	  recruit_notice(member,sc); 
		           	     break;
		              case "3":   // 개인정보조회
		              	 view_member_info(member,sc);
		          
		              	 break;
		               
		              case "4":	// 이력서 조회
		            	  resume_menu(member,sc);
		              	 break;
		              	
		              case "5":	// 로그아웃
		                  System.out.println(">>> 로그아웃 되었습니다. <<<\n");
		                  break exit_login;
		                 
		              default:
		              	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
		              	break;                 
		              }   // end of switch (s_Choice)--------------------
			    	  
		           }   // end of if(isLogin == true)----------------------------
				
				else {
					break;
				}
					
				} while(!"5".equals(s_choice));   // return 프로그램 종료일 때만 나갈 수 있게 하기
				break;
			case "2": // 아이디 비밀번호 찾기
				mdao = new MemberDAO_imple();
				String find_menu_no = "";
				boolean find_menu_exit = false;
				do {
					find_menu_exit = true;
					System.out.println("-------------- [어떤 정보를 찾으시겠습니까??] --------------\n"
							         + "  1. 아이디 찾기     2. 비밀번호 찾기     3.로그인 메뉴로 이동\n"
							         + "----------------------------------------------------");
					System.out.print("▶ 메뉴번호 선택 : ");
					find_menu_no = sc.nextLine();
					switch (find_menu_no) {
					case "1": // 아이디 찾기 
						MemberDTO mdto = new MemberDTO();
						boolean exit_jubun = true;
						String jubun = "";
						do {
							exit_jubun = true;
							
							System.out.println("\n[안내] 주민등록번호는 '-' 를 제외한 13자리의 숫자로 입력하세요");
							System.out.print("▷ 주민등록번호 : ");
							jubun = sc.nextLine();
							
							// 주민번호가 정확히 13자리인지 확인.
							if(jubun.length() == 13) {
								
								char[] jubun_arr = jubun.toCharArray();
								
								// 주민번호가 숫자로만 이루어져있는지 확인하기위한 반복문 
								for(int i = 0; i< jubun_arr.length; i++) {
									if(!('0' <= jubun_arr[i] && jubun_arr[i] <= '9')) {
										exit_jubun = false;
										System.out.println("[경고] 주민등록번호는 반드시 숫자로만 이루어져야합니다.");
										break;
									}
								}
							}
							else {
								System.out.println("[경고] 주민등록번호는 반드시 13자리의 숫자로 이루어져야합니다.");
								exit_jubun = false;
							}
							
							if(exit_jubun) {
							// 위의 조건을 모두 충족했을 때 주민번호 앞자리가 달력에 존재하는 값인지, 뒷자리의 첫번째 자리가 1,2,3,4 중 하나인지 확인하는 유효성 검사.
								mdto.setUSER_JUBUN(jubun);
								
								if(mdto.getUSER_JUBUN() != null) {
									jubun = jubun.substring(0,6) + "-" + jubun.substring(6);
									break;
								}
								else {
									exit_jubun = false;
								}
							}
						}while(!exit_jubun);
						
						System.out.print("▷ 비밀번호 : ");
						passwd = sc.nextLine();
						
						Map<String,String> find_id_map = new HashMap<>();
						find_id_map.put("user_jubun", jubun);
						find_id_map.put("user_passwd", passwd);
						
						
						String user_id = mdao.find_user_id(find_id_map);
						
						if(user_id != null) {
							System.out.println("▶ 아이디 : " + user_id);
						}
						else {
							System.out.println("[경고] 입력하신 정보가 일치하지 않습니다.");
						}
						break;
					case "2": // 비밀번호 찾기
						mdto = new MemberDTO();
						
						System.out.print("▷ 아이디 : ");
						user_id = sc.nextLine();
						
						exit_jubun = true;
						jubun = "";
						do {
							exit_jubun = true;
							
							System.out.println("\n[안내] 주민등록번호는 '-' 를 제외한 13자리의 숫자로 입력하세요");
							System.out.print("▷ 주민등록번호 : ");
							jubun = sc.nextLine();
							
							// 주민번호가 정확히 13자리인지 확인.
							if(jubun.length() == 13) {
								
								char[] jubun_arr = jubun.toCharArray();
								
								// 주민번호가 숫자로만 이루어져있는지 확인하기위한 반복문 
								for(int i = 0; i< jubun_arr.length; i++) {
									if(!('0' <= jubun_arr[i] && jubun_arr[i] <= '9')) {
										exit_jubun = false;
										System.out.println("[경고] 주민등록번호는 반드시 숫자로만 이루어져야합니다.");
										break;
									}
								}
							}
							else {
								System.out.println("[경고] 주민등록번호는 반드시 13자리의 숫자로 이루어져야합니다.");
								exit_jubun = false;
							}
							
							if(exit_jubun) {
							// 위의 조건을 모두 충족했을 때 주민번호 앞자리가 달력에 존재하는 값인지, 뒷자리의 첫번째 자리가 1,2,3,4 중 하나인지 확인하는 유효성 검사.
								mdto.setUSER_JUBUN(jubun);
								
								if(mdto.getUSER_JUBUN() != null) {
									jubun = jubun.substring(0,6) + "-" + jubun.substring(6);
									break;
								}
								else {
									exit_jubun = false;
								}
							}
						}while(!exit_jubun);
						
						Map<String,String> find_user_passwd_map = new HashMap<>();
						find_user_passwd_map.put("jubun", jubun);
						find_user_passwd_map.put("user_id", user_id);
						
						
						int is_change_user_passwd = mdao.find_user_passwd(find_user_passwd_map);
						
						if ( is_change_user_passwd == 1) {
							System.out.println("[안내] 비밀번호가 성공적으로 변경되었습니다.");
						}
						else if (is_change_user_passwd == 0) {
							System.out.println("[안내] 비밀번호 변경을 취소하였습니다.");
						}
						else {
							System.out.println("[안내] 입력하신 정보가 일치하지 않습니다.");
						}
						
						
						break;
					case "3": // 메인 메뉴로 이동
						
						break;
					default:
						System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.\n");
						find_menu_exit = false;
						break;
					}
				} while(!find_menu_exit);
				
				break;
			case "3":
				
				break;
	
			default:
				System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.\n");
				break;
			}
		} while(!("3".equals(choice_menu))); // end of do_while -----------------
		
		
		
	} // end of public void mem_login(Scanner sc)
	
	
	private void recruit_notice(MemberDTO member, Scanner sc) {
		  String s_choice = null;
		do {
		System.out.println("-------------------모집공고 검색 [" + member.getUSER_NAME()   + "님 로그인중]-----------------\r\n"
							+ " 1.모든공고목록보기     2.공고명검색      3.직위검색      4.연봉검색      5.근무형태검색      6.기업규모검색    7.공고지원      8.이전메뉴로 \r\n"
							+ "--------------------------------------------------------------------------\r\n"
							);


				System.out.print("▶ 메뉴번호 선택 :");
				
				
				s_choice = sc.nextLine(); 
				
				MemberDAO Mdao = new MemberDAO_imple();
				switch (s_choice) {
				
				
				
				
				case "1":
					
					view_all_recruit_notice(); // 모든 공고 목록을 보여주는 메소드 생성
					
					
					break;
				case "2":   // 공고명 
				
				System.out.println("▷ 검색어를 입력하세요 : ");
				String recruit_notice_name = sc.nextLine();
				if(recruit_notice_name.isBlank()) {
					System.out.println("[경고] 공백은 입력할 수 없습니다.");
					break;
				}
				else {
					Map<String, String>  paraMap  = new HashMap<>();
					paraMap.put("NOTICE_NAME", recruit_notice_name );
					
					List<Map<String, String>> mapList =Mdao.recruit_notice_name(paraMap);
					
					
					
					
					StringBuilder sb = new StringBuilder();
					
					for(Map<String, String> map : mapList) {
					sb.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
							+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
							+ map.get("COMPANY_NAME") + "\t"
							+ map.get("LOCATION_NAME") + "\t"
							+ map.get("YEARSAL") + "\t"
							+ map.get("WORKTYPE_NAME") + "\t"
							+ map.get("POSITION_NAME") + "\t"
							+ map.get("RECRUIT_FINISH_DAY") + "\n");
					}
					if(sb.toString().length()!=0) {
						System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
								+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉           근무형태         공고마감일      \r\n"
								+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
								
								);

						System.out.println(sb.toString()+"\n");
					}
					else {
						System.out.println("[경고] 해당하는 모집공고가 존재하지 않습니다.");
					}
					break;
				}
				
				
				
				
				
				case "3": // 직종
					System.out.println("▷ 검색어를 입력하세요 : ");
					String position_name = sc.nextLine();
					
					if(position_name.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String, String>  paraMap1  = new HashMap<>();
						paraMap1.put("PN", position_name);
						List<Map<String, String>> mapList1 =Mdao.position_name(paraMap1);
						
						StringBuilder sb1 = new StringBuilder();
						
						for(Map<String, String> map : mapList1) {
							sb1.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
									+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
									+ map.get("COMPANY_NAME") + "\t\t"
									+ map.get("WORKTYPE_NAME") + "\t\t"
									+ map.get("LOCATION_NAME") + "\t\t"
									+ map.get("YEARSAL") + "\t\t"
									+ map.get("POSITION_NAME") + "\t\t"
									+ map.get("RECRUIT_FINISH_DAY") + "\t\n");
							}
						
						
						
						if(mapList1.size()!=0) {
							System.out.println("------------------------------------------------------[공고 목록]---------------------------------------------------------------------------------------------------------------\r\n"
									+ "    공고번호 			   공고명                                         기업명           직위         근무지역                   연봉           근무형태         공고마감일      \r\n"
									+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
							System.out.println(sb1.toString());
						}
						else {
							System.out.println("[경고] 입력하신 사항에 해당하는 모집공고가 존재하지 않습니다. ");
						}
							
					}
					
					
						
					break;
					
				 

				case "4":   // 연봉
					
					String search_yearsal = "";
					System.out.println("[안내] 연봉을 입력하세요 입력하신 연봉보다 많은 공고는 모두 표시됩니다.");
					do {
						System.out.println("▶ 연봉 [6,000만원 > 6000 입력] : ");
						search_yearsal = sc.nextLine();
						
						if(search_yearsal.isBlank()) {
							System.out.println("[경고] 공백은 입력할 수 없습니다.");
							break;
						}
						else {
							boolean is_all_num = true;
							char[] search_yearsal_arr = search_yearsal.toCharArray();
							for(int i = 0; i < search_yearsal_arr.length; i++) {
								if(!('0'<=search_yearsal_arr[i] && search_yearsal_arr[i] <= '9')) {
									is_all_num = false;
								}
							}
							if(!is_all_num) {
								System.out.println("[경고] 반드시 숫자만 입력하세요");
							}
							else {
								Mdao.search_yearsal(search_yearsal); // 입력한 연봉보다 큰 모집공고를 모두 보여주는 메소드 생성
								break;
							}
						}
					
					} while(true);
					
				
					
					break;
				
				
				case "5" : // 근무형태
					System.out.println("▷ 검색어를 입력하세요 : ");
					String work_tp = sc.nextLine();
					
					if(work_tp.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String, String> paraMap11 = new HashMap<>();
						paraMap11.put("WT", work_tp);
						List<Map<String, String>> mapList11 = Mdao.work_tp(paraMap11);
						
						
						StringBuilder sb11 = new StringBuilder();
						
						for(Map<String, String> map : mapList11) {
							sb11.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
									+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
									+ map.get("COMPANY_NAME") + "\t"
									+ map.get("LOCATION_NAME") + "\t"
									+ map.get("YEARSAL") + "\t"
									+ map.get("WORKTYPE_NAME") + "\t"
									+ map.get("POSITION_NAME") + "\t"
									+ map.get("RECRUIT_FINISH_DAY") + "\n");
							}
						if(mapList11.size()!=0) {
							System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
									+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉           근무형태         공고마감일      \r\n"
									+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
							System.out.println(sb11.toString());
						}
						else {
							System.out.println("[경고] 검색된 공고가 존재하지 않습니다.");
						}
					}
					
					break;
					
				case "6" : // 기업규모
					System.out.println("▷ 검색어를 입력하세요 : ");
					String com_size = sc.nextLine();
					
					if(com_size.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String, String> paraMap111 = new HashMap<>();
						paraMap111.put("CS", com_size);
						List<Map<String, String>> mapList111 = Mdao.com_size(paraMap111);
						
						StringBuilder sb111 = new StringBuilder();
						
						for(Map<String, String> map : mapList111) {
							sb111.append(map.get("PK_RECRUIT_NOTICE_CODE") + "\t" 
									+ map.get("RECRUIT_NOTICE_NAME") + "\t\t"
									+ map.get("COMPANY_NAME") + "\t"
									+ map.get("LOCATION_NAME") + "\t"
									+ map.get("YEARSAL") + "\t"
									+ map.get("COM_SIZE") + "\t"
									+ map.get("WORKTYPE_NAME") + "\t"
									+ map.get("POSITION_NAME") + "\t"
									+ map.get("RECRUIT_FINISH_DAY") + "\n");
							}
						if(mapList111.size()!=0) {
							System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
									+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉        기업규모    근무형태         공고마감일      \r\n"
									+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
									
									);
							System.out.println(sb111.toString());
						}
						else {
							System.out.println("[경고] 검색된 정보가 없습니다. 다시한번 확인해주세요.");
						}
							
					}
					
					break;
				
				case "7" : // 공고 지원하기
					
					join_recruit_notice(member,sc); // 모집공고에 지원할 수 있게 해주는 메소드 생성.
					
					break;
				case "8" : // 이전메뉴로
					
					break;
				
				default:
				 
					System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
					break; 
				
				} //END OF  switch (s_choice) 
				} while(!"8".equals(s_choice));
			
		}
	
	
	
	
	// == 이력서 메뉴를 실행해주는 메소드 생성
		private void resume_menu(MemberDTO member, Scanner sc) {
			
			
			MemberDAO mdao = new MemberDAO_imple();
			
			String select_resume = "";
			do {
				System.out.println("------------------- 이력서 메뉴 [" + member.getUSER_NAME()  + "님 로그인중]-------------------\n"
						 + "  1.내 이력서 보기    2. 이력서 등록    3. 이력서 수정    4. 이력서 삭제    5. 이전메뉴로 \n"
						 + "--------------------------------------------------------------------------\n"
							);
				System.out.print("▶ 번호 선택 : ");
				select_resume = sc.nextLine();
				
				switch (select_resume) {
				
					case "1":// 내 이력서 목록보기
						
			            System.out.println("\n[이력서 목록]");
						System.out.println("-".repeat(80));
						System.out.println(" 이력서번호      이력서제목                        희망연봉             작성일자");
						System.out.println("-".repeat(80));
						
			            List<Map<String,String>> list_map = mdao.view_my_all_resume(member);
			            
			            
			            StringBuilder sb = new StringBuilder();
			            
			            for(Map<String, String> map : list_map) {
			            	sb.append("    "+map.get("pk_resume_code") + "\t");
				           	sb.append(map.get("self_introduce") + "\t");
				           	sb.append(map.get("want_yearsal") + "\t\t");
				           	sb.append(map.get("resume_date") + "\t");
			            }
			            System.out.println(sb.toString());
			            
			            //////////////////////////////////////////////////////////////////////
			            System.out.print("\n▶ 열람할 이력서 번호 선택 : ");
			            String choice_resume_no = sc.nextLine();
			            
			            mdao.view_resume_detail(choice_resume_no);  // tbl_resume 의 정보를 다 보여주는 DAO imple 메소드로 이동
			            
			            break;
				          
			           
					case "2": // 이력서 등록
		                  System.out.println("\n[이력서 등록 메뉴]");
		                  
		                  
		                  
		                  // 이력서명 입력
		                  String resume_name = "";
		                  do {
		                     System.out.print("▶ 이력서명 : ");
		                     resume_name = sc.nextLine();
		                     if( !(resume_name.isEmpty() )) {
		                        break;
		                     }
		                     else {
		                        System.out.println("<경고> 이력서명은 공백을 제외하고 입력해주세요.");
		                     }
		                  } while(true);
		                  
		                  
		                  
		                  // 병역정보 입력
		                  System.out.println("\n"+">> [병역정보 목록] << ");
		                  System.out.println("   1.미필");
		                  System.out.println("   2.군필");
		                  System.out.println("   3.면제");
		                  System.out.println("   4.해당사항 없음");
		                  
		                  String get_army_name = "";
		                  outer_army:
		                  do {
		                	  System.out.print("▶ 번호를 선택하세요 : ");
		                	  String yn = sc.nextLine();
		                	  switch (yn) {
							case "1":
								get_army_name = "미필";
								break outer_army;
							case "2":
								get_army_name = "군필";
								break outer_army;
							case "3":
								get_army_name = "면제";
								break outer_army;
							case "4":
								get_army_name = "해당없음";
								break outer_army;

							default:
								System.out.println("[경고] 메뉴에 없는 번호입니다.");
								break;
							}
		                	  
		                	  
		                	  
		                	  
		                  }while(true);
		                        
		                  
		                    
		                    
		                  
		                  // 희망연봉 입력
		                  String want_yearsal = "";
		                  do {
		                     
		                     System.out.println("\n[안내] 희망 연봉은 숫자로만 입력해주세요.(단위:\"만원\")");
		                     System.out.print("▶ 희망 연봉 : ");
		                     want_yearsal = sc.nextLine();
		                     
		                     if(want_yearsal.isEmpty() || !(Pattern.matches("^[0-9]*$",want_yearsal))) {
		                        System.out.println("<경고> 연봉 입력 형태를 확인하고 다시 입력해주세요.");
		                     }
		                     
		                     else {
		                         DecimalFormat decFormat = new DecimalFormat("###,###");
		                         want_yearsal = decFormat.format(Integer.parseInt(want_yearsal));
		                            break;
		                     }
		                     
		                  } while(true);
		                  
		               
		                  
		                  // 자격증 입력
		                  System.out.println("\n"+">> [자격증 목록] << ");
		                      int i = 0;
		                      List<Map<String,String>> list_map_5 = mdao.view_certifi_type();
		                     
		                      List<String> list_map_no_5 = new ArrayList<>();
		                        for(Map<String,String> map:list_map_5) {
		                           
		                            list_map_no_5.add(i, i+1 + "." + map.get("job_certifi_name")); 
		                            System.out.println( list_map_no_5.get(i));
		                            i++;
		                        }
		                        
		                        
		                        String get_certifi_name = null;
		                        outer:
		                        do {
		                           System.out.print("▶ 자격증 번호 선택 : ");
		                            String choice_num5 = sc.nextLine();
		                            
		                           for(int j = 0; j < list_map_no_5.size(); j++) {
		                              if(!(list_map_no_5.get(j).substring(0,list_map_no_5.get(j).indexOf(".")).equals(choice_num5))) {
		                              }
		                              else {
		                                 get_certifi_name = list_map_no_5.get(j).substring(list_map_no_5.get(j).indexOf(".")+1);
		                                 break outer;
		                              }
		                           }
		                           if(get_certifi_name == null) {
		                              System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
		                           }
		                        }while(true);
		                        
		                   String get_certifi_code = mdao.search_certifi_code(get_certifi_name);
		                  
		                  
		                  // 직무형태 입력
		                     //*** 직무형태를 입력하는 메소드 *** //
		                      System.out.println("\n"+">> [직무형태 목록] << ");
		                     i = 0;
		                      List<Map<String,String>> list_map_3 = mdao.view_job_type();
		                     
		                      List<String> list_map_no_3 = new ArrayList<>();
		                        for(Map<String,String> map:list_map_3) {
		                           
		                            list_map_no_3.add(i, i+1 + "." + map.get("job_type_name")); 
		                            System.out.println( list_map_no_3.get(i));
		                            i++;
		                        }
		                        
		                        
		                        String get_jobtype_name = null;
		                        outer:
		                        do {
		                           System.out.print("▶ 희망 직무형태 번호 선택 : ");
		                            String choice_num3 = sc.nextLine();
		                            
		                           for(int j = 0; j < list_map_no_3.size(); j++) {
		                              if(!(list_map_no_3.get(j).substring(0,list_map_no_3.get(j).indexOf(".")).equals(choice_num3))) {
		                              }
		                              else {
		                                 get_jobtype_name = list_map_no_3.get(j).substring(list_map_no_3.get(j).indexOf(".")+1);
		                                 break outer;
		                              }
		                           }
		                           if(get_jobtype_name == null) {
		                              System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
		                           }
		                        }while(true);
		                        
		                    String get_job_type_code = mdao.search_jobtype_code(get_jobtype_name);
		                   
		                     
		                  
		                  // 수상 및 활동내용 입력
		                  String award_act_list = "";
		                  do {
		                     System.out.print("\n▶ 수상 및 활동내용 입력 : ");
		                     award_act_list = sc.nextLine();
		                     
		                     if(award_act_list.isEmpty()) {
		                        System.out.println("<경고> 수상 및 활동내용은 공백을 제외하고 입력해주세요.");
		                     }
		                     else {
		                        break;
		                     }
		                  } while(true);
		               
		                  
		                  
		                  
		                  LocalDate now = LocalDate.now();
		                  
		                  System.out.println("\n[입력한 이력서 내용]");
		                  System.out.println("▷ 이력서명 : " + resume_name + "\n"
		                               + "▷ 병역정보 : " + get_army_name + "\n"
		                               + "▷ 희망연봉 : " + want_yearsal+ "만원" + "\n"
		                               + "▷ 취득한 자격증 : " + get_certifi_name + "\n"
		                               + "▷ 희망 직무형태 : " + get_jobtype_name + "\n"
		                               + "▷ 수상 및 활동내용 : " + award_act_list + "\n"
		                               + "▷ 작성일자 : " + now + "\n"
		                                 );
		                  
		                  do {
		                     System.out.println("▶ 위의 내용으로 이력서를 등록하시겠습니까? [Y/N] : ");
		                     String yn = "";
		                     yn = sc.nextLine();
		                     Map<String,String> map = new HashMap<>();
		                     if("y".equalsIgnoreCase(yn)) {
		                        System.out.println(">> 이력서 등록에 성공했습니다. <<");
		                        map.put("resume_name", resume_name);
		                        map.put("get_army_name", get_army_name);
		                        map.put("want_yearsal", want_yearsal);
		                        map.put("get_certifi_code", get_certifi_code);
		                        map.put("award_act_list", award_act_list);
		                        map.put("get_job_type_code", get_job_type_code);
		                        
		                       mdao.insert_resume(member,map); // 입력한 정보를 토대로 이력서를 작성하는 코드 생성
				                   
				                   
		                        
		                        
		                        
		                        break;
		                     }
		                     else if("n".equalsIgnoreCase(yn)){
		                        System.out.println(">> 이력서 등록을 취소했습니다. <<");
		                        break;
		                     }
		                     else {
		                        System.out.println("<경고> 입력값은 공백을 제외한 Y 또는 N 으로만 입력해주세요.");
		                     }
		                  } while(true);
		                  
		                  
		                  break;
						
						
					case "3": // 이력서 수정
						
						System.out.println("\n[이력서 수정 메뉴]");
		                  
		                  
						Map<String,String> paraMap = mdao.get_resume(member); // 원래 있던 이력서를 가지고와서 보여주는 메소드 생성.
						
						
						System.out.println("=== 기존 이력서 ===");
						System.out.println("1. 이력서 제목 : " + paraMap.get("self_introduce"));
						System.out.println("2. 병역정보 : " + paraMap.get("army"));
						System.out.println("3. 희망 연봉 : " + paraMap.get("want_yearsal"));
						System.out.println("4. 수상및 활동내역 : " + paraMap.get("award_act_list"));
						System.out.println("5. 자격증 명 : " + paraMap.get("certifi_name"));
						
						
						
						
						System.out.println("=== 이력서 수정 === ");
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						System.out.print("▶ 수정할 이력서 제목 : ");
						String change_self_introduce = sc.nextLine();
						
						if(change_self_introduce.isBlank()) {
							change_self_introduce = paraMap.get("self_introduce");
						}
						
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						
						System.out.println("== 병역정보 목록 == ");
						System.out.println("  1.  군필."
										 + "  2.  미필."
										 + "  3.  면제."
										 + "  4.  해당없음.");
						String change_army = "";
						outer:
						do {
							System.out.print("▶ 수정할 병역 정보 : ");
							change_army = sc.nextLine();
							
							if(change_army.isBlank()) {
								change_army = paraMap.get("army");
								break;
							}
							else {
								switch (change_army) {
								case "1":
									change_army = "군필";
									break outer;
								case "2":
									change_army = "미필";
									break outer;
								case "3":
									change_army =  "면제";
									break outer;
								case "4":
									change_army = "해당없음";
									break outer;

								default:
									System.out.println("[경고] 메뉴에 없는 번호입니다.");
									break;
								}
							}
							
							
						}while(true);
						
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						String change_want_yearsal = "";
						do {
							System.out.print("▶ 수정할 희망 연봉 [연봉은 반드시 숫자로만 입력하세요 예 5,000만원 > 5000] : ");
							change_want_yearsal = sc.nextLine();
							
							if(change_want_yearsal.isBlank()) {
								change_want_yearsal = paraMap.get("want_yearsal");
								break;
							}
							else {
								boolean is_all_number = true;
								for(int f = 0 ; f < change_want_yearsal.length(); f++) {
									if(!('0'<= change_want_yearsal.charAt(f) && change_want_yearsal.charAt(f) <= '9')) {
										System.out.println("[경고] 반드시 숫자만 입력하세요.");
										is_all_number = false;
										break;
									}
								}
								
								if(is_all_number) {
									
									int n_want_yearsal = Integer.parseInt(change_want_yearsal);
					            	DecimalFormat df = new DecimalFormat("#,###");
					            	change_want_yearsal = df.format(n_want_yearsal);
									change_want_yearsal += "만원";
									break;
								}
							}
							
						}while(true);
						
						
						System.out.println("[안내] 수정하지 않고 기존 정보를 그대로 유지하시려면 그냥 엔터를 입력하세요.");
						System.out.print("▶ 수정할 수상 및 활동내역 : ");
						
						String change_award_act_list = sc.nextLine();
						
						if(change_award_act_list.isBlank()) {
							change_award_act_list = paraMap.get("award_act_list");
						}
						
						
						// == 자격증 변경 == // 
						String certifi_code = "";
						outer:
						do {
							
							System.out.println("======== 자격증 목록 ========");
			            	  List<String> certifi_list = mdao.view_all_certifi_type_list(); // 모든 자격증 목록을 list에 담아주는 메소드 생성.
			            	  
			            	  List<String> no_certifi_list = new ArrayList<>();
			            	  for(int f = 0; f < certifi_list.size(); f++) {
			            		  String s = f+1+". "+certifi_list.get(f);
			            		  no_certifi_list.add(s);
			            		  System.out.println(no_certifi_list.get(f));
			            	  }
			            	  boolean is_all_number = true;
			            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
			            	  
			            	  do {
			            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 정보처리기사 > 4] : ");
			  	            	  String choice_no = sc.nextLine();
			  	            	  
			  	            	  if(choice_no.isBlank()) {
			  	            		certifi_code = mdao.get_certifi_code(paraMap.get("certifi_name")); // 입력한 자격증명과 일치하는 코드를 반환.
			  	            		  break outer;
			  	            	  }
			  	            	  else {
			  	            		  is_all_number = true;
			  	            		for(int f = 0;f < choice_no.length(); f++) {
				  	            		  if(!('0'<=choice_no.charAt(f) && choice_no.charAt(f) <= '9')) {
				  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
				  	            			  is_all_number = false;
				  	            			  break;
				  	            		  }
				  	            	  }
			  	            		String certifi_name = "";
			  	            		if(is_all_number) {
			  	            			boolean is_can_number = false;
			  	            			for(int f = 0;f<no_certifi_list.size();f++) {
			  	            				if(choice_no.equals(no_certifi_list.get(f).substring(0,no_certifi_list.get(f).indexOf(".")))) {
			  	            					certifi_name = no_certifi_list.get(f).substring(no_certifi_list.get(f).indexOf(".")+2);
			  	            					is_can_number = true;
			  	            					break;
			  	            				}
			  	            			}
			  	            			
			  	            			
			  	            			if(is_can_number) {
			  	            				certifi_code = mdao.get_certifi_code(certifi_name);
			  	            				break outer;
			  	            			}
			  	            			else {
			  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
			  	            			}
			  	            		}
			  	            		
			  	            		
			  	            	  }
			            	  }while(true);
							
						}while(true);
						
						
						
						
						
						
						paraMap.put("change_self_introduce", change_self_introduce);
						paraMap.put("change_army", change_army);
						paraMap.put("change_want_yearsal", change_want_yearsal);
						paraMap.put("change_award_act_list", change_award_act_list);
						paraMap.put("certifi_code", certifi_code);
						paraMap.put("my_certifi_no_1", paraMap.get("my_certifi_no_1"));
						
						
						
						int num = mdao.update_certifi(member,paraMap);
						
						if(num == 1) {
							System.out.println("[안내] 이력서 수정을 성공적으로 완료했습니다.");
						}
						else if( num == 0 ) {
							System.out.println("[안내] 이력서 수정을 취소했습니다.");
						}
						else {
							System.out.println("[경고] 이력서 수정을 실패했습니다. ");
						}
						
						
						
						
						break;
					case "4": // 이력서 삭제
						
						System.out.println("\n[이력서 목록]");
						System.out.println("-".repeat(80));
						System.out.println(" 이력서번호      이력서제목                        희망연봉             작성일자");
						System.out.println("-".repeat(80));
						
						list_map = mdao.view_my_all_resume(member);
			            
			            sb = new StringBuilder();
			            
			            for(Map<String, String> map : list_map) {
			            	sb.append("    "+map.get("pk_resume_code") + "\t");
				           	sb.append(map.get("self_introduce") + "\t");
				           	sb.append(map.get("want_yearsal") + "\t\t");
				           	sb.append(map.get("resume_date") + "\t");
			            }
			            System.out.println(sb.toString());
			            
			            //////////////////////////////////////////////////////////////////////
			            System.out.print("\n▶ 삭제할 이력서 번호 선택 : ");
			            choice_resume_no = sc.nextLine();
			            
			            mdao.view_resume_detail(choice_resume_no);  // tbl_resume 의 정보를 다 보여주는 DAO imple 메소드로 이동
			            
			            int n = mdao.delete_resume(choice_resume_no,member); // 선택한 이력서를 지워주는 메소드 생
						
			            if(n == 1) {
			            	System.out.println("[안내] 이력서를 성공적으로 삭제했습니다.");
			            }
			            else if (n == 0) {
			            	System.out.println("[안내] 이력서 삭제를 취소했습니다.");
			            }
			            else {
			            	System.out.println("[경고] 이력서 삭제를 실패했습니다.");
			            }
			            
			            
					case "5": // 이전 메뉴로
						
						break;
						
					default:
						System.out.println("[경고] 메뉴에 없는 번호입니다. 다시 선택하세요. \n");
						break;
						
				}// end of switch (select_resume)---------------------------
				
			} while(!("5".equals(select_resume))); // end of do~while(select_resume)---------------
				
			
			
			
			// 위에서 얻어온 정보들을 Map에 저장후 (insert)을 하게해주는 메소드 생성 
			MemberDTO mdto = new MemberDTO();
	        Map<String,String> paraMap = new HashMap<>();
	        
	        paraMap.put("PK_USER_ID",mdto.getPK_USER_ID() );
	        paraMap.put("USER_PASSWD",mdto.getUSER_PASSWD() );
	        paraMap.put("USER_NAME", mdto.getUSER_NAME());
	        paraMap.put("USER_EMAIL", mdto.getUSER_EMAIL() );
	        paraMap.put("USER_TEL", mdto.getUSER_TEL());
	        paraMap.put("USER_JUBUN", mdto.getUSER_JUBUN());
	        	        
			
		}// end of private void resume_menu(MemberDTO member, Scanner sc)
		
	
	
	
	
	
	// 모든 공고 목록을 보여주는 메소드 생성.
	private void view_all_recruit_notice() {
		
		MemberDAO mdao = new MemberDAO_imple();
		
		mdao.view_all_recruit_notice();
		
	}


	// 모집공고에 지원할 수 있게 해주는 메소드 생성.
	private void join_recruit_notice(MemberDTO member, Scanner sc) {
		
		MemberDAO mdao = new MemberDAO_imple();
		outer:
		do {
			System.out.print("▶ 지원할 공고 번호를 입력하세요 : ");
			String want_recruit_notice_no = sc.nextLine();
			
			boolean is_all_number = true;
			for(int i = 0; i < want_recruit_notice_no.length(); i++) {
				if(!('0'<=want_recruit_notice_no.charAt(i)&&want_recruit_notice_no.charAt(i)<='9')) {
					is_all_number = false;
					System.out.println("[경고] 반드시 숫자만 입력하세요");
					break;
				}
			}
			if(is_all_number) {
				
				Map<String,String> map = mdao.view_one_recruit_notice(want_recruit_notice_no); // 원하는 공고를 조회하는 메소드 생성.
				
				if(map.get("recruit_notice_name")!=null) {
          		  System.out.println("1. 공고명 : " + map.get("recruit_notice_name"));
          		  System.out.println("2. 연봉  : " + map.get("yearsal"));
          		  System.out.println("3. 직무  : " + map.get("job_type_name"));
          		  System.out.println("4. 자격요건 : " + map.get("quail"));
          		  System.out.println("5. 우대사항 : " + map.get("woodae"));
          		  System.out.println("6. 사내복지 : " + map.get("benefit"));
          		  System.out.println("7. 직책 : " + map.get("position_name"));
          		  System.out.println("8. 채용형태 : " + map.get("worktype_name"));
          		  System.out.println("9. 근무지역 : " + map.get("location_name"));
          		  
          		  for(int i = 0; i < 5; i++) {
          			  String recruit_step_name_ = "";
          			  recruit_step_name_ = "recruit_step_name_" + (i+1);
          			  if(map.get(recruit_step_name_)!=null) {
          				  System.out.println(10+i + ". " + (i+1) +"차전형 : " + map.get(recruit_step_name_));
          			  }
          			  else {
          				  break;
          			  }
          		  } // end of for =============
          		  System.out.println("== 공고 마감 일자 : " + map.get("recruit_finish_day"));
          		  
          		  
          		  do {
          			  System.out.print(">>> 위 공고에 지원하시겠습니까??? [Y/N] : ");
            		  String yn = sc.nextLine();
            		  if("y".equalsIgnoreCase(yn)) {
            			  int n = mdao.join_recruit_notice(member,want_recruit_notice_no); // 공고에 지원해주는 메소드 생성.
            			  
            			  if(n != 1) {
            				  System.out.println("[경고] 지원에 실패했습니다.");
            			  }
            			  break outer;
            		  }
            		  else if("n".equalsIgnoreCase(yn)) {
            			  System.out.println("[안내] 지원을 취소했습니다.");
            			  break outer;
            		  }
            		  else {
            			  System.out.println("[경고] 반드시 Y 혹은 N 두개중에 선택하세요.");
            		  }
          		  }while(true);
          		  
          		  
          		  
          		  
          		  
          		  
          		  
          	  }
          	  else {
          		  System.out.println("[경고] 해당 공고번호는 없는 공고입니다 다시한번 확인해주세요.");
          		  
          	  }
				
			}
			
			
			
		}while(true);
		
	}


	// 개인정보를 조회하게 해주는 메소드 생성.
	private void view_member_info(MemberDTO member, Scanner sc) {
		String view_member_info_no = "";
		outer_view_member:
		do {
			System.out.println("-------------------개인정보 조회 [" + member.getUSER_NAME()  + "님 로그인중]------------\n"
							 + "  1.개인정보 보기    2. 개인정보 수정    3. 회원탈퇴    4. 이전메뉴로 \n"
							 + "--------------------------------------------------------------------------\n"
								);
			
			System.out.print("▶ 메뉴번호 선택 : ");
			view_member_info_no = sc.nextLine();
			
			switch (view_member_info_no) {
			case "1": // 개인정보 보기
				System.out.println("===[개인정보 조회]===");
				System.out.println("▶ 비밀번호를 입력하세요 : ");
				String member_passwd = sc.nextLine();
				
				MemberDAO mdao = new MemberDAO_imple();
				
				Map<String,String> paraMap = new HashMap<>();
				paraMap.put("pk_user_id", member.getPK_USER_ID());
				paraMap.put("pass_wd", member_passwd);
				
				MemberDTO mdto = mdao.login(paraMap);
				if(mdto.getPK_USER_ID()!=null) { // 만약 가져온 값이 null이 아니라면.
					
					mdao.view_member_info(paraMap); // 회원 정보를 보여주는 메소드 생성.
					
				}
				else {
					System.out.println("[경고] 잘못된 비밀번호 입니다 비밀번호를 확인하세요.");
				}
				break;
			case "2": // 개인정보 수정
				System.out.println("===[개인정보 수정]===");
				System.out.println("▶ 비밀번호를 입력하세요 : ");
				member_passwd = sc.nextLine();
				
				mdao = new MemberDAO_imple();
				
				paraMap = new HashMap<>();
				paraMap.put("pk_user_id", member.getPK_USER_ID());
				paraMap.put("pass_wd", member_passwd);
				
				mdto = mdao.login(paraMap);
				if(mdto.getPK_USER_ID()!=null) { // 만약 가져온 값이 null이 아니라면.
					
					mdto = mdao.view_member_info(paraMap); // 회원 정보를 보여주는 메소드
					
					System.out.println("[안내] 수정하지 않고 기존의 값을 계속 유지하려면 그냥 '엔터'를 누르세요 ");
					
					// == 이름 수정 == // 
					String change_name = "";
					do {
						System.out.print("▶ 변경할 이름 : ");
						change_name = sc.nextLine();
						
						String before_name = mdto.getUSER_NAME();
						if(change_name.isBlank()) {
							change_name = mdto.getUSER_NAME();
							break;
						}
						else {
							mdto.setUSER_NAME(change_name);
							if(!mdto.getUSER_NAME().equals(before_name)) {
								break;
							}
							else {
								System.out.println("[경고] 기존과 동일한 이름입니다.");
							}
						}
					
					} while(true);
					
					// == 비밀번호 수정 == // 
					System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
					String change_passwd = "";
					do {
						System.out.print("▷ 비밀번호 : ");
						change_passwd = sc.nextLine();
						
						if(change_passwd.isBlank()) {
							change_passwd = mdto.getUSER_PASSWD();
							break;
						}
						else {
							mdto.setUSER_PASSWD(change_passwd); // 비밀번호 유효성 검사 
							if(!(mdto.getUSER_PASSWD() == null || mdto.getUSER_PASSWD().isBlank())) {
								break;
							}
							else {
								System.out.println("[경고] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요. ");
							}
						}
						
					}while(true); // end of do_while passwd
					
					
					// == email 수정 == // 
					
					System.out.println("\n[안내] 이메일 앞자리는 5~15 글자의 영문 대,소문자 및 숫자로만 이루어져야 합니다.");
					String change_email  = "";
					String before_email = mdto.getUSER_EMAIL();
					do {
						System.out.print("▶ 변경할 이메일 [이메일은 '@' 앞까지만 입력하세요] : ");
						change_email  = sc.nextLine();
						
						if(change_email.isBlank()) {
							change_email = mdao.getemail(paraMap);
							break;
						}
						else {
							// 이메일 앞자리가 영문과 숫자로만 이루어져있는지 확인.
							mdto.setUSER_EMAIL(change_email);
							if(!mdto.getUSER_EMAIL().equals(before_email)) {
								
								boolean is_exit = false;
								do {
									is_exit = false;
									System.out.println("[이메일 선택]");
									System.out.println("1. @naver.com\n"
											         + "2. @daum.net\n"
											         + "3. @gmail.com\n"
											         + "4. @kakao.com\n"
											         + "5. @saramin.co.kr\n");
									System.out.print("▷ 이메일 번호를 선택하세요 : ");
									String email_choice = sc.nextLine();
									
									// 메뉴에 있는번호만 선택할 수 있게 do_while문과 switch문 이용.
									switch (email_choice) {
									case "1":
										change_email += "@naver.com";
										break;
									case "2":
										change_email += "@daum.net";
										break;
									case "3":
										change_email += "@gmail.com";
										break;
									case "4":
										change_email += "@kakao.com";
										break;
									case "5":
										change_email += "@saramin.co.kr";
										break;
					
									default:
										is_exit = true;
										break;
									}
									if(is_exit) {
										System.out.println("[경고] 메뉴에 없는 번호입니다.");
									}
								}while(is_exit);// end of do_while
								
							break;	
								
							}
							else {
							}
						}
						
					}while(true);
					
					
					// == 지역코드 수정 == 
					
					String change_location_name = "";
					outer:
					do {
						System.out.println("\n▶ 변경할 주소 검색 : ");
						change_location_name = sc.nextLine();
						if(change_location_name.isBlank()) {
							change_location_name = mdto.getLOCATION_CODE();
							break outer;
						}
						else {
							List<String> address_list = mdao.search_address(change_location_name);
							
							System.out.println("--------------------------------------");
							System.out.println("    [" + change_location_name + "]이(가) 포함된 지역 목록");
							System.out.println("--------------------------------------");
							
							// select 해온 값을 담은 address_list를 하나하나 뽑아서 앞에 번호를 붙여주기위한 list생성. 
							List<String> address_list_no = new ArrayList<>();
							if(address_list.size()!=0) {
								for(int i = 0; i < address_list.size() ; i++) {
									// 1부터 순서대로 번호부여.
									address_list_no.add( i + 1 + ". "+address_list.get(i));
									System.out.println(address_list_no.get(i));
								}
								
								do {
									System.out.print("\n▷ 해당 지역에서 선택하시려면 1번 지역검색으로 돌아가시려면 2번을 선택하세요 : ");
									String no = sc.nextLine();
									
									switch (no) {
									case "1":
										
										System.out.print("▷ 거주하고있는 지역의 번호를 입력하세요 : ");
										String s_address_no = sc.nextLine();
										
										if(s_address_no.isBlank()) {
											System.out.println("[경고] 공백은 입력할 수 없습니다.");
											break;
										}
										
										change_location_name = "";
										// 뽑아온 list중에서 선택한 번호의 지역명을 번호없이 다시 지역명만 뽑아내는 for문.
										for(int i = 0;i<address_list_no.size();i++) {
											
											// 위에서 만든 숫자 + . + 지역명에서 . 앞까지의 숫자와 내가 입력한 숫자가 일치한다면
											if(address_list_no.get(i).substring(0,address_list_no.get(i).indexOf('.')).equals(s_address_no)) {
												
												// 숫자 + . + 지역명에서 . 뒷부분인 지역명만 따로 빼내 address 라는 변수에 다시 저장. 
												change_location_name = address_list_no.get(i).substring(address_list_no.get(i).indexOf('.')+2); 
												// 위에서 마지막에 +2가 붙은 이유는 . 뒤에 ' '(공백) 이 붙어있기 때문. 
												
												break;
											}
										}
										
										if(change_location_name.contains("전체")) {
											System.out.println("\n[경고] '전체' 가 들어있는 지역은 선택할 수 없습니다. 상세주소를 선택해주세요.");
											break;
										}
										else if (change_location_name.isBlank()) {
											System.out.println("[경고] 목록에 없는 번호입니다 다시한번 확인해주세요");
											continue outer;
										}
										else {
											change_location_name = mdao.search_address_code(change_location_name);
										}
										
										
										
										break outer;
										
									case "2":
										continue outer;
	
									default:
										System.out.println("[경고] 메뉴에 없는 번호 입니다.");
										break;
									}
									
								}while(true);
							}
							else {
								System.out.println("[경고] 해당 지역명은 존재하지 않습니다.");
							}
						}
					} while(true);
					
					
					
					// == 전화번호 입력 //
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					System.out.println("\n[안내] 전화번호는 '-'를 제외한 11자리의 숫자로만 입력하세요");
					String change_tel = "";
					boolean exit_tel = true;
					do {
						exit_tel = true;
						
						System.out.print("▶ 변경할 전화번호 : ");
						change_tel = sc.nextLine();
						
						if(change_tel.isBlank()) {
							change_tel = mdto.getUSER_TEL();
							break;
						}
						else {
							// 전화번호가 정확히 11자리인지 검사.
							if(change_tel.length() == 11) {
								
								char[] tel_arr = change_tel.toCharArray();
								
								// 입력한 전화번호가 모두 숫자로 이루어져있는지 확인하는 for문. 
								for(int i = 0; i< tel_arr.length; i++) {
									if(!('0' <= tel_arr[i] && tel_arr[i] <= '9')) {
										
										System.out.println("[경고] 전화번호는 반드시 숫자로만 이루어져야합니다.");
										exit_tel = false;
										break;
									}
								}
							}
							else {
								System.out.println("[경고] 전화번호는 반드시 11자리의 숫자로 이루어져야합니다.");
								exit_tel = false;
							}
							
							// 휴대폰번호 사이사이에 '-' 삽입.
							if(exit_tel) {
								change_tel = change_tel.substring(0,3) + "-" + change_tel.substring(3,7) + "-" + change_tel.substring(7);
							}
						}
							
					}while(!exit_tel);
					
					
					paraMap.put( "change_name" , change_name );
					paraMap.put( "change_passwd" , change_passwd );
					paraMap.put( "change_email" , change_email );
					paraMap.put( "change_location_name" , change_location_name );
					paraMap.put( "change_tel" , change_tel );
					
					// 위의 변경한 값을 토대로 업데이트를 진행해주는 메소드 생성
					int n = mdao.change_member_info(paraMap);
					
					if( n == 1) {
						System.out.println("[안내] 개인정보 수정이 성공적으로 완료되었습니다.");
					}
					else if( n == 0) {
						System.out.println("[안내] 개인정보 수정을 취소했습니다.");
					}
					else {
						System.out.println("[경고] 개인정보 수정을 실패했습니다.");
					}
					
				}
				else {
					System.out.println("[경고] 잘못된 비밀번호 입니다 비밀번호를 확인하세요.");
				}
				
				break;
				
			case "3": // 회원탈퇴
				mdao = new MemberDAO_imple();
				System.out.println("======[회원탈퇴]=====");
				System.out.print("▶ 비밀번호를 입력하세요 : ");
				String user_passwd = sc.nextLine();
				
				paraMap = new HashMap<>();
				paraMap.put("user_id", member.getPK_USER_ID());
				paraMap.put("user_passwd", user_passwd);
				
				int is_quit = mdao.quit_member(paraMap); // 비밀번호가 일치하면 회원탈퇴를 할 수 있게 해주는 메소드 생성.
				if(is_quit==1) {
					System.out.println("[안내] 회원 탈퇴가 성공적으로 이루어졌습니다.");
					break outer_view_member;
				}
				else if(is_quit == 0) {
					System.out.println("[안내] 회원 탈퇴를 취소했습니다.");
				}
				else {
				}
				
				
				break;
			case "4": // 이전메뉴로
				
				break;
	
			default:
				System.out.println("[경고] 메뉴에 없는 번호입니다. \n");
				break;
			}
		
		}while(!"4".equals(view_member_info_no));
		
		
		
	} // end of private void view_member_info(MemberDTO member, Scanner sc)


	
	
	// 구인회사 검색 메뉴
	 private void search_com(MemberDTO member, Scanner sc) {
		 String s_choice = null;
		 do {
		 	System.out.println("-------------------------구인회사 검색 [" + member.getUSER_NAME()  + "님 로그인중]------------------------\r\n"
		 						+ "  1. 모든기업 조회    2. 기업명 검색    3. 매출액 검색    4. 업종검색    5. 이전메뉴로 \r\n"
		 						+ "--------------------------------------------------------------------------\r\n"
		 						);
		 	
		 	
		 	System.out.print("▶ 메뉴번호 선택 :");
		 	 
		 	
		 	 s_choice = sc.nextLine(); 
		 	 
		 	MemberDAO Mdao = new MemberDAO_imple();
		      switch (s_choice) {
		      case "1":
		    	  
		    	  Mdao.view_all_company(); // 모든 회사를 보여주는 메소드 생성
		    	  
		    	  break;
		      case "2":   // 기업명 검색 
		    	  
		     	 System.out.println("▷ 검색어를 입력하세요 : ");
		     	 String search_com_name = sc.nextLine();
		     	 
		     	 if(search_com_name.isBlank()) {
		     		 System.out.println("[경고] 공백은 입력할 수 없습니다");
		     	 }
		     	 else {
		     		Map<String, String> paraMap = new HashMap<>();
			 		paraMap.put("COMPANY_NAME", search_com_name );
			 		
			 	List<Map<String, String>> mapList =Mdao.search_comname(paraMap);
			 	
			 		
			 		
			 StringBuilder sb = new StringBuilder();

			 for(Map<String, String> map : mapList) {
			 	sb.append(map.get("COMPANY_NAME") + "\t" 
			 			+ map.get("SALES") + "\t"
			 			+ map.get("NUMBER_OF_EMPLOYEE") + "\t"
			 			+ map.get("HOMEPAGE") + "\t"
			 			+ map.get("BUSS_TYPE_NAME") + "\t"
			 			+ map.get("COMPANY_ADDRESS") + "\t"
			 			+ map.get("COM_SIZE") + "\n");
			 }
			 
			 	  if(!sb.toString().isBlank()) {
			 		 System.out.println("--------------------------------------------------------------기업명에 ["+ search_com_name +"] 가 포함된 모든 기업정보 ----------------------------------------------------------------------\r\n"
				 				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			\r\n"
				 				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				 				);
			 		System.out.println(sb.toString());
			 	  }
			 	  else {
			 		  System.out.println("[경고] 기업명이 [" +  search_com_name  + "] 인 기업이 존재하지 않습니다.");
			 	  }
		     	 }
		      

		 		break;
		 		
		         
		       case "3": // 매출액 검색
		    	   System.out.println("[안내] 검색한 매출액 이상의 모든 회사를 보여줍니다 , 매출액은 반드시 숫자로만 입력하세요.");
		    	   do {
		    		   boolean is_all_number = true;
		    		   System.out.println("▷ 매출액을 검색하세요 : ");
		    		   String search_sales = sc.nextLine();
		    		   if(search_sales.isBlank()) {
		    			   System.out.println("[경고] 공백은 입력할 수 없습니다.");
		    		   }
		    		   else {
		    			   char[] search_sales_arr = search_sales.toCharArray();
			    		   for(int i = 0;i < search_sales_arr.length; i++) {
			    			   if(!('0'<=search_sales_arr[i] && search_sales_arr[i] <= '9')) {
			    				   System.out.println("[경고] 반드시 숫자만 입력하세요.");
			    				   is_all_number = false;
			    				   break;
			    			   }
			    		   }
			    		   
			    		   if(is_all_number) {
			    			   
			    			   MemberDAO mdao = new MemberDAO_imple();
			    			   mdao.search_company_sales(search_sales); // 입력한 매출액 이상의 모든 회사를 조회하는 메소드 생성
			    			   
			    			   break;
			    		   }
		    		   }
		           
		    	   }while(true);
		    	  
		    	   
		    	   break;
		       case "4":   // 업종검색
		     	System.out.println("▷ 검색어를 입력하세요 : ");
		     	String search_BUSS = sc.nextLine();
		     	
		     	if(search_BUSS.isBlank()) {
		     		System.out.println("[경고] 공백은 입력할 수 없습니다.");
		     	}
		     	else {
		     		Map<String, String> paraMap1 = new HashMap<>();
			 		paraMap1.put("BUSS_TYPE_NAME", search_BUSS.toUpperCase() );
			 		
			 		
			 		List<Map<String, String>> mapList1 =Mdao.search_buss_type(paraMap1);
			 		
			 		
			 		
			 		
			 		StringBuilder sb1 = new StringBuilder();

			 		for(Map<String, String> map : mapList1) {
			 			sb1.append(map.get("COMPANY_NAME") + "\t" 
			 					+ map.get("SALES") + "\t"
			 					+ map.get("NUMBER_OF_EMPLOYEE") + "\t"
			 					+ map.get("HOMEPAGE") + "\t"
			 					+ map.get("BUSS_TYPE_NAME") + "\t"
			 					+ map.get("COMPANY_ADDRESS") + "\t"
			 					+ map.get("COM_SIZE") + "\n");
			 		}
			 		     if(sb1.toString().length()>0) {
			 		    	System.out.println("--------------------------------------------------------------"+search_BUSS +"에 대한 기업정보 ----------------------------------------------------------------------\r\n"
					   				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			\r\n"
					   				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
					   				);

				 		     System.out.println(sb1.toString());
			 		     }
			 		     else {
			 		    	 System.out.println("[안내] 검색된 기업 정보가 존재하지 않습니다.");
			 		     }
			 		     
		     	}
		 		
		       	break;
		        
		       case "5":	// 이전 메뉴로 
		       	break;
		         
		       default:
		    	   
		        	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
		       	break; 
		       
		      } //END OF  switch (s_choice) 
		      } while(!"5".equals(s_choice));
		 
	} // end of private void search_com(MemberDTO member, Scanner sc)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// == 구직자 회원가입을 할 수 있게 해주는 메소드 생성
	public void mem_join(Scanner sc) {
		
		MemberDTO mdto = new MemberDTO();
		MemberDAO mdao = new MemberDAO_imple();
		
		
		
		System.out.println("\n[구직자 회원가입]\n");
		
		
		// == 아이디 입력 == //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("[안내] 아이디는 5~20자의 영문, 숫자의 조합으로 입력하세요.");
		outer:
		do {
			System.out.print("▷ 아이디 : ");
			String userid = sc.nextLine();
			
			mdto.setPK_USER_ID(userid);
			
			if(!(mdto.getPK_USER_ID()==null || mdto.getPK_USER_ID().isBlank())) {
				
				// 입력한 아이디가 이미 존재하는 아이디인지 확인하기위한 dbao 메소드 생성
				int n = mdao.check_userid(userid);
				if( n == 1 ) {
					System.out.println("[경고] 이미 존재하는 아이디 입니다. 새로운 아이디를 입력해주세요");
					continue outer;
				}
				else {
					
				}
				break;
			}
			else {
			}
			
		} while(true); // end of do_while userid ====================
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 비밀번호 입력 == //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
		do {
			System.out.print("▷ 비밀번호 : ");
			String passwd = sc.nextLine();
			mdto.setUSER_PASSWD(passwd); // 비밀번호 유효성 검사 
			if(!(mdto.getUSER_PASSWD() == null || mdto.getUSER_PASSWD().isBlank())) {
				break;
			}
			else {
				System.out.println("[경고] 비밀번호 조건을 확인하여 다시 입력하여 주십시오.");
			}
		}while(true); // end of do_while passwd
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 이름 입력 == //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 성함은 2글자 이상 6글자 이하의 한글로만 이루어져야 합니다.");
		do {
			System.out.print("▷ 성함 : ");
			String name = sc.nextLine();
			
			mdto.setUSER_NAME(name); // 이름 유효성 검사 
			if(!(mdto.getUSER_NAME() == null || mdto.getUSER_NAME().isBlank())) {
				break;
			}
			else {
			}
			
		} while(true);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 주민번호 입력 == // 
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 주민등록번호는 '-' 를 제외한 13자리의 숫자로 입력하세요");
		
		boolean exit_jubun = true;
		do {
			exit_jubun = true;
			
			System.out.print("▷ 주민등록번호 : ");
			String jubun = sc.nextLine();
			
			// 주민번호가 정확히 13자리인지 확인.
			if(jubun.length() == 13) {
				
				char[] jubun_arr = jubun.toCharArray();
				
				// 주민번호가 숫자로만 이루어져있는지 확인하기위한 반복문 
				for(int i = 0; i< jubun_arr.length; i++) {
					if(!('0' <= jubun_arr[i] && jubun_arr[i] <= '9')) {
						exit_jubun = false;
						System.out.println("[경고] 주민등록번호는 반드시 숫자로만 이루어져야합니다.");
						break;
					}
				}
			}
			else {
				System.out.println("[경고] 주민등록번호는 반드시 13자리의 숫자로 이루어져야합니다.");
				exit_jubun = false;
			}
			
			if(exit_jubun) {
			// 위의 조건을 모두 충족했을 때 주민번호 앞자리가 달력에 존재하는 값인지, 뒷자리의 첫번째 자리가 1,2,3,4 중 하나인지 확인하는 유효성 검사.
				mdto.setUSER_JUBUN(jubun);
				
				if(mdto.getUSER_JUBUN() != null) {
					break;
				}
				else {
					System.out.println("[경고] 주민번호가 잘못 입력되었습니다. 올바르게 입력해주세요.");
					exit_jubun = false;
				}
			}
		}while(!exit_jubun);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// == 이메일 입력 == // 
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 이메일 앞자리는 5~15 글자의 영문 대,소문자 및 숫자로만 이루어져야 합니다.");
		String email_front  = "";
		do {
			System.out.print("▷ 이메일 [이메일은 '@' 앞까지만 입력하세요] : ");
			email_front  = sc.nextLine();
			
			// 이메일 앞자리가 영문과 숫자로만 이루어져있는지 확인.
			mdto.setUSER_EMAIL(email_front);
			if(mdto.getUSER_EMAIL() != null) {
				
				
				boolean is_exit = false;
				do {
					is_exit = false;
					System.out.println("[이메일 선택]");
					System.out.println("1. @naver.com\n"
							         + "2. @daum.net\n"
							         + "3. @gmail.com\n"
							         + "4. @kakao.com\n"
							         + "5. @saramin.co.kr\n");
					System.out.print("▷ 이메일 번호를 선택하세요 : ");
					String email_choice = sc.nextLine();
					
					// 메뉴에 있는번호만 선택할 수 있게 do_while문과 switch문 이용.
					switch (email_choice) {
					case "1":
						email_front += "@naver.com";
						break;
					case "2":
						email_front += "@daum.net";
						break;
					case "3":
						email_front += "@gmail.com";
						break;
					case "4":
						email_front += "@kakao.com";
						break;
					case "5":
						email_front += "@saramin.co.kr";
						break;
	
					default:
						is_exit = true;
						break;
					}
					if(is_exit) {
						System.out.println("[경고] 메뉴에 없는 번호입니다.");
					}
				}while(is_exit);// end of do_while
				
			break;	
				
			}
			else {
			}
		}while(true);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 전화번호 입력 //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[안내] 전화번호는 '-'를 제외한 11자리의 숫자로만 입력하세요");
		String tel = "";
		boolean exit_tel = true;
		do {
			exit_tel = true;
			
			System.out.print("▷ 전화번호 : ");
			tel = sc.nextLine();
			
			// 전화번호가 정확히 11자리인지 검사.
			if(tel.length() == 11) {
				
				char[] tel_arr = tel.toCharArray();
				
				// 입력한 전화번호가 모두 숫자로 이루어져있는지 확인하는 for문. 
				for(int i = 0; i< tel_arr.length; i++) {
					if(!('0' <= tel_arr[i] && tel_arr[i] <= '9')) {
						
						System.out.println("[경고] 전화번호는 반드시 숫자로만 이루어져야합니다.");
						exit_tel = false;
						break;
					}
				}
			}
			else {
				System.out.println("[경고] 전화번호는 반드시 11자리의 숫자로 이루어져야합니다.");
				exit_tel = false;
			}
			
			// 휴대폰번호 사이사이에 '-' 삽입.
			if(exit_tel) {
				
				if(tel.substring(0,3).equals("010")) {
					tel = tel.substring(0,3) + "-" + tel.substring(3,7) + "-" + tel.substring(7);
				}
				else {
					System.out.println("[경고] 잘못된 전화번호 입니다 다시 확인해주세요.");
					exit_tel = false;
				}
				
				
			}
			
			
		}while(!exit_tel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// == 지역 입력 == // 
		//- 지역 : 서울시
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String address_code = "";
		System.out.println("\n[안내] 지역은 시 혹은 구 까지만 입력하세요.");
		outer:
		do {
			System.out.print("▷ 거주지역 : ");
			String address = sc.nextLine();
			
			
			//mdao를 이용하여 검색한 지역이 포함되어있는 모든 지역명을 select해오기 위한 search_address 메소드 생성.
			List<String> address_list = mdao.search_address(address);
			
			System.out.println("--------------------------------------");
			System.out.println("    [" + address + "]이(가) 포함된 지역 목록");
			System.out.println("--------------------------------------");
			
			// select 해온 값을 담은 address_list를 하나하나 뽑아서 앞에 번호를 붙여주기위한 list생성. 
			List<String> address_list_no = new ArrayList<>();
			if(address_list.size()!=0) {
				for(int i = 0; i < address_list.size() ; i++) {
					// 1부터 순서대로 번호부여.
					address_list_no.add( i + 1 + ". "+address_list.get(i));
					System.out.println(address_list_no.get(i));
				}
				
				do {
					System.out.print("\n▷ 해당 지역에서 선택하시려면 1번 지역검색으로 돌아가시려면 2번을 선택하세요 : ");
					String no = sc.nextLine();
					
					switch (no) {
					case "1":
						
						System.out.print("▷ 거주하고있는 지역의 번호를 입력하세요 : ");
						String address_no = sc.nextLine();
						if(address_no.isBlank()) {
							System.out.println("[경고] 공백은 입력할 수 없습니다.");
							break;
						}
						
						address = "";
						// 뽑아온 list중에서 선택한 번호의 지역명을 번호없이 다시 지역명만 뽑아내는 for문.
						for(int i = 0;i<address_list_no.size();i++) {
							
							// 위에서 만든 숫자 + . + 지역명에서 . 앞까지의 숫자와 내가 입력한 숫자가 일치한다면
							if(address_list_no.get(i).substring(0,address_list_no.get(i).indexOf('.')).equals(address_no)) {
								
								// 숫자 + . + 지역명에서 . 뒷부분인 지역명만 따로 빼내 address 라는 변수에 다시 저장. 
								address = address_list_no.get(i).substring(address_list_no.get(i).indexOf('.')+2); 
								// 위에서 마지막에 +2가 붙은 이유는 . 뒤에 ' '(공백) 이 붙어있기 때문. 
								
								break;
							}
						}
						if(address.isBlank()) {
							System.out.println("[경고] 목록에 없는 번호입니다.");
							break;
						}
						if(address.contains("전체")) {
							System.out.println("\n[경고] '전체' 가 들어있는 지역은 선택할 수 없습니다. 상세주소를 선택해주세요.");
							break;
						}
						
						// 위에서 뽑아온 지역명을 이용해서 다시한번 해당 지역명이 가지는 코드를 select해오고 지역코드를 address_code에 저장.
						address_code = mdao.search_address_code(address);
						
						break outer;
						
					case "2":
						continue outer;

					default:
						System.out.println("[경고] 메뉴에 없는 번호 입니다.");
						break;
					}
					
				}while(true);
			}
			else {
				System.out.println("[경고] 해당 지역명은 존재하지 않습니다.");
			}
			
		} while(true); // end of do_while ----------
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// 위에서 얻어온 정보들을 Map에 저장후 회원가입(insert)을 하게해주는 join_member 메소드 생성 
		Map<String,String> paraMap = new HashMap<>();
		
		paraMap.put("user_id",mdto.getPK_USER_ID() );
		paraMap.put("passwd",mdto.getUSER_PASSWD() );
		paraMap.put("name", mdto.getUSER_NAME());
		paraMap.put("jubun", mdto.getUSER_JUBUN());
		paraMap.put("email", email_front);
		paraMap.put("tel", tel);
		paraMap.put("address_code", address_code);
		
		
		// 리턴값으로 회원가입 유무를 알 수 있게 int로 지정.
		int n = mdao.join_member(paraMap);
		
		if(n == 1) {
			System.out.println(">> 축하드립니다 회원가입에 성공하셨습니다 <<");
		}
		else if(n == 0) {
			System.out.println(">> 회원가입을 취소하셨습니다 <<");
		}
		else {
			System.out.println(">> 회원가입을 실패했습니다 <<");
		}
		
	} // end of public void mem_join(Scanner sc) ---------------------------------------------------------

}
>>>>>>> c9027385ac9e12d196bf66c374e5852e058ed599
