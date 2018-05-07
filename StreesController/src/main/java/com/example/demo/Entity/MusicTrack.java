package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MusicTrack {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Double stress_level;
	private Long counceller_id;  //(foreign key of counceller.id)
	private String name;
	private String description;
	private String date_time;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getStress_level() {
		return stress_level;
	}
	public void setStress_level(Double stress_level) {
		this.stress_level = stress_level;
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
