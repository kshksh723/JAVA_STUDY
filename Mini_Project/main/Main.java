package main;

import java.util.Scanner;

import company.controller.Company_Controller;
import dbconnection.MyDBConnection;
import member.controller.Member_Controller;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String main_menu_no = "";
		
		Company_Controller com_ctrl = new Company_Controller();
		Member_Controller mem_ctrl = new Member_Controller();
		
		do {
			System.out.println("");
			System.out.println("--------------------------------------------[ 메인 메뉴 ]---------------------------------------------\n"
							 + "        1. 구직자 로그인    2. 구직자 회원가입    3. 구인회사 로그인    4. 구인회사 회원가입    5.프로그램종료    \n"
							 + "----------------------------------------------------------------------------------------------------");
			
			System.out.print("▶ 메뉴 번호 선택 : ");
			main_menu_no = sc.nextLine();
			
			switch (main_menu_no) {
			case "1":
				mem_ctrl.mem_login(sc);
				break;
			case "2":
				mem_ctrl.mem_join(sc);
				break;
			case "3":
				com_ctrl.com_login(sc);
				break;
			case "4":
				com_ctrl.com_join(sc);
				break;
			case "5":
				MyDBConnection.closeConnection();
				break;
	
			default:
				System.out.println("[경고] 메뉴에 존재하지 않는 번호입니다.");
				break;
			}
			
			
		} while(!"5".equals(main_menu_no)); // end of do_while()-----------------------
		
		sc.close();
		System.out.println(">> 프로그램 종료 <<");
		
	
	} // end of main() -------

}
