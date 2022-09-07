package net.koreate.filter_test;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {				
		return "home";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public void write() {
		
	}
	
	// 요청 시 스프링이 member 객체를 기본 생성자로 만들고 넘겨받은 파라미터와 일치한 변수명에 값을 채운 뒤 write(request, member)를 호출한다 
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, MemberVO member) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		System.out.println(member);
		return "redirect:/";
	}
}
