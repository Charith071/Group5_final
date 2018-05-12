package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Counceller;;




public interface AlluserDao extends CrudRepository<AllUsers, Long>{
	boolean existsByUsername(String username);
	boolean existsByPassword(String password);
	Iterable<AllUsers> findAllByType(String type);
	
	@Query(value="SELECT * FROM all_users WHERE username = ?1 and password = ?2 " , nativeQuery = true)
	AllUsers findByLogin(String username,String password);
	
	@Query(value="select * from all_users where id= ?1",nativeQuery=true)
	AllUsers findByIds(Integer id);
	
	/*@Query(value="SELECT * FROM all_users RIGHT JOIN counceller ON all_users.id=counceller.id " , nativeQuery = true)
	Object findJoin();*/
}
