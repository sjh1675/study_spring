package com.koreate.betty.domain.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.koreate.betty.domain.member.provider.MemberProvider;
import com.koreate.betty.domain.member.vo.ChkLog;
import com.koreate.betty.domain.member.vo.Member;

@Mapper
public interface MemberRepository {
	
	
	@SelectProvider(type=MemberProvider.class, method="findOne")
	public Member findOne(String id);
	
	@SelectProvider(type=MemberProvider.class, method="findAll")
	public List<Member> findAll();
	
	@SelectProvider(type=MemberProvider.class, method="findMyChkLog")
	public List<ChkLog> findMyChkLog(String loginId);
	
	@UpdateProvider(type=MemberProvider.class, method="update")
	public int update(Member member);
	
	@UpdateProvider(type=MemberProvider.class, method="updatePw")
	public int updatePw(@Param("id")String id, @Param("pw")String pw);

	@UpdateProvider(type=MemberProvider.class, method="delete")
	public int delete(String id);
	

	
	
}


// 안쓰게 될 것같은 코드 임시 이동
// 완성에 다다를 때 삭제

// @UpdateProvider(type=MemberProvider.class, method="changeImg")
// public int changeImg(@Param("id")String id, @Param("img")String img);