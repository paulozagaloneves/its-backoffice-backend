package pt.its.backoffice.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.base.Objects;

@MappedSuperclass
public class EntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date creation;

	@Column(nullable = false)
	private Date lastUpdated;

	@Column(nullable = false)
	private Boolean status = Boolean.TRUE;

	@Column(nullable = false, length = 50)
	private String creationUsername;

	@Column(nullable = false, length = 50)
	private String lastUpdatedUsername;

	@PrePersist
	public void prePersist() {
		this.setCreationUsername(getAuthentication().getName());
		this.setLastUpdatedUsername(getAuthentication().getName());
		this.creation = new Date();
		this.lastUpdated = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.lastUpdated = new Date();
		if(getAuthentication() != null) {
			this.setLastUpdatedUsername(getAuthentication().getName());
		}
	}
	
	protected static <T> T map(Object object, Class<T> clazz) {
		return new ModelMapper().map(object, clazz);
	}
	
	protected Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
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

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
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
		final EntityBase other = (EntityBase) obj;
		return Objects.equal(this.id, other.id);
	}
	


}
