package com.altimetrik.rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.rest.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	/*@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);*/

	public Optional<User> findByUsername(String username);
}
