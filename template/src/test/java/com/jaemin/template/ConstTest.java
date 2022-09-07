package com.jaemin.template;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.jaemin.template.util.TestConst;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConstTest {
	
	@Test
	public void constTest() {
		String str = "LOGIN_MEMBER";
		
		log.info("str = {}", str.hashCode());
		log.info("TestConst.LOGIN_MEMBER = {}", TestConst.LOGIN_MEMBER.hashCode());
		log.info("TestEnum.LOGIN_MEMBER = {}",TestEnum.LOGIN_MEMBER.hashCode());
	}

	static enum TestEnum {
		LOGIN_MEMBER;
	}
}

