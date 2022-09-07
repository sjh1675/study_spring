package com.jaemin.template.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.jaemin.template.dao.TestDAO;
import com.jaemin.template.vo.TestVO;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

	private final TestDAO dao;
	
	public void test(TestVO vo) {
		log.info("START =====Service test=====");
		dao.save(vo);
		log.info("END =====Service test=====");
	}
	
}
