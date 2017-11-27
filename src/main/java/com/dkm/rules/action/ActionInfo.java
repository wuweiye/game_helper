package com.dkm.rules.action;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.dkm.rules.event.EventAttr;

@Data
@Builder
public class ActionInfo {
	String id, name;
	
	List<EventAttr> paramList;
}
