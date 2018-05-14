package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Messages;

public interface MessagesDao extends CrudRepository<Messages,Long>{

}
