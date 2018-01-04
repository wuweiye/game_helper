package com.dkm.basic.component.ext.web;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PageResp<T> extends BaseResp {

	protected long total;
	protected List<T> rows = new ArrayList<T>();

	public PageResp(Page<T> page) {
		this(page.getTotalElements(), page.getContent());
	}
}
