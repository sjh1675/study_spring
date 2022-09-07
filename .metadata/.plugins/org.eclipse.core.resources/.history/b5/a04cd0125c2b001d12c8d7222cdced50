package com.koreate.betty.domain.book.provider;

import static com.koreate.betty.domain.model.TableConst.BOOK_COMMENT_TBL;
import static com.koreate.betty.domain.model.TableConst.BOOK_SINGLE_TBL;
import static com.koreate.betty.domain.model.TableConst.BOOK_TBL;
import static com.koreate.betty.domain.model.TableConst.MEMBER_TBL;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.koreate.betty.domain.book.dto.form.BookSearchForm;
import com.koreate.betty.domain.book.vo.Book;
import com.koreate.betty.domain.book.vo.BookComment;
import com.koreate.betty.global.util.BookCriteria;
import com.koreate.betty.global.util.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookCommentProvider {

	// 도서 상세 정보 페이지의 댓글 목록 (이미지, 아이디, 시간, 내용)
	public String findCommentByCode(@Param("code") String code, @Param("cri") Criteria cri) {
		return new SQL().SELECT("member.img, book_comment.nickname, book_comment.regdate, book_comment.comment")
				.FROM(BOOK_COMMENT_TBL).JOIN(MEMBER_TBL).WHERE("book_comment.member_id = member.id")
				.WHERE("book_code = #{code}").OFFSET("#{cri.startRow}").LIMIT("#{cri.perPageNum}").toString();
	}

	// 도서 상세 정보 페이지의 댓글 총계
	public String findCommentByCodeCount(String code) {
		return new SQL().SELECT("count(*)").FROM(BOOK_COMMENT_TBL).WHERE("book_code = #{code}").toString();
	}


	// 도서 상세 페이지 댓글 등록
	public String insertComment(BookComment bookComment) {
		return new SQL().INSERT_INTO(BOOK_COMMENT_TBL).INTO_COLUMNS("book_code", "member_id", "nickname", "comment")
				.INTO_VALUES("#{bookCode}", "#{memberId}", "#{nickname}", "#{comment}").toString();
	}

	// 도서 상세 페이지 댓글 삭제
	public String deleteComment(Integer num) {
		return new SQL().DELETE_FROM(BOOK_COMMENT_TBL).WHERE("num = #{num}").toString();
	}

}