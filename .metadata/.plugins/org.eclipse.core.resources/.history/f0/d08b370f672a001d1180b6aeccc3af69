package com.koreate.betty.domain.member.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static com.koreate.betty.domain.model.TableConst.*;

public class MemberCardProvider {

	// 멤버십 가입
	public String membershipJoin(String memberId, String grade) {
		return new SQL().INSERT_INTO(MEMBER_CARD_TBL)
				.INTO_COLUMNS("member_id","premium_grade")
				.INTO_VALUES("#{memberId}, #{grade}")
				.toString();
	}
	
	// 벌점 갱신
	public String demerit(String id, int demerit) {
		return new SQL().UPDATE(MEMBER_CARD_TBL)
				.SET("demerit = demerit + #{demerit}")
				.WHERE("member_id = #{id}")
				.toString();
	}
	
	public String findOne(String id) {
		return new SQL().SELECT("*").FROM(MEMBER_CARD_TBL).WHERE("member_id = #{id}").toString();
		
	}
	
	// 멤버십 갱신 / 등급 변경(?)
	
	
	// 포인트 충전
	public String updatePoint(@Param("id")String id, @Param("point")int point) {
		return new SQL().UPDATE(MEMBER_CARD_TBL)
				.SET("point = point + #{point}")
				.WHERE("member_id = #{id}")
				.toString();
	}
	
	public String updateGrade(@Param("id")String id, @Param("msGrade")String msGrade) {		
		return new SQL().UPDATE(MEMBER_CARD_TBL)
				.SET("premium_grade = #{msGrade}")
				.WHERE("member_id = #{id}")
				.toString();
	}
}