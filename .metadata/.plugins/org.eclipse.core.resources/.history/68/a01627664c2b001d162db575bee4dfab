package com.koreate.betty.domain.book.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.koreate.betty.domain.book.dao.BookRepository;
import com.koreate.betty.domain.book.dto.RentalStatusDTO;
import com.koreate.betty.domain.book.dto.form.BookDeleteForm;
import com.koreate.betty.domain.book.dto.form.BookForm;
import com.koreate.betty.domain.book.dto.form.BookSearchForm;
import com.koreate.betty.domain.book.dto.form.NewBookForm;
import com.koreate.betty.domain.book.vo.Book;
import com.koreate.betty.domain.book.vo.JBookSingle;
import com.koreate.betty.global.util.BookCriteria;
import com.koreate.betty.global.util.Criteria;
import com.koreate.betty.global.util.PageMaker;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.koreate.file.utils.MediaUtils;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepository;
	private final ServletContext context;
	
	// 페이징 처리가 필요한 뷰는 맵 객체로 반환합니다. 목록 key : list, PageMaker key : pm
	// 책 목록 (books) : 매개변수가 null이면 해당 검색 옵션이 없이 출력합니다
	public void bookList(BookCriteria cri, Model model) {
		int totalCount = bookRepository.findAllCount(cri);
		PageMaker pageMaker = new PageMaker(cri, totalCount);
		List<Book> list = bookRepository.findAll(cri);
		model.addAttribute("list", list);
		model.addAttribute("pm",pageMaker);
		
	}

	// 책 상세 정보
	public Book bookDetail(String code) {
		return bookRepository.bookDetail(code);
	}

	// 책 1개 싱글 + 정보
	public JBookSingle single(String code, Integer num) {
		return bookRepository.single(code, num);
	}

	// 한 도서의 재고 (대여 예약 미포함)
	public int countExistByCode(String code) {
		return bookRepository.countExistByCode(code);
	}

	// 한 도서의 총 갯수 (대여 예약 포함)
	public int countAllByCode(String code) {
		return bookRepository.countAllByCode(code);
	}

	// 도서 정보 등록
	public int bookRegister(NewBookForm form) {
		boolean result = BookImgUpload(form.getCode(), form.getImg());
		System.out.println("\n\n\n\nresult : " + result);
		if(result == false) {
			return 0;
		}
		return bookRepository.insert(form);
	}

	// 도서 정보 수정
	public int update(String targetCode, BookForm form) {
		Book book = form.createBook();
		return bookRepository.update(targetCode, book);
	}

	// 도서 재고 추가
	@Transactional
	public int insertWare(String code, String id, Integer count) {
		int result = 0;
		int iCount = count;
		while (iCount-- > 0) {
			int temp = bookRepository.insertWare(code, id);
			result += temp;
		}
		return result;
	}

	public List<JBookSingle> findBookSingleByCode(String code) { // 도서 폐기처리할 목록 불러오는 ajax 처리용
		return bookRepository.jFindByCode(code);
	}

	// 도서 폐기
	@Transactional
	public int deleteBook(List<BookDeleteForm> formList) { // 두배의 결과가 나옵니다
		int result = 0;

		for (BookDeleteForm del : formList) {
			result += bookRepository.insertDump(del);
			result += bookRepository.delete(del);
		}
		return result;
	}

	// 도서 정보 + 도서 번호(개별책 전부 출력)
	public Map<String, Object> jBookList(Integer page) {
		Map<String, Object> map = new HashMap<>();
		Criteria cri = new Criteria();
		if (page != null) {
			cri.setPage(page);
		}
		int totalCount = bookRepository.jFindAllCount();
		PageMaker pageMaker = new PageMaker(cri, totalCount);
		map.put("list", bookRepository.jFindAll(cri));
		map.put("pm", pageMaker);
		return map;
	}

	// 도서 현황 검색
	public Map<String, Object> bookSearch(BookSearchForm form, Integer page) {
		Map<String, Object> map = new HashMap<>();
		Criteria cri = new Criteria();
		if (page != null) {
			cri.setPage(page);
		}

		int totalCount = bookRepository.jSearchCount(form);
		PageMaker pageMaker = new PageMaker(cri, totalCount);
		List<Book> list = bookRepository.jSearch(form, cri);

		map.put("list", list);
		map.put("pm", pageMaker);

		if (list != null) { // 도서의 수를 알려주는 로직

			List<Integer> nowCount = new ArrayList<>(); // 타입 변경 자유(반환만 되면)
			List<Integer> allCount = new ArrayList<>();

			for (Book b : list) {
				nowCount.add(bookRepository.countExistByCode(b.getCode()));
				allCount.add(bookRepository.countAllByCode(b.getCode()));
			}

			map.put("nowCount", nowCount);
			map.put("allCount", allCount);
		}

		return map;
	}

	// 도서 현황 검색
	public Map<String, Object> rentalBookSearch(BookSearchForm form, Integer page) {
		Map<String, Object> map = new HashMap<>();
		Criteria cri = new Criteria();
		if (page != null) {
			cri.setPage(page);
		}

		int totalCount = bookRepository.jRentalSearchCount(form);
		PageMaker pageMaker = new PageMaker(cri, totalCount);
		List<RentalStatusDTO> list = bookRepository.jRentalSearch(form, cri);

		map.put("list", list);
		map.put("pm", pageMaker);

		return map;
	}
	
	public Integer findExistNum(String code) {
		Integer find = bookRepository.findExistNum(code);
		return find;
	}
	
	
	public boolean BookImgUpload(String code, MultipartFile img) {
				
		boolean uploaded = false;
		
		String originalName = img.getOriginalFilename();
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
//		log.info("\n\n\n\n originalName : {}", originalName);
//		log.info("\n\n\n\n formatName : {}", formatName);
		
		if (MediaUtils.getMediaType(formatName) == null) { // 이미지 파일이 아닐 경우
			return uploaded;
		}
		
		String sp = File.separator;
		
		String rawPath = context.getRealPath(sp+"resources"+sp+"img"+sp+"book"+sp+"raw");		
		String originPath = context.getRealPath(sp+"resources"+sp+"img"+sp+"book"+sp+"origin");		
		String thumbPath = context.getRealPath(sp+"resources"+sp+"img"+sp+"book"+sp+"thumbnail");
//		log.info("origin name = {}", originalName);
//		log.info("originPath = {}", originPath);
		
		String imgName = code + "." + formatName; 
		
		File file = new File(rawPath, imgName);
		
		try {
			img.transferTo(file);
			
			BufferedImage rawImg = ImageIO.read(file);		
						
			BufferedImage originImg = Thumbnails.of(rawImg).size(272, 400).keepAspectRatio(false).asBufferedImage();
			BufferedImage thumbImg = Thumbnails.of(rawImg).size(50, 50).keepAspectRatio(false).asBufferedImage();
			
			File mFile = new File(originPath, imgName);
			ImageIO.write(originImg, formatName, mFile);
			File tFile = new File(thumbPath, imgName);			
			ImageIO.write(thumbImg, formatName, tFile);
			
			uploaded = true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uploaded;
	}
	
	
	public int insertByCrawler(Book book) {
		return bookRepository.insertByCrawler(book);
	}

}