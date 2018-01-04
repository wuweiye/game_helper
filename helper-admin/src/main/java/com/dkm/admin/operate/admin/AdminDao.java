package com.dkm.admin.operate.admin;

import com.dkm.admin.operate.system.Sys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

	public Admin findByAccountAndSystem(String account, Sys system);

	public List<Admin> findByOidIn(String[] oids);

	public List<Admin> findByOidInAndStatusIn(String[] oids, String[] status);

}
