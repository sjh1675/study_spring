package net.koreate.rest;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import net.koreate.vo.SampleVO;

@Controller
public class HomeController {
		
	@GetMapping("/")
	public String home(Locale locale, Model model) {	
		return "home";
	}
	
	@GetMapping("resData")
	public void resData() {}

	@GetMapping("javascript")
	public void javascript() {}
	
	@GetMapping("ajaxTest")
	public void ajaxTest() {}
	
	@GetMapping("testXml")
	public String testXml() {
		return "xmlTest";
	}
	
	// GET, POST, PUT, PATCH, DELETE
	
	@GetMapping("methodTest")
	public void methodTest() {	
		
	}

	@PostMapping("methodTest")
	public void methodTest(SampleVO vo) {
		System.out.println("POST");
		System.out.println(vo);
	}
	
//	@RequestMapping(value="methodTest", method=RequestMethod.PUT)
	@PutMapping("methodTest")
	public String methodTestPUT(SampleVO vo, Model model) {
		System.out.println("PUT");
		System.out.println(vo);
		model.addAttribute(vo); // 데이터를 전달할 model 선언과 전송
		return "JSON";	// 반환값이 빈 이름이랑 같은 것을 BeanNameViewResolver가 찾음
	}
}
