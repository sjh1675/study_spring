package net.koreate.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/context/root/*.xml"})
public class DBTest {

	@Inject
	DataSource ds;
	
	@Test
	public void testDataSource() {
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			System.out.println(conn + " : 연결완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
