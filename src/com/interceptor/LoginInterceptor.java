package com.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String,Object> session=actionInvocation.getInvocationContext().getSession();
		//代理模式
		ActionProxy actionProxy = actionInvocation.getProxy();
		String methodName = actionProxy.getMethod();
		if("login".equals(methodName)){
			return actionInvocation.invoke();
		}
		User user=(User) session.get("user");
		if(user==null){
			HttpServletResponse response=ServletActionContext.getResponse();
			response.sendRedirect("index.jsp");
		
			return null;
		}else
		return actionInvocation.invoke();
	}

}
