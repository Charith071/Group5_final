package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MusicTrack {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double max_stress_level;
	private Double min_stress_level;
	private Long counceller_id;  //(foreign key of counceller.id)
	private String name;
	private String description;
	private String date_time;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	
	
	public Double getMax_stress_level() {
		return max_stress_level;
	}


	public void setMax_stress_level(Double max_stress_level) {
		this.max_stress_level = max_stress_level;
	}


	public Double getMin_stress_level() {
		return min_stress_level;
	}


	public void setMin_stress_level(Double min_stress_level) {
		this.min_stress_level = min_stress_level;
	}


	public Long getCounceller_id() {
		return counceller_id;
	}
	public void setCounceller_id(Long counceller_id) {
		this.counceller_id = counceller_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
