package com.action;

import com.service.SystemService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.SystemInfo;
import com.bean.User;
import com.opensymphony.xwork2.ActionSupport;

public class SystemAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
    
	private SystemService service = new SystemService();
	//判断登录的身份，要在struts.xml里写明
	public String toAdminView(){
		return "toAdminView";
	}
	
	public String toStudentView(){
		return "toStudentView";
	}
	
	public String toTeacherView(){
		return "toTeacherView";
	}
	
	public String toAdminPersonalView(){
		return "adminPersonal";
	}
	
	public String getAllAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		
		if("AllAccount".equalsIgnoreCase(method)){ 
			allAccount(request, response);
		} 
		return null;
	}
	
	public void editSystemInfo() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		
		SystemInfo sys = service.editSystemInfo(name, value);
		
    	request.getServletContext().setAttribute("systemInfo", sys);
    	response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write("success");
	}

	public void editPasswod() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = new User();
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		service.editPassword(user);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write("success");
	}


	public String loginOut() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.getSession().removeAttribute("user");
		return "loginOut";
	}
	
	private void allAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = service.getAccountList();
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}

	
	
	
	
}
