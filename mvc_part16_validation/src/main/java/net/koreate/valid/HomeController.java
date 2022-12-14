package net.koreate.valid;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.koreate.valid.vo.ValidationMemberVO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class HomeController {

	private DefaultMessageService messageService;

	@PostConstruct
	public void init() {
		this.messageService = NurigoApp.INSTANCE.initialize("NCSIVPAPXF7035LM", "VL4XX7BJRAOTXN09EHX8NR7ECZX0GLML",
				"https://api.coolsms.co.kr");
	}

	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}

	@GetMapping("regex")
	public void regex() {

	}

	@GetMapping("/user/joinVal")
	public void joinVal() {
		
	}
	
	@GetMapping("user/join")
	public String join() {
		return "user/join";
	}

	@GetMapping("user/login")
	public String login() {
		return "user/login";
	}

	@GetMapping("user/uidCheck")
	@ResponseBody
	public boolean isCheck(String u_id) {
		boolean isCheck = false;

		if (u_id != null && !u_id.equals("jh1675s@naver.com")) {
			isCheck = true;
		}

		return isCheck;
	}

	@GetMapping("/checkEmail")
	@ResponseBody
	public String sendMail(@RequestParam("u_id") String email) throws Exception {
		System.out.println(email);
		String code = "";
		for (int i = 0; i < 5; i++) {
			code += (int) (Math.random() * 10);
		}

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setFrom("sjh1675@gmail.com");
		helper.setTo(email);
		helper.setSubject("????????? ?????? ??????");
		helper.setText("?????? ?????? ????????? ??????????????????. <h3>[" + code + "]</h3>", true);

		mailSender.send(message);
		System.out.println("?????? ??????");

		return code;
	}

	// ???????????? ?????? ?????? ????????? ??????
	@PostMapping("/sendSMS")
	@ResponseBody
	public Map<String, String> sendSMS(String userPhoneNumber) throws Exception {
		// code ??????
		String code = "";
		for (int i = 0; i < 5; i++) {
			code += (int) (Math.random() * 10);
		}

		Message message = new Message();
		// ???????????? ??? ??????????????? ????????? 01012345678 ????????? ??????????????? ?????????.
		message.setFrom("01095526908");
		// ????????? ?????? - ?????? ?????? ??????
		message.setTo(userPhoneNumber);
		message.setText("???????????? ????????? ?????????["+code+"]????????? ?????? ??? ??????!");

		SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
		System.out.println(response);
		Map<String, String> map = new HashMap<>();
		map.put("code", code);
		map.put("result", response.getStatusCode());
		return map;
	}
	
	@Autowired
	ServletContext context;
	
	@PostMapping("/user/joinPost")
	public String joinPost(ValidationMemberVO vo, MultipartFile profileImage) throws Exception {
		System.out.println(vo);
		System.out.println(profileImage.isEmpty());
		System.out.println(profileImage.getOriginalFilename());
		System.out.println(profileImage.getContentType());
		System.out.println(profileImage.getName());
		if (!profileImage.isEmpty()) {
			// src/main/webapp/upload/profile/u_id/image
			String path = "upload" + File.separator
					+ "profile" + File.separator
					+ vo.getU_id();
			
			String realPath = context.getRealPath(path);
			File file = new File(realPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(realPath, profileImage.getOriginalFilename());
			profileImage.transferTo(file);
			String u_profile = path + File.separator + profileImage.getOriginalFilename();
			vo.setU_profile(u_profile);
		}
		System.out.println(vo);
		return "home";
	}
}
