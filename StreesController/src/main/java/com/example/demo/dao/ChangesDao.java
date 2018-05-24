package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Changes;

public interface ChangesDao extends CrudRepository<Changes,Long>{
	
	boolean existsByAffectedUserId(Long affected_id);
	Long countByAffectedUserId(Long affected_user_id);
	Optional<Changes> findByAffectedUserId(Long id);

	@Query(value="select * from changes where id= ?1",nativeQuery=true)
	Changes findByIds(Integer id);
	
	@Query(value="select * from changes where affected_user_id= ?1 and is_notification_send= ?2",nativeQuery=true)
	Iterable<Changes> getAllInstanceBy_affecctec_id_and_not_send(Long affected_user_id,String value);
	
	
	
}
