package com.koreate.betty.domain.offline.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreate.betty.domain.book.service.BookService;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.domain.offline.service.OfflineService;
import com.koreate.betty.domain.rental.service.RentalService;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/offline")
public class OfflineController {
	
	@Autowired
	OfflineService os;
	
	@Autowired
	BookService bs;
	
	@Autowired
	RentalService rs;
	
	@GetMapping
	public String offline(HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("user");
		String userId = loginUser.getId();
		model.addAttribute("seats", os.seatStatus());	// List<Integer>
		model.addAttribute("reserves", rs.reserveByMemberId(userId));	// List<ReserveBook>
		model.addAttribute("rentals", rs.rentalByMemberId(userId));		// List<RentalBook>
		return "offline/offline";
	}
	
	@PostMapping("receipt")
	public String rentalRecept(rentalDto rsv) {
		String id = rsv.getId();
		String code = rsv.getCode();
		System.out.println("\n\n\n\n code : " + code);
		Integer num = bs.findExistNum(code);
		int result = rs.reserveCancle(id, code);
		result += rs.rentalBook(id, code, num);
		
		return "redirect:/offline";
	}
	
	
	@PostMapping("return")
	public String rentalReturn(rentalDto rental) {
		String id = rental.getId();
		String code = rental.getCode();
		
		int result = rs.returnBook(id, code);
		
		return "redirect:/offline";
	}
	
	@PostMapping("checkIn")
	public String checkIn(String id, Integer seat) {
		int result = os.checkIn(id, seat);
		return "redirect:/offline";
	}
	
	@PostMapping("checkOut")
	public String checkOut(String id) {
		int result = os.checkOut(id);
		return "redirect:/offline";
	}
	
	// Rental 가져올 곳 많으면 따로 클래스 만들어도됨
	@Data
	@NoArgsConstructor
	static class rentalDto{
		private String code;
		private String num;
		private String id;
	}
	
}
