package com.dkm.admin.rules.fact;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CouponInfo {

	/**
	 * 对象ID.
	 */
	String oid;
	
	/**
	 * 优惠券/抵扣券.
	 */
	String name;
	
	/**
	 * 有效时间.
	 */
	Date validFrom, validTo;
	
	/**
	 * 有效日.
	 */
	int days;
}
