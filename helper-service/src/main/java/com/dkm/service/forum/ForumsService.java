package com.dkm.service.forum;

import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.Forums;
import com.dkm.resp.app.ForumReq;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ForumsService {


    public void createOrUpdateForums(Long id, String name);

    public void createForums(Long id);

    public void delete(Long id);

    public PageResp<ForumReq> forumsQuery(Specification<Forums> spec, Pageable pageable);

    public List<Forums> getForums(Long id);

    public void replyNumAdd(Long id);

}
