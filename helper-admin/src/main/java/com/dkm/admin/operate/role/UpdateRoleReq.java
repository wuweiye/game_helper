package com.dkm.admin.operate.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoleReq {

	@NotBlank
	private String oid;
	@NotBlank
	private String name;
	@NotBlank
	private String systemOid;

}
