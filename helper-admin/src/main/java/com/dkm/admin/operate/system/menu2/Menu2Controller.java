package com.dkm.admin.operate.system.menu2;

import com.alibaba.fastjson.JSONArray;
import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.admin.operate.component.web.view.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/operate/system/menu/2.0", produces = "application/json;charset=utf-8")
public class Menu2Controller extends BaseController {

	@Autowired
	private Menu2Service menu2Service;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResp> save(@RequestParam String system, @RequestBody String config) {
		String operator = super.getLoginUser();
		this.menu2Service.save(system, config, operator);
		return new ResponseEntity<BaseResp>(new BaseResp(), HttpStatus.OK);
	}

	@RequestMapping(value = "/load", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ResponseEntity<JSONArray> load(@RequestParam String system) {
		super.getLoginUser();
		JSONArray config = this.menu2Service.load(system);
		return new ResponseEntity<JSONArray>(config, HttpStatus.OK);
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ResponseEntity<JSONArray> view(@RequestParam String system, @RequestParam(defaultValue = "false") boolean develop) {
		JSONArray json = this.menu2Service.view(system, develop, super.getLoginUser());
		return new ResponseEntity<JSONArray>(json, HttpStatus.OK);
	}
}
