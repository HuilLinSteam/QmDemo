package com.bean;

public class User {
	//���徲̬����
	public static final int USER_ADMIN = 1;
	public static final int USER_STUDENT = 2;
	public static final int USER_TEACHER = 3;
	private int id;
	private String account;
	private String password = "111111";
	private String name;

	private int type;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name + ", type="
				+ type + "]";
	}
	
	
}
