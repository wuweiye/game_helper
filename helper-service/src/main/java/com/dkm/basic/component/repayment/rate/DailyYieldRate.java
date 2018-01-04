package com.dkm.basic.component.repayment.rate;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class DailyYieldRate implements YieldRate {

	// 日利率
	private BigDecimal rate;

	public DailyYieldRate(/* 日利率 */BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public BigDecimal dailyRate() {
		return this.rate;
	}

	@Override
	public BigDecimal monthRate() {
		return this.rate.multiply(new BigDecimal(30));
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("rate", this.rate);
		return json.toJSONString();
	}

}
