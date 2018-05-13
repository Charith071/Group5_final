package com.example.demo.extra;

public class LoginOutPut {
	private Long id;
	private String name;
	private Integer age;
	private String address;
	private String birth_date;
	private String create_date;
	private String email;
	private String gender;
	private String username;
	private String password;
	private Integer phone_number;
	private String type;
	private String status;
	
	private Double gps_location;
	private String certificate;
	private String qualification;
	private String profile_pic_name;
	
	private Integer guadiant_phone_no;
	private String job;
	private Float stress_level;
	
	
	
	
	public LoginOutPut(Long id, String name, Integer age, String address, String birth_date, String create_date,
			String email, String gender, String username, String password, Integer phone_number, String type,
			String status, Double gps_location, String profile_pic_name, Integer guadiant_phone_no, String job,
			Float stress_level) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.birth_date = birth_date;
		this.create_date = create_date;
		this.email = email;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
		this.type = type;
		this.status = status;
		this.gps_location = gps_location;
		this.profile_pic_name = profile_pic_name;
		this.guadiant_phone_no = guadiant_phone_no;
		this.job = job;
		this.stress_level = stress_level;
	}
	
	
	
	
	public LoginOutPut() {
		super();
	}




	public LoginOutPut(Long id, String name, Integer age, String address, String birth_date, String create_date,
			String email, String gender, String username, String password, Integer phone_number, String type,
			String status, Double gps_location, String certificate, String qualification, String profile_pic_name) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.birth_date = birth_date;
		this.create_date = create_date;
		this.email = email;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
		this.type = type;
		this.status = status;
		this.gps_location = gps_location;
		this.certificate = certificate;
		this.qualification = qualification;
		this.profile_pic_name = profile_pic_name;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getGps_location() {
		return gps_location;
	}
	public void setGps_location(Double gps_location) {
		this.gps_location = gps_location;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getProfile_pic_name() {
		return profile_pic_name;
	}
	public void setProfile_pic_name(String profile_pic_name) {
		this.profile_pic_name = profile_pic_name;
	}
	public Integer getGuadiant_phone_no() {
		return guadiant_phone_no;
	}
	public void setGuadiant_phone_no(Integer guadiant_phone_no) {
		this.guadiant_phone_no = guadiant_phone_no;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Float getStress_level() {
		return stress_level;
	}
	public void setStress_level(Float stress_level) {
		this.stress_level = stress_level;
	}
	
	
	
	

}
