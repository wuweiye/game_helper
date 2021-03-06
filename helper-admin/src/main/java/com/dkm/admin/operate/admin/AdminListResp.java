package com.dkm.admin.operate.admin;

import com.dkm.admin.operate.component.web.view.PageResp;

import java.util.List;

public class AdminListResp extends PageResp<AdminResp> {

	public AdminListResp() {
		super.total = 0;
	}

	public AdminListResp(long total) {
		super.total = total;
	}

	public AdminListResp(List<Admin> admins, long total) {
		this(admins, total, false);
	}

	public AdminListResp(List<Admin> admins, long total, boolean passwordable) {
		super.total = total;
		if (null != admins && admins.size() > 0) {
			for (Admin admin : admins) {
				super.rows.add(new AdminResp(admin, passwordable));
			}
		}
	}

}
