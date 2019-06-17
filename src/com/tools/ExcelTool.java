package com.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelTool<T> {
	
	/**
	 * ����Excel
	 * @param type
	 * @param filePath
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws Exception
	 */
	public List<T> importExcel(Class<T> type, InputStream is) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// ������Excel�������ļ�������
		HSSFWorkbook workbook = new HSSFWorkbook(is); 
		/*
		 * ��Excel�ĵ��У���һ�Ź������ȱʡ������0 
		 * �����Ϊ��HSSFSheet sheet = workbook.getSheetAt(0);
		 * ���ݱ�����ȡ: HSSFSheet sheet = wookbook.getSheet("Sheet1");
		 */
		HSSFSheet sheet = workbook.getSheetAt(0);
		//��ȡ��Excel�ļ��е���������
        int rows = sheet.getPhysicalNumberOfRows();
        //������
        HSSFRow titleRow = null;
        int r = 0;
        for(; r < rows; r++){
        	// ��ȡһ��
            HSSFRow row = sheet.getRow(r);
            // �в�Ϊ��
            if (row != null) {
            	titleRow = row; break;
            }
        }
        List<T> list = new LinkedList<T>();
        //���ڸ�ʽ��excel�е���ֵ�ı�
        DecimalFormat df = new DecimalFormat("0");
        //�����У���ȡ���ݲ���װ
        r++;
        for (; r <= rows; r++) {
            HSSFRow row = sheet.getRow(r);
            if (row != null) {
            	T obj = type.newInstance();
            	//��ȡ�������е����е���
            	int cells = row.getPhysicalNumberOfCells();
                //������
                for (int j = 0; j <= cells; j++) {
                	//��ȡ���е�ֵ
                    HSSFCell cell = row.getCell(j);
                    //��ȡ����
                    if (cell != null) {
                    	String name = titleRow.getCell(j).getStringCellValue();
                    	String value = "";
                    	switch (cell.getCellType()) {
                        	case HSSFCell.CELL_TYPE_FORMULA:
                        		break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                            	value = df.format(cell.getNumericCellValue());        
                                break;  
                        	case HSSFCell.CELL_TYPE_STRING:
                             	value = cell.getStringCellValue();
                                break;
                         	default:
                              	value = "";
                                break;
                    	}
                    	BeanUtils.setProperty(obj, name, value);
                    }      
                }
                list.add(obj);
            }
        }
		return list;
	}
	
	/**
	 * ����
	 * @param headers
	 * @param list
	 * @param out
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void exportExcel(String[] headers, List<T> list, OutputStream out) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // ����һ��������
        HSSFWorkbook workbook = new HSSFWorkbook();
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet();
        // ����һ����ʽ
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        // ������Щ��ʽ
        headerStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // ����һ������
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // ������Ӧ�õ���ǰ����ʽ
        headerStyle.setFont(font);

        // ������������
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            //HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(headers[i]);
        }
        // �����������ݣ�����������
        int index = 0;
        for(T t : list) {
            index++;
            row = sheet.createRow(index);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                String value = BeanUtils.getProperty(t, headers[i]);
                cell.setCellValue(value);
            }
        }
        //���
        try {
        	workbook.write(out);
    		out.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    }
	
	public void exportMapExcel(String[] headers, List<Map<String, Object>> list, OutputStream out) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // ����һ��������
        HSSFWorkbook workbook = new HSSFWorkbook();
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet();
        // ����һ����ʽ
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        // ������Щ��ʽ
//        headerStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // ����һ������
//        HSSFFont font = workbook.createFont();
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // ������Ӧ�õ���ǰ����ʽ
//        headerStyle.setFont(font);

        // ������������
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            //HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(headers[i]);
        }
        // �����������ݣ�����������
        int index = 0;
        for(Map<String, Object> map : list) {
            index++;
            row = sheet.createRow(index);
            
            Set<Entry<String, Object>> set = map.entrySet();
            
            Iterator<Entry<String, Object>> it = set.iterator();
            int i = 0;
            while(it.hasNext()){
            	
            	Entry<String, Object> next = it.next();
            	Object value = next.getValue();
            	String key = next.getKey();
            	if(!key.contains("escoreid")){
            		HSSFCell cell = row.createCell(i++);
                	if(value instanceof String){
                		String str = (String) value;
                		cell.setCellValue(str);
                	} else{
                		Integer score = (Integer) value;
                		cell.setCellValue(score.intValue());
                	}
            	}
            }
        }
        //���
        try {
        	workbook.write(out);
    		out.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    }
	
	
}
