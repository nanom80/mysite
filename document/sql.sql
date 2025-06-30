/*
use web_db;

show databases;
show schemas;
show tables;
select * from information_schema.tables where table_schema = 'hr_db';
select * from information_schema.columns where table_name = 'countries';
show columns from employees;
show full processlist; -- 현재 실행 중인 모든 스레드/세션 목록 (= select * from information_schema.processlist);
kill 51; -- 특정 쿼리 종료 (kill Id)

select user(); -- USER(): 클라이언트가 인증에 사용한 계정.
select current_user(); -- 현재 세션에서 사용되는 계정(권한 기준 계정)

*/

use web_db;

show databases;
show schemas;
show tables;

-- 테이블 만들기
create table users(
    no          int             primary key  auto_increment,
    id          varchar(20),
    password    varchar(20),
    name        varchar(20),
    gender      varchar(10)
);

ALTER TABLE users ADD CONSTRAINT uq_users_id UNIQUE (id);

insert into users values(null,'111','111','유재석','남');
insert into users values(null,'222','222','이효리','여');
insert into users values(null,'333','333','강호동','남');


create table guestbook(
    no          int             primary key  auto_increment,
    id          varchar(20),
    name        varchar(20),
    password    varchar(20),
    content     varchar(20),
    constraint  guestbook_fk    foreign key (id)
    references  users(id)
    
);

alter table guestbook modify content varchar(1000);

drop table guestbook;

select * from guestbook;

insert into guestbook values (null,'111','111','111','111');



create table board(
    no          int             primary key  auto_increment,
    user_no     int,
    title       varchar(500)    not null,
    content     text,
    hit         int             default 0,
    reg_date    datetime        not null,
    constraint board_fk foreign key(user_no)
    references users(no)
);

drop table board;
truncate table board;



insert into board values (20,1,'안녕하세요','반갑습니다',0,now());


select * from board;

select a.no,
    a.title,
    a.content,
    a.hit,
    date_format(a.reg_date,"%Y-%m-%d") regDate,
    b.no userNo,
    b.name userName
from board a, users b
where a.user_no = b.no
;


select * from users;

delete from board where no = 12;

-- ----------------------------------------------------------------






commit;

select * from users;

update users set gender='male' where no=1;

desc users;

delete from users where id is null;


-- 컬럼 추가
alter table book add pubs varchar(50);

-- 컬럼 수정
alter table book modify title varchar(100);
alter table book rename column title to subject;

-- 칼럼 삭제
alter table book drop author;

-- 테이블 명 수정
rename table book to article;

-- 테이블 삭제
drop table article;
drop table book;

-- TRUNCATE 명령: 테이블의 모든 로우를 제거 -> DML delete문과 비교
truncate table users;

SELECT * FROM book;
SELECT * FROM web_db.book;





