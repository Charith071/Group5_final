package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StressLevelHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long user_id; //(foreign key of user.id)
	private String date_time;
	private Double stress_level;
	
	
	public Long getId() {
		return id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public Double getStress_level() {
		return stress_level;
	}
	public void setStress_level(Double stress_level) {
		this.stress_level = stress_level;
	}
	
}
