package com.dkm.service.forum;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.ReplyMain;
import com.dkm.params.forum.ReplyParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ReplyMainService {

    public void save(ReplyParams params);

    public BaseResp delete(Long id);

    public void deleteAll(Long id);

    public PageResp<ReplyMain> replyMainQuery(Specification<ReplyMain> spec, Pageable pageable);

    public List<ReplyMain> getReplays(Long pid);


}
