package net.koreate.mvc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/context/root-context.xml"})
public class DBTest {
	
	@Autowired
	DataSource ds;
	
	@Test
	public void testDataSource() {
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			log.info("Connection : " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
