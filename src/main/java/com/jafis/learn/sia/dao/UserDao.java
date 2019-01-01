package com.jafis.learn.sia.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.User;

/**
 * @author Awiyanto Ajisasongko
 */
public interface UserDao extends JpaRepository<User, String> {
	
	@Query("SELECT m FROM User m where username=:username")
	public User checkUsername(@Param("username") String username);
	
	@Query("SELECT m FROM User m where userid=:userid")
	public User checkUserId(@Param("userid") String userid);
	
	@Query("SELECT m FROM User m where username=:username AND password=:password")
	public User login(@Param("username") String username, @Param("password") String password);

	@Query("SELECT m FROM User m WHERE isdeleted=false")
	public List<User> ambilDataYangAda();
}

