-- �??�� 1
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
    
    
     -- ?���? 2
    create table tbl_buss_type
    (pk_buss_type_code varchar2(10)
    ,buss_type_name Nvarchar2(20) not null
    );
    
    
     -- 경력?��?��?���? 3
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
    
    
    
    -- ?��격증?���? 4
    create table tbl_certifi
    (Pk_certifi_code    varchar2(10)
    ,certifi_name       NVARCHAR2(30)
    
    ,constraint Pk_tbl_certifi_code primary key(Pk_certifi_code)
    );

    
    
    
    
    -- 기업?��?��?���?  5
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
    
    alter table tbl_company_detail
    add company_tel varchar2(13);
    
    alter table tbl_company_detail
    modify company_no varchar2(10);
    
    update tbl_company_detail set company_tel = '02-1357-1357'
    where pk_company_code = '05';
    
    commit;
   
    -- 기업?��?��로그?�� ?��?���? 6
     create table tbl_company_login
    (Pk_Fk_company_code   varchar2(10)     -- 기업코드
    , company_passwd   varchar2(20)   not null  -- 비�?번호
    , company_name      nvarchar2(30)   -- 기업�?
    , Fk_resume_code  varchar2(10)  -- ?��?��?��코드
    
    ,constraint pk_tbl_company_login primary key(Fk_company_code)
    ,constraint FK_TBL_COMPANY_LOGIN foreign key(pk_company_code) references tbl_company_detail(pk_company_code)
    ); 

    
    
    
    -- 기업규모 ?��?���? 7 
    create table tbl_company_size
    (pk_com_size_code varchar2(10)
    ,com_size      Nvarchar2(5) not null
    ,constraint Pk_tbl_company_size primary key(com_size_code)
    );    
    
    
    
    -- 직무 ?��?�� 8
    create table tbl_job_type
    (pk_job_type_code VARCHAR2(10) 
    ,job_type_name NVARCHAR2(20) not null
    ,constraint Pk_job_type_code primary key(job_type_code)
    );
    
    
    
    -- �??�� 9
    create table tbl_location
    (pk_location_code  VARCHAR2(10)
    , location_name NVARCHAR2(20)  not null
    , constraint PK_tbl_location primary key(location_code) 
    );
    
    
    
    -- 구직?�� ?���? ?��?���? 10
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
    
    
    
    
     -- 개인?��?��로그?�� 11
    create table tbl_member_login
    (Pk_fk_user_id             VARCHAR2(15)     not null
    ,user_passwd            VARCHAR2(20)
    ,user_name              NVARCHAR2(10)
    ,Fk_recruit_notice_code VARCHAR2(10)
    
    ,constraint tbl_member_login_user_id primary key(Pk_user_id)
    ,constraint FK_tbl_member_login foreign key(Fk_job_code) references tbl_worktype(worktype_code)
    ,constraint FK_TBL_MEMBER_LOGIN_tbl_member foreign key(pk_fk_user_id) references tbl_member(pk_user_id)
    );
    
    
    
    
    -- ?�� ?��격증 12
    create table tbl_mycertifi
    (Pk_Fk_certifi_code    varchar2(10)
    ,mycertifi_date     DATE
    
    ,constraint Pk_certifi_code foreign key(Fk_certifi_code) references tbl_certifi(Pk_certifi_code)
    );
    
    
    
    
    -- 직위 ?��?���? 13
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
    
    
    
    
    -- 모집?��?�� ?��?���? 15
    create table TBL_RECRUIT_STEP
    (pk_recruit_step_code    VARCHAR2(10)
    ,recruit_step_name    NVARCHAR2(10)   not null
    ,constraint pk_tbl_recruit_step primary key(recruit_step_code)
    );
    
    
    
    
    -- ?��?��?�� 16
    create table tbl_resume
    (pk_resume_code        varchar2(10)        
    ,self_introduce        nvarchar2(50)    not null   
    ,army                  varchar2(10)     not null
    ,certifi_code       varchar2(10)        not null
    ,edu_info           varchar2(10)        not null
    ,job_type_code      varchar2(10)        not null
    ,want_yearsal          varchar2(15)     not null
    ,award_act_list        nvarchar2(200)  
    ,resume_date           date             default sysdate
    ,Fk_career_info_code  NUMBER(10)         
    ,constraint pk_tbl_resume primary key (resume_code)
    ,constraint fk_tbl_resume_certifi_code foreign key (certifi_code) references tbl_certifi(pk_certifi_code)
    ,constraint FK_tbl_resume_edu_info foreign key (edu_info) references tbl_school_info(edu_info)
    ,constraint FK_tbl_resume_job_type_code foreign key (job_type_code) references tbl_job_type(job_type_code)
    );
    


    
     -- 졸업?���? ?��?���? 17
    create table tbl_school
    (pk_school_code      varchar2(5)       
    ,school_name      Nvarchar2(15)     not null   
    ,school_dept_code      varchar2(3)   
    ,constraint PK_tbl_school primary key(school_code)
    ,constraint FK_tbl_school foreign key(school_dept_code) references tbl_school_dept (school_dept_code)
    );

