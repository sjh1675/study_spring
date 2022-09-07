package com.jaemin.template.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.jaemin.template.vo.TempVO;

@Mapper
public interface TempDAO {
	
	@Insert("INSERT IGNORE INTO book VALUES(#{code}, #{title}, #{auth}, #{pub}, #{pubdate}, #{page}, #{genre}, #{intro})")
	void insert(TempVO vo) throws Exception;
	
}
