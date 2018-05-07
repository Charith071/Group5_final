package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.User;

public interface UserDao extends CrudRepository<User, Long>{

}
