package com.koreate.betty.domain.member.provider;

import static com.koreate.betty.domain.model.TableConst.MEMBER_TBL;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.koreate.betty.domain.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignProvider {
	
		public String create(Member member) {
			return new SQL().INSERT_INTO(MEMBER_TBL)
					.INTO_COLUMNS("id", "pw", "nickname", "name", "gender", "birth", "phone", "post", "addr", "addr_detail", "email", "rights")
					.INTO_VALUES("#{id}, #{pw}, #{nickname}, #{name}, #{gender}, #{birth}, #{phone}, #{post}, #{addr}, #{addrDetail}, #{email}, #{rights}")
					.toString();
		}
		
		public String findIdById(String id) {
			return new SQL().SELECT("id").FROM(MEMBER_TBL)
					.WHERE("id = #{id}")
					.toString();
		}
			
		public String findNicknameByNickname(String nick) {
			return new SQL().SELECT("nickname").FROM(MEMBER_TBL)
					.WHERE("nickname = #{nick}")
					.toString();
		}
		
		public String findEmailByEmail(String email) {
			return new SQL().SELECT("email").FROM(MEMBER_TBL)
					.WHERE("email = #{email}")
					.toString();
		}
			
		public String findOneBySignIn(Member member) {
			return new SQL().SELECT("*")
					.FROM(MEMBER_TBL)
					.WHERE("id = #{id}")
					.WHERE("pw = #{pw}")
					.WHERE("del = 'n'")
					.toString();
		}
		
		public String findIdByForget(final String name, final String phone) {
			return new SQL().SELECT("id")
					.FROM(MEMBER_TBL)
					.WHERE("name = #{name}")
					.WHERE("phone = #{phone}")
					.toString();
		}
		
		public String findPwByForget(@Param("id")String id, @Param("phone")String phone) {
			return new SQL().SELECT("count(*)")
					.FROM(MEMBER_TBL)
					.WHERE("id = #{id}")
					.WHERE("phone = #{phone}")
					.toString();
		}
	
}
