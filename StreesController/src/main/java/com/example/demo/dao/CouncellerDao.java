package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Counceller;

public interface CouncellerDao extends CrudRepository<Counceller, Long>{
	/*boolean existsByUsername(String username);
	boolean existsByPassword(String password);*/
	
	
	
	@Query(value="select * from counceller where id= ?1",nativeQuery=true)
	Counceller findByIds(Integer id);
}
