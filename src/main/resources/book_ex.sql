create sequence seq_board;
drop sequence seq_board;

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



-------------------------댓글처리시작
create sequence seq_board;
drop sequence seq_board;
truncate table tbl_board;
select * from tbl_board order by bno desc;
-- 보드용 더미테이블 11개
insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '댓글용제목', '댓글용내용', 'kjs');

--댓글용 테이블
create table tbl_reply (
	rno number(10,0),  -- 댓글 번호
	bno number(10,0),  -- fk(게시판번호)
 	reply varchar2(1000) not null, -- 댓글
 	replyer varchar2(50) not null, -- 댓글 작성자
	replyDate date default sysdate,
	updateDate date default sysdate );
	
create sequence seq_reply ; -- 댓글용 자동번호객체 추가	

alter table tbl_reply add constraint pk_reply primary key (rno); 
-- pk를 rno로 지정(롤이름 : pk_reply)

alter table tbl_reply add constraint fk_reply_board foreign key (bno) references tbl_board (bno); 
-- tbl_reply의 bno(자)와 tbl_board의 bno(부)를 연결 (부모가 있어야 자식이 있다) 

select * from tbl_reply order by bno desc;

--댓글용 더미데이터
delete from tbl_reply; --더미데이터 삭제
truncate table tbl_reply;
drop sequence seq_reply; --자동번호 객체 삭제


insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 11, '댓글11', 'kjs'); 
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 10, '댓글10', 'kjs'); 
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 9, '댓글9', 'kjs'); 
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 8, '댓글8', 'kjs'); 
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 7, '댓글7', 'kjs'); 
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 6, '댓글6', 'kjs'); 

select rno, bno, reply, replyer, replyDate, updateDate from tbl_reply where bno = 10 order by rno asc;