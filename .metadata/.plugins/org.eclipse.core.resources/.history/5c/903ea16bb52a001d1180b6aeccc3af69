package com.koreate.betty.domain.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.koreate.betty.domain.member.dto.MemberForAdminDTO;
import com.koreate.betty.domain.member.dto.form.AdminSearchForm;
import com.koreate.betty.domain.member.provider.MemberProvider;
import com.koreate.betty.domain.member.vo.ChkLog;
import com.koreate.betty.domain.member.vo.Inquiry;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.global.util.Criteria;

@Mapper
public interface MemberRepository {
	
	
	@SelectProvider(type=MemberProvider.class, method="findOne")
	public Member findOne(String id);
	
	@SelectProvider(type=MemberProvider.class, method="findAll")
	public List<Member> findAll();
	
	@SelectProvider(type=MemberProvider.class, method="findMyChkLog")
	public List<ChkLog> findMyChkLog(String loginId);
	
	@SelectProvider(type=MemberProvider.class, method="findMemberForAdmin")
	public List<MemberForAdminDTO> findMemberForAdmin(@Param("form")AdminSearchForm form, @Param("cri")Criteria cri);
	
	@SelectProvider(type=MemberProvider.class, method="findMemberForAdminCount")
	public int findMemberForAdminCount(AdminSearchForm form);
	
	@SelectProvider(type=MemberProvider.class, method="blackCheckById")
	public int blackCheckById(String id);
	
	@UpdateProvider(type=MemberProvider.class, method="update")
	public int update(Member member);
	
	@UpdateProvider(type=MemberProvider.class, method="updatePw")
	public int updatePw(@Param("id")String id, @Param("pw")String pw);

	@UpdateProvider(type=MemberProvider.class, method="delete")
	public int delete(String id);

	

	
	
	@InsertProvider(type=MemberProvider.class, method="createInquiry")
	@Options(useGeneratedKeys = true , keyProperty = "bno")
	public int createInquiry(Inquiry inquiry);
	
}


// 안쓰게 될 것같은 코드 임시 이동
// 완성에 다다를 때 삭제

// @UpdateProvider(type=MemberProvider.class, method="changeImg")
// public int changeImg(@Param("id")String id, @Param("img")String img);