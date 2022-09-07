package com.koreate.betty.domain.book.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreate.betty.domain.book.dto.form.BookCommentForm;
import com.koreate.betty.domain.book.service.BookCommentService;
import com.koreate.betty.domain.book.service.BookService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookCommentApiController {

	@Autowired
	BookService bs;
	
	@Autowired
	BookCommentService bcs;
	
	// 댓글 출력
	@PostMapping("{isbn}/comments")  // json
	public Map<String, Object> bookComment(@PathVariable String isbn, Integer page) {
		Map<String, Object> map = bcs.findCommentByCode(isbn, page);
		return map;
	}
	
	// 댓글 추가
	@PostMapping("{isbn}/comment")
	public testDto addComment(BookCommentForm form) {
		log.info("form = {} ",form);
		int result = bcs.insertComment(form);
		String str = result == 1 ? "성공" : "실패";
		testDto dto = new testDto();
		dto.setData(str);
		return dto;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	static class testDto{
		private String data;
		
	}
	
	
	// 댓글 삭제
	@DeleteMapping("{isbn}/comment/{cno}")
	public String delComment(@PathVariable Integer cno) {
		int result = bcs.deleteComment(cno);
		return result == 1 ? "성공" : "실패";
	}
	
}
