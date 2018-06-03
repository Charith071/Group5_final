package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Changes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String updatedTableName;
	private Long updatedRecodeId;
	private String updatedStatus;
	private Long affectedUserId;
	private String is_notification_send;
	private String type;
	private String notificationSendDate;
	private String addDate;
	
	
	
	
	
	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public Changes() {
		super();
	}

	public String getNotificationSendDate() {
		return notificationSendDate;
	}

	public void setNotificationSendDate(String notificationSendDate) {
		this.notificationSendDate = notificationSendDate;
	}

	public Changes(String updatedTableName, Long updatedRecodeId, String updatedStatus, Long affectedUserId,
			String is_notification_send, String type,String addDate) {
		super();
		this.updatedTableName = updatedTableName;
		this.updatedRecodeId = updatedRecodeId;
		this.updatedStatus = updatedStatus;
		this.affectedUserId = affectedUserId;
		this.is_notification_send = is_notification_send;
		this.type = type;
		this.addDate=addDate;
	}

	public Long getId() {
		return id;
	}
	
	public String getUpdatedTableName() {
		return updatedTableName;
	}
	public void setUpdatedTableName(String updatedTableName) {
		this.updatedTableName = updatedTableName;
	}
	public Long getUpdatedRecodeId() {
		return updatedRecodeId;
	}
	public void setUpdatedRecodeId(Long updatedRecodeId) {
		this.updatedRecodeId = updatedRecodeId;
	}
	public String getUpdatedStatus() {
		return updatedStatus;
	}
	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	public Long getAffectedUserId() {
		return affectedUserId;
	}
	public void setAffectedUserId(Long affectedUserId) {
		this.affectedUserId = affectedUserId;
	}
	public String getIs_notification_send() {
		return is_notification_send;
	}
	public void setIs_notification_send(String is_notification_send) {
		this.is_notification_send = is_notification_send;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
}
