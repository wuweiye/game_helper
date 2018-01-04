package com.dkm.admin.operate.admin.role;

import com.dkm.admin.operate.admin.Admin;
import com.dkm.admin.operate.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "T_OP_ADMIN_ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRole {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
	private String oid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "adminOid", referencedColumnName = "oid")
	private Admin admin;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleOid", referencedColumnName = "oid")
	private Role role;
	private String operator;
	private Timestamp updateTime;
	private Timestamp createTime;

}
