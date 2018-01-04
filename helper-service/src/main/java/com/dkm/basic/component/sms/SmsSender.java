package com.dkm.basic.component.sms;

import com.dkm.basic.component.ext.web.BaseResp;

public interface SmsSender<T extends Sms> {
	public BaseResp send(T t);
}
