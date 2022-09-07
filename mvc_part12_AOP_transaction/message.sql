-- AOP TEST TABLE
CREATE TABLE tbl_user(
	uno INT PRIMARY KEY auto_increment,
    uid VARCHAR(50) NOT NULL UNIQUE,
    upw VARCHAR(50) NOT NULL,
    uname VARCHAR(50) NOT NULL,
    upoint INT NOT NULL default 0
);

CREATE TABLE tbl_message(
	mno INT PRIMARY KEY auto_increment,
    targetid VARCHAR(50) NOT NULL,  -- 수신자
    sender VARCHAR(50) NOT NULL,	-- 발신자
    message TEXT NOT NULL,			-- 메세지
    opendate TIMESTAMP NULL,		-- 메세지 읽은 시간
    senddate TIMESTAMP NOT NULL default now(), -- 발신 시간
    FOREIGN KEY(targetid) REFERENCES tbl_user(uid),
    FOREIGN KEY(sender) REFERENCES tbl_user(uid)
);

INSERT INTO tbl_user(uid,upw,uname) 
VALUES('id001','pw001','IRON MAN');


commit;

SELECT * FROM tbl_message;
SELECT * FROM tbl_user;

CREATE TABLE re_tbl_board(
	bno INT PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    origin INT NOT NULL default 0,
    depth INT NOT NULL default 0,
    seq INT NOT NULL default 0,
	regdate TIMESTAMP NOT NULL default now(),
    updatedate TIMESTAMP NOT NULL default now(),
    viewcnt INT NOT NULL default 0,
    showboard VARCHAR(1) default 'y',
    uno INT NOT NULL,
    CONSTRAINT fk_tbl_uno FOREIGN KEY(uno) REFERENCES tbl_user(uno)
);

-- 첨부파일 목록 tbl
-- 게시물에 등록된 첨부파일을 테이블로 저장

CREATE TABLE tbl_attach(
	fullName VARCHAR(200) NOT NULL,
    bno INT NOT NULL,
    regdate TIMESTAMP default now(),
    CONSTRAINT fk_tbl_attach FOREIGN KEY(bno) REFERENCES re_tbl_board(bno)
);

commit;
SELECT * FROM re_tbl_board;
SELECT * FROM re_tbl_board WHERE origin = 3;

-- NATURAL JOIN : 두 테이블에서 동일한 컬럼의 값이 일치하는 데이터만 검색
SELECT R.*, U.uname AS writer FROM re_tbl_board AS R NATURAL JOIN tbl_user AS U ORDER BY R.origin DESC, R.seq ASC limit 0, 15;

SELECT * FROM tbl_attach;

commit;

SELECT * FROM re_tbl_board ORDER BY bno DESC;

CREATE TABLE free_board(
	bno INT PRIMARY KEY auto_increment,
    acnt_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    origin INT NOT NULL default 0,
    depth INT NOT NULL default 0,
    seq INT NOT NULL default 0,
	regdate TIMESTAMP NOT NULL default now(),
    updatedate TIMESTAMP NOT NULL default now(),
    viewcnt INT NOT NULL default 0,
    showboard VARCHAR(1) default 'y',
    FOREIGN KEY(acnt_id) REFERENCES acnt(acnt_id)
);

SELECT now();
SELECT sysdate();
SELECT curdate();
SELECT curtime();
SELECT date_add(now(), interval -1 day);

SELECT fullName FROM tbl_attach WHERE regdate >= (CURDATE() - interval 1 day);
SELECT DATE_FORMAT(regdate, "%Y-%m-%d") FROM tbl_attach;

SELECT fullName FROM tbl_attach WHERE DATE_FORMAT(regdate, "%Y-%m-%d") = DATE_FORMAT(DATE_SUB(now(), interval 1 day), "%Y-%m-%d");

desc temp_book_tbl;
select * from temp_book_tbl;
















CREATE TABLE ban_ip(
	ip VARCHAR(50) primary key,
    cnt INT DEFAULT 1,
    bandate TIMESTAMP DEFAULT now()    
);

SELECT * FROM ban_ip;
DELETE FROM ban_ip;


