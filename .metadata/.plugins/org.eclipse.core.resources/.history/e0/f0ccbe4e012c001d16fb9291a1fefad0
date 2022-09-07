package com.koreate.betty.domain.member.provider;

import static com.koreate.betty.domain.model.TableConst.BLACK_LIST_TBL;
import static com.koreate.betty.domain.model.TableConst.CHK_LOG_TBL;
import static com.koreate.betty.domain.model.TableConst.INQUIRY_TBL;
import static com.koreate.betty.domain.model.TableConst.MEMBER_CARD_TBL;
import static com.koreate.betty.domain.model.TableConst.MEMBER_TBL;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.koreate.betty.domain.member.dto.form.AdminSearchForm;
import com.koreate.betty.domain.member.vo.Inquiry;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.global.util.Criteria;

public class MemberProvider {
	
	// 비밀번호 재설정
	public String updatePw(@Param("id")String id, @Param("pw")String changePw) {
		return new SQL().UPDATE(MEMBER_TBL)
				.SET("pw = #{pw}")
				.WHERE("id = #{id}")
				.toString();
	}
	
	// 계정 전체 검색 (직원 -> 회원정보 조회)
	public String findAll() {
		return new SQL().SELECT("*")
				.FROM(MEMBER_TBL)
				.toString();
	}

	// 계정 한개 검색 (마이페이지/직원 정보)
	public String findOne(String id) {
		return new SQL().SELECT("*")
				.FROM(MEMBER_TBL)
				.WHERE("id = #{id}")
				.toString();
	}
	
	// 계정 삭제(회원 탈퇴)	// ## 주의 : UPDATE 쿼리를 사용합니다
	public String delete(String id) {
		return new SQL().UPDATE(MEMBER_TBL)
				.SET("del = 'y'")
				.WHERE("id = #{id}")
				.toString();
	}	
	
	// 회원 정보 수정 (이미지 수정도 이걸로 처리됩니다) // 본인 회원 수정, 스태프의 회원 수정
	public String update(Member member) {
		SQL sql = new SQL().UPDATE(MEMBER_TBL)
		.SET("id = #{id}, pw = #{pw}, nickname = #{nickname}, name = #{name}, gender = #{gender}, birth = #{birth}, addr = #{addr}");
		if(member.getImg() != null) {
			sql.SET("img = #{img}");
		}
		return sql.WHERE("id = #{id}").toString();
	}
	
	// 체크인
	
	// 체크아웃
	
	// 체크인 이력 (출결 이력 표기)
	public String findMyChkLog(String loginId) {
		return new SQL().SELECT("*").FROM(CHK_LOG_TBL)
				.WHERE("member_id = #{loginId}")
				.toString();
	}
	

	// 관리자가 보는 회원정보 기반(서비스에서 데이터 추가 필요)
	public String findMemberForAdmin(@Param("form")AdminSearchForm form, @Param("cri")Criteria cri) {
		String allow = form.getAllowOption();
		String rights = form.getRightsOption();
		String grade = form.getGradeOption();
		String searchOption = form.getSearchOption();
		String searchText = form.getSearchText().trim();		
		
		SQL sql = new SQL().SELECT("img, id, nickname, name, gender, birth, phone, email, addr, rights, premium_grade, point, reg_date").FROM(MEMBER_TBL).JOIN(MEMBER_CARD_TBL)
				.WHERE("id = member_id");
		if (allow != null) {
			switch(allow) {
			case "all" :
				break;
			case "allow" :
				sql.WHERE("rights != 1");
				break;
			case "disallow" :
				sql.WHERE("rights = 1");
				break;
			}
		}
		
		if (rights != null) {
			switch(rights) {
			case "all" :
				break;
			case "member" :
				sql.WHERE("rights = 0");
				break;
			case "staff" :
				sql.WHERE("rights != 0");
				break;
			}
		}
		
		if (grade != null && !grade.equals("all")) {
			sql.WHERE("premium_grade = #{form.gradeOption}");
		}
		
		if (searchText != null && !searchText.equals("") && searchOption != null) {
			sql.WHERE(searchOption + " LIKE CONCAT('%', #{form.searchText}, '%')");
		}		
		
		sql.OFFSET("#{cri.startRow}").LIMIT("#{cri.perPageNum}");
		
		return sql.toString();
	}
	
	public String findMemberForAdminCount(AdminSearchForm form) {
		String allow = form.getAllowOption();
		String rights = form.getRightsOption();
		String grade = form.getGradeOption();
		String searchOption = form.getSearchOption();
		String searchText = form.getSearchText().trim();		
		
		SQL sql = new SQL().SELECT("count(*)").FROM(MEMBER_TBL).JOIN(MEMBER_CARD_TBL)
				.WHERE("id = member_id");
		if (allow != null) {
			switch(allow) {
			case "all" :
				break;
			case "allow" :
				sql.WHERE("rights != 1");
				break;
			case "disallow" :
				sql.WHERE("rights = 1");
				break;
			}
		}
		
		if (rights != null) {
			switch(rights) {
			case "all" :
				break;
			case "member" :
				sql.WHERE("rights = 0");
				break;
			case "staff" :
				sql.WHERE("rights != 0");
				break;
			}
		}
		
		if (grade != null && !grade.equals("all")) {
			sql.WHERE("premium_grade = #{gradeOption}");
		}
		if (searchText != null && !searchText.equals("") && searchOption != null) {			
			sql.WHERE(searchOption + " LIKE CONCAT('%', #{searchText}, '%')");				
		}	
		
		return sql.toString();
	}
	
	public String createInquiry(Inquiry inquiry) {
		return new SQL().INSERT_INTO(INQUIRY_TBL)
				.INTO_COLUMNS("member_id", "title", "content")
				.INTO_VALUES("#{memberId}","#{title}","#{content}")
				.toString();
	}

	
	// 블랙 아이디 확인
	public String blackCheckById(String id) {
		return new SQL().SELECT("count(*)").FROM(BLACK_LIST_TBL)
				.WHERE("member_id = #{id}")
				.toString();
	}
	
	// 직원 승인
	public String updateAllow(String id) {
		return new SQL().UPDATE(MEMBER_TBL).SET("rights = 2")
				.WHERE("id = #{id}")
				.toString();
	}
	
}

