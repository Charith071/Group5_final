package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AdminNotification;


public interface AdminNotificationDao extends CrudRepository<AdminNotification, Long>{

	@Query(value="select * from admin_notification where id= ?1",nativeQuery=true)
	AdminNotification findByIds(Integer id);
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'admin_notification'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from admin_notification order by id desc limit 1",nativeQuery=true)
	AdminNotification getLastRecord();
	
}
