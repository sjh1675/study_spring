package com.koreate.betty.domain.member.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static com.koreate.betty.domain.model.TableConst.*;

public class MemberCardProvider {
		
	public String findOne(String id) {
		return new SQL().SELECT("*").FROM(MEMBER_CARD_TBL).WHERE("member_id = #{id}").toString();
	}
	
	// 벌점 갱신
	public String demerit(@Param("id")String id, @Param("demerit")Integer demerit) {
		return new SQL().UPDATE(MEMBER_CARD_TBL)
				.SET("demerit = demerit + #{demerit}")
				.WHERE("member_id = #{id}")
				.toString();
	}
	
	
	// 멤버십 갱신 / 등급 변경(?)
	
	// 포인트 아이디로 검색
	public String findPointById(String id) {		
		return new SQL().SELECT("point").FROM(MEMBER_CARD_TBL)
				.WHERE("member_id = #{id}")				
				.toString();
	}
	
	
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
	
	
	public String updateLend(@Param("id")String id, @Param("grade")String grade) {
		SQL sql = new SQL();
		sql.UPDATE(MEMBER_CARD_TBL);
		if (grade.equals("Bronze")) {
			sql.SET("spending = spending + 1000");
		} else if (grade.equals("Silver")) {
			sql.SET("spending = spending + 500");
		} else {
			sql.SET("spending = spending");
		}
		sql.WHERE("id = #{id}");
		return sql.toString();
	}
	
}