package net.atos.sn.bl.micro.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -2784666643387120369L;
	private String parameter;
	private HttpStatus status;

	public ClientRuntimeException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public ClientRuntimeException(String message, String param, HttpStatus status) {
		super(message);
		this.parameter = param;
		this.status = status;
	}

	public ClientRuntimeException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
		this.status = status;
	}

	public ClientRuntimeException(String message, Throwable cause, String param, HttpStatus status) {
		super(message, cause);
		this.parameter = param;
		this.status = status;
	}

	public Map<String, Object> errorResponse() {
		Map<String, Object> response = new HashMap<>(3);
		response.put("status", this.status.value());
		response.put("message", this.getMessage());
		response.put("parameter", this.parameter);
		return response;
	}

}
