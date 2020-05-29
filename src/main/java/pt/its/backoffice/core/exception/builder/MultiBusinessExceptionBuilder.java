package pt.its.backoffice.core.exception.builder;

import java.util.function.BooleanSupplier;

import com.google.common.base.Strings;

import pt.its.backoffice.core.exception.BusinessException;
import pt.its.backoffice.core.exception.MultiBusinessException;


public final class MultiBusinessExceptionBuilder {

	private static final String REQUIRED_MESSAGE = "O campo '%s' é obrigatório.";
	
	private MultiBusinessException multiBusinessException;
	
	private MultiBusinessExceptionBuilder() {}

	public static MultiBusinessExceptionBuilder instance() {
		return new MultiBusinessExceptionBuilder();
	}

	public MultiBusinessExceptionBuilder addException(String msg) {
		BusinessException businessException = new BusinessException(msg);
		return addException(businessException);
	}

	public MultiBusinessExceptionBuilder addException(String msg, boolean condition) {
		if (condition) {
			BusinessException businessException = new BusinessException(msg);
			return addException(businessException);
		} else {
			return this;
		}
	}

	public MultiBusinessExceptionBuilder addRequiredException(String fieldName, Object fieldValue) {
		String mensagem = String.format(REQUIRED_MESSAGE, fieldName);
		return addException(mensagem, fieldValue == null || Strings.isNullOrEmpty(fieldName));
	}

	public MultiBusinessExceptionBuilder addException(Throwable t) {
		BusinessException businessException = new BusinessException(t);
		return addException(businessException);
	}

	public MultiBusinessExceptionBuilder addException(String msg, Throwable t) {
		BusinessException businessException = new BusinessException(msg);
		return addException(businessException);
	}
	
	public MultiBusinessExceptionBuilder addException(String message, BooleanSupplier condition) {
		return addException(message, condition.getAsBoolean());
	}

	private MultiBusinessExceptionBuilder addException(BusinessException businessException) {
		if (multiBusinessException == null) {
			multiBusinessException = new MultiBusinessException();
		}

		this.multiBusinessException.addException(businessException);
		return this;
	}

	public void handleThrowException() {
		if (this.multiBusinessException != null) {
			throw this.multiBusinessException;
		}
	}

}
