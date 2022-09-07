package com.koreate.betty.domain.offline.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static com.koreate.betty.domain.model.TableConst.CHK_REAL_TBL;
public class OfflineProvider {
	
	public String checkIn(@Param("id")String id, @Param("seat")Integer seat) {
		return new SQL().INSERT_INTO(CHK_REAL_TBL)
				.INTO_COLUMNS("member_id", "check_time", "seat")
				.INTO_VALUES("#{id}, now(), #{seat}")
				.toString();
	}
	
	public String checkOut(String id) {
		return new SQL().DELETE_FROM(CHK_REAL_TBL)
				.WHERE("member_id = #{id}")
				.toString();
	}
	
	public String seatStatus() {
		return new SQL().SELECT("seat").FROM(CHK_REAL_TBL)
				.toString();			
	}
}
