package com.dkm.service.forum;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.params.forum.ReplyParams;

public interface ReplyBranchService {

    public void save(ReplyParams params);

    public BaseResp delete(Long id);

    /**
     * 根据回复人删除回复
     * @param rmId
     */
    public void deleteAll(Long rmId);
}
