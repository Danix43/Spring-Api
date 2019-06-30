package danix43.api.errorhandling;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String responseMessage = "The JSON request is malformed";
		return buildResponseEntity(new ApiError(status, responseMessage));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConfilct(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Test error";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = EntityNotFoundException.class)
	protected ResponseEntity<Object> handleResourseNotExisting(EntityNotFoundException ex, WebRequest request) {
		String bodyOfResponse = "The resource that you are looking for doesn't exist!";
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, bodyOfResponse));
	}

}
