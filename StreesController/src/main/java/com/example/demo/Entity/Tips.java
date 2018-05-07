package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tips {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long counceller_id;  //(foreign key of counceler.id)
	private String date_time;
	private String tip;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCounceller_id() {
		return counceller_id;
	}
	public void setCounceller_id(Long counceller_id) {
		this.counceller_id = counceller_id;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
