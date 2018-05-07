package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.AllUsers;

public interface AlluserDao extends CrudRepository<AllUsers, Long>{
	
}
