package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Consulter;


@Repository
public interface ConsulterDao extends CrudRepository<Consulter, Integer> {
	boolean existsByUsername(String username);
	boolean existsByPassword(String password);
	boolean existsById(Integer id);
	
	@Query(value="SELECT * FROM consulter WHERE username = ?1 and password = ?2", nativeQuery = true)
	Consulter  findByLogin(String username ,String password);
	
	@Query(value="SELECT * FROM Consulter WHERE id = ?1", nativeQuery = true)
	Consulter findByIds(Integer id);
}
