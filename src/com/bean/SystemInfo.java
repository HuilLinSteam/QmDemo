package com.bean;

public class SystemInfo {
private int id; 
	
	private String schoolName; 

	private int forbidTeacher; 
	
	private int forbidStudent; 
	
	private String noticeTeacher;
	
	private String noticeStudent; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getForbidTeacher() {
		return forbidTeacher;
	}

	public void setForbidTeacher(int forbidTeacher) {
		this.forbidTeacher = forbidTeacher;
	}

	public int getForbidStudent() {
		return forbidStudent;
	}

	public void setForbidStudent(int forbidStudent) {
		this.forbidStudent = forbidStudent;
	}

	public String getNoticeTeacher() {
		return noticeTeacher;
	}

	public void setNoticeTeacher(String noticeTeacher) {
		this.noticeTeacher = noticeTeacher;
	}

	public String getNoticeStudent() {
		return noticeStudent;
	}

	public void setNoticeStudent(String noticeStudent) {
		this.noticeStudent = noticeStudent;
	}
}
