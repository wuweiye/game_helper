package com.dkm.admin.rules.action;

import com.dkm.admin.rules.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 规则触发动作基类.
 * 
 * @author xulizhong
 *
 */
@Component
@Slf4j
public class SpringEventAction {
	@Autowired
	ApplicationContext context;

	@Autowired
	ApplicationEventPublisher publisher;
	
	public void execute(String ruleId, BaseEvent event, String actionName,
			String actionParams) {
		// 根据actionName找到对应的actionEvent
		RuleFiredEvent fired = RuleFiredEvent.builder()
				.actionParams(actionParams).ruleId(ruleId).inputEvent(event)
				.build();

		// TODO 记录入库, 以便核对查询, 在这里还可以控制规则每天每人触发的次数限制

		Action actionBean = (Action) this.context.getBean(actionName);
		if (actionBean != null) {
			try {
				actionBean.execute(fired);
			} catch (Exception e) {
				log.warn("action error, name = {}, msg = {}", actionName, e.getMessage());
				e.printStackTrace();
			}
		}

		//暂时没有事件处理
		this.publisher.publishEvent(fired);
	}
}
