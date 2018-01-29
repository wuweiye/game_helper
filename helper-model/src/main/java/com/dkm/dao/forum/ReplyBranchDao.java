package com.dkm.dao.forum;

import com.dkm.model.forum.ReplyBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReplyBranchDao extends JpaRepository<ReplyBranch, Long >, JpaSpecificationExecutor<ReplyBranch> {

    public List<ReplyBranch> findByParent(Long parent);
}
