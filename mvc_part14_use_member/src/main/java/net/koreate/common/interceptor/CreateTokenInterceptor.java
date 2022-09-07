package net.koreate.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CreateTokenInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String token = UUID.randomUUID().toString();	// 게시물 상세보기 페이지 들어갈 때 마다 랜덤한 토큰이 발급됨
		
		System.out.println("게시물 상세보기 요청 token : " + token);
		session.setAttribute("csrf_token", token);
		return true;	// 리턴값이 true면 컨트롤러로 전달
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
