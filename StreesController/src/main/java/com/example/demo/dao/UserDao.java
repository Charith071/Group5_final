package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.User;



public interface UserDao extends CrudRepository<User, Long>{
	@Query(value="select * from user where id= ?1",nativeQuery=true)
	User findByIds(Integer id);
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'user'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from user order by id desc limit 1",nativeQuery=true)
	User getLastRecord();
}
