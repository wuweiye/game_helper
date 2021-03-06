package com.dkm.dao.forum;

import com.dkm.model.forum.ReplyMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReplyMainDao extends JpaRepository<ReplyMain, Long>, JpaSpecificationExecutor<ReplyMain> {

    public List<ReplyMain> findByPid(Long pid);
}
