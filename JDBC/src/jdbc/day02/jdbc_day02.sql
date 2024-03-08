show user;
-- USER이(가) "JDBC_USER"입니다.

-- 1) 학급테이블 생성
create table tbl_class
(classno        number(3)
,classname      varchar2(100)
,teachername    varchar2(20)
,constraint PK_tbl_class_classno primary key(classno)
);
-- Table TBL_CLASS이(가) 생성되었습니다.

create sequence seq_classno
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_CLASSNO이(가) 생성되었습니다.

insert into tbl_class(classno, classname, teachername) 
values(seq_classno.nextval, '자바웹프로그래밍A', '김샘'); 

insert into tbl_class(classno, classname, teachername) 
values(seq_classno.nextval, '자바웹프로그래밍B', '이샘');

insert into tbl_class(classno, classname, teachername) 
values(seq_classno.nextval, '자바웹프로그래밍C', '서샘');

commit;

select *
from tbl_class;


-- 2) 학생테이블 생성 
create table tbl_student
(stno           number(8)               -- 학번
,name           varchar2(20) not null   -- 학생명
,tel            varchar2(15) not null   -- 연락처
,addr           varchar2(100)           -- 주소
,registerdate   date default sysdate    -- 입학일자
,fk_classno     number(3) not null      -- 학급번호
,constraint PK_tbl_student_stno primary key(stno)
,constraint FK_tbl_student_classno foreign key(fk_classno) references tbl_class(classno)
);    
-- Table TBL_STUDENT이(가) 생성되었습니다.

-- 학번에 사용할 시퀀스 생성
create sequence seq_stno
start with 9001
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_STNO이(가) 생성되었습니다.

insert into tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(seq_stno.nextval, '이순신', '02-234-5678', '서울시 강남구 역삼동', default, 1);

insert into tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(seq_stno.nextval, '김유신', '031-345-8876', '경기도 군포시', default, 2);

insert into tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(seq_stno.nextval, '안중근', '02-567-1234', '서울시 강서구 화곡동', default, 2);

insert into tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(seq_stno.nextval, '엄정화', '032-777-7878', '인천시 송도구', default, 3);

insert into tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(seq_stno.nextval, '박순신', '02-888-9999', '서울시 마포구 서교동', default, 3);

commit;

select *
from tbl_student;


select to_number('9008')
from dual;



select to_number('Fdsfsdfs')
from dual;
-- ORA-01722: 수치가 부적합합니다