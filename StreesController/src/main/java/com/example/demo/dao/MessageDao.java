package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Message;

public interface MessageDao extends CrudRepository<Message, Long> {

}
