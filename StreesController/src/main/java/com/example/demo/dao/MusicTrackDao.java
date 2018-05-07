package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.MusicTrack;

public interface MusicTrackDao extends CrudRepository<MusicTrack, Long>{

}
