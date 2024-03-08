---- **** === 오라클 계정 생성하기 시작 === **** ----

-- 오라클 계정 생성을 위해서는 SYS 또는 SYSTEM 으로 연결하여 작업을 해야 합니다. [SYS 시작] --
show user;
-- USER이(가) "SYS"입니다.

-- 오라클 계정 생성시 계정명 앞에 c## 붙이지 않고 생성하도록 하겠습니다.
alter session set "_ORACLE_SCRIPT"=true;
-- Session이(가) 변경되었습니다.

-- 오라클 계정명은 JDBC_USER 이고 암호는 gclass 인 사용자 계정을 생성합니다.
create user JDBC_USER identified by gclass default tablespace users; 
-- User JDBC_USER이(가) 생성되었습니다.

-- 위에서 생성되어진 JDBC_USER 이라는 오라클 일반사용자 계정에게 오라클 서버에 접속이 되어지고,
-- 테이블 생성 등등을 할 수 있도록 여러가지 권한을 부여해주겠습니다.
grant connect, resource, create view, unlimited tablespace to JDBC_USER;
-- Grant을(를) 성공했습니다.

---- **** === 오라클 계정 생성하기 끝 === **** ----

show user;
-- USER이(가) "JDBC_USER"입니다.



create table tbl_memo
(no          number(4) -- 글번호 
,name        Nvarchar2(20) not null
,msg         Nvarchar2(100) not null
,writeday    date default sysdate
,constraint  PK_tbl_memo_no primary key(no)
);
-- Table TBL_MEMO이(가) 생성되었습니다.

create sequence seq_memo
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_MEMO이(가) 생성되었습니다.

select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
order by no desc;




select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday from tbl_memo order by no desc 



update tbl_memo set writeday = writeday - 1
where no between 1 and 3;

commit;


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
order by no desc; 

select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
where to_char(writeday,'yyyy-mm-dd') = '2024-03-06'
order by no desc;



select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
where no = '1' -- string type도 호환 가능하다
order by no desc;

select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
where msg like '%'||'점심'||'%'
order by no desc;
--  문자 찾기 


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
where msg like '%'||'^^'||'%'
order by no desc;


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
where name = '%'||'^^'||'%'
order by no desc;


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from tbl_memo
where msg like '차은우'
order by no desc;


---tbl_memo 테이블에 updateday 컬럼을 추가합니다 -----
alter table tbl_memo
add updateday date; -- not null 할 수 없음
-- Table TBL_MEMO이(가) 변경되었습니다.


select *
from tbl_memo;
/*
1	김승혜	^^	24/03/06	
2	윤두준	나는 가수입니다	24/03/06	
3	안녕하세요	안녕히계세요	24/03/06	

*/


select name, msg
from tbl_memo
where no = 2;
-- 이것을 바꾸려면

commit;
rollback;


select name, msg
from tbl_memo
where no = 65453; -- 존재하지 않는 숫자를 막 넣으면 데이터가 없다 바꿔준다 



select name, msg
from tbl_memo
where no = 2;
 
 
  " update tbl_memo set name = ?, msg = ? "
							+ " where no = ? ";
                            
                            
select name, msg, select *
 from view_emp_age;
day
from tbl_memo
where no = 2;


update tbl_memo set name = '영학서', msg = '불금이오니 칼퇴근 합니다.'
where no = 1;

commit;
rollback;

select *
from user_tables
where table_name = 'TBL_MEMO';


select *
from user_sequences
where sequence_name = 'SEQ_MEMO';
