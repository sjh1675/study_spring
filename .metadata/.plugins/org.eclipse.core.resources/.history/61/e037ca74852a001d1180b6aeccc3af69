package com.koreate.betty.domain.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreate.betty.domain.member.dto.form.UpdateForm;
import com.koreate.betty.domain.member.service.MemberService;
import com.koreate.betty.domain.member.vo.ChkLog;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.domain.model.SessionConst;
import com.koreate.betty.domain.rental.service.RentalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members/{id}")
public class MemberController {
	
	private final MemberService memberService;
	private final RentalService rentalService;

	@GetMapping
	public String memberInfo() { return "member/member-info"; }
	
	@GetMapping("edit")
	public String memberEdit() { return "member/member-edit"; }

	@GetMapping("charge")
	public String memberCharge() { return "member/member-charge"; }

	@GetMapping("rentals")
	public String memberRendal() { return "member/member-rental-list"; }
	
	@GetMapping("dashboard")
	public String memberDashboard(@PathVariable String id, Model model) {
		// id, checklog, rentallog, reservelog 

		model.addAttribute("memberCard", memberService.findGradeById(id));
		model.addAttribute("chkList", memberService.findMyChkLog(id));
		// 회원 대여 정보 : book provider의 rental 참조 (현재 미구현)
		model.addAttribute("rentalList", rentalService.rentalByMemberId(id));
		// 회원 예약 정보 : book provider의 reserve 참조 (현재 미구현)
		model.addAttribute("reserveList", rentalService.reserveByMemberId(id));
		return "member/member-dashboard";
	}

	@PostMapping("membership")
	public String registerMembership(@PathVariable String memberId, String id, String membershipGrade) {
		int result = memberService.updateMembership(id, membershipGrade);
		return "redirect:/members/" + memberId;
	}
	
	@PostMapping("edit")
	public String memberEdit(@PathVariable String id, UpdateForm form, HttpSession session, RedirectAttributes rttr) {
		form.encode();
		Member user = memberService.updateMember(form);
		if(user != null) {
			session.setAttribute(SessionConst.USER, user);
			rttr.addFlashAttribute("message","요청 성공");
			return "redirect:/members/"+user.getId();
		}
		rttr.addFlashAttribute("message","요청 실패");
		return "member/member-info";
	}

}
