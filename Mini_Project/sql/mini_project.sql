-- 지원 1
    create table tbl_apply
    (Pk_apply_no  number(3)
    ,Fk_recruit_notice_code varchar2(10)
    ,Fk_user_id             varchar2(15)
    ,apply_date             date   default sysdate
    ,pass_fail              varchar2(2)
    ,constraint Pk_tbl_apply_apply_no primary key(Pk_apply_no)
    ,constraint Fk_tbl_apply_user_id foreign key(Fk_user_id) references tbl_member(user_id)
    ,constraint Fk_tbl_apply_recruit_notice foreign key(Fk_recruit_notice_code) references tbl_recruit_notice(Pk_recruit_notice_code)
    );
    
    
    
    select *
    from tbl_join_recruit_notice;
    
    insert into tbl_join_recruit_notice(pk_join_no,fk_user_id,fk_recruit_notice_code) values(seq_tbl_join_recruit.nextval,?,?)
    
    select *
    from tbl_recruit_notice;
    
    desc tbl_company_detail;
    
    alter table tbl_company_detail
    modify sales varchar2(50);
    
    commit;
    
    select *
    from tbl_member;
    
    select *
    from tbl_user_resume;
    
    delete tbl_user_resume
    where fk_resume_code = ?
    
    select *
    from user_tables;
    
    select *
    from user_sequences;
    
    select *
    from tbm_resume;
    
    select *
    from tbl_member;
    
     -- 업종 2
    create table tbl_buss_type
    (pk_buss_type_code varchar2(10)
    ,buss_type_name Nvarchar2(20) not null
    );
    
    
     -- 경력상세정보 3
    create table tbl_career
    (Pk_career_info_code        NUMBER(10)      
    ,work_status                VARCHAR2(10)   not null     
    ,hire_date                  DATE           not null
    ,finish_date                DATE
    ,work_dept                  NVARCHAR2(15)  
    ,my_yearsal                 VARCHAR2(15)   
    ,Fk_company_code            VARCHAR2(10)
    ,Fk_location_code           VARCHAR2(10)
    ,Fk_job_type_code           VARCHAR2(10)        
    ,Fk_worktype_code           VARCHAR2(10) 
    
    ,constraint Pk_tbl_career_career_info_code primary key(Pk_career_info_code)
    ,constraint fk_tbl_career_company_code foreign key (Fk_company_code) references tbl_company_login(Fk_company_code)
    ,constraint fk_tbl_career_location_code foreign key (Fk_location_code) references tbl_location(location_code)
    ,constraint fk_tbl_career_job_type_code foreign key (Fk_job_type_code) references tbl_job_type(job_type_code)
    ,constraint fk_tbl_career_worktype_code foreign key (Fk_worktype_code) references tbl_worktype(worktype_code)
    );
    
    
    
    -- 자격증정보 4
    create table tbl_certifi
    (Pk_certifi_code    varchar2(10)
    ,certifi_name       NVARCHAR2(30)
    
    ,constraint Pk_tbl_certifi_code primary key(Pk_certifi_code)
    );

    
    
    
    
    -- 기업상세정보  5
    create table tbl_company_detail 
    (Pk_company_code        VARCHAR2(10)       
    ,company_name           VARCHAR2(30)     not null
    ,sales                  VARCHAR2(20)       
    ,number_of_employee     NUMBER(5)        not null
    ,homepage               VARCHAR2(300)
    ,establishment_date     DATE             not null
    ,boss_name              NVARCHAR2(5)     not null
    ,fk_buss_type_code      VARCHAR2(10)     not null
    ,company_address        VARCHAR2(50)
    ,company_area           VARCHAR2(10)
    ,company_can_people     NUMBER(5)
    ,Fk_company_size_code   VARCHAR2(10)
    
    ,constraint Pk_tbl_com_detail primary key(Pk_company_code)
    ,constraint FK_tbl_com_detail_buss foreign key(Fk_buss_type_code) references tbl_buss_type(pk_buss_type_code)
    ,constraint FK_tbl_com_detail_com_size foreign key(Fk_company_size_code) references tbl_company_size(com_size_code)
    );
    
    select *
    from tbl_company_detail;
    
    commit;
   
    -- 기업회원로그인 테이블 6
     create table tbl_company_login
    (Pk_Fk_company_code   varchar2(10)     -- 기업코드
    , company_passwd   varchar2(20)   not null  -- 비밀번호
    , company_name      nvarchar2(30)   -- 기업명
    , Fk_resume_code  varchar2(10)  -- 이력서코드
    
    ,constraint pk_tbl_company_login primary key(Fk_company_code)
    ,constraint FK_TBL_COMPANY_LOGIN foreign key(pk_company_code) references tbl_company_detail(pk_company_code)
    ); 

    
    
    
    -- 기업규모 테이블 7 
    create table tbl_company_size
    (pk_com_size_code varchar2(10)
    ,com_size      Nvarchar2(5) not null
    ,constraint Pk_tbl_company_size primary key(com_size_code)
    );    
    
    
    
    -- 직무 형태 8
    create table tbl_job_type
    (pk_job_type_code VARCHAR2(10) 
    ,job_type_name NVARCHAR2(20) not null
    ,constraint Pk_job_type_code primary key(job_type_code)
    );
    
    
    
    -- 지역 9
    create table tbl_location
    (pk_location_code  VARCHAR2(10)
    , location_name NVARCHAR2(20)  not null
    , constraint PK_tbl_location primary key(location_code) 
    );
    
    
    
    -- 구직자 정보 테이블 10
    create table tbl_member
    (pk_user_id        varchar2(15)
    ,user_email     varchar2(50)    not NULL    
    ,user_passwd    varchar2(20)    not NULL
    ,user_name      NVARCHAR2(10)   NOT NULL
    ,user_jubun     varchar2(14)    NOT NULL
    ,user_tel       varchar2(13)    NOT NULL
    ,resume_code        varchar2(10) 
    ,location_code  varchar2(10)    NOT NULL
    ,constraint pk_school_dept primary key(user_id)
    ,constraint fk_tbl_member_tbl_resume foreign key(resume_code) references tbl_resume (resume_code)
    ,constraint fk_tbl_member_tbl_location foreign key(location_code) references tbl_location(location_code)
    );
    
    
    
    
     -- 개인회원로그인 11
    create table tbl_member_login
    (Pk_fk_user_id             VARCHAR2(15)     not null
    ,user_passwd            VARCHAR2(20)
    ,user_name              NVARCHAR2(10)
    ,Fk_recruit_notice_code VARCHAR2(10)
    
    ,constraint tbl_member_login_user_id primary key(Pk_user_id)
    ,constraint FK_tbl_member_login foreign key(Fk_job_code) references tbl_worktype(worktype_code)
    ,constraint FK_TBL_MEMBER_LOGIN_tbl_member foreign key(pk_fk_user_id) references tbl_member(pk_user_id)
    );
    
    
    
    
    -- 내 자격증 12
    create table tbl_mycertifi
    (Pk_Fk_certifi_code    varchar2(10)
    ,mycertifi_date     DATE
    
    ,constraint Pk_certifi_code foreign key(Fk_certifi_code) references tbl_certifi(Pk_certifi_code)
    );
    
    
    
    
    -- 직위 테이블 13
    create table tbl_position
    (pk_position_code varchar2(10)
    ,position_name Nvarchar2(10) not null
    ,constraint Pk_tbl_position_position_code primary key(position_code)
    );
    
    
    
    
    -- 모집공고 14
    create table tbl_recruit_notice
    (Pk_recruit_notice_code     VARCHAR2(10)        
    ,recruit_notice_name        NVARCHAR2(30)      not null 
    ,yearsal                    VARCHAR2(15)       not null 
    ,quail                      NVARCHAR2(30)      not null
    ,woodae                     NVARCHAR2(50)       
    ,benefit                    NVARCHAR2(200)     
    ,recruit_start_day          DATE               not null
    ,recruit_finish_day         DATE               not null
    ,Fk_position_code           VARCHAR2(10)       not null
    ,Fk_company_code            VARCHAR2(10)       not null
    ,Fk_worktype_code           VARCHAR2(10)       not null
    ,Fk_job_type_code           VARCHAR2(10)       not null
    ,Fk_recruit_step_code_1     VARCHAR2(10)       not null
    ,Fk_recruit_step_code_2     VARCHAR2(10)       
    ,Fk_recruit_step_code_3     VARCHAR2(10)      
    ,Fk_recruit_step_code_4     VARCHAR2(10)       
    ,Fk_recruit_step_code_5     VARCHAR2(10)       
    ,Fk_location_code           VARCHAR2(10)       not null
    ,apply_no                   NUMBER(3)          not null
    
    ,constraint Pk_tbl_recruit_notice_code primary key(Pk_recruit_notice_code)
    ,constraint FK_tbl_recruit_notice_1 foreign key(Fk_position_code) references tbl_position(position_code)
    ,constraint FK_tbl_recruit_notice_2 foreign key(Fk_company_code) references tbl_company_detail(Pk_company_code)
    ,constraint FK_tbl_recruit_notice_3 foreign key(Fk_worktype_code) references tbl_worktype(worktype_code)
    ,constraint FK_tbl_recruit_notice_4 foreign key(Fk_job_type_code) references tbl_job_type(job_type_code)
    ,constraint FK_tbl_recruit_notice_5_1 foreign key(Fk_recruit_step_code_1) references tbl_recruit_step(recruit_step_code)
    ,constraint FK_tbl_recruit_notice_5_2 foreign key(Fk_recruit_step_code_2) references tbl_recruit_step(recruit_step_code)
    ,constraint FK_tbl_recruit_notice_5_3 foreign key(Fk_recruit_step_code_3) references tbl_recruit_step(recruit_step_code)
    ,constraint FK_tbl_recruit_notice_5_4 foreign key(Fk_recruit_step_code_4) references tbl_recruit_step(recruit_step_code)
    ,constraint FK_tbl_recruit_notice_5_5 foreign key(Fk_recruit_step_code_5) references tbl_recruit_step(recruit_step_code)
    ,constraint FK_tbl_recruit_notice_6 foreign key(Fk_location_code) references tbl_location(location_code)
    );
    
    
    
    
    -- 모집전형 테이블 15
    create table TBL_RECRUIT_STEP
    (pk_recruit_step_code    VARCHAR2(10)
    ,recruit_step_name    NVARCHAR2(10)   not null
    ,constraint pk_tbl_recruit_step primary key(recruit_step_code)
    );
    
    
    
    
    -- 이력서 16
    create table tbl_resume
    (pk_resume_code        varchar2(10)        
    ,self_introduce        nvarchar2(50)    not null   
    ,army                  varchar2(10)     not null
    ,my_certifi_no_1     varchar2(10)
    ,my_certifi_no_2     varchar2(10)
    ,my_certifi_no_3     varchar2(10)
    ,my_certifi_no_4     varchar2(10)
    ,my_certifi_no_5     varchar2(10)
    ,edu_info_1           varchar2(10)        not null
    ,edu_info_2           varchar2(10)        
    ,edu_info_3           varchar2(10)        
    ,job_type_code      varchar2(10)        not null
    ,want_yearsal          varchar2(15)     not null
    ,award_act_list        nvarchar2(200)  
    ,resume_date           date             default sysdate
    ,Fk_career_info_code  NUMBER(10)         
    ,constraint pk_tbl_resume primary key (resume_code)
    ,constraint FK_tbl_resume_edu_info foreign key (edu_info) references tbl_school_info(edu_info)
    ,constraint FK_tbl_resume_job_type_code foreign key (job_type_code) references tbl_job_type(job_type_code)
    );
    
     alter table tbl_career
    modify pk_career_info_code varchar2(30);
    
    alter table tbl_resume
    rename column Fk_career_info_code to fk_career_info_code_1;
    
    alter table tbl_resume
    add constraints fk_tbl_resume_career_1 foreign key(fk_career_info_code_1) references tbl_career(pk_career_info_code); 
    alter table tbl_resume
    add constraints fk_tbl_resume_career_2 foreign key(fk_career_info_code_2) references tbl_career(pk_career_info_code); 
    alter table tbl_resume
    add constraints fk_tbl_resume_career_3 foreign key(fk_career_info_code_3) references tbl_career(pk_career_info_code); 
    alter table tbl_resume
    add constraints fk_tbl_resume_career_4 foreign key(fk_career_info_code_4) references tbl_career(pk_career_info_code); 
    alter table tbl_resume
    add constraints fk_tbl_resume_career_5 foreign key(fk_career_info_code_5) references tbl_career(pk_career_info_code); 
    
    commit;
    
    
    
    alter table tbl_resume
    modify fk_career_info_code_1 varchar2(10);
    
    alter table tbl_resume
    add fk_career_info_code_2 varchar2(10);
    
    alter table tbl_resume
    add fk_career_info_code_3 varchar2(10);
    
    alter table tbl_resume
    add fk_career_info_code_4 varchar2(10);
    
    alter table tbl_resume
    add fk_career_info_code_5 varchar2(10);
    
    alter table tbl_resume
    drop constraint FK_TBL_RESUME_TBL_CAREER;
    
    alter table tbl_resume
    add constraints fk_edu_info_2 foreign key(edu_info_3) references tbl_school_info(pk_edu_info);
    
    commit;
    
    desc tbl_school_info;
    
    select *
    from user_constraints
    where table_name = 'TBL_RESUME';
    
    desc tbl_resume;
    
    alter table tbl_resume
    rename COLUMN edu_info to edu_info_1;
    
    from 
    
    select *
    from tbl_mycertifi
    order by 1;
    
    select *
    from user_tables;
    
    select *
    from tbl_eduinfo;
    
    select *
    from tbl_school_info;
    select *
    from tbl_resume;
    
    select *
    from tbl_company_detail;
    
    -- 기업명      연매출액           사원수     홈페이지                    업종           	 본사주소                   기업규모   			
    
    
    
    
    from tbl_company_detail
    
    
    select sales
         , substr(sales, 1, instr(sales, ',', 1, 1) - 1) || substr(sales, instr(sales, ',', 1, 1) + 1)
         , instr(sales, ',', 1, 1)
    from tbl_company_detail;
    
    desc tbl_company_detail;
    
    
    
    select '2,950,000,000,000', func_delcomma('2,950,000,000,000'),
           '500000000', func_delcomma('500000000')
    from dual;
    
    
    select COMPANY_NAME, SALES, func_delcomma(SALES) AS 세일즈
    from tbl_company_detail;
    
    
    select *
    from tbl_company_detail;
    
    
    
    WITH
    CD AS
    (
    select company_name, sales, number_of_employee, homepage, fk_buss_type_code, company_address, fk_company_size_code
    from tbl_company_detail
    where to_number(func_delcomma(sales)) > 100000
    )
    SELECT CD.company_name, CD.sales, CD.number_of_employee, CD.homepage, B.buss_type_name, CD.company_address, S.com_size
    FROM CD JOIN tbl_buss_type B
    on CD.fk_buss_type_code = B.pk_buss_type_code
    JOIN tbl_company_size S
    on CD.fk_company_size_code = S.pk_com_size_code;
    
    -- 공고번호 			 공고명                         기업명           직위         근무지역            연봉           근무형태         공고마감일      \r\n"
    
    
    WITH
    N AS
    (
    select *
    from TBL_RECRUIT_NOTICE
    where to_number(func_delcomma(yearsal)) > 3000
    )
    SELECT N.pk_recruit_notice_code, N.recruit_notice_name,C.company_name,P.position_name,L.location_name,N.yearsal||'만원' as yearsal,W.worktype_name, N.recruit_finish_day
    FROM N JOIN tbl_company_detail C
    on N.fk_company_code = C.pk_company_code
    JOIN tbl_position P
    on P.pk_position_code = N.fk_position_code
    JOIN tbl_location L 
    on N.fk_location_code = L.pk_location_code
    JOIN tbl_worktype W
    on W.pk_worktype_code = N.fk_worktype_code
    
    select *
    from tbl_recruit_notice
    
    select *
    from tbl_worktype;
    
    commit;
    
    select *
    from user_tables;
    
    select *
    from tbl_career;
    
    select *
    from tbl_company_size;
    
    select *
    from tbl_buss_type;
    
    commit;
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_resume;
    
    select SD.school_dept_name, SI.pk_edu_info
    from tbl_school_dept SD JOIN tbl_school_info SI
    on SD.pk_school_dept_code = SI.school_dept_code
    where school_dept_name = '영어과';
    
    select *
    from tbl_school;
    
    select *
    from tbl_member;
    
    update tbl_member set user_status = '1';
    
    commit;
    
    select *
    from tbl_resume;
    
    select *
    from tbl_school_info;
    
    desc tbl_member;
    
    insert into tbl_resume (pk_resume_code, self_introduce, army, my_certifi_no_1,my_certifi_no_2,my_certifi_no_3, edu_info_1, edu_info_2, job_type_code, want_yearsal,  fk_career_info_code_1)
    values (seq_pk_resume_code.nextval,'안녕하세요 저는 최고의 일꾼입니다.','미필',16,20,22,'10','13','06-01','3,000만원','4');
    
    commit;
    
    select *
    from tbl_career;
    
    
    select *
    from tbl_school_info;
    
    select *
    from tbl_mycertifi;
    
    select *
    from tbl_resume;
    
    select *
    from tbl_member;
    
    select *
    from tbl_member_login;
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_buss_type;
    
    select *
    from tbl_recruit_notice;
    
    
    
    commit;
    
    select *
    from tbl_resume;
    
    update tbl_member set resume_code =  '1'
    where pk_user_id = 'jeongws';
    
    select *
    from tbl_career;

    insert into tbl_career (pk_career_info_code, work_status, hire_date,   my_yearsal, fk_company_code, fk_location_code, fk_job_type_code, fk_worktype_code)
    values(seq_pk_cureer_info.nextval, '1', '20-01-11',  '2,700만원','4123456789','031-01','04-01','06');
    
    select *
    from tbl_career;
   
    commit;
    select *
    from tbl_career;
    
    select *
    from tbl_worktype;
    
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_location;
   
    select *
    from tbl_job_type;
   
   
    
    select *
    from user_constraints
    where table_name = 'TBL_CAREER';
    
    select *
    from tbl_job_type;
    
    select *
    from tbl_resume;
    
    select *
    from user_tables;
    
    alter table tbl_school_info
    modify school_code varchar2(15);

    create sequence seq_edu_info;

    desc tbl_resume;
    
    select *
    from tbl_school_info;
    
     -- 졸업학교 테이블 17
    create table tbl_school
    (pk_school_code      varchar2(5)       
    ,school_name      Nvarchar2(15)     not null   
    ,school_dept_code      varchar2(3)   
    ,constraint PK_tbl_school primary key(school_code)
    ,constraint FK_tbl_school foreign key(school_dept_code) references tbl_school_dept (school_dept_code)
    );

    
    
    
    -- 학과 테이블  18
    create table tbl_school_dept
    (school_dept_code   varchar2(3)     not null
    ,school_dept_name    varchar2(15)   not null
    ,constraint PK_tbl_school_dept primary key(school_dept_code)
    );
    
    
    
    -- 학력정보 19
     create table tbl_school_info
    (edu_info        varchar2(10)
    ,school_code     varchar2(5)        not null
    ,school_dept_code
    ,sch_hire_date      date            not null
    ,sch_grad_date      date            not null
    ,sch_grade          varchar2(5)     
    ,constraint PK_tbl_school_info primary key(edu_info)
    ,constraint FK_tbl_school_info foreign key(school_code) references tbl_school(school_code)
    );
    
    
    
    
    
    commit;
    
      -- 근무형태 테이블 20
    create table TBL_WORKTYPE
    (worktype_code    VARCHAR2(10)  
    ,worktype_name    NVARCHAR2(10)   not null
    ,constraint pk_tbl_worktype_worktype_code primary key(worktype_code)
    );
    
    
    select *
    from TBL_COMPANY_DETAIL;
    /*
        TBL_COMPANY_SIZE 
        TBL_WORKTYPE
        TBL_BUSS_TYPE
        TBL_CERTIFI
        TBL_RECRUIT_STEP
        TBL_POSITION
        TBL_SCHOOL
        TBL_SCHOOL_DEPT
        TBL_MYCERTIFI
        TBL_LOCATION
        TBL_JOB_TYPE
        TBL_COMPANY_DETAIL
        TBL_COMPANY_LOGIN
        TBL_SCHOOL_INFO
        TBL_RECRUIT_NOTICE
        TBL_RESUME
        TBL_MEMBER
        TBL_CAREER
        TBL_MEMBER_LOGIN
        TBL_APPLY
    */
    
    create sequence seq_fk_recruit_notice_code;
    create sequence seq_pk_apply_no;
    create sequence seq_pk_resume_code;
    create sequence seq_pk_cureer_info;
    
    
    select *
    from TBL_member;
    
    select *
    from tbl_recruit_notice;
    
    desc tbl_career;
    
    alter table tbl_recruit_notice modify fk_company_code null;
    
    update tbl_career set FK_COMPANY_CODE = null;
    
    commit;
    
    select *
    from tbl_member;
    
    insert into tbl_member_login(pk_fk_user_id, user_passwd, user_name) values('seonwooyu','Qwer1234$',	'유선우'); 
    insert into tbl_member_login(pk_fk_user_id, user_passwd, user_name) values('kangmj','Qwer1234$', '강민정');
    insert into tbl_member_login(pk_fk_user_id, user_passwd, user_name) values('parkyj','Qwer1234$', '박예진');
    insert into tbl_member_login(pk_fk_user_id, user_passwd, user_name) values('jeongws','Qwer1234$', '정우석');
    insert into tbl_member_login(pk_fk_user_id, user_passwd, user_name) values('kimsh','Qwer1234$',	'김승혜');
    
    select *
    from tbl_company_member;
    
    insert into tbl_company_member(pk_company_code,com_passwd) values('0123456789','Qwer1234$');
    insert into tbl_company_member(pk_company_code,com_passwd) values('2123456789','Qwer1234$');
    insert into tbl_company_member(pk_company_code,com_passwd) values('3123456789','Qwer1234$');
    insert into tbl_company_member(pk_company_code,com_passwd) values('1123456789','Qwer1234$');
    insert into tbl_company_member(pk_company_code,com_passwd) values('4123456789','Qwer1234$');
    
    commit;
    
    insert into tbl_company_login(pk_fk_company_code,com_passwd,company_name) values('4123456789','Qwer1234$','BaeMin');
    insert into tbl_company_login(pk_fk_company_code,com_passwd,company_name) values('1123456789','Qwer1234$','카카오');
    insert into tbl_company_login(pk_fk_company_code,com_passwd,company_name) values('3123456789','Qwer1234$','쿠팡');
    insert into tbl_company_login(pk_fk_company_code,com_passwd,company_name) values('2123456789','Qwer1234$','LINE');
    insert into tbl_company_login(pk_fk_company_code,com_passwd,company_name) values('0123456789','Qwer1234$','네이버');
    
    desc tbl_company_detail;
    
    insert into tbl_company_detail(pk_company_code, company_name, number_of_employee, establishment_date, boss_name, fk_buss_type_code, company_email, company_passwd, company_address, company_tel)
           values ('0100000000', '네이버 주식회사',5000,sysdate,'네이버','02','naver1@naver.com','Qwer1234$','서울시 중랑구','02-4444-4444');
    
    commit;
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_member_login;
    
    select*
    from tbl_member
    where pk_user_id = 'jeongws';
    
    select *
    from user_constraints
    where table_name = 'TBL_MEMBER';
    
    
    select *
    from tbl_buss_type;
    
    select *
    from tbl_company_size;
    
    
    WITH
    V AS
    (
    select *
    from tbl_company_detail
    where company_name like '%네이%'
    )
    select V.company_name, V.sales, V.number_of_employee, V.establishment_date, V.boss_name, V.company_address, B.buss_type_name, C.com_size
    FROM V JOIN tbl_buss_type B
    ON V.fk_buss_type_code = B.pk_buss_type_code
    JOIN tbl_company_size C
    ON V.fk_company_size_code = C.pk_com_size_code;
    
    
    update tbl_company_detail set fk_company_size_code = '02'
    where company_name = '네이버 주식회사';
    commit;
    
    update tbl_company_login set company_passwd = 'Asdf1234$'
	where pk_fk_company_code = 4123456789;
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_company_login;
    
    
    update tbl_company_detail set company_passwd = 'Qwer1234$';
    
    update tbl_company_login set company_passwd = 'Qwer1234$'
    where pk_fk_company_code = '4123456789';
    
    commit;
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_company_login;
    
    desc tbl_company_login;
    
    select *
    from tbl_company_login L JOIN tbl_company_detail D
    ON L.pk_fk_company_code = D.pk_company_code;
    
    select *
    from tbl_member;
    
    select *
    from tbl_buss_type B JOIN tbl_company_detail C
    ON B.pk_buss_type_code = C.fk_buss_type_code
    JOIN tbl_company_size S 
    ON S.pk_com_size_code = C.fk_company_size_code
    where company_name like '%네이%';
    
    update tbl_company_detail  set fk_company_size_code = '02'
    where company_name = '네이버 주식회사';
    
    select *
    from tbl_company_detail;
    
    commit;
    
    select *
    from tbl_member_login;
    
    select M.user_name as name, M.pk_user_id as user_id, M.user_email as email, L.location_name as location_name, M.user_tel, Rpad(substr(user_jubun,1,8),length(user_jubun),'*') as jubun
    from tbl_member M JOIN tbl_location L
    on M.location_code = L.pk_location_code;
    
    select *
    from tbl_member;
    
    select *
    from tbl_buss_type
    where buss_type_name like '%it%';
    
    delete tbl_company_detail
    where company_name = '네이버 주식회사';
    
    desc tbl_member_login;
    
    select *
    from tbl_member_login;
    
    select *
    from tbl_member;
    
    update tbl_member set user_email = 'jeongws1234@naver.com'
    where pk_user_id = 'jeongws';
    
    commit;
    
    select *
    from tbl_recruit_notice;
    
    select *
    from tbl_company_detail;
    
    /*
        4123456789
        1123456789
        3123456789
        2123456789
        0123456789
    */
    
    update tbl_recruit_notice set fk_company_code = '4123456789'
    where pk_recruit_notice_code = '6';
    
    update tbl_recruit_notice set fk_company_code = '1123456789'
    where pk_recruit_notice_code = '9';
    
    update tbl_recruit_notice set fk_company_code = '3123456789'
    where pk_recruit_notice_code = '10';
    
    update tbl_recruit_notice set fk_company_code = '2123456789'
    where pk_recruit_notice_code = '4';
    
    update tbl_recruit_notice set fk_company_code = '0123456789'
    where pk_recruit_notice_code = '12';
    
    select *
    from tbl_recruit_notice;
    
    select *
    from tbl_company_detail;
    
    select *
    from tbl_member;
    
    
    update tbl_member set user_status = '0'
    where pk_user_id = 'seotj';
    
    commit;
    
    
    /*
    새로운 가족을 찾습니다!                                                배달의민족    사원      서울시 송파구              6,000만원     인턴쉽             2024-05-01
Business Amalyst(비즈니스 데이터 분석) 경력자 모집합니다.         쿠팡             사원       서울시 성북구              5,000만원      정규직            2024-04-20
[카카오페이손해보험] 프론트엔드 엔지니어 모집                       카카오           사원      서울시 성북구              5,500만원      정규직            2024-04-15
실력이 안좋아도 괜찮아요 신입 개발자 모십니다.                        Line             사원       경기도 성남시 분당구    6,000만원      정규직            2024-03-29
2024 팀네이버 신입 공채(Design)                                             네이버         사원       경기도 성남시 분당구     6,000만원      정규직            2024-03-15
   

    */
    
    
    WITH
    R AS
    (
    select *
    from tbl_recruit_notice
    )
    SELECT R.pk_recruit_notice_code, R.recruit_notice_name, yearsal, C.company_name, W.worktype_name, L.location_name, P.position_name, R.recruit_finish_day
    from R JOIN tbl_company_detail C
    on R.fk_company_code = C.pk_company_code
    JOIN tbl_worktype W
    on R.fk_worktype_code = W.pk_worktype_code
    JOIN tbl_location L
    on R.fk_location_code = L.pk_location_code
    JOIN tbl_position P
    on R.fk_position_code = P.pk_position_code
    where R.recruit_notice_name like '%프론트엔드%'
    order by R.recruit_start_day desc;
    
    
    select *
    from tbl_recruit_notice;
    
    select *
    from tbl_company_detail;
    
    update tbl_recruit_notice set fk_company_code = '1123456789'
    where pk_recruit_notice_code = '10';
    
    commit;
    
    select *
    from tbl_member_login;
    
    
    alter table tbl_member_login
    modify fk_user_status varchar2(1) default '1';
    
    alter table tbl_member
    modify user_status varchar2(1) default '1';
    
    commit;
    
    select *
    from tbl_member_login;
    
    -- alter table 테이블명 add constraint 제약조건명 foreign key(컬럼명) references 부모테이블명(식별자컬럼명);
    alter table tbl_member_login
    add constraint tbl_member_login_fk_status foreign key (FK_USER_STATUS) references tbl_member(USER_STATUS);
    
   
    
    select *
    from user_constraints
    where table_name = 'TBL_MEMBER';
    
    
    select resume_code
    from tbl_member;
    
    select *
    from tbl_recruit_notice;
    
    select *
    from tbl_position;
    
    select *
    from tbl_worktype;
    
    select *
    from tbl_job_type;
    
    select *
    from tbl_recruit_step;
    
    
    select RN.recruit_notice_name, yearsal||'만원' as yearsal, quail, woodae, benefit, recruit_finish_day, P.position_name, W.worktype_name
         , listagg(S.recruit_step_name,', ') within group (order by S.pk_recruit_step_code) as recruit_step
    from tbl_recruit_notice RN JOIN tbl_position P
    on RN.fk_position_code = P.pk_position_code
    JOIN tbl_worktype W
    on RN.fk_worktype_code = W.pk_worktype_code
    JOIN tbl_job_type J
    on RN.fk_job_type_code = J.pk_job_type_code
    JOIN tbl_recruit_step S
    on RN.fk_recruit_step_code_1 = S.pk_recruit_step_code or RN.fk_recruit_step_code_2 = S.pk_recruit_step_code or RN.fk_recruit_step_code_3 = S.pk_recruit_step_code or RN.fk_recruit_step_code_4 = S.pk_recruit_step_code or RN.fk_recruit_step_code_5 = S.pk_recruit_step_code
    where RN.fk_company_code = '0123456789';
    
    
    
    
    
    
    
    
    
    SELECT RN.recruit_notice_name, yearsal||'만원' as yearsal, quail, woodae, benefit, recruit_finish_day
     , P.position_name
     , C.company_name
     , W.worktype_name
     , J.job_type_name
     , L.location_name
     , S1.recruit_step_name as recruit_step_name_1
     , S2.recruit_step_name as recruit_step_name_2
     , S3.recruit_step_name as recruit_step_name_3
     , S4.recruit_step_name as recruit_step_name_4
     , S5.recruit_step_name as recruit_step_name_5
    FROM tbl_recruit_notice RN JOIN tbl_position P
    ON RN.fk_position_code = P.pk_position_code
    JOIN tbl_company_detail C
    ON RN.fk_company_code = C.pk_company_code
    JOIN tbl_worktype W
    ON RN.fk_worktype_code = W.pk_worktype_code
    JOIN tbl_job_type J
    ON RN.fk_job_type_code = J.pk_job_type_code
    JOIN tbl_location L
    ON RN.fk_location_code = L.pk_location_code
    JOIN tbl_recruit_step S1
    ON RN.fk_recruit_step_code_1 = S1.pk_recruit_step_code
    LEFT JOIN tbl_recruit_step S2
    ON RN.fk_recruit_step_code_2 = S2.pk_recruit_step_code
    LEFT JOIN tbl_recruit_step S3
    ON RN.fk_recruit_step_code_3 = S3.pk_recruit_step_code
    LEFT JOIN tbl_recruit_step S4
    ON RN.fk_recruit_step_code_4 = S4.pk_recruit_step_code
    LEFT JOIN tbl_recruit_step S5
    ON RN.fk_recruit_step_code_5 = S5.pk_recruit_step_code
    where RN.pk_recruit_notice_code = 12 and RN.fk_company_code = '0123456789';

    desc tbl_recruit_notice;

    select *
    from tbl_recruit_notice;
    
    select *
    from tbl_member_login;
    
    
    update tbl_member_login set fk_user_status = '1';
    
    commit;
    
    select *
    from user_tables;
    
    select *
    from tbl_resume;
    
    select *
    from tbl_worktype;
    
    desc tbl_worktype;
    
    
    
    
    
    
    
    select *
    from tbl_location;
    
    select recruit_step_name
    from tbl_recruit_step;
    
    select *
    from tbl_recruit_notice;
    
    
                        SELECT case when apply_no > 0 then RN.recruit_notice_name||'['||RN.apply_no||']' else RN.recruit_notice_name end as recruit_notice_name
              		     , yearsal||'만원' as yearsal 
              		     , quail 
              		     , woodae 
              		     , benefit 
              		     , recruit_finish_day 
              		     , P.position_name 
              		     , C.company_name 
              		     , W.worktype_name 
              		     , J.job_type_name 
              		     , L.location_name 
              		     , S1.recruit_step_name as recruit_step_name_1 
              		     , S2.recruit_step_name as recruit_step_name_2 
              		     , S3.recruit_step_name as recruit_step_name_3 
              		     , S4.recruit_step_name as recruit_step_name_4 
              		     , S5.recruit_step_name as recruit_step_name_5 
              		    FROM tbl_recruit_notice RN JOIN tbl_position P 
              		    ON RN.fk_position_code = P.pk_position_code 
              		    JOIN tbl_company_detail C 
              		    ON RN.fk_company_code = C.pk_company_code 
              		    JOIN tbl_worktype W 
              		    ON RN.fk_worktype_code = W.pk_worktype_code 
              		    JOIN tbl_job_type J 
              		    ON RN.fk_job_type_code = J.pk_job_type_code 
              		    JOIN tbl_location L 
              		    ON RN.fk_location_code = L.pk_location_code 
              		    JOIN tbl_recruit_step S1 
              		    ON RN.fk_recruit_step_code_1 = S1.pk_recruit_step_code 
              		    LEFT JOIN tbl_recruit_step S2 
              		    ON RN.fk_recruit_step_code_2 = S2.pk_recruit_step_code 
              		    LEFT JOIN tbl_recruit_step S3 
              		    ON RN.fk_recruit_step_code_3 = S3.pk_recruit_step_code 
              		    LEFT JOIN tbl_recruit_step S4 
              		    ON RN.fk_recruit_step_code_4 = S4.pk_recruit_step_code 
              		    LEFT JOIN tbl_recruit_step S5 
              		    ON RN.fk_recruit_step_code_5 = S5.pk_recruit_step_code 
                        where recruit_finish_day - sysdate > 0
                    
                    
                    update tbl_recruit_notice set recruit_finish_day = '240319'
                    where fk_company_code = '0123456789';
                    
                    select company_name
                         , sales||'원' as sales
                         , number_of_employee||'명' as number_of_employee
                         , homepage
                         , boss_name
                         , BT.buss_type_name
                         , company_address
                         , CS.com_size
                         , company_tel
                         , company_email
                    from tbl_company_detail CD JOIN tbl_buss_type BT
                    on CD.fk_buss_type_code = BT.pk_buss_type_code
                    JOIN tbl_company_size CS
                    on CD.fk_company_size_code = CS.pk_com_size_code;
                    
                    select *
                    from tbl_company_detail;
                    
                    select pk_buss_type_code
                    from tbl_buss_type
                    where buss_type_name = '경영.사무';
                    
                    select pk_com_size_code
                    from tbl_company_size
                    where com_size = ?;
                    
                    select *
                    from tbl_company_size;
                    
                    
    select *
    from tbl_army;

