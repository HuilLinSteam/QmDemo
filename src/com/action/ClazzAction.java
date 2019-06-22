package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tools.StringTool;
import com.service.ClazzService;
import com.bean.Page;
import com.opensymphony.xwork2.ActionSupport;

public class ClazzAction extends ActionSupport{
	private ClazzService service = new ClazzService();

	//以下为action
	//转发到年级列表页
	public String toClazzListView(){
		return SUCCESS;
	}
	//删除班级
	public void deleteClazz() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int clazzid = Integer.parseInt(request.getParameter("clazzid"));
		try {
			service.deleteClazz(clazzid);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}		
	}
	//添加班级
	public void addClazz() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = request.getParameter("name");
		
		String gradeid = request.getParameter("gradeid");
		//String gradeid = Long.toString(gradeid);
		service.addClazz(name, gradeid);
		response.getWriter().write("success");		
	}
	//获取所有班级详细信息
	public void clazzDetailList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//获取参数
		String gradeid = request.getParameter("gradeid");
		//String gradeid = Long.toString(clazz.getGradeid());
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		response.setContentType("text/html;charset=UTF-8");
		String result = service.getClazzDetailList(gradeid, new Page(page, rows));
		//返回数据
        response.getWriter().write(result);		
	}
	
	
	private void addClazz(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String gradeid = request.getParameter("gradeid");
	//	String gradeid = Long.toString(gradeid);
		response.setContentType("text/html;charset=UTF-8");
		service.addClazz(name, gradeid);
		response.getWriter().write("success");
	}
	public void clazzList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		//获取参数
		String gradeid = request.getParameter("gradeid");
		
		System.out.println(gradeid);
		if(StringTool.isEmpty(gradeid)){
			return;
		}
		
		String result = service.getClazzList(gradeid);
		//返回数据
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
}
