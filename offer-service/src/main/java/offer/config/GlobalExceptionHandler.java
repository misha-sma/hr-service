package offer.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Value;
import offer.data.exception.OfferNotExistException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ OfferNotExistException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleDoubleEntryException(HttpServletRequest request, Exception e) {
		return ErrorResponse.builder().title("BAD_REQUEST").detail(e.getMessage())
				.request(request.getMethod() + " " + request.getRequestURI()).time(getTime()).build();
	}

	private String getTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}

	@Value
	@Builder
	public static class ErrorResponse {
		String title;
		String detail;
		String request;
		String time;
	}
}
