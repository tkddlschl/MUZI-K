package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUploadDTO {

	
	private int upload_no;
	private String u_id;
	private String u_email;
	private String u_image;
	private String u_path;
}
