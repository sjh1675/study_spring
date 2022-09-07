CREATE TABLE IF NOT EXISTS tbl_member(
	userid VARCHAR(50),
    userpw VARCHAR(45),
    username VARCHAR(45),
    regdate TIMESTAMP DEFAULT now(),
    updatedate TIMESTAMP DEFAULT now()
);

DESC tbl_member;
commit;
SELECT * FROM tbl_member;

DELETE FROM tbl_member;

CREATE TABLE tbl_board(
	bno int PRIMARY KEY auto_increment,
	title VARCHAR(200) NOT NULL,
    content TEXT NULL,
    writer VARCHAR(20) NOT NULL,
    regdate TIMESTAMP NOT NULL DEFAULT now(),
    viewcnt INT default 0
);

DESC tbl_board;

DROP TABLE tbl_board;

commit;

SELECT * FROM tbl_board WHERE title LIKE '%게시물%' ORDER BY bno DESC limit 0, 30;

INSERT INTO tbl_board(title, content, writer) SELECT title, content, writer FROM tbl_board;

SELECT * FROM tbl_board;


CREATE TABLE tbl_comment(
	cno INT PRIMARY KEY auto_increment,
    bno INT NOT NULL default 0,
    commentText TEXT NOT NULL,
    commentAuth VARCHAR(50) NOT NULL,
    regdate TIMESTAMP NOT NULL default now(),
    updatedate TIMESTAMP NOT NULL default now(),
	CONSTRAINT fx_tbl_bno FOREIGN KEY(bno) REFERENCES tbl_board(bno),
    INDEX(bno)
);

SELECT * FROM tbl_board;
SELECT * FROM tbl_comment;
commit;
DELETE FROM tbl_board WHERE bno = 1;

SELECT * FROM information_schema.table_constraints WHERE table_name = 'tbl_comment';

ALTER TABLE tbl_comment DROP FOREIGN KEY fx_tbl_bno;

ALTER TABLE tbl_comment ADD CONSTRAINT fk_tbl_bno FOREIGN KEY(bno) REFERENCES tbl_board(bno) ON DELETE CASCADE;