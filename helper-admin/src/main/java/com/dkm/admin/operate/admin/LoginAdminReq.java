package com.dkm.admin.operate.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAdminReq {
	
	@NotBlank
	private String account;
	@NotBlank
	private String password;
	@NotBlank
	private String system;    		//系统
	
}
