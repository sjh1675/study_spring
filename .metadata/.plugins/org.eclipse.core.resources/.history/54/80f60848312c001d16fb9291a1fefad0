package com.koreate.betty.domain.book.dto.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBookForm {
	@Size(max = 50)
	@NotBlank
	private String code;
	
	@Size(max = 50)
	@NotBlank
	private String title;
	
	@Size(max = 30)
	@NotBlank
	private String auth;
	
	@Size(max = 50)
	@NotBlank
	private String pub;
	
	@Size(max = 50)
	@NotBlank
	private String pubDate;
	
//	@NotBlank // 필수 X
	private Integer page;
	
	@NotBlank
	private Integer genre;
	
//	@NotBlank // 필수 X
	private String intro;
	
	@Nullable
	MultipartFile img;
	
}
