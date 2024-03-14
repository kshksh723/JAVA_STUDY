package jdbc.day04.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.day04.board.dbconnection.MyDBConnection;
import jdbc.day04.board.domain.BoardDTO;
import jdbc.day04.board.domain.CommentDTO;
import jdbc.day04.board.domain.MemberDTO;

// 게시판 글쓰기

//=== Transaction 처리 ===
//    (tbl_board 테이블에 insert 가 성공되어지면 tbl_member 테이블의 point 컬럼에 10씩 증가 update 를 할 것이다.
//     그런데 insert 또는 update 가 하나라도 실패하면 모두 rollback 할 것이고,
//     insert 와 update 가 모두 성공해야만 commit 할 것이다.)

// 게시판 글쓰기 Transaction 처리하여 성공되어지면 1을 리턴시켜 줄 것이고,
// 장애(오류)가 발생되어 실패함녀 -1을 리턴 시켜 줄 것이다


public class BoardDAO_imple implements BoardDAO  {

	
	// field(= attribute, property, 속성) 생성하기
	// 메소드마다 connection을 계속 해줄거니까 따로 빼준다.
	private Connection conn = MyDBConnection.getConn();
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
    // method, operation, 기능
	  
	// === 자원반납을 해주는 메소드 ===
	private void close() {
		
		try {
			if(rs != null) 		{rs.close();	rs = null;}
			if(pstmt != null) 	{pstmt.close();	pstmt = null;}
			// conn 은 프로그램을 종료할 때만 끊는것임. close 해버리면 안돼!
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	 }// end of private void close()

	

// 게시판 글쓰기 transaction 처리하여 성공되어지면 1을 리턴시켜 줄 것이고 
// 	장애(오류)가 발생되어 실패하면 -1을 리턴시켜줄 것이다 
 	
 	@Override
 	public int write(BoardDTO bdto) {
	int result = 0;
	
 	//transaction 처리
	try {
		conn.setAutoCommit(false);
		
		String sql = " insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd ) "
				+ " values(seq_board.nextval, ?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bdto.getFk_userid());
		pstmt.setString(2, bdto.getSubject());
		pstmt.setString(3, bdto.getContents());
		pstmt.setString(4, bdto.getBoardpasswd());
		
		int n1 = pstmt.executeUpdate(); //sql 실행하기
 		
		
		if(n1 == 1 ) { // tbl_board 테이블에 insert가 성공되었다라면 
			sql = " update tbl_member set point = point + 10 "
					+ " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bdto.getFk_userid());
			
			int n2 = pstmt.executeUpdate(); // sql문 실행하기
			
			if(n2 == 1 ) { // tbl_member 테이블에 update가 성공되었다라면 
				conn.commit(); // 커밋을 해준다
				result = 1;
				
			}
		}
 		} catch(SQLException e) {
		if(	e.getErrorCode() == 2290) {
			System.out.println(">> 아이디"+ bdto.getFk_userid() + " 님의 포인트는 30을 초과할 수 없기 때문에 오류 발생하였습니다 <<");
		}
		else {
			e.printStackTrace();
		}
			try {
				conn.rollback(); // 롤백을 해준다
				result = -1;
			} catch(SQLException e2) { }
		} finally {
			try {
			conn.setAutoCommit(true); // 자동 commit으로 복원시킨다
			} catch(SQLException e2) { }
			close(); // 자원반납하기 
		}
 		// 자동 commit으로 복원시킨다 
 		
 		
		return result;
	
	
 	}



// 글 목록 보기 
 	@Override
	public List<BoardDTO> boardList() {
 		List<BoardDTO> boardList = new ArrayList<>();
 		  try {
		      
		         
		         String sql = "   SELECT V1.boardno, V1.subject ||  ' [' || V2.comment_cnt || ']' as subject "
		         		+ "         , V1.name, V1.writeday, V1.viewcount "
		         		+ "           FROM             "
		         		+ "            (          "
		         		+ "            select B.boardno "
		         		+ "			, CASE WHEN length ( B.subject) >  15 THEN substr(B.subject,1,13) || '..'  "
		         		+ "		              ELSE B.subject END AS subject "
		         		+ "		         		   , M.name   "
		         		+ "		         	  ,to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday  "
		         		+ "		         	  , B.viewcount  "
		         		+ "		         		 from tbl_board B JOIN tbl_member M  "
		         		+ "		         ON B.fk_userid = M.userid  "
		         		+ "		     )V1    "
		         		+ "          LEFT OUTER   JOIN  "
		         		+ "             ( "
		         		+ "        select fk_boardno, count(*) as comment_cnt  "
		         		+ "        from tbl_comment  "
		         		+ "        group by fk_boardno "
		         		+ "                        ) V2  "
		         		+ "                    ON V1.boardno = V2.fk_boardno  "
		         		+ "                    ORDER BY V1.boardno DESC ";
		         			// status 가 1일 때만 로그인 완료된것이기 때문에 status = 1
		         
		         
			      pstmt = conn.prepareStatement(sql);
			    
			      rs = pstmt.executeQuery(); // sql 문 실행 
			      
			      while(rs.next()) { // select 되어진 결과물이 있을 때에만
			    	 BoardDTO bdto = new BoardDTO();
			    	 
			    	bdto.setBoardno(rs.getInt("boardno"));
			    	bdto.setSubject(rs.getString("subject"));
			    	
			    	MemberDTO member = new MemberDTO();
			    	member.setName(rs.getString("name"));
			   
			    	
			    	
			    	bdto.setMember(member);
			    	
			    	bdto.setWriteday(rs.getString("writeday"));
			    	bdto.setViewcount(rs.getInt("viewcount"));
			    	//////////////////////////////////////////////////
			    	
			    	
			    	boardList.add(bdto);
			      }// end of while
			      
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } finally {
				close();
		  }
		return boardList; // null이 아니다 
	}
 	
 	// ** 글 수정하기 ***
 	@Override
 	public int updateBoard(Map<String, String> paraMap) {
 	int result = 0;
 	
 	try {
 		String sql = " update tbl_board set subject = ?, contents = ? "
 				+ " where boardno = ? ";
 		
 	    pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, paraMap.get("subject"));
        pstmt.setString(2, paraMap.get("contents"));
        pstmt.setString(3, paraMap.get("boardno"));
        
        result = pstmt.executeUpdate(); //sql문 실행하기
        
        
 	} catch (SQLException e) {
         if(e.getErrorCode() == 1722) {
            System.out.println(">> [경고] 글번호는 정수로만 입력하세요!!");
         } else {
            e.printStackTrace();
         }
      } finally {
         close(); // 자원 반납하기
      }
	return result;
}

 	


