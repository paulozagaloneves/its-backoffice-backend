package pt.its.backoffice.business.dto;

import java.util.Date;

import com.google.common.base.Objects;

public class EntityBaseDTO {
	
	private Long id;
	
	private Date creation;

	private Date lastUpdated;

	private boolean status;

	private String creationUsername;

	private String lastUpdatedUsername;
	
	public EntityBaseDTO() {}
	
	public EntityBaseDTO(Long id, Date creation, Date lastUpdated, boolean status, String creationUsername,
			String lastUpdatedUsername) {
		this.id = id;
		this.creation = creation;
		this.lastUpdated = lastUpdated;
		this.status = status;
		this.creationUsername = creationUsername;
		this.lastUpdatedUsername = lastUpdatedUsername;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCreationUsername() {
		return creationUsername;	
	}

	public void setCreationUsername(String creationUsername) {
		this.creationUsername = creationUsername;
	}

	public String getLastUpdatedUsername() {
		return lastUpdatedUsername;
	}

	public void setLastUpdatedUsername(String lastUpdatedUsername) {
		this.lastUpdatedUsername = lastUpdatedUsername;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EntityBaseDTO other = (EntityBaseDTO) obj;
		return Objects.equal(this.id, other.id);
	}
}
