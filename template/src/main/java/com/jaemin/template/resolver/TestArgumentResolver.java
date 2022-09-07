package com.jaemin.template.resolver;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.core.MethodParameter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.jaemin.template.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("**Call** class = {}, method = {}",getClass().getName(),"supportsParameter");
		
		boolean hasTestAnnotation = parameter.hasParameterAnnotation(Checker.class);
		boolean hasMemberType = TestVO.class.isAssignableFrom(parameter.getParameterType());
		
		return hasTestAnnotation && hasMemberType;
	}
	

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		log.info("**Call** class = {}, method = {}",getClass().getName(),"resolveArgument");
		
		webRequest.getParameterNames().forEachRemaining(obj -> log.info("parameter = {}",obj));
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		request.getParameterMap().values().stream().forEach(obj -> log.info("request Value = {} ", obj));
		TestVO vo = new TestVO();
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		return vo;
	}

}
