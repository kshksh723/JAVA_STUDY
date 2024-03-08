package jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DQL_update_PreparedStatement_05 {

   public static void main(String[] args) {
      
      Connection conn = null; 
      // Connection conn 은 데이터베이스 서버와 연결을 맺어주는 자바의 객체이다. 
      
      PreparedStatement pstmt = null;
      // PreparedStatement pstmt 은 Connection conn(연결한 DB서버)에 전송할 SQL문(편지)을 전송(전달)을 해주는 객체(우편배달부)이다.
      
      ResultSet rs = null;
      // ResultSet rs 은 select 되어진 결과물이 저장되어지는 곳.
      
      
      Scanner sc = new Scanner(System.in);
      
      try {
         // >>> 1. 오라클 드라이버 로딩 <<<
         /*
            === OracleDriver(오라클 드라이버)의 역할 ===
            1). OracleDriver 를 메모리에 로딩시켜준다.
            2). OracleDriver 객체를 생성해준다.
            3). OracleDriver 객체를 DriverManager에 등록시켜준다.
                --> DriverManager 는 여러 드라이버들을 Vector 에 저장하여 관리해주는 클래스이다.
         */
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         
         // >>> 2. 어떤 오라클 서버에 연결을 할래? <<<
         System.out.print("▷ 연결할 오라클 서버의 IP 주소 : ");
         String ip = sc.nextLine();  //  127.0.0.1
         
         conn = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":1521:xe", "JDBC_USER", "gclass");
         
         // >>> 3. SQL문(편지)을 작성한다. <<<
         String sql = " select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday "
                  + " from tbl_memo "
                  + " order by no desc "; 
         // SQL문을 작성할때 1줄 마다 앞,뒤로 공백을 꼭 주도록 한다.!!!
         // SQL문 뒤에 ; 을 넣으면 오류이다.!!!!
         
         // >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 객체 PreparedStatement 객체(우편배달부) 생성하기  <<<
         pstmt = conn.prepareStatement(sql);
         
         
         // >>> 5. PreparedStatement 객체(우편배달부)는 작성된 SQL문(편지) 오라클 서버에 보내서 실행이 되도록 해야 한다. <<<
         rs = pstmt.executeQuery(); // sql문 실행 
         /*
             SQL문이 DQL(select)이므로 .executeQuery(); 을 사용해야 한다. 
         */
         
         System.out.println("-".repeat(70));
         System.out.println("글번호\t글쓴이\t글내용\t작성일자");
         System.out.println("-".repeat(70));
         
         /*
            === 인터페이스 ResultSet 의 주요한 메소드 ===
            1. next()  : select 되어진 결과물에서 커서를 다음으로 옮겨주는 것         리턴타입은 boolean  있으면 true 없으면 false
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
         
         while(rs.next()) {
            /*
               rs.next() 는 select 되어진 결과물에서 위치(행의 위치)를 다음으로 옮긴 후 
               행이 존재하면 true 를 리턴해주고, 행이 없으면 false 를 리턴해주는 메소드이다.
            */
            
            int no = rs.getInt("no"); // "no" 은 select 해온 컬럼명이다.
      // 또는   int no = rs.getInt("NO"); // "NO" 은 select 해온 컬럼명이다.
      // 또는   int no = rs.getInt(1);    // 1 은 select 해온 컬럼의 위치값으로써 1번째 컬럼을 가리키는 것이다.
            
            String name = rs.getString("name");  // "name" 은 select 해온 컬럼명이다.
        // 또는  String name = rs.getString(2);       // 2 는 select 해온 컬럼의 위치값으로써 2번째 컬럼을 가리키는 것이다. 
            
            String msg = rs.getString("msg");   // "msg" 은 select 해온 컬럼명이다.
      // 또는  String msg = rs.getString(3);       // 3 은 select 해온 컬럼의 위치값으로써 3번째 컬럼을 가리키는 것이다. 
            
            String writeday = rs.getString("writeday");   // "writeday" 은 select 해온 컬럼명이다.
       // 또는  String writeday = rs.getString(4);            // 4 는 select 해온 컬럼의 위치값으로써 4번째 컬럼을 가리키는 것이다.  
            
            sb.append(no);
            sb.append("\t"+name);
            sb.append("\t"+msg);
            sb.append("\t"+writeday+"\n");
            
         }// end of while( rs.next() )------------------------
            
         System.out.println(sb.toString());
         
         ////////////////////////////////////////////////////////////////
         String no = "";
         while(true) {
            System.out.print("▷ 삭제할 글번호 : ");
            no = sc.nextLine();
            
            sql = " select name, msg "
               + " from tbl_memo "
               + " where no = to_number(?) ";
            
            pstmt.close();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, no);
            
            rs.close();
            try {
               rs = pstmt.executeQuery(); //sql문을 실행
               
               break; // 정상이라면 빠져나옴
            } catch(SQLException e) {
               if(e.getErrorCode() == 1722) {
                  System.out.println(">> [경고] 삭제할 글번호는 숫자만 가능합니다.!! \n");
                  continue; //비정상이라면 다시 시작
               }
            }
         } // end of while(true) ------------------------
            
         //행이 1개밖에 없으므로(where no 했고, no는 pk라 한 행만 존재) if
         if(rs.next()) {// 삭제해야할 글번호가 존재하는 경우
            
            String before_name = rs.getString(1);
            String before_msg = rs.getString(2);
            
            System.out.println("\n ===== 삭제하기 전 내용 ===== ");
            System.out.println("\n □ 글쓴이 : " + before_name);
            System.out.println(" □ 글내용 : " + before_msg);
            
            String yn = "";
            do {
               System.out.println("> 정말로 삭제하시겠습니까?[Y/N] : ");
               yn = sc.nextLine();
               
               if("y".equalsIgnoreCase(yn)) { //equalsIgnoreCase 대소문자 구분
                  
                  sql = " delete tbl_memo "
                        + " where no = ? ";
                  
                  pstmt = conn.prepareStatement(sql);
                  pstmt.setString(1, no);

                  int n = pstmt.executeUpdate(); // sql문을 실행
                  if(n==1) {
                     // n==1 이라는 것은 delete 구문이 성공되었다는 말이다.
                     System.out.println(">> 데이터 삭제 성공!! << \n");
                  
                     sql = " select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday "
                              + " from tbl_memo "
                              + " order by no desc "; 
                     
                     pstmt.close();
                     pstmt = conn.prepareStatement(sql); //노란줄 나올땐 닫아주면 됨
                     
                     
                     rs = pstmt.executeQuery(); // sql문 실행 
                     System.out.println("-".repeat(70));
                     System.out.println("글번호\t글쓴이\t글내용\t작성일자");
                     System.out.println("-".repeat(70));
                     
                     sb.setLength(0);
                     while(rs.next()) {

                        int num = rs.getInt("no");
                        String name = rs.getString("name");  
                        String msg = rs.getString("msg");   
                        String writeday = rs.getString("writeday");  
                        
                        sb.append(num);
                        sb.append("\t"+name);
                        sb.append("\t"+msg);
                        sb.append("\t"+writeday+"\n");
                        
                     }// end of while( rs.next() )------------------------
                        
                     System.out.println(sb.toString());
                     
                  } 
               }
               else if("n".equalsIgnoreCase(yn)) { //equalsIgnoreCase 대소문자 구분
                  System.out.println(">> 데이터 삭제 취소!! <<");
               }
               else {
                  System.out.println(">> Y 또는 N 만 입력하세요!! << \n");
               }
            } while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
            
         } else { // 삭제해야할 글번호가 DB에 존재하지 않는 경우
            System.out.println("글번호 "+ no +"는 존재하지 않습니다.");
            }
      } catch (ClassNotFoundException e) {
         System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         // >>> 6. 사용하였던 자원을 반납하기 <<<
         // 반납의 순서는 생성순의 역순으로 한다.
      
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
        System.out.println("~~~ 프로그램 종료 ~~~");
      
   }// end of main()-------------------------------------------

}