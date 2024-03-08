package jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLException_insert_PreparedStatement_01 {

	public static void main(String[] args) {
		
	      
	      Connection conn = null; 
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      Scanner sc = new Scanner(System.in);
	      
	      try {
	    	
	    	      Class.forName("oracle.jdbc.driver.OracleDriver");
	    	         
	    	      conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");
	    	      System.out.print("▷ 학번 : ");
	    	      String stno = sc.nextLine();
	    	         
	    	         System.out.print("▷ 성명 : ");
	    	         String name = sc.nextLine();
	    	         
	    	         System.out.print("▷ 연락처 : ");
	    	         String tel = sc.nextLine();
	    	         
	    	         System.out.print("▷ 주소 : ");
	    	         String addr = sc.nextLine();
	    	         
	    	         System.out.print("▷ 학급번호 : ");
	    	         String fk_classno = sc.nextLine();
	    	         
	    	         String sql = " insert into tbl_student(stno, name, tel, addr, fk_classno) "
	    	         		+ " values(to_number(?), ?, ?, ?, to_number(?)) ";
	    	         
	    	       pstmt = conn.prepareStatement(sql);
	    	       pstmt.setString(1, stno);
	    	       pstmt.setString(2, name );
	    	       pstmt.setString(3, tel );
	    	       pstmt.setString(4, addr);
	    	       pstmt.setString(5, fk_classno);
	    	       
	    	       
	    	       try {
			    	     int n =  pstmt.executeUpdate(); // sql문 실행하기 
			    	      
			    	     if(n==1) {
			    	    	 System.out.println(">> 데이터 입력 성공 !! <<");
			    	     }
	    	       } catch(SQLException e)  {
			    	       if(e.getErrorCode() == 1722) {
			    	    	   System.out.println("[경고] 학급 및 학급번호를 정수로만 입력하세요 ! ");
			    	       } else {
			    	    	   e.printStackTrace();
			    	       }
	    	       }   
	      } catch (ClassNotFoundException e) {
	          System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {

		       try {
		            if(rs != null) {
		               rs.close();
		               rs = null;
		            }
		            
		            if(pstmt != null) {
		               pstmt.close();
		               pstmt = null;
		            }
		            
		            if(conn != null) {
		               conn.close();
		               conn = null;
		            }
		            
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		         
		      }
		  
	} // end of main() -------------

}

