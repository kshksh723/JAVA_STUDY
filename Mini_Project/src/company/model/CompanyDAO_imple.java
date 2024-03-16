package company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import company.domain.CompanyDTO;
import dbconnection.MyDBConnection;
import member.domain.MemberDTO;

public class CompanyDAO_imple implements CompanyDAO {

	private Connection conn = MyDBConnection.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs= null;
	
	// 자원반납을 해주는 메소드
			private void close() {
				try {
					
						if( rs != null ) {rs.close(); rs = null;}
						if( pstmt != null ) {pstmt.close(); pstmt = null;}
					
				} catch (SQLException e) {
				e.printStackTrace();
			}
			
			}// private void close() 
	/////////////////회사명 검색
	@Override
	public CompanyDTO  search_comname (Map<String, String> paraMap) {
		CompanyDTO CompanyDetail = null;

		try {
			
		
			String sql =
					 "  select  Fk_buss_type_code, Pk_buss_type_code, company_name, buss_type_name, company_name, sales, number_of_employee  "
					 + "     , homepage ,establishment_date , boss_name ,company_address , fk_buss_type_code , fk_company_size_code    "
					 + "     , com_size, buss_type_name  "
					 + "    from tbl_company_detail "
					 + "    join tbl_buss_type "
					 + "    on tbl_company_detail.fk_buss_type_code = tbl_buss_type.Pk_buss_type_code "
					 + "    join tbl_company_size "
					 + "    on tbl_company_detail.fk_company_size_code = tbl_company_size.pk_com_size_code "
					 + "    where company_name =  ? ";
			  
		
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		pstmt.setString(1, paraMap.get("COMPANY_NAME") ); //dto에 member를 보여달라 
		
		
		
		rs = pstmt.executeQuery(); // sql문 실행 
		 	if(rs.next()) {
		 		CompanyDetail = new CompanyDTO();
			
		 		CompanyDetail.setCOMPANY_NAME(rs.getString("COMPANY_NAME")); // 대소문자 구분치 않다 
		 		CompanyDetail.setSALES(rs.getString("SALES")); // 대소문자 구분치 않다 
		 		CompanyDetail.setNUMBER_OF_EMPLOYEE(rs.getString("NUMBER_OF_EMPLOYEE")); // 대소문자 구분치 않다 
		 		CompanyDetail.setHOMEPAGE(rs.getString("HOMEPAGE")); // 대소문자 구분치 않다 
		 		CompanyDetail.setESTABLISHMENT_DATE(rs.getString("ESTABLISHMENT_DATE")); // 대소문자 구분치 않다 
		 		CompanyDetail.setBOSS_NAME(rs.getString("BOSS_NAME")); // 대소문자 구분치 않다 
		 		CompanyDetail.setCOMPANY_ADDRESS(rs.getString("COMPANY_ADDRESS")); // 대소문자 구분치 않다 
		 		CompanyDetail.setBUSS_TYPE_NAME(rs.getString("BUSS_TYPE_NAME")); // 대소문자 구분치 않다 
		 		CompanyDetail.setCOM_SIZE(rs.getString("COM_SIZE")); // 대소문자 구분치 않다 
				
				return CompanyDetail;
		 	}
		 	else {
		 		return null;
		 	}
		
		} catch(SQLException e){
	         if(e.getErrorCode() == 1) {
	         
	             e.printStackTrace();
	          }
			
		} finally {
		close();
		 
		}
		return null;
	}
	//////////////////////////////////////////////////////
	
	@Override
	public CompanyDTO search_buss_type(Map<String, String> paraMap) {
		CompanyDTO CompanyDetail = null;

		try {
			
		
			String sql =
					 "      select company_name, sales, number_of_employee, homepage ,establishment_date , boss_name "
					 + " ,company_address  , com_size, buss_type_name "
					 + "    from tbl_company_detail "
					 + "    join tbl_buss_type "
					 + "    on tbl_company_detail.fk_buss_type_code = tbl_buss_type.Pk_buss_type_code "
					 + "    join tbl_company_size "
					 + "    on tbl_company_detail.fk_company_size_code = tbl_company_size.pk_com_size_code "
					 + "    where buss_type_name LIKE '%' || ? || '%' ";
			  
		
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		pstmt.setString(1, paraMap.get("SEARCH_BUSS") ); //
		
		
		
		rs = pstmt.executeQuery(); // sql문 실행 
		 	if(rs.next()) {
		 		CompanyDetail = new CompanyDTO();
			
		 		CompanyDetail.setCOMPANY_NAME(rs.getString("COMPANY_NAME")); // 대소문자 구분치 않다 
		 		CompanyDetail.setSALES(rs.getString("SALES")); // 대소문자 구분치 않다 
		 		CompanyDetail.setNUMBER_OF_EMPLOYEE(rs.getString("NUMBER_OF_EMPLOYEE")); // 대소문자 구분치 않다 
		 		CompanyDetail.setHOMEPAGE(rs.getString("HOMEPAGE")); // 대소문자 구분치 않다 
		 		CompanyDetail.setESTABLISHMENT_DATE(rs.getString("ESTABLISHMENT_DATE")); // 대소문자 구분치 않다 
		 		CompanyDetail.setBOSS_NAME(rs.getString("BOSS_NAME")); // 대소문자 구분치 않다 
		 		CompanyDetail.setCOMPANY_ADDRESS(rs.getString("COMPANY_ADDRESS")); // 대소문자 구분치 않다 
		 		CompanyDetail.setBUSS_TYPE_NAME(rs.getString("BUSS_TYPE_NAME")); // 대소문자 구분치 않다 
		 		CompanyDetail.setCOM_SIZE(rs.getString("COM_SIZE")); // 대소문자 구분치 않다 
				
				return CompanyDetail;
		 	}
		 	else {
		 		return null;
		 	}
		
		} catch(SQLException e){
	         if(e.getErrorCode() == 1) {
	         
	             e.printStackTrace();
	          }
			
		} finally {
		close();
		 
		}
		return null;
	}
	
}
