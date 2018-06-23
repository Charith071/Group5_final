package com.example.demo.extra;

import java.util.List;

import com.example.demo.Entity.Tips;

public class GrtTipsOut {

	private String response;
	private String res_status;
	private List<Tips> data;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getRes_status() {
		return res_status;
	}
	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}
	public List<Tips> getData() {
		return data;
	}
	public void setData(List<Tips> data) {
		this.data = data;
	}
	
	
}
