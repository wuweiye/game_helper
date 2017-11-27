package com.dkm.rules.action;

import lombok.Builder;
import lombok.Data;

import com.dkm.rules.event.BaseEvent;

@Data
@Builder
public class RuleFiredEvent {
	String ruleId;
	BaseEvent inputEvent;
	String actionParams;
}
