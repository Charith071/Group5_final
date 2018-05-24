package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.TableInfomation;

public interface TableInformationDao extends CrudRepository<TableInfomation, Long> {

	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'table_infomation'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from table_infomation where id= ?1",nativeQuery=true)
	TableInfomation findByIds(Integer id);
	
	@Query(value="select * from table_infomation order by id desc limit 1",nativeQuery=true)
	TableInfomation getLastRecord();
}
