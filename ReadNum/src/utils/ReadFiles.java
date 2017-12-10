package utils;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadFiles {
	public static final Map<Integer,Double> countResult = new HashMap<Integer,Double>();
	 public static List<Integer> getExcelNum(String path) { 
			ArrayList<Integer> columnList = new ArrayList<Integer>();

			  File file = new File(path);  
			  
			  try {  
			   FileInputStream in = new FileInputStream(file);  
			  
			   HSSFWorkbook wb = new HSSFWorkbook(in);
			   int sheets =wb.getNumberOfSheets();
			   for (int ss=0;ss<sheets;ss++){
				   Sheet sheet = wb.getSheetAt(ss);
				   int firstRowNum = sheet.getFirstRowNum();
				   int lastRowNum = sheet.getLastRowNum();
				   int chooseNum = 0;

				   Row row = sheet.getRow(firstRowNum);
				   Cell cell_a = row.getCell(firstRowNum);
				   for(int x = firstRowNum;!cell_a.getStringCellValue().trim().equals("页数");x++){
					   cell_a = row.getCell(x);
					   chooseNum = x;
				   }
				   for (int i = firstRowNum+1; i <= lastRowNum; i++) {
					   row = sheet.getRow(i);          //取得第i行
					   try {
						   cell_a =row.getCell(chooseNum);		//取得i行的第x列
						   double cellValue = cell_a.getNumericCellValue();
						   columnList.add((int) cellValue);
						   countResult.put(ss,cellValue);
					   }
					   catch (NullPointerException e){
						   System.out.println(e);
					   }
			   }

//				   if (null!=row.getCell(chooseNum)||row.getCell(chooseNum).getStringCellValue().equals("")){
//					   cell_a =row.getCell(chooseNum);		//取得i行的第x列
//					   double cellValue = cell_a.getNumericCellValue();
//					   columnList.add((int) cellValue);
//				   }
			    }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			  return columnList;
	 }
}
