package com.dkm.admin.rules.action;

import com.dkm.admin.rules.event.BaseEvent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleFiredEvent {
	String ruleId;
	BaseEvent inputEvent;
	String actionParams;
}
