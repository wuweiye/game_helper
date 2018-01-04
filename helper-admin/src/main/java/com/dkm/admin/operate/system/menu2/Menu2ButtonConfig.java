package com.dkm.admin.operate.system.menu2;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu2ButtonConfig {

	public static final String ENABLE_YES = "YES";
	public static final String ENABLE_NO = "NO";

	private String id;
	private String text;
	private String position;
	private String tableId;
	private String className;
	private String enable;
	private List<String> roles;
	private boolean authorize = true;

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("text", this.text);
		json.put("position", this.position);
		json.put("tableId", this.tableId);
		json.put("className", this.className);
		json.put("authorize", this.authorize);
		return json;
	}

}
