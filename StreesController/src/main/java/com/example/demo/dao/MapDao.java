package com.example.demo.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Map;
import com.mysql.jdbc.IterateBlock;

public interface MapDao extends CrudRepository<Map, Long>{
	boolean existsByUserId(Long user_id);
	boolean existsByCouncellerId(Long counceller_id);
	Long countByUserId(Long user_id);
	Long countByCouncellerId(Long counceller_id);
	
	
	@Query(value="select * from map where user_id= ?1",nativeQuery=true)
	Iterable<Map> findByUserIds(Integer id);
	
	@Query(value="select * from map where counceller_id= ?1",nativeQuery=true)
	Map findByUserCouncellerId(Integer id);
	
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'map'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from map order by id desc limit 1",nativeQuery=true)
	Map getLastRecord();
	
	@Query(value="select * from map where counceller_id=?1 and user_id=?2",nativeQuery=true)
	Map getInstanceBycouncellerId_and_userId(Integer councellerId,Integer userId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
