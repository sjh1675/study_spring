package net.koreate.deploy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import net.koreate.deploy.member.dao.MemberDAO;
import net.koreate.deploy.member.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/appServlet/*-context.xml", "classpath:/spring/*-context.xml"})
@Slf4j
public class DAOTest {
	

	@Autowired
	MemberDAO mdao;
	
	@Test
	public void testDAORead() {
		MemberVO member = new MemberVO("admin", "admin");
		MemberVO memberInfo = mdao.read(member);
		log.info("member info : {}", memberInfo);
	}
	
}
