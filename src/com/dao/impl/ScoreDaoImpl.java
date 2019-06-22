package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.bean.Clazz;
import com.bean.EScore;
import com.bean.Exam;
import com.bean.Grade;
import com.bean.Page;
import com.bean.Student;
import com.dao.inter.BaseDaoInter;
import com.dao.inter.ScoreDaoInter;
import com.dao.inter.StudentDaoInter;
import com.tools.MysqlTool;


public class ScoreDaoImpl extends BaseDaoImpl implements ScoreDaoInter {

	public List<Map<String, Object>> getScoreList(Exam exam) {
		//sql语句
		List<Object> stuParam = new LinkedList<>();
		StringBuffer stuSb = new StringBuffer("SELECT id, name, number FROM student WHERE gradeid=? ");
		stuParam.add(exam.getGradeid());
		if(exam.getClazzid() != 0){
			stuSb.append(" AND clazzid=?");
			stuParam.add(exam.getClazzid());
		}
		String stuSql = stuSb.toString();
		
		//获取数据库连接
		Connection conn = MysqlTool.getConnection();
		
		//获取该年级下的所有学生
		List<Object> stuList = getList(Student.class, stuSql, stuParam);
		
		//数据集合
		List<Map<String, Object>> list = new LinkedList<>();
		
		//sql语句
		String sql = "SELECT e.id,e.courseid,e.score FROM student s, escore e "
				+ "WHERE s.id=e.studentid AND e.examid=? AND e.studentid=?";
		
		for(int i = 0;i < stuList.size();i++){
			Map<String, Object> map = new LinkedHashMap<>();
			
			Student student = (Student) stuList.get(i);
			
			map.put("name", student.getName());
			map.put("number", student.getNumber());
			
			List<Object> scoreList = getList(conn, EScore.class, sql, new Object[]{exam.getId(), student.getId()});
			int total = 0;
			for(Object obj : scoreList){
				EScore score = (EScore) obj;
				total += score.getScore();
				
				//将成绩表id放入:便于获取单科成绩用于登记
				map.put("course"+score.getCourseid(), score.getScore());	//key:科目Id，value：成绩

				map.put("escoreid"+score.getCourseid(), score.getId());
			}
			if(exam.getType() == 1){
				map.put("total", total);
			}
			
			list.add(map);
		}
		return list;
	}
	

}
