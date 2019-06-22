package com.action;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bean.Teacher;
import com.bean.User;
import com.bean.Clazz;
import com.bean.Grade;
import com.bean.Page;
import com.service.TeacherService;
import com.tools.StringTool;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private TeacherService service = new TeacherService();
	HttpServletRequest request;
	HttpServletResponse response;
	private Teacher teach;
	

	private String course;
	
	public String toPersonal(){
		
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
		service.editTeacherPersonal(teach);
		response.getWriter().write("success");
	}
	public void teacherList() throws IOException {
		
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
	
		if(course!=null&&!course.equals("")) {
			String [] courses  = course.split(",");
			teach.setCourse(courses);
		}
		try {
			service.addTeacher(teach);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	
	public void editTeacher() throws IOException {
		if(course!=null&&!course.equals("")) {
			String [] courses  = course.split(",");
			teach.setCourse(courses);
		}
		try {
			service.editTeacher(teach);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	public void getTeacher() throws IOException {
		
		
		User user = (User) request.getSession().getAttribute("user");
		String number = user.getAccount();
		String result = service.getTeacherResult(number);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write(result);
	}
	
	
	public void getExamClazz() throws IOException {
		
		int gradeid = Integer.parseInt(request.getParameter("gradeid"));
		Grade grade = new Grade();
		grade.setId(gradeid);
		
		User user = (User) request.getSession().getAttribute("user");
		
		String result = service.getExamClazz(user.getAccount(), grade);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(result);
	}
	
	public void getExamCourse() throws IOException {
		
		int gradeid = Integer.parseInt(request.getParameter("gradeid"));
		Grade grade = new Grade();
		grade.setId(gradeid);
		String scid = request.getParameter("clazzid");
		if(StringTool.isEmpty(scid)){
			response.getWriter().write("");
			return;
		}
		int clazzid = Integer.parseInt(scid);
		Clazz clazz = new Clazz();
		clazz.setId(clazzid);
		
		User user = (User) request.getSession().getAttribute("user");
		
		String result = service.getExamClazz(user.getAccount(), grade, clazz);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write(result);
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response =  arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request =  arg0;
	}
	
	public Teacher getTeach() {
		return teach;
	}

	public void setTeach(Teacher teach) {
		this.teach = teach;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
}
