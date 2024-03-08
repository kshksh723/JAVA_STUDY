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
	    	      
	    	      do {
	    	      ////////////////////////////////////////////////////////////////////////
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
			    	    	//   System.out.println("~~ 확인용 : sql 구문 오류 발생2 ");
					    	       if(e.getErrorCode() == 1722) {
					    	    	   System.out.println("[경고] 학급 및 학급번호를 정수로만 입력하세요 ! ");
					    	       } else if(e.getErrorCode() == 1) { // pk에 위배가 되어짐 
					    	    	   System.out.println("[경고] 입력하신 학번은"+ stno + "은 이미 사용중입니다. 다른 학번을 입력하세요 !! ");
					    	}
					    	     else if (e.getErrorCode() == 2291) {
					    	    	  
					    	    	   System.out.println("[경고] 입력하신 학급번호 "+ fk_classno + "는 존재하지 않는 번호입니다 ");
					    	    
					    	    	   System.out.println(" >> 입력가능한 학급번호는 아래와 같습니다. \n");
					    	    	   
					    	    	   /*
					    	    	    	------------------------------
					    	    	    		학급번호		학급명
					    	    	    	------------------------------
					    	    	    		1			자바웹프로그래밍A
					    	    	    		2			자바웹프로그래밍B
					    	    	    		3			자바웹프로그래밍C
					    	    	    */
					    	       
					    	    	  sql =  " select classno, classname "
					    	    	   		+ " from tbl_class "
					    	    	   		+ " order by 1 "; 
					    	    	   
					    	       pstmt.close(); 
					    	       pstmt = conn.prepareStatement(sql); // sql을 전달할 우편배달부 --> 닫아야한다 --> close  
					    	      
					    	       // sql문 실행
					    	       rs = pstmt.executeQuery(); // sql문 실행하기
					    	       
					    	       int cnt = 0;
					    	       StringBuilder sb = new StringBuilder();
					    	       while (rs.next()) { 
					    	    	   cnt++;
					    	    	   
					    	    	   if(cnt == 1) {
					    	    		   System.out.println("-".repeat(50));
					    	    		   System.out.println(" 학급번호\t학급명");
					    	    		   System.out.println("-".repeat(25));
					    	    	   }
					    	    	   sb.append(rs.getInt("classno")+"\t");
					    	    	   sb.append(rs.getString("classname")+"\n");
					    	    	   
					    	    	   } // end of while(rs.next())
					    	    	   System.out.println(sb.toString());
					    	       }
					    	       else {
					    	    	   e.printStackTrace();
					    	       }
			    	       }   
	    	       
	    	       //////////////////////////////////////////////
	      } while (true);
	     
	      
	      } catch (ClassNotFoundException e) {
	          System.out.println(">>> ojdbc8.jar 파일이 없습니다.   <<<");
	      } catch (SQLException e) {
	    //	  System.out.println("~~ 확인용 sql 구문 오류 발생  1 ");
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

