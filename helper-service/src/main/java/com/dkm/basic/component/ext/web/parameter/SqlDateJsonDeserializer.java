package com.dkm.basic.component.ext.web.parameter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;

public class SqlDateJsonDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
		if (null == arg0.getText() || arg0.getText().toString().trim().equals("")) {
			return null;
		}
		return Date.valueOf(arg0.getText());
	}

}
