package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.MusicTrack;

public interface MusicTrackDao extends CrudRepository<MusicTrack, Long>{

	boolean existsByName(String  name);
	
	@Query(value="select * from music_track where id= ?1",nativeQuery=true)
	MusicTrack findByIds(Integer id);
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'music_track'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from music_track order by id desc limit 1",nativeQuery=true)
	MusicTrack getLastRecord();
}
