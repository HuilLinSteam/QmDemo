package com.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.bean.Clazz;
import com.bean.Course;
import com.bean.Grade;
import com.bean.Student;
import com.dao.impl.BaseDaoImpl;
import com.dao.inter.BaseDaoInter;
import com.tools.MysqlTool;
import com.tools.StringTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 骞寸骇鏈嶅姟灞�
 * @author bojiangzhou
 *
 */
public class GradeService {
	
	BaseDaoInter dao = new BaseDaoImpl();
	
	/**
	 * 鑾峰彇鎵�鏈夊勾绾�
	 * @param clazz 鏄惁鑾峰彇骞寸骇涓嬭绋�
	 * @return JSON鏍煎紡鐨勫勾绾�
	 */
	@Test
	public String getGradeList(String course){
		//鑾峰彇鏁版嵁
		List<Object> list = dao.getList(Grade.class, "SELECT * FROM grade");
		JsonConfig config = new JsonConfig();
		if(StringTool.isEmpty(course)){ //濡傛灉娌℃湁浼犺繘course鍙傛暟锛屽垯杩斿洖骞寸骇鐨刬d鍜屽悕绉板嵆鍙�
			config.setExcludes(new String[]{"clazzList", "clazzList", "studentList"});
		} else{ //涓嶄负绌洪渶瑕佸啀灏嗗勾绾т笅鐨勭彮绾ц幏鍙栧嚭鏉�
			//鑾峰彇璇剧▼
			for(Object obj : list){
				Grade grade = (Grade) obj;
				List<Object> gradeCourse = dao.getList(Course.class, 
						"SELECT c.* FROM grade_course gc, course c WHERE gc.gradeid=? AND gc.courseid=c.id", 
						new Object[]{grade.getId()});
				
				List<Course> courseList = new LinkedList<>();
				for(Object gc : gradeCourse){
					Course c = (Course) gc;
					courseList.add(c);
				}
				grade.setCourseList(courseList);
			}
			config.setExcludes(new String[]{"clazzList", "studentList"});
		}
		
		//json鍖�
        String result = JSONArray.fromObject(list, config).toString();
        
        return result;
	}

	/**
	 * 娣诲姞骞寸骇淇℃伅
	 * @param name 骞寸骇鍚嶇О
	 * @param clazzids 骞寸骇鎵�閫夎绋�
	 */
	public void addGrade(String name, String[] clazzids) {
		//鍏堟坊鍔犲勾绾�
		int key = dao.insertReturnKeys("INSERT INTO grade(name) value(?)", new Object[]{name});
		
		String sql = "INSERT INTO grade_course(gradeid, courseid) value(?, ?)";
		//鎵归噺璁剧疆璇剧▼
		Object[][] params = new Object[clazzids.length][2];
		for(int i = 0;i < clazzids.length;i++){
			params[i][0] = key;
			params[i][1] = Integer.parseInt(clazzids[i]);
		}
		dao.insertBatch(sql, params);
	}

	/**
	 * 鍒犻櫎骞寸骇
	 * @param gradeid
	 * @throws Exception 
	 */
	public void deleteGrade(int gradeid) throws Exception {
		//鑾峰彇杩炴帴
		Connection conn = MysqlTool.getConnection();
		try {
			//寮�鍚簨鍔�
			MysqlTool.startTransaction();
			System.out.println(gradeid);
			//鍒犻櫎鎴愮哗琛�
			dao.deleteTransaction(conn, "DELETE FROM escore WHERE gradeid=?", new Object[]{gradeid});
			//鍒犻櫎鑰冭瘯璁板綍
			dao.deleteTransaction(conn, "DELETE FROM exam WHERE gradeid=?", new Object[]{gradeid});
			//鍒犻櫎鐝骇鐨勮绋嬪拰鑰佸笀鐨勫叧鑱�
			dao.deleteTransaction(conn, "DELETE FROM clazz_course_teacher WHERE gradeid=?", new Object[]{gradeid});
			//鍒犻櫎鐝骇鐨勮绋嬪拰鑰佸笀鐨勫叧鑱�
			dao.deleteTransaction(conn, "DELETE FROM grade_course WHERE gradeid=?", new Object[]{gradeid});
			//鍒犻櫎鐢ㄦ埛
			List<Object> list = dao.getList(Student.class, "SELECT number FROM student WHERE gradeid=?",  new Object[]{gradeid});
			if(list.size() > 0){
				Object[] param = new Object[list.size()];
				for(int i = 0;i < list.size();i++){
					Student stu = (Student) list.get(i);
					param[i] = stu.getNumber();
				}
				String sql = "DELETE FROM user WHERE account IN ("+StringTool.getMark(list.size())+")";
				dao.deleteTransaction(conn, sql, param);
				//鍒犻櫎瀛︾敓
				dao.deleteTransaction(conn, "DELETE FROM student WHERE gradeid=?", new Object[]{gradeid});
			}
			//鍒犻櫎鐝骇
			dao.deleteTransaction(conn, "DELETE FROM clazz WHERE gradeid=?",  new Object[]{gradeid});
			//鏈�鍚庡垹闄ゅ勾绾�
			dao.deleteTransaction(conn, "DELETE FROM grade WHERE id=?",  new Object[]{gradeid});
			
			//鎻愪氦浜嬪姟
			MysqlTool.commit();
		} catch (Exception e) {
			//鍥炴粴浜嬪姟
			MysqlTool.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			MysqlTool.closeConnection();
		}
	}
	
}
