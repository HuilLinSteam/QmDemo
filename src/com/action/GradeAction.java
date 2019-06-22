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
		//鑾峰彇鍙傛暟锛歝ourse=course锛氭槸鍚﹁幏鍙栧勾绾х殑鍚屾椂杩斿洖璇ュ勾绾т笅鐨勮绋�
		String course = request.getParameter("course");
		
		String result = service.getGradeList(course);
		//杩斿洖鏁版嵁
		response.setContentType("text/html;charset=UTF-8"); 
        response.getWriter().write(result);
	}
	
}
