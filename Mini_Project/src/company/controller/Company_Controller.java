package company.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import company.domain.CompanyDTO;
import company.model.CompanyDAO_imple;
import company.model.CompanyDAO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Company_Controller {

	
	CompanyDAO cmdao = new  CompanyDAO_imple();
	CompanyDTO company = null;
    

	public void com_login(Scanner sc) {
		
		String choice_menu = "";
		do {
			System.out.println("--------------------------- [기업 로그인] ---------------------------\n"
			         		 + "  1. 아이디, 비밀번호 입력.     2. 아이디, 비밀번호 찾기.    3.메인메뉴로 이동.\n"
			         		 + "------------------------------------------------------------------");
			System.out.print("▶ 메뉴 번호 선택 : ");
			choice_menu = sc.nextLine();
			
			switch (choice_menu) {
			case "1": // 아이디 비밀번호 입력
				
				CompanyDTO company = new CompanyDTO();
				
				System.out.println(">>--- 구인회사 로그인 ---<<\n");
				System.out.print("▷ 아이디(사업자등록번호) : ");
				String PK_COMPANY_CODE = sc.nextLine();
				
				System.out.println("▷ 비밀번호 : ");
				String COMPANY_PASSWD = sc.nextLine();
				
				Map<String, String >paraMap = new HashMap<>();
				paraMap.put("PK_COMPANY_CODE", PK_COMPANY_CODE);
				paraMap.put("COMPANY_PASSWD", COMPANY_PASSWD);
				
				// 구인회사가 입력한 아이디, 비밀번호에 일치하는 정보가 있는지 확인하기위한 select 메소드
				company = cmdao.login(paraMap);
				
				if(company.getPK_COMPANY_CODE() != null) {
					
					String c_Choice = "";
					do {
						System.out.println("---------------------------구인회사 메인 메뉴 " + "["+ company.getCOMPANY_NAME()+ "로그인중 ...]------------------------------------------");
						System.out.println("1. 공고             2.  구직자 찾기             3. 내 기업 정보            4. 이전메뉴로");
						System.out.println("----------------------------------------------------------------------- -------------------------------------");
						
						System.out.println("▷ 메뉴번호 선택  : ");
						c_Choice = sc.nextLine();
						
						switch (c_Choice) {
						
						case "1": // 공고 관련
							recruit_notice(sc,company);
							
							break;
							
						case "2": // 구직자 찾기
							member_find(company,sc);
							
							break;
							
						case "3": // 내 회사 정보 조회
							recruit_notice_search(company,sc);
							
							break;
						case "4": // 이전메뉴로
							
							break;

							
						default:
							System.out.println("<경고> 없는 번호 입니다. 다시 입력하세요.");
							break;
						}
					}while(!"4".equals(c_Choice));
					
					
					
					
					
					
				} else {
					System.out.println("[경고] 사업자등록번호 혹은 비밀번호가 일치하지 않습니다.");
				}
				
				break;
			
			case "2": // 아이디, 비밀번호 찾기
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
						System.out.print("▶ 이메일 : ");
						String company_email = sc.nextLine();
						
						System.out.println("\n▶ 비밀번호 : ");
						String company_passwd = sc.nextLine();
						
						Map<String,String> find_com_id = new HashMap<>();
						find_com_id.put("com_email", company_email);
						find_com_id.put("com_passwd", company_passwd);
						
						
						String company_id = cmdao.find_com_id(find_com_id); // 이메일과 비밀번호가 일치한다면 사업자등록번호를 보여주기.
						
						if(company_id != null) {
							System.out.println("▶ 아이디(사업자 등록번호) : " + company_id);
						}
						else {
							System.out.println("[경고] 일치하는 정보가 없습니다. ");
						}
						
						break;
					case "2": // 비밀번호 찾기
						
						System.out.print("▶ 아이디(사업자등록번호) : ");
						String com_id = sc.nextLine();
						
						System.out.print("\n▶ 이메일 : ");
						company_email = sc.nextLine();
						Map<String,String> find_com_passwd = new HashMap<>();
						find_com_passwd.put("com_email", company_email);
						find_com_passwd.put("com_id", com_id);
						
						int is_change_com_passwd = cmdao.find_com_passwd(find_com_passwd); // 입력한 정보가 맞으면 비밀번호를 변경할 수 있게 해주는 메소드 생성.
						
						if(is_change_com_passwd == 1) {
							System.out.println("[안내] 비밀번호가 성공적으로 변경되었습니다.");
						}
						else if(is_change_com_passwd == 0) {
							System.out.println("[안내] 비밀번호 변경을 취소하였습니다.");
						}
						else {
							System.out.println("[경고] 입력하신 정보가 일치하지 않습니다.");
						}
						
						
						break;
					case "3": // 로그인메뉴로 이동
						
						break;

					default:
						find_menu_exit = false;
						System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.");
						break;
					}
					
					
					
				}while(!find_menu_exit);	
				
				
				break;
			case "3": // 메인메뉴로 이동.
				
				break;	
			default:
				System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.\n");
				break;	
			
			}
			
		} while(!"3".equals(choice_menu));
		
		 
	} // end of public CompanyDTO com_login(Scanner sc) 
	
	
	
	
	
	
	
	
	
	private void member_find(CompanyDTO company, Scanner sc) {
		 String F_R= null;
do {
	  System.out.println("---------------------------------- 공고 조회 ["  + company.getCOMPANY_NAME() +" 로그인중] -------------------------------------");
	  System.out.println("               1. 학과 조회하기        2. 연령대        3. 이전메뉴로   ");   
	  System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	
	
	System.out.print("▶ 메뉴번호 선택 :");
	 
	
	F_R = sc.nextLine(); 
	 
	
	CompanyDAO Cdao = new CompanyDAO_imple();
   switch (F_R) {
   case "1":   // 학과 조회하기 
       String searchDepartment;
       List<Map<String, String>> mapList = null;
       boolean istrue = false;
       do {
    	   istrue = false;
           System.out.println("▷ 학과를 입력하세요 : ");
           searchDepartment = sc.nextLine();  
           
           
           if(searchDepartment.isBlank()) {
        	   System.out.println("[경고] 공백은 입력할 수 없습니다.");
           }
           else {
        	   Map<String, String> paraMap = new HashMap<>();
               paraMap.put("SD", searchDepartment);
               
            
               mapList = Cdao.SEARCH_DP(paraMap);
               
             
               if (mapList.isEmpty()) {
                   System.out.println(">> 해당 학과를 찾을 수 없습니다. <<");
                   break;
               }
               else {
            	   istrue = true;
               }
           }
           
           
           
       } while (searchDepartment.isEmpty() || mapList.isEmpty());
       
       if(istrue) {
	        System.out.println("---------------------------------- 공고 조회 ["  + company.getCOMPANY_NAME() +" 로그인중] -------------------------------------");
	        System.out.println("     이름 		학과명			이력서보기   ");   
	        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	        
	        StringBuilder sb = new StringBuilder();
	        for (Map<String, String> map : mapList) {
	            sb.append(map.get("user_name") + "\t\t");
	            sb.append(map.get("school_dept_name") + "\t\t");
	            sb.append(map.get("self_introduce") + "\t\n");
	        }
	        System.out.println(sb.toString());
	        break;	
       }
       break;
      
   case "2": // 연령대 조회하기 
       String searchAge;
       List<Map<String, String>> mapList11 = new ArrayList<>();
       Map<String, String> paraMap11 = new HashMap<>();
       do {
    	   System.out.println("[안내] 연령대는 반드시 숫자로만 입력하세요 두자리의 숫자만 입력됩니다.");
           System.out.print("▷ 연령대를 입력하세요 : ");
           searchAge = sc.nextLine();
           
           if(searchAge.isBlank()) {
        	   System.out.println("[경고] 공백은 입력할 수 없습니다.");
           }
           else if(searchAge.length()!=2) {
        	   System.out.println("[경고] 두자리의 숫자만 입력하세요");
           }
           else {
        	   
        	   boolean is_all_number = true;
        	   for(int i = 0; i < searchAge.length(); i++) {
        		   if(!('0'<= searchAge.charAt(i) && searchAge.charAt(i)<='9')) {
        			   is_all_number = false;
        			   break;
        		   }
        	   }
        	   
        	   if(is_all_number) {
        		   System.out.println(searchAge.substring(0,1) + '0');
                   searchAge = searchAge.substring(0,1) + '0';
                 
                 paraMap11.put("SA", searchAge);
                 
                 mapList11 = cmdao.SEARCH_AGE(paraMap11);
                 
                 if (mapList11.isEmpty()) {
                     System.out.println(">> 해당 연령대를 찾을 수 없습니다. 다시 입력하세요. <<");
                 }
        	   }
        	   
        	   
           }
           
           

       } while (searchAge.isEmpty() || mapList11.isEmpty());

       System.out.println("---------------------------------- 연령대 조회 [" + company.getCOMPANY_NAME() + " 로그인중] -------------------------------------");
       System.out.println("             이름          나이         연령대  ");
       System.out.println("--------------------------------------------------------------------------------------------------------------------------");

    
       StringBuilder sb1 = new StringBuilder();
       for (Map<String, String> map : mapList11) {
           sb1.append(map.get("USER_NAME") + "\t\t");
           sb1.append(map.get("MAGE") + "\t\t");
           sb1.append(map.get("AGED") + "\n");
       }
       
       
       System.out.println(sb1.toString());
       break;


  
    	
    case "3" : // 이전 메뉴로
   	 
   	 break;
      
    default:
 	   
     	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
    	break; 
    
   } //END OF  switch (s_choice) 
   } while(!"3".equals(F_R));



		
		
		
		
		
		
	}
	
	
	
	
	
	// 내 회사 정보 조회
	private void recruit_notice_search(CompanyDTO company, Scanner sc) {
		
		String choice_menu = "";
		do {
			
			System.out.println("--------------- [" + company.getCOMPANY_NAME() + "] 기업 정보 메뉴 ---------------");
			System.out.println("   1. 회사 정보 조회           2. 회사 정보 수정           3. 이전메뉴로");
			System.out.println("-------------------------------------------------------------------------------");
			System.out.print("▶ 메뉴번호 선택 : ");
			choice_menu = sc.nextLine();
			
			switch (choice_menu) {
			case "1": // 회사 정보 조회
				view_my_company_info(company,sc);
				
				break;
			case "2": // 회사 정보 수정
				change_my_company_info(company,sc);
				break;
			case "3": // 이전 메뉴로
				
				
				break;

			default:
				System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.");
				break;
			}
			
			
		}while(!"3".equals(choice_menu));
		
		
		
		
		
	}// end of private void recruit_notice_search(CompanyDTO company2, Scanner sc)
	
	
	

	private void change_my_company_info(CompanyDTO company, Scanner sc) {
			
			System.out.print("▶ 비밀번호를 입력하세요 : ");
			String passwd = sc.nextLine();
			
			CompanyDAO cdao = new CompanyDAO_imple();
			CompanyDTO cdto = new CompanyDTO();
			Map<String,String> map = cdao.view_my_company_info(company,passwd); // 내 회사의 상세정보를 보여주는 메소드 생성.
			
			if(map.get("company_name")!=null) {
				System.out.println("---------- 수정 전 정보 ----------");
				System.out.println("1. 기업명 : " + map.get("company_name"));
				System.out.println("2. 매출액 : " + map.get("sales"));
				System.out.println("3. 사원수 : " + map.get("number_of_employee"));
				System.out.println("4. 홈페이지 : " + map.get("homepage"));
				System.out.println("5. 업종 : " + map.get("buss_type_name"));
				System.out.println("6. 기업주소 : " + map.get("company_address"));
				System.out.println("7. 기업규모 : " + map.get("com_size"));
				System.out.println("8. 전화번호 : " + map.get("company_tel"));
				System.out.println("9. 이메일 : " + map.get("company_email"));
				
				
				
				
				// == 기업명 변경 == //
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("[안내] 수정하지 않고 기존값을 유지하시려면 그냥 엔터를 누르세요");
				String change_company_name = "";
				do {
					System.out.println("▶ 변경할 기업 명 : ");
					change_company_name = sc.nextLine();
					if(change_company_name.isBlank()) {
						change_company_name = map.get("company_name");
						break;
					}
					else {
						cdto.setCOMPANY_NAME(change_company_name);
						if(cdto.getCOMPANY_NAME()!=null) {
							break;
						}
						else {
						}
					}
				}while(true);
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				// == 매출액 변경 == // 
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("[안내] 수정하지 않고 기존값을 유지하시려면 그냥 엔터를 누르세요");
				String change_sales = "";
				do {
					System.out.println("▶ 변경할 매출액 [매출액은 반드시 숫자로만 입력하세요] : ");
					change_sales = sc.nextLine();
					
					if(change_sales.isBlank()) {
						change_sales = map.get("sales").substring(0,map.get("sales").indexOf("원"));
						break;
					}
					
					boolean is_all_number = true;
					for(int i = 0; i < change_sales.length(); i++) {
						if(!('0'<=change_sales.charAt(i) && change_sales.charAt(i) <= '9')) {
							System.out.println("[경고] 반드시 숫자로만 입력해야합니다.");
							is_all_number = false;
							break;
						}
					}
					
					
					if(is_all_number) {
						long l_change_sales = Long.parseLong(change_sales);
		            	DecimalFormat df = new DecimalFormat("#,###");
		            	change_sales = df.format(l_change_sales);
		            	break;
					}
					
				}while(true);
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				// == 사원수 변경 == // 
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				String change_number_of_employee = "";
				System.out.println("[안내] 수정하지 않고 기존값을 유지하시려면 그냥 엔터를 누르세요");
				do {
					boolean is_all_number = true;
					System.out.print("▶ 수정할 사원수를 입력하세요 : ");
					change_number_of_employee = sc.nextLine();
					
					if(change_number_of_employee.isBlank()) {
						change_number_of_employee = map.get("number_of_employee");
						break;
					}
					
					else {
						for(int i = 0; i < change_number_of_employee.length(); i++) {
							if(!('0'<=change_number_of_employee.charAt(i) && change_number_of_employee.charAt(i) <= '9')) {
								is_all_number = false;
								break;
							}
						}
						
						if(is_all_number) {
							break;
						}
						else {
							System.out.println("[경고] 반드시 숫자만 입력하세요");
						}
					}
					
				}while(true);
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				// == 홈페이지 주소 변경 == //
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("[안내] 수정하지 않고 기존값을 유지하시려면 그냥 엔터를 누르세요");
				String change_homepage = "";
				
				
				do {
					System.out.println("\n[안내] 홈페이지는 \"https://\" 와 \".com\" 을 모두 포함하여 입력해주세요.");
					System.out.print("▶ 수정할 홈페이지 주소를 입력하세요 : ");
					
					change_homepage = sc.nextLine();
					
					if(change_homepage.isBlank()) {
						change_homepage = map.get("homepage");
						break;
					}
					else {
						if(change_homepage.length() > 50) {
				 	           System.out.println("<경고> 홈페이지는 50글자 이내로 입력하세요. \n");
				 	        }
				            else if(!(      (change_homepage.contains("https://") && (change_homepage.contains(".com")))    )){
				            	System.out.println("<경고> 홈페이지는 https:// 와 .com 를 모두 포함하여 입력하세요. ");
					 	    }
					 	      else {
					 	    	  
					 	    	 if(!(change_homepage.substring(0,8).equals("https://") && change_homepage.substring(change_homepage.indexOf(".com")).equals(".com")) ) {
						            	System.out.println("[경고] 잘못된 주소입니다. 다시한번 확인하세요");
						            }
					 	    	 else {
					 	    		 break;
					 	    	 }
					 	    }
					}
					
				}while(true);
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				// == 업종 변경 == // 
				String buss_type_code = "";
				outer:
				do {
					
					System.out.println("======== 업종 목록 ========");
	            	  List<String> buss_type_list = cdao.view_all_buss_type_list(); // 모든 업종 목록을 list에 담아주는 메소드 생성.
	            	  
	            	  List<String> no_buss_type_list = new ArrayList<>();
	            	  for(int i = 0; i < buss_type_list.size(); i++) {
	            		  String s = i+1+". "+buss_type_list.get(i);
	            		  no_buss_type_list.add(s);
	            		  System.out.println(no_buss_type_list.get(i));
	            	  }
	            	  boolean is_all_number = true;
	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	            	  
	            	  do {
	            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) IT.인터넷 > 4] : ");
	  	            	  String choice_no = sc.nextLine();
	  	            	  
	  	            	  if(choice_no.isBlank()) {
	  	            		buss_type_code = cdao.get_buss_type_code(map.get("buss_type_name")); // 입력한 업종명과 일치하는 코드를 반환.
	  	            		  break outer;
	  	            	  }
	  	            	  else {
	  	            		  is_all_number = true;
	  	            		for(int i = 0;i < choice_no.length(); i++) {
		  	            		  if(!('0'<=choice_no.charAt(i) && choice_no.charAt(i) <= '9')) {
		  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
		  	            			  is_all_number = false;
		  	            			  break;
		  	            		  }
		  	            	  }
	  	            		String buss_type_name = "";
	  	            		if(is_all_number) {
	  	            			boolean is_can_number = false;
	  	            			for(int i = 0;i<no_buss_type_list.size();i++) {
	  	            				if(choice_no.equals(no_buss_type_list.get(i).substring(0,no_buss_type_list.get(i).indexOf(".")))) {
	  	            					buss_type_name = no_buss_type_list.get(i).substring(no_buss_type_list.get(i).indexOf(".")+2);
	  	            					is_can_number = true;
	  	            					break;
	  	            				}
	  	            			}
	  	            			
	  	            			
	  	            			if(is_can_number) {
	  	            				buss_type_code = cdao.get_buss_type_code(buss_type_name);
	  	            				break outer;
	  	            			}
	  	            			else {
	  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
	  	            			}
	  	            		}
	  	            		
	  	            		
	  	            	  }
	            	  }while(true);
					
				}while(true);
				
				
				// == 기업 주소 변경 == // 
				System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
				System.out.print("▶ 변경할 주소를 입력하세요 : ");
				String change_address = sc.nextLine();
				
				if(change_address.isBlank()) {
					change_address = map.get("company_address");
				}
				
				
				// == 기업 규모 변경 == // 
				String company_size_code = "";
				outer:
				do {
					
					System.out.println("======== 기업규모 목록 ========");
	            	  List<String> company_size_list = cdao.view_all_company_size_list(); // 모든 기업규모 목록을 list에 담아주는 메소드 생성.
	            	  
	            	  List<String> no_company_size_list = new ArrayList<>();
	            	  for(int i = 0; i < company_size_list.size(); i++) {
	            		  String s = i+1+". "+company_size_list.get(i);
	            		  no_company_size_list.add(s);
	            		  System.out.println(no_company_size_list.get(i));
	            	  }
	            	  boolean is_all_number = true;
	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	            	  
	            	  do {
	            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 중소기업 > 3] : ");
	  	            	  String choice_no = sc.nextLine();
	  	            	  
	  	            	  if(choice_no.isBlank()) {
	  	            		company_size_code = cdao.get_company_size_code(map.get("com_size")); // 입력한 기업규모명과 일치하는 코드를 반환.
	  	            		  break outer;
	  	            	  }
	  	            	  else {
	  	            		  is_all_number = true;
	  	            		for(int i = 0;i < choice_no.length(); i++) {
		  	            		  if(!('0'<=choice_no.charAt(i) && choice_no.charAt(i) <= '9')) {
		  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
		  	            			  is_all_number = false;
		  	            			  break;
		  	            		  }
		  	            	  }
	  	            		String company_size_name = "";
	  	            		if(is_all_number) {
	  	            			boolean is_can_number = false;
	  	            			for(int i = 0;i<no_company_size_list.size();i++) {
	  	            				if(choice_no.equals(no_company_size_list.get(i).substring(0,no_company_size_list.get(i).indexOf(".")))) {
	  	            					company_size_name = no_company_size_list.get(i).substring(no_company_size_list.get(i).indexOf(".")+2);
	  	            					is_can_number = true;
	  	            					break;
	  	            				}
	  	            			}
	  	            			
	  	            			
	  	            			if(is_can_number) {
	  	            				company_size_code = cdao.get_company_size_code(company_size_name);
	  	            				break outer;
	  	            			}
	  	            			else {
	  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
	  	            			}
	  	            		}
	  	            		
	  	            		
	  	            	  }
	            	  }while(true);
					
				}while(true);
				
				
				
				// == 전화번호 변경 == // 
				System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
				String change_tel = "";
				do {
		               System.out.println("\n[안내] 전화번호는 '-'을 제외한 숫자로만 입력해주세요.");
		               System.out.print("▷ 변경할 전화번호: ");
		               change_tel = sc.nextLine();
		               
		               if(change_tel.isBlank()) {
		            	   change_tel = map.get("company_tel");
		            	   break;
		               }
		               else {
		            	   if(!(Pattern.matches("^[0-9]*$", change_tel)) || ( change_tel.length() <9 || change_tel.length() > 11) ) {
				                  System.out.println("<경고> 전화번호는 숫자로만 입력해주세요.");
				               }
				               else {
				            	   
				            	   if(change_tel.length() == 11)
				            	   {
				            		   System.out.println("▷ 입력된 전화번호 : " + change_tel.substring(0,3) + "-" + change_tel.substring(3,7) + "-" + change_tel.substring(7)); 
						                  change_tel = change_tel.substring(0,3) + "-" + change_tel.substring(3,7) + "-" + change_tel.substring(7);
						                  break;
				            	   }
				            	   
				            	   else if (change_tel.length() == 10) {
				            		   System.out.println("▷ 입력된 전화번호 : " + change_tel.substring(0,2) + "-" + change_tel.substring(2,6) + "-" + change_tel.substring(6)); 
						                  change_tel = change_tel.substring(0,2) + "-" + change_tel.substring(2,3) + "-" + change_tel.substring(6);
						                  break;
				            	   }
				            	   else {
				            		   System.out.println("▷ 입력된 전화번호 : " + change_tel.substring(0,2) + "-" + change_tel.substring(2,5) + "-" + change_tel.substring(5)); 
						                  change_tel = change_tel.substring(0,2) + "-" + change_tel.substring(2,5) + "-" + change_tel.substring(5);
						                  break;
				            	   }
				            	   
				            	   
				                  
				               }
		               }
		               
		               
		               
		            } while(true); // end of do_while com_tel--------------------------------------
				
				
				// == 이메일 변경 == // 
				String change_email = "";
	            do {
	               System.out.println("\n[안내] 이메일은 영문, 숫자로만 입력해주세요.");
	               System.out.print("▷ 이메일: ");
	               change_email = sc.nextLine();
	               
	               if(change_email.isBlank()) {
	            	   change_email = map.get("company_email");
	            	   break;
	               }
	               else {
	            	   if(!(Pattern.matches("^[0-9a-zA-Z]*$", change_email)) || change_email.length()>15 ) {
	 	                  System.out.println("<경고> 이메일은 공백을 제외한 15글자 이내의 영문, 숫자로만 입력해 주세요.");
	 	               }
	 	               else {
	 	                  
	 	                  // 이메일 입력했을 때 뒤에 @naver.com 어떤거 선택할건지 뜨게 하기
	 	                  String domain_choice = "";
	 	                  
	 	                  System.out.println("\n▶ 입력하실 이메일 도메인을 선택하세요."); 
	 	                  System.out.println("1.@naver.com\n"
	 	                               + "2.@gmail.com\n"
	 	                               + "3.@daum.net\n"
	 	                               + "4.@hanmail.net\n"
	 	                               + "5.@nate.com\n"
	 	                               + "6.@kakao.com\n");
	 	                  
	 	                  System.out.print("▶ 도메인 번호 선택 : " + domain_choice); 
	 	                  domain_choice = sc.nextLine();
	 	                  
	 	                  
	 	                  switch (domain_choice) {
	 	                     case "1":
	 	                    	change_email += "@naver.com";
	 	                        break;
	 	                        
	 	                     case "2":
	 	                    	change_email += "@gmail.com";
	 	                        break;
	 	                        
	 	                     case "3":
	 	                    	change_email += "@daum.net";
	 	                        break;
	 	                        
	 	                     case "4":
	 	                    	change_email += "@hanmail.net";
	 	                        break;
	 	                        
	 	                     case "5":
	 	                    	change_email += "@nate.com";
	 	                        break;
	 	                        
	 	                     case "6":
	 	                    	change_email += "@kakao.com";
	 	                        break;
	 	                     
	 	                     default:
	 	                        System.out.println("<경고> 도메인 번호는 1부터 6까지의 정수로 입력해주세요.");
	 	                        break;
	 	                  }// end of switch(domain_choice)
	 	                  break;
	 	               }
	               }
	               
	               
	               
	            } while(true);// end of do_while com_email--------------------------------------
				
	            
	            
				/*
				change_company_name
				change_sales
				change_number_of_employee
				change_homepage
				buss_type_code
				change_address
				company_size_code
				change_tel
				change_email
				*/
				
	            
	            Map<String, String> paraMap = new HashMap<>();
	            paraMap.put("change_company_name", change_company_name);
	            paraMap.put("change_sales", change_sales);
	            paraMap.put("change_number_of_employee", change_number_of_employee);
	            paraMap.put("buss_type_code", buss_type_code);
	            paraMap.put("change_address", change_address);
	            paraMap.put("company_size_code", company_size_code);
	            paraMap.put("change_tel", change_tel);
	            paraMap.put("change_email", change_email);
	            
	            int number = cmdao.change_company_detail(company,paraMap); // 저장한 값을 토대로 회사 상세 정보를 변경해주는 메소드 생성        
				
	           if(number == 1) {
	        	   System.out.println("[안내] 기업정보 수정이 성공적으로 이루어졌습니다.");
	           }
	           else if(number == 0) {
	        	   System.out.println("[안내] 기업 정보 수정을 취소했습니다.");
	           }
	           else {
	        	   System.out.println("[경고] 정보 수정을 실패했습니다 ");
	           }
	            
			} // end of if(map.get("company_name")!=null)
			
			
			
			
			
			
			
			
			
			
			
			else {
				System.out.println("[경고] 비밀번호가 일치하지 않습니다.");
			}
			
		
	} // end of private void change_my_company_info(CompanyDTO company2, Scanner sc)









	// 회사 정보 조회
	private void view_my_company_info(CompanyDTO company, Scanner sc) {
		
		System.out.print("▶ 비밀번호를 입력하세요 : ");
		String passwd = sc.nextLine();
		
		CompanyDAO cdao = new CompanyDAO_imple();
		Map<String,String> map = cdao.view_my_company_info(company,passwd); // 내 회사의 상세정보를 보여주는 메소드 생성.
		
		if(map.get("company_name")!=null) {
			System.out.println("---------- 내 기업 정보 ----------");
			System.out.println("1. 기업명 : " + map.get("company_name"));
			System.out.println("2. 매출액 : " + map.get("sales"));
			System.out.println("3. 사원수 : " + map.get("number_of_employee"));
			System.out.println("4. 홈페이지 : " + map.get("homepage"));
			System.out.println("5. 업종 : " + map.get("buss_type_name"));
			System.out.println("6. 기업주소 : " + map.get("company_address"));
			System.out.println("7. 기업규모 : " + map.get("com_size"));
			System.out.println("8. 전화번호 : " + map.get("company_tel"));
			System.out.println("9. 이메일 : " + map.get("company_email"));
		}
		else {
			System.out.println("[경고] 입력하신 정보가 일치하지 않습니다.");
		}
		
		
	} // end of private void view_my_company_info(CompanyDTO company, Scanner sc)


	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// *** 공고(등록, 수정, 삭제, 이전메뉴로) *** //
	private void recruit_notice(Scanner sc, CompanyDTO company) {
	
		
		String choice = "";
		do {
			System.out.println("------------------------------------ 공고 메뉴 ------------------------------------");
			System.out.println("    1. 등록한공고조회     2. 공고 등록     3.공고 수정     4. 공고 삭제     5. 이전메뉴");
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("▷ 번호를 선택하세요. : ");
			choice = sc.nextLine();
			
			switch (choice) {
			
				case "1": // 등록한 공고조회
				
				view_my_recruit_notice(sc, company);
				// 공고목록 띄우기
				// 선택하면 1. 지원자 수 2.
				break;
				
				case "2": // 공고 등록
				
				recruit_notice_register(sc, company);
				
				break;
				
				case "3": // 내가 올린 공고 수정
				
				recruit_notice_sujug(company, sc);
				
				break;
				
				case "4": // 공고 삭제
				
				recruit_notice_delete(sc, company);
				
				break;
				
				case "5": // 이전메뉴로
				
				
				break;
				
				default:
					System.out.println("[경고] 존재하지 않는 메뉴 번호입니다.");
				break;
			}
		}while(!"5".equals(choice));
	
	
	}
	
	
	
	// 공고 삭제
	private void recruit_notice_delete(Scanner sc, CompanyDTO company) {
		
		boolean is_num = true;
		CompanyDAO cdao = new CompanyDAO_imple();
		
				System.out.print("▶ 삭제할 공고 번호를 입력하세요 : ");
				String delete_recruit_no = sc.nextLine();
				
				for ( int i = 0; i < delete_recruit_no.length();i++) {
					if(!('0'<=delete_recruit_no.charAt(i) && delete_recruit_no.charAt(i)<='9')) {
						is_num = false;
						break;
					}
				}
				
				if(is_num) {
					
					if(delete_recruit_no.isBlank()) {
						System.out.println("[경고] 공백은 입력할 수 없습니다.");
					}
					else {
						Map<String,String> map = cdao.view_one_recruit_notice(company,delete_recruit_no); // 입력한 공고번호에 일치하는 공고 상세정보 출력하기
						
						
		            	  
		            	  
		            	  if(map.get("recruit_notice_name")!=null) {
		            		  System.out.println("========== 삭제할 공고 정보 ==========");
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
		            			  System.out.println(">>> 정말 삭제하시겠습니까? [Y/N] <<<");
			            		  String yn = sc.nextLine();
			            		  
			            		  if("y".equalsIgnoreCase(yn)) {
			            			  
			            			 int n = cdao.delete_recruit_notice(company,delete_recruit_no); //선택한 모집공고를 삭제하는 메소드 생성
			            			  
			            			 
			            			 if(n == 1) {
			            				 System.out.println(">> 공고가 성공적으로 삭제되었습니다. <<");
			            			 }
			            			 else if( n == 0) {
			            				 
			            			 }
			            			  
			            			  break;
			            		  }
			            		  else if("n".equalsIgnoreCase(yn)) {
			            			  System.out.println("[안내] 삭제를 취소했습니다.");
			            			  break;
			            		  }
			            		  else { 
			            			  System.out.println("[경고] 반드시 Y 혹은 N 중에서 입력하세요 ");
			            		  }
		            		  }while(true);
		            		  
					
		            	  }
		            	  else {
		            		  System.out.println("[안내] 공고번호에 일치하는 모집공고가 존재하지 않습니다.");
		            	  }
					
					}
				}
				else {
					System.out.println("[경고] 반드시 숫자만 입력가능합니다.");
				}
		
		
		
	} // end of private void recruit_notice_delete(Scanner sc, CompanyDTO company2)


	// 등록한 공고조회
	private void view_my_recruit_notice(Scanner sc, CompanyDTO company) {
		
		CompanyDAO cdao = new CompanyDAO_imple();
		
		int n = cdao.view_my_recruit_notice(company); // 내가 등록한 공고목록을 다 보여주는 메소드 생성.
		String menu_no = "";
		do {
			System.out.println("▶ 상세 공고 내용을 조회하시려면 1번, 상위메뉴로 돌아가시려면 2번을 선택하세요 : ");
			System.out.print("▷ 번호 선택 : ");
			menu_no = sc.nextLine();
			
			switch (menu_no) {
			case "1":
				if ( n == 1) {
					do {
						System.out.print("▶ 조회할 공고 번호를 입력하세요 : ");
						String change_recruit_notice_no = sc.nextLine();
						if(change_recruit_notice_no.isBlank()) {
							System.out.println("[경고] 공백은 입력할 수 없습니다.");
						}
						else {
							Map<String,String> map = cdao.view_one_recruit_notice(company,change_recruit_notice_no); // 입력한 공고번호에 일치하는 공고 상세정보 출력하기
							
							/*
			            	  map.put("recruit_step_name_1", rs.getString("recruit_step_name_1"));
			            	  map.put("recruit_step_name_2", rs.getString("recruit_step_name_2"));
			            	  map.put("recruit_step_name_3", rs.getString("recruit_step_name_3"));
			            	  map.put("recruit_step_name_4", rs.getString("recruit_step_name_4"));
			            	  map.put("recruit_step_name_5", rs.getString("recruit_step_name_5"));
			            	  */
			            	  
			            	  
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
			            		  break;
			            	  }
			            	  else {
			            		  System.out.println("[경고] 해당 공고번호는 없는 공고입니다 다시한번 확인해주세요.");
			            		  
			            	  }
							
						}
					} while(true);
				}
				break;
			case "2":
				
				break;

			default:
				System.out.println("[경고] 메뉴에 없는 번호입니다.");
				break;
			}
			
			
		} while(!"2".equals(menu_no));
		
	} // end of private void recruit_notice_register(Scanner sc, CompanyDTO company)

	
	
	
	///////////////////////////////////////////////////////////////
	// *** 공고 등록 *** //
	
	private void recruit_notice_register(Scanner sc, CompanyDTO company) {
	
	
	String recruit_name = "";
	String yearsal = "";
	String quali = "";
	String woodae  = "";
	String benefit = "";
	String get_re_step_code_2 = "";
	String get_re_step_code_3 = "";
	String get_re_step_code_4 = "";
	String get_re_step_code_5 = "";
	String address_code = "";
	
	
	System.out.println(">> 공고 등록 <<");
	
	System.out.println("[안내] 공고 제목은 필수입력사항이며 50글자까지 작성 가능합니다.");
	
	do {
	System.out.print("1. 공고 제목 : ");
	recruit_name = sc.nextLine();
	if( recruit_name.isEmpty() || recruit_name.length()  >= 50) {
	
	System.out.println("<경고> 공고 제목 필수입력사항이며 50글자까지 작성가능합니다.");
	}
	else  {
	break;
	}
	
	}while(true);
	
	
	System.out.println("\n"+"2. 회사명 : " + company.getCOMPANY_NAME());
	System.out.println("[안내] 연봉은 필수입력사항이며 0000만원 형식으로 숫자만 입력해주세요.");
	
	do {
	System.out.print("3. 연봉 : ");
	yearsal  =  sc.nextLine();
	
	if( yearsal.isEmpty() || !(Pattern.matches("^[0-9]*$", yearsal)) ) {
	System.out.println("<경고> 연봉은 필수입력 사항이며 숫자만 입력하세요. ");
	}
	else {
	DecimalFormat decFormat = new DecimalFormat("###,###");
	yearsal = decFormat.format(Integer.parseInt(yearsal));
	break;
	}
	}while(true);
	
	
	System.out.println("[안내] 자격요건은 필수입력사항이며 30글자까지 작성가능합니다.");
	do {
	System.out.print("4. 자격요건 : ");
	quali  =  sc.nextLine();
	if( quali.isEmpty() || quali.length() > 30) {
	System.out.println("<경고> 자격요건은 필수입력사항이며 30글자까지 작성가능합니다.");
	}
	else {
	break;
	}
	}while(true);
	
	
	
	
	
	do {
	System.out.print("\n"+"5. 우대사항(없을 경우 엔터): ");
	woodae  =  sc.nextLine();
	if(woodae != null && woodae.length() == 0 ) {
	break;
	}
	else{
	if(woodae.length() > 50) {
	System.out.println("<경고> 50글자 이내로 입력하세요.");
	}
	else {
	break;
	}
	
	}
	}while(true);
	
	
	do {
	System.out.print("\n"+"6. 복리후생(없을 경우 엔터): ");
	benefit  =  sc.nextLine();
	if(benefit != null && benefit.length() == 0) {
	break;
	}
	else {
	if(benefit.length() > 200) {
	System.out.println("<경고> 200글자 이내로 입력하세요. ");
	}
	else {
	break;
	}
	}
	}while(true);
	
	//*** 직위를 입력하는 메소드 *** //
	System.out.println(">> [직위 번호 목록] << ");
	int i = 0;
	List<Map<String,String>> list_map = cmdao.view_position();
	
	List<String> list_map_no = new ArrayList<>();
	for(Map<String,String> map:list_map) {
	
	list_map_no.add(i, i+1 + "." + map.get("position_name")); 
	System.out.println( list_map_no.get(i));
	i++;
	}
	
	String get_po_name = null;
	outer:
	do {
	System.out.print("▶ 직위 번호 선택 : ");
	String choice_num = sc.nextLine();
	
	
	
	for(int j = 0;j < list_map_no.size(); j++) {
	if(!(list_map_no.get(j).substring(0,list_map_no.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_po_name = list_map_no.get(j).substring(list_map_no.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_po_name == null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	else if(Pattern.matches("^[가-힣A-Za-z]*$^", get_po_name)){
	
	System.out.println();
	}
	}while(true);
	
	String get_po_type_code = cmdao.search_position_code(get_po_name);
	
	System.out.println("7. 선택한 모집 직위 : " + get_po_name);
	//*** 직위를 입력하는 메소드 끝 *** //
	
	
	
	//*** 근무형태를 입력하는 메소드 *** //
	System.out.println("\n"+">> [근무형태 목록] << ");
	i = 0;
	List<Map<String,String>> list_map_2 = cmdao.view_work_type();
	
	List<String> list_map_no_2 = new ArrayList<>();
	for(Map<String,String> map:list_map_2) {
	
	list_map_no_2.add(i, i+1 + "." + map.get("worktype_name")); 
	System.out.println( list_map_no_2.get(i));
	i++;
	}
	
	
	String get_worktype_name = null;
	outer:
	do {
	System.out.print("▶ 근무형태 번호 선택 : ");
	String choice_num = sc.nextLine();
	
	for(int j = 0; j < list_map_no_2.size(); j++) {
	if(!(list_map_no_2.get(j).substring(0,list_map_no_2.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_worktype_name = list_map_no_2.get(j).substring(list_map_no_2.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_worktype_name == null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	}while(true);
	
	String get_worktype_code = cmdao.search_worktype_code(get_worktype_name);
	
	System.out.println("8. 선택한 근무형태 : " + get_worktype_name);
	
	
	
	//*** 직무형태를 입력하는 메소드 *** //
	System.out.println("\n"+">> [직무형태 목록] << ");
	i = 0;
	List<Map<String,String>> list_map_3 = cmdao.view_job_type();
	
	List<String> list_map_no_3 = new ArrayList<>();
	for(Map<String,String> map:list_map_3) {
	
	list_map_no_3.add(i, i+1 + "." + map.get("job_type_name")); 
	System.out.println( list_map_no_3.get(i));
	i++;
	}
	
	
	String get_jobtype_name = null;
	outer:
	do {
	System.out.print("▶ 직무형태 번호 선택 : ");
	String choice_num = sc.nextLine();
	
	for(int j = 0; j < list_map_no_3.size(); j++) {
	if(!(list_map_no_3.get(j).substring(0,list_map_no_3.get(j).indexOf(".")).equals(choice_num))) {
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
	
	String get_jobtype_code = cmdao.search_jobtype_code(get_jobtype_name);
	
	System.out.println("9. 선택한 직무형태 : " + get_jobtype_name);
	
	
	
	System.out.println("\n"+"[모집전형 종류 테이블] ");
	i = 0;
	List<Map<String,String>> list_map_4 = cmdao.view_recruit_step();
	
	List<String> list_map_no_4 = new ArrayList<>();
	for(Map<String,String> map:list_map_4) {
	
	list_map_no_4.add(i, i+1 + "." + map.get("recruit_step_name")); 
	System.out.println( list_map_no_4.get(i));
	i++;
	}
	
	String get_re_step_name = null;
	System.out.println("[안내] 모집 전형 1단계는 필수입력사항입니다. ");
	outer:
	do {
	System.out.print("10-1. 모집 전형 1단계 : ");
	String choice_num = sc.nextLine();
	
	for(int j = 0; j < list_map_no_4.size(); j++) {
	if(!(list_map_no_4.get(j).substring(0,list_map_no_4.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_re_step_name = list_map_no_4.get(j).substring(list_map_no_4.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_re_step_name == null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	}while(true);
	
	String get_re_step_code_1 = cmdao.search_re_step(get_re_step_name);
	System.out.println("선택한 모집 전형 : " + get_re_step_name + "\n");
	
	
	
	System.out.println("[안내] 추가할 모집 전형이 없으면 엔터를 누르세요. ");
	out:
	do {
	get_re_step_name = null;
	outer:
	do {
	System.out.print("\n"+"10-2. 모집 전형 2단계 : ");
	String choice_num = sc.nextLine();
	if(choice_num != null && choice_num.length() == 0 ) {
	break out;
	}
	
	for(int j = 0; j < list_map_no_4.size(); j++) {
	if(!(list_map_no_4.get(j).substring(0,list_map_no_4.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_re_step_name = list_map_no_4.get(j).substring(list_map_no_4.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_re_step_name == null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	}while(true);
	
	get_re_step_code_2 = cmdao.search_re_step(get_re_step_name);
	System.out.println("선택한 모집 전형 : " + get_re_step_name);
	
	
	if( !(get_re_step_code_2 != null && get_re_step_code_2.length() == 0) ) {
	
	get_re_step_name = null;
	outer:
	do {
	System.out.println("\n"+"10-3. 모집 전형 3단계 : ");
	String choice_num = sc.nextLine();
	if(choice_num != null && choice_num.length() == 0 ) {
	break out;
	}
	
	for(int j = 0; j < list_map_no_4.size(); j++) {
	if(!(list_map_no_4.get(j).substring(0,list_map_no_4.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_re_step_name = list_map_no_4.get(j).substring(list_map_no_4.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_re_step_name == null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	}while(true);
	
	get_re_step_code_3 = cmdao.search_re_step(get_re_step_name);
	System.out.println("선택한 모집 전형 : " + get_re_step_name);
	}
	
	
	if( !(get_re_step_code_3.isBlank()) ) {
	
	get_re_step_name = null;
	outer:
	do {
	System.out.println("\n"+"10-4. 모집 전형 4단계 : ");
	String choice_num = sc.nextLine();
	if(choice_num != null && choice_num.length() == 0 ) {
	break out;
	}
	
	for(int j = 0; j < list_map_no_4.size(); j++) {
	if(!(list_map_no_4.get(j).substring(0,list_map_no_4.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_re_step_name = list_map_no_4.get(j).substring(list_map_no_4.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_re_step_name ==  null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	}while(true);
	
	get_re_step_code_4 = cmdao.search_re_step(get_re_step_name);
	System.out.println("선택한 모집 전형 : " + get_re_step_name);
	
	}
	
	
	if( !(get_re_step_code_4.isBlank()) ) { 
	
	get_re_step_name = null;
	outer :
	do {
	System.out.println("\n"+"10-5. 모집 전형 5단계 : ");
	String choice_num = sc.nextLine();
	if(choice_num != null && choice_num.length() == 0 ) {
	break out;
	}
	
	for(int j = 0; j < list_map_no_4.size(); j++) {
	if(!(list_map_no_4.get(j).substring(0,list_map_no_4.get(j).indexOf(".")).equals(choice_num))) {
	}
	else {
	get_re_step_name = list_map_no_4.get(j).substring(list_map_no_4.get(j).indexOf(".")+1);
	break outer;
	}
	}
	if(get_re_step_name == null) {
	System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
	}
	}while(false);
	
	get_re_step_code_5 = cmdao.search_re_step(get_re_step_name);
	System.out.println("선택한 모집 전형 : " + get_re_step_name);
	
	}
	
	}while(true);
	
	
	
	
	// == 지역 입력 == // 
	//- 지역 : 서울시
	System.out.println("\n[안내] 지역은 시 혹은 구 까지만 입력하세요.");
	outer:
	do {
	System.out.print("▷ 회사가 위치하는 지역 : ");
	String address = sc.nextLine();
	
	List<String> address_list = cmdao.search_address(address);
	
	System.out.println("--------------------------------------");
	System.out.println("    [" + address + "]이(가) 포함된 지역 목록");
	System.out.println("--------------------------------------");
	
	List<String> address_list_no = new ArrayList<>();
	if(address_list.size()!=0) {
	for(i = 0; i < address_list.size() ; i++) {
	
	address_list_no.add( i + 1 + ". "+address_list.get(i));
	System.out.println(address_list_no.get(i));
	}
	
	do {
	System.out.print("\n▷ 해당 지역에서 선택하시려면 1번 지역검색으로 돌아가시려면 2번을 선택하세요 : ");
	String no = sc.nextLine();
	
	switch (no) {
	case "1":
	
	System.out.print("▷ 회사가 위치하는 지역의 번호를 입력하세요 : ");
	String address_no = sc.nextLine();
	if(address_no.isBlank()) {
	System.out.println("[경고] 공백은 입력할 수 없습니다.");
	break;
	}
	
	for(i = 0;i<address_list_no.size();i++) {
	
	if(address_list_no.get(i).substring(0,address_list_no.get(i).indexOf('.')).equals(address_no)) {
	
	
	address = address_list_no.get(i).substring(address_list_no.get(i).indexOf('.')+2); 
	
	
	break;
	}
	}
	
	if(address.contains("전체")) {
	System.out.println("\n[경고] '전체' 가 들어있는 지역은 선택할 수 없습니다. 상세주소를 선택해주세요.");
	break;
	}
	address_code = cmdao.search_address_code(address);
	
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
	
	} while(true); // end of do_while -----------------------
	
	
	
	
	String finish_day = "";
	
	do {
	System.out.print("9. 공고 마감일자 (yymmdd 형태로 입력) : ");
	finish_day = sc.nextLine();
	
	// 현재 날짜
	Date now = new Date();
	
	SimpleDateFormat sdformat = new SimpleDateFormat("yyMMdd");
	sdformat.setLenient(false);
	// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할 때 날짜로 변경되지 못하는 값일 경우 오류가 발생한다.
	// 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다.
	
	Date d_expireday = null;
	
	if (finish_day.isBlank()) {
	System.out.println(">> [경고] 마감일자는 필수 입력사항입니다. <<\n");
	
	} else {
	
	try {
	// === 문자열을 날짜 형태로 변환하기 ===
	d_expireday = sdformat.parse(finish_day); // 마감일자를 날짜 형태로 변환
	
	if (!(d_expireday.after(now))) {
	System.out.println(">> [경고] 마감일자는 현재 날짜보다 이후 날짜로만 입력 가능합니다. <<\n");
	
	} else {
	break;
	}
	
	} catch (ParseException e) {
	System.out.println(">> [경고] 달력에 존재하지 않는 값입니다. <<\n");
	continue;
	}
	}
	
	} while (true);
	
	
	
	
	
	
	Map<String, String> paraMap = new HashMap<String, String>();
	
	paraMap.put("re_company_code", company.getPK_COMPANY_CODE());
	paraMap.put("re_name", recruit_name);
	paraMap.put("re_compay_name", company.getCOMPANY_NAME());
	paraMap.put("re_yearsal", yearsal);
	paraMap.put("re_quali", quali);
	paraMap.put("re_woodae", woodae);
	paraMap.put("re_benefit", benefit);
	paraMap.put("re_position_code", get_po_type_code);
	paraMap.put("re_worktype_code", get_worktype_code);
	paraMap.put("re_jobtype_code", get_jobtype_code);
	paraMap.put("re_step_1", get_re_step_code_1);
	paraMap.put("re_step_2", get_re_step_code_2);
	paraMap.put("re_step_3", get_re_step_code_3);
	paraMap.put("re_step_4", get_re_step_code_4);
	paraMap.put("re_step_5", get_re_step_code_5);
	paraMap.put("re_locatiton", address_code);
	paraMap.put("re_finish_day", finish_day);
	
	
	int n = cmdao.join_recruit(paraMap);
	
	if(n == 1) {
	System.out.println(">> 모집공고 등록에 성공하셨습니다 <<");
	}
	else if(n == 0) {
	System.out.println(">> 모집공고 등록을 취소하셨습니다 <<");
	}
	else {
	System.out.println(">> 모집공고 등록을 실패했습니다 <<");
	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 내가 올린 공고 수정 
	private void recruit_notice_sujug(CompanyDTO company, Scanner sc) {
		
		
		CompanyDAO cdao = new CompanyDAO_imple();
		
		int n = cdao.view_my_recruit_notice(company); // 내가 등록한 공고목록을 다 보여주는 메소드 생성.
		
		if ( n == 1) {
			do {
				System.out.print("▶ 수정할 공고 번호를 입력하세요 : ");
				String change_recruit_notice_no = sc.nextLine();
				if(change_recruit_notice_no.isBlank()) {
					System.out.println("[경고] 공백은 입력할 수 없습니다.");
				}
				else {
					Map<String,String> map = cdao.view_one_recruit_notice(company,change_recruit_notice_no); // 입력한 공고번호에 일치하는 공고 상세정보 출력하기
					
					
	            	  
	            	  
	            	  if(map.get("recruit_notice_name")!=null) {
	            		  System.out.println("========== 기존 공고 정보 ==========");
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
	            		  
	            		  
	            		  System.out.println("========== 공고 수정 ==========");
	            		  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	            		  
	            		  // == 수정할 공고명 입력 == //
	            		  System.out.println(">> 공고명은 최대 50글자입니다.");
	            		  System.out.println("▶ 수정할 공고명 : ");
	            		  String change_recruit_notice_name = sc.nextLine();
	            		  if(change_recruit_notice_name.isBlank()) {
	            			  change_recruit_notice_name = map.get("recruit_notice_name");
	            		  }
	            		  
	            		  // == 수정할 연봉 입력 == //
	            		  boolean is_all_number = true;
	            		  String change_yearsal = "";
	            		  do {
	            			  is_all_number = true;
	            			  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
		            		  System.out.println(">> 연봉은 숫자로만 입력하세요 [5000 으로 입력시 5,000만원 으로 등록됩니다.] ");
		            		  System.out.println("▶ 수정할 연봉 : ");
		            		  change_yearsal = sc.nextLine();
		            		  if(change_yearsal.isBlank()) {
		            			  change_yearsal = map.get("yearsal").substring(0,map.get("yearsal").indexOf("만원"));
		            		  }
		            		  else {
		            			  char[] change_yearsal_arr = change_yearsal.toCharArray();
		            			  for(int i = 0;i < change_yearsal_arr.length; i++) {
		            				  if(!('0'<=change_yearsal_arr[i] && change_yearsal_arr[i] <= '9')) {
		            					  System.out.println("[경고] 연봉은 반드시 숫자로만 입력하세요.");
		            					  is_all_number = false;
		            					  break;
		            				  }
		            			  }
		            			  
		            			  int n_change_yearsal = Integer.parseInt(change_yearsal);
			  	            	  DecimalFormat df = new DecimalFormat("#,###");
			  	            	  change_yearsal = df.format(n_change_yearsal);
		            		  }
	            		  }while(!is_all_number);
	            		  
	            		  
	  	            	  
	            		  // == 수정할 직무 == // 
	  	            	  System.out.println("======== 직무 목록 ========");
	  	            	  List<String> job_type_list = cdao.view_all_job_type_list(); // 모든 직무 목록을 list에 담아주는 메소드 생성.
	  	            	  
	  	            	  List<String> no_job_type_list = new ArrayList<>();
	  	            	  for(int i = 0; i < job_type_list.size(); i++) {
	  	            		  String s = i+1+". "+job_type_list.get(i);
	  	            		  no_job_type_list.add(s);
	  	            		  System.out.println(no_job_type_list.get(i));
	  	            	  }
	  	            	  
	  	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	  	            	  String job_type_code = "";
	  	            	  do {
	  	            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 웹디자인 > 10] : ");
		  	            	  String choice_no = sc.nextLine();
		  	            	  
		  	            	  if(choice_no.isBlank()) {
		  	            		  job_type_code = cdao.get_job_type_code(map.get("job_type_name")); // 입력한 직무명과 일치하는 코드를 반환.
		  	            		  break;
		  	            	  }
		  	            	  else {
		  	            		  is_all_number = true;
		  	            		for(int i = 0;i < choice_no.length(); i++) {
			  	            		  if(!('0'<=choice_no.charAt(i) && choice_no.charAt(i) <= '9')) {
			  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
			  	            			  is_all_number = false;
			  	            			  break;
			  	            		  }
			  	            	  }
		  	            		String job_type_name = "";
		  	            		if(is_all_number) {
		  	            			boolean is_can_number = false;
		  	            			for(int i = 0;i<no_job_type_list.size();i++) {
		  	            				if(choice_no.equals(no_job_type_list.get(i).substring(0,no_job_type_list.get(i).indexOf(".")))) {
		  	            					job_type_name = no_job_type_list.get(i).substring(no_job_type_list.get(i).indexOf(".")+2);
		  	            					is_can_number = true;
		  	            					break;
		  	            				}
		  	            			}
		  	            			
		  	            			
		  	            			if(is_can_number) {
		  	            				job_type_code = cdao.get_job_type_code(job_type_name);
		  	            				break;
		  	            			}
		  	            			else {
		  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
		  	            			}
		  	            		}
		  	            		
		  	            		
		  	            	  }
	  	            	  }while(true);
	  	            	  
	  	            	  
	  	            	  // == 자격요건 수정 == //
	  	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	  	            	  System.out.println("▶ 수정할 자격요건 내용을 입력하세요 : ");
	  	            	  String change_quail = sc.nextLine();
	  	            	  
	  	            	  if(change_quail.isBlank()) {
	  	            		  change_quail = map.get("quail");
	  	            	  }
	  	            	  
	  	            	  // == 우대사항 수정 == //
	  	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	  	            	  System.out.println("▶ 수정할 우대사항 내용을 입력하세요 : ");
	  	            	  String change_woodae = sc.nextLine();
	  	            	  
	  	            	  if(change_woodae.isBlank()) {
	  	            		  change_woodae = map.get("woodae");
	  	            	  }
	  	            	  
	  	            	  // == 사내복지사항 수정 == //
	  	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	  	            	  System.out.println("▶ 수정할 복지사항 내용을 입력하세요 : ");
	  	            	  String change_benefit = sc.nextLine();
	  	            	  
	  	            	  if(change_benefit.isBlank()) {
	  	            		  change_benefit = map.get("benefit");
	  	            	  }
	  	            	  
	  	            	  
	  	            	  
	  	            	  // == 직책 수정 == //
	  	            	  System.out.println("======== 직책 목록 ========");
	  	            	  List<String> position_type_list = cdao.view_all_position_list(); // 모든 직책 목록을 list에 담아주는 메소드 생성.
	  	            	  
	  	            	  List<String> no_position_list = new ArrayList<>();
	  	            	  for(int i = 0; i < position_type_list.size(); i++) {
	  	            		  String s = i+1+". "+position_type_list.get(i);
	  	            		  no_position_list.add(s);
	  	            		  System.out.println(no_position_list.get(i));
	  	            	  }
	  	            	  
	  	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	  	            	  String position_code = "";
	  	            	  do {
	  	            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 사원 > 11] : ");
		  	            	  String choice_no = sc.nextLine();
		  	            	  
		  	            	  if(choice_no.isBlank()) {
		  	            		position_code = cdao.get_position_code(map.get("position_name")); // 입력한 직책명과 일치하는 코드를 반환.
		  	            		  break;
		  	            	  }
		  	            	  else {
		  	            		  is_all_number = true;
		  	            		for(int i = 0;i < choice_no.length(); i++) {
			  	            		  if(!('0'<=choice_no.charAt(i) && choice_no.charAt(i) <= '9')) {
			  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
			  	            			  is_all_number = false;
			  	            			  break;
			  	            		  }
			  	            	  }
		  	            		String no_position_name = "";
		  	            		if(is_all_number) {
		  	            			boolean is_can_number = false;
		  	            			for(int i = 0;i<no_position_list.size();i++) {
		  	            				if(choice_no.equals(no_position_list.get(i).substring(0,no_position_list.get(i).indexOf(".")))) {
		  	            					no_position_name = no_position_list.get(i).substring(no_position_list.get(i).indexOf(".")+2);
		  	            					is_can_number = true;
		  	            					break;
		  	            				}
		  	            			}
		  	            			
		  	            			
		  	            			if(is_can_number) {
		  	            				position_code = cdao.get_position_code(no_position_name);
		  	            				break;
		  	            			}
		  	            			else {
		  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
		  	            			}
		  	            		}
		  	            		
		  	            		
		  	            	  }
	  	            	  }while(true);
	  	            	  
	  	            	  
	  	            	  // == 채용형태 수정 == //
	  	            	  System.out.println("======== 채용형태 목록 ========");
	  	            	  List<String> worktype_list = cdao.view_all_worktype_list(); // 모든 채용형태 목록을 list에 담아주는 메소드 생성.
	  	            	  
	  	            	  List<String> no_worktype_list = new ArrayList<>();
	  	            	  for(int i = 0; i < worktype_list.size(); i++) {
	  	            		  String s = i+1+". "+worktype_list.get(i);
	  	            		no_worktype_list.add(s);
	  	            		  System.out.println(no_worktype_list.get(i));
	  	            	  }
	  	            	  
	  	            	  System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
	  	            	  String worktype_code = "";
	  	            	  do {
	  	            		System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 정규직 > 6] : ");
		  	            	  String choice_no = sc.nextLine();
		  	            	  
		  	            	  if(choice_no.isBlank()) {
		  	            		worktype_code = cdao.get_worktype_code(map.get("worktype_name")); // 입력한 채용형태명과 일치하는 코드를 반환.
		  	            		  break;
		  	            	  }
		  	            	  else {
		  	            		  is_all_number = true;
		  	            		for(int i = 0;i < choice_no.length(); i++) {
			  	            		  if(!('0'<=choice_no.charAt(i) && choice_no.charAt(i) <= '9')) {
			  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
			  	            			  is_all_number = false;
			  	            			  break;
			  	            		  }
			  	            	  }
		  	            		String worktype_name = "";
		  	            		if(is_all_number) {
		  	            			boolean is_can_number = false;
		  	            			for(int i = 0;i<no_worktype_list.size();i++) {
		  	            				if(choice_no.equals(no_worktype_list.get(i).substring(0,no_worktype_list.get(i).indexOf(".")))) {
		  	            					worktype_name = no_worktype_list.get(i).substring(no_worktype_list.get(i).indexOf(".")+2);
		  	            					is_can_number = true;
		  	            					break;
		  	            				}
		  	            			}
		  	            			
		  	            			
		  	            			if(is_can_number) {
		  	            				worktype_code = cdao.get_worktype_code(worktype_name);
		  	            				break;
		  	            			}
		  	            			else {
		  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
		  	            			}
		  	            		}
		  	            		
		  	            		
		  	            	  }
	  	            	  }while(true);
	  	            	  
	  	            	  
	  	            	  // == 근무지역 수정 == //
	  	            	String address_code = "";
	  	            	System.out.println("[안내] 기존의 정보를 수정하지 않고 그대로 유지하시려면 그냥 엔터를 누르세요");
		  	      		System.out.println("\n[안내] 지역은 시 혹은 구 까지만 입력하세요.");
		  	      		MemberDAO mdao = new MemberDAO_imple();
		  	      		outer:
		  	      		do {
		  	      			System.out.print("▷ 수정할 근무지역 : ");
		  	      			String change_address = sc.nextLine();
		  	      			
		  	      			if(change_address.isBlank()) {
		  	      				change_address = map.get("location_name");
		  	      			    address_code = mdao.search_address_code(change_address);
		  	      			    break;
		  	      			}
		  	      			else {
		  	      			//mdao를 이용하여 검색한 지역이 포함되어있는 모든 지역명을 select해오기 위한 search_address 메소드 생성.
			  	      			List<String> address_list = mdao.search_address(change_address);
			  	      			
			  	      			System.out.println("--------------------------------------");
			  	      			System.out.println("    [" + change_address + "]이(가) 포함된 지역 목록");
			  	      			System.out.println("--------------------------------------");
			  	      			
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
		  	      						
		  	      						System.out.print("▷ 근무 지역의 번호를 입력하세요 : ");
		  	      						String address_no = sc.nextLine();
		  	      						if(address_no.isBlank()) {
		  	      							System.out.println("[경고] 공백은 입력할 수 없습니다.");
		  	      							break;
		  	      						}
		  	      						
		  	      						change_address = "";
		  	      						// 뽑아온 list중에서 선택한 번호의 지역명을 번호없이 다시 지역명만 뽑아내는 for문.
		  	      						for(int i = 0;i<address_list_no.size();i++) {
		  	      							
		  	      							// 위에서 만든 숫자 + . + 지역명에서 . 앞까지의 숫자와 내가 입력한 숫자가 일치한다면
		  	      							if(address_list_no.get(i).substring(0,address_list_no.get(i).indexOf('.')).equals(address_no)) {
		  	      								
		  	      								// 숫자 + . + 지역명에서 . 뒷부분인 지역명만 따로 빼내 address 라는 변수에 다시 저장. 
		  	      							change_address = address_list_no.get(i).substring(address_list_no.get(i).indexOf('.')+2); 
		  	      								// 위에서 마지막에 +2가 붙은 이유는 . 뒤에 ' '(공백) 이 붙어있기 때문. 
		  	      								
		  	      								break;
		  	      							}
		  	      						}
		  	      						if(change_address.isBlank()) {
		  	      							System.out.println("[경고] 목록에 없는 번호입니다.");
		  	      							break;
		  	      						}
		  	      						if(change_address.contains("전체")) {
		  	      							System.out.println("\n[경고] '전체' 가 들어있는 지역은 선택할 수 없습니다. 상세지역을 선택해주세요.");
		  	      							break;
		  	      						}
		  	      						
		  	      						// 위에서 뽑아온 지역명을 이용해서 다시한번 해당 지역명이 가지는 코드를 select해오고 지역코드를 address_code에 저장.
		  	      						address_code = mdao.search_address_code(change_address);
		  	      						
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
		  	      			
		  	      			// select 해온 값을 담은 address_list를 하나하나 뽑아서 앞에 번호를 붙여주기위한 list생성. 
		  	      			
		  	      			
		  	      		} while(true); // end of do_while ----------
	  	            	  
	  	            	  
		  	      		
		  	      		
	  	            	  // == 모집전형 수정 == // 
	  	            	System.out.println("======== 모집전형 목록 ========");
	  	            	  List<String> recruit_step_list = cdao.view_all_recruit_step_list(); // 모든 모집전형 목록을 list에 담아주는 메소드 생성.
	  	            	  
	  	            	  List<String> no_recruit_step_list = new ArrayList<>();
	  	            	  for(int i = 0; i < recruit_step_list.size(); i++) {
	  	            		  String s = i+1+". "+recruit_step_list.get(i);
	  	            		no_recruit_step_list.add(s);
	  	            		  System.out.println(no_recruit_step_list.get(i));
	  	            	  }
	  	            	  String recruit_step_code = "";
	  	            	outer:
	  	            	for(int i = 0;i < 5; i++) {
		  	      			  
	  	            		do {
	  	            			  System.out.println((i+1) + "차 전형 ===");
		  	            		  System.out.println("▶ 원하는 번호를 숫자로만 입력하세요 [예) 면접 > 1] : ");
			  	            	  String choice_no = sc.nextLine();
			  	            	  
			  	            	  if(choice_no.isBlank() && i == 0) {
			  	            			  map = cdao.get_recruit_step_code(map); // 맨 처음 엔터를 눌렀을 때 모집전형 코드를 가지고오는 메소드 생성
			  	            		  break outer;
			  	            	  }
			  	            	  else {
			  	            		  
			  	            		  
			  	            		  if(choice_no.isBlank()) {
			  	            			  break outer;
			  	            		  }
			  	            		  else {
			  	            			is_all_number = true;
				  	            		for(int j = 0;j < choice_no.length(); j++) {
					  	            		  if(!('0'<=choice_no.charAt(j) && choice_no.charAt(j) <= '9')) {
					  	            			  System.out.println("[경고] 반드시 숫자만 입력하세요.");
					  	            			  is_all_number = false;
					  	            			  break;
					  	            		  }
					  	            	  }
				  	            		String recruit_step_name = "";
				  	            		if(is_all_number) {
				  	            			boolean is_can_number = false;
				  	            			for(int k = 0;k<no_recruit_step_list.size();k++) {
				  	            				if(choice_no.equals(no_recruit_step_list.get(k).substring(0,no_recruit_step_list.get(k).indexOf(".")))) {
				  	            					recruit_step_name = no_recruit_step_list.get(k).substring(no_recruit_step_list.get(k).indexOf(".")+2);
				  	            					is_can_number = true;
				  	            					break;
				  	            				}
				  	            			}
				  	            			
				  	            			
				  	            			if(is_can_number) {
				  	            				recruit_step_code = cdao.get_recruit_step_code_2(recruit_step_name); // 입력한 전형명에 일치하는 코드를 반환.
				  	            				map.put("recruit_step_code_" + (i+1), recruit_step_code);
				  	            				break;
				  	            			}
				  	            			else {
				  	            				System.out.println("[경고] 목록에 없는 번호입니다.");
				  	            			}
				  	            		}
			  	            		  }
			  	            		  
			  	            		  
			  	            		
			  	            		
			  	            	  }
		  	            	  }while(true);
	  	            		
		  	      		  }
	  	            	  
		  	      		  
	  	            	
	  	            	
	  	            	  
	  	            	  
		  	      		/*
		            	  map.put("recruit_step_name_1", rs.getString("recruit_step_name_1"));
		            	  map.put("recruit_step_name_2", rs.getString("recruit_step_name_2"));
		            	  map.put("recruit_step_name_3", rs.getString("recruit_step_name_3"));
		            	  map.put("recruit_step_name_4", rs.getString("recruit_step_name_4"));
		            	  map.put("recruit_step_name_5", rs.getString("recruit_step_name_5"));
		            	  */
	  	            	  
	  	            	  
	  	            	  
		  	            	/*  
		  	            	change_recruit_notice_name // 공고명
		  	            	change_yearsal  // 연봉
		            		job_type_code // 직무
		            		change_quail // 자격요건
		            		change_woodae // 우대사항
		            		change_benefit // 복지
		            		*/  
	            		  
	            		  
	            		  map.put("change_recruit_notice_name", change_recruit_notice_name);
	            		  map.put("change_yearsal", change_yearsal);
	            		  map.put("job_type_code", job_type_code);
	            		  map.put("change_quail", change_quail);
	            		  map.put("change_woodae", change_woodae);
	            		  map.put("change_benefit", change_benefit);
	            		  map.put("position_code", position_code);
	            		  map.put("worktype_code", worktype_code);
	            		  map.put("address_code", address_code);
	            		  
	            		  
	            		  int num = cdao.change_recruit_notice(change_recruit_notice_no,map); // 위에 담긴 map값으로 모집공고를 업데이트 해주는 메소드 생성.
	            		  
	            		  if( num == 1) {
	            			  System.out.println("[안내] 성공적으로 수정되었습니다.");
	            		  }
	            		  else if (num == 0) {
	            			  System.out.println("[안내] 수정을 취소하였습니다.");
	            		  }
	            		  else {
	            			  System.out.println("[경고] 수정을 실패했습니다.");
	            		  }
	            		  
	            		  
	            		  break;
	            		  
	            	  }
	            	  else {
	            		  System.out.println("[경고] 해당 공고번호는 없는 공고입니다 다시한번 확인해주세요.");
	            	  }
					
				}
			} while(true);
		}
		
	}


	// *** 구인회사 회원가입을 할 수 있게 해주는 메소드 생성 *** //
	public void com_join(Scanner sc) {
         
         CompanyDTO cdto = new CompanyDTO();
         CompanyDAO cdao = new CompanyDAO_imple();
         
         
         System.out.println("\n[구인회사 회원가입]");
         
         
         // *** 아이디를 입력하는 메소드 *** //
         String com_id = "";
         System.out.println("\n[안내] 아이디는 사업자등록번호 숫자 10자리로 입력해주세요.");
         outer:
         do {
            System.out.print("▷ 아이디(사업자등록번호) : ");
            com_id = sc.nextLine();
            
            cdto.setPK_COMPANY_CODE(com_id);
            
            if(!(cdto.getPK_COMPANY_CODE()== null || cdto.getPK_COMPANY_CODE().isBlank())) {
               
               // 입력한 아이디가 이미 존재하는 아이디인지 확인하기 위한 cdao 메소드 생성
               int n = cdao.check_com_id(com_id);
               if( n == 1 ) {
                  System.out.println("<경고> 이미 존재하는 아이디 입니다. 새로운 아이디를 입력해주세요."+"\n");
                  continue outer;
               }
               else {
                  
               }
               break;
            }
            else {
            }
            
         } while(true); // end of do_while com_id--------------------------------------

         
         
         
         // *** 비밀번호 입력하는 메소드 *** //
         String com_passwd = "";
         System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
         do {
            System.out.print("▷ 비밀번호 : ");
            com_passwd = sc.nextLine();
            cdto.setCOMPANY_PASSWD(com_passwd); // 비밀번호 유효성 검사 
            if(!(cdto.getCOMPANY_PASSWD() == null || cdto.getCOMPANY_PASSWD().isBlank())) {
               break;
            }
            else {
            }
         }while(true); // end of do_while com_passwd--------------------------------------

   
   
         
         // *** 회사명 입력하는 메소드 *** //
         String com_name = "";
            do {
               System.out.println("\n[안내] 회사명은 한글, 영문, 숫자로 입력해주세요.");
               System.out.print("▷ 회사명: ");
               com_name = sc.nextLine();
               
               if(com_name.isEmpty() || !(Pattern.matches("^[0-9a-zA-Zㄱ-ㅎ가-힣]*$", com_name))) {
                  System.out.println("<경고> 회사명은 공백을 제외한 한글, 영문, 숫자만으로 입력해주세요.");
               }
               else {
                  break;
               }
            } while(true); // end of do_while com_name--------------------------------------

         
         
         
         //*** 이메일 입력하는 메소드 *** //
         String com_email = "";
            do {
               System.out.println("\n[안내] 이메일은 영문, 숫자로만 입력해주세요.");
               System.out.print("▷ 이메일: ");
               com_email = sc.nextLine();
               
               
               if(com_email.isEmpty() || !(Pattern.matches("^[0-9a-zA-Z]*$", com_email)) || com_email.length()>15 ) {
                  System.out.println("<경고> 이메일은 공백을 제외한 15글자 이내의 영문, 숫자로만 입력해 주세요.");
               }
               else {
                   outer:
            	   do {
            		// 이메일 입력했을 때 뒤에 @naver.com 어떤거 선택할건지 뜨게 하기
                       String domain_choice = "";
                       
                       System.out.println("\n▶ 입력하실 이메일 도메인을 선택하세요."); 
                       System.out.println("1.@naver.com\n"
                                    + "2.@gmail.com\n"
                                    + "3.@daum.net\n"
                                    + "4.@hanmail.net\n"
                                    + "5.@nate.com\n"
                                    + "6.@kakao.com\n");
                       
                       System.out.print("▶ 도메인 번호 선택 : " + domain_choice); 
                       domain_choice = sc.nextLine();
                       
                       
                       switch (domain_choice) {
                          case "1":
                             com_email += "@naver.com";
                             break outer;
                             
                          case "2":
                             com_email += "@gmail.com";
                             break outer;
                             
                          case "3":
                             com_email += "@daum.net";
                             break outer;
                             
                          case "4":
                             com_email += "@hanmail.net";
                             break outer;
                             
                          case "5":
                             com_email += "@nate.com";
                             break outer;
                             
                          case "6":
                             com_email += "@kakao.com";
                             break outer;
                          
                          default:
                             System.out.println("<경고> 도메인 번호는 1부터 6까지의 정수로 입력해주세요.");
                             break;
                       }// end of switch(domain_choice)
                       
            	   }while(true);
            	   
            	   break;
               }
               
            } while(true);// end of do_while com_email--------------------------------------
            
            
            
            
          // *** 전화번호 입력하는 메소드 *** //
          String com_tel = "";
          do {
               System.out.println("\n[안내] 전화번호는 '-'을 제외한 숫자로만 입력해주세요.");
               System.out.print("▷ 전화번호: ");
               com_tel = sc.nextLine();
               
               if( com_tel.isEmpty() || !(Pattern.matches("^[0-9]*$", com_tel)) || (com_tel.length() < 9 || com_tel.length() > 11)   ) {
                  System.out.println("<경고> 전화번호는 숫자로만 9~11자리를 입력해주세요.");
               
               }
               
               else {
            	   if(com_tel.length() == 9) {
 	                  System.out.println("▷ 입력된 전화번호 : " + com_tel.substring(0,2) + "-" + com_tel.substring(2,5) + "-" + com_tel.substring(5)); 
 	                  com_tel = com_tel.substring(0,2) + "-" + com_tel.substring(2,5) + "-" + com_tel.substring(5);
 	                  break;
 	               }
 	               else if(com_tel.length() == 10) {
 		                  System.out.println("▷ 입력된 전화번호 : " + com_tel.substring(0,2) + "-" + com_tel.substring(2,6) + "-" + com_tel.substring(6)); 
 		                  com_tel = com_tel.substring(0,2) + "-" + com_tel.substring(2,6) + "-" + com_tel.substring(6);
 		                  break;
 		               }
 	               else {
 		            	  System.out.println("▷ 입력된 전화번호 : " + com_tel.substring(0,3) + "-" + com_tel.substring(3,7) + "-" + com_tel.substring(7)); 
 		                  com_tel = com_tel.substring(0,3) + "-" + com_tel.substring(3,7) + "-" + com_tel.substring(7);
 		                  break;
 		               }
               }
               
            } while(true); // end of do_while com_tel--------------------------------------
            
            
            
            
        
           // *** 회사 매출액 입력하는 메소드 *** //
           String s_com_sales = "";
           do {
	            System.out.println("\n[안내] 매출액은 숫자로만 입력해주세요.");
	            System.out.print("▷ 매출액: ");
	            s_com_sales = sc.nextLine();
	            
	            if(s_com_sales.isEmpty() || !(Pattern.matches("^[0-9]*$", s_com_sales))) {
	            	System.out.println("<경고> 매출액은 숫자로만 입력하세요.");
	            }
	            else {
	            	long com_sales = Long.parseLong(s_com_sales);
	            	DecimalFormat df = new DecimalFormat("#,###");
	            	s_com_sales = df.format(com_sales);
	            	System.out.println("▷ 입력된 매출액 : " + s_com_sales + " 원");
	            	break;
	            }
			} while(true); //  end of do_while com_sales--------------------------------------
            
            
            
            
            
           // *** 사원수 입력하는 메소드 *** //
		   String s_com_employee_no = "";
		   do {
				System.out.println("\n[안내] 사원수는 숫자로만 입력해주세요.");
	            System.out.print("▷ 사원수: ");
	            s_com_employee_no = sc.nextLine();
	            
	            if(s_com_employee_no.isEmpty() || !(Pattern.matches("^[0-9]*$", s_com_employee_no))) {
	            	System.out.println("<경고> 사원수는 숫자로만 입력하세요.");
	            }
	            else {
	            	System.out.println("▷ 입력된 사원수 : " + s_com_employee_no + " 명");
	            	break;
	            }
            } while(true);
            
            
            
            
           // *** 대표자명 입력하는 메소드 *** //
           String com_boss_name = "";
           do {
	            System.out.println("\n[안내] 대표자명은 2~6글자 이내의 한글로만 입력해주세요");
	            System.out.print("▷ 대표자명: ");
	            com_boss_name = sc.nextLine();
	            
	            cdto.setBOSS_NAME(com_boss_name);
	            
	            if(cdto.getBOSS_NAME()!=null) {
	            	break;
	            }
	            else {
	            	
	            }
            } while(true);
            
            
            
            
            // *** 홈페이지 입력하는 메소드 *** //
            String com_homepage = "";
            do {
	            System.out.println("\n[안내] 홈페이지는 \"https://\" 와 \".com\" 을 모두 포함하여 입력해주세요.");
	            System.out.print("▷ 홈페이지: ");
	            com_homepage = sc.nextLine(); 
	            
	            
	            if(com_homepage.length() > 50) {
	 	           System.out.println("<경고> 홈페이지는 50글자 이내로 입력하세요. \n");
	 	        }
	            else if(!(      (com_homepage.contains("https://") && (com_homepage.contains(".com")))    )){
	            	System.out.println("<경고> 홈페이지는 https:// 와 .com 를 모두 포함하여 입력하세요. ");
		 	    }
		 	      else {
		 	    	  
		 	    	 if(!(com_homepage.substring(0,8).equals("https://") && com_homepage.substring(com_homepage.indexOf(".com")).equals(".com")) ) {
			            	System.out.println("[경고] 잘못된 주소입니다. 다시한번 확인하세요");
			            }
		 	    	 else {
		 	    		 break;
		 	    	 }
		 	    }
	            
	
	            
	            
	            
	            
            } while(true);
          
            
            
            // *** 설립일 입력하는 메소드 *** //
            String expireday = "";
            do {
               System.out.println("\n[안내] 설립일은 \"19990125\" 형태로 입력하세요.");
               System.out.print("▶ 설립일: ");
               expireday = sc.nextLine();

               // 현재 날짜
               Date now = new Date();
               
               SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");
               sdformat.setLenient(false);
               // false 로 해주어야만 입력한 값을 날짜 타입으로 변경할 때 날짜로 변경되지 못하는 값일 경우 오류가 발생한다.
               // 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다.

               Date d_expireday = null;

               if (expireday.isBlank()) {
                  System.out.println("<경고> 설립일은 필수 입력사항입니다. \n");

               } else {

                  try {
                     // === 문자열을 날짜 형태로 변환하기 ===
                     d_expireday = sdformat.parse(expireday); // 마감일자를 날짜 형태로 변환

                     if (!(d_expireday.before(now))) {
                        System.out.println("<경고> 설립일은 현재 날짜보다 이전 날짜로만 입력 가능합니다. \n");

                     } else {
                        break;
                     }

                  } catch (ParseException e) {
                     System.out.println("<경고> 달력에 존재하지 않는 값입니다. <<\n");
                     continue;
                  }
               }

            } while (true);
         
            
            
           
            // *** 업종을 입력하는 메소드 *** //
            System.out.println("\n[업종 번호 목록]");
            
            int i = 0;
            List<Map<String,String>> list_map = cdao.view_all_buss_type();
            
            List<String> list_map_no = new ArrayList<>();
            for(Map<String,String> map:list_map) {
           	 
         	    list_map_no.add(i, i+1 + "." + map.get("buss_type_name")); 
         	    System.out.println( list_map_no.get(i));
         	    i++;
            }
            
            
            String get_buss_name = null;
            outer:
            do {
           	 System.out.print("▶ 업종 번호 선택 : ");
                String choice_num = sc.nextLine();
                
           	 for(int j = 0;j < list_map_no.size(); j++) {
           		 if(!(list_map_no.get(j).substring(0,list_map_no.get(j).indexOf(".")).equals(choice_num))) {
           		 }
           		 else {
           			 get_buss_name = list_map_no.get(j).substring(list_map_no.get(j).indexOf(".")+1);
           			 break outer;
           		 }
           	 }
           	 if(get_buss_name == null) {
           		 System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
           	 }
            }while(true);
            
            String get_buss_type_code = cdao.search_buss_type_code(get_buss_name);
            
            System.out.println("▷ 선택한 업종명 : " + get_buss_name);
            
            
            
            
            //*** 회사 규모 입력 *** //
            System.out.println("\n[회사 규모 목록]");
            i = 0;
            List<Map<String,String>> list_map_2 = cdao.view_all_company_size();
            
            
            List<String> list_map_no_2 = new ArrayList<>();
            for(Map<String,String> map:list_map_2) {
               
                list_map_no_2.add(i, i+1 + "." + map.get("com_size")); 
                System.out.println( list_map_no_2.get(i));
                i++;
            }
            
            String get_com_size_name = null;
            outer:
            do {
               System.out.print("▶ 회사 규모 번호 선택 : ");
                String choice_num = sc.nextLine();
                
               for(int j = 0; j < list_map_no_2.size(); j++) { // 입력한 숫자와 이름과 숫자를 붙여준것에서 숫자가 같으면
                  if(!(list_map_no_2.get(j).substring(0,list_map_no_2.get(j).indexOf(".")).equals(choice_num))) {
                     
                  }
                  else {
                     get_com_size_name = list_map_no_2.get(j).substring(list_map_no_2.get(j).indexOf(".")+1);
                     break outer;
                  }
               }
               if(get_com_size_name == null) {
                  System.out.println("<경고> 메뉴에 없는 번호입니다. 목록에 있는 번호로 선택해주세요.");
               }
            }while(true);
            
           
            String get_com_size_code = cdao.search_PK_COM_SIZE_CODE(get_com_size_name);
            
            System.out.println("▷ 선택한 회사 규모 : " + get_com_size_name);
          
            
            // *** 회사주소를 입력하는 메소드 *** //
            String com_address = "";
            do {
            	System.out.println("[안내] 주소는 공백을 제외한 한글 및 숫자로만 입력해주세요");
	            System.out.print("\n▶ 회사 주소 : ");
	            com_address = sc.nextLine();
	            
	            if(com_address.length()==0) {
	            	System.out.println("[경고] 주소는 반드시 입력해야 합니다.");
	            }
	            else {
	            	if(Pattern.matches("^[ㄱ-ㅎ가-힣0-9' ']*$", com_address)) {
	            		break;
	            	}
	            	else {
	            		System.out.println("<경고> 주소는 공백을 제외한 한글 및 숫자로만 입력해주세요.");
	            	}
	            }
	            
	            
            } while(true);
            
            
            
            
            
            
            
			// 회원가입정보 입력 완료 후 입력사항 띄우기 (주민번호, 전화번호에 "-" 넣어서)
			System.out.println("\n[회원가입 정보]");
			System.out.print("▷ 아이디(사업자등록번호) : " + com_id +"\n"
			               + "▷ 비밀번호 : " + com_passwd +"\n"
			               + "▷ 회사명 : " + com_name +"\n"
			               + "▷ 이메일 : " + com_email +"\n"
			               + "▷ 전화번호 : " + com_tel +"\n"
			               + "▷ 대표자명 : " + com_boss_name +"\n"
			               + "▷ 매출액 : " + s_com_sales + " 원" + "\n"
			               + "▷ 사원수 : " + s_com_employee_no + " 명" +"\n"
			               + "▷ 홈페이지 : " + com_homepage +"\n"
			               + "▷ 설립일 : " + expireday.substring(0,4) + "-" + expireday.substring(4,6) + "-" + expireday.substring(6) + "\n"
			             
			               + "▷ 주소 : "  + com_address +"\n"
			               + "▷ 업종명 : "  + get_buss_name + "\n"
			               + "▷ 회사규모 : " + get_com_size_name +"\n");
            
         
            
            
            // 위에서 얻어온 정보들을 Map에 저장후 회원가입(insert)을 하게해주는 join_company 메소드 생성 
            Map<String,String> paraMap = new HashMap<>();
            
            paraMap.put("com_id",cdto.getPK_COMPANY_CODE() );
            paraMap.put("com_passwd",cdto.getCOMPANY_PASSWD() );
            paraMap.put("com_name", com_name);
            paraMap.put("com_email", com_email);
            paraMap.put("com_tel", com_tel);
            paraMap.put("com_sales", s_com_sales);
            paraMap.put("com_employee_no", s_com_employee_no);
            paraMap.put("com_homepage", com_homepage);
            paraMap.put("com_establish", expireday); 
            paraMap.put("com_boss_name", cdto.getBOSS_NAME());
            paraMap.put("get_buss_type_code", get_buss_type_code);
            paraMap.put("get_com_size_code", get_com_size_code);
            paraMap.put("com_address", com_address);
            
            
            
            
            // 리턴값으로 회원가입 유무를 알 수 있게 int로 지정.
            int n = cdao.join_company(paraMap);
            
            
            if(n == 1) {
               System.out.println(">> 축하드립니다 회원가입에 성공하셨습니다. <<");
            }
            else if(n == 0) {
               System.out.println(">> 회원가입을 취소하셨습니다. <<");
            }
            else {
               System.out.println(">> 회원가입을 실패했습니다. <<");
            }
        
      
      }// end of public void com_join(Scanner sc)
	
	
	
	
}
