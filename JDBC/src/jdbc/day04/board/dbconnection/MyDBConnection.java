package jdbc.day04.board.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBConnection {
	// ================= singleton 패턴 만들기 시작 =================== //   
		/*
		    !!! === singleton 패턴에서 중요한 것은 다음의 3가지 이다  === !!!
		    
		    == 첫번째,
		       private 변수로 자기 자신의 클래스 인스턴스를 가지도록 해야 한다.
		       접근제한자가 private 이므로 외부 클래스에서는 직접적으로 접근이 불가하다.
		       또한 static 변수로 지정하여 SingletonNumber 클래스를 사용할 때 
		       객체생성은 딱 1번만 생성되도록 해야 한다.  
		*/   
		
		// --> field (첫번째로 작동) <-- //
		// static 변수로 만든다 
		private static Connection conn = null; //초기화를 안해도 널이다 
		
		
		// --> static 초기화 블럭(두번째로 작동)<-- //
		static {
				try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver"); // connection은 db 서버 
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");  // Unhandled exception type SQLException
				
				}catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
					System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
				
				}catch(SQLException e){
			     e.printStackTrace();
			          
			          
				}
	} // end of static{}
		
		// == 두번째,
		// 생성자에 접근제한자를 반드시 private으로 지정하여, 외부에서 절대로 인스턴스를 생성하지 못하도록 막아버린다
		private MyDBConnection() {}
		
		
		
		
		// == 세번째,
		// static 메소드를 생성[ 지금은 getConn() ]하여 외부에서 해당 클래스의 객체를 사용할 수 있도록 해준다
		public static Connection getConn() {
			return conn;
		}
		
		// ================= singleton 패턴 만들기 끝 =================== //   

		///////////////////////////////////////////////
		// 자원 반납
		// == Connection conn 객체 지원 반납하기
		public static void closeConnection(){
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end of public static void closeConnection()
		
		}
			
