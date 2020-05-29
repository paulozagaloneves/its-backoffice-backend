package pt.its.backoffice.business.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetailsDTO {

	private List<String> errors = new ArrayList<>();

	private Date timestamp;

	public ErrorDetailsDTO() {
	}
	
	public ErrorDetailsDTO(String error) {
		this.errors.add(error);
	}

	public ErrorDetailsDTO(List<String> errors) {
		this.errors = errors;
		this.timestamp = new Date();
	}

	public List<String> getErrors() {
		return errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
