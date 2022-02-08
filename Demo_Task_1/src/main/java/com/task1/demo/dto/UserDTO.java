package com.task1.demo.dto;

public class UserDTO {

	private Integer userid;

	private String username;

	private Integer age;
	
	private boolean enabled;
	
	private Address address;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer userid, String username, Integer age, boolean enabled) {
		this.userid=userid;
		this.username=username;
		this.age=age;
		this.enabled=enabled;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
