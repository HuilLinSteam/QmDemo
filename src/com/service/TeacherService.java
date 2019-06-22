package com.service;

import java.sql.Connection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bean.Clazz;
import com.bean.Course;
import com.bean.CourseItem;
import com.bean.Grade;
import com.bean.Page;
import com.bean.Student;
import com.bean.Teacher;
import com.bean.User;
import com.dao.impl.BaseDaoImpl;
import com.dao.impl.StudentDaoImpl;
import com.dao.impl.TeacherDaoImpl;
import com.dao.inter.BaseDaoInter;
import com.dao.inter.StudentDaoInter;
import com.dao.inter.TeacherDaoInter;
import com.tools.MysqlTool;
import com.tools.StringTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 鏁欏笀绫绘湇鍔″眰
 * @author bojiangzhou
 *
 */
public class TeacherService {
	
	private TeacherDaoInter dao;
	
	public TeacherService(){
		dao = new TeacherDaoImpl();
	}
	
	/**
	 * 鑾峰彇鏁欏笀淇℃伅
	 * @param page
	 * @param rows
	 * @return
	 */
	public String getTeacherList(Page page) {
		//sql璇彞
		String sql = "SELECT * FROM teacher ORDER BY id DESC LIMIT ?,?";
		//鑾峰彇鏁版嵁
		List<Teacher> list = dao.getTeacherList(sql, new Object[]{page.getStart(), page.getSize()}, null, null);
		//鑾峰彇鎬昏褰曟暟
		long total = dao.count("SELECT COUNT(*) FROM teacher", new Object[]{});
		//瀹氫箟Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();  
		//total閿� 瀛樻斁鎬昏褰曟暟锛屽繀椤荤殑
        jsonMap.put("total", total);
        //rows閿� 瀛樻斁姣忛〉璁板綍 list 
        jsonMap.put("rows", list); 
        //鏍煎紡鍖朚ap,浠son鏍煎紡杩斿洖鏁版嵁
        String result = JSONObject.fromObject(jsonMap).toString();
        
        //杩斿洖
		return result;
	}
	
	/**
	 * 鑾峰彇鏌愪釜鑰佸笀鐨勫叿浣撲俊鎭細鍖呮嫭鎵�閫夎绋�
	 * @param number
	 * @return
	 */
	public Teacher getTeacher(String number) {
		//sql璇彞
		String sql = "SELECT * FROM teacher WHERE number=?";
		//鑾峰彇鏁版嵁
		List<Teacher> list = dao.getTeacherList(sql, new Object[]{number}, null, null);
        //杩斿洖
		return list.get(0);
	}
	
	/**
	 * 鑾峰彇鏌愬勾绾т笅鑰佸笀鐨勭彮绾�
	 * @param number
	 * @param grade
	 * @return
	 */
	public String getExamClazz(String number, Grade grade) {
		//sql璇彞
		String sql = "SELECT * FROM teacher WHERE number=?";
		//鑾峰彇鏁版嵁
		Teacher list = dao.getTeacherList(sql, new Object[]{number}, grade, null).get(0);
		
		List<Clazz> clazzList = new LinkedList<>();
		List<CourseItem> courseItem = list.getCourseList();
		for(CourseItem item : courseItem){
			boolean flag = true;
			for(Clazz clazz : clazzList){
				if(clazz.getId() == item.getClazzid()){
					flag = false;
					break;
				}
			}
			if(flag){
				clazzList.add(item.getClazz());
			}
		}
		String result = JSONArray.fromObject(clazzList).toString();
		
        //杩斿洖
		return result;
	}
	
	/**
	 * 鏌ヨ鑰冭瘯涓嬭�佸笀鐨勮绋�
	 * @param number
	 * @param grade
	 * @param clazz
	 * @return
	 */
	public String getExamClazz(String number, Grade grade, Clazz clazz) {
		//sql璇彞
		String sql = "SELECT * FROM teacher WHERE number=?";
		//鑾峰彇鏁版嵁
		Teacher list = dao.getTeacherList(sql, new Object[]{number}, grade, clazz).get(0);
		
		List<Course> courseList = new LinkedList<>();
		List<CourseItem> courseItem = list.getCourseList();
		for(CourseItem item : courseItem){
			courseList.add(item.getCourse());
		}
		String result = JSONArray.fromObject(courseList).toString();
		
        //杩斿洖
		return result;
	}
	
	/**
	 * 鑾峰彇鑰佸笀璇︾粏淇℃伅
	 * @param number
	 * @return
	 */
	public String getTeacherResult(String number) {
		Teacher teacher = getTeacher(number);
		String result = JSONObject.fromObject(teacher).toString();
        //杩斿洖
		return result;
	}
	
