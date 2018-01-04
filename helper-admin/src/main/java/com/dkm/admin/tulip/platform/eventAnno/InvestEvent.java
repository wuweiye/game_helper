package com.dkm.admin.tulip.platform.eventAnno;/*
package com.guohuai.tulip.platform.eventAnno;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.guohuai.rules.event.BaseEvent;
import com.guohuai.rules.event.EventAnno;
import com.guohuai.tulip.platform.coupon.couponOrder.CouponOrderEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

*/
/**
 * 投资申购事件
 * 
 * @author suzhicheng
 *
 *//*

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EventAnno("投资申购事件")
@Component
public class InvestEvent extends BaseEvent {
	*/
/** 国槐基线 *//*

	public static final String systemType_GH="GH";
	*/
/** 掌悦理财 *//*

	public static final String systemType_ZY="ZY";
	*/
/** 金猪理财 *//*

	public static final String systemType_JZ="JZ";
	*/
/** 天金所 *//*

	public static final String systemType_TJS="TJS";
	
	*/
/** 卡券Id *//*

	private String couponId;
	*/
/** 产品Id *//*

	private String productId;
	*/
/** 产品名称 *//*

	private String productName;
	*/
/** 用户Id *//*

	private String userId;
	*/
/** 订单类型 *//*

	private String orderType;
	*/
/** 期限 *//*

	private int dueTime;
	*/
/** 卡券抵扣金额 *//*

	private BigDecimal discount=BigDecimal.ZERO;
	*/
/** 用户支付金额 *//*

	private BigDecimal userAmount=BigDecimal.ZERO;
	*/
/** 产品收益 *//*

	private BigDecimal rateinvestment=BigDecimal.ZERO;
	*/
/** 订单时间 *//*

	private Timestamp createTime;
	*/
/** 订单编号 *//*

	private String orderCode;
	*/
/** 订单状态 *//*

	private String orderStatus;
	
	*/
/** 是否已经生成佣金订单 *//*

	private String isMakedCommisOrder=CouponOrderEntity.isMakedCommisOrder_no;

	*/
/** 事件类型 *//*

	private String eventType = "investment";
	
	*/
/** 系统类型 *//*

	private String systemType="GH";
	// 匹配规则的字段
	*/
/**
	 * 累计推荐人数量
	 *//*

	@EventAnno("累计推荐人数量")
	private Integer friends = 0;
	*/
/**
	 * 累计投资额度
	 *//*

	@EventAnno("累计投资额度")
	private BigDecimal investAmount = BigDecimal.ZERO;
	
	*/
/**
	 * 单次投资额度
	 *//*

	@EventAnno("单次投资额度")
	private BigDecimal singleInvestAmount = BigDecimal.ZERO;
	
	*/
/**
	 * 累计投资次数
	 *//*

	@EventAnno("累计投资次数")
	private Integer investCount = 0;
	*/
/**
	 * 投资额度
	 *//*

	@EventAnno("投资额度")
	private BigDecimal orderAmount = BigDecimal.ZERO;
	*/
/**
	 * 首投金额
	 *//*

	@EventAnno("首投金额")
	private BigDecimal firstInvestAmount = BigDecimal.ZERO;
	*/
/**
	 * 注册时间
	 *//*

	@EventAnno("注册时间")
	private Date registerTime;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getDueTime() {
		return dueTime;
	}

	public void setDueTime(int dueTime) {
		this.dueTime = dueTime;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(BigDecimal userAmount) {
		this.userAmount = userAmount;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getRateinvestment() {
		return rateinvestment;
	}

	public void setRateinvestment(BigDecimal rateinvestment) {
		this.rateinvestment = rateinvestment;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getIsMakedCommisOrder() {
		return isMakedCommisOrder;
	}

	public void setIsMakedCommisOrder(String isMakedCommisOrder) {
		this.isMakedCommisOrder = isMakedCommisOrder;
	}
	
	*/
/**
	 * 获取 friends
	 *//*

	public Integer getFriends() {
		return friends;
	}

	*/
/**
	 * 设置 friends
	 *//*

	public void setFriends(Integer friends) {
		this.friends = friends;
	}

	*/
/**
	 * 获取 investAmount
	 *//*

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	*/
/**
	 * 设置 investAmount
	 *//*

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	*/
/**
	 * 获取 investCount
	 *//*

	public Integer getInvestCount() {
		return investCount;
	}

	*/
/**
	 * 设置 investCount
	 *//*

	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}

	*/
/**
	 * 获取 firstInvestAmount
	 *//*

	public BigDecimal getFirstInvestAmount() {
		return firstInvestAmount;
	}

	*/
/**
	 * 设置 firstInvestAmount
	 *//*

	public void setFirstInvestAmount(BigDecimal firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}

	*/
/**
	 * 获取 registerTime
	 *//*

	public Date getRegisterTime() {
		return registerTime;
	}

	*/
/**
	 * 设置 registerTime
	 *//*

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	*/
/**
	 * 获取 eventType
	 *//*

	public String getEventType() {
		return eventType;
	}

	*/
/**
	 * 设置 eventType
	 *//*

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	*/
/**
	 * 获取 systemType
	 *//*

	public String getSystemType() {
		return systemType;
	}

	*/
/**
	 * 设置 systemType
	 *//*

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public BigDecimal getSingleInvestAmount() {
		return singleInvestAmount;
	}

	public void setSingleInvestAmount(BigDecimal singleInvestAmount) {
		this.singleInvestAmount = singleInvestAmount;
	}

}
*/
