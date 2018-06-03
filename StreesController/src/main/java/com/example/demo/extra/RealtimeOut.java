package com.example.demo.extra;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.Changes;

public class RealtimeOut {

	private boolean status;
	private List<Changes> list=new ArrayList<Changes>();
	
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public List<Changes> getList() {
		return list;
	}

	public void setList(List<Changes> list) {
		this.list = list;
	}
	
	
	
	
	
	
	
	
	
	
}
