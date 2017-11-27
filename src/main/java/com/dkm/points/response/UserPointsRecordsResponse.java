package com.dkm.points.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.dkm.base.BaseResp;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserPointsRecordsResponse extends BaseResp {
	
	private List<UserPointsRecords> rows;
	private int page;
	private int row;
	private int totalPage;
	private long total;
	
}
