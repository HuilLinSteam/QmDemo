package com.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.bean.Exam;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ScoreService;

public class SoreAction extends ActionSupport{
		//创建服务层对象
		private ScoreService service = new ScoreService();
		

		public void columnList() throws IOException {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			Enumeration<String> pNames = request.getParameterNames();
			Exam exam = new Exam();
			
			/* hasMoreElements( )用于测试此枚举是否包含更多的元素*/
			while(pNames.hasMoreElements()){
				/*nextElement()方法如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素。*/
				/*pName为枚举对象*/
				/*value是pName对象的值*/
				String pName = pNames.nextElement();
				String value = request.getParameter(pName);
				try {
					BeanUtils.setProperty(exam, pName, value);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
			//获取数据
			String result = service.columnList(exam);
			//返回数据
			response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write(result);
		}
		
		public void scoreList() throws IOException {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
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
			
			//获取数据
			String result = service.getScoreList(exam);
			//返回数据
			response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write(result);
		}
		
		public void exportScore() {
			//获取分页参数
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
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
			
			service.exportScore(response, exam);
		}
		
		
		public void setScore() throws IOException {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] score = request.getParameterValues("score[]");
			service.setScore(score);
			//返回数据
			response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write("success");
		}
		
}