use spring_data;

DROP TABLE notice_board;

CREATE TABLE notice_board (
  notice_num int NOT NULL AUTO_INCREMENT,
  notice_category varchar(20) DEFAULT NULL,
  notice_author varchar(50) DEFAULT NULL,
  notice_title varchar(50) DEFAULT NULL,
  notice_content longtext,
  notice_cnt int default 0,
  PRIMARY KEY (`notice_num`)
);

commit;

show tables;

INSERT INTO notice_board(notice_title) SELECT notice_title FROM notice_board;

SELECT * FROM notice_board;


CREATE TABLE notice_board (
  notice_num int NOT NULL AUTO_INCREMENT,
  notice_category varchar(20) DEFAULT NULL,
  notice_author varchar(50) DEFAULT NULL,
  notice_title varchar(50) DEFAULT NULL,
  notice_content longtext,
  notice_cnt int default 0,
  PRIMARY KEY (`notice_num`)
);