package jdbc.day02;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.Scanner;

/*
 *== HR에서 예전에 생성해두었던 프로시저 pcd_tbl_member_test1_insert을 사용해본다
 *  
      === tbl_member_test1 테이블에 insert 할 수 있는 요일명과 시간을 제한해 두겠습니다. ===
        
          tbl_member_test1 테이블에 insert 할 수 있는 요일명은 월,화,수,목,금 만 가능하며
          또한 월,화,수,목,금 중에 오후 2시 부터 오후 5시 이전까지만(오후 5시 정각은 안돼요) insert 가 가능하도록 하고자 한다.
          만약에 insert 가 불가한 요일명(토,일)이거나 불가한 시간대에 insert 를 시도하면 
          '영업시간(월~금 15:00 ~ 16:59:59 까지) 아니므로 입력불가함!!' 이라는 오류메시지가 뜨도록 한다. 
          
              select to_char(sysdate, 'd')   --- sysdate의 주의 일요일 부터 (지금은 2024년 3월 04일) sysdate(지금은 2024년 3월 4일)까지 며칠째인지를 알려주는 것이다 
                                    -- '1'(일요일) '2'(월요일) '3'(화요일) '4'(수요일) '5'(목요일) '6'(금요일) '7'(토요일)
     from dual;
     
   
    create or replace procedure pcd_tbl_member_test1_insert
 (p_userid   IN  tbl_member_test1.userid%type
 ,p_passwd   IN  tbl_member_test1.passwd%type
 ,p_name     IN  tbl_member_test1.name%type)
 is
      v_passwd_length  number(2);
      v_ch             varchar2(1);
      v_flag_alphabet  number(1) := 0;
      v_flag_number    number(1) := 0;
      v_flag_special   number(1) := 0;
      
      error_insert     exception; 
      error_dayTime    exception;
 begin
    -- 입력(insert)이 불가한 요일명과 시간대를 알아봅니다 -- 
    if( to_char(sysdate, 'd')in('1','7') or 
        to_number(to_char(sysdate, 'hh24')) < 14 or 
        to_number(to_char(sysdate, 'hh24')) > 16) then 
        raise error_dayTime;
    else -- 입력(insert)이 가능한 요일명과 시간대이라면 암호를 검사하겠다
    
        
               v_passwd_length := length(p_passwd);
               
               if( v_passwd_length < 5 or v_passwd_length > 20 ) then
                   raise error_insert; -- 사용자가 정의하는 예외절(exception)을 구동시켜라.
               else
                   for i in 1..v_passwd_length loop
                       v_ch := substr(p_passwd, i, 1);
                       
                       if(v_ch between 'A' and 'Z') OR (v_ch between 'a' and 'z') then -- 영문자 이라면 
                            v_flag_alphabet := 1;
                       elsif(v_ch between '0' and '9') then -- 숫자 이라면
                            v_flag_number := 1;
                       else -- 특수문자이라면      
                            v_flag_special := 1;
                       end if;
               
           end loop; -- end of for loop
           
           if(v_flag_alphabet * v_flag_number * v_flag_special = 1) then 
              insert into tbl_member_test1(userid, passwd, name) values(p_userid, p_passwd, p_name);
           else
              raise error_insert; -- 사용자가 정의하는 예외절(exception)을 구동시켜라.
           end if;
           
       end if;
       
       end if;
       
       exception 
          when  error_dayTime then 
               raise_application_error(-20003, '>> 영업시간(월~금 14:00 ~ 16:59:59까지)이 아니므로 입력불가 <<'); 
         when error_insert then 
               raise_application_error(-20002, '>> 암호는 최소 5글자 이상이면서 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다. <<'); 
               
 end pcd_tbl_member_test1_insert;
 -- Procedure PCD_TBL_MEMBER_TEST1_INSERT이(가) 컴파일되었습니다.
 
 
 
 
 
 
     create or replace procedure pcd_tbl_member_test1_insert
 (p_userid   IN  tbl_member_test1.userid%type
 ,p_passwd   IN  tbl_member_test1.passwd%type
 ,p_name     IN  tbl_member_test1.name%type)
 is
      v_passwd_length  number(2);
      v_ch             varchar2(1);
      v_flag_alphabet  number(1) := 0;
      v_flag_number    number(1) := 0;
      v_flag_special   number(1) := 0;
      
      error_insert     exception; 
      error_dayTime    exception;
 begin
    -- 입력(insert)이 불가한 요일명과 시간대를 알아봅니다 -- 
    if( to_char(sysdate, 'd')in('1','7') or 
        to_number(to_char(sysdate, 'hh24')) < 14 or 
        to_number(to_char(sysdate, 'hh24')) > 10) then 
        raise error_dayTime;
    else -- 입력(insert)이 가능한 요일명과 시간대이라면 암호를 검사하겠다
    
        
               v_passwd_length := length(p_passwd);
               
               if( v_passwd_length < 5 or v_passwd_length > 20 ) then
                   raise error_insert; -- 사용자가 정의하는 예외절(exception)을 구동시켜라.
               else
                   for i in 1..v_passwd_length loop
                       v_ch := substr(p_passwd, i, 1);
                       
                       if(v_ch between 'A' and 'Z') OR (v_ch between 'a' and 'z') then -- 영문자 이라면 
                            v_flag_alphabet := 1;
                       elsif(v_ch between '0' and '9') then -- 숫자 이라면
                            v_flag_number := 1;
                       else -- 특수문자이라면      
                            v_flag_special := 1;
                       end if;
               
           end loop; -- end of for loop
           
           if(v_flag_alphabet * v_flag_number * v_flag_special = 1) then 
              insert into tbl_member_test1(userid, passwd, name) values(p_userid, p_passwd, p_name);
           else
              raise error_insert; -- 사용자가 정의하는 예외절(exception)을 구동시켜라.
           end if;
           
       end if;
       
       end if;
       
       exception 
          when  error_dayTime then 
               raise_application_error(-20003, '>> 영업시간(월~금 14:00 ~ 16:59:59까지)이 아니므로 입력불가 <<'); 
         when error_insert then 
               raise_application_error(-20002, '>> 암호는 최소 5글자 이상이면서 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다. <<'); 
               
 end pcd_tbl_member_test1_insert;
 
 
  exec pcd_tbl_looptest_1_insert('유관순', 50000);
  
  commit;

select *
from tbl_member_test1;





create or replace procedure pcd_tbl_member_test1_insert
 (p_userid   IN  tbl_member_test1.userid%type
 ,p_passwd   IN  tbl_member_test1.passwd%type
 ,p_name     IN  tbl_member_test1.name%type)
 is
      v_passwd_length  number(2);
      v_ch             varchar2(1);
      v_flag_alphabet  number(1) := 0;
      v_flag_number    number(1) := 0;
      v_flag_special   number(1) := 0;
      
      error_insert     exception; 
      error_dayTime    exception;
 begin
       
       -- 입력(insert)이 불가한 요일명과 시간대를 알아봅니다. --
       if( to_char(sysdate, 'd') in('1','7') OR 
           to_number(to_char(sysdate, 'hh24')) < 14 OR
           to_number(to_char(sysdate, 'hh24')) > 16 ) then 
           raise error_dayTime;
       else --  입력(insert)이 가능한 요일명과 시간대 이라면 암호를 검사하겠다.
       
           v_passwd_length := length(p_passwd);
           
           if( v_passwd_length < 5 or v_passwd_length > 20 ) then
               raise error_insert; -- 사용자가 정의하는 예외절(exception)을 구동시켜라.
           else
               for i in 1..v_passwd_length loop
                   v_ch := substr(p_passwd, i, 1);
                   
                   if(v_ch between 'A' and 'Z') OR (v_ch between 'a' and 'z') then -- 영문자 이라면 
                        v_flag_alphabet := 1;
                   elsif(v_ch between '0' and '9') then -- 숫자 이라면
                        v_flag_number := 1;
                   else -- 특수문자이라면      
                        v_flag_special := 1;
                   end if;
                   
               end loop; -- end of for loop
               
               if(v_flag_alphabet * v_flag_number * v_flag_special = 1) then 
                  insert into tbl_member_test1(userid, passwd, name) values(p_userid, p_passwd, p_name);
               else
                  raise error_insert; -- 사용자가 정의하는 예외절(exception)을 구동시켜라.
               end if;
               
           end if;
       
       end if;
       
       exception 
          when error_dayTime then 
               raise_application_error(-20003, '>> 영업시간(월~금 14:00 ~ 16:59:59 까지)이 아니므로 입력불가함!! <<'); 
          
          when error_insert then 
               raise_application_error(-20002, '>> 암호는 최소 5글자 이상이면서 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다. <<'); 
               
 end pcd_tbl_member_test1_insert;
 -- Procedure PCD_TBL_MEMBER_TEST1_INSERT이(가) 컴파일되었습니다.


 exec pcd_tbl_member_test1_insert('eomjh','qwer1234$','엄정화');
 /*
    오류 보고 -
    ORA-20003: >> 영업시간(월~금 14:00 ~ 16:59:59 까지)이 아니므로 입력불가함!! <<
 
 
 -- 현재시각은 월요일 오후 2시 1분이다
 exec pcd_tbl_member_test1_insert('eomjh','qwer1234$','엄정화');
 -- PL/SQL 프로시저가 성공적으로 완료되었습니다.
 */
   
   
 
