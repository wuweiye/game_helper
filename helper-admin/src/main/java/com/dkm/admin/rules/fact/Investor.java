package com.dkm.admin.rules.fact;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Investor {
	/**
	 * 投资者渠道: pc/app/wechat.
	 */
	String channelId;

	/**
	 * 投资者OID.
	 */
	String oid;

	/**
	 * 注册时间.
	 */
	Date regTime;
	
	/**
	 * 已注册天数, 历史注册用户.
	 */
	int days;
}
