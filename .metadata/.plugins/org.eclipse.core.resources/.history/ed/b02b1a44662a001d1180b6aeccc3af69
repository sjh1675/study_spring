package com.koreate.betty.domain.rental.provider;
import static com.koreate.betty.domain.model.TableConst.RENTAL_RESERVE_TBL;
import static com.koreate.betty.domain.model.TableConst.RENTAL_TBL;
import static com.koreate.betty.domain.model.TableConst.BOOK_TBL;
import static com.koreate.betty.domain.model.TableConst.RENTAL_LOG_TBL;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.koreate.betty.domain.rental.vo.ReserveBook;

public class RentalProvider {
	
	public String rentalByMemberId(String id) {
		return new SQL().SELECT("rental.*, book.title").FROM(RENTAL_TBL).JOIN(BOOK_TBL)
				.WHERE("book_code = code")
				.WHERE("member_id = #{id}")
				.toString();
	}
	
	public String reserveByMemberId(String id) {
		return new SQL().SELECT("rental_reserve.*, book.title").FROM(RENTAL_RESERVE_TBL).JOIN(BOOK_TBL)
				.WHERE("book_code = code")
				.WHERE("member_id = #{id}")
				.toString();
	}

	// num은 BookSingleProvider의 findExistNum 메소드 사용
	public String rentalBook(@Param("id")String id, @Param("code")String code, @Param("num")Integer num) {
		SQL sql = new SQL().INSERT_INTO(RENTAL_TBL)
		.INTO_VALUES("#{code}, #{num}, #{id}, DATE_FORMAT(now(), '%Y-%m-%d'), DATE_FORMAT(DATE_ADD(now(), INTERVAL (SELECT lend_period FROM premium WHERE grade = (SELECT premium_grade FROM member_card WHERE member_id = #{id})) DAY), '%Y-%m-%d')");
		return sql.toString(); 
	}
	
	// 위 쿼리가 작동하지 않을 시 반납일을 null로 변환한 뒤 작성
//	public String rentalUpdate(@Param("id")String id, @Param("code")String code) {
//		return "";
//	}
	
	public String reserveBook(ReserveBook reserveBook) {
		return new SQL().INSERT_INTO(RENTAL_RESERVE_TBL)
				.INTO_VALUES("#{code}, #{num}, #{id}, DATE_FORMAT(#{date}, '%Y-%m-%d')")
				.toString();
	}
	
	public String reserveCancle(@Param("id")String id, @Param("code")String code) {
		return new SQL().DELETE_FROM(RENTAL_RESERVE_TBL)
				.WHERE("member_id = #{id}")
				.WHERE("book_code = #{code}")
				.toString();
	}
	
	public String returnBook(@Param("id")String id, @Param("code")String code) {
		return new SQL().DELETE_FROM(RENTAL_TBL)
				.WHERE("member_id = #{id}")
				.WHERE("book_code = #{code}")
				.toString();
	}	
	
}