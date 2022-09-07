package net.koreate.mvc;

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
import org.springframework.web.servlet.View;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/context/root-context.xml",
		"file:src/main/resources/context/root-servlet.xml" })
@WebAppConfiguration
public class MvcTest {

	@Autowired
	private WebApplicationContext wc;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}


	// @Test
	public void test_listpage() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/sboard/listPage")
				.param("page", "1")
				.param("perPageNum", "1")
				.param("searchType", "t")
				.param("keyword", "2")
				).andReturn().getModelAndView();

		log.info("mav : " + mav);
	}

	// @Test
	public void test_register() throws Exception {

		ResultActions ra = mvc.perform(MockMvcRequestBuilders.post("/sboard/register")
				.param("title", "테스트 등록 제목 2")
				.param("content", "테스트 등록 내용 2")
				.param("writer", "테스트 등록 작성자 2"));
		MvcResult result = ra.andReturn();
		ModelAndView mav = result.getModelAndView();
		FlashMap flash = result.getFlashMap();

		log.info("mav : " + mav);
		log.info("flash : " + flash.entrySet());

	}

	@Test
	public void test_readDetail() throws Exception {	
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/sboard/readDetail").param("bno", "3")).andReturn().getModelAndView();
		Map<String, Object> obj = mav.getModel();
		for(String key : obj.keySet()) {			
			log.info("model : " + obj.get(key));
		}
		log.info("viewName : " + mav.getViewName());		
	}
	
}
