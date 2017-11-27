package com.guohuai.test;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        for(int i = 0; i < 10; i++){
            System.out.println("Listener"+i);
        }
        System.out.println("Listener");
    }
}
