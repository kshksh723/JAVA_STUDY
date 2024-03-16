package member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import company.domain.CompanyDTO;
import company.model.*;
import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Member_Controller {

	public void mem_login(Scanner sc) {
	
		 
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
	      
	      
	      
	      if(!(member.getPK_USER_ID()==null)) {
	    	  System.out.println(member.getUSER_NAME()+"님이 로그인 하셨습니다");
	    	  isLogin = true;
	    	  
	    	  
	      }
	      else {
	    	  System.out.println("[경고] 아이디 혹은 비밀번호가 일치하지 않습니다.");
	      }
	      
	      
	/////////////////////////////////////////////////////////////////
	    String s_choice = ""; 
	    do {  
		if(isLogin == true) {
	    	  System.out.println("---------------------------------------구직자 메뉴 [" + member.getUSER_NAME() +"님 로그인중]--------------------------------\r\n"
	    	  						+ "        1.구인회사 검색    2.모집공고 검색      3.개인정보 조회     4.이력서 조회      5.로그아웃\r\n"
	    	  						+ "------------------------------------------------------------------------------------------------------------\r\n"
	    	  						);
	    	  
	    	  System.out.print("▷ 메뉴번호 선택 : ");
              
	    	  s_choice = sc.next();
	    	  
	    	  switch (s_choice) {
              case "1":   // 구인회사 검색
                search_com(member,sc);
                
            	  
            	  
                  break;
               case "2": // 모집공고검색
                  
            	  
            	   
            	   break;
               case "3":   // 개인정보조회
               	
               	
           
               	break;
                
               case "4":	// 이력서 조회
               
               	
               	break;
               	
               case "5":	// 로그아웃
                   System.out.println(">>> 로그아웃 되었습니다. <<<\n");
                   break;
                  
               
               default:
            	   
               	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
               	break;                 
               }   // end of switch (s_Choice)--------------------
            }   // end of if(isLogin == true)----------------------------
         
		
	} while(!"5".equals(s_choice));   // return 프로그램 종료일 때만 나갈 수 있게 하기
}


	
	private void search_com(MemberDTO member, Scanner sc) {
		 String s_choice = null;
do {
	System.out.println("-------------------구인회사 검색 [" + member.getUSER_NAME()  + "님 로그인중]------------\r\n"
						+ "  1. 기업명 검색    2. 매출액 검색    3. 업종검색    4. 이전메뉴로 \r\n"
						+ "--------------------------------------------------------------------------\r\n"
						);
	
	
	System.out.print("▶ 메뉴번호 선택 :");
	 
	
	 s_choice = sc.next(); 
	 CompanyDAO cdao = new CompanyDAO_imple();
     switch (s_choice) {
     case "1":   // 기업명 검색 
   	  
    	 System.out.print("▷ 검색어를 입력하세요 : " + sc.nextLine());
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("COMPANY_NAME", sc.nextLine() );
		
		CompanyDTO cd = cdao.search_comname(paraMap );
	
		System.out.println("--------------------------------------------------------------"+ cd.getCOMPANY_NAME() +" 기업정보 ----------------------------------------------------------------------\r\n"
				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			대표이름\r\n"
				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
				);
		System.out.print(cd.getCOMPANY_NAME() 
				+ '\t' + cd.getSALES() 
				+ '\t' + cd.getNUMBER_OF_EMPLOYEE() 
				+ '\t' + cd.getHOMEPAGE()
				+ '\t' + cd.getBUSS_TYPE_NAME()
				+ "\t\t" + cd.getCOMPANY_ADDRESS()
				+ "\t\t" + cd.getCOM_SIZE() 
				+ "\t\t" + cd.getBOSS_NAME()
				+ "\n\n");

		
		
		
		
		
        
      case "2": // 매출액 검색
         
   	  
   	   
   	   break;
      case "3":   // 업종검색
    	  System.out.print("▷ 검색어를 입력하세요 : " + sc.nextLine());
  		Map<String, String> paraMap1 = new HashMap<>();
  		paraMap1.put("SEARCH_BUSS", sc.nextLine() );
  		
  		CompanyDTO cd1 = cdao.search_buss_type(paraMap1 );
  	
  		System.out.println("--------------------------------------------------------------"+ cd1.getCOMPANY_NAME() +" 기업정보 ----------------------------------------------------------------------\r\n"
  				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			대표이름\r\n"
  				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
  				);
  		System.out.print(cd1.getCOMPANY_NAME() 
  				+ '\t' + cd1.getSALES() 
  				+ '\t' + cd1.getNUMBER_OF_EMPLOYEE() 
  				+ '\t' + cd1.getHOMEPAGE()
  				+ '\t' + cd1.getBUSS_TYPE_NAME()
  				+ "\t\t" + cd1.getCOMPANY_ADDRESS()
  				+ "\t\t" + cd1.getCOM_SIZE() 
  				+ "\t\t" + cd1.getBOSS_NAME()
  				+ "\n\n");

  
      	break;
       
      case "4":	// 이전 메뉴로 
      
      	
      	break;
        
      default:
   	   
       	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
      	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
      	break; 
      
     } //END OF  switch (s_choice) 
     } while(!"4".equals(s_choice));
	}

} 
	
      	
     
	


	
	
	
	
	
	
	
	
	
////////////////////////////////////////

	
		
		
 
              
	      
	     

	      

	   



