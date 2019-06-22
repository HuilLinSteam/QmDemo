package com.action;


import com.service.CourseService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Course;
import com.opensymphony.xwork2.ActionSupport;

public class CourseAction extends ActionSupport{
	private CourseService service = new CourseService();
	
	public void courseList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String gradeid = request.getParameter("gradeid");
		
		String result = service.getCourseList(gradeid);
		//ËøîÂõûÊï∞ÊçÆ
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
	
	//“‘œ¬Œ™action
	public void deleteCourse() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		try {
			service.deleteClazz(courseid);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}		
	}
	public void addCourse() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = request.getParameter("name");
		Course course = new Course();
		course.setName(name);
		service.addCourse(course);
		response.getWriter().write("success");		
	}	

	public String toCourseListView() {
		return SUCCESS;
	}
}
