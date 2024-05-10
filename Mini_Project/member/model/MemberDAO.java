<<<<<<< HEAD
package member.model;

import java.util.List;
import java.util.Map;

import member.domain.MemberDTO;


public interface MemberDAO {

	MemberDTO login(Map<String, String> paraMap); // 로그인 처리를 하게 해주는 메소드
	
	int check_userid(String userid); // 내가 회원가입 할 아이디가 이미 있는 아이디인지 검사하기

	List<String> search_address(String address); // 검색한 지역들을 확인해주는 메소드 생성

	String search_address_code(String address); // 검색한 지역의 코드를 알려주는 메소드 생성

	int join_member(Map<String, String> paraMap); // 입력한 정보를 바탕으로 회원가입을 하게 해주는 메소드 생성

	String find_user_id(Map<String, String> find_id_map); // 주민등록번호와 비밀번호가 일치한다면 아이디를 찾아주는 메소드 생성

	int find_user_passwd(Map<String, String> find_passwd_map); // 주민등록번호와 아이디가 일치한다면 비밀번호를 변경해주는 메소드 생성

	List<Map<String, String>> search_comname(Map<String, String> paraMap); // 회사명 찾기

	List<Map<String, String>> search_buss_type(Map<String, String> paraMap1); // 업종검색

	MemberDTO view_member_info(Map<String, String> paraMap); // 회원 정보를 보여주는 메소드 생성.

	int change_member_info(Map<String, String> paraMap); // 위의 변경한 값을 토대로 업데이트를 진행해주는 메소드 생성

	String getemail(Map<String, String> paraMap); // 아이디, 비밀번호를 입력하면 가지고있던 email을 리턴시켜주는 메소드 생성 (애초에 코드를 잘못짰는데 수정하기가 너무 일이 커져서 그냥 새로 메소드 하나 만듬)

	int quit_member(Map<String, String> paraMap); // 아이디, 비밀번호가 일치하면 회원 탈퇴를 할 수 있게 해주는 메소드 생성.

	List<Map<String, String>> recruit_notice_name(Map<String, String> paraMap); // 모집공고 조회 

	List<Map<String, String>> position_name(Map<String, String> paraMap); // 직위 검색에 대한 메소드
	
	List<Map<String, String>> work_tp(Map<String, String> paraMap1); // 직종에대한 검색 메소드

	List<Map<String, String>> com_size(Map<String, String> paraMap111); // 기업규모에 대한 검색 메소드

	void search_company_sales(String search_sales); // 입력한 매출액 이상의 모든 회사를 조회하는 메소드 생성

	void view_all_company(); // 모든 회사를 보여주는 메소드 생성

	void search_yearsal(String search_yearsal); // 입력한 연봉보다 큰 모집공고를 모두 보여주는 메소드 생성

	Map<String, String> view_one_recruit_notice(String want_recruit_notice_no); // 원하는 공고를 조회하는 메소드 생성.

	int join_recruit_notice(MemberDTO member, String want_recruit_notice_no); // 공고에 지원해주는 메소드 생성.

	void view_all_recruit_notice(); // 모든 공고 목록을 보여주는 메소드 생성.
	
	void view_resume_detail(String choice_resume_no); // 이력서 번호 선택시 상세 정보를 보여주는 메소드

	List<Map<String, String>> view_job_type(); // 직무형태 목록을 띄워주는 메소드

	String search_jobtype_code(String get_jobtype_name); // 직무형태 번호를 받아서 목록에 따른 직무코드를 알려주는 메소드

	List<Map<String, String>> view_certifi_type(); // 자격증 목록을 띄워주는 메소드

	String search_certifi_code(String get_certifi_name); // 자격증 번호를 받아서 목록에 따른 자격증코드를 알려주는 메소드

	List<Map<String, String>> view_army_type();// 병역정보 목록을 띄워주는 메소드

	String search_army_code(String get_army_name); // 병역정보 번호를 받아서 목록에 따른 병역정보 코드를 알려주는 메소드
	
	List<Map<String, String>> view_my_all_resume(MemberDTO member); // 구직자 이력서 목록을 보여주는 메소드

	int delete_resume(String choice_resume_no, MemberDTO member); // 선택한 이력서를 지워주는 메소드 생성 

	Map<String, String> get_resume(MemberDTO member); // 기존의 이력서를 열람하게 해주는 메소드 

	List<String> view_all_certifi_type_list(); // 모든 자격증 목록을 list에 담아주는 메소드 생성.

	String get_certifi_code(String string); // 입력한 자격증명과 일치하는 코드를 반환.
 
	int update_certifi(MemberDTO member, Map<String, String> paraMap); // 수정한 정보들로 이력서를 바꾸어 주는 메소드 생성.

	void insert_resume(MemberDTO member, Map<String, String> map); // 입력한 정보를 토대로 이력서를 작성하는 코드 생성



	
}
=======
package member.model;

