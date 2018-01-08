package com.dkm.admin.test;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestService {



    ApplicationEventPublisher eventPublisher;



    public void onTestEvent(String biz){

        System.out.println("test event start");

        TestEvent event = new TestEvent();
        event.setAge("11");
        event.setName("测试");
        eventPublisher.publishEvent(event);

    }



    @Async
    @EventListener
    public void OnListener(TestEvent event){

        int i = 99999;
       while (i > 0){
           System.out.println(i --);
       }
        System.out.println("Listener");
    }
}
