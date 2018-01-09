package com.dkm.dao.config;

import com.dkm.model.config.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigDao extends JpaRepository<Config,Long> {
}
