package net.koreate.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import net.koreate.mvc.board.dao.BoardDAO;
import net.koreate.mvc.board.vo.BoardVO;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/context/root-context.xml"})
public class BoardDAOTest {
	
	@Autowired
	BoardDAO bdao;
	
	@Test
	public void bdao_test() {
		log.info("bdao 객체 생성 : " + bdao);
	}
	
	
	// @Test
	public void dao_listCount_test() throws Exception {
		log.info("tbl data count : " + bdao.listCount());		
	}
	
	
	//@Test
	public void dao_read_test() throws Exception {
		int bno = 20;
		BoardVO board = bdao.read(bno);
		log.info("게시물 번호 " + bno + "와 일치하는 게시물 : " + board);
	}
	
	// @Test
	public void dao_create_test() throws Exception {
		
		BoardVO board = new BoardVO();
		board.setTitle("테스트 제목 1");
		board.setContent("테스트 내용 1");
		board.setWriter("테스트 작성자 1");
		
		int result = bdao.create(board);
		log.info("삽입완료 된 행의 갯수 : " + result);
	}
	
}
