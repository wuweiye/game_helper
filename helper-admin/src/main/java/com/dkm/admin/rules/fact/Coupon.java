package com.dkm.admin.rules.fact;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Coupon {
	String type;
	Date validDate;
	
	CouponInfo info;
}
