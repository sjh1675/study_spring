package net.koreate.board.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.koreate.board.util.Calculator;

@Configuration // 참조로 인해 매개변수로 메소드를 실행 시키면 새로운 객체(빈, 즉 싱글톤이 아닌)가 들어가서 싱글톤 보장이 안되는데, 이미 빈으로 등록되어 있다면 새로운 객체를 생성하는게 아닌 등록된 빈을 준다
// 없다면 Test의 ====이하의 주소값이 다른걸 확인할 수 있다
@MapperScan(basePackages= {"net.koreate.board.dao"})
public class RootConfig {

	// == spring container
	// BeanFactory == 주로 빈을 관리하고 조회함
	// 국제화 기능, 환경변수 관련 처리, 애플리케이션 이벤트, 리소스 조회 등의 기능이 있다
	@Autowired
	ApplicationContext context;
	
	
	@Bean // 메소드가 반환하는 값을 빈으로 등록시켜줌
	@Scope("prototype") // 기본 스코프는 싱글톤이지만, 프로토타입으로 설정 시 빈을 싱글톤으로 관리하지 않음
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/spring_data");
		ds.setUsername("spring");
		ds.setPassword("12345");
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setTypeAliasesPackage("net.koreate.board.vo, net.koreate.board.util");
		bean.setMapperLocations(context.getResources("classpath:mybatis/sql/*.xml"));
		
		return bean.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() throws Exception {
		SqlSession session = new SqlSessionTemplate(sqlSessionFactory());
		return session;
	}
	
	@Bean
	public Calculator getCalculator() {
		return new Calculator();
	}
	
}
