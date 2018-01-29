package com.dkm.dao.forum;

import com.dkm.model.forum.Forums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ForumsDao extends JpaRepository<Forums, Long >, JpaSpecificationExecutor<Forums> {
    List<Forums> findById(Long id);
}
