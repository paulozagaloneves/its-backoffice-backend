package pt.its.backoffice.core.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pt.its.backoffice.business.dto.ErrorDetailsDTO;
import pt.its.backoffice.core.exception.MultiBusinessException;


@ControllerAdvice
@RestController
public class MultiBusinessExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MultiBusinessException.class)
	public final ResponseEntity<ErrorDetailsDTO> handleException(MultiBusinessException exception, WebRequest request) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(exception.getExceptionMessages());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
