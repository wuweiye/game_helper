package com.dkm.admin.operate.system.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "T_OP_SYSTEM_MENU")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

	public static final String OID_MENU = "MENU";
	public static final String OID_FLOW = "FLOW";

	@Id
	private String oid;
	private String config;
	private String operator;
	private String system;
	private Timestamp updateTime;
	private Timestamp createTime;

}
