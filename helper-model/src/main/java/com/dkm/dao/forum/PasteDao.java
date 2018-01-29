package com.dkm.dao.forum;

import com.dkm.model.forum.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PasteDao extends JpaRepository<Paste, Long >, JpaSpecificationExecutor<Paste> {

    public List<Paste> findByFid(Long fid);
}
