package com.guohuai.rules.action;

import lombok.Builder;
import lombok.Data;

import com.guohuai.rules.event.BaseEvent;
import lombok.Getter;

@Data
@Builder
public class RuleFiredEvent {
	String ruleId;
	BaseEvent inputEvent;
	String actionParams;
}
