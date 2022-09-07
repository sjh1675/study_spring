package com.jaemin.template.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jaemin.template.vo.TestVO;

@Mapper
public interface TestDAO {
	
	void save(TestVO obj);
	
	List<TestVO> findAll();
	
	TestVO findByTest(String str);
}











