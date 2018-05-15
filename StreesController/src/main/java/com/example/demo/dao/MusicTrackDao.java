package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.MusicTrack;

public interface MusicTrackDao extends CrudRepository<MusicTrack, Long>{

	boolean existsByName(String  name);
	
	@Query(value="select * from music_track where id= ?1",nativeQuery=true)
	MusicTrack findByIds(Integer id);
}
