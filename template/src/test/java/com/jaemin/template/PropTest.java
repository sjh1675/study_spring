package com.jaemin.template;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jaemin.template.config.WebConfig;

import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;

//@PropertySource("classpath:prop/database.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@Slf4j
@PropertySource({"classpath:/prop/file.properties" , "classpath:prop/file.properties", "classpath:/file.properties", "classpath:file.properties"})
public class PropTest {
	
	//@Value("${jdbc.driver}")
	@Value("${file.path}")
	String str;

	@Test
	public void propValueTest() {
		log.info("file.path = {}",str);
	}
	
	MessageSource ms;
	
	//@Autowired
	ApplicationContext ac; // = new AnnotationConfigApplicationContext(WebConfig.class);
	
	//@Test
	public void test() {
		log.info(ac.getId());
		log.info(str);
		for(Object obj : ac.getBeanDefinitionNames()) {
			log.info(obj.toString());
		}
		
	}
	
	
	//@Test
	public void 메시지테스트() {
		ResourceBundleMessageSource message = new ResourceBundleMessageSource();
		message.setBasename("messages");
		message.setDefaultEncoding("utf-8");
		ms = message;
		log.info("메시지 테스트");
		
		
		String msTest = ms.getMessage("hello", null, Locale.KOREA);
		log.info(msTest);
		
		msTest = ms.getMessage("hello", null, Locale.ENGLISH);
		log.info(msTest);
		
		msTest = ms.getMessage("hello", null, null);
		log.info(msTest);
	}

}
