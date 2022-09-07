package net.koreate.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.vo.SampleVO;

@Controller
public class SampleController {
	
	@GetMapping("/testJSON")
	public String toJSON(Model model) {
		model.addAttribute("Hello PJS");
		return "JSON";
	}
	
	@GetMapping("getSample")
	@ResponseBody	// 전달한 값이 JSP가 아닌 데이터임을 명시하는 어노테이션
	public SampleVO toJSON(SampleVO sample) {		
		System.out.println("getSample : " + sample);
		return sample;	// 뷰리졸버에서 문자열을 받지 객체를 받지않음
						// 객체를 넘기게 되면 @ResponseBody 어노테이션과 함께 JSON으로 변환하여 데이터를 전달한다
	}
	
	@GetMapping(value="getSampleList", produces="application/json")
	// consumes : 어떤 형태로 받을건지 produces : 어떤 형태로 보여줄 것인지
	@ResponseBody
	public List<SampleVO> getSampleList(){
		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setName("PJS" + i);
			vo.setAge(i);
			list.add(vo);
		}
		return list;
	}
	
	
	@PostMapping("getSample")
	@ResponseBody
	public List<SampleVO> listSample(SampleVO vo){
		List<SampleVO> list = new ArrayList<>();
		
		list.add(vo);
		for (int i = 1; i < 10; i++) {
			SampleVO add = new SampleVO();
			add.setName(vo.getName()+i);
			add.setAge(vo.getAge()+i);
			list.add(add);
		}		
		return list;
	}
	
	@PutMapping("testPUT")
	@ResponseBody
	public SampleVO testPUT(@RequestBody SampleVO vo) {	// 단순 파라미터라면 vo를 자동으로 값을 대입하여 생성해주지만
														// 넘기는게 JSON 타입이기 때문에 JSON 타입을 객체로 입력받기 위해 @RequestBody
		System.out.println("testPUT : " + vo);
		return vo;
	}
}