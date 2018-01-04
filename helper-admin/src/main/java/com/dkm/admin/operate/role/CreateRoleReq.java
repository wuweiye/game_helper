package com.dkm.admin.operate.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoleReq {

	@NotBlank
	@Length(max = 30, message = "角色长度不能超过30（包含）！")
	private String name;
	
	@NotBlank
	private String systemOid;
	
}
