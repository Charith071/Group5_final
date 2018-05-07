package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Map {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long user_id; //(foreign key os user.id)
	private Long counceller_id;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getCounceller_id() {
		return counceller_id;
	}
	public void setCounceller_id(Long counceller_id) {
		this.counceller_id = counceller_id;
	}
	
	
	
}
