package com.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.descriptor.web.ContextService;

import com.bean.User;
import com.tools.VCodeGenerator;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SystemService;
public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private SystemService service = new SystemService();
	
	 
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String account = request.getParameter("account");
		
		String password = request.getParameter("password");
		
		String vcode = request.getParameter("vcode");
		
		int type = Integer.parseInt(request.getParameter("type"));
		
		
		String msg = "";
		
		String sVcode = (String) request.getSession().getAttribute("vcode");
		if(!sVcode.equalsIgnoreCase(vcode)){
			msg = "vcodeError";
		} else{	
			
			User user = new User();
			user.setAccount(account);
			user.setPassword(password);
			user.setType(Integer.parseInt(request.getParameter("type")));
			
			User loginUser = service.getAdmin(user);
			if(loginUser == null){
				msg = "loginError";
			} else{ 
				if(User.USER_ADMIN == type){
					msg = "admin";
				} else if(User.USER_STUDENT == type){
					msg = "student";
				} else if(User.USER_TEACHER == type){
					msg = "teacher";
				}
				
				request.getSession().setAttribute("user", loginUser);
			}
		}
		response.getWriter().write(msg);
		return null;
		
	}
	
	
	public String getVCode() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VCodeGenerator vcGenerator = new VCodeGenerator();
		
		String vcode = vcGenerator.generatorVCode();
		
		request.getSession().setAttribute("vcode", vcode);
		
		BufferedImage vImg = vcGenerator.generatorRotateVCodeImage(vcode, true);
		
		ImageIO.write(vImg, "gif", response.getOutputStream());
		return null;
	}
	
}