package com.dkm.service.forum;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.model.forum.Forums;
import com.dkm.model.forum.ReplyMain;
import com.dkm.params.forum.ReplyParams;
import com.dkm.resp.forum.ForumRep;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ReplyMainService {

    public void save(ReplyParams params);

    public BaseResp delete(Long id);

    public void deleteAll(Long id);

    public PageResp<ReplyMain> replyMainQuery(Specification<ReplyMain> spec, Pageable pageable);


}
