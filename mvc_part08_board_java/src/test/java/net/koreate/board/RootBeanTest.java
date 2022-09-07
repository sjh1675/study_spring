package net.koreate.board;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import net.koreate.board.config.RootConfig;
import net.koreate.board.controller.BoardController;
import net.koreate.board.dao.BoardDAO;
import net.koreate.board.service.BoardServiceImpl;
import net.koreate.board.util.Calculator;
import net.koreate.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class RootBeanTest {

	@Autowired
	Calculator calc;
	
	@Before
	public void before() {
		System.out.println("테스트 수행 전 실행");		
	}
	
	@After
	public void after() {
		System.out.println("테스트 완료 후 실행");
	}
	
	@Test
	public void test() throws Exception {
		System.out.println("test 호출");
		System.out.println(calc.minus(10, 5));
		System.out.println("test 종료");
	}
	
	@Inject
	BoardDAO dao;
	
	
	@Test
	public void testController() throws Exception {
		BoardController boardController = new BoardController(new BoardServiceImpl(dao));
		Model model = new ConcurrentModel();
		boardController.listAll(model);
		List<BoardVO> list = (ArrayList<BoardVO>)model.getAttribute("list");
		for (BoardVO b : list) {
			System.out.println(b);
		}
	}
	
}
