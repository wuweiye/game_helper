package com.dkm.admin.operate.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordReq {

	@NotBlank
	private String originalPassword;
	
	@NotBlank
	private String newPassword;
	
}
