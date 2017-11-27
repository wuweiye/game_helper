package com.guohuai.tulip.platform.eventAnno;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.guohuai.rules.event.BaseEvent;
import com.guohuai.rules.event.EventAnno;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 赎回事件
 * 
 * @author suzhicheng
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EventAnno("赎回事件")
@Component
public class RedeemEvent extends BaseEvent {

	/** 卡券Id */
	private String couponId;
	/** 产品Id */
	private String productId;
	/** 产品名称 */
	private String productName;
	/** 用户Id */
	private String userId;
	/** 订单类型 */
	private String orderType;
	/** 用户支付金额 */
	private BigDecimal userAmount=BigDecimal.ZERO;
	/** 订单时间 */
	private Timestamp createTime;
	/** 订单编号 */
	private String orderCode;
	/** 订单状态 */
	private String orderStatus;
	/** 事件类型 */
	private String eventType = "redeem";

	// 匹配规则的字段
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

	/**
	 * 获取 couponId
	 */
	public String getCouponId() {
		return couponId;
	}

	/**
	 * 设置 couponId
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	/**
	 * 获取 productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置 productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取 productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置 productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取 userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置 userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取 orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 设置 orderType
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * 获取 userAmount
	 */
	public BigDecimal getUserAmount() {
		return userAmount;
	}

	/**
	 * 设置 userAmount
	 */
	public void setUserAmount(BigDecimal userAmount) {
		this.userAmount = userAmount;
	}

	/**
	 * 获取 orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * 设置 orderAmount
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 获取 createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 设置 orderCode
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * 获取 orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置 orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取 friends
	 */
	public Integer getFriends() {
		return friends;
	}

	/**
	 * 设置 friends
	 */
	public void setFriends(Integer friends) {
		this.friends = friends;
	}

	/**
	 * 获取 investAmount
	 */
	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	/**
	 * 设置 investAmount
	 */
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	/**
	 * 获取 investCount
	 */
	public Integer getInvestCount() {
		return investCount;
	}

	/**
	 * 设置 investCount
	 */
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}

	/**
	 * 获取 firstInvestAmount
	 */
	public BigDecimal getFirstInvestAmount() {
		return firstInvestAmount;
	}

	/**
	 * 设置 firstInvestAmount
	 */
	public void setFirstInvestAmount(BigDecimal firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}

	/**
	 * 获取 registerTime
	 */
	public Date getRegisterTime() {
		return registerTime;
	}

	/**
	 * 设置 registerTime
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * 获取 eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * 设置 eventType
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
