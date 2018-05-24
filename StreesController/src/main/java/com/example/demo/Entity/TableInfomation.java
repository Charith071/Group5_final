package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class TableInfomation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String tableName;
	private String lastUpdateDateTime;
	private String numberOfRows;
	private String idOfLastRow;
	
	
	
	
	
	public String getIdOfLastRow() {
		return idOfLastRow;
	}

	public void setIdOfLastRow(String idOfLastRow) {
		this.idOfLastRow = idOfLastRow;
	}

	public Long getId() {
		return id;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}
	public void setLastUpdateDateTime(String lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public String getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(String numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	

	
	
	
	
}
