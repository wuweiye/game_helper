package com.dkm.operate.component.web.view;

import org.springframework.validation.BindException;

import com.dkm.operate.component.exception.OPException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResp {

	protected int errorCode;
	protected String errorMessage;

	public void error(Throwable error) {
		if (error instanceof OPException) {
			OPException e = (OPException) error;
			this.errorCode = e.getCode() == 0 ? -1 : e.getCode();
			this.errorMessage = e.getMessage();
		} else if (error instanceof BindException) {
			BindException be = (BindException) error;
			String msg = be.getBindingResult().getAllErrors().get(0).getDefaultMessage();
			this.errorCode = -1;
			this.errorMessage = msg;
		} else {
			this.errorCode = -1;
			this.errorMessage = error.getMessage();
		}
	}

}
