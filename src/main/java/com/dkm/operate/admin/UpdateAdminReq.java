package com.dkm.operate.admin;

import java.sql.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAdminReq {

	@NotBlank
	private String oid;
	@NotBlank
	private String name;
	@NotBlank
	private String comment;
	@Email
	private String email;
	private String phone;
	private Date validTime;
	@NotBlank
	private String[] roles;

}