select *
from tbl_resume;

select *
from user_tables;

select *
from tbl_resume;


select *
from tbl_resume;

alter table tbl_resume
modify army varchar2(15);

commit;

select *
from tbl_career;

select *
from tbl_certifi;

select *
from user_constraints
where table_name = 'TBL_RESUME';

select *
from tbl_mycertifi;

select *
from tbl_resume R JOIN tbl_member M
on R.pk_resume_code = M.resume_code;



alter table tbl_mycertifi
drop column my_certifi_date;

select *
from tbl_member;

select *
from tbl_resume;

select *
from tbl_mycertifi;

select *
from tbl_mycertifi;

select *
from tbl_certifi;

select *
from tbl_member;




select *
from tbl_member M JOIN tbl_resume R
ON M.resume_code = R.pk_resume_code
JOIN tbl_mycertifi MC
on M.pk_user_id = MC.fk_user_id
JOIN tbl_certifi C 
ON MC.fk_certifi_code = C.pk_certifi_code
where pk_user_id = 'parkyj';

select *
from tbl_member;

map.put("resume_name", resume_name);
map.put("get_army_name", get_army_name);
map.put("want_yearsal", want_yearsal);
map.put("get_certifi_code", get_certifi_code);
map.put("award_act_list", award_act_list);
map.put("get_job_type_code", get_job_type_code);

