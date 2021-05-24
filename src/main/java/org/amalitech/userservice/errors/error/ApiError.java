package org.amalitech.userservice.errors.error;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

	int status;

	String message;

	long timestamp;

	String path;

	Map<String, String> validationErrors;

	public ApiError(int status, String message, String path) {
		super();
		this.status = status;
		this.message = message;
		this.path = path;
		this.timestamp = new Date().getTime();
	}

}
