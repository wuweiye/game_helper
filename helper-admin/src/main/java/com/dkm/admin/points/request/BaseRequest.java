package com.dkm.admin.points.request;

import lombok.Data;

@Data
public abstract class BaseRequest {

	private int page=1;
	
	private int rows=10;
	
}
