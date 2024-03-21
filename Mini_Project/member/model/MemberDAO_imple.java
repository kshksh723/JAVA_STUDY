package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dbconnection.MyDBConnection;
import member.domain.MemberDTO;


public class MemberDAO_imple implements MemberDAO {

	private Connection conn = MyDBConnection.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs= null;
	Scanner sc = new Scanner(System.in);
	
	// == 자원 반납을 하게 해주는 메소드 생성 ==/
	private void close() {
		try {
			if(rs != null) 	  {rs.close();	rs = null;}
			if(pstmt != null) {pstmt.close();	pstmt = null;}
			// conn은 프로그램을 종료할때만 끊어야한다. 
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()----------------------------
	
	
	// ==  로그인처리(select) 메소드 == 
	@Override
	public MemberDTO login(Map<String, String> paraMap) {
		MemberDTO member = new MemberDTO();

		try {
			
		
			String sql = " select PK_user_id, user_passwd, user_name, user_status "
					+ " from tbl_member "
					+ " where  PK_user_id = ? and user_passwd = ? ";
			  
		
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		pstmt.setString(1, paraMap.get("pk_user_id") ); //dto에 member를 보여달라 
		pstmt.setString(2, paraMap.get("pass_wd")); // 2 는 String sql 에서 두번째 위치홀더(?)를 말한다. 두번째 위치홀더(?)에 msg 을 넣어준다.
		
		rs = pstmt.executeQuery(); // sql문 실행 
		
		 	if(rs.next()) {
				member = new MemberDTO();
				
				member.setPK_USER_ID(rs.getString("pk_user_id")); // 대소문자 구분치 않다 
				member.setUSER_PASSWD(rs.getString("user_passwd"));
				member.setUSER_NAME(rs.getString("user_name"));
				member.setUSER_STATUS(rs.getString("user_status"));
				
				return member;
		 	}
		 	else {
		 		return member;
		 	}
		
		} catch(SQLException e){
	         if(e.getErrorCode() == 1) {
	         
	             e.printStackTrace();
	          }
	         e.printStackTrace();
		} finally {
		close();
		 
		}
		return member;
		
	}
	
	
	// == 회사명으로 검색하게 해주는 메소드 생성
	@Override
	public List<Map<String, String>> search_comname(Map<String, String> paraMap) {

	    List<Map<String, String>> mapList = new ArrayList<>();
	    try {
	        String sql =

	            "  WITH "
	            + "    V AS "
	            + "    (  "
	            + "    select  *  "
	            + "    from tbl_company_detail "
	            + "    where company_name like  '%' || ? || '%' "
	            + "    ) "
	            + "    select distinct  V.company_name, V.sales, V.number_of_employee, V.homepage, V.establishment_date, V.company_address, B.buss_type_name, C.com_size "
	            + "    FROM V JOIN tbl_buss_type B  "
	            + "    ON V.fk_buss_type_code = B.pk_buss_type_code "
	            + "    JOIN tbl_company_size C  "
	            + "    ON V.fk_company_size_code = C.pk_com_size_code ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("COMPANY_NAME"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> MemberDTODetail = new HashMap<>(); 

	            MemberDTODetail.put("COMPANY_NAME", rs.getString("COMPANY_NAME"));
	            MemberDTODetail.put("SALES", rs.getString("SALES"));
	            MemberDTODetail.put("NUMBER_OF_EMPLOYEE", rs.getString("NUMBER_OF_EMPLOYEE"));
	            MemberDTODetail.put("HOMEPAGE", rs.getString("HOMEPAGE"));
	            MemberDTODetail.put("ESTABLISHMENT_DATE", rs.getString("ESTABLISHMENT_DATE"));
	            MemberDTODetail.put("COMPANY_ADDRESS", rs.getString("COMPANY_ADDRESS"));
	            MemberDTODetail.put("BUSS_TYPE_NAME", rs.getString("BUSS_TYPE_NAME"));
	            MemberDTODetail.put("COM_SIZE", rs.getString("COM_SIZE"));

	            mapList.add(MemberDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return mapList;

	} // end of public List<Map<String, String>> search_comname(Map<String, String> paraMap)
	
	
	
	
	// == 업종명으로 검색하게 해주는 메소드 생성
	@Override
	public List<Map<String, String>> search_buss_type(Map<String, String> paraMap1) {

		List<Map<String, String>> mapList = new ArrayList<>();
	    try {
	        String sql =
	               "      select distinct  Fk_buss_type_code, Pk_buss_type_code, company_name, buss_type_name, company_name, sales, number_of_employee "
	               + "     , homepage ,establishment_date  ,company_address , fk_buss_type_code , fk_company_size_code  "
	               + "     , com_size, buss_type_name "
	               + "    from tbl_company_detail "
	               + "    join tbl_buss_type "
	               + "    on tbl_company_detail.fk_buss_type_code = tbl_buss_type.Pk_buss_type_code "
	               + "    join tbl_company_size "
	               + "    on tbl_company_detail.fk_company_size_code = tbl_company_size.pk_com_size_code "
	               + "    where buss_type_name LIKE '%' || ? || '%' ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap1.get("BUSS_TYPE_NAME"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> MemberDTODetail = new HashMap<>(); 

	            MemberDTODetail.put("COMPANY_NAME", rs.getString("COMPANY_NAME"));
	            MemberDTODetail.put("SALES", rs.getString("SALES"));
	            MemberDTODetail.put("NUMBER_OF_EMPLOYEE", rs.getString("NUMBER_OF_EMPLOYEE"));
	            MemberDTODetail.put("HOMEPAGE", rs.getString("HOMEPAGE"));
	            MemberDTODetail.put("ESTABLISHMENT_DATE", rs.getString("ESTABLISHMENT_DATE"));
	            MemberDTODetail.put("COMPANY_ADDRESS", rs.getString("COMPANY_ADDRESS"));
	            MemberDTODetail.put("BUSS_TYPE_NAME", rs.getString("BUSS_TYPE_NAME"));
	            MemberDTODetail.put("COM_SIZE", rs.getString("COM_SIZE"));

	            mapList.add(MemberDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return mapList;


	} // end of public List<Map<String, String>> search_buss_type(Map<String, String> paraMap1)
	
	
	
	// 공고명 조회 

	@Override
	public List<Map<String, String>> recruit_notice_name(Map<String, String> paraMap) {
		 List<Map<String, String>> mapList = new ArrayList<>();
		    try {
		        String sql =
		               " WITH "
		               + "    R AS  "
		               + "    ( "
		               + "    select *  "
		               + "    from tbl_recruit_notice "
		               + "    ) "
		               + "    SELECT R.pk_recruit_notice_code, C.company_name,  R.recruit_notice_name, yearsal, W.worktype_name, L.location_name, P.position_name, to_char(R.recruit_finish_day,'yy-mm-dd') as recruit_finish_day "
		               + "    from R JOIN tbl_company_detail C "
		               + "    on R.fk_company_code = C.pk_company_code "
		               + "    JOIN tbl_worktype W "
		               + "    on R.fk_worktype_code = W.pk_worktype_code "
		               + "    JOIN tbl_location L "
		               + "    on R.fk_location_code = L.pk_location_code "
		               + "    JOIN tbl_position P "
		               + "    on R.fk_position_code = P.pk_position_code "
		               + "    where R.recruit_notice_name LIKE '%' || ? || '%' and sysdate - recruit_finish_day < 0"
		               + "    order by R.recruit_start_day desc ";

		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, paraMap.get("NOTICE_NAME"));
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            Map<String, String> MemberDTODetail = new HashMap<>(); 

		            MemberDTODetail.put("PK_RECRUIT_NOTICE_CODE", rs.getString("PK_RECRUIT_NOTICE_CODE")); //공고코드
		            MemberDTODetail.put("RECRUIT_NOTICE_NAME", rs.getString("RECRUIT_NOTICE_NAME")); //공고명
		            MemberDTODetail.put("COMPANY_NAME", rs.getString("COMPANY_NAME")); // 기업명
		            MemberDTODetail.put("YEARSAL", rs.getString("YEARSAL"));	// 연봉
		            MemberDTODetail.put("WORKTYPE_NAME", rs.getString("WORKTYPE_NAME")); // 근무형태
		            MemberDTODetail.put("LOCATION_NAME", rs.getString("LOCATION_NAME"));	// 지역명
		            MemberDTODetail.put("POSITION_NAME", rs.getString("POSITION_NAME")); // 직위명
		            MemberDTODetail.put("RECRUIT_FINISH_DAY", rs.getString("RECRUIT_FINISH_DAY")); // 공고마감날짜

		            mapList.add(MemberDTODetail);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close();
		    }
		    return mapList;


		}
	
	
	
	
	// 직종명 검색
	@Override
	public List<Map<String, String>> position_name(Map<String, String> paraMap) {
		List<Map<String, String>> mapList = new ArrayList<>();
	    try {
	        String sql =
	               "     WITH "
	               + "    R AS "
	               + "    ( "
	               + "    select * "
	               + "    from tbl_recruit_notice "
	               + "    ) "
	               + "    SELECT R.pk_recruit_notice_code, C.company_name, "
	               + "  R.recruit_notice_name, yearsal, W.worktype_name, L.location_name, "
	               + " P.position_name, to_char(R.recruit_finish_day,'yy-mm-dd') as recruit_finish_day "
	               + "    from R JOIN tbl_company_detail C "
	               + "    on R.fk_company_code = C.pk_company_code "
	               + "    JOIN tbl_worktype W "
	               + "    on R.fk_worktype_code = W.pk_worktype_code "
	               + "    JOIN tbl_location L "
	               + "    on R.fk_location_code = L.pk_location_code "
	               + "    JOIN tbl_position P "
	               + "    on R.fk_position_code = P.pk_position_code "
	               + "    where P.position_name like '%' || ?  || '%' and sysdate - recruit_finish_day < 0 "
	               + "    order by R.recruit_start_day desc ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("PN"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> MemberDTODetail = new HashMap<>(); 

	            MemberDTODetail.put("PK_RECRUIT_NOTICE_CODE", rs.getString("PK_RECRUIT_NOTICE_CODE")); //공고코드
	            MemberDTODetail.put("RECRUIT_NOTICE_NAME", rs.getString("RECRUIT_NOTICE_NAME")); //공고명
	            MemberDTODetail.put("COMPANY_NAME", rs.getString("COMPANY_NAME")); // 기업명
	            MemberDTODetail.put("POSITION_NAME", rs.getString("POSITION_NAME")); // 직위명
	            MemberDTODetail.put("LOCATION_NAME", rs.getString("LOCATION_NAME"));	// 지역명
	            MemberDTODetail.put("YEARSAL", rs.getString("YEARSAL"));	// 연봉
	            MemberDTODetail.put("WORKTYPE_NAME", rs.getString("WORKTYPE_NAME")); // 근무형태
	            MemberDTODetail.put("RECRUIT_FINISH_DAY", rs.getString("RECRUIT_FINISH_DAY")); // 공고마감날짜

	            mapList.add(MemberDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return mapList;


	}
	
	
	
	// 근무형태 
	@Override
	public List<Map<String, String>> work_tp(Map<String, String> paraMap1) {
		List<Map<String, String>> mapList = new ArrayList<>();
	    try {
	        String sql =
	               "   WITH "
	               + "    R AS "
	               + "    ( "
	               + "    select * "
	               + "    from tbl_recruit_notice "
	               + "    ) "
	               + "    SELECT R.pk_recruit_notice_code, C.company_name,  R.recruit_notice_name, yearsal, W.worktype_name, L.location_name, P.position_name, to_char(R.recruit_finish_day,'yy-mm-dd') as recruit_finish_day "
	               + "    from R JOIN tbl_company_detail C "
	               + "    on R.fk_company_code = C.pk_company_code "
	               + "    JOIN tbl_worktype W "
	               + "    on R.fk_worktype_code = W.pk_worktype_code "
	               + "    JOIN tbl_location L "
	               + "    on R.fk_location_code = L.pk_location_code "
	               + "    JOIN tbl_position P "
	               + "    on R.fk_position_code = P.pk_position_code "
	               + "    where  W.worktype_name like '%' || ? || '%' and sysdate - recruit_finish_day < 0 "
	               + "    order by R.recruit_start_day desc ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap1.get("WT"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> MemberDTODetail = new HashMap<>(); 

	            MemberDTODetail.put("PK_RECRUIT_NOTICE_CODE", rs.getString("PK_RECRUIT_NOTICE_CODE")); //공고코드
	            MemberDTODetail.put("RECRUIT_NOTICE_NAME", rs.getString("RECRUIT_NOTICE_NAME")); //공고명
	            MemberDTODetail.put("COMPANY_NAME", rs.getString("COMPANY_NAME")); // 기업명
	            MemberDTODetail.put("POSITION_NAME", rs.getString("POSITION_NAME")); // 직위명
	            MemberDTODetail.put("LOCATION_NAME", rs.getString("LOCATION_NAME"));	// 지역명
	            MemberDTODetail.put("YEARSAL", rs.getString("YEARSAL"));	// 연봉
	            MemberDTODetail.put("WORKTYPE_NAME", rs.getString("WORKTYPE_NAME")); // 근무형태
	            MemberDTODetail.put("RECRUIT_FINISH_DAY", rs.getString("RECRUIT_FINISH_DAY")); // 공고마감날짜

	            mapList.add(MemberDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return mapList;


	}
	
	
	
	// 기업규모
	@Override
	public List<Map<String, String>> com_size(Map<String, String> paraMap111) {
		
		List<Map<String, String>> mapList = new ArrayList<>();
	    try {
	        String sql =
	               "     WITH "
	               + "    R AS "
	               + "    ( "
	               + "    select * "
	               + "    from tbl_recruit_notice "
	               + "    ) "
	               + "    SELECT R.pk_recruit_notice_code, C.company_name,  R.recruit_notice_name, yearsal, W.worktype_name, L.location_name, P.position_name,com_size, to_char(R.recruit_finish_day,'yy-mm-dd') as recruit_finish_day "
	               + "    from R JOIN tbl_company_detail C "
	               + "    on R.fk_company_code = C.pk_company_code "
	               + "    JOIN tbl_worktype W "
	               + "    on R.fk_worktype_code = W.pk_worktype_code "
	               + "    JOIN tbl_location L "
	               + "    on R.fk_location_code = L.pk_location_code "
	               + "    join tbl_company_size "
	               + "    on C.fk_company_size_code = tbl_company_size.pk_com_size_code "
	               + "    JOIN tbl_position P "
	               + "    on R.fk_position_code = P.pk_position_code "
	               + "     where  com_size like '%' || ? || '%' and sysdate - recruit_finish_day < 0"
	               + "    order by R.recruit_start_day desc ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap111.get("CS"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> MemberDTODetail = new HashMap<>(); 

	            MemberDTODetail.put("PK_RECRUIT_NOTICE_CODE", rs.getString("PK_RECRUIT_NOTICE_CODE")); //공고코드
	            MemberDTODetail.put("RECRUIT_NOTICE_NAME", rs.getString("RECRUIT_NOTICE_NAME")); //공고명
	            MemberDTODetail.put("COMPANY_NAME", rs.getString("COMPANY_NAME")); // 기업명
	            MemberDTODetail.put("POSITION_NAME", rs.getString("POSITION_NAME")); // 직위명
	            MemberDTODetail.put("LOCATION_NAME", rs.getString("LOCATION_NAME"));	// 지역명
	            MemberDTODetail.put("YEARSAL", rs.getString("YEARSAL"));	// 연봉
	            MemberDTODetail.put("COM_SIZE", rs.getString("COM_SIZE"));	// 기업규모
	            MemberDTODetail.put("WORKTYPE_NAME", rs.getString("WORKTYPE_NAME")); // 근무형태
	            MemberDTODetail.put("RECRUIT_FINISH_DAY", rs.getString("RECRUIT_FINISH_DAY")); // 공고마감날짜

	            mapList.add(MemberDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return mapList;


	}
	
	
	
	// 내가 회원가입 할 아이디가 이미 있는 아이디인지 검사하기
	@Override
	public int check_userid(String userid) {
		
		int result = 0; // 같은 아이디를 사용하는 사람이 있는지 없는지 체크하게 해주는 변수 
		
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select * "
					   + " from tbl_member "
					   + " where pk_user_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) {
				result = 1; // 같은 아이디를 사용하는 사람이 있는 경우
			}
			else {
				result = 0; // 같은 아이디를 사용하는 사람이 없는 경우
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		return result;
	} // end of public int check_userid(String userid)


	// 검색한 지역들을 확인해주는 메소드 생성
	@Override
	public List<String> search_address(String address) {
		
		List<String> search_address = new ArrayList<>();
		
		try {
			String sql = " select location_name "
					   + " from tbl_location "
					   + " where location_name like '%'|| ? ||'%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				search_address.add(rs.getString("location_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		return search_address;
	}// end of public List<String> search_address(String address)


	// 검색한 지역의 코드를 알려주는 메소드 생성
	@Override
	public String search_address_code(String address) {
		
		String address_code = "";
		
		try {
			String sql = " select pk_location_code "
					   + " from tbl_location "
					   + " where location_name like '%'|| ? ||'%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				address_code = rs.getString("pk_location_code");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		return address_code;
	}


	// 입력한 정보를 바탕으로 회원가입을 할 수 있게 해주는 메소드 생성
	@Override
	public int join_member(Map<String, String> paraMap) {
		
		int result_1 = 0; // tbl_member 테이블에 인서트 성공 유무의 값을 저장해둘 변수 및 최종 리턴 
		int result_2 = 0; // tbl_member_login 테이블에 인서트 성공 유무의 값을 저장해둘 변수 
		
		try {
			conn = MyDBConnection.getConn();
			
			// tbl_member 에 insert하기위한 sql문 //
			String sql = " insert into tbl_member(PK_USER_ID , USER_EMAIL , USER_PASSWD , USER_NAME , USER_JUBUN , USER_TEL , LOCATION_CODE) "
					   + " values (?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("user_id"));
			pstmt.setString(2, paraMap.get("email"));
			pstmt.setString(3, paraMap.get("passwd"));
			pstmt.setString(4, paraMap.get("name"));
			pstmt.setString(5, paraMap.get("jubun"));
			pstmt.setString(6, paraMap.get("tel"));
			pstmt.setString(7, paraMap.get("address_code"));
			
			conn.setAutoCommit(false); // 회원가입을 정말 진행할지 아닐지 물어보기위해 auto commit 해제 
			result_1 = pstmt.executeUpdate();
			
			// tbl_member_login 에도 동시에 insert를 해주어야하기때문에 login 테이블에도 insert해주기 위한 sql 문 
			sql = " insert into tbl_member_login(pk_fk_user_id, user_passwd, user_name) "
					+ " values (?,?,?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, paraMap.get("user_id")); 
				pstmt.setString(2, paraMap.get("passwd"));
				pstmt.setString(3, paraMap.get("name"));
				result_2 = pstmt.executeUpdate();
			
			
			if(result_1 == 1 && result_2 == 1) { // tbl_member와 tbl_member_login 테이블 두 곳 모두 insert를 성공한다면. 
					
				String yn = "";
				do {
					System.out.println("▶ 정말 회원가입 하시겠습니까? [Y/N]");
					yn = sc.nextLine();
					if("y".equalsIgnoreCase(yn)) {
						result_1 = 1;
						conn.commit(); 
					}
					else if("n".equalsIgnoreCase(yn)) {
						result_1 = 0;
						conn.rollback();
					}
					else {
						System.out.println("[경고] 반드시 Y 혹은 N 중에서 입력하세요");
					}
				}while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
			
				
				

				conn.setAutoCommit(true);// sql 문을 모두 끝내고 돌아가기전에 다시 auto commit활성화 
			}
			
		} catch (SQLException e) { // 이미 아이디 검사에서 중복되는 아이디가 있는지 다 검사해왔기 때문에 ecxception이 뜨지 않음.
			e.printStackTrace();
			result_1 = -1;
		} finally {
			close();	
		}
		
		
		return result_1; // 리턴값이 1이면 회원가입 성공, 0이면 회원가입 취소 -1이면 회원가입 실패
	}

	
	// 주민등록번호와 비밀번호가 일치한다면 아이디를 찾아주는 메소드 생성
	@Override
	public String find_user_id(Map<String, String> find_id_map) {
		String user_id = null;
		
		try {
			String sql = " select pk_user_id "
					   + " from tbl_member "
					   + " where user_jubun = ? and user_passwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find_id_map.get("user_jubun"));
			pstmt.setString(2, find_id_map.get("user_passwd"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_id = rs.getString("pk_user_id");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		return user_id;
	} // end ofpublic String find_user_id(Map<String, String> find_id_map)

	
	// 주민등록번호와 아이디가 일치한다면 비밀번호를 변경해주는 메소드 생성
	@Override
	public int find_user_passwd(Map<String, String> find_passwd_map) {
		
		int succes_change = 0;
		
		try {
			String sql = " select user_passwd "
					   + " from tbl_member "
					   + " where user_jubun = ? and pk_user_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find_passwd_map.get("jubun"));
			pstmt.setString(2, find_passwd_map.get("user_id"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("-----------[비밀번호 변경]-----------");
				
				System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
				String change_passwd = "";
				
				do {
					MemberDTO mdto = new MemberDTO();
					System.out.print("▶ 변경할 비밀번호를 입력하세요 : ");
					change_passwd = sc.nextLine();
					mdto.setUSER_PASSWD(change_passwd); // 비밀번호 유효성 검사 
					if(!(mdto.getUSER_PASSWD() == null || mdto.getUSER_PASSWD().isBlank())) {
						break;
					}
					else {
						System.out.println("[경고] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
					}
				}while(true); // end of do_while passwd
				
				sql = " update tbl_member set user_passwd = ? "
					+ " where user_jubun = ? and pk_user_id = ?";
				pstmt = conn.prepareStatement(sql);
				
				conn.setAutoCommit(false); // 수동커밋으로 전환  
				
				pstmt.setString(1, change_passwd);
				pstmt.setString(2, find_passwd_map.get("jubun"));
				pstmt.setString(3, find_passwd_map.get("user_id"));
				
				int n_1 = pstmt.executeUpdate();
				
				
				String yn = "";
				if(n_1 == 1) {
					
					
					sql = " update tbl_member_login set user_passwd = ? "
						+ " where pk_fk_user_id = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, change_passwd);
						pstmt.setString(2, find_passwd_map.get("user_id"));
						
						int n_2 = pstmt.executeUpdate();
					
					if(n_2 == 1){
						do {
						System.out.print("▶ 비밀번호를 변경 하시겠습니까? [Y/N] : ");
						yn = sc.nextLine();

						
							if("y".equalsIgnoreCase(yn)) {
								conn.commit();
								succes_change = 1;
								conn.setAutoCommit(true);
								break;
							}
							else if("n".equalsIgnoreCase(yn)) {
								conn.rollback();
								succes_change = 0;
								conn.setAutoCommit(true);
								break;
							}
							else {
								System.out.println("[경고] 반드시 Y 혹은 N 중에서 입력하세요");
							}
						}while(true);
					}
					else {
						succes_change = -1;
					}

				}
				else {
					succes_change = -1;
				}
			
			}
			else {
				succes_change = -1;
			}
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		return succes_change;
	} // end of public int find_user_passwd(Map<String, String> find_passwd_map)

	
	
	
	// 회원 정보를 보여주는 메소드 생성.
	@Override
	public MemberDTO view_member_info(Map<String, String> paraMap) {
		
		MemberDTO mdto = new MemberDTO();
		
		try {
			String sql = " select M.user_name as name"
					   + ", M.pk_user_id as user_id"
					   + ", M.user_email as email"
					   + ", L.location_name as location_name"
					   + ", M.user_tel"
					   + ", Rpad(substr(user_jubun,1,8),length(user_jubun),'*') as jubun"
					   + ", M.location_code "
					   + ", M.user_passwd "
					   + " from tbl_member M JOIN tbl_location L "
					   + " on M.location_code = L.pk_location_code "
					   + " where M.pk_user_id = ? and M.user_passwd = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("pk_user_id"));
			pstmt.setString(2, paraMap.get("pass_wd"));
			rs = pstmt.executeQuery();
			
			StringBuilder sb = new StringBuilder();
			if(rs.next()) {
				sb.append("1. 이름 : " + rs.getString("name") + "\n");
				sb.append("2. 아이디 : " + rs.getString("user_id") + "\n");
				sb.append("3. 이메일 : " + rs.getString("email") + "\n");
				sb.append("4. 거주지역 : " + rs.getString("location_name") + "\n");
				sb.append("5. 연락처 : " + rs.getString("user_tel") + "\n");
				sb.append("6. 주민등록번호 : " + rs.getString("jubun") + "\n");
				
				mdto.setUSER_NAME(rs.getString("name"));
				mdto.setUSER_EMAIL(rs.getString("email").substring(0,rs.getString("email").indexOf("@")));
				mdto.setUSER_TEL(rs.getString("user_tel"));
				mdto.setUSER_PASSWD(rs.getString("user_passwd"));
				mdto.setLOCATION_CODE(rs.getString("location_code"));
			}
			
			System.out.println(sb.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		return mdto;
		
		
		
	} // end of public List<String> view_member_info(Map<String, String> paraMap)

	
	
	// 위의 변경한 값을 토대로 업데이트를 진행해주는 메소드 생성
	@Override
	public int change_member_info(Map<String, String> paraMap) {
		
		int result = 0;
		/*
		 * PK_USER_ID    NOT NULL VARCHAR2(15)  
USER_EMAIL    NOT NULL VARCHAR2(50)  
USER_PASSWD   NOT NULL VARCHAR2(20)  
USER_NAME     NOT NULL NVARCHAR2(10) 
USER_JUBUN    NOT NULL VARCHAR2(14)  
USER_TEL      NOT NULL VARCHAR2(13)  
RESUME_CODE            VARCHAR2(10)  
LOCATION_CODE NOT NULL VARCHAR2(10)  
		 */
		try {
			String sql = " update tbl_member set user_email = ?, user_passwd = ?, user_name = ?, user_tel = ?, location_code = ? "
					   + " where pk_user_id = ? and user_passwd = ? ";
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("change_email"));
			pstmt.setString(2, paraMap.get("change_passwd"));
			pstmt.setString(3, paraMap.get("change_name"));
			pstmt.setString(4, paraMap.get("change_tel"));
			pstmt.setString(5, paraMap.get("change_location_name"));
			pstmt.setString(6, paraMap.get("pk_user_id"));
			pstmt.setString(7, paraMap.get("pass_wd"));
			
			int n_1 = pstmt.executeUpdate();
			if(n_1 == 1) {
				
				sql = " update tbl_member_login set user_passwd = ?, user_name = ? "
					+ " where pk_fk_user_id = ? and user_passwd = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, paraMap.get("change_passwd"));
				pstmt.setString(2, paraMap.get("change_name"));
				pstmt.setString(3, paraMap.get("pk_user_id"));
				pstmt.setString(4, paraMap.get("pass_wd"));
				
				int n_2 = pstmt.executeUpdate();
				
				if(n_2 == 1) {
					do {
					
					System.out.println(">>> 이대로 수정하시겠습니까??? [Y/N] <<<");
					String yn = sc.nextLine();
					
					if("y".equalsIgnoreCase(yn)) {
						conn.commit();
						conn.setAutoCommit(true);
						return result = 1;
					}
					else if("n".equalsIgnoreCase(yn)) {
						conn.rollback();
						conn.setAutoCommit(true);
						return result = 0;
					}
					
					
					}while(true);
				}
				else {
					conn.rollback();
					result = -1;
				}
			}
			else {
				result = -1;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		return result;
		
	}// end of public int change_member_info(Map<String, String> paraMap)

	// 아이디, 비밀번호를 입력하면 가지고있던 email을 리턴시켜주는 메소드 생성 (애초에 코드를 잘못짰는데 수정하기가 너무 일이 커져서 그냥 새로 메소드 하나 만듬)
	@Override
	public String getemail(Map<String, String> paraMap) {
		
		String email = "";
		
		try {
			String sql = " select user_email"
					   + " from tbl_member "
					   + " where pk_user_id = ? and user_passwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("pk_user_id"));
			pstmt.setString(2, paraMap.get("pass_wd"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				email = rs.getString("user_email");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		return email;
	}

	
	
	
	// 아이디, 비밀번호가 일치하면 회원 탈퇴를 할 수 있게 해주는 메소드 생성.
	@Override
	public int quit_member(Map<String, String> paraMap) {
		
		int result = 0;
		
		try {
			String sql = " select fk_user_status "
					   + " from tbl_member_login "
					   + " where pk_fk_user_id = ? and user_passwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("user_id"));
			pstmt.setString(2, paraMap.get("user_passwd"));
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				
				if(rs.getString("fk_user_status").equals("0")) {
					System.out.println("[경고] 이미 탈퇴한 회원정보입니다.");
					return result = -1;
				}
				else {
					conn.setAutoCommit(false);
					
					sql = " update tbl_member_login set fk_user_status = '0' "
						+ " where pk_fk_user_id = ? and user_passwd = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, paraMap.get("user_id"));
					pstmt.setString(2, paraMap.get("user_passwd"));
					int n_1 = pstmt.executeUpdate();
					
					if( n_1 == 1 ) {
						
						sql = " update tbl_member set user_status = '0' "
							+ " where pk_user_id = ? and user_passwd = ? ";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, paraMap.get("user_id"));
						pstmt.setString(2, paraMap.get("user_passwd"));
						int n_2 = pstmt.executeUpdate();
						
						if( n_2 == 1) {
							do {
								
								System.out.print(">>> 정말로 탈퇴하시겠습니까? [Y/N] : ");
								String yn = sc.nextLine();
								if("y".equalsIgnoreCase(yn)) {
									conn.commit();
									conn.setAutoCommit(true);
									return result = 1;
								}
								else if("n".equalsIgnoreCase(yn)) {
									conn.rollback();
									conn.setAutoCommit(true);
									return result = 0;
								}
								else {
									System.out.println("[경고] 메뉴에 없는 번호입니다.");
								}
								
							}while (true);
						}
						else { //두번째 update문을 실패했을 경우. 
							result = -1;
						}
					}
					else { // 첫번째 update문을 실패했을 경우.
						result = -1;
					}
				}
			}
			else {
				System.out.println("[경고] 일치하는 정보가 없습니다.");
				result = -1;
			}
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		return result;
		
		
	} // end of public void quit_member(Map<String, String> paraMap)

	
	
	// 입력한 매출액 이상의 모든 회사를 조회하는 메소드 생성
	@Override
	public void search_company_sales(String search_sales) {
		
		try {
			String sql = " WITH "
					+ "    CD AS "
					+ "    ( "
					+ "    select company_name, sales, number_of_employee, homepage, fk_buss_type_code, company_address, fk_company_size_code "
					+ "    from tbl_company_detail "
					+ "    where to_number(func_delcomma(sales)) >= ? "
					+ "    ) "
					+ "    SELECT CD.company_name, CD.sales, CD.number_of_employee, CD.homepage, B.buss_type_name, CD.company_address, S.com_size "
					+ "    FROM CD JOIN tbl_buss_type B "
					+ "    on CD.fk_buss_type_code = B.pk_buss_type_code "
					+ "    JOIN tbl_company_size S "
					+ "    on CD.fk_company_size_code = S.pk_com_size_code ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search_sales);
			rs = pstmt.executeQuery();
			
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				sb.append(rs.getString("company_name") + "\t");
				sb.append(rs.getString("sales") + "\t");
				sb.append(rs.getString("number_of_employee") + "\t");
				sb.append(rs.getString("homepage") + "\t");
				sb.append(rs.getString("buss_type_name") + "\t");
				sb.append(rs.getString("company_address") + "\t");
				sb.append(rs.getString("com_size") + "\n");
				
				
			}
			
			if(!sb.toString().isBlank()) {
			long num_search_sales = Long.parseLong(search_sales);
			   DecimalFormat df = new DecimalFormat("#,###");
			   search_sales = df.format(num_search_sales);
			   System.out.println("--------------------------------------------------------------매출액이 ["+ search_sales +"원] 이상인 모든 기업목록 ----------------------------------------------------------------------\r\n"
	 				+ "   기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			\r\n"
	 				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
	 				);
			   System.out.println(sb.toString());
			}
			else {
				System.out.println("[안내] 해당 매출액 이상인 기업은 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
	} // end of public void search_company_sales(String search_sales)

	
	
	// 모든 회사를 보여주는 메소드 생성
	@Override
	public void view_all_company() {
		
		try {
			String sql = " WITH "
					+ "    CD AS "
					+ "    ( "
					+ "    select company_name, sales, number_of_employee, homepage, fk_buss_type_code, company_address, fk_company_size_code "
					+ "    from tbl_company_detail "
					+ "    ) "
					+ "    SELECT CD.company_name, CD.sales, CD.number_of_employee, CD.homepage, B.buss_type_name, CD.company_address, S.com_size "
					+ "    FROM CD JOIN tbl_buss_type B "
					+ "    on CD.fk_buss_type_code = B.pk_buss_type_code "
					+ "    JOIN tbl_company_size S "
					+ "    on CD.fk_company_size_code = S.pk_com_size_code ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				sb.append("   "+rs.getString("company_name") + "      ");
				sb.append(rs.getString("sales") + "\t\t");
				sb.append(rs.getString("number_of_employee") + "\t");
				sb.append(rs.getString("homepage") + "\t");
				sb.append(rs.getString("buss_type_name") + "\t");
				sb.append(rs.getString("company_address") + "\t");
				sb.append(rs.getString("com_size") + "\n");
				
				
			}
			
			if(!sb.toString().isBlank()) {
			   System.out.println("--------------------------------------------------------------모든 기업 목록 ----------------------------------------------------------------------\r\n"
	 				+ "   기업명      연매출액                  사원수            홈페이지                    업종           	 본사주소                   기업규모   			\r\n"
	 				+ "--------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
	 				);
			   System.out.println(sb.toString());
			}
			else {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
	} // end of public void view_all_company()

	// 입력한 연봉보다 큰 모집공고를 모두 보여주는 메소드 생성
	@Override
	public void search_yearsal(String search_yearsal) {
		
		try {
			String sql = " WITH "
					+ "    N AS "
					+ "    ( "
					+ "    select * "
					+ "    from TBL_RECRUIT_NOTICE "
					+ "    where to_number(func_delcomma(yearsal)) >= ? "
					+ "    ) "
					+ "    SELECT N.pk_recruit_notice_code, N.recruit_notice_name,C.company_name,P.position_name,L.location_name,N.yearsal||'만원' as yearsal,W.worktype_name, to_char(N.recruit_finish_day,'yy-mm-dd') as recruit_finish_day "
					+ "    FROM N JOIN tbl_company_detail C "
					+ "    on N.fk_company_code = C.pk_company_code "
					+ "    JOIN tbl_position P "
					+ "    on P.pk_position_code = N.fk_position_code "
					+ "    JOIN tbl_location L  "
					+ "    on N.fk_location_code = L.pk_location_code "
					+ "    JOIN tbl_worktype W "
					+ "    on W.pk_worktype_code = N.fk_worktype_code "
					+ "    where sysdate - recruit_finish_day < 0";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search_yearsal);
			rs = pstmt.executeQuery();
			
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				sb.append("   "+rs.getString("pk_recruit_notice_code") + "      ");
				sb.append(rs.getString("recruit_notice_name") + "\t\t");
				sb.append(rs.getString("company_name") + "\t");
				sb.append(rs.getString("position_name") + "\t");
				sb.append(rs.getString("location_name") + "\t");
				sb.append(rs.getString("yearsal") + "\t");
				sb.append(rs.getString("worktype_name") + "\t");
				sb.append(rs.getString("recruit_finish_day") + "\n");
				
				
			}
			
			if(!sb.toString().isBlank()) {
				System.out.println("-----------------------------------------------------[공고 목록]----------------------------------------------------------------------------------------------\r\n"
						+ "   공고번호 			 공고명                         기업명           직위         근무지역            연봉           근무형태         공고마감일      \r\n"
						+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
			   System.out.println(sb.toString());
			}
			else {
				System.out.println("[경고] 해당 연봉 이상의 기업은 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
	} // end of public void search_yearsal(String search_yearsal)

	
	
	
	// 원하는 공고를 조회하는 메소드 생성.
	@Override
	public Map<String, String> view_one_recruit_notice(String want_recruit_notice_no) {
		Map<String,String> map = new HashMap<>();
		
		try {
			
			
			
			
              conn = MyDBConnection.getConn();
              String sql = " SELECT RN.recruit_notice_name "
              		+ "     , yearsal||'만원' as yearsal "
              		+ "     , quail "
              		+ "     , woodae "
              		+ "     , benefit "
              		+ "     , recruit_finish_day "
              		+ "     , P.position_name "
              		+ "     , C.company_name "
              		+ "     , W.worktype_name "
              		+ "     , J.job_type_name "
              		+ "     , L.location_name "
              		+ "     , S1.recruit_step_name as recruit_step_name_1 "
              		+ "     , S2.recruit_step_name as recruit_step_name_2 "
              		+ "     , S3.recruit_step_name as recruit_step_name_3 "
              		+ "     , S4.recruit_step_name as recruit_step_name_4 "
              		+ "     , S5.recruit_step_name as recruit_step_name_5 "
              		+ "    FROM tbl_recruit_notice RN JOIN tbl_position P "
              		+ "    ON RN.fk_position_code = P.pk_position_code "
              		+ "    JOIN tbl_company_detail C "
              		+ "    ON RN.fk_company_code = C.pk_company_code "
              		+ "    JOIN tbl_worktype W "
              		+ "    ON RN.fk_worktype_code = W.pk_worktype_code "
              		+ "    JOIN tbl_job_type J "
              		+ "    ON RN.fk_job_type_code = J.pk_job_type_code "
              		+ "    JOIN tbl_location L "
              		+ "    ON RN.fk_location_code = L.pk_location_code "
              		+ "    JOIN tbl_recruit_step S1 "
              		+ "    ON RN.fk_recruit_step_code_1 = S1.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S2 "
              		+ "    ON RN.fk_recruit_step_code_2 = S2.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S3 "
              		+ "    ON RN.fk_recruit_step_code_3 = S3.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S4 "
              		+ "    ON RN.fk_recruit_step_code_4 = S4.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S5 "
              		+ "    ON RN.fk_recruit_step_code_5 = S5.pk_recruit_step_code "
              		+ "    where RN.pk_recruit_notice_code = ?  ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, want_recruit_notice_no);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              while(rs.next()) {
            	  map.put("recruit_notice_name", rs.getString("recruit_notice_name"));
            	  map.put("yearsal", rs.getString("yearsal"));
            	  map.put("quail", rs.getString("quail"));
            	  map.put("woodae", rs.getString("woodae"));
            	  map.put("benefit", rs.getString("benefit"));
            	  map.put("recruit_finish_day", rs.getString("recruit_finish_day"));
            	  map.put("position_name", rs.getString("position_name"));
            	  map.put("company_name", rs.getString("company_name"));
            	  map.put("worktype_name", rs.getString("worktype_name"));
            	  map.put("job_type_name", rs.getString("job_type_name"));
            	  map.put("location_name", rs.getString("location_name"));
            	  map.put("recruit_step_name_1", rs.getString("recruit_step_name_1"));
            	  map.put("recruit_step_name_2", rs.getString("recruit_step_name_2"));
            	  map.put("recruit_step_name_3", rs.getString("recruit_step_name_3"));
            	  map.put("recruit_step_name_4", rs.getString("recruit_step_name_4"));
            	  map.put("recruit_step_name_5", rs.getString("recruit_step_name_5"));
              }
              
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		
		
		return map;
	}

	
	
	// 공고에 지원해주는 메소드 생성.
	@Override
	public int join_recruit_notice(MemberDTO member, String want_recruit_notice_no) {
		
		int result = 0;
		
		try {
			
			
			
			
			String sql = " select fk_user_id, fk_recruit_notice_code "
					+ " from tbl_join_recruit_notice "
					+ " where fk_user_id = ? and fk_recruit_notice_code = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPK_USER_ID());
			pstmt.setString(2, want_recruit_notice_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("[경고] 이미 지원한 공고입니다.");
				return result = -1;
			}
			
			
			sql = " insert into tbl_join_recruit_notice(pk_join_no,fk_user_id,fk_recruit_notice_code) values(seq_tbl_join_recruit.nextval,?,?)";
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPK_USER_ID());
			pstmt.setString(2, want_recruit_notice_no);
			int n_1 = pstmt.executeUpdate();
			
			if(n_1 == 1) {
				sql = " update tbl_recruit_notice set apply_no = apply_no + 1 "
					+ " where pk_recruit_notice_code = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, want_recruit_notice_no);
				int n_2 = pstmt.executeUpdate();
				
				if(n_2 == 1) {
					System.out.println("[안내] 성공적으로 지원하였습니다.");
					conn.commit();
					conn.setAutoCommit(true);
					return result = 1;
				}
				else {
					
					conn.rollback();
					conn.setAutoCommit(true);
					return result = 0;
				}
			}
			else {
				conn.rollback();
			}
				
			conn.setAutoCommit(true);
			
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();	
			}
		
		
		// TODO Auto-generated method stub
		return result;
		
	} // end of public int join_recruit_notice(MemberDTO member, String want_recruit_notice_no)

	// 모든 공고 목록을 보여주는 메소드 생성.
	@Override
	public void view_all_recruit_notice() {
		
		try {
			String sql = " SELECT RN.pk_recruit_notice_code "
					+ "     , case when apply_no > 0 then RN.recruit_notice_name||'['||RN.apply_no||']' else RN.recruit_notice_name end as recruit_notice_name "
              		+ "     , yearsal||'만원' as yearsal "
              		+ "     , quail "
              		+ "     , woodae "
              		+ "     , benefit "
              		+ "     , to_char(recruit_finish_day,'yyyy-mm-dd') as recruit_finish_day "
              		+ "     , P.position_name "
              		+ "     , C.company_name "
              		+ "     , W.worktype_name "
              		+ "     , J.job_type_name "
              		+ "     , L.location_name "
              		+ "     , S1.recruit_step_name as recruit_step_name_1 "
              		+ "     , S2.recruit_step_name as recruit_step_name_2 "
              		+ "     , S3.recruit_step_name as recruit_step_name_3 "
              		+ "     , S4.recruit_step_name as recruit_step_name_4 "
              		+ "     , S5.recruit_step_name as recruit_step_name_5 "
              		+ "    FROM tbl_recruit_notice RN JOIN tbl_position P "
              		+ "    ON RN.fk_position_code = P.pk_position_code "
              		+ "    JOIN tbl_company_detail C "
              		+ "    ON RN.fk_company_code = C.pk_company_code "
              		+ "    JOIN tbl_worktype W "
              		+ "    ON RN.fk_worktype_code = W.pk_worktype_code "
              		+ "    JOIN tbl_job_type J "
              		+ "    ON RN.fk_job_type_code = J.pk_job_type_code "
              		+ "    JOIN tbl_location L "
              		+ "    ON RN.fk_location_code = L.pk_location_code "
              		+ "    JOIN tbl_recruit_step S1 "
              		+ "    ON RN.fk_recruit_step_code_1 = S1.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S2 "
              		+ "    ON RN.fk_recruit_step_code_2 = S2.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S3 "
              		+ "    ON RN.fk_recruit_step_code_3 = S3.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S4 "
              		+ "    ON RN.fk_recruit_step_code_4 = S4.pk_recruit_step_code "
              		+ "    LEFT JOIN tbl_recruit_step S5 "
              		+ "    ON RN.fk_recruit_step_code_5 = S5.pk_recruit_step_code"
              		+ "    where RN.recruit_finish_day - sysdate > 0 ";
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {

				sb.append(rs.getString("pk_recruit_notice_code") + "\t");
				sb.append(rs.getString("company_name")+"\t");
				sb.append(rs.getString("recruit_notice_name")+"\t");
				sb.append(rs.getString("yearsal")+"\t");
				sb.append(rs.getString("quail")+"\t");
				sb.append(rs.getString("recruit_finish_day")+"\t");
				sb.append(rs.getString("position_name")+"\t");
				sb.append(rs.getString("worktype_name")+"\t");
				sb.append(rs.getString("job_type_name")+"\t");
				sb.append(rs.getString("location_name")+"\t");
				for(int i = 0; i < 5; i++) {
					if(rs.getString("recruit_step_name_" + (i+1))!=null && i == 0) {
						sb.append(rs.getString("recruit_step_name_" + (i+1)) );
					}
					else if(rs.getString("recruit_step_name_" + (i+1))!=null) {
						sb.append(" - "+rs.getString("recruit_step_name_" + (i+1)) );
					}
					else {
						sb.append("\n");
						break;
					}
					if(i==4) {
						sb.append("\n");
					}
				}
				
			}
			if(sb.toString().length() > 0) {
				System.out.println("---------------------------------------------------- 모든 공고 목록 ----------------------------------------------------------");
				System.out.println("  공고번호      기업명       공고명      연봉      자격요건     공고마감일     직책     채용형태      직무     근무지역     채용절차 ");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------");
				System.out.println(sb.toString());
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		
		
	}// end of public void view_all_recruit_notice()
	
	
	// 구직자 메뉴에서 모든 이력서 목록을 보여주는 메소드
		@Override
		public List<Map<String, String>> view_my_all_resume(MemberDTO member) {
			
			List<Map<String,String>> map_resume_list = new ArrayList<>();
			
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select R.pk_resume_code "
		         		+ "		, R.self_introduce "
		         		+ "		, R.army "
		         		+ "		, R.want_yearsal "
		         		+ "		, R.award_act_list "
		         		+ "		, R.resume_date "
		         		+ "		, C2.certifi_name "
		         		+ "		, R.job_type_code "
		         		+ "		from tbl_resume R "
		         		+ "     join tbl_member M "
		         		+ "		on R.pk_resume_code = M.resume_code "
		         		+ "		join tbl_mycertifi C1 "
		         		+ "		on R.my_certifi_no_1 = C1.my_certifi_no "
		         		+ "		join tbl_certifi C2 "
		         		+ "		on C1.fk_CERTIFI_code = C2.Pk_certifi_code "
		         		+ "		where M.PK_USER_ID = ? ";
		         
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, member.getPK_USER_ID());
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         while(rs.next()) {
		        	 
		        	 Map<String,String> map2 = new HashMap<>();
		        	 if(!(rs.getString("pk_resume_code") == null || rs.getString("self_introduce")==null || rs.getString("want_yearsal")==null || rs.getString("resume_date")==null)) {
		        		 
	        	 	 map2.put("pk_resume_code", rs.getString("pk_resume_code") );
	        	 	 map2.put("self_introduce", rs.getString("self_introduce") );
	        	 	 map2.put("want_yearsal", rs.getString("want_yearsal") );
	        	 	 map2.put("resume_date", rs.getString("resume_date") );
		        	 }
		        	 
	        	 	/*
	        	 	 if(rs.getString("my_certifi_no_1") != null) {
	        	 		 map2.put("my_certifi_no_1", rs.getString("my_certifi_no_1") ); }
	        	 	 if(rs.getString("my_certifi_no_2") != null) {
	        	 		 map2.put("my_certifi_no_2", rs.getString("my_certifi_no_2") ); }
	        	 	 if(rs.getString("my_certifi_no_3") != null) {
	        	 		 map2.put("my_certifi_no_3", rs.getString("my_certifi_no_3") ); }
	        	 	 if(rs.getString("my_certifi_no_4") != null) {
	        	 		 map2.put("my_certifi_no_4", rs.getString("my_certifi_no_4") ); }
	        	 	 if(rs.getString("my_certifi_no_5") != null) {
	        	 		 map2.put("my_certifi_no_5", rs.getString("my_certifi_no_5") ); }
	 
	        	 	 if(rs.getString("edu_info_1") != null) {
	        	 		 map2.put("edu_info_1", rs.getString("edu_info_1") ); }
	        	 	 if(rs.getString("edu_info_2") != null) {
	        	 		 map2.put("edu_info_2", rs.getString("edu_info_2") ); }
	        	 	 if(rs.getString("edu_info_3") != null) {
	        	 		 map2.put("edu_info_3", rs.getString("edu_info_3") ); }
	        	 	 
	        	 	 if(rs.getString("fk_career_info_code_1") != null) {
	        	 		 map2.put("fk_career_info_code_1", rs.getString("fk_career_info_code_1") ); }
	        	 	 if(rs.getString("fk_career_info_code_2") != null) {
	        	 		 map2.put("fk_career_info_code_2", rs.getString("fk_career_info_code_2") ); }
	        	 	 if(rs.getString("fk_career_info_code_3") != null) {
	        	 		 map2.put("fk_career_info_code_3", rs.getString("fk_career_info_code_3") ); }
	        	 	 if(rs.getString("fk_career_info_code_4") != null) {
	        	 		 map2.put("fk_career_info_code_4", rs.getString("fk_career_info_code_4") ); }
	        	 	 if(rs.getString("fk_career_info_code_5") != null) {
	        	 		 map2.put("fk_career_info_code_5", rs.getString("fk_career_info_code_5") ); }
	        	 	 */
	        
		        	 map_resume_list.add(map2);
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return map_resume_list;
			
		}// end of public List<Map<String, String>> view_my_all_resume()

	
	
	
	// 이력서 번호 선택시 이력서 상세 정보를 보여주는 메소드
		@Override
		public void view_resume_detail(String choice_resume_no) {
			
			try {
				String sql =  " WITH R AS "
			         		+ " ( "
			         		+ " select * from tbl_resume "
			         		+ " ) "
			         		+ " select R.pk_resume_code "
			         		+ "    , R.self_introduce "
			         		+ "    , R.army "
			         		+ "    , R.want_yearsal "
			         		+ "    , R.award_act_list "
			         		+ "    , to_char(R.resume_date,'yyyy-mm-dd') as resume_date_2 "
			         		+ "     , C2.certifi_name "
			         		+ " from R "
			         		+ " join tbl_mycertifi C1 "
			         		+ " on R.my_certifi_no_1 = C1.my_certifi_no "
			         		+ " join tbl_certifi C2 "
			         		+ " on C1.fk_CERTIFI_code = C2.Pk_certifi_code "
			         		+ " join tbl_member M "
			         		+ " on R.pk_resume_code = M.resume_code "
			         		+ " where R.pk_resume_code = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, choice_resume_no);
				rs = pstmt.executeQuery();
				
				StringBuilder sb = new StringBuilder();
				
				
					while(rs.next()) {
						sb.append("\n["+rs.getString("pk_resume_code") + "번 이력서]\n");
						sb.append("▷ 이력서제목 : "+rs.getString("self_introduce") + "\n");
						sb.append("▷ 병역정보 : "+rs.getString("army") + "\n");
						sb.append("▷ 희망연봉 : "+rs.getString("want_yearsal") + "\n");
						sb.append("▷ 수상내역 및 활동내역 : "+rs.getString("award_act_list") + "\n");
						sb.append("▷ 작성날짜 : "+rs.getString("resume_date_2") + "\n");
						sb.append("▷ 취득한 자격증 : "+rs.getString("certifi_name") + "\n");
					}
					
					
					if(sb.toString().length()==0) {
						System.out.println("[경고] 입력하신 번호에 해당하는 이력서가 존재하지 않습니다.");
					}
					else {

						System.out.println(sb.toString());
					}
				
				
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();	
			}
			
		         		
			
		}// end of public void view_resume_detail()

		
		// 직무형태 목록을 띄워주는 메소드
		@Override
		public List<Map<String, String>> view_job_type() {

			List<Map<String,String>> map_List = new ArrayList<>();
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select job_type_name "
	                         + " from tbl_job_type " ;
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              int i = 0;
	              while(rs.next()) {
	                 Map<String,String> map = new HashMap<>();
	                 map.put("job_type_name", rs.getString("job_type_name")); 
	                 
	                 map_List.add(i, map);
	                 i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return map_List;
			
			
		}// end of public List<Map<String, String>> view_job_type()

		
		

		// 직무형태 번호를 받아서 목록에 따른 직무코드를 알려주는 메소드
		@Override
		public String search_jobtype_code(String get_jobtype_name) {

			String get_job_type = null;
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_job_type_code "
	                         + " from tbl_job_type "
	                         + " where job_type_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, get_jobtype_name);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              if(rs.next()) {
	                 get_job_type =  rs.getString("pk_job_type_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return get_job_type;
			
			
		}// end of public String search_jobtype_code(String get_jobtype_name)

		
		
		
		// 자격증 목록을 띄워주는 메소드
		@Override
		public List<Map<String, String>> view_certifi_type() {

			List<Map<String,String>> map_List2 = new ArrayList<>();
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select certifi_name "
	                         + " from tbl_certifi " ;
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              int i = 0;
	              while(rs.next()) {
	                 Map<String,String> map = new HashMap<>();
	                 map.put("job_certifi_name", rs.getString("certifi_name")); 
	                 
	                 map_List2.add(i, map);
	                 i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return map_List2;
			
		}// end of public List<Map<String, String>> view_certifi_type()

		
		
		
		// 자격증 번호를 받아서 목록에 따른 자격증코드를 알려주는 메소드
		@Override
		public String search_certifi_code(String get_certifi_name) {

			String get_certifi_type = null;
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_certifi_code  "
	                         + " from tbl_certifi "
	                         + " where certifi_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, get_certifi_name);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              if(rs.next()) {
	                 get_certifi_type =  rs.getString("pk_certifi_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return get_certifi_type;
			
		}// end of public String search_certifi_code(String get_certifi_name)

		
		
		// 병역정보 목록을 띄워주는 메소드
		@Override
		public List<Map<String, String>> view_army_type() {

			List<Map<String,String>> map_List6 = new ArrayList<>();
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select army "
	                         + " from tbl_resume " ;
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              int i = 0;
	              while(rs.next()) {
	                 Map<String,String> map = new HashMap<>();
	                 map.put("army_name", rs.getString("army")); 
	                 
	                 map_List6.add(i, map);
	                 i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return map_List6;
			
			
		}// end of public List<Map<String, String>> view_army_type()

		
		
		
		 // 병역정보 번호를 받아서 목록에 따른 병역정보 코드를 알려주는 메소드
		@Override
		public String search_army_code(String get_army_name) {
			
			String get_army_type = null;
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " WITH R AS "
			         		+ " ( "
			         		+ " select * from tbl_resume "
			         		+ " ) "
			         		+ " select R.army "
			         		+ " from R "
			         		+ " join tbl_member M "
			         		+ " on R.pk_resume_code = M.resume_code "
			         		+ " where R.pk_resume_code = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, get_army_name);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              if(rs.next()) {
	                 get_army_type =  rs.getString("pk_army_type_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return get_army_type;
			
			
			

		}// end of public String search_army_code(String get_army_name)

		
		 // 선택한 이력서를 지워주는 메소드 생성
		@Override
		public int delete_resume(String choice_resume_no, MemberDTO member) {
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " update tbl_member set resume_code = '' "
	              		+ "where resume_code = ? and pk_user_id = ? ";
	              conn.setAutoCommit(false);
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, choice_resume_no);
	              pstmt.setString(2, member.getPK_USER_ID());
	              int n_1 = pstmt.executeUpdate(); // sql문 실행
	              
	              if(n_1 == 1) {
	            	  
	            	  
	            	  sql = " delete tbl_user_resume "
	            		  		+ "    where fk_resume_code = ? ";
	            	  pstmt = conn.prepareStatement(sql);
	            	  pstmt.setString(1, choice_resume_no);
	            	  int n_3 = pstmt.executeUpdate();
	            	  
	            	  sql = " delete tbl_resume "
	            	  		+ " where pk_resume_code = ? ";
	            	  pstmt = conn.prepareStatement(sql);
	            	  pstmt.setString(1, choice_resume_no);
	            	  int n_2 = pstmt.executeUpdate();
	            	  
	            	  if(n_2 == 1 && n_3 == 1) {
	            		  
	            		  
	            		  do {
	            			  
	            			  System.out.println(">>> 정말로 이력서를 삭제하시겠습니까? [Y/N] <<<");
	            			  String yn = sc.nextLine();
	            			  
	            			  if("y".equalsIgnoreCase(yn)) {
	            				  conn.commit();
	            				  conn.setAutoCommit(true);
	            				  return result = 1;
	            			  }
	            			  else if("n".equalsIgnoreCase(yn)){
	            				  conn.rollback();
	            				  conn.setAutoCommit(true);
	            				  return result = 0;
	            			  }
	            			  else {
	            				  System.out.println("[경고] 반드시 Y/N 중에서만 입력하세요 ");
	            			  }
	            			  
	            		  }while(true);
	            		  
	            		  
	            	  }
	            	  else {
	            		  conn.rollback();
	            		  conn.setAutoCommit(true);
	            		  return result = -1;
	            	  }
	            	  
	              }
	              else {
	            	  conn.rollback();
	            	  conn.setAutoCommit(true);
	            	  return result = -1;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return result;
		} // end of public int delete_resume(String choice_resume_no, MemberDTO member)


		
		// 원래 있던 이력서를 가지고와서 보여주는 메소드 생성.
		@Override
		public Map<String, String> get_resume(MemberDTO member) {

			
			Map<String,String> map = new HashMap<>();
			
			
			try {
				String sql =  " WITH R AS "
			         		+ " ( "
			         		+ " select * from tbl_resume "
			         		+ " ) "
			         		+ " select R.pk_resume_code "
			         		+ "    , R.self_introduce "
			         		+ "    , R.army "
			         		+ "    , R.want_yearsal "
			         		+ "    , R.award_act_list "
			         		+ "    , to_char(R.resume_date,'yyyy-mm-dd') as resume_date_2 "
			         		+ "     , C2.certifi_name "
			         		+ "    , R.my_certifi_no_1 "
			         		+ " from R "
			         		+ " join tbl_mycertifi C1 "
			         		+ " on R.my_certifi_no_1 = C1.my_certifi_no "
			         		+ " join tbl_certifi C2 "
			         		+ " on C1.fk_certifi_code = C2.Pk_certifi_code "
			         		+ " join tbl_member M "
			         		+ " on R.pk_resume_code = M.resume_code "
			         		+ " where M.pk_user_id = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getPK_USER_ID());
				rs = pstmt.executeQuery();
				
				
				
				
					while(rs.next()) {
						
						map.put("pk_resume_code", rs.getString("pk_resume_code"));
						map.put("self_introduce", rs.getString("self_introduce"));
						map.put("army", rs.getString("army"));
						map.put("want_yearsal", rs.getString("want_yearsal"));
						map.put("award_act_list", rs.getString("award_act_list"));
						map.put("certifi_name", rs.getString("certifi_name"));
						map.put("my_certifi_no_1", rs.getString("my_certifi_no_1"));
						
					}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();	
			}
			
			
			
			return map;
		}

		
		// 모든 자격증 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_certifi_type_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select certifi_name "
	              		+ " from tbl_certifi ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("certifi_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		}

		// 입력한 자격증명과 일치하는 코드를 반환.
		@Override
		public String get_certifi_code(String string) {
			String certifi_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_certifi_code "
	              		+ " from tbl_certifi "
	              		+ " where certifi_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  certifi_code = rs.getString("pk_certifi_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return certifi_code;
		}


		 // 수정한 정보들로 이력서를 바꾸어 주는 메소드 생성.
		@Override
		public int update_certifi(MemberDTO member,Map<String, String> paraMap) {
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              
	              
	              String sql = " update tbl_mycertifi set fk_certifi_code = ?"
	              		     + " where my_certifi_no = ? and fk_user_id = ? ";
	              
	              conn.setAutoCommit(false);
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, paraMap.get("certifi_code"));
	              pstmt.setString(2, paraMap.get("my_certifi_no_1"));
	              pstmt.setString(3, member.getPK_USER_ID());
	              int n_1 = pstmt.executeUpdate();
	              
	              if(n_1 == 1) {
	            	  sql = " update tbl_resume "
	  	              		+ "    set self_introduce = ? "
	  	              		+ ",   army = ? "
	  	              		+ ",   want_yearsal = ? "
	  	              		+ ",   award_act_list = ? "
	  	              		+ ",   resume_date = sysdate "
	  	              		+ " where pk_resume_code = ? ";
	  	              
	  	              
	  	              
	  	              pstmt = conn.prepareStatement(sql);
	  	              pstmt.setString(1, paraMap.get("change_self_introduce"));
	  	              pstmt.setString(2, paraMap.get("change_army"));
	  	              pstmt.setString(3, paraMap.get("change_want_yearsal"));
	  	              pstmt.setString(4, paraMap.get("change_award_act_list"));
	  	              pstmt.setString(5, paraMap.get("pk_resume_code"));
	  	              
	  	              int n = pstmt.executeUpdate(); // sql문 실행
	  	              
	  	              if(n == 1) {
	  	            	  do {
	  		            	  System.out.print(">>> 정말로 수정하시겠습니까? [Y/N] : ");
	  		            	  String yn = sc.nextLine();
	  		            	  
	  		            	  if("y".equalsIgnoreCase(yn)) {
	  		            		  conn.commit();
	  		            		  conn.setAutoCommit(true);
	  		            		  return result = 1;
	  		            	  }
	  		            	  else if("n".equalsIgnoreCase(yn)) {
	  		            		  conn.rollback();
	  		            		  conn.setAutoCommit(true);
	  		            		  return result = 0;
	  		            	  }
	  		            	  else {
	  		            		  conn.rollback();
	  		            		  conn.setAutoCommit(true);
	  		            		  return result = -1;
	  		            	  }
	  		            	  
	  		              }while(true);
	  	              }
	  	              else {
	  	            	  conn.rollback();
	  	            	  conn.setAutoCommit(true);
	  	            	  return result = -1;
	  	              }
	              }
	              else {
	            	  conn.rollback();
	            	  conn.setAutoCommit(true);
	            	  return result = -1;
	              }
	              
	              
	              
	              
	              
	              
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			
			return result;
		}

		
		// 입력한 정보를 토대로 이력서를 작성하는 코드 생성
		@Override
		public void insert_resume(MemberDTO member,Map<String, String> map) {
			
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " insert into tbl_mycertifi(my_certifi_no,fk_certifi_code, fk_user_id) values (seq_my_certifi_no.nextval, ? ,?) ";
	              
	              conn.setAutoCommit(false);
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, map.get("get_certifi_code"));
	              pstmt.setString(2, member.getPK_USER_ID());
	              int n_1 = pstmt.executeUpdate();
	              
	              if(n_1==1) {
	            	  
	            	  String my_certifi_no = "";
	            	  sql = " select my_certifi_no "
	            	      + " from tbl_mycertifi"
	            	      + " where fk_user_id = ? and fk_certifi_code = ? ";
	            	  pstmt = conn.prepareStatement(sql);
	            	  pstmt.setString(1, member.getPK_USER_ID());
	            	  pstmt.setString(2, map.get("get_certifi_code"));
	            	  rs = pstmt.executeQuery();
	            	  if(rs.next()) {
	            		  my_certifi_no = rs.getString("my_certifi_no");
	            	  }
	            	  
	            	  sql = "insert into tbl_resume(pk_resume_code,self_introduce, army, want_yearsal, my_certifi_no_1, award_act_list, job_type_code, edu_info_1) "
	            	  		+ "values(SEQ_PK_RESUME_CODE.nextval,?,?,?,?,?,?,'2')";
	            	  pstmt = conn.prepareStatement(sql);
	            	  pstmt.setString(1, map.get("resume_name"));
	            	  pstmt.setString(2, map.get("get_army_name"));
	            	  pstmt.setString(3, map.get("want_yearsal"));
	            	  pstmt.setString(4, my_certifi_no);
	            	  pstmt.setString(5, map.get("award_act_list"));
	            	  pstmt.setString(6, map.get("get_job_type_code"));
	            	  int n_2 = pstmt.executeUpdate();
	            	  
	            	  if(n_2 == 1) {
	            		  
	            		  String fk_resume_code = "";
	            		  sql = " select pk_resume_code "
	            		  		+ "  from tbl_resume R JOIN tbl_member M "
	            		  		+ "  on R.pk_resume_code = M.resume_code "
	            		  		+ "  where M.pk_user_id = ? ";
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, member.getPK_USER_ID());
	            		  rs = pstmt.executeQuery();
	            		  if(rs.next()) {
	            			  fk_resume_code = rs.getString("pk_resume_code");
	            			  }
	            		  
	            		  sql = " insert into tbl_user_resume(pk_resume_no,fk_user_id,fk_resume_code) values(seq_user_resume_no.nextval,?,?) ";
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, member.getPK_USER_ID());
	            		  pstmt.setString(2, fk_resume_code);
	            		  int n_3 = pstmt.executeUpdate();
	            		  
	            		  sql = " update tbl_member set resume_code = ? "
	            		  	  + " where pk_user_id = ? ";
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, fk_resume_code);
	            		  pstmt.setString(2, member.getPK_USER_ID());
	            		  int n_4 = pstmt.executeUpdate();
	            		  
	            		  
	            		  
	            		  if(n_3 == 1 && n_4 == 1) {
	            			  conn.commit();
	            			  conn.setAutoCommit(true);
	            		  }
	            		  else {
	            			  conn.rollback();
	            			  conn.setAutoCommit(true);
	            		  }
	            		  
	            	  }
	            	  else {
	            		  conn.rollback();
	            		  conn.setAutoCommit(true);
	            	  }
	              }
	              else {
	            	  conn.rollback();
	            	  conn.setAutoCommit(true);
	            	  System.out.println("[경고] 이력서 등록에 실패했습니다.");
	              }
	              
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
		}



		
		
		
		
	
	
	
	
}
