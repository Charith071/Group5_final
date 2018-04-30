package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserDao  extends CrudRepository<User, Integer>{
	List<User> findByName(String name);
	List<User> findByAge(Integer age);
	boolean existsByUsername(String username);
	boolean existsByPassword(String password);
	boolean existsById(Integer id);
	
	@Query(value="SELECT * FROM user WHERE username = ?1 and password = ?2", nativeQuery = true)
	User  findByLogin(String username ,String password);
	
	@Query(value="SELECT * FROM user WHERE id = ?1", nativeQuery = true)
	User findByIds(Integer id);
	

	
}
