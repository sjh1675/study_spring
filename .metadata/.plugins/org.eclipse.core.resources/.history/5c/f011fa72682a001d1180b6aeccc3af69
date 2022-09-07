package com.koreate.betty.domain.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.koreate.betty.domain.member.provider.MemberCardProvider;
import com.koreate.betty.domain.member.vo.MemberCard;

@Mapper
public interface MemberCardRepository {

	@SelectProvider(type=MemberCardProvider.class, method="findOne")
	public MemberCard findOne(String id);
	
	@UpdateProvider(type=MemberCardProvider.class, method="demerit")
	public int updateDemerit(@Param("id")String id, @Param("point")Integer demerit);
	
	@UpdateProvider(type=MemberCardProvider.class, method="updatePoint")
	public int updatePoint(@Param("id")String id, @Param("point")int point);
	
	@UpdateProvider(type=MemberCardProvider.class, method="updateGrade")
	public int updateGrade(@Param("id")String id, @Param("msGrade")String msGrade);
	
	@UpdateProvider(type=MemberCardProvider.class, method="updateLend")
	public int updateLend(@Param("id")String id, @Param("grade")String grade);
	
}
