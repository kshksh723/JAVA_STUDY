show user;
-- USER이(가) "JDBC_USER"입니다.


select *
from tbl_member
order by userseq desc;


update tbl_member set status = 1;
commit;