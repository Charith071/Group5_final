package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Tips;

public interface TipsDao extends CrudRepository<Tips, Long>{
	
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'tips'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from (select * from tips order by id desc limit ?1) t order by t.id asc",nativeQuery=true)
	Iterable<Tips> getLastRecords(Integer n);
	
	@Query(value="select * from tips order by id desc limit 1",nativeQuery=true)
	Tips getLastRecord();
	

}
