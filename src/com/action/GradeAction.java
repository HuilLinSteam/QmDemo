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
	
	public String toGradeListView(){
		return SUCCESS;
	}
	//删除年级
	public void deleteGrade() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		int gradeid = Integer.parseInt(request.getParameter("gradeid"));
		try {
			service.deleteGrade(gradeid);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}		
	}
	//添加年级
	public void addGrade() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] clazzids = request.getParameterValues("clazzid");
		String name = request.getParameter("name");
		
		service.addGrade(name, clazzids);
        response.getWriter().write("success");	
	}
}
