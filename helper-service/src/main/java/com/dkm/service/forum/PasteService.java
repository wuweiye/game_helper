package com.dkm.service.forum;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.Paste;
import com.dkm.params.forum.PasteParams;
import com.dkm.resp.app.PasteDetailReq;
import com.dkm.resp.app.PasteReq;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface PasteService {

    public BaseResp save(PasteParams params);

    public BaseResp delete(Long id);

    public void deleteAll(Long id);

    public List<Paste> getPastes(Long fId);

    public PageResp<PasteReq> replyMainQuery(Specification<Paste> spec, Pageable pageable);

    public Paste getPasteById(Long id);

    PageResp<PasteDetailReq> getPasteDetail(Long id);
}