-- 도서 테이블
CREATE TABLE book(
	b_code VARCHAR(50) primary key,
    b_title VARCHAR(50) NOT NULL,
    b_auth VARCHAR(30) NOT NULL,
    b_pub VARCHAR(50) NOT NULL,		-- 출판사
    b_pub_date VARCHAR(50) NOT NULL,	-- 출판일
    b_page INT,						-- 책 쪽수
    b_genre INT NOT NULL,			-- 책 분류    
    b_img VARCHAR(100) NOT NULL,	-- 이미지 경로
    b_intro text
);

DROP TABLE book;

CREATE TABLE temp_book_tbl(
   bCode VARCHAR(50) primary key,      -- ISBN
    bName VARCHAR(50) NOT NULL,         -- 책 제목
    bAuthor VARCHAR(30) NOT NULL,      -- 저자
    bPub VARCHAR(50) NOT NULL,         -- 출판사
    bPubdate VARCHAR(50) NOT NULL,      -- 출판일
    bPage INT,							-- 페이지
    bType VARCHAR(50) NOT NULL,         -- 장르
    bImage VARCHAR(100) NOT NULL,      -- 이미지
    bOverView VARCHAR(5000) NOT NULL,   -- 책 소개
    bCount INT default 1
);

INSERT INTO temp_book_tbl VALUES(10,2,3,4,5,6,7,8,9,1);
SELECT * FROM book;
SELECT * FROM temp_book_tbl;
SELECT bCode, bName, bAuthor, bPub, bPubdate, bPage, bType, bImage, bOverView FROM temp_book_tbl;
INSERT INTO book (SELECT bCode, bName, bAuthor, bPub, bPubdate, bPage, bType, bImage, bOverView FROM temp_book_tbl);


select * from re_tbl_board ORDER BY origin, depth;


-- -------------------------------------------------
select * from book JOIN book_single;
select code, img from book limit 0, 1035;
select * from book limit 0, 2000;
select count(*) from book;
select * from book WHERE code = '9788935213344';
desc book;
insert into book_single(book_code) VALUES (9791197693441);
select * from book_single ORDER BY book_code;
select count(*) from book_single;
insert into book_single(book_code) SELECT code AS book_code FROM book;
SELECT * FROM book_buy LIMIT 0, 5000;

SELECT * FROM member;

UPDATE book_buy SET member_id = '123' WHERE date > DATE_ADD(now(), INTERVAL -1 minute);



insert into beatrice.book select * from spring_data.book;
SELECT REPLACE(img, '_', '-') from book;

select * from member;
delete from book_single;

delete from book_single WHERE book_code = '9791197693441' AND num = 5;


INSERT INTO member VALUES('1224','2','4','2','2','2000-11-22', '3', '3', '3', '2000-11-22', 'n', 'profile', 0);
select * from member;
commit;


select * from member_card;

select * from member;
update member set del = 'n';

SELECT * FROM book ORDER BY code;


insert into book_single(book_code) SELECT code AS book_code FROM book;

INSERT INTO book VALUES(1, 1, 1, 1, '2010-11-11', 1, 1, 1);
UPDATE book SET code = '2' WHERE code = '1';
SELECT * FROM book ORDER BY code;
select * from book_single order by book_code;
select * from book_buy;
select * from chk_log;
desc chk_log;
insert into chk_log(member_id, check_time, checkout_time, seat) VALUES ("123", "2022-08-24 00:00:01", "2022-08-24 00:00:05", "14");




CREATE TABLE book_backup(						-- 책의 고유한 정보 : 똑같은 책을 식별하지 않고 한번만 삽입됩니다
		    code VARCHAR(50) primary key,			-- 책 코드 : ISBN
		    title VARCHAR(50) NOT NULL,				-- 제목
		    auth VARCHAR(30) NOT NULL,				-- 작가
		    pub VARCHAR(50) NOT NULL,				-- 출판사
		    pub_date TIMESTAMP NOT NULL,			-- 출판일
		    page INT,								-- 쪽수
		    genre INT NOT NULL,						-- 장르
		    intro text								-- 책 소개
		);

-- -------------------------

