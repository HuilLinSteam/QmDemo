package com.action;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.tomcat.util.descriptor.web.ContextService;

//import org.apache.tomcat.util.descriptor.web.ContextService;


import com.bean.User;
import com.tools.VCodeGenerator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SystemService;
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	HttpServletRequest request ;
	HttpServletResponse response ;
	private static final long serialVersionUID = 1L;
	private SystemService service = new SystemService();
	User user,loginUser;
	public User getLoginUser() {
		return loginUser;
	}


	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void login() throws IOException {
		
		
		String account = user.getAccount();
		
		String password = user.getPassword();
		
		String vcode = request.getParameter("vcode");
//		String x = request.getParameter("type");
//		int type = Integer.parseInt(x);
		ActionContext actionContext = ActionContext.getContext();
		

		int type = Integer.parseInt(request.getParameter("type"));
		String msg = "";
		//System.out.println(user.toString());
		String sVcode = (String) request.getSession().getAttribute("vcode");
		if(!sVcode.equalsIgnoreCase(vcode)){
			msg = "vcodeError";
			
		} else{
			
			User user1 = new User();
			user1.setAccount(account);
			user1.setPassword(password);
			user1.setType(Integer.parseInt(request.getParameter("type")));
			System.out.println(user1.toString());
			loginUser = service.getAdmin(user1);
			if(loginUser == null){
				msg = "loginError";
			} else{ 
//				if(User.USER_ADMIN == type){
				if(User.USER_ADMIN == type){
					msg = "admin";
				} else if(User.USER_STUDENT == type){
					msg = "student";
				} else if(User.USER_TEACHER == type){
					msg = "teacher";
				}
//				request.getSession().setAttribute("user", loginUser);
//				System.out.println(session.toString());
				
//				ServletActionContext.getServletContext().setAttribute("user", loginUser);
				ActionContext.getContext().getSession().put("user", loginUser);
//				session.put("user", loginUser);
				
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(msg);
		
		
	}
	
	
	public String getVCode() throws IOException {
		
		VCodeGenerator vcGenerator = new VCodeGenerator();
		
		String vcode = vcGenerator.generatorVCode();
		
		request.getSession().setAttribute("vcode", vcode);
		
		BufferedImage vImg = vcGenerator.generatorRotateVCodeImage(vcode, true);
		
		ImageIO.write(vImg, "gif", response.getOutputStream());
		return null;
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}


	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}


	
}