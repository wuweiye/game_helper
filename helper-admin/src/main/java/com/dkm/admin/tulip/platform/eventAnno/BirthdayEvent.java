package com.dkm.admin.tulip.platform.eventAnno;

import com.dkm.admin.rules.event.BaseEvent;
import com.dkm.admin.rules.event.EventAnno;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EventAnno("生日活动")
@Component
public class BirthdayEvent extends BaseEvent{
	/** 用户Id */
	private String userId;
	/** 事件类型 */
	private String eventType = "birthday";
	
	/**
	 * 累计推荐人数量
	 */
	@EventAnno("累计推荐人数量")
	private Integer friends = 0;
	/**
	 * 累计投资额度
	 */
	@EventAnno("累计投资额度")
	private BigDecimal investAmount = BigDecimal.ZERO;
	/**
	 * 累计投资次数
	 */
	@EventAnno("累计投资次数")
	private Integer investCount = 0;
	/**
	 * 投资额度
	 */
	@EventAnno("投资额度")
	private BigDecimal orderAmount = BigDecimal.ZERO;
	/**
	 * 首投金额
	 */
	@EventAnno("首投金额")
	private BigDecimal firstInvestAmount = BigDecimal.ZERO;
	/**
	 * 注册时间
	 */
	@EventAnno("注册时间")
	private Date registerTime;
}
