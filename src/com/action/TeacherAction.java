package com.action;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.bean.Teacher;
import com.bean.User;
import com.bean.Page;
import com.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherAction extends ActionSupport{
	private TeacherService service = new TeacherService();
	
	public String toPersonal(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		Teacher teacher = service.getTeacher(user.getAccount());
		request.getSession().setAttribute("userDetail", teacher);
		return "teacherPersonal";
	}
	
	public String toTeacherNoteListView(){
		return "toTeacherNoteListView";
		
	}
	public String toExamTeacherView() {
		return "toExamTeacherView";
	}
	public String toTeacherListView(){
		return "toTeacherListView";
	}
	public void editTeacherPersonal() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取参数名
		Enumeration<String> pNames = request.getParameterNames();
		Teacher teacher = new Teacher();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				BeanUtils.setProperty(teacher, pName, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		service.editTeacherPersonal(teacher);
		response.getWriter().write("success");
	}
	public void teacherList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		//获取数据
		String result = service.getTeacherList(new Page(page, rows));
		//返回数据
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
	
	public void deleteTeacher() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取要删除的学号
		String[] ids = request.getParameterValues("ids[]");
		String[] numbers = request.getParameterValues("numbers[]");
		try {
			service.deleteTeacher(ids, numbers);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	
	public void addTeacher() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取参数名
		Enumeration<String> pNames = request.getParameterNames();
		Teacher teacher = new Teacher();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			System.out.println(pName);
			System.out.println(value);
			try {
				if("course[]".equals(pName)){//设置所选课程
					BeanUtils.setProperty(teacher, "course", request.getParameterValues("course[]"));
				} else{
					BeanUtils.setProperty(teacher, pName, value);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			service.addTeacher(teacher);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	
	public void editTeacher() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取参数名
		Enumeration<String> pNames = request.getParameterNames();
		Teacher teacher = new Teacher();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				if("course[]".equals(pName)){//设置所选课程
					BeanUtils.setProperty(teacher, "course", request.getParameterValues("course[]"));
				} else{
					BeanUtils.setProperty(teacher, pName, value);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			service.editTeacher(teacher);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	public void getTeacher() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		String number = user.getAccount();
		String result = service.getTeacherResult(number);
		response.getWriter().write(result);
	}
	
}
