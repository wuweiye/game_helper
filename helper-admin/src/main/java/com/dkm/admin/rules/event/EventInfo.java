package com.dkm.admin.rules.event;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EventInfo {
	String eventId;
	String eventClass;
	String eventName;
	List<EventAttr> attrList;
}
