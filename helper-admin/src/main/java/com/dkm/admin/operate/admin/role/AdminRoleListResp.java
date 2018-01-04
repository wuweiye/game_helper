package com.dkm.admin.operate.admin.role;

import com.dkm.admin.operate.component.web.view.PageResp;

import java.util.List;

public class AdminRoleListResp extends PageResp<AdminRoleResp> {

	public AdminRoleListResp(List<AdminRole> adminRoles, long total) {
		super.total = total;
		if (null != adminRoles && adminRoles.size() > 0) {
			for (AdminRole adminRole : adminRoles) {
				super.rows.add(new AdminRoleResp(adminRole));
			}
		}
	}

}
