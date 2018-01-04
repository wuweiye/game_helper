package com.dkm.admin.operate.admin.log;

import com.dkm.admin.operate.component.web.view.PageResp;

import java.util.List;

public class AdminLogListResp extends PageResp<AdminLogResp> {

	public AdminLogListResp(){
		super.total = 0;
	}
	
	public AdminLogListResp(long total){
		super.total = total;
	}
	
	public AdminLogListResp(List<AdminLog> adminLogs, long total){
		super.total = total;
		if(null != adminLogs && adminLogs.size()>0){
			for(AdminLog adminLog:adminLogs){
				super.rows.add(new AdminLogResp(adminLog));
			}
		}
	}
}
