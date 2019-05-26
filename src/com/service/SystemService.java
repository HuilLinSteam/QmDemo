package com.service;

import java.util.List;

import com.bean.User;
import com.dao.impl.SystemDaoImpl;
import com.dao.inter.SystemDaoInter;
import com.bean.SystemInfo;

import net.sf.json.JSONArray;

public class SystemService {
	SystemDaoInter dao = new SystemDaoImpl();
	//显示所有用户账号--account
	public String getAccountList(){
		List<String> list = dao.getColumn("SELECT account FROM user", null);
		String result = JSONArray.fromObject(list).toString();
		return result;
	}
	//用户登录验证
	public User getAdmin(User user){
		User searchUser = (User)dao.getObject(User.class, 
				"SELECT * FROM user WHERE account=? AND password=? AND type=?",
				new Object[]{user.getAccount(), user.getPassword(), user.getType()});
		return searchUser;
	}
	public SystemInfo editSystemInfo(String name, String value) {
		
		dao.update("UPDATE system SET "+name+" = ?", new Object[]{value});
		
    	SystemInfo sys = (SystemInfo) dao.getObject(SystemInfo.class, "SELECT * FROM system", null);
    	return sys;
	}
	public void editPassword(User user) {
		dao.update("UPDATE user SET password=? WHERE account=?", 
				new Object[]{user.getPassword(),user.getAccount()});
	}
}