public class Procedure_insert_sqlexception_CallableStatement_04 {

	public static void main(String[] args) {
		  Connection conn = null;
	         // Connection conn 은 데이터베이스 서버와 연결을 맺어주는 자바의 객체이다. 
	         
	       CallableStatement cstmt = null;
	         // CallableStatement cstmt 은 Connection conn(연결한 DB 서버)에 존재하는 Procedure 를 호출해주는 객체(우편배달부)이다. 
	       String userid = ""; 
	       try {
	           // >>> 1. 오라클 드라이버 로딩 <<<
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	           
	           
	           // >>> 2. 어떤 오라클 서버에 연결을 할래? <<<
	           conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.193:1521:xe", "hr", "gclass");
	           
	           // >>> 3. Connection conn 객체를 사용하여 prepareCall() 메소드를 호출함으로써
	             //        CallableStatement cstmt 객체를 생성한다. 즉, 우편배달부(택배기사) 객체 만들기
	           cstmt = conn.prepareCall("{call pcd_tbl_member_test1_insert(?,?,?)}");
	           /*
	                 오라클 서버에 생성한 프로시저 ,?,?,?,?,? 의 
	                 매개변수 갯수가 2개 이므로 ? 를 2개 준다.
	                
	                 다음으로 오라클의 프로시저를 수행( executeUpdate() ) 하기에 앞서서  
	                 반드시 해야할 일은 IN mode 로 되어진 파라미터에 값을 넣어주고,
	                 OUT mode 로 설정된 곳에 그 결과값을 받아오도록 아래와 같이 설정해야 한다.
	                 
	                 프로시저의 IN mode 로 되어진 파라미터에 값을 넣어줄때는 
	                 cstmt.setXXX() 메소드를 사용한다. 
	                 
	                 프로시저의 OUT mode 로 되어진 파라미터에 저장되어진 값을 자바에서 꺼내 오려면 
	                 cstmt.registerOutParameter() 메소드를 사용한다.
	                 
	                 ※ registerOutParameter() 메소드는?
	                 ==> public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException 
	                     : 프로시저를 실행하여 받아온 값을 JDBC타입(자바에서 인식하는 타입)으로 등록시켜주는 메소드이다.
	                  
	                  자바에서는 오라클의 OUT mode 변수에 오라클 데이터타입으로 저장되어 있는 값들을 읽어와서
	                  JDBC타입(자바에서 인식하는 타입)으로 변경하는 과정을 >거쳐야만 한다<.
	                  대표적인 sqlType을 알아보면 NULL, FLOAT, INTEGER, VARCHAR, DATE, CLOB, BLOB 등이 있다.  
	                                      실수      정수      문자
	           */
	           Scanner sc = new Scanner(System.in);
	           System.out.print("▷ 아이디 : ");
	          userid = sc.nextLine();
	           System.out.print("▷ 비밀번호 : ");
	           String passwd = sc.nextLine();
	           System.out.print("▷ 성명 : ");
	           String name = sc.nextLine(); // 서울 강남구
	           
	           //자바와 sql의 타입이 맞게끔 매핑을 시켜줘야 한다.
	           
	           cstmt.setString(1, userid); // 숫자 1 은 프로시저 파라미터중 첫번째 파라미터인 IN 모드의 ? 를 말한다. => 자동 형변환이 되므로 String이어도 number로 형변환 된다.
	           cstmt.setString(2, passwd);
	           cstmt.setString(3, name);
	           
	           
	          
	           
	           // >>> 4. CallableStatement cstmt 객체를 사용하여 오라클의 프로시저 실행하기
	         int n =   cstmt.executeUpdate(); // 오라클 서버에게 해당 프로시저를 실행해라는 것이다.
	           //프로시저의 실행은 cstmt.executeUpdate(); 또는 cstmt.execute();이다. [둘다 똑같]
	           
	         if(n==1) {
	        	 System.out.println(">> 데이터 입력 성공 << ");
	         }
	           
	    
	             sc.close();
	           
	        } catch (ClassNotFoundException e) {
	           System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
	        } catch (SQLException e) {
	        //   e.printStackTrace();
	        if(e.getErrorCode() == 20002 || e.getErrorCode() == 20003 ) {
	        	System.out.println(e.getMessage());
	        }
	        else if(e.getErrorCode() == 1) {//pk는 1번이
	        	System.out.println(">> 아이디"+ userid  +"은 현재 사용중이므로 다른 아이디로 입력하세요 !! <<"); // userid가 지역변수라 밖으로빼야허ㅏㅁ 
	        }
	        else {
	        	e.printStackTrace();
	        }
	      
	        } finally {
	           // >>> 6. 사용하였던 자원을 반납하기 <<<
	           // 반납의 순서는 생성순의 역순으로 한다.
	        
	           try {
	          	
	          	 
	              if(cstmt != null) {
	                 cstmt.close();
	                 cstmt = null;
	              }
	              
	              if(conn != null) {
	                 conn.close();
	                 conn = null;
	              }
	              
	           } catch (SQLException e) {
	              e.printStackTrace();
	           }
	           
	        }
	        
	          System.out.println("~~~ 프로그램 종료 ~~~");
	}// end of main() ~ 

}
