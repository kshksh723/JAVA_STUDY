package my.day19.a.collection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Ctrl_gujikja {
	// == 구직자(Gujikja) 신규 회원가입시
		//    Gujikja 클래스의 field 의 요구사항에 모두 맞으면
		//   List<Gujikja_imple> gu_list와  Map<String, Gujikja> gu_map 에 저장시켜주는 메소드 생성하기 ==
		void register(Scanner sc, List<Gujikja_imple> gu_list, Map<String, Gujikja> gu_map);
		
		// 구직자 로그인
		Gujikja_imple login (Scanner sc, Map<String, Gujikja_imple> gu_map);
		
		
		// == 구직자 전용메뉴 메소드 생성하기 == //
		public void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Gujikja_imple> cp_list, Recruit[] rc_arr, List<Gujikja_imple> rcApply_arr);
}