 // *** 글내용보기 *** //
    // == 현재 로그인 사용자가 자신이 쓴 글을 볼때는 조회수 증가가 없지만
    //    다른 사용자가 쓴 글을 볼때는 조회수를 1증가 해주어야 한다.
    @Override
    public BoardDTO viewContents(Map<String, String> paraMap) {
       
       BoardDTO bdto = null;
       
       try {
          String sql = 
          		 " SELECT   V2.comment_cnt IS NULL THEN  V1.subject  "
          		 + "            ELSE V1.subject ||  ' [' || V2.comment_cnt || ']' "
          		 + "            END as subject  "
          		+ " FROM "
          		+ " ( "
          		+ " SELECT boardno "
          		+ " , CASE WHEN length (subject) >  15 THEN substr(subject,1,13) || '..' "
          		+ "        ELSE subject END AS subject "
          		+ "    , contents "
          		+ "    , viewcount "
          		+ "    , fk_userid "
          		+ " from tbl_board "
          		+ " where boardno = ? "
          		+ " ) V JOIN tbl_member M "
          		+ " ON V.fk_userid = M.userid  ";
                   
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, paraMap.get("boardno"));
                   
          rs = pstmt.executeQuery(); // sql문 실행
          
          if(rs.next()) {
        	  
             bdto = new BoardDTO();
             
             bdto.setSubject(rs.getString("subject"));
             bdto.setContents(rs.getString("contents"));
             
             MemberDTO member = new MemberDTO();
             member.setName(rs.getString("name"));
             bdto.setMember(member);
             
             bdto.setViewcount(rs.getInt("viewcount"));
             
             // 로그인한 사용자가 다른 사용자가 쓴 글을 조회할 경우에만 글조회수 1증가 시켜야 한다. 
             if( !paraMap.get("login_userid").equals(rs.getString("fk_userid")) ) {
                
                sql = " update tbl_board set viewcount = viewcount + 1 "
                   + " where boardno = ? ";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, paraMap.get("boardno"));
                
                pstmt.executeUpdate();
                
                bdto.setViewcount(bdto.getViewcount() + 1);
             }
             
          }// end of if----------------------------------------
          
       } catch (SQLException e) {
          if(e.getErrorCode() == 1722) {
             System.out.println(">> [경고] 글번호는 정수만 가능합니다. << \n");
          }
          else {
             e.printStackTrace();
          }
       } finally {
          close();   
       } 
       
       return bdto;
    }// end of public BoardDTO viewContents(Map<String, String> paraMap)-----


