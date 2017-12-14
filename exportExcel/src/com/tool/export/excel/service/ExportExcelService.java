package com.tool.export.excel.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;

@Service
public class ExportExcelService {

	/**
	 * ����excel�ļ� �����ڵ�ҳ���,��һ�����⣬�����������̶���
	 * 
	 * @param firstTitle һ������
	 * @param secondTitleDetails �������⣨������{orderNum={showName=,trueName=,width=}}
	 * @param content ����
	 */
	public void exportExcelForOnePage(OutputStream outPut, String firstTitle, Map<String, Map<String, Object>> secondTitleDetails, List<Map<String, Object>> content) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(firstTitle);
		int colSize = secondTitleDetails.size();
		if (colSize <= 0) {
			// ����HSSFRow����
			HSSFRow row = sheet.createRow(3);
			// ����HSSFCell����
			HSSFCell cell = row.createCell(3);
			// ���õ�Ԫ���ֵ
			cell.setCellValue("������");
		} else {
			// ������ʽ
			HSSFCellStyle titleCellStyle = wb.createCellStyle();
			HSSFFont titleFontStyle = wb.createFont();
			titleFontStyle.setFontName("����");
			titleFontStyle.setFontHeightInPoints((short) 18);
			titleCellStyle.setFont(titleFontStyle);
			titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			titleCellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

			HSSFCellStyle secondTitleCellStyle = wb.createCellStyle();
			HSSFFont secondTitleFontStyle = wb.createFont();
			secondTitleFontStyle.setFontName("����");
			secondTitleFontStyle.setFontHeightInPoints((short) 12);
			secondTitleFontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secondTitleCellStyle.setFont(secondTitleFontStyle);
			secondTitleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			secondTitleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFCellStyle contentCellStyle = wb.createCellStyle();
			HSSFFont contentFontStyle = wb.createFont();
			contentFontStyle.setFontName("����");
			contentFontStyle.setFontHeightInPoints((short) 11);
			contentCellStyle.setFont(contentFontStyle);
			contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			contentCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			int nowRow = 0;// ���ڵ�����

			// һ������
			CellRangeAddress mergeCell = new CellRangeAddress(nowRow, nowRow, 0, colSize - 1);
			sheet.addMergedRegion(mergeCell);
			HSSFRow separatedRow = sheet.createRow(nowRow++);
			separatedRow.setHeight((short) (25 * 20));
			HSSFCell mergeFirstCell = separatedRow.createCell(0);
			mergeFirstCell.setCellStyle(titleCellStyle);
			mergeFirstCell.setCellValue(firstTitle);

			// ��������
			HSSFRow secondTitle = sheet.createRow(nowRow++);
			secondTitle.setHeight((short) (19 * 20));
			for (int i = 1; i <= colSize; i++) {
				HSSFCell cell = secondTitle.createCell(i-1);
				cell.setCellStyle(titleCellStyle);
				Map<String, Object> titieValueDetail = secondTitleDetails.get(i
						+ "");
				cell.setCellValue(titieValueDetail.get("showName") + "");
				int width = (Integer) titieValueDetail.get("width");
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * width);
			}

			// ��Ҫ����
			for (Map<String, Object> info : content) {
				HSSFRow row = sheet.createRow(nowRow++);
				row.setHeight((short) (19 * 20));
				for (int i = 1; i <= colSize; i++) {
					HSSFCell cell = row.createCell(i-1);
					cell.setCellStyle(contentCellStyle);
					Map<String, Object> titieValueDetail = secondTitleDetails
							.get(i + "");
					String key = titieValueDetail.get("trueName") + "";
					cell.setCellValue(info.get(key) + "");
				}
			}
		}
		// ������Ͽ�ʼ����ļ�
		wb.write(outPut);
	}

}
