package danix43.api.modules;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private List<ApiSubError> subErrors;

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	ApiError(HttpStatus status) {
		this();
		this.status = status;
		this.message = "Unexpected error";
	}

	ApiError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}
	
	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}

}