CREATE TABLE book(						-- 책의 고유한 정보 : 똑같은 책을 식별하지 않고 한번만 삽입됩니다
		    code VARCHAR(50) primary key,			-- 책 코드 : ISBN
		    title VARCHAR(50) NOT NULL,				-- 제목
		    auth VARCHAR(30) NOT NULL,				-- 작가
		    pub VARCHAR(50) NOT NULL,				-- 출판사
		    pub_date TIMESTAMP NOT NULL,			-- 출판일
		    page INT,								-- 쪽수
		    genre INT NOT NULL,						-- 장르
		    intro text								-- 책 소개
		);
		
CREATE TABLE book_single(		-- 책 하나하나 분리된 책 한권 : 똑같은 같은 책이라도 식별합니다
	book_code VARCHAR(50),
	num INT,											-- 책 번호
	rental VARCHAR(1) default 'n' NOT NULL,				-- 대여 현황
	stat VARCHAR(20) default 'A' NOT NULL,				-- 책의 상태
    ware_member VARCHAR(50) NOT NULL,
    ware_date TIMESTAMP default now(),
	FOREIGN KEY(book_code) REFERENCES book(code) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(ware_member) REFERENCES member(id),
	PRIMARY KEY(num, book_code)
);

CREATE TABLE member(			-- 회원 계정
    id VARCHAR(50) PRIMARY KEY,									-- 아이디
    pw VARCHAR(50) NOT NULL,									-- 비밀번호
    nickname VARCHAR(50) UNIQUE NOT NULL,						-- 닉네임
    name VARCHAR(50) NOT NULL,									-- 이름
    gender VARCHAR(50) NOT NULL,								-- 성별
    birth TIMESTAMP NOT NULL,									-- 생일
	phone VARCHAR(50) NOT NULL,									-- 전화
    addr VARCHAR(50) NOT NULL,									-- 주소
    email VARCHAR(50) NOT NULL UNIQUE,							-- 이메일
    reg_date TIMESTAMP default now() NOT NULL,					-- 등록일
    del VARCHAR(1) default 'n' NOT NULL,						-- 삭제 유무
    img VARCHAR(200) default 'profile/profile.jpg' NOT NULL,		-- 계정 프로필 이미지 : default 차후 파일명 추가
    rights int default 0 NOT NULL								-- 권한(일반 : 0, 스태프 : 1, 관리자 : 2)
);

DELIMITER $$

CREATE TRIGGER auto_create_card AFTER INSERT ON member
FOR EACH ROW
BEGIN    
	INSERT INTO member_card(member_id, premium_grade) VALUES(NEW.id, 'bronze');
END $$

CREATE TRIGGER auto_delete_card AFTER UPDATE ON member
FOR EACH ROW BEGIN
	IF NEW.del = 'y' THEN
		DELETE FROM member_card WHERE member_id = NEW.id;
    END IF;
END $$

DELIMITER ;



CREATE TABLE book_comment(		-- 책 하나하나 분리된 책 한권 : 똑같은 같은 책이라도 식별합니다
	num INT PRIMARY KEY auto_increment,						-- 후기 번호
    book_code VARCHAR(50) NOT NULL,			-- 책 번호
    member_id VARCHAR(50) NOT NULL,			-- 작성자
	comment text NOT NULL,	
	FOREIGN KEY(book_code) REFERENCES book(code) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(member_id) REFERENCES member(id)
);

		CREATE TABLE premium(			-- 회원 프리미엄
		    grade VARCHAR(30) PRIMARY KEY,				-- 회원 등급
		    fee INT NOT NULL,							-- 요금제
		    lend_bookcnt INT NOT NULL,					-- 대여할 수 있는 책의 수
		    lend_period INT NOT NULL,					-- 대여할 수 있는 기간
		    due_sale INT NOT NULL,						-- 연체료 할인
		    lend_price INT NOT NULL						-- 대여료
		);
        
        


		CREATE TABLE member_card(		-- 회원 카드
		    member_id VARCHAR(50) PRIMARY KEY,
		    premium_grade VARCHAR(50) NOT NULL,
		    demerit INT default 0 NOT NULL,					-- 벌점(증가)
            point INT default 0 NOT NULL,								-- 포인트
		    FOREIGN KEY(member_id) REFERENCES member(id),
		    FOREIGN KEY(premium_grade) REFERENCES premium(grade) ON UPDATE CASCADE ON DELETE RESTRICT
		);


		CREATE TABLE black_list(		-- 블랙리스트
		    member_id VARCHAR(50) PRIMARY KEY,
		    cause text NOT NULL,				-- 블랙 사유
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);


