package com.dkm.admin.operate.role;

import com.dkm.admin.operate.component.web.view.PageResp;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RoleListResp extends PageResp<RoleResp> {

	public RoleListResp(List<Role> roles, long total) {
		super.total = total;
		if (null != roles && roles.size() > 0) {
			for (Role role : roles) {
				super.rows.add(new RoleResp(role));
			}
		}
	}

}
