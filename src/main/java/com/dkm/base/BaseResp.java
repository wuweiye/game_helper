package com.dkm.base;

import org.springframework.validation.BindException;

import com.dkm.basic.component.exception.GHException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResp {

	protected int resultCode;
	protected String resultMessage;

	public void error(Throwable error) {
		if (error instanceof GHException) {
			GHException e = (GHException) error;
			this.resultCode = e.getCode() == 0 ? -1 : e.getCode();
			this.resultMessage = e.getMessage();
		} else if (error instanceof BindException) {
			BindException be = (BindException) error;
			String msg = be.getBindingResult().getAllErrors().get(0).getDefaultMessage();
			this.resultCode = -1;
			this.resultMessage = msg;
		} else {
			this.resultCode = -1;
			this.resultMessage = error.getMessage();
		}
	}

}
