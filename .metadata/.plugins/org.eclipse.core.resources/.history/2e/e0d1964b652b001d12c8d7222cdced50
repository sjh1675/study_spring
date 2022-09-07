package com.koreate.betty.domain.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.koreate.betty.domain.book.dto.RentalStatusDTO;
import com.koreate.betty.domain.book.dto.form.BookDeleteForm;
import com.koreate.betty.domain.book.dto.form.BookSearchForm;
import com.koreate.betty.domain.book.dto.form.NewBookForm;
import com.koreate.betty.domain.book.provider.BookProvider;
import com.koreate.betty.domain.book.provider.BookSingleProvider;
import com.koreate.betty.domain.book.vo.Book;
import com.koreate.betty.domain.book.vo.JBookSingle;
import com.koreate.betty.global.util.BookCriteria;
import com.koreate.betty.global.util.Criteria;

@Mapper
public interface BookRepository {

	@SelectProvider(type=BookProvider.class, method="findAll")		// 책 목록
	public List<Book> findAll(BookCriteria cri);
	
	@SelectProvider(type=BookProvider.class, method="findAllCount")		// 전체 책 목록 총계  (findAll PageMaker) 
	public Integer findAllCount(BookCriteria cri);
		
	@SelectProvider(type=BookProvider.class, method="bookDetail")		// 책 상세정보 
	public Book bookDetail(String code);
	
	@SelectProvider(type=BookSingleProvider.class, method="single")		// 번호 있는 책 한권
	public JBookSingle single(@Param("code")String code, @Param("num")Integer num);
	
	@SelectProvider(type=BookSingleProvider.class, method="countExistByCode")	// 재고 (대여 예약 미포함)
	public int countExistByCode(String code);
	
	@SelectProvider(type=BookSingleProvider.class, method="countAllByCode")	// 한 책의 갯수(대여 예약 포함)
	public int countAllByCode(String code);	
	
	@SelectProvider(type=BookProvider.class, method="jFindAll") // book, book_single
	public List<Book> jFindAll(Criteria cri);
	
	@SelectProvider(type=BookProvider.class, method="jFindAllCount")
	public int jFindAllCount();
	
	@SelectProvider(type=BookProvider.class, method="jFindByCode") // book, book_single
	public List<JBookSingle> jFindByCode(String code);
	
	@SelectProvider(type=BookProvider.class, method="jSearch")
	public List<Book> jSearch(BookSearchForm form, Criteria cri);
	
	@SelectProvider(type=BookProvider.class, method="jSearchCount")
	public int jSearchCount(BookSearchForm form);
	
	@SelectProvider(type=BookProvider.class, method="jRentalSearch")
	public List<RentalStatusDTO> jRentalSearch(BookSearchForm form, Criteria cri);
	
	@SelectProvider(type=BookProvider.class, method="jRentalSearchCount")
	public int jRentalSearchCount(BookSearchForm form);
	
	@SelectProvider(type=BookSingleProvider.class, method="findExistNum")	// 렌탈 서비스에서 참조
	public Integer findExistNum(String code);	
	
	@InsertProvider(type=BookProvider.class, method="insert")
	public int insert(NewBookForm book);
	
	@InsertProvider(type=BookSingleProvider.class, method="insertWare")
	public int insertWare(@Param("code")String code, @Param("id")String id);
	
	@InsertProvider(type=BookSingleProvider.class, method="insertDump")
	public int insertDump(BookDeleteForm form);
	
	@UpdateProvider(type=BookProvider.class, method="update")
	public int update(@Param("targetCode") String targetCode, @Param("book") Book book);
	
	@DeleteProvider(type=BookSingleProvider.class, method="delete")
	public int delete(BookDeleteForm form);
	
	// 크롤링
	@InsertProvider(type=BookProvider.class, method="insertByCrawler")
	public int insertByCrawler(Book book);
	
}
