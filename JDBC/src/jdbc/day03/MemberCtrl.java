package jdbc.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MemberCtrl {

   // field, attribute, property, 기능
   MemberDAO mdao = new MemberDAO_imple();   
   // 회원가입기능만 사용할 것이므로 클래스가 아닌 인터페이스만 하면 된다. => 메소드만 필요하지 세부 내용을 보여줄 필요는 없다.
   // 다형성으로 MemberDAO_imple mdao 대신 MemberDAO mdao 가능
   
   
   // method, operation, 기능
   
   // ◆◆◆◆◆◆◆◆◆ **** 시작메뉴를 보여주는 메소드(기능) **** ◆◆◆◆◆◆◆◆◆ //
   public void menu_start(Scanner sc) {
      
      String s_Choice = "";
      boolean isLogin = false;
      MemberDTO member = null;   // 지역변수이므로 반드시 초기화!!(노란색 member)
      
      do {
         if(isLogin == false) {   // 로그인 하기 전 or 로그인 실패한 경우
            do {
               /////////////////////////////////////////////////////////////////
               System.out.println("\n >>> ---- 시작메뉴 ---- <<<\n"
                     + "1. 회원가입   2. 로그인      3. 프로그램종료 \n"
                     + "-------------------------------------------\n");
               
               System.out.print("▷ 메뉴번호 선택 : ");
               s_Choice = sc.nextLine();
               
               switch (s_Choice) {
               case "1":   // 회원가입
                  memberRegister(sc);
                  break;
               case "2":   // 로그인
                  member = login(sc);   // 로그인 시도하기
                  if(member != null)    // 로그인 성공
                     isLogin = true;
                  break;
               case "3":   // 프로그램 종료
            	   
                  // menu_start(Scanner sc) 메소드 종료시키기
                  return;
               default:
                  System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!! <<<");
                  break;
               }   // end of switch (s_Choice)--------------
               ////////////////////////////////////////////////////////////////////
            } while(!("2".equals(s_Choice) && isLogin == true ));   // 2번이면서 Login 이 성공한 경우
         } // end of if(isLogin == false)------------------------
         
         // 로그인을 한 후 
         if(isLogin == true) {
        	 
        	 String admin_menu = "admin".equals(member.getUserid())?"4.모든회원조회":"";
        	 
            System.out.println("\n >>> ---- 시작메뉴 [ "+ member.getName() + "님 로그인중..]---- <<<\n"
                  + "1. 로그아웃   2. 회원탈퇴하기      3. 나의정보보기 "+  admin_menu + " \n"
                  + "-------------------------------------------------------------\n");
            
            System.out.print("▷ 메뉴번호 선택 : ");
            s_Choice = sc.nextLine();
            
            switch (s_Choice) {
            case "1":   // 로그아웃
               member = null;
               isLogin = false;
               System.out.println(">>> 로그아웃 되었습니다. <<<\n");
               break;
            case "2": // 회원탈퇴
                // auto  commit
           	 String yn = "";
            	do {
            	 System.out.println("> 정말로 탈퇴하시겠습니까?[Y/N] : ");
            	 	yn = sc.nextLine();
            	 
            	if("y".equalsIgnoreCase(yn)) { //equalsIgnoreCase 대소문자 구분
                 int n = mdao.memberDelete(member.getUserseq());
                if(n==1) {
                   member = null;      // 로그아웃 처리
                   isLogin = false;   // 로그아웃 처리
                   System.out.println(">>> 회원탈퇴가 성공했습니다. <<<");
                }
            	}
            	else if("n".equalsIgnoreCase(yn)) {
            		System.out.println("회원탈퇴를 취소하셨습니다<< \n" );
            	}
            	else {
            		System.out.println(">>  y 또는 n만 입력하세요 << \n");
            	} // end of switch 
            }while(! "y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));
            	
                
                break;
            case "3":   // 나의정보보기
            	
            	
          //  System.out.println(member.toString());
           System.out.println(member);
            	break;
             
            case "4":	// admin으로 로그인시 모든 회원 조회, 일반 회원으로 로그인 시 메뉴에 없는 번호로 표시  
            	if( "admin".equals(member.getUserid())) {
            		showAllMember(); 
            	
            	}
            	else {
            		System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
            	}
            	
            	break;
               
            default:
            	System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<");
               break;
            }   // end of switch (s_Choice)--------------------
         }   // end of if(isLogin == true)----------------------------
      }while(true);   // return 프로그램 종료일 때만 나갈 수 있게 하기
      
   }   // end of public void menu_start(Scanner sc)--------------------

   
   





// ◆◆◆◆◆◆◆◆◆ *** 회원가입을 해주는 메소드 ***  ◆◆◆◆◆◆◆◆◆ //
   private void memberRegister(Scanner sc) {
      
      System.out.println("\n >>> ---- 회원가입 ---- <<<");
      System.out.print("1. 아이디 : ");
      String userid = sc.nextLine();
      
      System.out.print("2. 비밀번호 : ");
      String passwd = sc.nextLine();
      
      System.out.print("3. 회원명 : ");
      String name = sc.nextLine();
      
      System.out.print("4. 연락처(휴대폰) : ");
      String mobile = sc.nextLine();
      
      MemberDTO member = new MemberDTO();      // Member 클래스
      member.setUserid(userid);
      member.setPasswd(passwd);
      member.setName(name);
      member.setMobile(mobile);
      
      int n = mdao.memberRegister(member);   // 메소드 호출
      
      if(n == 1)
         System.out.println("\n>>> 회원가입을 축하드립니다. <<<");
      else
         System.out.println(">>> 회원 가입이 실패되었습니다. <<<");
   }   // end of private void memberRegister(Scanner sc)----------------
   
   

   // ◆◆◆◆◆◆◆◆◆ *** 로그인을 해주는 메소드 ***  ◆◆◆◆◆◆◆◆◆ //
   private MemberDTO login(Scanner sc) {
      
      MemberDTO member = null;
      
      System.out.println("\n >>> --- 로그인 --- <<<");
      
      System.out.print("▷ 아이디 : ");
      String userid = sc.nextLine();
      System.out.print("▷ 비밀번호 : ");
      String passwd = sc.nextLine();
      
      // mdao.login(userid, passwd);   가능하지만 spring 은 놉! => map 활용하기
      Map<String, String> paraMap = new HashMap<>();      // key : string / values : string
      paraMap.put("userid", userid);
      paraMap.put("passwd", passwd);
      
      member = mdao.login(paraMap);
      
      if(member != null)
         System.out.println("\n >>> 로그인 성공!! <<< \n");
      else
         System.out.println("\n >>> 로그인 실패ㅠㅠ <<< \n");
      
      return member;
   }   // end of private MemberDTO login(Scanner sc)---------------------
   
// ◆◆◆◆◆◆◆◆◆ *** 모든 회원을 조회해주는 메소드 ***  ◆◆◆◆◆◆◆◆◆ //
   private void showAllMember() {
	
	   List<MemberDTO> memberList = mdao.showAllMember();
	   
	   if(memberList.size() > 0) {
		   
	   }
	   else {
		   System.out.println(">> 가입된 회원이 1명도 없습니다 ㅜㅜ  <<");
	   }
	
   } // end of private void showAllMember()

}
