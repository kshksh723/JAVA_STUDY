package jdbc.day04.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.day04.board.dbconnection.MyDBConnection;
import jdbc.day04.board.domain.BoardDTO;
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
		      
		         
		         String sql = " select B.boardno "
		         		+ "	, CASE WHEN length ( B.subject) >  15 THEN substr(B.subject,1,13) || '..' "
		         		+ "        ELSE B.subject END AS subject "
		         		+ "    , M.name "
		         		+ "    ,to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday "
		         		+ "    , B.viewcount "
		         		+ " from tbl_board B JOIN tbl_member M "
		         		+ " ON B.fk_userid = M.userid  "
		         		+ " order by B.boardno DESC ";
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
          		 " SELECT V.subject, V.contents, M.name, V.viewcount, V.fk_userid "
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
}
    
    


