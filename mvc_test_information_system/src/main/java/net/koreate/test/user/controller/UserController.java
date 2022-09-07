package net.koreate.test.user.controller;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.koreate.test.user.service.UserService;
import net.koreate.test.user.vo.UserVO;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService us;
	
	@GetMapping("/login")
	public String login() throws Exception{
		return "user/login";
	}
	
	@GetMapping("/join")
	public String join() throws Exception{
		return "user/join";
	}
	
	
	/**
	 * @see Request Login 
	 * @method POST
	 * @url /user/login
	 * @param userid userpw
	 * @return redirect:/ 
	 */
	
	@PostMapping("/login")
	public String login(String userid, String userpw, HttpSession session) throws Exception {
		UserVO vo = new UserVO();
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		
		UserVO user = us.login(vo);
		
		session.setAttribute("user", user);
		
		return "redirect:/";
	}
	
	
	
	/**
	 * @see Request Join
	 * @method POST
	 * @url /user/join
	 * @param userid / userpw / username / email
	 * @return redirect:/user/login
	 */
	
	@PostMapping("/join")
	public String join(String userid, String userpw, String username, String email) throws Exception {
		
		UserVO vo = new UserVO();
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		vo.setUsername(username);
		vo.setEmail(email);
		
		us.join(vo);
				
		return "redirect:/user/login";
	}
	

}
