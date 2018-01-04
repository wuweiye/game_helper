package com.dkm.admin.rules.action;

import com.dkm.admin.rules.event.EventAttr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActionInfo {
	String id, name;
	
	List<EventAttr> paramList;
}