DELIMITER $$

CREATE TRIGGER auto_black_list AFTER UPDATE ON member_card
FOR EACH ROW BEGIN
    IF NEW.demerit >= 10 THEN
       INSERT INTO black_list VALUES(NEW.member_id, '벌점 초과');   
    END IF;
END $$

DELIMITER ;


		CREATE TABLE chk_real(			-- 체크인 실시간
		    num INT AUTO_INCREMENT PRIMARY KEY,		-- 체크인 번호
		    member_id VARCHAR(50) NOT NULL,
		    check_time TIMESTAMP NOT NULL,			-- 체크인 한 시간
		    seat VARCHAR(50) NOT NULL UNIQUE,		-- 지정 좌석
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);

		CREATE TABLE chk_log(			-- 체크인 이력
		    num INT AUTO_INCREMENT PRIMARY KEY,		-- 체크인 번호
		    member_id VARCHAR(50) NOT NULL,
		    check_time TIMESTAMP NOT NULL,			-- 체크인 한 시간
		    checkout_time TIMESTAMP NOT NULL,		-- 체크아웃 시간
		    seat VARCHAR(50) NOT NULL,				-- 지정 좌석
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);

		CREATE TABLE rental(			-- 대여
		    book_code VARCHAR(50),
		    book_num INT,
		    member_id VARCHAR(50),
		    date TIMESTAMP NOT NULL,	-- 대여한 날짜
		    return_date TIMESTAMP NOT NULL,		-- 반납할 날짜
		    FOREIGN KEY(book_code, book_num) REFERENCES book_single(book_code, num) ON UPDATE CASCADE ON DELETE CASCADE,
		    FOREIGN KEY(member_id) REFERENCES member(id),
		    PRIMARY KEY(book_code, book_num)
		);
        
        
        
        CREATE TABLE rental_reserve(	-- 대여 예약
		    book_code VARCHAR(50),
		    book_num INT,
		    member_id VARCHAR(50),
		    date TIMESTAMP NOT NULL,	-- 대여 예약 날짜
		    FOREIGN KEY(book_code, book_num) REFERENCES book_single(book_code, num) ON UPDATE CASCADE ON DELETE CASCADE,
		    FOREIGN KEY(member_id) REFERENCES member(id),
		    PRIMARY KEY(book_code, book_num)
		);
        
        CREATE TABLE rental_log(			-- 대여 이력
		    book_code VARCHAR(50),
		    book_num INT,
		    member_id VARCHAR(50),
		    date TIMESTAMP NOT NULL,	-- 대여한 날짜
		    return_date TIMESTAMP NOT NULL,		-- 반납한 날짜
		    FOREIGN KEY(book_code) REFERENCES book(code) ON UPDATE CASCADE,
		    FOREIGN KEY(member_id) REFERENCES member(id),
		    PRIMARY KEY(book_code, book_num)
		);        

		CREATE TABLE book_board(			-- 도서 정보 게시판
		    num INT AUTO_INCREMENT PRIMARY KEY,		-- 게시글 번호
		    book_code VARCHAR(50) NOT NULL,
		    title VARCHAR(50) NOT NULL,				-- 제목
		    content VARCHAR(50) NOT NULL,			-- 내용
		    viewcnt INT default 0 NOT NULL,			-- 조회수
		    FOREIGN KEY(book_code) REFERENCES book(code) ON UPDATE CASCADE
		);

		CREATE TABLE book_buy(			-- 책 입고
			book_code VARCHAR(50),
            num INT NOT NULL,			-- 책 번호		    
		    date TIMESTAMP NOT NULL default now(),	-- 입고일
            member_id VARCHAR(50),		-- 담당 직원
		    FOREIGN KEY(book_code) REFERENCES book(code) ON UPDATE CASCADE,
            FOREIGN KEY(member_id) REFERENCES member(id),
            PRIMARY KEY(book_code, num)
		);
        

		CREATE TABLE book_dump(			-- 책 출고
		    book_code VARCHAR(50),
		    book_num INT,
		    date TIMESTAMP default now() NOT NULL,	-- 출고일
			member_id VARCHAR(50) NOT NULL,		-- 담당 직원
            cause VARCHAR(50) NOT NULL,
		    PRIMARY KEY(book_code, book_num),
		    FOREIGN KEY(book_code) REFERENCES book(code) ON UPDATE CASCADE,
            FOREIGN KEY(member_id) REFERENCES member(id)
		);
        



