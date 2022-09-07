package com.koreate.betty.domain.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.koreate.betty.domain.board.vo.FreeBoard;
import com.koreate.betty.domain.book.dto.BookListPageDto;
import com.koreate.betty.domain.book.dto.form.BookForm;
import com.koreate.betty.domain.book.service.BookService;
import com.koreate.betty.domain.book.vo.Book;
import com.koreate.betty.global.error.exception.NotFoundISBNException;
import com.koreate.betty.global.util.BookCriteria;
import com.koreate.betty.global.util.PageMaker;
import com.koreate.betty.global.util.SearchCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	// 도서 목록
	@GetMapping

	public String bookListPage(@ModelAttribute("cri") BookCriteria cri, Model model) {
		log.info("booklistpage cri = {}", cri);
		bookService.bookList(cri, model);
		return "book/book-board";
	}
	
	// 도서 상세 정보
	@GetMapping("{isbn}")
	public String bookDetail(@PathVariable String isbn, Model model) { // key : book
		Book book = bookService.bookDetail(isbn);
		if (book == null) {
			throw new NotFoundISBNException();
		}
		model.addAttribute("book", book);
		return "book/book-detail";
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView notFoundHandler(NotFoundISBNException ex) {
		log.error("ex = {}",ex);
		return new ModelAndView("/home");
	}
	
	// 도서 검색 : 작업 대기 중
	
	// 도서 페이지 전환 (ajax가 아닌 일반 처리) : 작업 대기 중
	
	// 신규 도서 입고
	@PostMapping
	public String newBook(BookForm form) {
		int result = bookService.bookRegister(form);
		return "redirect:/books/" + form.getCode();
	}
	
	// 도서 정보 수정
	@PutMapping("{isbn}")
	public String updateBook(BookForm form) {
		String code = form.getCode();
		int result = bookService.update(code, form);
		return "redirect:/books/" + code;
	}	
	
	// 도서 후기 목록 페이지 (목록 출력은 ajax 사용으로 인해 commentApi에서 처리)
	@GetMapping("{isbn}/comments")
	public String bookComment(@PathVariable String isbn) {
		return "redirect:/books/" + isbn;
	}
	
	// 재고 추가
	@PostMapping("{isbn}/single")
	public String addWare(@PathVariable String isbn, String id, int amount) {
		int result = 0;
		Integer count = amount;		
		result = bookService.insertWare(isbn, id, count);
		return "redirect:/staff/books/";
	}
	
}