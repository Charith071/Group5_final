package com.example.demo.extra;

public class JsonResponse {
	private String response;
	private String res_status;
	
	
	public JsonResponse() {
		super();
	}
	public JsonResponse(String response, String res_status) {
		super();
		this.response = response;
		this.res_status = res_status;
	}
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
	
	

	
	
}
