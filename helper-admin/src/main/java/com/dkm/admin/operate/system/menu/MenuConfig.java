package com.dkm.admin.operate.system.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuConfig {

	private String id;
	private String text;
	private List<SubMenu> children;

}
