package com.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bean.Exam;
import com.bean.Page;

import com.bean.User;

import com.service.ExamService;
import com.tools.StringTool;
import com.opensymphony.xwork2.ActionSupport;

public class ExamAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	HttpServletRequest request;
	HttpServletResponse response;
	private ExamService service = new ExamService();
	Exam exam;
	public void teacherExamList() throws IOException {

		User user = (User) request.getSession().getAttribute("user");
		String number = user.getAccount();
		String result = service.teacherExamList(number);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write(result);
	}
	public String toExamListView() {
		return "toExamListView";
	}
	public void ExamList() throws IOException {
	
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//年级ID
		String gradeid = request.getParameter("gradeid");
		//班级ID
		String clazzid = request.getParameter("clazzid");
			
		Exam exam = new Exam();
			
		if(!StringTool.isEmpty(gradeid)){
			exam.setGradeid(Integer.parseInt(gradeid));
		}
		if(!StringTool.isEmpty(clazzid)){
			exam.setClazzid(Integer.parseInt(clazzid));
		}
			
		//获取数据
		String result = service.getExamList(exam, new Page(page, rows));
		//返回数据
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(result);	
	}
	
	//add
	public void addExam() throws IOException {
		try {
			service.addExam(exam);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("success");
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	//delete
	public void deleteExam() throws IOException {
		//获取要删除的id
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			service.deleteExam(id);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
	public void addLitterExam() throws IOException {
		//获取参数名
		Enumeration<String> pNames = request.getParameterNames();
		Exam exam = new Exam();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				BeanUtils.setProperty(exam, pName, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(exam);
			service.addExam(exam);
//>>>>>>> 3554a96118cb1e41ee7939e1c97c6841fa0a3d22
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}
//<<<<<<< HEAD
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response=arg0;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
		
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	
	public void studentExamList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		String number = user.getAccount();
		
		String result = service.studentExamList(number);
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().write(result);
	}
	
}
