package com.dkm.admin.operate.admin.log;

import com.dkm.basic.component.ext.web.BaseController;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/operate/admin/log", produces = "application/json;charset=utf-8")
public class AdminLogController extends BaseController {

	@Autowired
	private AdminLogService adminLogService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody ResponseEntity<AdminLogListResp> list(
			@And({ @Spec(params = "oid", path = "admin.oid", spec = Equal.class),
					@Spec(params = "type", path = "type", spec = In.class) }) Specification<AdminLog> spec,
			@RequestParam(defaultValue = "1") int page, @RequestParam int rows) {
		super.getLoginUser();
		AdminLogListResp adminLogListResp = this.adminLogService.list(spec, page-1, rows);
		return new ResponseEntity<AdminLogListResp>(adminLogListResp,HttpStatus.OK);
	}
}
