package com.koreate.betty.domain.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import com.koreate.betty.domain.book.service.BookService;
import com.koreate.betty.domain.member.dto.form.PointForm;
import com.koreate.betty.domain.member.dto.form.SignInForm;
import com.koreate.betty.domain.member.dto.form.SignUpForm;
import com.koreate.betty.domain.member.dto.form.UpdateForm;
import com.koreate.betty.domain.member.service.MemberService;
import com.koreate.betty.domain.member.service.SignService;
import com.koreate.betty.domain.member.vo.ChkLog;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.global.config.AppConfig;
import com.koreate.betty.global.config.RootConfig;
import com.koreate.betty.global.config.WebConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { WebConfig.class, AppConfig.class, RootConfig.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberTest {

	@Autowired
	MemberService memberService;
	@Autowired
	SignService signService;
	BookService bs;
	
//	@Test
	public void joinOne() {
		SignUpForm form = new SignUpForm();
		form.setId("namu6751");
		form.setPw("namu6751");
		form.setRepw("namu6751");
		form.setNickname("namu6751");
		form.setName("namu6751");
		form.setGender("namu6751");
		form.setAddr("namu6751");
		form.setBirth(new Date(0));
		form.setPhone("010_0000_0000");
		form.setRights(3);
		form.setEmail("namu6751@naver.com");
		log.info("count = {} , form = {}",form);
		signService.signUp(form);
	}
	
//	@Test 
	public void joinTest() {
		IntStream.rangeClosed(1, 10).forEach(
					i -> {
						SignUpForm form = new SignUpForm();
						form.setId("id00"+i);
						form.setPw("pw00"+i);
						form.setRepw("pw00"+i);
						form.setNickname("nick00"+i);
						form.setName("name00"+i);
						String gender = i % 2 == 0 ? "man" : "woman";
						form.setGender(gender);
						form.setAddr("addr00"+i);
						form.setBirth(new Date());
						form.setPhone("0100000000"+i);
						int count = i <= 3 ? i : 0;
						form.setRights(count);
						form.setEmail("email"+i+"@naver.com");
						log.info("count = {} , form = {}",i,form);
						signService.signUp(form);
					}
				);
	}
	
//	@Test // 완료
	public void loginTest() {
		SignInForm form = new SignInForm("1", "1", true);
		Member loginMember = signService.signIn(form);
		log.info("\n\n\n ::: loginTest Member : {}", loginMember);
	}
	
//	@Test // 완료
	public void findIdTest() {
		String name = "2";
		String phone = "1";
		String id = signService.forgetId(name, phone);
		
		log.info("\n\n\n ::: findIdTest id : {}", id);
	}

//	@Test // 완료
	public void findForChangePwTest() {
		String id = "123";
		String phone = "2";
		
		String result = signService.forgetPw(id, phone);
		
		log.info("\n\n\n ::: confirmForChangePwTest : {}", result);
	}
	
//	@Test // 완료
	public void changePwTest() {
		String id = "123";
		String pw = "3";
		
		int result = memberService.changePw(id, pw);
		
		log.info("\n\n\n ::: changePwTest : {}", result);
	}
	
//	@Test // 완료
	public void findAllTest() {
		List<Member> list = memberService.findAll();
		for (Member m : list) {
			log.info("\n ::: findAllTest member : {}", m);
		}
	}
	
//	findOne 메소드 생략
	
//	@Test // 완료
	public void changeImgTest() {
		String id = "123";
		String img = "profile2.jpg";
		
//		int result = signService.changeImg(id, img);	// 현재 서비스도 삭제됨
		
//		log.info("\n\n\n ::: changeImg result : {}", result);
	}
	
	
	
	
//	@Test // 완료
	public void deleteMemberTest() {
		String id = "123";
		
		int result = memberService.deleteMember(id);
		log.info("\n\n\n ::: deleteMemberTest result : {}", result);
	}
	
	
//	@Test
	public void addPointTest() {
		PointForm form = new PointForm("123", "1-1-1", 10000);
		
		int result = memberService.addPoint(form);
		
		log.info("\n\n\n\n result : {}", result);
	}	
	
//	@Test
	public void findMyChkLogTest() {
		
		String id = "123";
		
		List<ChkLog> list = memberService.findMyChkLog(id);
		
		for (ChkLog c : list) {
			log.info("\n\n chk : {}", c);
		}		
	}
	
//	@Test
	public void updateMembershipTest() {
		String id = "1224";
		String grade = "bronze";
		
		int result = memberService.updateMembership(id, grade);
		log.info("\n\n\n\n result : {}", result);
	}
}
