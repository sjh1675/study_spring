package com.koreate.betty.domain.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.koreate.betty.domain.member.exception.MessageException;
import com.koreate.betty.domain.member.exception.NotFoundIdException;
import com.koreate.betty.domain.member.service.MemberService;
import com.koreate.betty.domain.member.service.SignService;
import com.koreate.betty.domain.member.util.Base64Util;
import com.koreate.betty.domain.member.vo.Inquiry;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.domain.model.CookieConst;
import com.koreate.betty.domain.model.SessionConst;
import com.koreate.betty.global.error.ErrorResult;
import com.koreate.betty.infra.email.EmailSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{id}")
public class MemberApiController {
	
	private final EmailSender emailSender;
	private final MemberService memberService;
	private final SignService SignService;
	
	@PostMapping("inquiry")
	public boolean memberSendMail(@PathVariable String id, Inquiry inquiry) {
		if (inquiry == null) {
			throw new RuntimeException("요청 오류");
		}
		emailSender.inquiry(inquiry);
		return true;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public ErrorResult mailHandle(MessageException ex) {
		log.error("error = {}",ex.getMessage());
		return new ErrorResult(HttpStatus.BAD_GATEWAY.value(), "메시지 전송 실패");
	}
	
	@DeleteMapping
	public Map<String,String> deleteMember(String id, @PathVariable("id") String userId, HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		log.info("id = {}, userId = {}",id,userId);
		if(userId.equals(id)) {
			result = memberService.deleteMember(id); 
			HttpSession session = request.getSession(false);
			Member user = (Member) session.getAttribute(SessionConst.USER);
			session.invalidate();
			Cookie findCookie = WebUtils.getCookie(request, CookieConst.COOKIE_USER);
			if (findCookie != null) {
				String encodedId = Base64Util.encode(user.getId());
				Cookie cookie = new Cookie(CookieConst.COOKIE_USER, encodedId);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			Map<String, String> map = new HashMap<>();
			map.put("code","success");
			return map;
		}
		throw new NotFoundIdException();
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult mailHandle(NotFoundIdException ex) {
		log.error("error = {}",ex.getMessage());
		return new ErrorResult(HttpStatus.BAD_REQUEST.value(), "아이디 검색 실패");
	}
	
	
	
}
