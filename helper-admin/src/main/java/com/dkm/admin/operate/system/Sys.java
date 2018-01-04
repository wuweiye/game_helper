package com.dkm.admin.operate.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_OP_SYSTEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sys {

	@Id
	private String oid;
	private String name;

}
