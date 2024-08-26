create sequence seq_board;

create table tbl_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
); -- tbl_board 테이블 생성(번호, 제목, 내용, 작성자, 작성일, 수정일)

alter table tbl_board add constraint pk_board primary key(bno);

-- drop table tbl_board;
truncate table tbl_board;
select * from tbl_board;

insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');
insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '테스트 제목2', '테스트 내용2', 'user01');
insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '테스트 제목3', '테스트 내용3', 'user01');
insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '테스트 제목4', '테스트 내용4', 'user00');
insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '테스트 제목5', '테스트 내용5', 'user01');
insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '테스트 제목6', '테스트 내용6', 'user00');


--페이징 처리 위한 테스트 데이터 삽입(재귀함수)
insert into tbl_board (bno, title, content, writer) (select seq_board.nextval, title, content, writer from tbl_board);


select /*+INDEX_DESC(tbl_board pk_board)*/* from tbl_board where bno>0;
-- /*+INDEX_DESC(tbl_board pk_board)*/ 오라클 힌트 : /*+ hintname(parameter)*/ order by bno desc

select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, bno, title, content, writer, regdate, updatedate from TBL_BOARD where bno >0 ;
--member 테이블 생성
create table member_tb(
 ninckname varchar2(20) primary key,
 pw varchar2(20) not null,
 email varchar2(100) not null,
 phone varchar2(20) not null,
 birth Date not null,
 regdate Date default sysdate
);

--drop table member_tb;
select * from member_tb;
insert into member_tb (ninckname, pw, email, phone, birth) values ('kkk', 'kkk', 'kkk@test.com', '010-111-1111', to_date('2000/01/01','YYYY/MM/DD'));
insert into member_tb (ninckname, pw, email, phone, birth) values ('aaa', 'aaa','aaa@test.com', '010-111-1111', to_date('1900/01/01','YYYY/MM/DD'));
insert into member_tb (ninckname, pw, email, phone, birth) values ('bbb', 'bbb', 'bbb@test.com', '010-111-1111', to_date('2020/01/01','YYYY/MM/DD'));