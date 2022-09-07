package com.jaemin.template.web;

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

import com.jaemin.template.config.AppConfig;
import com.jaemin.template.config.RootConfig;
import com.jaemin.template.config.WebConfig;

import lombok.extern.slf4j.Slf4j;

@ContextConfiguration(classes = { WebConfig.class, AppConfig.class, RootConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Slf4j
public class TestControllerTest {

	
	
		@Autowired
		private WebApplicationContext wc;
		
		private MockMvc mvc;	// 가상의 DispatcherServlet
		
		@Before
		public void setUp()throws Exception{
			mvc = MockMvcBuilders.webAppContextSetup(wc).build();
		}

		@Test
		public void contestLoad() {
			log.info("wc = {}",wc);
			log.info("mvc = {}",mvc);
		}
		
		@Test
		public void controllerTest() throws Exception {
			mvc.perform(MockMvcRequestBuilders
					.post("/template/add")
					.param("id", "test")
					.param("pw", "test"));
		}
		
		
		//@Test
		public void testRead() throws Exception{
			// perform : 수행하다
			/**
			 	MockMvcRequestBuilders를 사용해 설정 한 요청 데이터를 
			 	수행 -> perform 
			 */
			ModelMap map = mvc.perform(
				MockMvcRequestBuilders.get("/sboard/listPage")
			).andReturn().getModelAndView().getModelMap();
			System.out.println("testRead : "+map);
		}
		
		//@Test
		public void readBoard() throws Exception{
			System.out.println("readBoard() ============= ");
			ModelAndView mav = mvc.perform(
									MockMvcRequestBuilders
									.get("/sboard/readPage")
									.param("bno", "2041")
								).andReturn().getModelAndView();
			ModelMap map = mav.getModelMap();
			Map<String, Object> obj = mav.getModel();
			for(String key : obj.keySet()) {
				System.out.println("key : " + key);
				System.out.println("value : " + obj.get(key));
			}
			System.out.println("viewName : "+mav.getViewName());
		}
		
		//@Test
		public void insertBoardTest() throws Exception{
			ResultActions ra = mvc.perform(
						MockMvcRequestBuilders.post("/sboard/register")
						.param("title","테스트 제목")
						.param("content", "테스트 내용")
						.param("writer", "박정대")
					);
			MvcResult result = ra.andReturn();
			ModelAndView mav = result.getModelAndView();
			FlashMap flash = result.getFlashMap();
			System.out.println("============================");
			System.out.println(mav);
			System.out.println("flash : " + flash.entrySet());
			System.out.println("============================");
		}
		
		//@Test
		public void modifyBoardTest() throws Exception{
			ResultActions ra = mvc.perform(
				MockMvcRequestBuilders.post("/sboard/modifyPage")
				.param("bno","2043")
				.param("title", "update test title")
				.param("content", "update test content")
				.param("writer", "박주신")
			);
			MvcResult result = ra.andReturn();
			FlashMap flash = result.getFlashMap();
			ModelAndView mav = result.getModelAndView();
			System.out.println("==========================");
			System.out.println(mav);
			System.out.println(flash.entrySet());
			System.out.println("==========================");
		}
	
}
