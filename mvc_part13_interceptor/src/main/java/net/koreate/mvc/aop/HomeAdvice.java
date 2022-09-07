package net.koreate.mvc.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect		// 공통적 기능을 정의함을 명시하는 클래스
public class HomeAdvice {

	@Around(value="execution(* net.koreate.mvc.controller.*.*(..))")
	public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("--- AROUND START CONTROLLER ---");
		System.out.println("target : " + pjp.getTarget());
		System.out.println("name : " + pjp.getSignature().getName());
		System.out.println("args : " + Arrays.toString(pjp.getArgs()));
		
		Object o = pjp.proceed();
		
		if (o != null) {
			System.out.println("arround : " + o);
		}
		
		System.out.println("--- AROUND END CONTROLLER ---");
		return o;
	}
	
}