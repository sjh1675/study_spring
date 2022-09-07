package com.koreate.betty.domain.rental.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.koreate.betty.domain.rental.dao.RentalRepository;
import com.koreate.betty.domain.rental.vo.RentalBook;
import com.koreate.betty.domain.rental.vo.ReserveBook;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	
	
	private final RentalRepository rentalRepository;
	
	
	// 회원의 대여한 책 리스트
	public List<RentalBook> rentalByMemberId(String id) {		
		return rentalRepository.rentalByMemberId(id);
	}

	// 회원의 예약한 책 리스트
	public List<ReserveBook> reserveByMemberId(String id) {
		return rentalRepository.reserveByMemberId(id);
	}
	
	// 도서 대여하기
	public int rentalBook(String id, String code, Integer num) {
		int result = rentalRepository.rentalBook(id, code, num);
		return result;
	}	
	
	// 도서 예약하기
	public int reserveBook(ReserveBook reserveBook) {
		return rentalRepository.reserveBook(reserveBook);
	}
	
	// 도서 예약 취소
	public int reserveCancle(String id, String code) {
		int result = rentalRepository.reserveCancle(id, code);
		return result;
	}
	
	// 도서 반납하기
	public int returnBook(String id, String code) {
		int result = rentalRepository.returnBook(id, code);
		return result;
	}
}
