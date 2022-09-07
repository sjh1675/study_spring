package com.jaemin.template.aop;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class TestAdvisor {

	@PostConstruct
	public void init() {
		log.info("===== TestAdvisor initialized -=-=-=-=-");
	}
	
	//@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler // (NoHandlerFoundException.class)
	public ModelAndView errorHandle(Exception ex) {
		log.info("**Call** class = {}, method = {}",getClass().getName(),"errorHandle");
		log.error("getMessage = {}",ex.getMessage());
		log.info("==========info error vs stackTrace");
		ex.printStackTrace();
		return new ModelAndView("hello");
	}
	
}
