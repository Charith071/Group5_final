package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.StressLevelHistory;

public interface StressLevelHistoryDao extends CrudRepository<StressLevelHistory, Long>{

}
