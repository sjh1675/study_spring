package net.koreate.common;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/root-context.xml", "/servlet-context.xml"})
@WebAppConfiguration		// 테스트 시에 웹에 필요한 설정 정보를 가져옴(MockMvc 사용시에 매개변수로 필요함)
public class ControllerTest {

	@Autowired
	private WebApplicationContext wc;
	
	private MockMvc mvc;	// 가상의 DispatcherServlet
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wc).build();	// 요청을 받을 수 있는 모조 디스패쳐서블릿
	}
	
	// @Test
	public void testRead() throws Exception {
		// MockMvcRequestBuilders를 사용해 설정한 요청 정보를 수행 -> perform
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/sboard/listPage")).andReturn().getModelAndView().getModelMap();
		System.out.println("map : " + map);
	}
	
	// @Test
	public void readBoard() throws Exception {	// 게시글 한개 정보 출력
		System.out.println("readBoard() 시작");
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/sboard/readPage").param("bno", "41")).andReturn().getModelAndView();
		ModelMap map = mav.getModelMap();
		Map<String, Object> obj = mav.getModel();
		for(String key : obj.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + obj.get(key));
		}
		System.out.println("viewName : " + mav.getViewName());
		System.out.println("readBoard() 종료");
	}
	
	// @Test
	public void insertBoardTest() throws Exception {
		ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/sboard/register").param("title", "테스트 제목").param("content", "테스트 내용").param("writer", "테스트 작성자"));
		MvcResult result = ra.andReturn();
		ModelAndView mav = result.getModelAndView();
		FlashMap flash = result.getFlashMap();
		System.out.println("===========================");
		System.out.println(mav);
		System.out.println("flash : " + flash.entrySet());
		System.out.println("===========================");
	}
	
	@Test
	public void modifyBoardTest() throws Exception {
		ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/sboard/modifyPage").param("bno", "33").param("title", "제").param("content", "내").param("writer", "작"));
		MvcResult result = ra.andReturn();
		FlashMap flash = result.getFlashMap();
		ModelAndView mav = result.getModelAndView();
		System.out.println("==========================");
		System.out.println("mav : " + mav);
		System.out.println("flash : " + flash.entrySet());
		System.out.println("==========================");
	}
}