DELIMITER $$

CREATE TRIGGER auto_control BEFORE INSERT ON book_single
FOR EACH ROW BEGIN
    SET NEW.num = (
       SELECT IFNULL(MAX(num), 0) + 1
       FROM book_single
       WHERE book_code  = NEW.book_code
    );
END $$

DELIMITER ;

-- 이하는 게시판 (책 정보 게시판은 위쪽에 있음)

		CREATE TABLE free_board(		-- 자유 게시판
			bno INT PRIMARY KEY auto_increment,			-- 글 번호
		    member_id VARCHAR(50) NOT NULL,
		    tag VARCHAR(50) NOT NULL,					-- 제목 말머리(태그)
		    title VARCHAR(200) NOT NULL,				-- 제목
		    content TEXT NOT NULL,						-- 내용
			regdate TIMESTAMP NOT NULL default now(),	-- 글을 등록한 시간
		    viewcnt INT NOT NULL default 0,				-- 조회수
		    showboard VARCHAR(1) default 'y',			-- 게시글 노출 유무(삭제시 n으로 변경)
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);

		CREATE TABLE free_comment(						-- 자유게시판 댓글
			cno INT PRIMARY KEY auto_increment,			-- 댓글 번호
		    free_bno INT NOT NULL,
		    member_id VARCHAR(50) NOT NULL,
		    comment TEXT NOT NULL,						-- 댓글 내용
			origin INT NOT NULL default 0,				-- 원본 댓글
			regdate TIMESTAMP NOT NULL default now(),	-- 댓글 등록한 시간
		    showboard VARCHAR(1) default 'y',			-- 댓글 노출 유무(삭제시 n으로 변경)
		    FOREIGN KEY(free_bno) REFERENCES free_board(bno) ON UPDATE CASCADE ON DELETE CASCADE,
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);

		CREATE TABLE suggest_board(						-- 건의사항
			bno INT PRIMARY KEY auto_increment,	-- 게시글 번호
		    member_id VARCHAR(50) NOT NULL,
		    title VARCHAR(200) NOT NULL,		-- 제목
		    content TEXT NOT NULL,				-- 내용
		    origin INT NOT NULL default 0,		-- 원본
		    depth INT NOT NULL default 0,		-- 깊이 : 답변글에 대해 답변을 달지 못하도록 1로 고정할 것(서비스 로직)
			regdate TIMESTAMP NOT NULL default now(),	-- 건의/답변글 등록 시간
		    viewcnt INT NOT NULL default 0,		-- 조회수
            recommend INT NOT NULL default 0,
		    showboard VARCHAR(1) default 'y',	-- 글 노출 유무(삭제시 n으로 변경)
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);


		CREATE TABLE notice_board(						-- 공지사항
			bno INT PRIMARY KEY auto_increment,			-- 글 번호
		    member_id VARCHAR(50) NOT NULL,
		    title VARCHAR(200) NOT NULL,				-- 제목
		    content TEXT NOT NULL,						-- 내용
			regdate TIMESTAMP NOT NULL default now(),	-- 글 등록 시간
		    viewcnt INT NOT NULL default 0,				-- 조회수
		    showboard VARCHAR(1) default 'y',			-- 글 노출 유무(삭제시 n으로 변경)
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);


		CREATE TABLE inquiry(								-- 문의
			bno INT PRIMARY KEY auto_increment,				-- 문의 글 번호
		    member_id VARCHAR(50) NOT NULL,
		    title VARCHAR(200) NOT NULL,					-- 제목
		    content TEXT NOT NULL,							-- 내용
			regdate TIMESTAMP NOT NULL default now(),		-- 등록시간
		    showboard VARCHAR(1) default 'y',				-- 글 노출 유무
		    FOREIGN KEY(member_id) REFERENCES member(id)
		);





