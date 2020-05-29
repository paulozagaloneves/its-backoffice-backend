package pt.its.backoffice.core.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pt.its.backoffice.business.dto.ErrorDetailsDTO;
import pt.its.backoffice.core.exception.BusinessException;


@ControllerAdvice
@RestController
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ErrorDetailsDTO> handleException(BusinessException exception, WebRequest request) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
