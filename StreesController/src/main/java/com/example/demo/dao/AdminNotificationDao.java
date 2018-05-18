package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AdminNotification;


public interface AdminNotificationDao extends CrudRepository<AdminNotification, Long>{

	@Query(value="select * from admin_notification where id= ?1",nativeQuery=true)
	AdminNotification findByIds(Integer id);
}
