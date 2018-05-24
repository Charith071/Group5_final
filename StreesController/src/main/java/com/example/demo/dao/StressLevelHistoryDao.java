package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.StressLevelHistory;

public interface StressLevelHistoryDao extends CrudRepository<StressLevelHistory, Long>{
		
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'stress_level_history'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from stress_level_history order by id desc limit 1",nativeQuery=true)
	StressLevelHistory getLastRecord();
}
