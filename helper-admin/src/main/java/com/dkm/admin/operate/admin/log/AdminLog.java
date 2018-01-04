package com.dkm.admin.operate.admin.log;

import com.dkm.admin.operate.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "T_OP_ADMIN_LOG")
public class AdminLog implements Serializable {

	private static final long serialVersionUID = 4371625102013036310L;

	public static final String TYPE_REGIST = "REGIST";
	public static final String TYPE_FREEZE = "FREEZE";
	public static final String TYPE_UNFREEZE = "UNFREEZE";
	public static final String TYPE_LOGIN = "LOGIN";
	public static final String TYPE_LOGOUT = "LOGOUT";
	public static final String TYPE_RESETPWD = "RESETPWD";
	public static final String TYPE_GENPWD = "GENPWD";

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
	private String oid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminOid", referencedColumnName = "oid")
	private Admin admin;
	
	private String type;
	
	private String operator;
	private String operateIp;
	private Timestamp updateTime;
	private Timestamp createTime;

}
