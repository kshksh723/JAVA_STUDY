//package my.day13.a.inheritance;
//
//public class Ctrl_company extends Ctrl_Common_ {
//
//	// 구인회사 (company) 신규 회원가입시
//	// company 클래스의 field의 요구사항에 모두 맞으면
//	// company[] cp_arr에 저장시켜주는 메소드 생성하기 ==
//	public static void main(String[] args) {
//		if(Company.count < cp_arr.lengt) {
//			Company cp = new Company
//					
//					
//		do {
//			System.out.println("2. 비밀번호 : ");
//			String passwd = sc.nextLine();
//			cp.setPasswd(passwd);
//		}while(!(cp.getName() != null ));
//					
//			
//		do {
//			System.out.println("4. 사업자 등록번호 : ");
//			String business_number = sc.nextLine();
//			
//			cp.setBusiness_number(business_number);
//		} while(!cp.getBusiness_number() != null)); // 값을 안넣어주면 default가 null값
//
//		/////////////////////////////////////////////////
//			cp_arr[Company.count++]=cp;
//		System.out.println(">>구인회사 회원가입 성공 !! << \n");
//		
//		else {
//			System.out.println(">> 정원 "+ cp_arr.length + "개가 꽉차서 구인회사 회원가입이 ");
//		}
//		
//		
//	}
//
//
package my.day13.a.inheritance;

import java.util.Scanner;

public class Ctrl_company extends Ctrl_common {

	// == 구인회사(Company) 신규 회원가입시
	//    Company 클래스의 field 의 요구사항에 모두 맞으면
	//    Company[] cp_arr 에 저장시켜주는 메소드 생성하기 ==
	public void register(Scanner sc, Company[] cp_arr) {
		
		if(Company.count < cp_arr.length) { // 지금까지 생성된 구인회사 회원수가 cp_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
		    
			Company cp = new Company();
			
			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
			outer:
			do {
				System.out.print("1.아이디 : ");
				String id = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				for(int i=0; i<Company.count; i++) {
					
					if( id.equals(cp_arr[i].getId()) ) {
						System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
						continue outer;
					}
					
				}// end of for-------------------
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
			
			cp_arr[Company.count++] = cp;
			
			System.out.println(">> 구인회사 회원가입 성공 !! <<\n");
			
		}
		else { // 지금까지 생성된 구인회사 회원수가 cp_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
			System.out.println(">> 정원 "+ cp_arr.length + "개가 꽉차서 구인회사 회원가입이 불가합니다.!! <<\n");
		}		
		
	}// end of public void register(Scanner sc, Company[] cp_arr)-----------

	// 구인회사 로그인
	public Company login(Scanner sc, Company[] cp_arr) {
		System.out.print("> 구인회사 ID : ");
		String id = sc.nextLine();
		
		System.out.print("> 비밀번호 : ");
		String passwd = sc.nextLine();
		
		for(int i = 0; i<Company.count; i++) {
			if(id.equals(cp_arr[i].getId()) 
			&& passwd.equals(cp_arr[i].getPasswd())) {
				return cp_arr[i]; // 메소드  종료
			}
		}// end fo for
			return null;
	
	}// end of	public Company login(Scanner sc, Company[] cp_arr)-------
	// == 우리회사정보 수정 ==
	   private void update_myInfo(Scanner sc, Company login_cp) {
	      
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
}
