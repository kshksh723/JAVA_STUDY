<<<<<<< HEAD
package company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import company.domain.CompanyDTO;
import dbconnection.MyDBConnection;

public class CompanyDAO_imple implements CompanyDAO {

	Connection conn = MyDBConnection.getConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	
	private void close() {
		try {
			if(rs != null) 	  {rs.close();	rs = null;}
			if(pstmt != null) {pstmt.close();	pstmt = null;}
			// conn은 프로그램을 종료할때만 끊어야한다. 
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()----------------------------
	
	
	// 구인회사가 입력한 아이디, 비밀번호에 일치하는 정보가 있는지 확인하기위한 select 메소드
	@Override
	public CompanyDTO login(Map<String, String> paraMap) {
		
		CompanyDTO cdto = new CompanyDTO();
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select pk_fk_company_code,company_name, company_passwd"
					   + " from tbl_company_login "
					   + " where pk_fk_company_code = ? and company_passwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("PK_COMPANY_CODE"));
			pstmt.setString(2, paraMap.get("COMPANY_PASSWD"));
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) {
				cdto.setPK_COMPANY_CODE(rs.getString("pk_fk_company_code"));
				cdto.setCOMPANY_PASSWD(rs.getString("company_passwd"));
				cdto.setCOMPANY_NAME(rs.getString("company_name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		return cdto;
	} // end of public CompanyDTO login(Map<String, String> paraMap)

	
	// 이메일과 비밀번호가 일치한다면 사업자등록번호를 보여주기.
	@Override
	public String find_com_id(Map<String, String> find_com_id) {
		
		String company_id = null;
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select pk_company_code "
					   + " from tbl_company_detail "
					   + " where company_email = ? and company_passwd = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find_com_id.get("com_email"));
			pstmt.setString(2, find_com_id.get("com_passwd"));
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) {
				company_id = rs.getString("pk_company_code");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		
		
		
		
		return company_id;
	} // end of public String find_com_id(Map<String, String> find_com_id)

	
	// 입력한 정보가 맞으면 비밀번호를 변경할 수 있게 해주는 메소드 생성.
	@Override
	public int find_com_passwd(Map<String, String> find_com_passwd) {
		
		int result = 0;
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select pk_company_code "
					   + " from tbl_company_detail "
					   + " where company_email = ? and pk_company_code = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find_com_passwd.get("com_email"));
			pstmt.setString(2, find_com_passwd.get("com_id"));
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) { // 위에서 입력한 email 과 아이디가 일치하는 값이 있다면
				
				System.out.println("-----------[비밀번호 변경]-----------");
				
				System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
				String change_passwd = "";
				do {
					CompanyDTO cmdto = new CompanyDTO();
					System.out.print("▶ 변경할 비밀번호를 입력하세요 : ");
					change_passwd = sc.nextLine();
					cmdto.setCOMPANY_PASSWD(change_passwd); // 비밀번호 유효성 검사 
					if(!(cmdto.getCOMPANY_PASSWD() == null || cmdto.getCOMPANY_PASSWD().isBlank())) {
						break;
					}
					else {
						System.out.println("[경고] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
					}
				}while(true); // end of do_while passwd
				
				sql = " update tbl_company_detail set company_passwd = ? "
					+ " where company_email = ? and pk_company_code = ? ";
				
				conn.setAutoCommit(false);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, change_passwd);
				pstmt.setString(2, find_com_passwd.get("com_email"));
				pstmt.setString(3, find_com_passwd.get("com_id"));
				
				int n_1 = pstmt.executeUpdate();
				
				if(n_1 == 1) {
					
				sql = " update tbl_company_login set COMPANY_PASSWD = ? " 
					+ " where PK_FK_COMPANY_CODE = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, change_passwd);
				pstmt.setString(2, find_com_passwd.get("com_id"));
				int n_2 = pstmt.executeUpdate();
				
					String yn = "";
					if(n_2 == 1){
						System.out.print("▶ 비밀번호를 변경 하시겠습니까? [Y/N] : ");
						yn = sc.nextLine();

						do {
							if("y".equalsIgnoreCase(yn)) {
								conn.commit();
								return result = 1;
							}
							else if("n".equalsIgnoreCase(yn)) {
								conn.rollback();
								return result = 0;
							}
							else {
								System.out.println("[경고] 반드시 Y 혹은 N 중에서 입력하세요");
							}
						}while(!("y".equalsIgnoreCase(yn)||"n".equalsIgnoreCase(yn)));
					}
					
					else {
						result = -1;
					}
					
				}
				else {
					result = -1;
				}
				
			}
			else {
				result = -1;
			}
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		return result;
	} // end of public int find_com_passwd(Map<String, String> find_com_passwd)

	
	// *** 아이디 중복 검사 메소드 *** //
		@Override
		public int check_com_id(String com_id) {
			int result = 0; // 같은 아이디를 사용하는 사람이 있는지 없는지 체크하게 해주는 변수 
		      
		      try {
		         conn = MyDBConnection.getConn();
		         String sql = " select * "
		                  + " from tbl_company_detail "
		                  + " where pk_company_code = ? ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, com_id);
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
		}
		
		
		// *** 입력한 구인회사 회원가입 정보를 토대로 회원가입 할 수 있게 해주는 메소드 생성*** //
		@Override
		public int join_company(Map<String, String> paraMap) {

			  int result_1 = 0; 
		      int result_2 = 0; 
		      
		      try {
		         conn = MyDBConnection.getConn();
		         
		         // tbl_company_detail 에 insert 하기위한 sql문 //
		         String sql = "  insert into tbl_company_detail(pk_company_code, company_passwd, company_name, company_email, company_tel, sales, "
		         		    + "  NUMBER_OF_EMPLOYEE, HOMEPAGE, ESTABLISHMENT_DATE, BOSS_NAME , fk_buss_type_code, Fk_company_size_code, company_address ) "
		                    + "  values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, paraMap.get("com_id"));
		         pstmt.setString(2, paraMap.get("com_passwd"));
		         pstmt.setString(3, paraMap.get("com_name"));
		         pstmt.setString(4, paraMap.get("com_email"));
		         pstmt.setString(5, paraMap.get("com_tel"));
		         pstmt.setString(6, paraMap.get("com_sales"));
		         pstmt.setInt(7, Integer.parseInt(paraMap.get("com_employee_no")));
		         pstmt.setString(8, paraMap.get("com_homepage"));
		         pstmt.setString(9, paraMap.get("com_establish"));
		         pstmt.setString(10, paraMap.get("com_boss_name"));
		         pstmt.setString(11, paraMap.get("get_buss_type_code"));
		         pstmt.setString(12, paraMap.get("get_com_size_code"));
		         pstmt.setString(13, paraMap.get("com_address"));
		         
		         conn.setAutoCommit(false); // auto commit 해제 
		         
		         result_1 = pstmt.executeUpdate();
		         
		         // *** 두 값 비교하기 위해서는 company_login 테이블에도 insert 해야함 *** //
		         sql = " insert into tbl_company_login(Pk_Fk_company_code, company_passwd, company_name) "
		         	 + " values(?,?,?) " ;
		         
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, paraMap.get("com_id"));
		         pstmt.setString(2, paraMap.get("com_passwd"));
		         pstmt.setString(3, paraMap.get("com_name"));
		         
		         result_2 = pstmt.executeUpdate();
		         
		         
		         if(result_1 == 1 && result_2 == 1) {
		        	 
		        	 String yn = "";
		        	 do {
			        	 System.out.print("\n▶ 위의 회원정보로 회원가입 하시겠습니까? [Y/N] : ");
			        	 
			        	 yn = sc.nextLine();
			        	 
			        	 if("y".equalsIgnoreCase(yn)){
			        		 result_1 = 1;
			        		 conn.commit(); 
			        	 }
			        	 else if("n".equalsIgnoreCase(yn)) {
			        		 result_1 = 0;
			        		 conn.rollback();
			        	 }
			        	 else {
			        		 System.out.println("<경고> 입력값은 Y 또는 N으로 입력해주세요.");
			        	 }
		        	 } while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
			        	 	 
		        	 conn.setAutoCommit(true);
		        	 
		         }

		      } catch (SQLException e) {
				  e.printStackTrace();
				  result_1 = -1;
				  
			  } finally {
					close();
			  }// end of finally
				
			  return result_1; // 성공이면 return 값이 1
			
		}// end of public int join_company(Map<String, String> paraMap)
	
	
		
		// *** 모든 업종을 보여주는 메소드 *** // 
		@Override
		public List<Map<String, String>> view_all_buss_type() {
			
			List<Map<String,String>> map_List = new ArrayList<>();
			
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select buss_type_name "
		                    + " from tbl_buss_type  ";
		         
		         pstmt = conn.prepareStatement(sql);
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         int i = 0;
		         while(rs.next()) {
		        	 Map<String,String> map = new HashMap<>();
		        	 map.put("buss_type_name", rs.getString("buss_type_name"));
		        	 
		        	 map_List.add(i, map);
		        	 i++;
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return map_List;
			
		} // end of public void view_all_buss_type()
		
		
		// *** 검색한 번호를 토대로 업종 코드를 알려주는 메소드 생성 (select) *** //
		@Override
		public String search_buss_type_code(String get_buss_name) {
			
			String get_buss_type_code = null;
			
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select pk_buss_type_code "
		                    + " from tbl_buss_type "
		                    + " where buss_type_name = ? ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, get_buss_name);
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         if(rs.next()) {
		        	 get_buss_type_code =  rs.getString("pk_buss_type_code" );
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return get_buss_type_code;
			
		}// end of public String search_buss_type_code(String get_buss_name)
		
		
		//*** 모든 회사 규모 목록 보여주는 메소드 *** //
		@Override
		public List<Map<String, String>> view_all_company_size() {
			  List<Map<String,String>> map_List = new ArrayList<>();
		         
		         try {
		               conn = MyDBConnection.getConn();
		               String sql = " select com_size " // 회사 규모명 대기업,중소 이런거
		                          + " from tbl_company_size  ";
		               
		               pstmt = conn.prepareStatement(sql);
		               rs = pstmt.executeQuery(); // sql문 실행
		               
		               int i = 0;
		               while(rs.next()) {
		                  Map<String,String> map = new HashMap<>();
		                  map.put("com_size", rs.getString("com_size")); 
		                  
		                  map_List.add(i, map);
		                  i++;
		               }
		               
		            } catch (SQLException e) {
		               e.printStackTrace();
		            } finally {
		               close();   
		            }
		         
		         return map_List;

		}// end of public List<Map<String, String>> view_all_company_size()
		
		
		// *** 검색한 번호를 토대로 회사 규모 코드를 알려주는 메소드 생성(select) *** //
		@Override
		public String search_PK_COM_SIZE_CODE(String get_com_size_name) {
			String get_com_size = null;
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_com_size_code "
	                       + " from tbl_company_size "
	                       + " where com_size = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, get_com_size_name);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              if(rs.next()) {
	                 get_com_size =  rs.getString("pk_com_size_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return get_com_size;

		}// end of public String search_PK_COM_SIZE_CODE(String get_com_size_name)


		// 내가 올린 모든 공고 목록을 보여주는 메소드 생성. 
		@Override
		public int view_my_recruit_notice(CompanyDTO company) {
			
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_recruit_notice_code, recruit_notice_name, yearsal||'만원' as yearsal , recruit_start_day "
	              		+ "    from tbl_recruit_notice "
	              		+ "    where fk_company_code = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, company.getPK_COMPANY_CODE());
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              StringBuilder sb = new StringBuilder();
	              
	              while(rs.next()) {
	            	  sb.append(rs.getString("pk_recruit_notice_code") + "\t");
	            	  sb.append(rs.getString("recruit_notice_name") + "\t");
	            	  sb.append(rs.getString("yearsal") + "\t");
	            	  sb.append(rs.getString("recruit_start_day") + "\n");
	              }
	              
	              if(sb.toString().isBlank()) {
	            	  System.out.println("[안내] 등록한 공고가 존재하지 않습니다.");
	            	  return result = 0;
	              }
	              else {
	            	  System.out.println("──────────────────────[" + company.getCOMPANY_NAME() + "] 에서 등록한 구인공고──────────────────────\n"
	            	  		           + "     공고번호       공고명                       연봉                    공고등록날짜 \n"
	            	  		           + "──────────────────────────────────────────────────────────────────────────────────────────────");
	            	  System.out.println(sb.toString());
	            	  return result = 1;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			return result;
		}

		// 입력한 공고번호에 일치하는 공고 상세정보 출력하기
		@Override
		public Map<String, String> view_one_recruit_notice(CompanyDTO company,String change_recruit_notice_no) {
			
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
	              		+ "    where RN.pk_recruit_notice_code = ? and RN.fk_company_code = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, change_recruit_notice_no);
	              pstmt.setString(2, company.getPK_COMPANY_CODE());
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

		
		// 모든 직무 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_job_type_list() {
			
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select job_type_name "
	              		+ "    from tbl_job_type ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("job_type_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		}

		// 입력한 직무명과 일치하는 코드를 반환.
		@Override
		public String get_job_type_code(String string) {
			
			String job_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_job_type_code\r\n"
	              		+ "    from tbl_job_type\r\n"
	              		+ "    where job_type_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  job_code = rs.getString("pk_job_type_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return job_code;
		} // end of public String get_job_type_code(String string)

		
		
		// 모든 직책 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_position_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select position_name "
	              		+ "    from tbl_position ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("position_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		} // end of public List<String> view_all_position_list()

		
		// 입력한 직책명과 일치하는 코드를 반환.
		@Override
		public String get_position_code(String string) {
			String position_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_position_code "
	              		+ "    from tbl_position "
	              		+ "    where position_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  position_code = rs.getString("pk_position_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return position_code;
		}// end of public String get_position_code(String string)

		
		
		// 모든 채용형태 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_worktype_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select worktype_name "
	              		+ "    from tbl_worktype ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("worktype_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		} // end of public List<String> view_all_worktype_list()

		
		 // 입력한 채용형태명과 일치하는 코드를 반환.
		@Override
		public String get_worktype_code(String string) {
			String worktype_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_worktype_code "
	              		+ "    from tbl_worktype "
	              		+ "    where WORKTYPE_NAME = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  worktype_code = rs.getString("pk_worktype_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return worktype_code;
		} //end of public String get_worktype_code(String string)

		
		
		
		 // 모든 모집전형 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_recruit_step_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select recruit_step_name "
	              		+ "    from tbl_recruit_step ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("recruit_step_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		}

		
		 // 맨 처음 엔터를 눌렀을 때 모집전형 코드를 가지고오는 메소드 생성
		@Override
		public Map<String, String> get_recruit_step_code(Map<String, String> map) {
			
			Map<String,String> map_1 = new HashMap<>();
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_recruit_step_code "
	              		+ "    from tbl_recruit_step "
	              		+ "    where recruit_step_name = ? ";
	              
	              
	              
	              for(int i = 0; i < 5; i++) {
	            	  if(map.get("recruit_step_name_" + (i+1))!=null) {
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, map.get( "recruit_step_name_" + (i+1) ));
	            		  rs = pstmt.executeQuery(); // sql문 실행
	            		  if(rs.next()) {
	            			  map_1.put("recruit_step_code_" + (i+1), rs.getString("pk_recruit_step_code"));
	            		  }
	            	  }
	            	  else {
	            		  break;
	            	  }
	              }
	              
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return map_1;
		}

		
		 // 입력한 전형명에 일치하는 코드를 반환.
		@Override
		public String get_recruit_step_code_2(String recruit_step_name) {
			String recruit_step_code = "";
			
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_recruit_step_code "
	              		+ "    from tbl_recruit_step "
	              		+ "    where recruit_step_name = ? ";
	              
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1 , recruit_step_name);
	            		  rs = pstmt.executeQuery(); // sql문 실행
	            		 
	              if(rs.next()) {
	            	  recruit_step_code = rs.getString("pk_recruit_step_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			return recruit_step_code;
		} // end ofpublic String get_recruit_step_code_2(String recruit_step_name)

		
		
		 // 위에 담긴 map값으로 모집공고를 업데이트 해주는 메소드 생성.
		@Override
		public int change_recruit_notice(String change_recruit_notice_no, Map<String, String> map) {
			
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              conn.setAutoCommit(false);
	              
	            	  String sql = " update tbl_recruit_notice set "
	  	              		+ "     recruit_notice_name = ? "
	  	              		+ "    ,yearsal = ? "
	  	              		+ "    ,quail = ? "
	  	              		+ "    ,woodae = ? "
	  	              		+ "    ,benefit = ? "
	  	              		+ "    ,fk_position_code = ? "
	  	              		+ "    ,fk_worktype_code = ? "
	  	              		+ "    ,fk_job_type_code = ? "
	  	              		+ "    ,fk_location_code = ? "
	  	              		+ "     where pk_recruit_notice_code = ? ";
	              
	            	  	
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, map.get("change_recruit_notice_name") );
	            		  pstmt.setString(2, map.get("change_yearsal"));
	            		  pstmt.setString(3, map.get("change_quail"));
	            		  pstmt.setString(4, map.get("change_woodae"));
	            		  pstmt.setString(5, map.get("change_benefit"));
	            		  pstmt.setString(6, map.get("position_code"));
	            		  pstmt.setString(7, map.get("worktype_code"));
	            		  pstmt.setString(8, map.get("job_type_code"));
	            		  pstmt.setString(9, map.get("address_code"));
	            		  pstmt.setString(10, change_recruit_notice_no);
	            		  int n_1 = pstmt.executeUpdate();
	            		  
	            		  
	            		  if(n_1 == 1) {
	            			  
	            			  
	            			  int cnt = 0;
	            			  for(int i = 0; i < 5; i ++) {
	            				  if(map.get("recruit_step_code_" + (i+1))!=null) {
	            					  sql = " update tbl_recruit_notice set fk_recruit_step_code_" + (i+1) +" = ?"
	            					  	  + " where pk_recruit_notice_code = ? ";
	            					  pstmt = conn.prepareStatement(sql);
	            					  pstmt.setString(1, map.get("recruit_step_code_"+(i+1)));
	            					  pstmt.setString(2, change_recruit_notice_no);
	            					  int n_2 = pstmt.executeUpdate();
	            					  if(n_2 == 1) {
	            						  cnt++;
	            					  }
	            				  }
	            				  else {
	            					  sql = " update tbl_recruit_notice set fk_recruit_step_code_" + (i+1) +" = '' "
		            					  	  + " where pk_recruit_notice_code = ? ";
		            					  pstmt = conn.prepareStatement(sql);
		            					  pstmt.setString(1, change_recruit_notice_no);
		            					  int n_2 = pstmt.executeUpdate();
		            					  if(n_2 == 1) {
		            						  cnt++;
		            					  }
	            				  }
	            				  
	            			  }
	            			  
	            			  if(cnt==5) {
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
	            			  }
	            			  else {
	            				  result = -1;
	            			  }
	            			  
	            		  }
	            		  else {
	            			  result = -1;
	            		  }
	            		 
	              
	            		  
	            	conn.setAutoCommit(true);	  
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			return result;
		} // end of public int change_recruit_notice(String change_recruit_notice_no, Map<String, String> map)

		
		
		//선택한 모집공고를 삭제하는 메소드 생성
		@Override
		public int delete_recruit_notice(CompanyDTO company, String delete_recruit_no) {
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " delete tbl_join_recruit_notice"
	              		+ "      where fk_recruit_notice_code = ? ";
	              		  
	              
	              		  conn.setAutoCommit(false);
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1 , delete_recruit_no );
	            		  int n = pstmt.executeUpdate();
	            		 
	            	if(n != 0) {
	            		sql = " delete tbl_recruit_notice "
	            			+ " where pk_recruit_notice_code = ? and fk_company_code = ? ";
	            		pstmt = conn.prepareStatement(sql);
	            		pstmt.setString(1, delete_recruit_no);
	            		pstmt.setString(2, company.getPK_COMPANY_CODE());
	            		int n_2 = pstmt.executeUpdate();
	            		
	            		if(n_2 == 1) {
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
	            		conn.setAutoCommit(true);
	            		return result = 0;
	            		
	            	}
 
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return result;
		}// end of public int delete_recruit_notice(CompanyDTO company, String delete_recruit_no)

		
		
		// 내 회사의 상세정보를 보여주는 메소드 생성
		@Override
		public Map<String, String> view_my_company_info(CompanyDTO company, String passwd) {
			Map<String,String> map = new HashMap<>();
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select company_name "
		         		+ "   , sales||'원' as sales "
		         		+ "   , number_of_employee||'명' as number_of_employee "
		         		+ "   , homepage "
		         		+ "   , boss_name "
		         		+ "   , BT.buss_type_name "
		         		+ "   , company_address "
		         		+ "   , CS.com_size "
		         		+ "   , company_tel "
		         		+ "   , company_email "
		         		+ "    from tbl_company_detail CD JOIN tbl_buss_type BT "
		         		+ "    on CD.fk_buss_type_code = BT.pk_buss_type_code "
		         		+ "    JOIN tbl_company_size CS "
		         		+ "    on CD.fk_company_size_code = CS.pk_com_size_code "
		         		+ "    where pk_company_code = ? and company_passwd = ?";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, company.getPK_COMPANY_CODE());
		         pstmt.setString(2, passwd);
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         while(rs.next()) {
		        	 map.put("company_name", rs.getString("company_name"));
		        	 map.put("sales", rs.getString("sales"));
		        	 map.put("number_of_employee", rs.getString("number_of_employee"));
		        	 map.put("homepage", rs.getString("homepage"));
		        	 map.put("boss_name", rs.getString("boss_name"));
		        	 map.put("buss_type_name", rs.getString("buss_type_name"));
		        	 map.put("company_address", rs.getString("company_address"));
		        	 map.put("com_size", rs.getString("com_size"));
		        	 map.put("company_tel", rs.getString("company_tel"));
		        	 map.put("company_email", rs.getString("company_email"));
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return map;
		} // end of public void view_my_company_info(CompanyDTO company, String passwd)

		
//////////////////////////////////////////////////////////////////////
// *** 직위 목록 모두 보여주는 메소드 *** //
@Override
public List<Map<String, String>> view_position() {

List<Map<String,String>> map_List = new ArrayList<>();

try {
conn = MyDBConnection.getConn();
String sql = " select position_name "
+ " from tbl_position " ;

pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery(); // sql문 실행

int i = 0;
while(rs.next()) {
Map<String,String> map = new HashMap<>();
map.put("position_name", rs.getString("position_name")); 

map_List.add(i, map);
i++;
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return map_List;


}



// *** 검색한 번호를 가지고 직위 코드를 알려주는 메소드 *** //
@Override
public String search_position_code(String get_po_name) {

String get_po = null;

try {
conn = MyDBConnection.getConn();
String sql = " select pk_position_code "
+ "    from tbl_position "
+ "    where position_name = ? ";

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, get_po_name);
rs = pstmt.executeQuery(); // sql문 실행

if(rs.next()) {
get_po =  rs.getString("pk_position_code");
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return get_po;
}



// *** 근무형태 목록 모두 보여주는 메소드 *** //
@Override
public List<Map<String, String>> view_work_type() {
List<Map<String,String>> map_List = new ArrayList<>();

try {
conn = MyDBConnection.getConn();
String sql = " select worktype_name"
+ " from tbl_worktype " ;

pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery(); // sql문 실행

int i = 0;
while(rs.next()) {
Map<String,String> map = new HashMap<>();
map.put("worktype_name", rs.getString("worktype_name")); 

map_List.add(i, map);
i++;
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return map_List;
}



// *** 검색한 번호를 가지고 직위 코드 알려주는 메소드 *** //
@Override
public String search_worktype_code(String get_worktype_name) {

String get_work_type = null;

try {
conn = MyDBConnection.getConn();
String sql = " select pk_worktype_code "
+ " from tbl_worktype "
+ " where worktype_name = ? ";

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, get_worktype_name);
rs = pstmt.executeQuery(); // sql문 실행

if(rs.next()) {
get_work_type =  rs.getString("pk_worktype_code");
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return get_work_type;


}



// *** 직무형태 목록 모두 보여주는 메소드 *** //
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



}




// *** 검색한 번호를 가지고 직무 코드 알려주는 메소드 *** //
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
}



// *** 검색한 지역의 코드를 알려주는 메소드 생성 *** //
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
}

// *** 검색한 지역의 코드를 알려주는 메소드 생성 *** //
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

// *** 모집 전형 모두 보여주는 메소드 *** //
@Override
public List<Map<String, String>> view_recruit_step() {

List<Map<String,String>> map_List = new ArrayList<>();

try {
conn = MyDBConnection.getConn();
String sql = " select recruit_step_name "
+ " from TBL_RECRUIT_STEP " ;

pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery(); // sql문 실행

int i = 0;
while(rs.next()) {
Map<String,String> map = new HashMap<>();
map.put("recruit_step_name", rs.getString("recruit_step_name")); 

map_List.add(i, map);
i++;
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return map_List;

}



// *** 검색한 번호를 가지고 모집 전형 코드 알려주는 메소드 *** ///
@Override
public String search_re_step(String get_re_step_name_1) {

String get_re_step = null;

try {
conn = MyDBConnection.getConn();
String sql = " select pk_recruit_step_code "
+ " from TBL_RECRUIT_STEP "
+ " where recruit_step_name = ? ";

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, get_re_step_name_1);
rs = pstmt.executeQuery(); // sql문 실행

if(rs.next()) {
get_re_step =  rs.getString("pk_recruit_step_code");
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return get_re_step;
}




// *** 구인회사 모집공고 등록하는 메소드(insert) *** //
@Override
public int join_recruit(Map<String, String> paraMap) {

int result_1 = 0; 

try {
conn = MyDBConnection.getConn();

// tbl_recruit_notice 에 insert 하기위한 sql문 //
String sql = " insert into TBL_RECRUIT_NOTICE (PK_RECRUIT_NOTICE_CODE, RECRUIT_NOTICE_NAME, YEARSAL, QUAIL, WOODAE, BENEFIT, RECRUIT_START_DAY, RECRUIT_FINISH_DAY, FK_COMPANY_CODE, FK_POSITION_CODE "
+ " , FK_WORKTYPE_CODE ,FK_JOB_TYPE_CODE, FK_RECRUIT_STEP_CODE_1, FK_RECRUIT_STEP_CODE_2 "
+ " , FK_RECRUIT_STEP_CODE_3, FK_RECRUIT_STEP_CODE_4, FK_RECRUIT_STEP_CODE_5,FK_LOCATION_CODE)"
+ "   values(SEQ_FK_RECRUIT_NOTICE_CODE.nextval, ?,?,?,?,?, to_char(sysdate, 'yy/mm/dd'),?,?,?,?,?,?,?,?,?,?,?)" ;

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, paraMap.get("re_name"));
pstmt.setString(2, paraMap.get("re_yearsal"));
pstmt.setString(3, paraMap.get("re_quali"));
pstmt.setString(4, paraMap.get("re_woodae"));
pstmt.setString(5, paraMap.get("re_benefit"));
pstmt.setString(6, paraMap.get("re_finish_day"));
pstmt.setString(7, paraMap.get("re_company_code"));
pstmt.setString(8, paraMap.get("re_position_code"));
pstmt.setString(9, paraMap.get("re_worktype_code"));
pstmt.setString(10, paraMap.get("re_jobtype_code"));
pstmt.setString(11, paraMap.get("re_step_1"));
pstmt.setString(12, paraMap.get("re_step_2"));
pstmt.setString(13, paraMap.get("re_step_3"));
pstmt.setString(14, paraMap.get("re_step_4"));
pstmt.setString(15, paraMap.get("re_step_5"));
pstmt.setString(16, paraMap.get("re_locatiton"));

conn.setAutoCommit(false); // auto commit 해제 

result_1 = pstmt.executeUpdate();



if(result_1 == 1 ) {

String yn = "";
do {
System.out.print("\n▶ 위의 회원정보로 모집공고를 등록하시겠습니까? [Y/N] : ");

yn = sc.nextLine();

if("y".equalsIgnoreCase(yn)){
result_1 = 1;
conn.commit(); 
}
else if("n".equalsIgnoreCase(yn)) {
result_1 = 0;
conn.rollback();
}
else {
System.out.println("<경고> 입력값은 Y 또는 N으로 입력해주세요.");
}
} while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));

conn.setAutoCommit(true);

}

} catch (SQLException e) {
e.printStackTrace();
result_1 = -1;

} finally {
close();
}// end of finally

return result_1; 


}

	
@Override
public List<Map<String, String>> SEARCH_DP(Map<String, String> paraMap) {
	  
	List<Map<String, String>> mapList = new ArrayList<>();
	    
	   try {
	        String sql =

	        	" select   pk_school_dept_code ,  school_dept_name, user_name,  self_introduce "
	            + " from tbl_school_dept SD JOIN tbl_school_info SI "
	            + " on SD.pk_School_dept_code = SI.school_dept_code "
	            + " JOIN tbl_resume R "
	            + " on SI.pk_edu_info = R.edu_info_1 or SI.pk_edu_info = R.edu_info_2 or SI.pk_edu_info = R.edu_info_3  "
	            + " JOIN tbl_member M "
	            + " on R.pk_resume_code = M.resume_code "
	            + " where school_dept_name LIKE '%' || ?  || '%'  ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("SD"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> CompanyDTODetail = new HashMap<>(); 

	           CompanyDTODetail.put("school_dept_name", rs.getString("school_dept_name"));
	           CompanyDTODetail.put("user_name", rs.getString("user_name"));
	           CompanyDTODetail.put("self_introduce", rs.getString("self_introduce"));
	        

	            mapList.add(CompanyDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return mapList;


}



// 연령대 구하기 
@Override
public List<Map<String, String>> SEARCH_AGE(Map<String, String> paraMap) {
	List<Map<String, String>> mapList = new ArrayList<>();
    
	   try {
	        String sql =

	        	"          select * "
	        	+ " from (select user_name "
	        	+ "   , case when CURRENT_YEAR_BIRTHDAY > to_date( to_char(SYSDATE, 'yyyymmdd'), 'yyyymmdd') "
	        	+ "                     then extract(year from sysdate) - birthyear - 1 "
	        	+ "                else extract(year from sysdate) - birthyear\r\n"
	        	+ "           end AS mage "
	        	+ "         , to_number(trunc(case when CURRENT_YEAR_BIRTHDAY > to_date( to_char(SYSDATE, 'yyyymmdd'), 'yyyymmdd') "
	        	+ "                     then extract(year from sysdate) - birthyear - 1 "
	        	+ "                else extract(year from sysdate) - birthyear "
	        	+ "           end, -1) ) AS aged "
	        	+ "               FROM  "
	        	+ "    ( "
	        	+ "      select user_name "
	        	+ "           , case when substr(user_jubun,8,1) in('1','3') then '남' else '여' end AS GENDER "
	        	+ "           , case when substr(user_jubun,8,1) in('1','2') then '19' else '20' end || substr(user_jubun, 1, 2) AS BIRTHYEAR "
	        	+ "           , to_date(to_char(sysdate, 'yyyy') || substr(user_jubun,3,4), 'yyyymmdd') AS CURRENT_YEAR_BIRTHDAY "
	        	+ "      from tbl_member "
	        	+ "    ) V ) where aged = ? ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("SA"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> CompanyDTODetail = new HashMap<>(); 

	           CompanyDTODetail.put("USER_NAME", rs.getString("USER_NAME"));
	           CompanyDTODetail.put("MAGE", rs.getString("MAGE"));
	           CompanyDTODetail.put("AGED", rs.getString("AGED"));
	        
	        

	            mapList.add(CompanyDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return mapList;

}

	//모든 업종 목록을 list에 담아주는 메소드 생성.
	@Override
	public List<String> view_all_buss_type_list() {
		
		List<String> list = new ArrayList<>();
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select buss_type_name "
              		+ "                    from tbl_buss_type ";
              
              pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              while(rs.next()) {
            	  int i = 0;
            	  list.add(i,rs.getString("buss_type_name"));
            	  i++;
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		return list;
	}


	@Override
	public String get_buss_type_code(String string) {
		String buss_type_code = "";
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select pk_buss_type_code "
              		+ "      from tbl_buss_type "
              		+ "      where buss_type_name = ? ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, string);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              if(rs.next()) {
            	  buss_type_code = rs.getString("pk_buss_type_code");
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		
		
		return buss_type_code;
	}

	// 모든 기업규모 목록을 list에 담아주는 메소드 생성.
	@Override
	public List<String> view_all_company_size_list() {
List<String> list = new ArrayList<>();
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select com_size "
              		+ "      from tbl_company_size ";
              
              pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              while(rs.next()) {
            	  int i = 0;
            	  list.add(i,rs.getString("com_size"));
            	  i++;
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		return list;
	}

	// 입력한 기업규모명과 일치하는 코드를 반환.
	@Override
	public String get_company_size_code(String string) {
		String com_size_code = "";
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select pk_com_size_code "
              		+ "                    from tbl_company_size "
              		+ "                    where com_size = ? ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, string);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              if(rs.next()) {
            	  com_size_code = rs.getString("pk_com_size_code");
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		
		
		return com_size_code;
	}

	
	
	// 저장한 값을 토대로 회사 상세 정보를 변경해주는 메소드 생성
	@Override
	public int change_company_detail(CompanyDTO company,Map<String, String> paraMap) {

		int result = 0;
		try {
            conn = MyDBConnection.getConn();
            String sql = " update tbl_company_login set company_name = ? "
            		+ "    where pk_fk_company_code = ?  ";
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, paraMap.get("change_company_name"));
            pstmt.setString(2, company.getPK_COMPANY_CODE());
            int n_1 = pstmt.executeUpdate(); // sql문 실행
            
            if(n_1 == 1) {
            	
            	sql = " update tbl_company_detail "
            			+ "set company_name = ? "
            			+ "  , sales = ?  "
            			+ "  , number_of_employee = ? "
            			+ "  , homepage = ? "
            			+ "  , fk_buss_type_code = ? "
            			+ "  , company_address = ? "
            			+ "  , fk_company_size_code = ? "
            			+ "  , company_tel = ? "
            			+ "  , company_email = ? "
            			+ "  where pk_company_code = ? ";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, paraMap.get("change_company_name"));
            	pstmt.setString(2, paraMap.get("change_sales"));
            	pstmt.setString(3, paraMap.get("change_number_of_employee"));
            	pstmt.setString(4, paraMap.get("change_homepage"));
            	pstmt.setString(5, paraMap.get("buss_type_code"));
            	pstmt.setString(6, paraMap.get("change_address"));
            	pstmt.setString(7, paraMap.get("company_size_code"));
            	pstmt.setString(8, paraMap.get("change_tel"));
            	pstmt.setString(9, paraMap.get("change_email"));
            	pstmt.setString(10, company.getPK_COMPANY_CODE());
            	int n_2 = pstmt.executeUpdate();
            	
            	if(n_2 == 1) {
            		
            		do {
	            		System.out.println(">>> 정말로 수정 하시겠습니까? [Y/N] <<<");
	            		String yn = sc.nextLine();
	            		if("y".equalsIgnoreCase(yn)) {
	            			conn.commit();
	            			conn.setAutoCommit(true);
	            			return result = 1;
	            		}
	            		else if ("n".equalsIgnoreCase(yn)) {
	            			conn.rollback();
	            			conn.setAutoCommit(true);
	            			return result = 0;
	            		}
	            		else {
	            			System.out.println("[경고] 반드시 Y 혹은 N 둘중에서 선택하세요");
	            		}
	            		
            		} while(true);
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


	
	
}
=======
package company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import company.domain.CompanyDTO;
import dbconnection.MyDBConnection;

public class CompanyDAO_imple implements CompanyDAO {

	Connection conn = MyDBConnection.getConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	
	private void close() {
		try {
			if(rs != null) 	  {rs.close();	rs = null;}
			if(pstmt != null) {pstmt.close();	pstmt = null;}
			// conn은 프로그램을 종료할때만 끊어야한다. 
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()----------------------------
	
	
	// 구인회사가 입력한 아이디, 비밀번호에 일치하는 정보가 있는지 확인하기위한 select 메소드
	@Override
	public CompanyDTO login(Map<String, String> paraMap) {
		
		CompanyDTO cdto = new CompanyDTO();
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select pk_fk_company_code,company_name, company_passwd"
					   + " from tbl_company_login "
					   + " where pk_fk_company_code = ? and company_passwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("PK_COMPANY_CODE"));
			pstmt.setString(2, paraMap.get("COMPANY_PASSWD"));
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) {
				cdto.setPK_COMPANY_CODE(rs.getString("pk_fk_company_code"));
				cdto.setCOMPANY_PASSWD(rs.getString("company_passwd"));
				cdto.setCOMPANY_NAME(rs.getString("company_name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		return cdto;
	} // end of public CompanyDTO login(Map<String, String> paraMap)

	
	// 이메일과 비밀번호가 일치한다면 사업자등록번호를 보여주기.
	@Override
	public String find_com_id(Map<String, String> find_com_id) {
		
		String company_id = null;
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select pk_company_code "
					   + " from tbl_company_detail "
					   + " where company_email = ? and company_passwd = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find_com_id.get("com_email"));
			pstmt.setString(2, find_com_id.get("com_passwd"));
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) {
				company_id = rs.getString("pk_company_code");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		
		
		
		
		
		
		return company_id;
	} // end of public String find_com_id(Map<String, String> find_com_id)

	
	// 입력한 정보가 맞으면 비밀번호를 변경할 수 있게 해주는 메소드 생성.
	@Override
	public int find_com_passwd(Map<String, String> find_com_passwd) {
		
		int result = 0;
		
		try {
			conn = MyDBConnection.getConn();
			String sql = " select pk_company_code "
					   + " from tbl_company_detail "
					   + " where company_email = ? and pk_company_code = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find_com_passwd.get("com_email"));
			pstmt.setString(2, find_com_passwd.get("com_id"));
			rs = pstmt.executeQuery(); // sql문 실행
			
			if(rs.next()) { // 위에서 입력한 email 과 아이디가 일치하는 값이 있다면
				
				System.out.println("-----------[비밀번호 변경]-----------");
				
				System.out.println("\n[안내] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
				String change_passwd = "";
				do {
					CompanyDTO cmdto = new CompanyDTO();
					System.out.print("▶ 변경할 비밀번호를 입력하세요 : ");
					change_passwd = sc.nextLine();
					cmdto.setCOMPANY_PASSWD(change_passwd); // 비밀번호 유효성 검사 
					if(!(cmdto.getCOMPANY_PASSWD() == null || cmdto.getCOMPANY_PASSWD().isBlank())) {
						break;
					}
					else {
						System.out.println("[경고] 비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자가 모두 포함되게 입력하세요.");
					}
				}while(true); // end of do_while passwd
				
				sql = " update tbl_company_detail set company_passwd = ? "
					+ " where company_email = ? and pk_company_code = ? ";
				
				conn.setAutoCommit(false);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, change_passwd);
				pstmt.setString(2, find_com_passwd.get("com_email"));
				pstmt.setString(3, find_com_passwd.get("com_id"));
				
				int n_1 = pstmt.executeUpdate();
				
				if(n_1 == 1) {
					
				sql = " update tbl_company_login set COMPANY_PASSWD = ? " 
					+ " where PK_FK_COMPANY_CODE = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, change_passwd);
				pstmt.setString(2, find_com_passwd.get("com_id"));
				int n_2 = pstmt.executeUpdate();
				
					String yn = "";
					if(n_2 == 1){
						System.out.print("▶ 비밀번호를 변경 하시겠습니까? [Y/N] : ");
						yn = sc.nextLine();

						do {
							if("y".equalsIgnoreCase(yn)) {
								conn.commit();
								return result = 1;
							}
							else if("n".equalsIgnoreCase(yn)) {
								conn.rollback();
								return result = 0;
							}
							else {
								System.out.println("[경고] 반드시 Y 혹은 N 중에서 입력하세요");
							}
						}while(!("y".equalsIgnoreCase(yn)||"n".equalsIgnoreCase(yn)));
					}
					
					else {
						result = -1;
					}
					
				}
				else {
					result = -1;
				}
				
			}
			else {
				result = -1;
			}
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();	
		}
		
		return result;
	} // end of public int find_com_passwd(Map<String, String> find_com_passwd)

	
	// *** 아이디 중복 검사 메소드 *** //
		@Override
		public int check_com_id(String com_id) {
			int result = 0; // 같은 아이디를 사용하는 사람이 있는지 없는지 체크하게 해주는 변수 
		      
		      try {
		         conn = MyDBConnection.getConn();
		         String sql = " select * "
		                  + " from tbl_company_detail "
		                  + " where pk_company_code = ? ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, com_id);
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
		}
		
		
		// *** 입력한 구인회사 회원가입 정보를 토대로 회원가입 할 수 있게 해주는 메소드 생성*** //
		@Override
		public int join_company(Map<String, String> paraMap) {

			  int result_1 = 0; 
		      int result_2 = 0; 
		      
		      try {
		         conn = MyDBConnection.getConn();
		         
		         // tbl_company_detail 에 insert 하기위한 sql문 //
		         String sql = "  insert into tbl_company_detail(pk_company_code, company_passwd, company_name, company_email, company_tel, sales, "
		         		    + "  NUMBER_OF_EMPLOYEE, HOMEPAGE, ESTABLISHMENT_DATE, BOSS_NAME , fk_buss_type_code, Fk_company_size_code, company_address ) "
		                    + "  values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, paraMap.get("com_id"));
		         pstmt.setString(2, paraMap.get("com_passwd"));
		         pstmt.setString(3, paraMap.get("com_name"));
		         pstmt.setString(4, paraMap.get("com_email"));
		         pstmt.setString(5, paraMap.get("com_tel"));
		         pstmt.setString(6, paraMap.get("com_sales"));
		         pstmt.setInt(7, Integer.parseInt(paraMap.get("com_employee_no")));
		         pstmt.setString(8, paraMap.get("com_homepage"));
		         pstmt.setString(9, paraMap.get("com_establish"));
		         pstmt.setString(10, paraMap.get("com_boss_name"));
		         pstmt.setString(11, paraMap.get("get_buss_type_code"));
		         pstmt.setString(12, paraMap.get("get_com_size_code"));
		         pstmt.setString(13, paraMap.get("com_address"));
		         
		         conn.setAutoCommit(false); // auto commit 해제 
		         
		         result_1 = pstmt.executeUpdate();
		         
		         // *** 두 값 비교하기 위해서는 company_login 테이블에도 insert 해야함 *** //
		         sql = " insert into tbl_company_login(Pk_Fk_company_code, company_passwd, company_name) "
		         	 + " values(?,?,?) " ;
		         
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, paraMap.get("com_id"));
		         pstmt.setString(2, paraMap.get("com_passwd"));
		         pstmt.setString(3, paraMap.get("com_name"));
		         
		         result_2 = pstmt.executeUpdate();
		         
		         
		         if(result_1 == 1 && result_2 == 1) {
		        	 
		        	 String yn = "";
		        	 do {
			        	 System.out.print("\n▶ 위의 회원정보로 회원가입 하시겠습니까? [Y/N] : ");
			        	 
			        	 yn = sc.nextLine();
			        	 
			        	 if("y".equalsIgnoreCase(yn)){
			        		 result_1 = 1;
			        		 conn.commit(); 
			        	 }
			        	 else if("n".equalsIgnoreCase(yn)) {
			        		 result_1 = 0;
			        		 conn.rollback();
			        	 }
			        	 else {
			        		 System.out.println("<경고> 입력값은 Y 또는 N으로 입력해주세요.");
			        	 }
		        	 } while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
			        	 	 
		        	 conn.setAutoCommit(true);
		        	 
		         }

		      } catch (SQLException e) {
				  e.printStackTrace();
				  result_1 = -1;
				  
			  } finally {
					close();
			  }// end of finally
				
			  return result_1; // 성공이면 return 값이 1
			
		}// end of public int join_company(Map<String, String> paraMap)
	
	
		
		// *** 모든 업종을 보여주는 메소드 *** // 
		@Override
		public List<Map<String, String>> view_all_buss_type() {
			
			List<Map<String,String>> map_List = new ArrayList<>();
			
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select buss_type_name "
		                    + " from tbl_buss_type  ";
		         
		         pstmt = conn.prepareStatement(sql);
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         int i = 0;
		         while(rs.next()) {
		        	 Map<String,String> map = new HashMap<>();
		        	 map.put("buss_type_name", rs.getString("buss_type_name"));
		        	 
		        	 map_List.add(i, map);
		        	 i++;
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return map_List;
			
		} // end of public void view_all_buss_type()
		
		
		// *** 검색한 번호를 토대로 업종 코드를 알려주는 메소드 생성 (select) *** //
		@Override
		public String search_buss_type_code(String get_buss_name) {
			
			String get_buss_type_code = null;
			
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select pk_buss_type_code "
		                    + " from tbl_buss_type "
		                    + " where buss_type_name = ? ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, get_buss_name);
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         if(rs.next()) {
		        	 get_buss_type_code =  rs.getString("pk_buss_type_code" );
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return get_buss_type_code;
			
		}// end of public String search_buss_type_code(String get_buss_name)
		
		
		//*** 모든 회사 규모 목록 보여주는 메소드 *** //
		@Override
		public List<Map<String, String>> view_all_company_size() {
			  List<Map<String,String>> map_List = new ArrayList<>();
		         
		         try {
		               conn = MyDBConnection.getConn();
		               String sql = " select com_size " // 회사 규모명 대기업,중소 이런거
		                          + " from tbl_company_size  ";
		               
		               pstmt = conn.prepareStatement(sql);
		               rs = pstmt.executeQuery(); // sql문 실행
		               
		               int i = 0;
		               while(rs.next()) {
		                  Map<String,String> map = new HashMap<>();
		                  map.put("com_size", rs.getString("com_size")); 
		                  
		                  map_List.add(i, map);
		                  i++;
		               }
		               
		            } catch (SQLException e) {
		               e.printStackTrace();
		            } finally {
		               close();   
		            }
		         
		         return map_List;

		}// end of public List<Map<String, String>> view_all_company_size()
		
		
		// *** 검색한 번호를 토대로 회사 규모 코드를 알려주는 메소드 생성(select) *** //
		@Override
		public String search_PK_COM_SIZE_CODE(String get_com_size_name) {
			String get_com_size = null;
	        
	        try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_com_size_code "
	                       + " from tbl_company_size "
	                       + " where com_size = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, get_com_size_name);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              if(rs.next()) {
	                 get_com_size =  rs.getString("pk_com_size_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
	        
	        return get_com_size;

		}// end of public String search_PK_COM_SIZE_CODE(String get_com_size_name)


		// 내가 올린 모든 공고 목록을 보여주는 메소드 생성. 
		@Override
		public int view_my_recruit_notice(CompanyDTO company) {
			
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_recruit_notice_code, recruit_notice_name, yearsal||'만원' as yearsal , recruit_start_day "
	              		+ "    from tbl_recruit_notice "
	              		+ "    where fk_company_code = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, company.getPK_COMPANY_CODE());
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              StringBuilder sb = new StringBuilder();
	              
	              while(rs.next()) {
	            	  sb.append(rs.getString("pk_recruit_notice_code") + "\t");
	            	  sb.append(rs.getString("recruit_notice_name") + "\t");
	            	  sb.append(rs.getString("yearsal") + "\t");
	            	  sb.append(rs.getString("recruit_start_day") + "\n");
	              }
	              
	              if(sb.toString().isBlank()) {
	            	  System.out.println("[안내] 등록한 공고가 존재하지 않습니다.");
	            	  return result = 0;
	              }
	              else {
	            	  System.out.println("──────────────────────[" + company.getCOMPANY_NAME() + "] 에서 등록한 구인공고──────────────────────\n"
	            	  		           + "     공고번호       공고명                       연봉                    공고등록날짜 \n"
	            	  		           + "──────────────────────────────────────────────────────────────────────────────────────────────");
	            	  System.out.println(sb.toString());
	            	  return result = 1;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			return result;
		}

		// 입력한 공고번호에 일치하는 공고 상세정보 출력하기
		@Override
		public Map<String, String> view_one_recruit_notice(CompanyDTO company,String change_recruit_notice_no) {
			
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
	              		+ "    where RN.pk_recruit_notice_code = ? and RN.fk_company_code = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, change_recruit_notice_no);
	              pstmt.setString(2, company.getPK_COMPANY_CODE());
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

		
		// 모든 직무 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_job_type_list() {
			
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select job_type_name "
	              		+ "    from tbl_job_type ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("job_type_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		}

		// 입력한 직무명과 일치하는 코드를 반환.
		@Override
		public String get_job_type_code(String string) {
			
			String job_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_job_type_code\r\n"
	              		+ "    from tbl_job_type\r\n"
	              		+ "    where job_type_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  job_code = rs.getString("pk_job_type_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return job_code;
		} // end of public String get_job_type_code(String string)

		
		
		// 모든 직책 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_position_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select position_name "
	              		+ "    from tbl_position ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("position_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		} // end of public List<String> view_all_position_list()

		
		// 입력한 직책명과 일치하는 코드를 반환.
		@Override
		public String get_position_code(String string) {
			String position_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_position_code "
	              		+ "    from tbl_position "
	              		+ "    where position_name = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  position_code = rs.getString("pk_position_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return position_code;
		}// end of public String get_position_code(String string)

		
		
		// 모든 채용형태 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_worktype_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select worktype_name "
	              		+ "    from tbl_worktype ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("worktype_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		} // end of public List<String> view_all_worktype_list()

		
		 // 입력한 채용형태명과 일치하는 코드를 반환.
		@Override
		public String get_worktype_code(String string) {
			String worktype_code = "";
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_worktype_code "
	              		+ "    from tbl_worktype "
	              		+ "    where WORKTYPE_NAME = ? ";
	              
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, string);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              if(rs.next()) {
	            	  worktype_code = rs.getString("pk_worktype_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return worktype_code;
		} //end of public String get_worktype_code(String string)

		
		
		
		 // 모든 모집전형 목록을 list에 담아주는 메소드 생성.
		@Override
		public List<String> view_all_recruit_step_list() {
			List<String> list = new ArrayList<>();
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select recruit_step_name "
	              		+ "    from tbl_recruit_step ";
	              
	              pstmt = conn.prepareStatement(sql);
	              rs = pstmt.executeQuery(); // sql문 실행
	              
	              
	              while(rs.next()) {
	            	  int i = 0;
	            	  list.add(i,rs.getString("recruit_step_name"));
	            	  i++;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return list;
		}

		
		 // 맨 처음 엔터를 눌렀을 때 모집전형 코드를 가지고오는 메소드 생성
		@Override
		public Map<String, String> get_recruit_step_code(Map<String, String> map) {
			
			Map<String,String> map_1 = new HashMap<>();
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_recruit_step_code "
	              		+ "    from tbl_recruit_step "
	              		+ "    where recruit_step_name = ? ";
	              
	              
	              
	              for(int i = 0; i < 5; i++) {
	            	  if(map.get("recruit_step_name_" + (i+1))!=null) {
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, map.get( "recruit_step_name_" + (i+1) ));
	            		  rs = pstmt.executeQuery(); // sql문 실행
	            		  if(rs.next()) {
	            			  map_1.put("recruit_step_code_" + (i+1), rs.getString("pk_recruit_step_code"));
	            		  }
	            	  }
	            	  else {
	            		  break;
	            	  }
	              }
	              
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			return map_1;
		}

		
		 // 입력한 전형명에 일치하는 코드를 반환.
		@Override
		public String get_recruit_step_code_2(String recruit_step_name) {
			String recruit_step_code = "";
			
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " select pk_recruit_step_code "
	              		+ "    from tbl_recruit_step "
	              		+ "    where recruit_step_name = ? ";
	              
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1 , recruit_step_name);
	            		  rs = pstmt.executeQuery(); // sql문 실행
	            		 
	              if(rs.next()) {
	            	  recruit_step_code = rs.getString("pk_recruit_step_code");
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			return recruit_step_code;
		} // end ofpublic String get_recruit_step_code_2(String recruit_step_name)

		
		
		 // 위에 담긴 map값으로 모집공고를 업데이트 해주는 메소드 생성.
		@Override
		public int change_recruit_notice(String change_recruit_notice_no, Map<String, String> map) {
			
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              conn.setAutoCommit(false);
	              
	            	  String sql = " update tbl_recruit_notice set "
	  	              		+ "     recruit_notice_name = ? "
	  	              		+ "    ,yearsal = ? "
	  	              		+ "    ,quail = ? "
	  	              		+ "    ,woodae = ? "
	  	              		+ "    ,benefit = ? "
	  	              		+ "    ,fk_position_code = ? "
	  	              		+ "    ,fk_worktype_code = ? "
	  	              		+ "    ,fk_job_type_code = ? "
	  	              		+ "    ,fk_location_code = ? "
	  	              		+ "     where pk_recruit_notice_code = ? ";
	              
	            	  	
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1, map.get("change_recruit_notice_name") );
	            		  pstmt.setString(2, map.get("change_yearsal"));
	            		  pstmt.setString(3, map.get("change_quail"));
	            		  pstmt.setString(4, map.get("change_woodae"));
	            		  pstmt.setString(5, map.get("change_benefit"));
	            		  pstmt.setString(6, map.get("position_code"));
	            		  pstmt.setString(7, map.get("worktype_code"));
	            		  pstmt.setString(8, map.get("job_type_code"));
	            		  pstmt.setString(9, map.get("address_code"));
	            		  pstmt.setString(10, change_recruit_notice_no);
	            		  int n_1 = pstmt.executeUpdate();
	            		  
	            		  
	            		  if(n_1 == 1) {
	            			  
	            			  
	            			  int cnt = 0;
	            			  for(int i = 0; i < 5; i ++) {
	            				  if(map.get("recruit_step_code_" + (i+1))!=null) {
	            					  sql = " update tbl_recruit_notice set fk_recruit_step_code_" + (i+1) +" = ?"
	            					  	  + " where pk_recruit_notice_code = ? ";
	            					  pstmt = conn.prepareStatement(sql);
	            					  pstmt.setString(1, map.get("recruit_step_code_"+(i+1)));
	            					  pstmt.setString(2, change_recruit_notice_no);
	            					  int n_2 = pstmt.executeUpdate();
	            					  if(n_2 == 1) {
	            						  cnt++;
	            					  }
	            				  }
	            				  else {
	            					  sql = " update tbl_recruit_notice set fk_recruit_step_code_" + (i+1) +" = '' "
		            					  	  + " where pk_recruit_notice_code = ? ";
		            					  pstmt = conn.prepareStatement(sql);
		            					  pstmt.setString(1, change_recruit_notice_no);
		            					  int n_2 = pstmt.executeUpdate();
		            					  if(n_2 == 1) {
		            						  cnt++;
		            					  }
	            				  }
	            				  
	            			  }
	            			  
	            			  if(cnt==5) {
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
	            			  }
	            			  else {
	            				  result = -1;
	            			  }
	            			  
	            		  }
	            		  else {
	            			  result = -1;
	            		  }
	            		 
	              
	            		  
	            	conn.setAutoCommit(true);	  
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			return result;
		} // end of public int change_recruit_notice(String change_recruit_notice_no, Map<String, String> map)

		
		
		//선택한 모집공고를 삭제하는 메소드 생성
		@Override
		public int delete_recruit_notice(CompanyDTO company, String delete_recruit_no) {
			int result = 0;
			
			try {
	              conn = MyDBConnection.getConn();
	              String sql = " delete tbl_join_recruit_notice"
	              		+ "      where fk_recruit_notice_code = ? ";
	              		  
	              
	              		  conn.setAutoCommit(false);
	            		  pstmt = conn.prepareStatement(sql);
	            		  pstmt.setString(1 , delete_recruit_no );
	            		  int n = pstmt.executeUpdate();
	            		 
	            	if(n != 0) {
	            		sql = " delete tbl_recruit_notice "
	            			+ " where pk_recruit_notice_code = ? and fk_company_code = ? ";
	            		pstmt = conn.prepareStatement(sql);
	            		pstmt.setString(1, delete_recruit_no);
	            		pstmt.setString(2, company.getPK_COMPANY_CODE());
	            		int n_2 = pstmt.executeUpdate();
	            		
	            		if(n_2 == 1) {
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
	            		conn.setAutoCommit(true);
	            		return result = 0;
	            		
	            	}
 
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              close();   
	           }
			
			
			
			return result;
		}// end of public int delete_recruit_notice(CompanyDTO company, String delete_recruit_no)

		
		
		// 내 회사의 상세정보를 보여주는 메소드 생성
		@Override
		public Map<String, String> view_my_company_info(CompanyDTO company, String passwd) {
			Map<String,String> map = new HashMap<>();
			try {
		         conn = MyDBConnection.getConn();
		         String sql = " select company_name "
		         		+ "   , sales||'원' as sales "
		         		+ "   , number_of_employee||'명' as number_of_employee "
		         		+ "   , homepage "
		         		+ "   , boss_name "
		         		+ "   , BT.buss_type_name "
		         		+ "   , company_address "
		         		+ "   , CS.com_size "
		         		+ "   , company_tel "
		         		+ "   , company_email "
		         		+ "    from tbl_company_detail CD JOIN tbl_buss_type BT "
		         		+ "    on CD.fk_buss_type_code = BT.pk_buss_type_code "
		         		+ "    JOIN tbl_company_size CS "
		         		+ "    on CD.fk_company_size_code = CS.pk_com_size_code "
		         		+ "    where pk_company_code = ? and company_passwd = ?";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, company.getPK_COMPANY_CODE());
		         pstmt.setString(2, passwd);
		         rs = pstmt.executeQuery(); // sql문 실행
		         
		         while(rs.next()) {
		        	 map.put("company_name", rs.getString("company_name"));
		        	 map.put("sales", rs.getString("sales"));
		        	 map.put("number_of_employee", rs.getString("number_of_employee"));
		        	 map.put("homepage", rs.getString("homepage"));
		        	 map.put("boss_name", rs.getString("boss_name"));
		        	 map.put("buss_type_name", rs.getString("buss_type_name"));
		        	 map.put("company_address", rs.getString("company_address"));
		        	 map.put("com_size", rs.getString("com_size"));
		        	 map.put("company_tel", rs.getString("company_tel"));
		        	 map.put("company_email", rs.getString("company_email"));
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();   
		      }
			
			return map;
		} // end of public void view_my_company_info(CompanyDTO company, String passwd)

		
//////////////////////////////////////////////////////////////////////
// *** 직위 목록 모두 보여주는 메소드 *** //
@Override
public List<Map<String, String>> view_position() {

List<Map<String,String>> map_List = new ArrayList<>();

try {
conn = MyDBConnection.getConn();
String sql = " select position_name "
+ " from tbl_position " ;

pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery(); // sql문 실행

int i = 0;
while(rs.next()) {
Map<String,String> map = new HashMap<>();
map.put("position_name", rs.getString("position_name")); 

map_List.add(i, map);
i++;
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return map_List;


}



// *** 검색한 번호를 가지고 직위 코드를 알려주는 메소드 *** //
@Override
public String search_position_code(String get_po_name) {

String get_po = null;

try {
conn = MyDBConnection.getConn();
String sql = " select pk_position_code "
+ "    from tbl_position "
+ "    where position_name = ? ";

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, get_po_name);
rs = pstmt.executeQuery(); // sql문 실행

if(rs.next()) {
get_po =  rs.getString("pk_position_code");
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return get_po;
}



// *** 근무형태 목록 모두 보여주는 메소드 *** //
@Override
public List<Map<String, String>> view_work_type() {
List<Map<String,String>> map_List = new ArrayList<>();

try {
conn = MyDBConnection.getConn();
String sql = " select worktype_name"
+ " from tbl_worktype " ;

pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery(); // sql문 실행

int i = 0;
while(rs.next()) {
Map<String,String> map = new HashMap<>();
map.put("worktype_name", rs.getString("worktype_name")); 

map_List.add(i, map);
i++;
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return map_List;
}



// *** 검색한 번호를 가지고 직위 코드 알려주는 메소드 *** //
@Override
public String search_worktype_code(String get_worktype_name) {

String get_work_type = null;

try {
conn = MyDBConnection.getConn();
String sql = " select pk_worktype_code "
+ " from tbl_worktype "
+ " where worktype_name = ? ";

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, get_worktype_name);
rs = pstmt.executeQuery(); // sql문 실행

if(rs.next()) {
get_work_type =  rs.getString("pk_worktype_code");
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return get_work_type;


}



// *** 직무형태 목록 모두 보여주는 메소드 *** //
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



}




// *** 검색한 번호를 가지고 직무 코드 알려주는 메소드 *** //
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
}



// *** 검색한 지역의 코드를 알려주는 메소드 생성 *** //
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
}

// *** 검색한 지역의 코드를 알려주는 메소드 생성 *** //
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

// *** 모집 전형 모두 보여주는 메소드 *** //
@Override
public List<Map<String, String>> view_recruit_step() {

List<Map<String,String>> map_List = new ArrayList<>();

try {
conn = MyDBConnection.getConn();
String sql = " select recruit_step_name "
+ " from TBL_RECRUIT_STEP " ;

pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery(); // sql문 실행

int i = 0;
while(rs.next()) {
Map<String,String> map = new HashMap<>();
map.put("recruit_step_name", rs.getString("recruit_step_name")); 

map_List.add(i, map);
i++;
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return map_List;

}



// *** 검색한 번호를 가지고 모집 전형 코드 알려주는 메소드 *** ///
@Override
public String search_re_step(String get_re_step_name_1) {

String get_re_step = null;

try {
conn = MyDBConnection.getConn();
String sql = " select pk_recruit_step_code "
+ " from TBL_RECRUIT_STEP "
+ " where recruit_step_name = ? ";

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, get_re_step_name_1);
rs = pstmt.executeQuery(); // sql문 실행

if(rs.next()) {
get_re_step =  rs.getString("pk_recruit_step_code");
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
close();   
}

return get_re_step;
}




// *** 구인회사 모집공고 등록하는 메소드(insert) *** //
@Override
public int join_recruit(Map<String, String> paraMap) {

int result_1 = 0; 

try {
conn = MyDBConnection.getConn();

// tbl_recruit_notice 에 insert 하기위한 sql문 //
String sql = " insert into TBL_RECRUIT_NOTICE (PK_RECRUIT_NOTICE_CODE, RECRUIT_NOTICE_NAME, YEARSAL, QUAIL, WOODAE, BENEFIT, RECRUIT_START_DAY, RECRUIT_FINISH_DAY, FK_COMPANY_CODE, FK_POSITION_CODE "
+ " , FK_WORKTYPE_CODE ,FK_JOB_TYPE_CODE, FK_RECRUIT_STEP_CODE_1, FK_RECRUIT_STEP_CODE_2 "
+ " , FK_RECRUIT_STEP_CODE_3, FK_RECRUIT_STEP_CODE_4, FK_RECRUIT_STEP_CODE_5,FK_LOCATION_CODE)"
+ "   values(SEQ_FK_RECRUIT_NOTICE_CODE.nextval, ?,?,?,?,?, to_char(sysdate, 'yy/mm/dd'),?,?,?,?,?,?,?,?,?,?,?)" ;

pstmt = conn.prepareStatement(sql);
pstmt.setString(1, paraMap.get("re_name"));
pstmt.setString(2, paraMap.get("re_yearsal"));
pstmt.setString(3, paraMap.get("re_quali"));
pstmt.setString(4, paraMap.get("re_woodae"));
pstmt.setString(5, paraMap.get("re_benefit"));
pstmt.setString(6, paraMap.get("re_finish_day"));
pstmt.setString(7, paraMap.get("re_company_code"));
pstmt.setString(8, paraMap.get("re_position_code"));
pstmt.setString(9, paraMap.get("re_worktype_code"));
pstmt.setString(10, paraMap.get("re_jobtype_code"));
pstmt.setString(11, paraMap.get("re_step_1"));
pstmt.setString(12, paraMap.get("re_step_2"));
pstmt.setString(13, paraMap.get("re_step_3"));
pstmt.setString(14, paraMap.get("re_step_4"));
pstmt.setString(15, paraMap.get("re_step_5"));
pstmt.setString(16, paraMap.get("re_locatiton"));

conn.setAutoCommit(false); // auto commit 해제 

result_1 = pstmt.executeUpdate();



if(result_1 == 1 ) {

String yn = "";
do {
System.out.print("\n▶ 위의 회원정보로 모집공고를 등록하시겠습니까? [Y/N] : ");

yn = sc.nextLine();

if("y".equalsIgnoreCase(yn)){
result_1 = 1;
conn.commit(); 
}
else if("n".equalsIgnoreCase(yn)) {
result_1 = 0;
conn.rollback();
}
else {
System.out.println("<경고> 입력값은 Y 또는 N으로 입력해주세요.");
}
} while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));

conn.setAutoCommit(true);

}

} catch (SQLException e) {
e.printStackTrace();
result_1 = -1;

} finally {
close();
}// end of finally

return result_1; 


}

	
@Override
public List<Map<String, String>> SEARCH_DP(Map<String, String> paraMap) {
	  
	List<Map<String, String>> mapList = new ArrayList<>();
	    
	   try {
	        String sql =

	        	" select   pk_school_dept_code ,  school_dept_name, user_name,  self_introduce "
	            + " from tbl_school_dept SD JOIN tbl_school_info SI "
	            + " on SD.pk_School_dept_code = SI.school_dept_code "
	            + " JOIN tbl_resume R "
	            + " on SI.pk_edu_info = R.edu_info_1 or SI.pk_edu_info = R.edu_info_2 or SI.pk_edu_info = R.edu_info_3  "
	            + " JOIN tbl_member M "
	            + " on R.pk_resume_code = M.resume_code "
	            + " where school_dept_name LIKE '%' || ?  || '%'  ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("SD"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> CompanyDTODetail = new HashMap<>(); 

	           CompanyDTODetail.put("school_dept_name", rs.getString("school_dept_name"));
	           CompanyDTODetail.put("user_name", rs.getString("user_name"));
	           CompanyDTODetail.put("self_introduce", rs.getString("self_introduce"));
	        

	            mapList.add(CompanyDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return mapList;


}



// 연령대 구하기 
@Override
public List<Map<String, String>> SEARCH_AGE(Map<String, String> paraMap) {
	List<Map<String, String>> mapList = new ArrayList<>();
    
	   try {
	        String sql =

	        	"          select * "
	        	+ " from (select user_name "
	        	+ "   , case when CURRENT_YEAR_BIRTHDAY > to_date( to_char(SYSDATE, 'yyyymmdd'), 'yyyymmdd') "
	        	+ "                     then extract(year from sysdate) - birthyear - 1 "
	        	+ "                else extract(year from sysdate) - birthyear\r\n"
	        	+ "           end AS mage "
	        	+ "         , to_number(trunc(case when CURRENT_YEAR_BIRTHDAY > to_date( to_char(SYSDATE, 'yyyymmdd'), 'yyyymmdd') "
	        	+ "                     then extract(year from sysdate) - birthyear - 1 "
	        	+ "                else extract(year from sysdate) - birthyear "
	        	+ "           end, -1) ) AS aged "
	        	+ "               FROM  "
	        	+ "    ( "
	        	+ "      select user_name "
	        	+ "           , case when substr(user_jubun,8,1) in('1','3') then '남' else '여' end AS GENDER "
	        	+ "           , case when substr(user_jubun,8,1) in('1','2') then '19' else '20' end || substr(user_jubun, 1, 2) AS BIRTHYEAR "
	        	+ "           , to_date(to_char(sysdate, 'yyyy') || substr(user_jubun,3,4), 'yyyymmdd') AS CURRENT_YEAR_BIRTHDAY "
	        	+ "      from tbl_member "
	        	+ "    ) V ) where aged = ? ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("SA"));
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> CompanyDTODetail = new HashMap<>(); 

	           CompanyDTODetail.put("USER_NAME", rs.getString("USER_NAME"));
	           CompanyDTODetail.put("MAGE", rs.getString("MAGE"));
	           CompanyDTODetail.put("AGED", rs.getString("AGED"));
	        
	        

	            mapList.add(CompanyDTODetail);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return mapList;

}

	//모든 업종 목록을 list에 담아주는 메소드 생성.
	@Override
	public List<String> view_all_buss_type_list() {
		
		List<String> list = new ArrayList<>();
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select buss_type_name "
              		+ "                    from tbl_buss_type ";
              
              pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              while(rs.next()) {
            	  int i = 0;
            	  list.add(i,rs.getString("buss_type_name"));
            	  i++;
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		return list;
	}


	@Override
	public String get_buss_type_code(String string) {
		String buss_type_code = "";
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select pk_buss_type_code "
              		+ "      from tbl_buss_type "
              		+ "      where buss_type_name = ? ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, string);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              if(rs.next()) {
            	  buss_type_code = rs.getString("pk_buss_type_code");
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		
		
		return buss_type_code;
	}

	// 모든 기업규모 목록을 list에 담아주는 메소드 생성.
	@Override
	public List<String> view_all_company_size_list() {
List<String> list = new ArrayList<>();
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select com_size "
              		+ "      from tbl_company_size ";
              
              pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              while(rs.next()) {
            	  int i = 0;
            	  list.add(i,rs.getString("com_size"));
            	  i++;
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		return list;
	}

	// 입력한 기업규모명과 일치하는 코드를 반환.
	@Override
	public String get_company_size_code(String string) {
		String com_size_code = "";
		
		try {
              conn = MyDBConnection.getConn();
              String sql = " select pk_com_size_code "
              		+ "                    from tbl_company_size "
              		+ "                    where com_size = ? ";
              
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, string);
              rs = pstmt.executeQuery(); // sql문 실행
              
              
              if(rs.next()) {
            	  com_size_code = rs.getString("pk_com_size_code");
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();   
           }
		
		
		
		return com_size_code;
	}

	
	
	// 저장한 값을 토대로 회사 상세 정보를 변경해주는 메소드 생성
	@Override
	public int change_company_detail(CompanyDTO company,Map<String, String> paraMap) {

		int result = 0;
		try {
            conn = MyDBConnection.getConn();
            String sql = " update tbl_company_login set company_name = ? "
            		+ "    where pk_fk_company_code = ?  ";
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, paraMap.get("change_company_name"));
            pstmt.setString(2, company.getPK_COMPANY_CODE());
            int n_1 = pstmt.executeUpdate(); // sql문 실행
            
            if(n_1 == 1) {
            	
            	sql = " update tbl_company_detail "
            			+ "set company_name = ? "
            			+ "  , sales = ?  "
            			+ "  , number_of_employee = ? "
            			+ "  , homepage = ? "
            			+ "  , fk_buss_type_code = ? "
            			+ "  , company_address = ? "
            			+ "  , fk_company_size_code = ? "
            			+ "  , company_tel = ? "
            			+ "  , company_email = ? "
            			+ "  where pk_company_code = ? ";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, paraMap.get("change_company_name"));
            	pstmt.setString(2, paraMap.get("change_sales"));
            	pstmt.setString(3, paraMap.get("change_number_of_employee"));
            	pstmt.setString(4, paraMap.get("change_homepage"));
            	pstmt.setString(5, paraMap.get("buss_type_code"));
            	pstmt.setString(6, paraMap.get("change_address"));
            	pstmt.setString(7, paraMap.get("company_size_code"));
            	pstmt.setString(8, paraMap.get("change_tel"));
            	pstmt.setString(9, paraMap.get("change_email"));
            	pstmt.setString(10, company.getPK_COMPANY_CODE());
            	int n_2 = pstmt.executeUpdate();
            	
            	if(n_2 == 1) {
            		
            		do {
	            		System.out.println(">>> 정말로 수정 하시겠습니까? [Y/N] <<<");
	            		String yn = sc.nextLine();
	            		if("y".equalsIgnoreCase(yn)) {
	            			conn.commit();
	            			conn.setAutoCommit(true);
	            			return result = 1;
	            		}
	            		else if ("n".equalsIgnoreCase(yn)) {
	            			conn.rollback();
	            			conn.setAutoCommit(true);
	            			return result = 0;
	            		}
	            		else {
	            			System.out.println("[경고] 반드시 Y 혹은 N 둘중에서 선택하세요");
	            		}
	            		
            		} while(true);
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


	
	
}
>>>>>>> c9027385ac9e12d196bf66c374e5852e058ed599
