package com.dkm.service.forum;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.Paste;
import com.dkm.params.forum.PasteParams;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface PasteService {

    public void save(PasteParams params);

    public BaseResp delete(Long id);

    public void deleteAll(Long id);

    public List<Paste> getPastes(Long fId);

    public PageResp<Paste> replyMainQuery(Specification<Paste> spec, Pageable pageable);
}
