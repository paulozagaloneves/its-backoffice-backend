package pt.its.backoffice.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pt.its.backoffice.business.dto.UserDTO;
import pt.its.backoffice.business.dto.UserFilterDTO;
import pt.its.backoffice.business.model.User;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	static final String USER_DTO_PROJETION = "new pt.its.backoffice.business.dto.UserDTO(u.id, u.name, u.username, u.password, u.creation, u.lastUpdated, u.status, u.creationUsername, u.lastUpdatedUsername)";
	
	@Query("select u from User u "
			+ " where u.username = :username and u.status = true")
	User findByUsername(String username);
	
	
	@Query("select u.password from User u where u.id = :id")
	String findPassword(Long id);

	@Query("select "+ USER_DTO_PROJETION +" from User u where u.id = :id")
	UserDTO find(Long id);
	
	@Query("select "+ USER_DTO_PROJETION +" from User u")
	List<UserDTO> findAllUsers();
	
	@Query("select "+ USER_DTO_PROJETION +" from User u "
			+ "WHERE (:#{#filter.name} IS NULL OR u.name LIKE %:#{#filter.name}%) "
			+ "AND (:#{#filter.username} IS NULL OR u.username LIKE %:#{#filter.username}%)")
	List<UserDTO> find(UserFilterDTO filter);

}
