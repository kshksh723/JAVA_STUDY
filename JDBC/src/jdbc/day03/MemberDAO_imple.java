package jdbc.day03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberDAO_imple implements MemberDAO {

	// field, attribute, property, 속성
	Connection conn;
	PreparedStatement pstmt; // = null이 빠진거랑 똑같다 
	ResultSet rs;
	
	// method, operation, 기능
	
	// 자원반납을 해주는 메소드
	private void close() {
		try {
			
				if( rs != null ) {rs.close(); rs = null;}
				if( pstmt != null ) {pstmt.close(); pstmt = null;}
			
		} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}// private void close() 
	
	
	// 회원가입(insert) 메소드 
	@Override
	public int memberRegister(MemberDTO member) {
		
		int result = 0;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // connection은 db 서버 
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");  // Unhandled exception type SQLException
		
			String sql = " insert into tbl_member(userseq, userid, passwd, name, mobile) " // 문자열(userseq, userid, passwd, name, mobile)
					+ " values(userseq.nextval, ?, ?, ?, ?) ";
			
		
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		pstmt.setString(1, member.getUserid() ); //dto에 member를 보여달라 
		pstmt.setString(2, member.getPasswd()); // 2 는 String sql 에서 두번째 위치홀더(?)를 말한다. 두번째 위치홀더(?)에 msg 을 넣어준다.
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getMobile());
		
		
		result = pstmt.executeUpdate(); // sql문 실행 
		
		
		} catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
			System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
		
		}catch(SQLException e){
	        
	             e.printStackTrace();
	          }
		return result;
		}// end of public int memberRegister(MemberDTO member) 

	
	
	// ==  로그인처리(select) 메소드 == 
	@Override
	public MemberDTO login(Map<String, String> paraMap) {
		
	MemberDTO member = null;

	try {
		
		Class.forName("oracle.jdbc.driver.OracleDriver"); // connection은 db 서버 
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");  // Unhandled exception type SQLException
	
		String sql = " select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as registerday "
				+ " from tbl_member "
				+ " where status = 1 and userid = ? and passwd = ? ";
		
	
		
	pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
	pstmt.setString(1, paraMap.get("userid") ); //dto에 member를 보여달라 
	pstmt.setString(2, paraMap.get("passwd")); // 2 는 String sql 에서 두번째 위치홀더(?)를 말한다. 두번째 위치홀더(?)에 msg 을 넣어준다.
	
	
	
	rs = pstmt.executeQuery(); // sql문 실행 
	 	if(rs.next()) {
		member = new MemberDTO();
		
		member.setUserseq(rs.getInt("userseq")); // 대소문자 구분치 않다 
		member.setUserid(rs.getString("userid"));
		member.setName(rs.getString("name"));
		member.setMobile(rs.getString("mobile"));
		member.setPoint(rs.getInt("point"));
		member.setRegisterday(rs.getString("registerday"));
	}
	
	} catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
		System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
	
	}catch(SQLException e){
         if(e.getErrorCode() == 1) {
         
             e.printStackTrace();
          }
		
	} finally {
		close();
	 
	}	
		return member;
	}// end of public MemberDTO login(Map<String, String> paraMap)


	// 회원탈퇴
	@Override
	public int memberDelete(int userseq) {
		int result = 0;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // connection은 db 서버 
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");  // Unhandled exception type SQLException
		
			String sql = " update tbl_member set status = 0 " // 문자열(userseq, userid, passwd, name, mobile)
					+ " where userseq = ? ";
		
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		pstmt.setInt(1, userseq ); //dto에 member를 보여달라 
	
		
		
		result = pstmt.executeUpdate(); // sql문 실행 
		
		
		} catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
			System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
		
		}catch(SQLException e){
	        
	             e.printStackTrace();

		} finally {
			close();
		 
		}	
		
		return result; 
		
	} // end of public 
	

	//모든 회원을 조회해주는 메소드 
	@Override
	public List<MemberDTO> showAllMember() {
		
		List<MemberDTO> memberList = new ArrayList<>();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // connection은 db 서버 
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");  // Unhandled exception type SQLException
		
			String sql = " select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as registerday "
					+ "     , status  "
					+ " from tbl_member "
					+ " order by userseq asc ";
			
		
		
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		
		
		rs = pstmt.executeQuery(); // sql문 실행 
		 	while (rs.next()) {
		MemberDTO member = new MemberDTO();
			
			member.setUserseq(rs.getInt("userseq")); // 대소문자 구분치 않다 
			member.setUserid(rs.getString("userid"));
			member.setName(rs.getString("name"));
			member.setMobile(rs.getString("mobile"));
			member.setPoint(rs.getInt("point"));
			member.setRegisterday(rs.getString("registerday"));
			member.setStatus(rs.getInt("status"));
			
			memberList.add(member);
		} // end of while
		
		} catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
			System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
		
		}catch(SQLException e){
	         if(e.getErrorCode() == 1) {
	         
	             e.printStackTrace();
	          }
			
		} finally {
			close();
		 
		}	
		
		return memberList;
	} // end of pulibc list
}
