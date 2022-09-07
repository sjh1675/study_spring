package com.koreate.betty.domain.member.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.domain.model.CookieConst;
import com.koreate.betty.domain.model.SessionConst;

public class SignHelper {
	
	public static Cookie createSignInCookie(String id){
		String encodedId = Base64Util.encode(id);
		Cookie cookie = new Cookie(CookieConst.COOKIE_USER, encodedId);
		cookie.setMaxAge(24 * 60 * 60);
		cookie.setPath("/");
		return cookie;
	}
	
	
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Member user = (Member) session.getAttribute(SessionConst.USER);
		session.invalidate();
		
		Cookie findCookie = WebUtils.getCookie(request, CookieConst.COOKIE_USER);
		if (findCookie != null) {
			Cookie cookie = deleteSignInCookie(user.getId());
			response.addCookie(cookie);
		}
	}
	
	public static String makeCodeForSMS(HttpSession session) {
		String code = generateForgetCode();
		session.setAttribute(SessionConst.EMAIL_CODE, code);
		session.setMaxInactiveInterval(180);
		return code;
	}
	public static String makeCodeForEmail(HttpSession session) {
		String code = generateForgetCode();
		session.setAttribute(SessionConst.SMS_CODE, code);
		session.setMaxInactiveInterval(180);
		return code;
	}
	
	private static Cookie deleteSignInCookie(String id) {
		String encodedId = Base64Util.encode(id);
		Cookie cookie = new Cookie(CookieConst.COOKIE_USER, encodedId);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		return cookie;
	}
	
	private static String generateForgetCode() {
		String code = "";
		for (int i = 0; i < 5; i++) {
			code += (int) (Math.random() * 10);
		}
		return code;
	}
}
