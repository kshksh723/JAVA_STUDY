package jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DML_insert_PreparedStatement_01 {

	public static void main(String[] args) {
		Connection conn = null;		// Connection conn 데이터베이스 서버와 연결을 맺어주는 자바의 객체이다. Connection conn 이 오라클 데이터베이스 서버이다 
		PreparedStatement pstmt = null;
		// PreparedStatement pstmt 은 Connection conn(연결한 DB서버)에 전송할 SQL문(편지)을 전송(전달)을 해주는 객체(우편배달부)이다.
	
		Scanner sc = new Scanner(System.in); // ip를 받을 것을 스캔한다
		// >>> 1. 오라클 드라이버 로딩 <<<
		

		try {
			/*
	        === OracleDriver(오라클 드라이버)의 역할 ===
	        1). OracleDriver 를 메모리에 로딩시켜준다.
	        2). OracleDriver 객체를 생성해준다.
	        3). OracleDriver 객체를 DriverManager에 등록시켜준다.
	            --> DriverManager 는 여러 드라이버들을 Vector 에 저장하여 관리해주는 클래스이다.
	     */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			// 2. 어떤 오라클 서버에 연결을 할래? <<<
			System.out.print("> 연결할 오라클 서버의 IP 주소 : ");
			String ip = sc.nextLine();  // 127.0.0.1
			
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":1521:xe", "JDBC_USER", "gclass");  // Unhandled exception type SQLException
		// == Connection conn에서 DML의 기본값은 auto commit이다 ==///
			// == Connection conn에서 DML의 기본값은 auto commit을 수동 commit으로 전환하겠습니다  ==///
			conn.setAutoCommit(false); // false가 수동 commit으로 전환 
	//		conn.setAutoCommit(true); // 자동 commit
			// 대부분 안적음.. 대부분 auto commit 
			
			
			// >>>> 3. SQL 문(편지)을 작성한다  <<<
			System.out.println("> 글쓴이 : ");
			String name = sc.nextLine();

			System.out.println("> 글내용 : ");
			String msg = sc.nextLine();
	
			/*
			String sql = " insert into tbl_memo(no, name, msg) "
					+ " values(seq_memo.nextval,'" +name+ "', '" +msg+"')";
			//위와 같이 변수의 값을 직접 sql문에 대입시켜 버리면 외부에서 입력한 데이터값이 보여지게 된다
			 // 그래서 아래처럼 위치홀더(?)를 사용한다
			  * 
			 
	*/		
			
			String sql = " insert into tbl_memo(no, name, msg) "
					+ " values(seq_memo.nextval,?,?)";
			// ?를 "위치홀더"라고 부른다  !! 
			// sql문 뒤에 ;를 넣으면 오류이다 
			
			/*
			 		String sql = " select into tbl_memo(no, name, msg) "
					+ " values(seq_memo.nextval,'" +name+ "', '" +msg+"')";
					
					
					--> .executeQuery();를 써야 한다 
			 */
			
			// 위와 같이 변수의 값을 직접 sql문에 대입시켜버리면 외부에서 입력한 데이터값이 보여지므로 보안상 위험해집니다. 
			
			
	
			// >>>>  PreparedStatement pstmt 객체는(우편배달부)는 작성된 sql문(편지)을 오라클 서버에 보내서 실행이 되도록 해주는 객체이다   <<<
			
			
		//4.	연결한 오라클서버(conn)에 SQL문(편지)을 전달할 객체 PreparedStatement 객체(우편배달부) 생성하기 
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 
		pstmt.setString(1,name ); // 1 은 String sql 에서 첫번째 위치홀더(?)를 말한다. 첫번째 위치홀더(?)에 name 을 넣어준다.
		pstmt.setString(2,msg ); // 2 는 String sql 에서 두번째 위치홀더(?)를 말한다. 두번째 위치홀더(?)에 msg 을 넣어준다.
		
		
		
		System.out.println("~~ 확인용 sql => " + sql );
		
			
		// >> 5. PreparedStatement 객체(우편배달부)는 작성된 SQL문(편지) 오라클 서버에 보내서 실행이 되도록 해야 한다. <<<
		
		int n = pstmt.executeUpdate(); //  중요하다 
		
		
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
		
		System.out.println("~~ 확인용 n => " + n );
		
		if(n ==1) {
			String yn = "";
			
				do {
						//////////////////////이것이 계속 반복////////////////////////////
						System.out.print("> 정말로 입력하시겠습니까? [Y/N] : ");
						yn = sc.nextLine();
						
						if("y".equalsIgnoreCase(yn)) {// 대소문자 구분치 않는다 
						conn.commit();	// 커밋 
						System.out.println(">> 데이터 입력 성공 !! << ");
							
					}	else if("n".equalsIgnoreCase(yn)) {// 대소문자 구분치 않는다 
						conn.rollback();	// 롤백 
						System.out.println(">> 데이터 입력 취소 !! << ");
							
					}
					else {
						System.out.println(">> Y  또는 N 만 입력하세요!! << \n");
					}
				
					//////////////////////////////////////////////////////////
				
			} while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn) ));
			}
		} catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
			System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
		
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			// >> 6. 사용하였던 자원을 반납하기 <<
			// 반납의 순서는 생성순의 역순으로 한다
			
			try {
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
		
		
		
		
		sc.close();
		System.out.println("~~~~~프로그램 종료 ~~~~~~~~~~~");
	
	
	
	
	
	
	
	} // end of main() ----------------------------------

}