// 조회수 증가는 없고 단순히 글내용만 보여주기 
@Override
public BoardDTO viewContents_2(String boardno) {
	 
	BoardDTO bdto = null;
     
     try {
        String sql =  " SELECT subject, contents, fk_userid, boardpasswd "
                 + " FROM tbl_board "
                 + " WHERE boardno = ? ";
              
                 
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, boardno);
                 
        rs = pstmt.executeQuery(); // sql문 실행
        
        if(rs.next()) {
           bdto = new BoardDTO();
           
           bdto.setSubject(rs.getString("subject"));
           bdto.setContents(rs.getString("contents"));
           bdto.setFk_userid(rs.getString("fk_userid"));
           bdto.setBoardpasswd(rs.getString("boardpasswd"));
           
        	}
        } catch (SQLException e) {
            if(e.getErrorCode() == 1722) {
               System.out.println(">> [경고] 글번호는 정수만 가능합니다. << \n");
            }
            else {
               e.printStackTrace();
            }
         } finally {
            close();   
         } 
         
         return bdto;
      }


// 글 삭제하기
			@Override
			public int deleteBoard(String boardno) {
			   
			   int result = 0; 
			   
			   try {
			      String sql = " DELETE FROM tbl_board "
			                + " where boardno = ? "; 
			      
			      pstmt = conn.prepareStatement(sql);
			      pstmt.setString(1, boardno);
			      //dto에 담긴 필드의 데이터를 담아서 위치홀더에 넣기
			      
			      // sql문 실행하기
			      result = pstmt.executeUpdate(); //1개 행이 나옴 ->boardno가 pk이기 때문에 한개 행만 나오게 된다.
			   
			      
			   } catch (SQLException e) {
			      if(e.getErrorCode() == 1722) {
			         System.out.println(">> [경고] 글번호는 정수로만 입력하세요!!");
			      } else {
			         e.printStackTrace();
			      }
			   } finally {
			      close(); // 자원 반납하기
			   }
			
			   
			   return result;
			}



    
