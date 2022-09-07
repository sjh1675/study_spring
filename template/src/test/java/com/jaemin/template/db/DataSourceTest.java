package com.jaemin.template.db;


import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jaemin.template.config.AppConfig;
import com.jaemin.template.config.DBConfig;
import com.jaemin.template.config.WebConfig;
import com.jaemin.template.dao.TestDAO;
import com.jaemin.template.vo.TestVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, AppConfig.class, DBConfig.class})
@WebAppConfiguration
public class DataSourceTest {
	
	@Autowired
	TestDAO dao;
	@Autowired
	DataSource ds;
	

	//@Test
	public void 히카리() throws InterruptedException {
			HikariConfig config = new HikariConfig("/prop/database.properties");
			config.setAutoCommit(false);
			config.setMaximumPoolSize(10);
			config.setMinimumIdle(1);
			ds = new HikariDataSource(config);
			
			Thread.sleep(1000);
	}
	
	@Transactional
	//@Test
	public void 트랜잭션() {
		TestVO vo1 = new TestVO();
		vo1.setId("testId");
		vo1.setPw("testPw");

		TestVO vo2 = new TestVO();
		vo2.setId("testId2");
		vo2.setPw("testPw2");
		
		Connection a = DataSourceUtils.getConnection(ds);
		DataSourceUtils.releaseConnection(a, ds);
		
		Connection b =DataSourceUtils.getConnection(ds);
		DataSourceUtils.releaseConnection(b, ds);
		
		Assertions.assertThat(b).isEqualTo(a);
	}
	
}



















