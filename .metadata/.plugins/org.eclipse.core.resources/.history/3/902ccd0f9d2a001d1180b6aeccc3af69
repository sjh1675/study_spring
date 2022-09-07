package com.koreate.betty.domain.offline.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.koreate.betty.domain.offline.provider.OfflineProvider;

@Mapper
public interface OfflineRepository {
	
	@SelectProvider(type=OfflineProvider.class, method="seatStatus")	// 좌석 현황
	public List<Integer> seatStatus();
	
	@InsertProvider(type=OfflineProvider.class, method="checkIn")
	public int checkIn(@Param("id")String id, @Param("seat")Integer seat);
	
	@DeleteProvider(type=OfflineProvider.class, method="checkOut")
	public int checkOut(String id);

}