insert into tbl_resume(pk_resume_code,self_introduce, army, want_yearsal, my_certifi_no_1, award_act_list, job_type_code) values(SEQ_PK_RESUME_CODE.nextval,?,?,?,?,?,?)
                       
                       select *
                   from user_sequences;    
                                
                                
                                select pk_resume_code
                                from tbl_resume R JOIN tbl_member M
                                on R.pk_resume_code = M.resume_code
                                
                                select *
                                from tbl_member;
                                
                                from tbl_m
                                
                                update tbl_member set resume_code = '24'
                                where pk_user_id = 'jeongws';
                                
                                select *
                                from tbl_resume;
                                
                               
			         		 select R.pk_resume_code 
			         		    , R.self_introduce 
			         		    , R.army 
			         		    , R.want_yearsal 
			         		    , R.award_act_list 
			         		    , R.resume_date 
			         		    , C2.certifi_name 
			         		    , R.job_type_code 
			         		 from tbl_resume R 
                             join tbl_member M 
			         		 on R.pk_resume_code = M.resume_code
			         		 join tbl_mycertifi C1 
			         		 on R.my_certifi_no_1 = C1.my_certifi_no 
			         		 join tbl_certifi C2 
			         		 on C1.fk_CERTIFI_code = C2.Pk_certifi_code 
			         		 where M.PK_USER_ID = 'jeongws';
                                
                                select *
                                from tbl_resume;
                                
                                select *
                                from tbl_mycertifi
                                
                                select *
                                from tbl_mycertifi;
                                
                                
                                
                                commit;
                                
                                select *
                                from tbl_member;
                                
                                commit;
                                
                                select *
                                from tbl_resume;
                                
                                
                                
                         select R.pk_resume_code 
		         		, R.self_introduce 
		         		, R.army 
		         		, R.want_yearsal 
		         		, R.award_act_list 
		         		, R.resume_date 
		         		, C2.certifi_name 
		         		, R.job_type_code 
		         		from tbl_resume R 
		         		 join tbl_member M 
		         		on R.pk_resume_code = M.resume_code 
		         		join tbl_mycertifi C1 
		         		on R.my_certifi_no_1 = C1.my_certifi_no 
		         		join tbl_certifi C2 
		         		on C1.fk_CERTIFI_code = C2.Pk_certifi_code 
                        join tbl_user_resume UR
                        on UR.fk_user_id = M.pk_user_id
		         		where M.PK_USER_ID = 'jeongws'
                                
                                
                                commit;
                                select *
                                from tbl_resume;
                                
                                update tbl_user_resume set fk_resume_code = '25'
                                where pk_resume_no = '5';
                                
                                
                                
                                select *
                                from tbl_user_resume;
                                
                                select *
                                from tbl_resume;
                                
                                
                                
                                select *
                                
                                from tbl_member M JOIN tbl_resume R
                                on M.resume_code = R.pk_resume_code
                                where pk_user_id = 'jeongws'
                                
                                alter table tbl_resume
                                modify columns edu_info_1 null;
                                
                                select *
                                from tbl_school_info;

