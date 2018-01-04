package com.dkm.admin.points.response;

import lombok.Data;


@Data
public class AccountTransRes {

	/*private List<AccountTransEntity> rows;*/
	private int page;
	private int row;
	private int totalPage;
	private long total;
}
