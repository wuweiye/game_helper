package com.dkm.admin.rules.fact;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class InvestorOrder {
	
	/**
	 * 用户oid.
	 */
	String oid;

	/**
	 * 产品OID.
	 */
	String productOid;
	
	/**
	 * 订单时间, 结算成功时间.
	 */
	Date orderTime, settlementTime;
	
	/**
	 * 订单金额.
	 */
	BigDecimal amount;
}
