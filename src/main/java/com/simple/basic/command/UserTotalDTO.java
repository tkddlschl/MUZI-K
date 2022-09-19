package com.simple.basic.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTotalDTO {

	private String u_id;
	private String u_pw;
	private String u_email;
	private String u_nick;
	private char u_type;
	private String u_image;
	private String u_path;
}
