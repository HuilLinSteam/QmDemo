package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tools.StringTool;
import com.service.ClazzService;
import com.opensymphony.xwork2.ActionSupport;

public class ClazzAction extends ActionSupport{
	private ClazzService service = new ClazzService();
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
