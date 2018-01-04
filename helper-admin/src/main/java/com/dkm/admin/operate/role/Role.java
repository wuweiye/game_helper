package com.dkm.admin.operate.role;

import com.dkm.admin.operate.system.Sys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "T_OP_ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
	private String oid;
	private String name;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "systemOid", referencedColumnName = "oid")
	private Sys system;
	private String operator;
	private Timestamp updateTime;
	private Timestamp createTime;

}
