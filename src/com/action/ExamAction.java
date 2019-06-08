package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.User;
import com.service.ExamService;
import com.opensymphony.xwork2.ActionSupport;

public class ExamAction extends ActionSupport{
	private ExamService service = new ExamService();
	public void teacherExamList() throws IOException {
		//获取当前用户
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
		String number = user.getAccount();
		String result = service.teacherExamList(number);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write(result);
	}
	
}
