package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Booking_details {
	@Id
	private Long id; //(foreign keu of booking_request.id);
	private String date_time;
	private String location;
	private String details;
	private String status;
	private Double payment;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	
	
	
}
