package com.dkm.admin.tulip.platform.eventAnno;

import com.dkm.admin.rules.event.BaseEvent;
import com.dkm.admin.rules.event.EventAnno;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 注册事件
 * @author suzhicheng
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EventAnno("注册事件")
@Component
public class RegisterEvent extends BaseEvent {
	/**
	 * 友人编号
	 */
	private String friendId;
	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 好友联系方式
	 */
	private String friendPhone;
	/**
	 * 用户联系方式
	 */
	private String phone;
	/**
	 * 用户生日
	 */
	private Date birthday;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 注册时间
	 */
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());
	/** 事件类型 */
	private String eventType="register";
	//匹配规则的字段
	/**
	 *  累计推荐人数量 
	 */
	@EventAnno("累计推荐人数量")
	private Integer friends=0;
	/** 
	 * 累计投资额度
	 */
	@EventAnno("累计投资额度")
	private BigDecimal investAmount=BigDecimal.ZERO;
	/** 
	 * 累计投资次数
	 */
	@EventAnno("累计投资次数")
	private Integer investCount=0;
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

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendPhone() {
		return friendPhone;
	}

	public void setFriendPhone(String friendPhone) {
		this.friendPhone = friendPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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