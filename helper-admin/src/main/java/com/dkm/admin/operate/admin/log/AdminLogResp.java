package com.dkm.admin.operate.admin.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLogResp {
	
	private String oid;
	private String type;
	private String operateIp;
	private Timestamp createTime;

	private String sn;
	private String account;
	private String name;
	
	public AdminLogResp(AdminLog adminLog){
		this.oid = adminLog.getOid();
		this.type = adminLog.getType();
		this.operateIp = adminLog.getOperateIp();
		this.createTime = adminLog.getCreateTime();
		
		this.sn = adminLog.getAdmin().getSn();
		this.account = adminLog.getAdmin().getAccount();
		this.name = adminLog.getAdmin().getName();
	}
}
