show user;
-- USER이(가) "JDBC_USER"입니다.


select *
from tbl_member
order by userseq desc;


update tbl_member set status = 1;
commit;

---- *** 게시판 테이블 생성하기 *** ----
create table tbl_board
(boardno       number         not null        -- 글번호
,fk_userid     varchar2(30)   not null        -- 작성자아이디
,subject       Nvarchar2(100) not null        -- 글제목
,contents      Nvarchar2(200) not null        -- 글내용
,writeday      date default sysdate not null  -- 작성일자 
,viewcount     number default 0 not null      -- 조회수 
,boardpasswd   varchar2(20) not null          -- 글암호
,constraint PK_tbl_board_boardno primary key(boardno)
,constraint FK_tbl_board_fk_userid foreign key(fk_userid) references tbl_member(userid)
);
-- Table TBL_BOARD이(가) 생성되었습니다.

create sequence seq_board
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_BOARD이(가) 생성되었습니다. 

desc tbl_board;

select *
from tbl_board
order by boardno desc;

desc tbl_member;
-- 글번호   글제목   작성자   작성일자    조회수







-----------------------------------------------------------------------------------------
/*
   Transaction(트랜잭션) 처리 실습을 위해서
   tbl_member 테이블의 point 컬럼의 값은 최대 30을 넘지 못하도록 check 제약을 걸도록 하겠습니다.
   
   -- 플젝에서 trancsaction 꼭 하기 
*/
-----------------------------------------------------------------------------------------

alter table tbl_member
add constraint CK_tbl_member_point check( point between 0 and 30);
-- Table TBL_MEMBER이(가) 변경되었습니다.


select *
from tbl_member
order by userseq desc;

update tbl_member set point = point + 10
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.
-- 10

update tbl_member set point = point + 10
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.
-- 20

update tbl_member set point = point + 10
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

update tbl_member set point = point + 10
where userid = 'leess';
/*
오류 보고 -
ORA-02290: 체크 제약조건(JDBC_USER.CK_TBL_MEMBER_POINT)이 위배되었습니다
check 제약때문에 30 못넘어간다 
*/

select *
from tbl_member
order by userseq desc;

rollback;

-- 글번호  글제목    작성자      작성일자    조회수 --
select *
from tbl_board

-- 조인(보드 - 회원) 



select *
from tbl_member;

-- 글 목록 보기 
select B.boardno
, CASE WHEN length ( B.subject) >  15 THEN substr(B.subject,1,13) || '..' 
        ELSE B.subject END AS subject 

    , M.name
    ,to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday
    , B.viewcount
from tbl_board B JOIN tbl_member M
ON B.fk_userid = M.userid
order by B.boardno DESC;


-- 글 내용 보기 

SELECT V.subject, V.contents, M.name, V.viewcount, V.fk_userid 
FROM
(
SELECT boardno
, CASE WHEN length (subject) >  15 THEN substr(subject,1,13) || '..' 
        ELSE subject END AS subject 
    , contents
    , viewcount
    , fk_userid
from tbl_board 
where boardno = '1'
)V JOIN tbl_member M
ON V.fk_userid = M.userid;



-- 글 삭제하기
delete from tbl_board
where boardno = 


---- *** 댓글 테이블 생성하기 *** ----
create table tbl_comment
(commentno   number         not null        -- 댓글번호
,fk_boardno  number         not null        -- 원글의 글번호 
,fk_userid   varchar2(30)   not null        -- 작성자 아이디
,contents    Nvarchar2(100) not null        -- 댓글내용
,writeday    date default sysdate not null  -- 작성일자
,constraint PK_tbl_comment_commentno primary key(commentno)
,constraint FK_tbl_comment_fk_boardno foreign key(fk_boardno) references tbl_board(boardno) on delete cascade 
,constraint FK_tbl_comment_fk_userid foreign key(fk_userid) references tbl_member(userid) on delete cascade
);
-- Table TBL_COMMENT이(가) 생성되었습니다.
-- 자식 테이블에 있는 원글에 있는 자신의 행들을 




update tbl_comment
from name 
where userid

create sequence seq_comment
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_COMMENT이(가) 생성되었습니다.

select *    
from tbl_comment;

