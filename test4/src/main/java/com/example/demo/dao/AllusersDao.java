package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Allusers;

public interface AllusersDao extends CrudRepository<Allusers, Integer> {
	boolean existsByUsername(String username);
	boolean existsByPassword(String password);
	
	@Query(value="SELECT * FROM allusers WHERE username = ?1 and password = ?2 " , nativeQuery = true)
	Allusers findByLogin(String username,String password);
	
	@Query(value="select * from allusers where id= ?1",nativeQuery=true)
	Allusers findByIds(Integer id);
	
}
