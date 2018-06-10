package com.example.demo.extra;

import java.util.ArrayList;
import java.util.List;

public class GetAllCouncellerOut {

	private String res_status;
	private List<UserdetailsResponse> list=new ArrayList<UserdetailsResponse>();
	
	public String getRes_status() {
		return res_status;
	}
	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}
	public List<UserdetailsResponse> getList() {
		return list;
	}
	public void setList(List<UserdetailsResponse> list) {
		this.list = list;
	}
	
	
	
}
