package com.PRS.Capstone.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsernameAndPassword(String username, String password);
	
//	@Query("select u from User u where u.username = ?1 and u.password = ?2")  //believe this is Linq query. Code is saying that the username
//	User findByLogin(String username, String password);						//should be the first parameter and the password second when finding
																		//user via their login.
//	@Query("select u from User u where u.lastname = ?1")
//	List<User> findByLastname(String lastname);
	
}



