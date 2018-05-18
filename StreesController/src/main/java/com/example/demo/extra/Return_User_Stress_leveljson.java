package com.example.demo.extra;

public class Return_User_Stress_leveljson {

	private String stress_level;
	private String response;
	private String res_status;
	
	
	public Return_User_Stress_leveljson() {
		super();
	}
	public Return_User_Stress_leveljson(String stress_level, String response, String res_status) {
		super();
		this.stress_level = stress_level;
		this.response = response;
		this.res_status = res_status;
	}
	public String getStress_level() {
		return stress_level;
	}
	public void setStress_level(String stress_level) {
		this.stress_level = stress_level;
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
