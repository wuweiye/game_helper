package com.dkm.basic.component.collectinfo;

import com.alibaba.fastjson.JSONObject;
import com.dkm.basic.component.ext.web.BaseResp;
import feign.Feign;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectInfoSdk {
	private static String host="http://demo.guohuaitech.com/";
	private static CollectInfoApi collectInfoApi=Feign.builder().target(CollectInfoApi.class, host);
	
	public static BaseResp collectInfo(String info){
		BaseResp rep=new BaseResp();
		try {
			rep = JSONObject.parseObject(collectInfoApi.collectInfo(info),BaseResp.class);
		} catch (Exception e) {
			rep.setResultCode(-1);
		}
		return rep;
	}
}
