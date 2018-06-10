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
	
	@Query(value="select * from messages where receiver = ?1 OR sender= ?1 order by id desc limit ?2",nativeQuery=true)
	Iterable<Messages>  get_LastRecords_by__id(Long id,Integer n);
	
	@Query(value="select * from messages where receiver = ?1 OR sender= ?1",nativeQuery=true)
	Iterable<Messages>  get_All_Records_by__id(Long id);
	
	@Query(value="select * from messages where receiver = ?1 AND sender= ?2 OR receiver =?1 AND sender =?2 order by id desc",nativeQuery=true)
	Iterable<Messages>  get_All_Records_by__two_ids(Long id1,Long id2);
	
	@Query(value="select * from messages where receiver = ?1 AND sender= ?2 OR receiver =?1 AND sender =?2 order by id desc limit ?3",nativeQuery=true)
	Iterable<Messages>  get_LastRecords_by__two_ids(Long id1,Long id2,Integer n);
}
