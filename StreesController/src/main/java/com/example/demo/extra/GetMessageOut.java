package com.example.demo.extra;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.Messages;

public class GetMessageOut {

	private String res_status;
	
	private List<Messages> list=new ArrayList<Messages>();

	

	public String getRes_status() {
		return res_status;
	}

	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}

	public List<Messages> getList() {
		return list;
	}

	public void setList(List<Messages> list) {
		this.list = list;
	}
	
	
}