-- ---------------------------------------


drop trigger auto_buy;
drop trigger auto_control;
drop trigger auto_black_list;
drop trigger auto_delete_card;
drop trigger auto_create_card;

drop table book_dump;
drop table book_buy;
drop table book_board;
drop table rental;
drop table rental_reserve;
drop table rental_log;
drop table chk_log;
drop table chk_real;
drop table black_list;
drop table member_card;
drop table premium;

drop table inquiry;
drop table notice_board;
drop table suggest_board;
drop table free_comment;
drop table free_board;
drop table book_comment;
drop table member;
 
drop table book_single;
drop table book;






-- --------------------------------------
INSERT INTO suggest_board(member_id, title, content) VALUES("123", "제목", "내용");
select * from suggest_board;
select * from member;

select * from mvc_member;
desc mvc_member;
INSERT INTO mvc_member(id, pass, name, age, gender) VALUES('admin', 'admin', 'admin', 1000, 'male');


DELIMITER $$
CREATE TRIGGER auto_buy BEFORE INSERT ON book_single
FOR EACH ROW
FOLLOWS auto_control
BEGIN
    INSERT INTO book_buy(book_code, num) VALUES (NEW.book_code, NEW.num);
END $$

DELIMITER ;

CREATE TABLE mul_test(
	free_bno INT,
	cno INT auto_increment,    
    PRIMARY KEY(cno, free_bno),
    FOREIGN KEY(free_bno) REFERENCES free_board(free_bno)
);

select * from acnt;
select * from free_board;
select * from mul_test;

INSERT INTO acnt(acnt_id, pw, name, birth, phone, addr, email) VALUES('123', '123', '지훈', '20220101', '01012345678', '부산', 'jh');
INSERT INTO free_board(acnt_id, tag, title, content) VALUES ('123', '태그', '제목', '내용');
INSERT INTO mul_test(free_bno) VALUES(2);

drop table mul_test;

desc re_tbl_board;
desc tbl_user;




CREATE TABLE book_test(						-- 책의 고유한 정보 : 똑같은 책을 식별하지 않고 한번만 삽입됩니다
		    code VARCHAR(50) primary key,			-- 책 코드 : ISBN
		    title VARCHAR(50),				-- 제목
		    auth VARCHAR(30),				-- 작가
		    pub VARCHAR(50),				-- 출판사
		    pub_date VARCHAR(50),			-- 출판일
		    page INT,								-- 쪽수
		    genre INT,						-- 장르
		    img VARCHAR(100),				-- 책 이미지
		    intro text								-- 책 소개
);


select * from book_test LIMIT 0, 3500;
select count(*) from book_test;
-- 503(2105 : 고전) 1096(170 : 경제) 1616(517 : 예술,대중) 2143(74 : 역사)  2664(987 : 과학)  ? (656 : 인문학)
commit;

-- 각 테이블 별로 INSERT에 문제가 없는지 확인

SELECT * FROM book ORDER BY code asc;
SELECT count(*) FROM book;

INSERT INTO book_single(book_code) SELECT code FROM book;
SELECT * FROM book_single ORDER BY book_code, num limit 0, 4000;
select count(*) from book_single;
desc book_single;

SELECT * FROM book NATURAL JOIN book_single WHERE code = book_code limit 0, 2000;
SELECT count(*) FROM book NATURAL JOIN book_single WHERE code = book_code;
SELECT count(*) FROM book INNER JOIN book_single;

commit;

SELECT * FROM book WHERE code = "9772950991004";

INSERT INTO book VALUES ('book',1,1,1,1,1,1,1,1);
INSERT INTO book VALUES ('book',1,1,1,'12-12-12',1,1,1);
delete from book WHERE code = 'book';
select * from book;
DELETE FROM book;



SELECT * FROM book_single;
INSERT INTO book_single VALUES ('book','book001',2,2);	-- book 에 존재하는 책만 가능
DELETE FROM book_single;

INSERT INTO member(id, pw, name, birth, phone, addr, email) VALUES ('member',1,1,1234-12-12,1,1,1);
DELETE FROM member;

