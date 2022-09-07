package net.koreate.db_test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.db_test.dao.MemberDAO;
import net.koreate.db_test.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) // 컨텍스트 확인(매개변수로 경로 이름)
public class DataSourceTest {	// test에서는 서버가 실행되지 않으므로 빈이 생성되지 않는데, 임시 서버를 설정을 해주는 어노테이션들		// 참고 (서버 실행 -> 배포서술자 -> 컨텍스트 -> 컴포넌트스캔 -> 빈 생성
	
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
	
	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testSqlSessionFactory() {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println("연결 정보 객체 생성 완료");
			System.out.println(session.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	MemberDAO dao;
	
	// @Test
	public void testInsertMember() {
		MemberVO vo = new MemberVO();
		vo.setUserid("id005");
		vo.setUserpw("pw005");
		vo.setUsername("최기근");
		dao.insertMember(vo);
	}
	
	
	@Test
	public void testReadMember() {
		MemberVO m = dao.readMember("id001");
		System.out.println("readMember : " + m);
		
		MemberVO member = dao.readMemberWithPass("id002", "pw002");
		System.out.println("readWithPass : " + member);
		
		System.out.println("=================================");
		List<MemberVO> memberList = dao.readMemberList();
		
		for (MemberVO vo : memberList) {
			System.out.println(vo);
		}
	}
}