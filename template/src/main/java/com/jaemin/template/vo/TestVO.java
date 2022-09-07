package com.jaemin.template.vo;

import javax.validation.constraints.NotBlank;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVO {
	
	private Integer num;
	private String camelTest;
	@NotBlank
	private String id;
	@NotBlank
	private String pw;
	
	/*
	 * @NotBlank
	 * 
	 * @NotNull
	 * 
	 * @Range(min = 1000, max = 1000000)
	 * 
	 * @Max(value = 9999)
	 * 
	 * @Value("${file.dir}") private String temp;
	 */
}
