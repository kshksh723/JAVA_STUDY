package jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DQL_select_where_PreparedStatement_03 {

	public static void main(String[] args) {
		Connection conn = null;		// Connection conn 데이터베이스 서버와 연결을 맺어주는 자바의 객체이다. Connection conn 이 오라클 데이터베이스 서버이다 
		PreparedStatement pstmt = null;
		// PreparedStatement pstmt 은 Connection conn(연결한 DB서버)에 전송할 SQL문(편지)을 전송(전달)을 해주는 객체(우편배달부)이다.
	
		ResultSet rs = null;
		//ResultSet rs은 select 되어진 결과물이 저장되어지는 곳 
		
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
	
			
			
			// >>>> 3. SQL 문(편지)을 작성한다  <<<
	String sql =  "  select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday "
					+ " from tbl_memo"
					+ " order by no desc ";
	// 공백을 주지 않으면 sql oracle에서 오류가 남.. 좌우에 공백을 줘야한다 꼭 기억
			// sql문을 작성할 때 1줄 마다 앞, 뒤로 공백을 꼭 주도록 한다.
	// sql문 뒤에 ;을 넣으면 오류 
		
			
		
			
		//4.	연결한 오라클서버(conn)에 SQL문(편지)을 전달할 객체 PreparedStatement 객체(우편배달부) 생성하기 
			
		pstmt = conn.prepareStatement(sql); //pstmt = conn.prepareStatement(sql); 이렇게 꼭 해줘야 함 

		
		
		
	
		
			
		// >> 5. PreparedStatement 객체(우편배달부)는 작성된 SQL문(편지) 오라클 서버에 보내서 실행이 되도록 해야 한다. <<<
		
	rs = pstmt.executeQuery();
		
		
	/*
       sql문이 DQL(SELECT)이므로  .executeQuery(); 을 사용해야 한다 
      */
	
		System.out.println("-".repeat(70));
		System.out.println("글번호\t글쓴이\t글내용\t작성일자");
		System.out.println("-".repeat(70));
		
		
		
		/*
        === 인터페이스 ResultSet 의 주요한 메소드 ===
        1. next()  : select 되어진 결과물에서 커서를 다음으로 옮겨주는 것         리턴타입은 boolean 
        2. first() : select 되어진 결과물에서 커서를 가장 처음으로 옮겨주는 것     리턴타입은 boolean
        3. last()  : select 되어진 결과물에서 커서를 가장 마지막으로 옮겨주는 것    리턴타입은 boolean
        
        == 커서가 위치한 행에서 컬럼의 값을 읽어들이는 메소드 ==
        getInt(숫자) : 컬럼의 타입이 숫자이면서 정수로 읽어들이때
                      파라미터 숫자는 컬럼의 위치값 
                      
        getInt(문자) : 컬럼의 타입이 숫자이면서 정수로 읽어들이때
                      파라미터 문자는 컬럼명 또는 alias명 
                       
        getLong(숫자) : 컬럼의 타입이 숫자이면서 정수로 읽어들이때
                       파라미터 숫자는 컬럼의 위치값 
                      
        getLong(문자) : 컬럼의 타입이 숫자이면서 정수로 읽어들이때
                       파라미터 문자는 컬럼명 또는 alias명                
        
        getFloat(숫자) : 컬럼의 타입이 숫자이면서 실수로 읽어들이때
                        파라미터 숫자는 컬럼의 위치값 
                      
        getFloat(문자) : 컬럼의 타입이 숫자이면서 실수로 읽어들이때
                        파라미터 문자는 컬럼명 또는 alias명 
                           
        getDouble(숫자) : 컬럼의 타입이 숫자이면서 실수로 읽어들이때
                         파라미터 숫자는 컬럼의 위치값 
                      
        getDouble(문자) : 컬럼의 타입이 숫자이면서 실수로 읽어들이때
                         파라미터 문자는 컬럼명 또는 alias명    
                             
        getString(숫자) : 컬럼의 타입이 문자열로 읽어들이때
                         파라미터 숫자는 컬럼의 위치값 
                      
        getString(문자) : 컬럼의 타입이 문자열로 읽어들이때
                         파라미터 문자는 컬럼명 또는 alias명                                                        
     */
		StringBuilder sb = new StringBuilder();
		
		while (rs.next()) {
			
			
			/*
            rs.next() 는 select 되어진 결과물에서 위치(행의 위치)를 다음으로 옮긴 후 
            행이 존재하면 true 를 리턴해주고, 행이 없으면 false 를 리턴해주는 메소드이다.
         */
					int no = rs.getInt("no"); // 컬럼name--> no를 쓴다, 대소문자 구분치 않는다
					// 또는 int no =rs.getInt("NO"); // "NO"은 select 해온 컬럼명이다
					//	또는 int no =	rs.getInt(1); // 1은 select 해 온 컬럼의 위치값으로써 1번째 컬럼을 가리키는 것이다  
					
			
				String name = rs.getString("name"); //"name"은 select 해온 컬럼명이다
			//	또는 String name = rs.getString(2);	// 2는 select 해 온 컬럼의 위치 값으로써 2번째 컬럼을 가리키는 것이다
				
				// name 컬럼은 varchar2 문자열 타입이다 문자면 getString을 쓴다
				
				String msg = rs.getString("msg"); //"msg"은 select 해온 컬럼명이다
				//	또는 String msg = rs.getString(3);	// 3은 select 해 온 컬럼의 위치 값으로써 3번째 컬럼을 가리키는 것이다
				
				String writeday = rs.getString("writeday"); //"writeday"은 select 해온 컬럼명이다
				//	또는 String writeday = rs.getString(4);	// 4는 select 해 온 컬럼의 위치 값으로써 4번째 컬럼을 가리키는 것이다
			
					sb.append(no);
					sb.append("\t"+ name);
					sb.append("\t" + msg);
					sb.append("\t" + writeday+"\n"); 
				}//end of while(rs.next()) ---------
				System.out.println(sb.toString()); // StringBuilder에 쌓인 값들을  toString으로 쌓아준다
	/////////////////////////////////////////////////////////////////////////////////////////////////
				
				// ==== StringBuilder sb를 초기화 하기 ========== // 
				
		//		sb = new StringBuilder(); 
			// 또는 
				sb.setLength(0); // 초기화
				
				sb.append("------------------>> 조회할 대상 <<<--------------\n");
				sb.append("1. 글번호	2. 글쓴이	 3. 글내용	4. 작성일자	5. 종료\n");
				sb.append("-----------------------------------------------\n");
				
				String menu = sb.toString();
				
				String menuNo = "";
				do {
					/////////////////////////////////////////////
				System.out.println(menu);
				System.out.print("> 번호선택 : ");
				menuNo = sc.nextLine();
				
				String colName = ""; //where 절에 들어올 컬럼명
				String searchType = "";
				
				switch (menuNo) {
					case "1":	// 글번호
						colName = "no";
						searchType = "글번호중";
						break;
					case "2":// 글쓴이
						colName = "name";
						searchType = "글쓴이중";
						break;
					case "3":// 글내용
						colName = "msg";
						searchType = "글내용중";
						break;
					case "4":// 작성일자
						colName = "to_char(writeday, 'yyyy-mm-dd)";
						searchType = "작성일자중";
						break;
						
					case "5":// 종료
						
						break;

				default:
					System.out.println("~~~~~~~ 메뉴에 없는 번호 입니다 ~~~~~~~~~~~ \n");
					break;
				}// end of sw`itch(menuNo) 
				
				if("1".equals(menuNo) || "2".equals(menuNo) || "3".equals(menuNo) || "4".equals(menuNo)) {
				
				
						System.out.println("== [주의] 작성일자 검색이라면 2024-03-07와 같이 입력하세요 == ");
						System.out.print("> 검색어 : ");
						String search = sc.nextLine();
						
						
						sql =  "  select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday "
								+ " from tbl_memo";
								
								
					if( !"3".equals(menuNo) )	{
						sql += " where " +colName+ " = ? ";
						/*
				                !!!! 중요 !!!!
				                컬럼명 또는 테이블명은 위치홀더인 ? 를 쓰면 안되고 
				                반드시 변수로 처리 해야 한다.!!!!!
				                데이터값만 위치홀더인 ? 를 써야 한다.!!!!! 
		            */
					}else { // 글내용으로 검색시 
						sql += " where " +colName+ " like '%'|| ? ||'%' ";
					}
								 // 앞 뒤로 공백이 없으면 안됌 
								sql +=  " order by no desc ";
							
								
					pstmt = conn.prepareStatement(sql); 
					pstmt.setString(1, search);
					try {
							rs = pstmt.executeQuery(); // sql문 실행
					} catch(SQLException e) {
						if(e.getErrorCode() == 1722) {
							System.out.println(">> [경고] 글번호 검색어는 숫자만 가능합니다 !! \n");
							continue;
						}
					}
					
				
					
			// ==== StringBuilder sb를 초기화 하기 ========== // 
					
			//	sb = new StringBuilder(); 
			// 또는 
			sb.setLength(0);			
					// 인터페이스 resultset의 주요한 메서드 next를 많이 쓰인다 ! 
			int cnt = 0;
			while(rs.next()) { // 다음에가서 행이 있으면 
				cnt++;
					
				if(cnt == 1) {
					System.out.println("-".repeat(70));
					System.out.println("글번호\t글쓴이\t글내용\t작성일자");
					System.out.println("-".repeat(70));
				}
				
				int no = rs.getInt(1); // 컬럼name--> no를 쓴다, 대소문자 구분치 않는다
				String name = rs.getString(2); //"name"은 select 해온 컬럼명이다
				String msg = rs.getString(3); //"msg"은 select 해온 컬럼명이다
				String writeday = rs.getString("writeday"); //"writeday"은 select 해온 컬럼명이다
			
				sb.append(no);
				sb.append("\t"+ name);
				sb.append("\t" + msg);
				sb.append("\t" + writeday+"\n"); 
			}// end of while(rs.next()) 
			
			if(cnt > 0) { // 검색되어진 결과물이 존재하는 경우 
				System.out.println(sb.toString()); // 바꿔서 찍으라 
			}
			else {// 검색되어진 결과물이 없을 경우
				System.out.println(">>> " + searchType +" "+search+"에 해당하는 데이터는 없습니다. <<< \n");
			}
			
		}// end of if--------------------
				
						///////////////////////////////////				
				} while(!("5".equals(menuNo)));
				
				// end of do ~ while----------------------------------
				
				
				
				} catch (ClassNotFoundException e) {// Unhandled exception type ClassNotFoundException를 이미 올려다 두지 않고 파일이 없는 상태에서 해봤자 안된다 surrond
					System.out.println(">>> objdbc8.jar 파일이 없습니다 <<< ");
				
				}catch(SQLException e){
					
							e.printStackTrace();
					
					// System.out.println("~~오류코드번호 : " + e.getErrorCode());
				
				} finally {
					// >> 6. 사용하였던 자원을 반납하기 <<
					// 반납의 순서는 생성순의 역순으로 한다
					
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
	
		sc.close();
		System.out.println("~~~~~프로그램 종료 ~~~~~~~~~~~");

	
	} // end of main() ----------------------------------



}
