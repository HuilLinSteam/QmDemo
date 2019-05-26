package com.dao.inter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("rawtypes")
//SuppressWarnings压制警告，即去除警告 
//rawtypes是说传参时也要传递带泛型的参数
public interface BaseDaoInter {
	List<Object> getList(Class type,String sql);
	List<Object> getList(Class type,String sql,Object[] param);
	List<Object> getList(Class type, String sql, List<Object> param);
	List<Object> getList(Connection conn, Class type, String sql);
	List<Object> getList(Connection conn, Class type, String sql, Object[] param);
	List<Object> getList(Connection conn, Class type, String sql, List<Object> param);
	Object getObject(Class type, String sql, Object[] param);
	Object getObject(Connection conn, Class type, String sql, Object[] param);
	Long count(String sql);
	Long count(String sql, Object[] param);
	Long count(String sql, List<Object> param);
	void update(String sql, Object[] param);
	void update(String sql, List<Object> param);
	void updateTransaction(Connection conn, String sql, Object[] param) throws SQLException;
	void updateBatch(String sql, Object[][] param);
	void insert(String sql, Object[] param);
	void insertTransaction(Connection conn, String sql, Object[] param) throws SQLException;
	int insertReturnKeys(String sql, Object[] param);
	int insertReturnKeysTransaction(Connection conn, String sql, Object[] param) throws SQLException;
	void insertBatch(String sql, Object[][] param);
	void insertBatchTransaction(Connection conn, String sql, Object[][] param) throws SQLException;
	void delete(String sql, Object[] param);
	void deleteTransaction(Connection conn, String sql, Object[] param) throws SQLException;
	void deleteTransaction(Connection conn, String sql, List<Object> param) throws SQLException;
	void deleteBatchTransaction(Connection conn, String sql, Object[][] param);
	List<String> getColumn(String sql, Object[] param);
}
