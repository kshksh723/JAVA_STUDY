package my.day16.d.INTERFACE_inheritance;

import java.util.Scanner;

public interface Ctrl_gujikja {
	// == 구직자(Gujikja) 신규 회원가입시
		//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
		//    CommonMember[] cmbr_arr 에 저장시켜주는 메소드 생성하기 ==
		void register(Scanner sc, CommonMember[] cmbr_arr);
		
		
		// == 구직자 전용메뉴 메소드 생성하기 == //
		public void gu_menu(Scanner sc, Gujikja_imple login_gu, CommonMember[] cmbr_arr, Recruit[] rc_arr, RecruitApply[] rcApply_arr);
}
