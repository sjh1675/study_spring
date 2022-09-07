package com.koreate.betty.domain.rental.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.koreate.betty.domain.rental.provider.RentalProvider;
import com.koreate.betty.domain.rental.vo.RentalBook;
import com.koreate.betty.domain.rental.vo.ReserveBook;

@Repository
public interface RentalRepository {

	@SelectProvider(type=RentalProvider.class, method="rentalByMemberId") // 내 대여
	public List<RentalBook> rentalByMemberId(String id);
	
	@SelectProvider(type=RentalProvider.class, method="reserveByMemberId") // 내 예약
	public List<ReserveBook> reserveByMemberId(String id);
	
	@InsertProvider(type=RentalProvider.class, method="rentalBook") // 도서 대여하기
	public int rentalBook(@Param("id")String id, @Param("code")String code, @Param("num")Integer num);
	
	@InsertProvider(type=RentalProvider.class, method="reserveBook") // 도서 예약하기
	public int reserveBook(ReserveBook reserveBook);
	
	@DeleteProvider(type=RentalProvider.class, method="reserveCancle") // 예약 취소
	public int reserveCancle(@Param("id")String id, @Param("code")String code);
	
	@DeleteProvider(type=RentalProvider.class, method="returnBook") // 도서 반납하기
	public int returnBook(@Param("id")String id, @Param("code")String code);
	
}