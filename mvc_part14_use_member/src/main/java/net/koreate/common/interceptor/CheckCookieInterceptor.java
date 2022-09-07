package net.koreate.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;
import net.koreate.common.session.MySessionEventListener;
import net.koreate.user.service.UserService;
import net.koreate.user.vo.UserVO;

@Slf4j
public class CheckCookieInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService us;
	
	@Autowired
	private MySessionEventListener mel;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("---check Cookie 시작");
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfo") != null) {
			log.info("이미 로그인 상태");
			return true;
		}
		
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie c : cookies) {
//				if (c.getName().equals("signInCookie")) {
//					String uid = c.getValue();
//					UserVO vo = us.getUserById(uid);
//					if (vo != null) {
//						session.setAttribute("userInfo", vo);						
//					}
//					return true;
//				}
//			}	
//		}
		
		Cookie cookie = WebUtils.getCookie(request, "signInCookie");	// request의 쿠키 배열을 얻어서 이름이 일치하는 쿠키 정보를 찾아준다
		if (cookie != null) {
			String uid = cookie.getValue();
			UserVO vo = us.getUserById(uid);
			if (vo != null) {
				boolean result = mel.expireDuplicatedSession(uid, request.getSession().getId());
				session.setAttribute("userInfo", vo);						
			}
		}
		
		
		log.info("---check Cookie 끝");
		return true;
	}
	
}
