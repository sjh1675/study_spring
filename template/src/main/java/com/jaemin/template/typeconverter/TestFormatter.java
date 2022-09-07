package com.jaemin.template.typeconverter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFormatter implements Formatter<Number> {

	@Override
	public Number parse(String text, Locale locale) throws ParseException {
		log.info("test={}, locale={}", text, locale);
		NumberFormat format = NumberFormat.getInstance(locale);
		return format.parse(text);
	}

	@Override
	public String print(Number object, Locale locale) {
		log.info("object={}, locale={}", object, locale);
		return NumberFormat.getInstance(locale).format(object);
	}
}
