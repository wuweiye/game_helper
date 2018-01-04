package com.dkm.admin.rules.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventAttr {
	String id, name, type, enums;
}
