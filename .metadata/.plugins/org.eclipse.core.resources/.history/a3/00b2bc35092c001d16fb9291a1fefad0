package com.koreate.betty.domain.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreate.betty.domain.member.dto.MemberForAdminDTO;
import com.koreate.betty.domain.member.dto.form.AdminSearchForm;
import com.koreate.betty.domain.member.service.AdminService;
import com.koreate.betty.domain.member.service.MemberService;
import com.koreate.betty.domain.offline.service.OfflineService;
import com.koreate.betty.domain.rental.service.RentalService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService as;

	@Autowired
	OfflineService os;

	@Autowired
	RentalService rs;

	@Autowired
	MemberService ms;

	@GetMapping
	public String adminMain() {
		return "admin/admin-main";
	}

	@GetMapping("profit")
	public String adminProfit(Model model) {
		model.addAttribute("profit", as.findTotalProfit());
		return "admin/admin-profit";
	}

	@GetMapping("members")
	public String memberListOfAdmin() {
		return "admin/admin-members";
	}

	@GetMapping("members/cond")
	@ResponseBody
	public Map<String, Object> memberSearchList(AdminSearchForm form) {

		Map<String, Object> map = as.memberList(form);

		List<MemberForAdminDTO> oldList = (List<MemberForAdminDTO>) map.get("oldList");
		List<MemberForAdminDTO> list = new ArrayList<>();
		if (oldList != null) {

			for (MemberForAdminDTO m : oldList) {
				String id = m.getId();
				// 현재 대여 권수
				Integer nowRent = rs.rentalBookCountById(id);
				m.setNowRentCount(nowRent);
				// 총 대여 횟수
				Integer allRent = rs.rentalBookLogCountById(id) + nowRent;
				m.setAllRentCount(allRent);
				// 체크인 여부
				m.setCheckIn(os.checkMember(id));
				// 블랙 여부
				m.setBlack(as.blackCheckById(id));

				list.add(m);
			}
		}
		map.put("list", list);

		return map;
	}
	
	@PostMapping("members/allow")
	@ResponseBody
	public String updateAllow(String id) {
		int result = as.updateAllow(id);
		
		return result == 1 ? "Success" : "Failed";
	}

}