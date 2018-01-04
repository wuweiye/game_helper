package com.dkm.admin.operate.role;

import com.dkm.admin.operate.system.Sys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

	public List<Role> findByNameAndSystem(String name, Sys system);

	public List<Role> findByOidIn(String[] oids);

}
