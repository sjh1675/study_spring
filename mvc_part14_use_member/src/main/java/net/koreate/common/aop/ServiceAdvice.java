package net.koreate.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect // Advice 클래스임을 알수 있게 함
@Slf4j	// 편하게 로그를 찍을 수 있는 롬복 어노테이션
public class ServiceAdvice {
	
	@Around("execution(* net.koreate.*.service.*.*(..))")
	public Object checkService(ProceedingJoinPoint pjp) throws Throwable {
		// 전처리
		log.info("---- around ServiceAdvice START");
		log.info("---- target : " + pjp.getTarget());
		log.info("---- method : " + pjp.getSignature().getName());
		log.info("---- params : " + Arrays.toString(pjp.getArgs()));
		
		Object o = pjp.proceed();
		// 후처리
		log.info("---- return value : " + o);
		
		log.info("---- around ServiceAdvice END");
		return o;
	}
}