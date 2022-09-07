package net.koreate.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.koreate.dao.MessageDAO;
import net.koreate.vo.MessageVO;

@Component
@Aspect
@Slf4j
public class AOPAdvice {
	
	public AOPAdvice() {
		System.out.println("AOPAdvice 생성");
		log.info("logger AOP advice");
	}
		
	// 각 AOP 어노테이션이 타겟의 패턴을 지정하여 매치시켜주고, proxy 패턴으로 객체가 생성됨
	@Before("execution(* net.koreate.service.*.*(..))") // net.koreate.service.* 까지가 수행하는 객체임으로, Target을 의미하고 이 객체 안의 메소드는 JoinPoints가 된다
														// net.koreate.service.*.*(..) 까지가 내부의 메소드 들이므로 PointCuts을 의미함
	public void startLog(JoinPoint jp)throws RuntimeException{
		log.info("------------------------------");
		log.info("-------START LOG-------");
		// net.koreate.service.MessageService
		log.info("target : " + jp.getTarget());
		// method-execution
		log.info("type : " + jp.getKind());
		// 실행 된 메소드 이름
		log.info("name : " + jp.getSignature().getName());
		Object[] objs = jp.getArgs();
		log.info("parameters : " + Arrays.toString(objs));
		SimpleDateFormat sdf
			= new SimpleDateFormat("HH:mm:ss");
		log.info("start time : "+sdf.format(new Date()));
		log.info("------ END START LOG -------");
	}
	
	@After("execution(* net.koreate.service.MessageService.*(..))")
	public void endLog(JoinPoint jp) throws RuntimeException{
		log.info("------ END LOG -------");
			SimpleDateFormat sdf
			= new SimpleDateFormat("HH:mm:ss");
		log.info("start time : "+sdf.format(new Date()));
		log.info("------ END LOG END-------");
	}
	
	@AfterReturning(pointcut="execution(!void net.koreate.service.MessageService.*(..))", 	// 반환값이 있는 모든 메소드의 종료 시에 시행
			returning = "returnValue")														// 메소드의 반환값을 받을 수 있다 (이름이 일치해야한다 > returnValue)
	public void returningTest(JoinPoint jp, Object returnValue) throws Exception { // 밑의 Around 와 달리 호출 시점이 정해져있음
		log.info("------------------------------");
		log.info("-------START AfterReturning LOG-------");
		// net.koreate.service.MessageService
		
		log.info("target : " + jp.getTarget());
		// method-execution
		log.info("type : " + jp.getKind());
		// 실행 된 메소드 이름
		log.info("name : " + jp.getSignature().getName());
		log.info("returnValue : " + returnValue);
		Object[] objs = jp.getArgs();
		log.info("parameters : " + Arrays.toString(objs));
		SimpleDateFormat sdf
			= new SimpleDateFormat("HH:mm:ss");
		log.info("start time : "+sdf.format(new Date()));
		log.info("-------END AfterReturning LOG-------");
	}
	
	
	@Around("execution(* net.koreate.controller.MessageController.*(..))")
	public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable {	// 매개변수 ProceedingJoinPoint는 호출 시점을 제어할 수 있음
		log.info("--------------------------------------");
		
		log.info("-------Around Controller Start-------");
		long startTime = System.currentTimeMillis();
		log.info("Around target : " + pjp.getTarget());
		log.info("Around name : " + pjp.getSignature().getName());
		log.info("Around parameters : " + Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		
		if (o != null) {
			log.info("Around Object : " + o);
			log.info("Around Object : " + o.toString());
		}
		
		long endTime = System.currentTimeMillis();
		log.info("work time : " + (endTime - startTime));
		log.info("-------Around Controller End--------");
		
		return o;
	}
	
	
	@Autowired
	private MessageDAO dao;
	
	@Around(value="execution(net.koreate.vo.MessageVO net.koreate.service.MessageService.readMessage(String, int)) && args(uid, mno)")
	public Object readMessageCheck(ProceedingJoinPoint pjp, String uid, int mno) throws Throwable {
		log.info("---- around readMessage START ----");
		
		Object[] args = pjp.getArgs();
		log.info("args : " + args);
				
		MessageVO messageVO = dao.readMessage(mno);
		log.info(messageVO.toString());
		
		if (messageVO.getOpendate() != null) {
			log.info("---- throw readMessage Around END ----");
			throw new NullPointerException("이미 읽은 메세지입니다. AROUND");
		}
		
		Object o = pjp.proceed();
		
		if (o != null && o instanceof MessageVO) {
			messageVO = (MessageVO)o;
			log.info("readMessage Around : " + messageVO);
		}
		
		log.info("---- readmessage Around END ----");
		return null;
	}
	
	@AfterThrowing(value="execution(* net.koreate.service.*.*(..))", throwing = "exception")
	public void endThrowingLog(JoinPoint jp, Exception exception) {
		log.info("---------------------------------------");
		log.info("-------- START AFTER THROWING ---------");
		
		log.info("target : " + jp.getTarget());
		log.info("name : " + jp.getSignature().getName());
		log.info("error : " + exception.getMessage());
		log.info("");
		log.info("--------- END AFTER THROWING ----------");
	}
	
}