create table tbl_user_resume
(pk_resume_no  number(30)
,fk_user_id    varchar2(15)
,fk_resume_code varchar2(15)
,constraints pk_tbl_user_resume primary key(pk_resume_no)
,constraints fk_user_id foreign key (fk_user_id) references tbl_member(pk_user_id)
,constraints fk_resume_code foreign key(fk_resume_code) references tbl_resume(pk_resume_code)
);

select *
from tbl_user_resume;

insert into tbl_user_resume(pk_resume_no,fk_user_id,fk_resume_code) values(seq_user_resume_no.nextval,?,?)

commit;

select *
from tbl_member;

select *
from tbl_resume;

insert into tbl_user_resume(pk_resume_no,fk_user_id, fk_resume_code) values(seq_user_resume_no.nextval, 'kangmj', '2');
insert into tbl_user_resume(pk_resume_no,fk_user_id, fk_resume_code) values(seq_user_resume_no.nextval, 'parkyj', '4');
insert into tbl_user_resume(pk_resume_no,fk_user_id, fk_resume_code) values(seq_user_resume_no.nextval, 'kimsh', '11');

commit;

create sequence seq_user_resume_no;

WITH R AS 
( 
select * from tbl_resume 
 ) 
 select R.pk_resume_code 
 , R.self_introduce 
 , R.army 
