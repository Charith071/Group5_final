package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Messages;

public interface MessagesDao extends CrudRepository<Messages,Long>{

	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'messages'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from messages order by id desc limit 1",nativeQuery=true)
	Messages getLastRecord();
	
	@Query(value="select * from messages where receiver = ?1 order by id desc limit 1",nativeQuery=true)
	Messages getLastRecord_by_receveir_id(Long id);
}
