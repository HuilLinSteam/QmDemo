package com.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.bean.Page;
import com.bean.Student;
import com.bean.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StudentService;
import com.tools.StringTool;

public class StudentAction extends ActionSupport{
	//创建服务层对象
		private StudentService service = new StudentService();
		private Student std;
	
	
	//发到成绩查询页面
	public String toExamStudentView() {
		return "examStudentList";
	}
	//转发到成绩查询页面中查看成绩，所有学生的成绩信息
	public String toStudentNoteListView() {
		return "studentNoteList";
	}
	 public void studentClazzList() throws IOException {
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse response=ServletActionContext.getResponse();
		 User user = (User) request.getSession().getAttribute("user");
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//获取数据
		String result =service.getStudentList(user.getAccount(), new Page(page, rows));
		//返回数据
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
	 
	public String toPersonal() throws ServletException, IOException {
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse response=ServletActionContext.getResponse();
		 User user = (User) request.getSession().getAttribute("user");
			Student student = service.getStudent(user.getAccount());
			request.getSession().setAttribute("userDetail", student);
			return "toStudentPersonalView";
		}
	
	public void editStudent() throws IOException {
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse response=ServletActionContext.getResponse();
		//获取参数名
		/*Enumeration<String> pNames = request.getParameterNames();
		Student student = new Student();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				BeanUtils.setProperty(student, pName, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}*/
		service.editStudent(std);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write("success");
	}
	
	//管理员层
	//学生信息管理————学生列表
	public String toStudentListView() {
		return "toStudentListView";
	}
	public void studentList() throws IOException {
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse response=ServletActionContext.getResponse();
		 //年级ID
		String gradeid = request.getParameter("gradeid");
		//班级ID
		String clazzid = request.getParameter("clazzid");
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		//封装参数
		Student student = new Student();
		if(!StringTool.isEmpty(gradeid)){
			student.setGradeid(Integer.parseInt(gradeid));
		}
		if(!StringTool.isEmpty(clazzid)){
			student.setClazzid(Integer.parseInt(clazzid));
		}
		
		//获取数据
		String result = service.getStudentList(student, new Page(page, rows));
		//返回数据
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
	
	public void deleteStudent() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse response=ServletActionContext.getResponse();
		//获取要删除的学号
		String[] numbers = request.getParameterValues("numbers[]");
		String[] ids = request.getParameterValues("ids[]");
		try {
			service.deleteStudent(ids, numbers);
			response.setContentType("text/html;charset=UTF-8"); 
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	public void addStudent() throws IOException {
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse response=ServletActionContext.getResponse();
		
		 //获取参数名
	/*	Enumeration<String> pNames = request.getParameterNames();
		Student student = new Student();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				BeanUtils.setProperty(student, pName, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}*/
		service.addStudent(std);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write("success");
	}
	public Student getStd() {
		return std;
	}
	public void setStd(Student std) {
		this.std = std;
	}
	
	
	
	
}