, R.want_yearsal 
, R.award_act_list 
, R.resume_date 
 , C2.certifi_name 
 , R.job_type_code 
from R 
join tbl_mycertifi C1 
on R.my_certifi_no_1 = C1.my_certifi_no 
join tbl_certifi C2 
on C1.MY_CERTIFI_NO = C2.Pk_certifi_code 
join tbl_member M 
on R.pk_resume_code = M.resume_code 
join tbl_school_info S 
on R.edu_info_2 = S.pk_edu_info 
JOIN tbl_user_resume UR
on UR.fk_user_id = M.pk_user_id
where UR.FK_USER_ID = 'parkyj';



select *
from tbl_certifi;


update tbl_mycertifi set fk_user_id = 'parkyj'
where  my_certifi_no = 15;

commit;


commit;

alter table tbl_mycertifi
add fk_user_id varchar2(15) ;

commit;

commit;



select *
from tbl_resume;

select *
from tbl_resume;



                    
    select *
    from tbl_member_login;
    
    select *
    from tbl_recruit_notice;
    
    select *
    from tbl_join_recruit_notice;
    
    create sequence seq_tbl_join_recruit;
    
    
    insert into tbl_join_recruit_notice(pk_join_no, fk_user_id, fk_recruit_notice_code) values (seq_tbl_join_recruit.nextval , ? , ? )
    
    update tbl_recruit_notice set apply_no = 0;
    commit;
    
    alter table tbl_recruit_notice
    modify apply_no number(3) default 0;
    
    desc tbl_recruit_notice;
    
    delete tbl_join_recruit_notice;
    
    
    create table tbl_join_recruit_notice
    (pk_join_no number(30)
    ,fk_user_id varchar2(15)
    ,fk_recruit_notice_code varchar2(10)
    
    ,constraints pk_tbl_join_recruit_notice primary key(pk_join_no)
    ,constraints fk_tbl_recruit_notice_id foreign key(fk_user_id) references tbl_member(pk_user_id)
    ,constraints fk_tbl_recruit_notice_code foreign key(fk_recruit_notice_code) references tbl_recruit_notice(pk_recruit_notice_code)
    )
    
    desc tbl_recruit_notice;
    --  12	2024 팀네이버 신입 공채(Design)	6,000	2025년 2월 이내 졸업 예정인 학/석사(전공무관)	ui/gui 사용 가능한 사람	지원금/보험, 출퇴근, 리프레시	24/03/05	24/03/18	00	0123456789	01	06-05	0	031-03-03		1	3	4	8
    -- 12	자바 두명 타세요	8,000	자격없음	주 1일 출근	연차 150일	24/03/05	24/03/18	00	0123456789	01	06-01	0	02-23		4	8	4	8
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    create or replace function func_delcomma
    (p_data in varchar2)
    return varchar2
    is
       v_temp varchar2(50);
    begin
       v_temp := p_data;
       loop
          exit when instr(v_temp, ',', 1, 1) = 0;
          v_temp := substr(v_temp, 1, instr(v_temp, ',', 1, 1) - 1) || substr(v_temp, instr(v_temp, ',', 1, 1) + 1);
       end loop;
       
       return v_temp;
    end func_delcomma;