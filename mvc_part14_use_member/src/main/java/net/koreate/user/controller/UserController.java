package net.koreate.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.koreate.user.service.UserService;
import net.koreate.user.vo.UserVO;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

	private final UserService us;
	
	@RequestMapping("/signIn")
	public String sighIn() {		
		return "user/signIn";
	}
	
	@RequestMapping("/signUp")
	public String signUp () {
		return "user/signUp";
	}
	
	@PostMapping("/signUpPost")
	public String signUpPost(UserVO vo) throws Exception {
		us.signUp(vo);
		return "redirect:/user/signIn";
	}
	
	@PostMapping("/signInPost")
	public String signInPost(UserVO vo, 
			// HttpSession session
			Model model
			) throws Exception {
		model.addAttribute("userInfo", us.signIn(vo));
		return "redirect:/";
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpSession session, HttpServletResponse response, @CookieValue(name="signInCookie", required = false) Cookie signInCookie) { // 디스패처서블릿이 어노테이션과 함께 쿠키정보를 검색해서 전달해줌
		if (session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			session.removeAttribute("invalidate");
			System.out.println("로그아웃 완료");
		}
		
		if (signInCookie != null) {			
			signInCookie.setMaxAge(0);
			signInCookie.setPath("/");
			response.addCookie(signInCookie);
		}
		
		return "redirect:/";
	}	
}