//*** 댓글쓰기 ***
@Override
public int writeComment(CommentDTO cmtdto) {
   
   int result = 0;
   
   // default 가 자동 commit 인데, transaction 처리를 위해서 수동 commit 으로 전환시킨다.
   // 그래야 rollback - committ 이 되기 때문
   try {
      conn.setAutoCommit(false);
      
      String sql = " insert into tbl_comment(commentno, fk_boardno, fk_userid, contents) "
               + " values(seq_comment.nextval, ?, ?, ?) ";
      
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setInt(1, cmtdto.getFk_boardno()); // DAO 를 호출해준 ctrl 에서 bdto 에 set set 해줬으니까 그거 가져온거임
      pstmt.setString(2, cmtdto.getFk_userid());
      pstmt.setString(3, cmtdto.getContents());
      
      int n1 = pstmt.executeUpdate(); // sql 문 실행하기 -- return type 이 int고, 성공되면 1이 나올 것이다.
      
      if(n1 == 1) { // tbl_board 테이블에 insert 가 성공되었다면 POINT +10 씩 update 한다.
         sql = " update tbl_member set point = point + 5 "
            + " where userid = ? ";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, cmtdto.getFk_userid() ); // point 를 가지고 오려면 회원정보가 필요한 것이고
                                         // bdto 에 member 정보를 불러오는 set 이 있음
         int n2 = pstmt.executeUpdate(); // sql 문 실행하기
         // 정상이라면 1
         
         if(n2==1) { // n1==1 이라는 것은 게시글에 insert 가 성공되었다는 뜻이고
                  // n2==1 이라는 것은 sql 문에 update 가 성공되었다는 뜻
            conn.commit(); // 둘 다 성공이라면 commit 을 해주겠다.
            result = 1;
         }
      }
      
   } catch (SQLException e) {
      
      if(e.getErrorCode()==2291) {
         /*
              오류 보고 -
              ORA-02291: 무결성 제약조건(JDBC_USER.FK_TBL_COMMENT_FK_BOARDNO)이 위배되었습니다- 부모 키가 없습니다
            */
         System.out.println(">>> 입력하신 원글번호 "+ cmtdto.getFk_boardno() +"는 게시글에 존재하지 않습니다. <<<");
         result = -1;  // 처음부터 오류가 떨어져서 insert 자체가 안된것임. rollback 이런게 소용x
      }
      
      else if(e.getErrorCode()==2290) { // 포인트 초과 오류 번호 / check 제약
         System.out.println(">> 아이디 "+ cmtdto.getFk_userid() +" 님의 포인트는 30을 초과할 수 없기 때문에 오류가 발생하였습니다. <<\n");
         try {
            conn.rollback(); // insert 성공하고 update 실패할 경우 insert 문을 rollback 해주어야 하기 때문
            result = -1;
         } catch (SQLException e2) { }
      }
      
      else {
         e.printStackTrace();
      }
      
   } finally {
      try {
         conn.setAutoCommit(true); // ★ 자동 commit 으로 복원시킨다.
      } catch (SQLException e2) { }
      
      close(); // 자원 반납하기
   }
   
   return result;
   
}// public int writeComment(CommentDTO cmtdto)

// 원글에 대한 댓글을 가져오는 것(특정 게시글 글번호에 대한 tbl_comment 테이블과 tbl_member 테이블을 JOIN 해서 보여준다.)

@Override
public List<CommentDTO> commentList(String boardno) {
	List<CommentDTO> commentList = new ArrayList<>();
	BoardDTO bdto = null;

    try {
       String sql =  "  SELECT C.contents, M.name, to_char(C.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday "
       		+ "  FROM "
       		+ "           ( "
       		+ "                        select * "
       		+ "                        from tbl_comment "
       		+ "                        where fk_boardno = ?  "
       		+ "                        ) C JOIN tbl_member M "
       		+ "                        ON C.fk_userid = M.userid "
       		+ "                        order by C.commentno desc ";
             
                
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, boardno);
                
       rs = pstmt.executeQuery(); // sql문 실행
       
       while (rs.next()) {
    	   CommentDTO cmtdto = new CommentDTO();
    	   
    	   cmtdto.setContents(rs.getString("contents"));
       
    	   MemberDTO mdto = new MemberDTO();
    	   mdto.setName(rs.getString("name"));
    	   
    	   cmtdto.setWriteday(rs.getString("writeday"));
        
    	   commentList.add(cmtdto);
	       } // while (rs.next()) 
	       
	       } catch (SQLException e) {
           
              e.printStackTrace();
           }

        finally {
           close();   
        } 
        
        return commentList;
} // end of public List<CommentDTO> commentList(String boardno) 


