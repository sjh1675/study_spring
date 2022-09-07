package com.jaemin.template.typeconverter;

import org.springframework.core.convert.converter.Converter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestConverter implements Converter<String, String>{

	@Override
	public String convert(String source) {
		log.info("**Call** class = {}, method = {}",getClass().getName(),"convert");
		return source;
	}

	

}
