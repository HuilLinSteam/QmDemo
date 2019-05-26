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
	public String check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		
		if("LoginOut".equals(method)){
			loginOut(request, response);
		} else if("toAdminView".equalsIgnoreCase(method)){ 
			request.getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
		} else if("toStudentView".equals(method)){ 
			request.getRequestDispatcher("/WEB-INF/view/student/student.jsp").forward(request, response);
		} else if("toTeacherView".equals(method)){ 
			request.getRequestDispatcher("/WEB-INF/view/teacher/teacher.jsp").forward(request, response);
		} else if("toAdminPersonalView".equals(method)){ 
			request.getRequestDispatcher("/WEB-INF/view/admin/adminPersonal.jsp").forward(request, response);
		}
		return SUCCESS;
	}
	
	
	public String getAllAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		
		if("AllAccount".equalsIgnoreCase(method)){ 
			allAccount(request, response);
		} else if("EditPasswod".equals(method)){ 
			editPasswod(request, response);
		} else if("EditSystemInfo".equals(method)){ 
			editSystemInfo(request, response);
		}
		return null;
	}
	
	private void editSystemInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		
		SystemInfo sys = service.editSystemInfo(name, value);
		
    	request.getServletContext().setAttribute("systemInfo", sys);
    	
		response.getWriter().write("success");
	}

	private void editPasswod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		service.editPassword(user);
		response.getWriter().write("success");
	}


	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("user");
		String contextPath = request.getContextPath();
		
		response.sendRedirect(contextPath+"/index.jsp");
	}
	
	private void allAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = service.getAccountList();
		
        response.getWriter().write(result);
	}
	public String check() throws ServletException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		check(request, response);
		return null;
	}
	
}
