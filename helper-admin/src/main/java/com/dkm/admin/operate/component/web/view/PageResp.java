package com.dkm.admin.operate.component.web.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PageResp<T> extends BaseResp {

	protected long total = 0;
	protected List<T> rows = new ArrayList<T>();

}
