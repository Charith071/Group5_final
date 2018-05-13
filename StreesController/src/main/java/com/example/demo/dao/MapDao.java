package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Map;

public interface MapDao extends CrudRepository<Map, Long>{
	boolean existsByUserId(Long user_id);
	boolean existsByCouncellerId(Long counceller_id);
	Long countByUserId(Long user_id);
	Long countByCouncellerId(Long counceller_id);
	
	
	@Query(value="select * from map where userId= ?1",nativeQuery=true)
	Map findByUserIds(Integer id);
	
	@Query(value="select * from map where councellerId= ?1",nativeQuery=true)
	Map findByUserCouncellerId(Integer id);
	
}
