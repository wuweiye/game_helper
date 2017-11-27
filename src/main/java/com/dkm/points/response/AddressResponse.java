package com.dkm.points.response;

import com.dkm.base.BaseResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddressResponse extends BaseResp {
	private String oid;
	/**
	 * 用户oid
	 */
	private String userOid;
	/**
	 * 收货人姓名
	 */
	private String name;
	/**
	 * 收货地址
	 */
	private String takeAddress;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否为默认地址(1默认，0否)
	 */
	private int isDefault;
	/**
	 * 电话号码
	 */
	private String phone;
	/**
	 * 是否删除（yes：是  no：否）
	 */
	private String isdel;
	/**
	 * 邮编
	 */
	private String zipCode;

}
