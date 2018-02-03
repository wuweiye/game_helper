package com.dkm.event;

import com.dkm.event.add.AddManageEvent;
import com.dkm.event.delete.DeleteEvent;
import com.dkm.model.forum.Forums;
import com.dkm.model.forum.Paste;
import com.dkm.service.forum.ForumsService;
import com.dkm.service.forum.PasteService;
import com.dkm.service.forum.ReplyBranchService;
import com.dkm.service.forum.ReplyMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public class ForumsListener {


    @Autowired
    ReplyBranchService replyBranchService;

    @Autowired
    ForumsService forumsService;

    @Autowired
    ReplyMainService replyMainService;

    @Autowired
    PasteService pasteService;

    @Async
    @EventListener
    public void OnListener(DeleteEvent event){

        if(event.getType().equals("forums")){

            List<Paste> pastes = pasteService.getPastes(event.getId());
            for(Paste paste : pastes){
                pasteService.deleteAll(paste.getId());
                forumsDeleteAfter(paste.getId());
            }

        }else if(event.getType().equals("paste")){

            forumsDeleteAfter(event.getId());
        }else {
            replyBranchService.deleteAll(event.getId());
        }



    }

    //TODO-F 新增加后事件异步处理
    @Async
    @EventListener
    public  void OnAddManageListener(AddManageEvent event){

        if(event.getType().equals("forums")){

        }else if(event.getType().equals("paste")){

            pasteAddAfter(event.getId());

        }else if(event.getType().equals("replyMain")){

        }else if(event.getType().equals("replyBranch")){

        }

    }


    private void pasteAddAfter(Long id) {

        Paste paste = pasteService.getPasteById(id);
        forumsService.replyNumAdd(paste.getFid());

    }

    public void forumsDeleteAfter(Long pid){

        List<Forums> forums = forumsService.getForums(pid);
        for(Forums forum : forums){
            replyMainService.deleteAll(forum.getId());
            replyBranchService.deleteAll(forum.getId());

        }
    }

}
