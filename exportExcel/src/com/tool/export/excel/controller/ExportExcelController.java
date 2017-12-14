package com.tool.export.excel.controller;

import java.io.OutputStream;
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

import com.tool.export.excel.service.DisposeExcelDateService;
import com.tool.export.excel.service.ExportExcelService;

/**
 * 导出设备信息到excel表格
 * 
 * @author NH
 */
@Controller
public class ExportExcelController {

	@Autowired
	private ExportExcelService exportExcelService;
	@Autowired
	private DisposeExcelDateService disposeExcelDateService;
	
	/**
	 * 导出台帐
	 */
	@RequestMapping("tz")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browserTypeIsFireFox = request.getParameter("browserTypeIsFireFox");
		
		String titleName = "XXX台帐.xls";
		response.setContentType("application/x-xls");
		if(browserTypeIsFireFox!=null && browserTypeIsFireFox.equals("1")){
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(titleName.getBytes(),"ISO8859-1"));
		}else{
			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(titleName,"UTF-8"));
		}
		OutputStream outPut = response.getOutputStream();
		
		List<Map<String, Object>> content = new ArrayList<Map<String,Object>>();
		Map<String, Object> test = new HashMap<String, Object>();
		test.put("num",1);
		content.add(test);
		Map<String, Object> test2 = new HashMap<String, Object>();
		test2.put("num",2);
		content.add(test2);
		exportExcelService.exportExcelForOnePage(outPut, titleName,disposeExcelDateService.getTZColName(),content);
		outPut.flush();
		outPut.close();
	}
	
}
