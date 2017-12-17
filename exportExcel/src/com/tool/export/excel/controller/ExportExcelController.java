package com.tool.export.excel.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tool.export.excel.dto.TzDTO;
import com.tool.export.excel.service.DisposeExcelDateService;
import com.tool.export.excel.service.ExportExcelService;
import com.tool.export.excel.service.TzService;

/**
 * 导出设备信息到excel表格
 * 
 * @author NH
 */
@Controller
public class ExportExcelController {

	@Autowired
	private TzService tzService;
	@Autowired
	private ExportExcelService exportExcelService;
	@Autowired
	private DisposeExcelDateService disposeExcelDateService;
	
	/**
	 * 导出台帐
	 */
	@RequestMapping("tz")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String titleName = request.getParameter("titleName");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String browserTypeIsFireFox = request.getParameter("browserTypeIsFireFox");
		
		String cs = new String(titleName.getBytes("ISO-8859-1"),"utf-8");
		
		titleName += ".xls";
		response.setContentType("application/x-xls");
		response.setHeader("Content-Disposition", "attachment;filename=" + titleName);
		
		OutputStream outPut = response.getOutputStream();
		
		List<Map<String, Object>> content = tzService.selectByDate(beginTime, endTime);
		
		exportExcelService.exportExcelForOnePage(outPut, titleName,disposeExcelDateService.getTZColName(),content);
		outPut.flush();
		outPut.close();
	}
	
}
