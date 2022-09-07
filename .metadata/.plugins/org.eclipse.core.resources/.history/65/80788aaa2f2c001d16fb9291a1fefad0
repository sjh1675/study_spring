package com.koreate.betty.domain.book.provider;

import static com.koreate.betty.domain.model.TableConst.BOOK_SINGLE_TBL;
import static com.koreate.betty.domain.model.TableConst.BOOK_TBL;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.koreate.betty.domain.book.dto.form.BookSearchForm;
import com.koreate.betty.domain.book.dto.form.NewBookForm;
import com.koreate.betty.domain.book.vo.Book;
import com.koreate.betty.global.util.BookCriteria;
import com.koreate.betty.global.util.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookProvider {

	// 전체 책 목록
	public String findAll(BookCriteria cri) {
		SQL sql = new SQL();
		sql.SELECT("*").FROM(BOOK_TBL);
		bookListResolver(sql, cri);

		if (cri != null) {
			sql.OFFSET("#{startRow}");
			sql.LIMIT("#{perPageNum}");
		}

		return sql.toString();
	}

	// 전체 책 목록 총계
	public String findAllCount(BookCriteria cri) {
		SQL sql = new SQL();
		sql.SELECT("count(*)").FROM(BOOK_TBL);
		bookListResolver(sql, cri);
		return sql.toString();
	}

	
	
	// 도서 상세 정보
	public String bookDetail(String code) {
		return new SQL().SELECT("*").FROM(BOOK_TBL).WHERE("code = #{code}").toString();
	}

	// 신규 도서 등록
	public String insert(NewBookForm form) {
		return new SQL().INSERT_INTO(BOOK_TBL)
				.INTO_VALUES("#{code}, #{title}, #{auth}, #{pub}, #{pubDate}, #{page}, #{genre}, #{intro}").toString();
	}

	// 도서 정보 변경 (변경할 책 코드, 들어갈 정보) // 테스트 완료
	// 코드가 반드시 일치하는 한권이어야 하고, 모든 필수 정보가 들어있어야 합니다
	public String update(@Param("targetCode") String targetCode, @Param("book") Book book) {
		return new SQL().UPDATE(BOOK_TBL).SET("code = #{book.code}").SET("title = #{book.title}")
				.SET("auth = #{book.auth}").SET("pub = #{book.pub}").SET("pub_date = #{book.pubDate}")
				.SET("page = #{book.page}").SET("genre = #{book.genre}").SET("intro = #{book.intro}")
				.WHERE("code = #{targetCode}").toString();
	}
	//select * from book ORDER BY pub_date DESC limit 3;
	public String findRecentBooks() {
		return new SQL().SELECT("*").FROM(BOOK_TBL)
				.ORDER_BY("pub_date DESC")
				.LIMIT("3")
				.toString();
	}

	/*
	 * jBooks : book + bookSingle 조인 이하의 코드는 Join을 사용하여 책의 정보와 책의 권수를 함께 다룸
	 */

	// 도서 전체 검색 // 테스트 완료
	public String jFindAll(Criteria cri) {
		return new SQL().SELECT("*").FROM(BOOK_TBL).JOIN(BOOK_SINGLE_TBL).WHERE("code = book_code").ORDER_BY("code")
				.ORDER_BY("num").OFFSET("#{startRow}").LIMIT("#{perPageNum}").toString();
	}

	// 도서 전체 검색 갯수
	public String jFindAllCount() {
		return new SQL().SELECT("count(*)").FROM(BOOK_TBL).JOIN(BOOK_SINGLE_TBL).WHERE("code = book_code")
				.ORDER_BY("code").toString();
	}

	// code로 찾은 한 도서의 목록 (isbn은 동일한 책이 여럿이 나옴) -> 특정 책을 클릭 했을 때, 책의 상태를 확인하기 위함
	public String jFindByCode(String code) {
		return new SQL().SELECT("*").FROM(BOOK_TBL).JOIN(BOOK_SINGLE_TBL).WHERE("code = book_code").ORDER_BY("code")
				.WHERE("code = #{code}").ORDER_BY("num").toString();
	}

	// 도서 현황
	public String jSearch(BookSearchForm form, Criteria cri) {
		SQL sql = new SQL().SELECT("*").FROM(BOOK_TBL);
		jBookSearchResolver(sql, form);
		sql.ORDER_BY("code");
		sql.OFFSET(cri.getStartRow()).LIMIT(cri.getPerPageNum());
		return sql.toString();
	}

	// 도서 현황 count
	public String jSearchCount(BookSearchForm form) {
		SQL sql = new SQL().SELECT("count(*)").FROM(BOOK_TBL);
		jBookSearchResolver(sql, form);
		return sql.toString();
	}

	// 대여 현황
	// select book.code, book.title, book_single.rental, rental.member_id AS
	// 'rental_id', rental_reserve.member_id AS 'reserve_id',
	// rental.date AS 'rental_date', rental.return_date, rental_reserve.date AS
	// 'reserve_date' from book
	// LEFT JOIN book_single ON book.code = book_single.book_code
	// LEFT JOIN rental ON book_single.num = rental.book_num AND book.code =
	// rental.book_code
	// LEFT JOIN rental_reserve ON book_single.num = rental_reserve.book_num AND
	// book.code = rental_reserve.book_code;

	public String jRentalSearch(BookSearchForm form, Criteria cri) {

		SQL sql = new SQL().SELECT(
				"book.code, book.title, book_single.rental, rental.member_id AS 'rental_id', rental_reserve.member_id AS 'reserve_id',"
						+ " rental.date AS 'rental_date', rental.return_date, rental_reserve.date AS 'reserve_date'")
				.FROM(BOOK_TBL);
		
		jRentalSearchResolver(sql, form);

		sql.ORDER_BY("book.code");
		sql.OFFSET(cri.getStartRow()).LIMIT(cri.getPerPageNum());

		return sql.toString();
	}

	public String jRentalSearchCount(BookSearchForm form) {
		
		SQL sql = new SQL().SELECT("count(*)").FROM(BOOK_TBL);
		
		jRentalSearchResolver(sql, form);

		return sql.toString();
	}

	// 크롤링
	public String insertByCrawler(Book book) {
		return "INSERT IGNORE INTO book VALUES(#{code}, #{title}, #{auth}, #{pub}, #{pubDate}, #{page}, #{genre}, #{intro})";
	}

	
	private void bookListResolver(SQL sql, BookCriteria cri) {
		if (cri.getSearchText() != null) {
			switch(cri.getSearchOption()) {
			case "title":
				sql.WHERE("title LIKE CONCAT('%',#{searchText},'%')");
				break;
			case "auth":
				sql.WHERE("auth LIKE CONCAT('%',#{searchText},'%')");
				break;
			case "intro":
				sql.WHERE("intro LIKE CONCAT('%',#{searchText},'%')");
				break;				
			}
		}

		if (cri.getGenre() != null && cri.getGenre() != "") {
			sql.WHERE("genre = #{genre}");
		}

		if (cri.getAuth() != null && cri.getAuth() != "") {
			sql.WHERE("auth LIKE CONCAT('%',#{auth},'%')");
		}
		
		if (cri.getPub() != null && cri.getPub() != "") {
			sql.WHERE("pub LIKE CONCAT('%',#{pub},'%')");
		}			
		
		if (cri.getPubDate() != null && !cri.getPubDate().trim().equals("")) { 
			Timestamp pubDate = Timestamp.valueOf(cri.getPubDate() + " 00:00:00");
			String date = new SimpleDateFormat("yyyy-MM-dd").format(pubDate);
			sql.WHERE("pub_date >= '" + date + "'");
		}
		
	}
	
	
	private void jBookSearchResolver(SQL sql, BookSearchForm form) {
		String search = form.getSearchText();
		String searchOption = form.getSearchOption();
		String pubDateOption = form.getPubDateOption();


		if (searchOption != null && !searchOption.trim().equals("")) {
			switch (searchOption) {
			case "title":
				sql.WHERE("title LIKE CONCAT('%', '" + search + "', '%')");
				break;
			case "content":
				sql.WHERE("content LIKE CONCAT('%', '" + search + "', '%')");
				break;
			case "auth":
				sql.WHERE("auth LIKE CONCAT('%', '" + search + "', '%')");
				break;
			case "pub":
				sql.WHERE("pub LIKE CONCAT('%', '" + search + "', '%')");
				break;
			default:
				break;
			}
		}

		if (form.getPubDate() != null && !form.getPubDate().trim().equals("")) { // 테스트 필요
			Timestamp pubDate = Timestamp.valueOf(form.getPubDate() + " 00:00:00");
			String date = new SimpleDateFormat("yyyy-MM-dd").format(pubDate);
			if (pubDateOption.equals("after")) {
				sql.WHERE("pub_date >= '" + date + "'");
			} else {
				sql.WHERE("pub_date <= '" + date + "'");
			}
		}

		String genre = form.getGenre();
		if (genre != null && !genre.trim().equals("")) {
			sql.WHERE("genre = " + genre);
		}
	}
	
	
	private void jRentalSearchResolver(SQL sql, BookSearchForm form) {
		String search = form.getSearchText();
		String searchOption = form.getSearchOption();
		String rentOption = form.getRentOption();
		String pubDateOption = form.getPubDateOption();

		sql.LEFT_OUTER_JOIN("book_single ON book.code = book_single.book_code")
				.LEFT_OUTER_JOIN("rental ON book_single.num = rental.book_num AND book.code = rental.book_code")
				.LEFT_OUTER_JOIN(
						"rental_reserve ON book_single.num = rental_reserve.book_num AND book.code = rental_reserve.book_code");


		
		if (searchOption != null && !searchOption.trim().equals("")) {
			switch (searchOption) {
			case "title":
				sql.WHERE("title LIKE CONCAT('%', '" + search + "', '%')");
				break;
			case "content":
				sql.WHERE("content LIKE CONCAT('%', '" + search + "', '%')");
				break;
			case "auth":
				sql.WHERE("auth LIKE CONCAT('%', '" + search + "', '%')");
				break;
			case "pub":
				sql.WHERE("pub LIKE CONCAT('%', '" + search + "', '%')");
				break;
			default:
				break;
			}
		}

		if (rentOption != null && !rentOption.trim().equals("")) { // null일 경우 전체 검색이고, 옵션값을 rent 또는 reserve로 해주세요
			switch (rentOption) {
			case "able":
				sql.WHERE("book_single.rental = 'n'");
				break;
			case "rent":
				sql.WHERE("book_single.rental = 'y'");
				break;
			case "reserv":
				sql.WHERE("book_single.rental = 'r'");
				break;
			default:
				break;
			}
		}

		if (form.getPubDate() != null && !form.getPubDate().trim().equals("")) { // 테스트 필요
			Timestamp pubDate = Timestamp.valueOf(form.getPubDate() + " 00:00:00");
			String date = new SimpleDateFormat("yyyy-MM-dd").format(pubDate);
			if (pubDateOption.equals("after")) {
				sql.WHERE("book.pub_date >= '" + date + "'");
			} else {
				sql.WHERE("book.pub_date <= '" + date + "'");
			}
		}
		
	}
	
}