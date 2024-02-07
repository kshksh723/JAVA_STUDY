package my.day16.d.INTERFACE_inheritance;

import java.util.Scanner;

public interface Ctrl_company {
	
	// == 구인회사(Company) 신규 회원가입시
		//    Company 클래스의 field 의 요구사항에 모두 맞으면
		//    CommonMember[] cmbr_arr 에 저장시켜주는 메소드 생성하기 ==
		public void register(Scanner sc, CommonMember[] cmbr_arr);
		
	// == 구인회사 전용메뉴 ==
	public void cp_menu(Scanner sc, Company_imple login_cp, CommonMember[] cmbr_arr, Recruit[] rc_arr, RecruitApply[] rcApply_arr);
}
