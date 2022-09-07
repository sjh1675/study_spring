package com.jaemin.template.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TestAdvice {

	@Around ("execution(* com.jaemin.template.*.*.*(..))")
	public Object checkService(ProceedingJoinPoint pjp) throws Throwable {
		log.info("===== around ServiceAdvice START =====");
		long start = System.currentTimeMillis();
		log.info("--- target : " + pjp.getTarget());
		log.info("--- method : " + pjp.getSignature().getName());
		log.info("--- params : " + Arrays.toString(pjp.getArgs()));
		try {
			return pjp.proceed();
		} finally {
			long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+ pjp + " " + timeMs + "ms");
			log.info("===== around ServiceAdvice END =====");
		}
	}
}
