package com.dkm.admin.rules.event.jinzhuan;

import com.dkm.admin.rules.event.BaseEvent;
import com.dkm.admin.rules.event.EventAnno;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author xueyunlong
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EventAnno("红包")
@Component
public class RedPacketEvent extends BaseEvent {
	
	@EventAnno("用户OID")
	String userOid;
	
	@EventAnno("红包金额")
	String redAmount;
	
	
	
}