select *    
from tbl_member;
tbl_board 




            select B.boardno
			, CASE WHEN length ( B.subject) >  15 THEN substr(B.subject,1,13) || '..' 
		              ELSE B.subject END AS subject 
		         		   , M.name 
		         	  ,to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday 
		         	  , B.viewcount 
		         		 from tbl_board B JOIN tbl_member M 
		         ON B.fk_userid = M.userid  
		         		order by B.boardno DESC
                        
                        
                        
                        select fk_boardno, count(*) as comment_cnt
                        from tbl_comment
                        group by fk_boardno
                        
         SELECT V1.boardno
         ,case when  V2.comment_cnt IS NULL THEN  V1.subject  
            ELSE V1.subject ||  ' [' || V2.comment_cnt || ']'
            END as subject 
         , V1.name, V1.writeday, V1.viewcount
           FROM              
            (            
            select B.boardno
			, CASE WHEN length ( B.subject) >  15 THEN substr(B.subject,1,13) || '..' 
		              ELSE B.subject END AS subject 
		         		   , M.name 
		         	  ,to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday 
		         	  , B.viewcount 
		         		 from tbl_board B JOIN tbl_member M 
		         ON B.fk_userid = M.userid  
		     )V1    
          LEFT OUTER   JOIN 
             (
        select fk_boardno, count(*) as comment_cnt
        from tbl_comment
        group by fk_boardno
                        ) V2
                    ON V1.boardno = V2.fk_boardno
                    ORDER BY V1.boardno DESC;
                        --OUTER은 생략 가능 
                        
                        
                        
                        --  V1.boardno, V1.subject ||  ' [' || V2.comment_cnt || ']' as subject  댓글 갯수
                        
                        SELECT C.contents, M.name, to_char(C.writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday
                        FROM
                        (
                        select *
                        from tbl_comment
                        where fk_boardno = '1'
                        ) C JOIN tbl_member M
                        ON C.fk_userid = M.userid
                        order by C.commentno desc;
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
--- 최근 1주일내에 작성된 게시글만 날짜별로 개수를 출력하도록 한다
update tbl_board set writeday = writeday - 7  -- 일주일전
where boardno = 1;
-- 1 행 이(가) 업데이트되었습니다.


-- 일주일전 테이블 
select writeday
    , to_char(writeday, 'yyyy-mm-dd hh24:mi:ss')
    , to_char(writeday, 'yyyy-mm-dd')
    , to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd')
    , to_char(to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 'yyyy-mm-dd hh24:mi:ss')
    , sysdate
    ,to_char( to_date (to_char( sysdate, 'yyyy-mm-dd')), 'yyyy-mm-dd hh24:mi:ss')
from tbl_board;



select to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd')
,  to_date (to_char( sysdate, 'yyyy-mm-dd'),  'yyyy-mm-dd')
, to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')
from tbl_board;
------------------------------------------------------------------------------------------------------

select 
    to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), --작성일자를 년월일만 땡겨와서 다시 날짜로 바꾸면 0시 0분 0초가 된다.
    to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd'),
    to_date(to_char(writeday, 
    'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') --날짜 - 날짜는 숫자, 일수차이 
from tbl_board;



select 
    to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), --작성일자를 년월일만 땡겨와서 다시 날짜로 바꾸면 0시 0분 0초가 된다.
    to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd'),
    to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') --날짜 - 날짜는 숫자, 일수차이 
from tbl_board;
                        
                        
                        
select 
    to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 
    to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd'),
    to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7;
    
from tbl_board;




select writeday
     , to_char(writeday, 'yyyy-mm-dd hh24:mi:ss')
     , to_char(writeday, 'yyyy-mm-dd')
     , to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd')
     , to_char(to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 'yyyy-mm-dd hh24:mi:ss')
     , sysdate
     , to_char(to_date(to_char(sysdate, 'yyyy-mm-dd')), 'yyyy-mm-dd hh24:mi:ss')
from tbl_board; 

select to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd')
     , to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')
     , to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd')  
from tbl_board; 

select * 
from tbl_board
where to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7; 










SELECT COUNT(*) AS TOTAL
      ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 6, 1, 0)) as PREVIOUS6
    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 5, 1, 0)) as PREVIOUS5
    ,   SUM(decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 4, 1, 0))as PREVIOUS4
    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 3, 1, 0))as PREVIOUS3
    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 2, 1, 0)) as PREVIOUS2
    , SUM(  decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 1, 1, 0)) as PREVIOUS1
    ,  SUM( decode(to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd'), 0, 1, 0)) as TODAY
FROM tbl_board
WHERE to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7; 
-- map도 한 행이다  -- 2	0	0	0	0	0	1	1 이걸 map으로 본다







-- // 이번달 일자별 게시글 작성건수

select decode( grouping(to_char(writeday, 'yyyy-mm-dd')),0,to_char(writeday, 'yyyy-mm-dd'), '전체' ) as writeday
    , count(*) as cnt
from tbl_board
where to_char (writeday, 'yyyymm') = to_char(sysdate,'yyyymm')
group by rollup( to_char(writeday, 'yyyy-mm-dd'));