INSERT INTO premium VALUES (1,1,1,1,1,1);
DELETE FROM premium;

INSERT INTO member_card VALUES ('member',1,0);
DELETE FROM member_card;

INSERT INTO black_list VALUES ('member',1);
DELETE FROM black_list;

INSERT INTO ck_real VALUES (1, 'member', 1234-12-12, 1);
DELETE FROM ck_real;

INSERT INTO ck_log VALUES (1,'member',1234-12-12,1234-12-15,1);
DELETE FROM ck_log;

INSERT INTO rental VALUES ('book','book001','member',1234-12-12,1234-12-15);
DELETE FROM rental;

INSERT INTO rental_reserve VALUES ('book','book001','member',1234-12-12);
DELETE FROM rental_reserve;

INSERT INTO rental_log VALUES ('book','book001','member',1234-12-12,1234-12-15);
DELETE FROM rental_log;

INSERT INTO book_board VALUES (1,'book',1,1,1,1,1);
DELETE FROM book_board;

INSERT INTO book_buy VALUES ('book',1,1234-12-12);
DELETE FROM book_buy;

INSERT INTO book_dump VALUES ('book','book001',1234-12-12);
DELETE FROM book_dump;

INSERT INTO free_board VALUES (123,'member',1,1,1,1234-12-12,1,'y');
DELETE FROM free_board;

INSERT INTO free_comment VALUES (1, 123,'member',1,1,1,1,1234-12-12,'y');
DELETE FROM free_comment;

INSERT INTO suggest_board VALUES (1,'member',1,1,1,1,1,1234-12-12,1,1,'y');
DELETE FROM suggest_board;

INSERT INTO notice_board VALUES (1,'member',1,1,1234-12-12,1,'y');
DELETE FROM notice_board;

INSERT INTO inquiry VALUES (123,'member',1,1,1234-12-12,'y');
DELETE FROM inquiry;






-- ------------------

DROP TABLE IF EXISTS test_board;

-- TEST BOARD
CREATE TABLE IF NOT EXISTS test_board(
	bno int PRIMARY KEY auto_increment,
	title VARCHAR(50),
	content TEXT,
	writer VARCHAR(50),
	regdate TIMESTAMP default now(),
	viewcnt int default 0
);
INSERT INTO test_board(title, content, writer) VALUES ('제목', '내용', '작성자');
INSERT INTO test_board(title, content, writer)
SELECT title,content,writer FROM test_board;

SELECT * FROM test_board;
commit;

DROP TABLE IF EXISTS test_sboard;
-- SEARCH BOARD
CREATE TABLE IF NOT EXISTS test_sboard(
	bno int PRIMARY KEY auto_increment,
	title VARCHAR(200) NOT NULL,
	content text NOT NULL,
	writer VARCHAR(50) NOT NULL,
	regdate TIMESTAMP default now(),
	updatedate TIMESTAMP default now(),
	viewcnt int default 0
);
INSERT INTO test_sboard(title, content, writer) VALUES ("제목", "내용", "작성자");
INSERT INTO test_sboard(title,content,writer)
select title,content,writer FROM test_sboard;

select * from test_sboard ORDER BY bno DESC;




CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,
    u_id VARCHAR(50) NOT NULL UNIQUE,
    u_pw VARCHAR(200) NOT NULL,
    u_profile VARCHAR(200) NULL,
    u_phone VARCHAR(20) NOT NULL,
    u_name VARCHAR(20) NOT NULL,
    u_birth VARCHAR(20) NOT NULL,
    u_post VARCHAR(50),
    u_addr VARCHAR(100),
    u_addr_detail VARCHAR(100),
    u_point INT default 0,
    u_info char(1) default 'y',
    u_date TIMESTAMP NOT NULL default now(),
    u_visit TIMESTAMP NOT NULL default now(),
	u_withdraw char(1) default 'n'
);


CREATE TABLE validation_member_auth(
	u_id VARCHAR(50) NOT NULL,
    u_auth VARCHAR(50) NOT NULL,
    constraint fk_member_auth FOREIGN KEY(u_id) references validation_member(u_id)    
);