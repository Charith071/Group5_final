package com.example.demo.function;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonFunction {

	public  String getCurrentdate() {
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentdate=LocalDate.now();
		return currentdate.toString();				
	}

	public  String[] split_sequence(String seq,String separetor ){
			String[] words=seq.split(separetor);
			return words;
	}

	public  Integer cal_age(String bdate){
			String[] words =bdate.split("/");
			String birth_year=words[0].trim();
			
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate currentdate=LocalDate.now();
			
			String[] words2=currentdate.toString().split("-") ; 
			String currentyear=words2[0].trim();
			Integer age= Integer.parseInt(currentyear)-Integer.parseInt(birth_year);
			return age;
	}
}
