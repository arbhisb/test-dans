package com.dans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dans.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value = "SELECT * from tuser where username = :username and password = :password", nativeQuery = true)
	Optional<User> findUser(@Param("username") String username,@Param("password") String password);
}
