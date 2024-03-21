package company.model;

import java.util.List;
import java.util.Map;

import company.domain.CompanyDTO;

public interface CompanyDAO {

	CompanyDTO login(Map<String, String> paraMap); // 구인회사가 입력한 아이디, 비밀번호에 일치하는 정보가 있는지 확인하기위한 select 메소드

	String find_com_id(Map<String, String> find_com_id); // 이메일과 비밀번호가 일치한다면 사업자등록번호를 보여주기.

	int find_com_passwd(Map<String, String> find_com_passwd); // 입력한 정보가 맞으면 비밀번호를 변경할 수 있게 해주는 메소드 생성.

	// *** 구인회사 회원가입 메소드 생성 *** //
	int join_company(Map<String, String> paraMap);
	  
	// *** 아이디 중복 검사 메소드 *** //
	int check_com_id(String com_id);
	

	// *** 모든 업종을 보여주는 메소드 *** // 
	List<Map<String, String>> view_all_buss_type();

	// *** 검색한 번호를 토대로 업종 코드를 알려주는 메소드 생성 (select) *** //
	String search_buss_type_code(String get_buss_name);

	
    // *** 모든 회사 규모 목록 보여주는 메소드 *** // 
    List<Map<String, String>> view_all_company_size();
    
    // *** 검색한 번호를 토대로 업종 코드를 알려주는 메소드 생성(select) *** //
    String search_PK_COM_SIZE_CODE(String get_com_size_name);

	int view_my_recruit_notice(CompanyDTO company); // 내가 등록한 공고를 다 보여주는 메소드 생성

	Map<String, String> view_one_recruit_notice(CompanyDTO company, String change_recruit_notice_no); // 입력한 공고번호에 일치하는 공고 상세정보 출력하기

	List<String> view_all_job_type_list(); // 모든 직무 목록을 list에 담아주는 메소드 생성.

	String get_job_type_code(String string); // 입력한 직무명과 일치하는 코드를 반환.

	List<String> view_all_position_list(); // 모든 직책 목록을 list에 담아주는 메소드 생성.

	String get_position_code(String string); // 입력한 직책명과 일치하는 코드를 반환.

	List<String> view_all_worktype_list(); // 모든 채용형태 목록을 list에 담아주는 메소드 생성.

	String get_worktype_code(String string); // 입력한 채용형태명과 일치하는 코드를 반환.

	List<String> view_all_recruit_step_list(); // 모든 모집전형 목록을 list에 담아주는 메소드 생성.

	Map<String, String> get_recruit_step_code(Map<String, String> map); // 맨 처음 엔터를 눌렀을 때 모집전형 코드를 가지고오는 메소드 생성

	String get_recruit_step_code_2(String recruit_step_name); // 입력한 전형명에 일치하는 코드를 반환.

	int change_recruit_notice(String change_recruit_notice_no, Map<String, String> map); // 위에 담긴 map값으로 모집공고를 업데이트 해주는 메소드 생성.

	int delete_recruit_notice(CompanyDTO company, String delete_recruit_no); //선택한 모집공고를 삭제하는 메소드 생성
 
	Map<String, String> view_my_company_info(CompanyDTO company, String passwd); // 내 회사의 상세정보를 보여주는 메소드 생성

	List<Map<String, String>> view_position(); // 직위 목록 모두 보여주는 메소드

	String search_position_code(String get_po_name); // 검색한 번호를 가지고 직위 코드를 알려주는 메소드

	List<Map<String, String>> view_work_type(); // 근무형태 목록 모두 보여주는 메소드

	String search_worktype_code(String get_worktype_name); //  검색한 번호를 가지고 직위 코드 알려주는 메소드

	List<Map<String, String>> view_job_type(); // 직무 목록 모두 보여주는 메소드

	String search_jobtype_code(String get_jobtype_name); // 검색한 번호를 가지고 직무 코드 알려주는 메소드

	int join_recruit(Map<String, String> paraMap); // 구인회사 모집공고 등록하는 메소드(insert)

	List<String> search_address(String address); // 검색한 지역의 코드를 알려주는 메소드

	String search_address_code(String address); // 검색한 지역의 코드를 알려주는 메소드 생성

	List<Map<String, String>> view_recruit_step(); // 모집 전형 목록 모두 보여주는 메소드

	String search_re_step(String get_re_step_name); // 검색한 번호를 가지고 모집 전형 코드 알려주는 메소드

	List<Map<String, String>> SEARCH_DP(Map<String, String> paraMap);// 구직자 학과 찾기

	List<Map<String, String>> SEARCH_AGE(Map<String, String> paraMap);

	List<String> view_all_buss_type_list(); // 모든 업종 목록을 list에 담아주는 메소드 생성.

	String get_buss_type_code(String string); // 입력한 업종명과 일치하는 코드를 반환.

	List<String> view_all_company_size_list(); // 모든 기업규모 목록을 list에 담아주는 메소드 생성.

	String get_company_size_code(String string); // 입력한 업종명과 일치하는 코드를 반환.

	int change_company_detail(CompanyDTO company, Map<String, String> paraMap); // 저장한 값을 토대로 회사 상세 정보를 변경해주는 메소드 생성
	
	
	
	
}
