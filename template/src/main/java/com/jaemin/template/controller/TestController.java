package com.jaemin.template.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.jaemin.template.resolver.Checker;
import com.jaemin.template.service.TestService;
import com.jaemin.template.vo.TestVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@PropertySource("classpath:/prop/file.properties")
public class TestController {
	
	@Autowired
	TestService ts;
	
	@Value("${file.dir}")
	String path;
	
	@PostConstruct
	public void tempTest() {
		log.info("****************************************");
		log.info("file.path = {}", path);
		log.info("****************************************");
	}
	
	private final TestService service;
	
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@PostMapping("/add")
	public String test(@Checker @Valid @ModelAttribute TestVO vo, BindingResult bindingResult ) { //@Checker  
		log.info("+_+_+_=-=_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
		log.info("START ===== Controller VO = {}",vo);
		log.info("TestVO = {}", vo);
		if(bindingResult.hasErrors()) {
			log.info("error detected");
			return "hello";
		}
		service.test(vo);
		log.info("END ===== Controller =====");
		return "redirect:/";
	}
	
	
	@GetMapping("/ex")
	public String exception() {
		throw new RuntimeException("Test Exception");
	}
	
}
