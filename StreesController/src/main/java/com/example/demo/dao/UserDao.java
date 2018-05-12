package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.User;



public interface UserDao extends CrudRepository<User, Long>{
	@Query(value="select * from user where id= ?1",nativeQuery=true)
	User findByIds(Integer id);
}
