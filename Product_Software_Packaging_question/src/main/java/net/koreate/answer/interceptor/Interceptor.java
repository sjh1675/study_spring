package net.koreate.answer.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.koreate.user.vo.LoginDTO;
import net.koreate.user.vo.UserVO;

public class Interceptor implements HandlerInterceptor {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("userInfo");
		
		if (loginUser != null) {	// 로그인 성공
			LoginDTO dto = (LoginDTO)modelAndView.getModel().get("loginDTO");
			boolean checkCookie = dto.isUserCookie();
			if (checkCookie) {
				Cookie cookie = new Cookie("signInCookie", loginUser.getUid());
				cookie.setMaxAge(60*60*24*15);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		} 				
	}
}