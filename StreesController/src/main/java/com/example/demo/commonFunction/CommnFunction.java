package com.example.demo.commonFunction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class CommnFunction {
		//====encript any string==========
	public String string_encript(String word) {
		String Encripted_word=Base64.getEncoder().encodeToString(word.getBytes());
		return Encripted_word;
		
		
		
	}
	
	//=========decrip any encripted string==============
	public String string_decode(String Encripted_word) {
		byte[] decript_word=Base64.getDecoder().decode(Encripted_word);
		return new String(decript_word);
		
	}
	
	
	//========return currentdate===================
	public  String getCurrentdate() {
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentdate=LocalDate.now();
		return currentdate.toString();				
	}
	
	//============calculate age=========================
	public  Integer cal_age(String bdate){
		String[] words =bdate.split("-");
		String birth_year=words[0].trim();
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentdate=LocalDate.now();
		
		String[] words2=currentdate.toString().split("-") ; 
		String currentyear=words2[0].trim();
		Integer age= Integer.parseInt(currentyear)-Integer.parseInt(birth_year);
		return age;
	}
	
	
}
