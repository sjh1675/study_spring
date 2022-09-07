package com.koreate.betty.global.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public String home(Locale locale, Model model) {
		log.info("locale = {}",locale);
		return "home";
	}
	
	@GetMapping("library")
	public String welcome() {
		return "library/library-welcome";
	}
	
}
