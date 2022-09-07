package com.koreate.betty.domain.book.provider;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.koreate.betty.domain.book.dto.RentalStatusDTO;
import com.koreate.betty.domain.book.dto.form.BookForm;
import com.koreate.betty.domain.book.dto.form.BookSearchForm;
import com.koreate.betty.domain.book.service.BookService;
import com.koreate.betty.domain.book.vo.Book;
import com.koreate.betty.domain.book.vo.JBookRental;
import com.koreate.betty.global.config.AppConfig;
import com.koreate.betty.global.config.RootConfig;
import com.koreate.betty.global.config.WebConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { WebConfig.class, AppConfig.class, RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepoTest {

	@Autowired
	BookService bs;

	// @Test
	public void bookUpdateTest() { // 테스트 완료 (update)

		String targetCode = "9772950991004";

		Timestamp time = Timestamp.valueOf("2022-06-10 00:00:00");
		BookForm form = new BookForm("9772950991004", "더퍼슨스 the Persons No.4 : 브랜드 디렉터 Brand Director", "더퍼슨스", "더퍼슨스",
				"2022-06-10", 330, 300,
				"책소개\r\n"
						+ " 우리는 매일 브랜드를 마주하며 살아간다. 때로는 브랜드의 메시지에 공감하기도 하고, 그들의 아름다움과 뛰어남에 감탄하기도 한다. 더불어 다른 이들이 어떤 브랜드를 좋아하는지 열심히 관찰한다. 각자가 좋아하는 브랜드가 곧 각자의 정체성을 대변하기 때문이다.\r\n"
						+ " \r\n"
						+ " 브랜드의 이면에서 브랜드의 정체성을 쌓아올리고 있는 브랜드 디렉터를 만났다. 새로운 유기체의 페르소나를 빚어내는 사람. 인간 존재를 고민하며 정체성을 탐구하는 사람. 비즈니스가 단순 돈벌이에 그치지 않도록 알맹이를 채워 넣는 사람. 세상에 없던 정체성을 생성하며 우리에게 소구하는 국내 최고 브랜드 디렉터 9명을 만나보았다.");

		int result = bs.update(targetCode, form);
		log.info("result : " + result);
	}

	// @Test
	public void joinTest() { // 테스트 완료 (jbooksList)
		Map<String, Object> map = bs.bookList(null, null, null);
		List<Book> list = (List<Book>) map.get("list");
		log.info("size : " + list.size());
		Book b1 = list.get(1);
		log.info(b1.getCode());
		log.info(b1.getTitle());
		log.info(b1.getAuth());
	}

	// @Test
	public void jBooksSearchTest() {
		String searchText = "마술";
		String searchOption = "title";

		String rentOption = "all";

		String pubDate = "2015-01-01";
		String pubDateOption = "after";

		String genre = null;

		BookSearchForm bsf = new BookSearchForm();
		bsf.setSearchText(searchText);
		bsf.setSearchOption(searchOption);
		bsf.setRentOption(rentOption);
		bsf.setPubDate(pubDate);
		bsf.setPubDateOption(pubDateOption);
		bsf.setGenre(genre);

	}

//	@Test
	public void jSearchTest() {
		BookSearchForm books = new BookSearchForm();
		
		Map<String, Object> booksMap = bs.bookSearch(books, 1);
		Map<String, Object> rentalsMap = bs.rentalBookSearch(books, 1);
		
		List<Book> booksList = (List<Book>)booksMap.get("list");
		List<RentalStatusDTO> rentalsList = (List<RentalStatusDTO>)rentalsMap.get("list");
		
		log.info("\n\n\n\n");
		log.info("booksList : {}", booksList);
		log.info("rentalsList : {}", rentalsList);
		
		for (int i = 0; i < 5; i++) {
			log.info("\nbook : {}", booksList.get(i).getTitle());
		}
		
		for (int i = 0; i < 5; i++) {
			log.info("\nrentalBook : {}", rentalsList.get(i).getTitle());
		}
	}
	
}
