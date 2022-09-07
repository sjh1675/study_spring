package net.koreate.board.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import net.koreate.board.dao.BoardDAO;

public class Calculator {

	@Inject
	BoardDAO dao;
	
	public Calculator() {
		System.out.println("Calculator 생성");
		System.out.println(dao); // 객체 조차 없으므로 호출이 불가능함
	}
	
	public int minus(int a, int b) {
		return a - b;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("객체 생성 후 주입되어 bean을 사용할 준비가 되었을 때 호출");
		System.out.println(dao);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Calculator destroy");
		// 객체가 소멸 되기 직전에 호출
	}
}