import java.util.List;
import java.util.Map;

import member.domain.MemberDTO;


public interface MemberDAO {

	MemberDTO login(Map<String, String> paraMap); // 로그인 처리를 하게 해주는 메소드
	
	int check_userid(String userid); // 내가 회원가입 할 아이디가 이미 있는 아이디인지 검사하기

	List<String> search_address(String address); // 검색한 지역들을 확인해주는 메소드 생성

	String search_address_code(String address); // 검색한 지역의 코드를 알려주는 메소드 생성

	int join_member(Map<String, String> paraMap); // 입력한 정보를 바탕으로 회원가입을 하게 해주는 메소드 생성

	String find_user_id(Map<String, String> find_id_map); // 주민등록번호와 비밀번호가 일치한다면 아이디를 찾아주는 메소드 생성

	int find_user_passwd(Map<String, String> find_passwd_map); // 주민등록번호와 아이디가 일치한다면 비밀번호를 변경해주는 메소드 생성

	List<Map<String, String>> search_comname(Map<String, String> paraMap); // 회사명 찾기

	List<Map<String, String>> search_buss_type(Map<String, String> paraMap1); // 업종검색

	MemberDTO view_member_info(Map<String, String> paraMap); // 회원 정보를 보여주는 메소드 생성.

	int change_member_info(Map<String, String> paraMap); // 위의 변경한 값을 토대로 업데이트를 진행해주는 메소드 생성

	String getemail(Map<String, String> paraMap); // 아이디, 비밀번호를 입력하면 가지고있던 email을 리턴시켜주는 메소드 생성 (애초에 코드를 잘못짰는데 수정하기가 너무 일이 커져서 그냥 새로 메소드 하나 만듬)

	int quit_member(Map<String, String> paraMap); // 아이디, 비밀번호가 일치하면 회원 탈퇴를 할 수 있게 해주는 메소드 생성.

	List<Map<String, String>> recruit_notice_name(Map<String, String> paraMap); // 모집공고 조회 

	List<Map<String, String>> position_name(Map<String, String> paraMap); // 직위 검색에 대한 메소드
	
	List<Map<String, String>> work_tp(Map<String, String> paraMap1); // 직종에대한 검색 메소드

	List<Map<String, String>> com_size(Map<String, String> paraMap111); // 기업규모에 대한 검색 메소드

	void search_company_sales(String search_sales); // 입력한 매출액 이상의 모든 회사를 조회하는 메소드 생성

	void view_all_company(); // 모든 회사를 보여주는 메소드 생성

	void search_yearsal(String search_yearsal); // 입력한 연봉보다 큰 모집공고를 모두 보여주는 메소드 생성

	Map<String, String> view_one_recruit_notice(String want_recruit_notice_no); // 원하는 공고를 조회하는 메소드 생성.

	int join_recruit_notice(MemberDTO member, String want_recruit_notice_no); // 공고에 지원해주는 메소드 생성.

	void view_all_recruit_notice(); // 모든 공고 목록을 보여주는 메소드 생성.
	
	void view_resume_detail(String choice_resume_no); // 이력서 번호 선택시 상세 정보를 보여주는 메소드

	List<Map<String, String>> view_job_type(); // 직무형태 목록을 띄워주는 메소드

	String search_jobtype_code(String get_jobtype_name); // 직무형태 번호를 받아서 목록에 따른 직무코드를 알려주는 메소드

	List<Map<String, String>> view_certifi_type(); // 자격증 목록을 띄워주는 메소드

	String search_certifi_code(String get_certifi_name); // 자격증 번호를 받아서 목록에 따른 자격증코드를 알려주는 메소드

	List<Map<String, String>> view_army_type();// 병역정보 목록을 띄워주는 메소드

	String search_army_code(String get_army_name); // 병역정보 번호를 받아서 목록에 따른 병역정보 코드를 알려주는 메소드
	
	List<Map<String, String>> view_my_all_resume(MemberDTO member); // 구직자 이력서 목록을 보여주는 메소드

	int delete_resume(String choice_resume_no, MemberDTO member); // 선택한 이력서를 지워주는 메소드 생성 

	Map<String, String> get_resume(MemberDTO member); // 기존의 이력서를 열람하게 해주는 메소드 

	List<String> view_all_certifi_type_list(); // 모든 자격증 목록을 list에 담아주는 메소드 생성.

	String get_certifi_code(String string); // 입력한 자격증명과 일치하는 코드를 반환.
 
	int update_certifi(MemberDTO member, Map<String, String> paraMap); // 수정한 정보들로 이력서를 바꾸어 주는 메소드 생성.

	void insert_resume(MemberDTO member, Map<String, String> map); // 입력한 정보를 토대로 이력서를 작성하는 코드 생성



	
}
>>>>>>> c9027385ac9e12d196bf66c374e5852e058ed599
