package pt.its.backoffice.core.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<BusinessException> exceptions = new ArrayList<>();

	public MultiBusinessException() {
	}

	public List<BusinessException> getExceptions() {
		return exceptions;
	}
	
	public List<String> getExceptionMessages() {
		return exceptions.stream().map(BusinessException::getMessage).collect(Collectors.toList());
	}

	public void addException(BusinessException businessException) {
		this.exceptions.add(businessException);
	}

	@Override
	public String toString() {
		return "MultiBusinessException [exceptions=" + exceptions + "]";
	}
	
}
