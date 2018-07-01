package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.AllUsers;





public interface AlluserDao extends CrudRepository<AllUsers, Long>{
	boolean existsByUsername(String username);
	boolean existsByPassword(String password);
	boolean existsByKeycloakId(String keycloakId);
	boolean existsByEmail(String email);
	AllUsers findByKeycloakId(String keycloakId);
	AllUsers findByEmail(String email);
	
	Iterable<AllUsers> findAllByType(String type);
	
	@Query(value="SELECT * FROM all_users WHERE username = ?1 and password = ?2 " , nativeQuery = true)
	AllUsers findByLogin(String username,String password);
	
	@Query(value="select * from all_users where id= ?1",nativeQuery=true)
	AllUsers findByIds(Integer id);
	
	@Query(value="select * from all_users where type= ?1 and status= ?2",nativeQuery=true)
	Iterable<AllUsers> findByTypeAndStatus(String type ,String status);
	
	/*@Query(value="SELECT * FROM all_users RIGHT JOIN counceller ON all_users.id=counceller.id " , nativeQuery = true)
	Object findJoin();*/
	
	
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'all_users'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from all_users order by id desc limit 1",nativeQuery=true)
	AllUsers getLastRecord();
	
	
	
	
}
