package net.koreate.test.user.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
	
	private String userid;
	private String userpw;
	private String username;
	private String email;
	private Date regdate;
	private Date updatedate;
	
}
