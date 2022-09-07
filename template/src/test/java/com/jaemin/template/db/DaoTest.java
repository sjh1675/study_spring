package com.jaemin.template.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jaemin.template.config.AppConfig;
import com.jaemin.template.config.RootConfig;
import com.jaemin.template.config.WebConfig;
import com.jaemin.template.dao.TempDAO;
import com.jaemin.template.dao.TestDAO;
import com.jaemin.template.vo.TempVO;
import com.jaemin.template.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class, RootConfig.class, AppConfig.class })
@WebAppConfiguration
public class DaoTest {

	@Autowired
	TestDAO dao;
	@Autowired
	TempDAO tpdao; 
	
	//@Test
	public void 카멜케이스_테스트() throws InterruptedException {
		//dao.save(new TestVO("cccc", "dddd", "eeee"));
		List<TestVO> lis = dao.findAll();
		log.info("dao list = {}", lis);
		Thread.sleep(1000);
	}
	
	//@Test
	//@Transactional
	public void 카멜_검색() throws Exception{
		TestVO vo = dao.findByTest("camelTest3");
		log.info("find vo = {}",vo);
	}
	
	@Test
	//@Transactional
	public void 테스트() throws Exception {
//		TempVO vo = new TempVO("125","1","1","1","1",1,"1","1","1", 1);
//		tpdao.insert(vo);
		
		/*
		private String bCode;
		private String bName;
		private String bAuthor;
		private String bPub;
		private String bPubdate;
		private int bPage;
		private String bType;
		private String bImage;
		private String bOverView;
		private int bCount;
		*/
	}
	
	
}
