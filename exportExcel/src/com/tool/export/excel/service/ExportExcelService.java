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
	 * 导出excel文件 适用于单页表格,有一级标题，二级的列名固定的
	 * 
	 * @param firstTitle 一级标题
	 * @param secondTitleDetails 二级标题（列名）{orderNum={showName=,trueName=,width=}}
	 * @param content 内容
	 */
	public void exportExcelForOnePage(OutputStream outPut, String firstTitle, Map<String, Map<String, Object>> secondTitleDetails, List<Map<String, Object>> content) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(firstTitle);
		int colSize = secondTitleDetails.size();
		if (colSize <= 0) {
			// 创建HSSFRow对象
			HSSFRow row = sheet.createRow(3);
			// 创建HSSFCell对象
			HSSFCell cell = row.createCell(3);
			// 设置单元格的值
			cell.setCellValue("无数据");
		} else {
			// 创建样式
			HSSFCellStyle titleCellStyle = wb.createCellStyle();
			HSSFFont titleFontStyle = wb.createFont();
			titleFontStyle.setFontName("宋体");
			titleFontStyle.setFontHeightInPoints((short) 18);
			titleCellStyle.setFont(titleFontStyle);
			titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			titleCellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

			HSSFCellStyle secondTitleCellStyle = wb.createCellStyle();
			HSSFFont secondTitleFontStyle = wb.createFont();
			secondTitleFontStyle.setFontName("宋体");
			secondTitleFontStyle.setFontHeightInPoints((short) 12);
			secondTitleFontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			secondTitleCellStyle.setFont(secondTitleFontStyle);
			secondTitleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			secondTitleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFCellStyle contentCellStyle = wb.createCellStyle();
			HSSFFont contentFontStyle = wb.createFont();
			contentFontStyle.setFontName("宋体");
			contentFontStyle.setFontHeightInPoints((short) 11);
			contentCellStyle.setFont(contentFontStyle);
			contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			contentCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			int nowRow = 0;// 现在的行数

			// 一级标题
			CellRangeAddress mergeCell = new CellRangeAddress(nowRow, nowRow, 0, colSize - 1);
			sheet.addMergedRegion(mergeCell);
			HSSFRow separatedRow = sheet.createRow(nowRow++);
			separatedRow.setHeight((short) (25 * 20));
			HSSFCell mergeFirstCell = separatedRow.createCell(0);
			mergeFirstCell.setCellStyle(titleCellStyle);
			mergeFirstCell.setCellValue(firstTitle);

			// 二级标题
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

			// 主要数据
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
		// 构建完毕开始输出文件
		wb.write(outPut);
	}

}
