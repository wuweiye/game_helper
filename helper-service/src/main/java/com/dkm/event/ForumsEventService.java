package com.dkm.event;

import com.dkm.event.add.AddManageEvent;
import com.dkm.event.delete.DeleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ForumsEventService {

    ApplicationEventPublisher publisher;

    /**
     * 删除后续操作
     * @param id
     * @param type
     */
    public void onDeleteEvent(Long id, String type){

        DeleteEvent event = new DeleteEvent();
        event.setId(id);
        event.setType(type);
        publisher.publishEvent(event);

    }

    public void onAddManageEvent(Long id, String type) {

        AddManageEvent event = new AddManageEvent();

        event.setId(id);
        event.setType(type);

        publisher.publishEvent(event);
    }
}