	/**
	 * 娣诲姞鑰佸笀淇℃伅
	 * @param teacher
	 * @throws Exception 
	 */
	public void addTeacher(Teacher teacher) throws Exception {
		Connection conn = MysqlTool.getConnection();
		try {
			//寮�鍚簨鍔�
			MysqlTool.startTransaction();
			
			String sql = "INSERT INTO teacher(number, name, sex, phone, qq) value(?,?,?,?,?)";
			Object[] param = new Object[]{
					teacher.getNumber(), 
					teacher.getName(), 
					teacher.getSex(), 
					teacher.getPhone(),
					teacher.getQq()
				};
			//娣诲姞鏁欏笀淇℃伅
			int teacherid = dao.insertReturnKeysTransaction(conn, sql, param);
			//璁剧疆璇剧▼
			if(teacher.getCourse() != null && teacher.getCourse().length > 0){
				for(String course : teacher.getCourse()){
					String[] gcc = course.split("_");
					int gradeid = Integer.parseInt(gcc[0]);
					int clazzid = Integer.parseInt(gcc[1]);
					int courseid = Integer.parseInt(gcc[2]);
					
					dao.insertTransaction(conn, 
							"INSERT INTO clazz_course_teacher(clazzid, gradeid, courseid, teacherid) value(?,?,?,?) ", 
							new Object[]{clazzid, gradeid, courseid, teacherid});
				}
			}
			//娣诲姞鐢ㄦ埛璁板綍
			dao.insertTransaction(conn, "INSERT INTO user(account, name, type) value(?,?,?)", 
					new Object[]{
						teacher.getNumber(),
						teacher.getName(),
						User.USER_TEACHER
				});
			
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
	
	/**
	 * 淇敼鏁欏笀淇℃伅
	 * @param teacher
	 * @throws Exception
	 */
	public void editTeacher(Teacher teacher) throws Exception {
		Connection conn = MysqlTool.getConnection();
		try {
			//寮�鍚簨鍔�
			MysqlTool.startTransaction();
			
			String sql = "UPDATE teacher set name=?,sex=?,phone=?,qq=? WHERE id=?";
			Object[] param = new Object[]{
					teacher.getName(), 
					teacher.getSex(), 
					teacher.getPhone(),
					teacher.getQq(),
					teacher.getId()
				};
			//淇敼鏁欏笀淇℃伅
			dao.updateTransaction(conn, sql, param);
			//淇敼绯荤粺鐢ㄦ埛淇℃伅
			dao.update("UPDATE user SET name=? WHERE account=?", 
					new Object[]{teacher.getName(), teacher.getNumber()});
			//鍒犻櫎鏁欏笀涓庤绋嬬殑鍏宠仈
			dao.deleteTransaction(conn, "DELETE FROM clazz_course_teacher WHERE teacherid =?", new Object[]{teacher.getId()});
			//璁剧疆璇剧▼
			if(teacher.getCourse() != null && teacher.getCourse().length > 0){
				for(String course : teacher.getCourse()){
					String[] gcc = course.split("_");
					int gradeid = Integer.parseInt(gcc[0]);
					int clazzid = Integer.parseInt(gcc[1]);
					int courseid = Integer.parseInt(gcc[2]);
					
					dao.insertTransaction(conn, 
							"INSERT INTO clazz_course_teacher(clazzid, gradeid, courseid, teacherid) value(?,?,?,?) ", 
							new Object[]{clazzid, gradeid, courseid, teacher.getId()});
				}
			}
			
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
	
	/**
	 * 鏁欏笀淇敼涓汉淇℃伅
	 * @param teacher
	 */
	public void editTeacherPersonal(Teacher teacher){
		
		String sql = "UPDATE teacher SET name=?, sex=?, phone=?, qq=? WHERE number=?";
		
		//鏇存柊淇℃伅
		dao.update(sql, new Object[]{
				teacher.getName(), 
				teacher.getSex(),
				teacher.getPhone(),
				teacher.getQq(),
				teacher.getNumber()});
		
		dao.update("UPDATE user SET name=? WHERE account=?", 
				new Object[]{teacher.getName(), teacher.getNumber()});
	}
	
	/**
	 * 鍒犻櫎鏁欏笀
	 * @param ids 鏁欏笀ID鏁扮粍
	 * @param numbers 鏁欏笀宸ュ彿鏁扮粍
	 * @throws Exception 
	 */
	public void deleteTeacher(String[] ids, String[] numbers) throws Exception{
		//鑾峰彇鍗犱綅绗�
		String mark = StringTool.getMark(ids.length);
		Integer tid[] = new Integer[ids.length];
		for(int i =0 ;i < ids.length;i++){
			tid[i] = Integer.parseInt(ids[i]);
		}
		//鑾峰彇杩炴帴
		Connection conn = MysqlTool.getConnection();
		//寮�鍚簨鍔�
		MysqlTool.startTransaction();
		try {
			//鍒犻櫎鏁欏笀涓庤绋嬬殑鍏宠仈
			dao.deleteTransaction(conn, "DELETE FROM clazz_course_teacher WHERE teacherid IN("+mark+")", tid);
			//鍒犻櫎鏁欏笀
			dao.deleteTransaction(conn, "DELETE FROM teacher WHERE id IN("+mark+")", tid);
			//鍒犻櫎绯荤粺鐢ㄦ埛
			dao.deleteTransaction(conn, "DELETE FROM user WHERE account IN("+mark+")",  numbers);
			
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
	
	/**
	 * 璁剧疆鐓х墖
	 * @param number
	 * @param fileName 
	 */
	public void setPhoto(String number, String fileName) {
		String photo = "photo/"+fileName;
		dao.update("UPDATE teacher SET photo=? WHERE number=?", new Object[]{photo, number});
	}
	
}
