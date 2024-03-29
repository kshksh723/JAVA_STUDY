package member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import dbconnection.MyDBConnection;
import member.domain.MemberDTO;


public class MemberDAO_imple implements MemberDAO {

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

		// ==  로그인처리(select) 메소드 == 
		@Override
		public MemberDTO login(Map<String, String> paraMap) {
			MemberDTO member = null;

			try {
				
			
				String sql = " select PK_user_id, user_passwd, user_name "
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
					
					return member;
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
