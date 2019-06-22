package com.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考试类
 * 
 * 考试分为年级统考和平时考试两种
 * 年级统考由管理员添加一次考试
 * 平时考试由科任老师添加考试
 * @author bojiangzhou
 *
 */
public class Exam {
	
	/**
	 * 考试类型：年级统考
	 */
	public static final int EXAM_GRADE_TYPE = 1;
	
	/**
	 * 考试类型：平时考试
	 */
	public static final int EXAM_NORMAL_TYPE = 2;
	
	private int id; 
	

	private String name; 
	
	private Date time; 
	
	private String etime; 
	
	private String remark; 
	
	private Grade grade; 
	
	private int gradeid; 
	
	private Clazz clazz; 
	
	private int clazzid; 
	
	private Course course; 
	
	private int courseid; 
	
	private int type = EXAM_GRADE_TYPE; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年  MM 月  dd 日");
		this.etime = sdf.format(time);
		this.time = time;
	}
	
	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.time = sdf.parse(etime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.etime = etime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public int getGradeid() {
		return gradeid;
	}

	public void setGradeid(int gradeid) {
		Grade grade = new Grade();
		grade.setId(gradeid);
		this.grade = grade;
		this.gradeid = gradeid;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public int getClazzid() {
		return clazzid;
	}

	public void setClazzid(int clazzid) {
		Clazz clazz = new Clazz();
		clazz.setId(clazzid);
		this.clazz = clazz;
		this.clazzid = clazzid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		Course course = new Course();
		course.setId(courseid);
		this.course = course;
		this.courseid = courseid;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", time=" + time + ", etime=" + etime + ", remark=" + remark
				+ ", grade=" + grade + ", gradeid=" + gradeid + ", clazz=" + clazz + ", clazzid=" + clazzid
				+ ", course=" + course + ", courseid=" + courseid + ", type=" + type + "]";
	}
	
}
