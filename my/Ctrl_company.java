package my.day19.a.collection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Ctrl_company {
	
	// == 구인회사(Company) 신규 회원가입시
		//    Company 클래스의 field 의 요구사항에 모두 맞으면
		//    List<Company> cp_list와 Map<String, Gujikja> gu_map 에 저장시켜주는 메소드 생성하기 ==
		public void register(Scanner sc, List<Company_imple> cp_list, Map<String, Company_imple> gu_map);
		Company_imple login(sc, cp_map);
	// == 구인회사 전용메뉴 ==
	public void cp_menu(Scanner sc, Map<String, Company_imple> gu_list, CommonMember[] cmbr_arr, Recruit[] rc_arr, RecruitApply[] rcApply_arr);
}