select *
from tbl_member
    
    
    
    -- ?���? ?��?���?  18
    create table tbl_school_dept
    (school_dept_code   varchar2(3)     not null
    ,school_dept_name    varchar2(15)   not null
    ,constraint PK_tbl_school_dept primary key(school_dept_code)
    );
    
    
    
    -- ?��?��?���? 19
     create table tbl_school_info
    (edu_info        varchar2(10)
    ,school_code     varchar2(5)        not null
    ,final_edu          Nvarchar2(10)   not null
    ,sch_hire_date      date            not null
    ,sch_grad_date      date            not null
    ,sch_grade          varchar2(5)     
    ,constraint PK_tbl_school_info primary key(edu_info)
    ,constraint FK_tbl_school_info foreign key(school_code) references tbl_school(school_code)
    );
    
    
    
      -- 근무?��?�� ?��?���? 20
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
    from TBL_RECRUIT_NOTICE;
    
    select userseq, userid,name 
    
    
    
    
    
select PK_user_id, user_passwd
from tbl_member
where  PK_user_id = 'kimsh' and user_passwd = 'Qwer1234$';
    
    select PK_user_id, user_passwd, user_name 
    from tbl_member 
    where  PK_user_id = 'kimsh' and user_passwd = 'Qwer1234$';
    
    
    insert into TBL_RECRUIT_NOTICE
    (pk_recruit_notice_code, recruit_notice_name, YEARSAL, WOODAE, BENEFIT, RECRUIT_START_DAY, RECRUIT_FINISH_DAY, FK_POSITION_CODE, FK_COMPANY_CODE, FK_WORKTYPE_CODE,FK_JOB_TYPE_CODE, FK_RECRUIT_STEP_CODE_1, FK_RECRUIT_STEP_CODE_2, FK_RECRUIT_STEP_CODE_3, FK_RECRUIT_STEP_CODE_4,FK_RECRUIT_STEP_CODE_5, FK_LOCATION_CODE,QUAIL)
    values (seq_fk_recruit_notice_code.nextval , '?��?��?�� ?��좋아?�� 괜찮?��?�� ?��?�� 개발?�� 모십?��?��.','6,000만원','?��개발 경력?��, ?��보처리기?�� ?��격증 보유?��, �??�� ?���? 졸업?��','�?2?��출근, ?�� 1?�� ?��?��?�� ?��?���? �?�?, ?��과급 3,000%, 명절?��?���?, ?��?��?���?, ?��?��로운 ?��차반�?',sysdate , sysdate + 15,'00','03','01','06-01','0','4-1','7','4-2','8','031-03-03','경력 3?�� ?��?��');
