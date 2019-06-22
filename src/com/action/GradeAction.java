package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.service.GradeService;
import com.opensymphony.xwork2.ActionSupport;


public class GradeAction extends ActionSupport{
	
	private GradeService service = new GradeService();
	
	public void gradeList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String course = request.getParameter("course");
		
		String result = service.getGradeList(course);

		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
	
}
