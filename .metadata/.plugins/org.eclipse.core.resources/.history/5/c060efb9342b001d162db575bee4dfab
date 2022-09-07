package com.koreate.betty.domain.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreate.betty.domain.member.dao.MemberRepository;
import com.koreate.betty.domain.member.dto.MemberForAdminDTO;
import com.koreate.betty.domain.member.dto.form.AdminSearchForm;
import com.koreate.betty.global.util.Criteria;
import com.koreate.betty.global.util.PageMaker;

@Service
public class AdminService {

	@Autowired
	MemberRepository memberRepository;
	
	public Map<String, Object> memberList(AdminSearchForm form){
		Map<String, Object> map = new HashMap<>();
		
		Criteria cri = new Criteria();
		cri.setPage(form.getPage());
		int totalCount = memberRepository.findMemberForAdminCount(form);
		PageMaker pm = new PageMaker(cri, totalCount);
		
		List<MemberForAdminDTO> oldList = memberRepository.findMemberForAdmin(form, cri);
		
		map.put("oldList", oldList);
		map.put("pm", pm);
		return map;
	}
	
	public boolean blackCheckById(String id) {
		return memberRepository.blackCheckById(id) == 1 ? true : false;
	}
	
}
