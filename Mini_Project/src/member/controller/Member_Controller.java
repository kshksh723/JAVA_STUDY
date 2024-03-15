package member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import member.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class Member_Controller {

	public MemberDTO mem_login(MemberDTO mdto, Scanner sc) {
	
		 MemberDTO member = null;
		 MemberDAO mdao = new MemberDAO_imple();
	      
	      System.out.println("\n >>> --- 로그인 --- <<<");
	      
	      System.out.print("▷ 아이디 : ");
	      String userid = sc.nextLine();
	      System.out.print("▷ 비밀번호 : ");
	      String passwd = sc.nextLine();
	      
	      // mdao.login(userid, passwd);   가능하지만 spring 은 놉! => map 활용하기
	      Map<String, String> paraMap = new HashMap<>();      // key : string / values : string
	      paraMap.put("pk_user_id", userid);
	      paraMap.put("pass_wd", passwd);
	      
	      member = mdao.login(paraMap);
	      
	      if(member != null)
	         System.out.println("\n >>> 로그인 성공!! <<< \n");
	      	System.out.println("-------------------------------------구직자 메뉴 [정우석 로그인중]-----------------------------------\r\n"
	      			+ "        1.구인회사 검색    2.모집공고 검색      3.개인정보 조회     4.이력서 조회      5.로그아웃\r\n"
	      			+ "------------------------------------------------------------------------------------------------------------\r\n"
	      			+ "▶ 메뉴번호 선택 :  1\r\n"
	      			+ "  ");  
	         System.out.println(">> 아이디 혹은 비밀번호가 일치하지 않습니다!! <<");

	      
	      
	      return member;
	   }   // end of private MemberDTO login(Scanner sc)---------------------
	   
}


