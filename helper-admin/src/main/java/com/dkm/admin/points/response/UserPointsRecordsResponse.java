package com.dkm.admin.points.response;

import com.dkm.basic.component.ext.web.BaseResp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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