// 최근 1주일내
@Override
public Map<String, Integer> statistics_by_Week() {

	 Map<String, Integer> resultMap = new HashMap<>();
	 List<CommentDTO> commentList = new ArrayList<>();
		BoardDTO bdto = null;

	    try {
	       String sql =  " SELECT COUNT(*) AS TOTAL "
	       		+ "      ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 6, 1, 0)) as PREVIOUS6 "
	       		+ "    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 5, 1, 0)) as PREVIOUS5 "
	       		+ "    ,   SUM(decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 4, 1, 0))as PREVIOUS4 "
	       		+ "    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 3, 1, 0))as PREVIOUS3 "
	       		+ "    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 2, 1, 0)) as PREVIOUS2 "
	       		+ "    , SUM(  decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 1, 1, 0)) as PREVIOUS1 "
	       		+ "    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 0, 1, 0)) as TODAY "
	       		+ " FROM tbl_board "
	       		+ " WHERE to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7  ";
	             
	                
	       pstmt = conn.prepareStatement(sql);
	      
	                
	       rs = pstmt.executeQuery(); // sql문 실행
	       rs.next();
	       
	       resultMap.put("TOTAL",rs.getInt("TOTAL"));
	       resultMap.put("PREVIOUS6",rs.getInt("PREVIOUS6"));
	       resultMap.put("PREVIOUS5",rs.getInt("PREVIOUS5"));
	       resultMap.put("PREVIOUS4",rs.getInt("PREVIOUS4"));
	       resultMap.put("PREVIOUS3",rs.getInt("PREVIOUS3"));
	       resultMap.put("PREVIOUS2",rs.getInt("PREVIOUS2"));
	       resultMap.put("PREVIOUS1",rs.getInt("PREVIOUS1"));
	       resultMap.put("TODAY",rs.getInt("TODAY"));
	      
		       } catch (SQLException e) {
	           
	              e.printStackTrace();
	           }

	        finally {
	           close();   
	        } 
	 return resultMap;
	
}// end of  public Map<String, Integer> statistics_by_Week()


//이번달 일자별 게시글 작성건수
@Override
public List<Map<String, String>> statistics_by_CurrentMonth() {

	List<Map<String, String>> mapList = new ArrayList <> ();
	   try {
	       String sql =  " select decode( grouping(to_char(writeday, 'yyyy-mm-dd')),0,to_char(writeday, 'yyyy-mm-dd'), '전체' ) as writeday "
	       		+ "    , count(*) as cnt "
	       		+ " from tbl_board "
	       		+ " where to_char (writeday, 'yyyymm') = to_char(sysdate,'yyyymm') "
	       		+ " group by rollup( to_char(writeday, 'yyyy-mm-dd'))  ";
	             
	                
	       pstmt = conn.prepareStatement(sql);
	      
	                
	       rs = pstmt.executeQuery(); // sql문 실행
	      //복수개 -> while문
	       
	       while(rs.next()) {
	    	   Map<String, String> map = new HashMap<>();  
	    	   map.put("writeday", rs.getString("writeday"));
	    	   // map.put("cnt", String.valueOf(rs.getInt("cnt")));
	    	   // 또는
	    	   map.put("cnt", rs.getString("cnt")); // 둘다 쓸 수 있다
	    	   
	    	   mapList.add(map); //SELECT 되어진 행의 개수만큼 담아짐
	       } // end of while(rs.next())
	       
		       } catch (SQLException e) {
	           
	              e.printStackTrace();
	           }

	        finally {
	           close();   
	        } 
	
	return mapList;
} // public List<Map<String, String>> statistics_by_CurrentMonth()

 // 데이터가 없더라도 0000으로 나온다 
}