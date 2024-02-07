package my.day16.d.INTERFACE_inheritance;

import java.util.Scanner;

public interface Ctrl_common {

	// == 시작화면(메인 메뉴)를 보여주는 메소드 생성하기 == //
	void main_menu();
	
	// == 구직자 및 구인회사 로그인 메소드 생성하기 == //
	CommonMember login(Scanner sc, CommonMember[] cmbr_arr, int type);
	
}