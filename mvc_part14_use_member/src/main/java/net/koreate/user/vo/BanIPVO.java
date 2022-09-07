package net.koreate.user.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BanIPVO {

	private String ip;		// 요청이 들어온 아이피
	private int cnt;		// 요청 횟수
	private Date bandate;	// 로그인 실패 시간
}
