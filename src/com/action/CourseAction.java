package com.action;

import com.service.CourseService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CourseAction extends ActionSupport{
	private CourseService service = new CourseService();
	
	public void courseList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String gradeid = request.getParameter("gradeid");
		
		String result = service.getCourseList(gradeid);
		//返回数据
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
}
