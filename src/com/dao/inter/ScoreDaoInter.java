package com.dao.inter;

import java.util.List;
import java.util.Map;

import com.bean.Exam;


/**
 * �����ɼ������ݲ�ӿ�
 * @author bojiangzhou
 *
 */
public interface ScoreDaoInter extends BaseDaoInter {
	
	/**
	 * ��ȡѧ���ɼ���
	 * @param exam
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getScoreList(Exam exam);
	
}
