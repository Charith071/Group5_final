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
	private Long userId; //(foreign key os user.id)
	private Long councellerId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCouncellerId() {
		return councellerId;
	}
	public void setCouncellerId(Long councellerId) {
		this.councellerId = councellerId;
	}
	
	
	
	
	
	
	
}
