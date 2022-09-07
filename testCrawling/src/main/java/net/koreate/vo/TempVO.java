package net.koreate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TempVO {

	private String code;
	private String title;
	private String auth;
	private String pub;
	private String pubdate;
	private int page;
	private String genre;
	private String img;
	private String intro;
}