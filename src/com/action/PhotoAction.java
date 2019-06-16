package com.action;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.User;
import com.tools.StringTool;
import com.service.PhotoService;
import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport{
	PhotoService service = new PhotoService();
	public String setPhoto() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取学号或工号
		User user = (User) request.getSession().getAttribute("user");
		
		String msg = service.setPhoto(user, request);
		
		response.getWriter().write(msg);
		return "setPhoto";
	}
	
	
	public void getPhoto() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = new User();
		//获取number
		String number = request.getParameter("number");
		if(!StringTool.isEmpty(number)){
			int type = Integer.parseInt(request.getParameter("type"));
			user.setAccount(number);
			user.setType(type);
		} else{
			user = (User) request.getSession().getAttribute("user");
		}
		InputStream is = service.getPhoto(user);
		System.out.println(is);
		if(is != null){
			byte[] b = new byte[is.available()];
			is.read(b);
			System.out.println(b);
			System.out.println(is.read(b));
			response.getOutputStream().write(b, 0, b.length);
		}
	}
}
