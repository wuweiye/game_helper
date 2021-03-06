package com.dkm.admin.operate.admin;

import com.dkm.admin.operate.component.util.DateUtil;
import com.dkm.admin.operate.component.web.view.BaseResp;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class AdminInfoResp extends BaseResp {

	private String oid;
	private String sn;
	private String account;
	private String name;
	private String email;
	private String phone;
	private String status;
	private String resources;
	private String validDate;
	private String validTime;
	private Set<String> roles = new HashSet<String>();

	public AdminInfoResp(Admin admin) {
		super();
		this.oid = admin.getOid();
		this.sn = admin.getSn();
		this.account = admin.getAccount();
		this.name = admin.getName();
		this.email = admin.getEmail();
		this.phone = admin.getPhone();
		this.status = admin.getStatus();
		this.resources = admin.getResources();
		if (null != admin.getValidTime()) {
			this.validDate = DateUtil.formatDate(admin.getValidTime().getTime());
			this.validTime = DateUtil.formatDatetime(admin.getValidTime().getTime());
		}
	}

}
