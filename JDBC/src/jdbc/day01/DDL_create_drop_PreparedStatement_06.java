package jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DDL_create_drop_PreparedStatement_06 {

	public static void main(String[] args) {
		  Connection conn = null; 
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	          try {
	      
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	      conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");
	  
	      String sql_1 = " select * "
	      		+ " from user_tables "
	      		+ " where table_name = 'TBL_MEMO' ";
	      
	      String sql_2 = " drop table TBL_MEMO cascade constraints purge ";
	      					// TBL_MEMO 테이블이 자식 테이블이 없을 경우 삭제하는 것 
	      
	     // String sql_2 = " drop table TBL_MEMO cascade constraints purge ";
			// TBL_MEMO 테이블이 자식 테이블이  있을 경우 먼저 자식 테이블에 존재하는 foregin key를 먼저 삭제한 후 테이블을 삭제하는 것
	    
	      
	      
	      String sql_3 = " create table tbl_memo "
	      		+ " (no          number(4) "
	      		+ " ,name        Nvarchar2(20) not null "
	      		+ " ,msg         Nvarchar2(100) not null "
	      		+ " ,writeday    date default sysdate "
	      		+ " ,updateday	date "
	      		+ " ,constraint  PK_tbl_memo_no primary key(no) "
	      		+ " ) ";
	      
	      String sql_4 = " select * "
	      		+ " from user_sequences "
	      		+ " where sequence_name = 'SEQ_MEMO' ";
	      
	      
	      String sql_5 = " drop sequence SEQ_MEMO ";
	      
	      
	      String sql_6 = " create sequence seq_memo "
			      		+ " start with 1 "
			      		+ " increment by 1 "
			      		+ " nomaxvalue "
			      		+ " nominvalue "
			      		+ " nocycle "
			      		+ " nocache ";
	      
	      String sql_7 = " insert into tbl_memo(no, name, msg) "
					+ " values(seq_memo.nextval,'이순신','안녕하세요 이순신 입니다') ";
	      
	      String sql_8 =  " select * "
	      				+ " from tbl_memo "
	      				+ " order by no desc ";
	      
	      
	      // 생성해야 할 TBL_MEMO 테이블이 이미 기존에 존재하는지 여부를 알아온다
	      pstmt = conn.prepareStatement(sql_1);
	      rs = pstmt.executeQuery(); //sql 문 실행하기
	      
	      if(rs.next()) { // TBL_MEMO 테이블이 이미 존재한다면 
	    	  
	    	  // TBL_MEMO 테이블을 drop 하겠다
	    	  pstmt.close();
	    	  pstmt = conn.prepareStatement(sql_2);
	    	  int n = pstmt.executeUpdate();  // sql 문 실행하기 
	    	  /*
	          .executeUpdate(); 은 SQL문이 DML문(insert, update, delete, merge) 이거나 
	                            SQL문이 DDL문(create, drop, alter, truncate) 일 경우에 사용된다. 
	                
	          SQL문이 DML문이라면 return 되어지는 값은 적용되어진 행의 개수를 리턴시켜준다.
	          예를 들어, insert into ... 하면 1 개행이 입력되므로 리턴값은 1 이 나온다. 
	                 update ... 할 경우에 update 할 대상의 행의 개수가 5 이라면 리턴값은 5 가 나온다. 
	                 delete ... 할 경우에 delete 되어질 대상의 행의 개수가 3 이라면 리턴값은 3 이 나온다.
	                
	          SQL문이 DDL문이라면 return 되어지는 값은 무조건 0 이 리턴된다.
	           
	           
	          .executeQuery(); 은 SQL문이 DQL(select)일 경우에 사용된다. 
	        */
	    	  System.out.println("~~ 확인용 drop" + n);
	    	  // ~~ 확인용 drop table : 0
	      }
	      // tbl_memo 테이블을 생성한다 
    	  pstmt = conn.prepareStatement(sql_3);
    	  int n = pstmt.executeUpdate();  // sql 문 실행하기 
    	  System.out.println("~~~~~~~확인용 create table : " + n);
    	  // 확인용 create table :  0 ~~~~~~~~
	      
    	   pstmt = conn.prepareStatement(sql_4);
    	   rs.close();
 	       rs = pstmt.executeQuery(); //sql 문 실행하기
 	      
 	      if(rs.next()) { // TBL_MEMO 시퀀스가 이미 존재한다면 
 	    	  
 	    	  // TBL_MEMO 시퀀스을 drop 하겠다
 	    	  pstmt.close();
 	    	  pstmt = conn.prepareStatement(sql_5);
 	    	  n = pstmt.executeUpdate();  // sql 문 실행하기 
 	   
 	    	  System.out.println("~~ 확인용 drop sequence " + n);
 	    	  // ~~ 확인용 drop table : 0
 	      }
 	      //SEO_MEMO 시퀀스를 생성한다 
 
 	      
 	      pstmt.close();
	    	  pstmt = conn.prepareStatement(sql_6);
	    	  n = pstmt.executeUpdate(); 
	    	  System.out.println("~~ 확인용 drop sequence " + n);  
	      	  // ~~ 확인용 CREATE table : 0
	    	  
// TBL_MEMO 테이블에 INSERT하기 
	    	  pstmt = conn.prepareStatement(sql_7);
	    	  n = pstmt.executeUpdate(); 
	    	  System.out.println("~~ 확인용 insert into tbl_memo " + n);
	    	  // 확인용 insert into tbl_memo : 1
	    	  
	    	  // TBL_MEMO SELECT 하기
	    	  pstmt = conn.prepareStatement(sql_8);
	    	  rs = pstmt.executeQuery(); // SQL문 실행하기
	    	  
	    	  StringBuilder sb = new StringBuilder();
	    	  int cnt = 0;
	    	  
	    	  while(rs.next()) {
	    		  cnt++;
	    
	    	if(cnt ==1) {
	    		sb.append("=".repeat(100)+"\n");
	    		sb.append("일련번호\t성명\t글내용\t작성일자\t변경일자\n");
	    		sb.append("=".repeat(100)+"\n");
	    	}
	    	sb.append(rs.getInt("NO")+"\t"+
	    			rs.getString("NAME")+"\t"+
	    			rs.getString("MSG")+"\t"+
	    			rs.getString("WRITEDAY")+"\t"+
	    			rs.getString("UPDATEDAY")+"\n"
	    			);
	    		
	    	
	    		
	    }// end of while(rs.next())
	    	  if(cnt > 0) {
	    		  System.out.println(sb.toString());
	    	  }
	    	  else {
	    		  System.out.println(">> 입력된 데이터가 없습니다 <<");
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

	} // end of main()-------------

}
