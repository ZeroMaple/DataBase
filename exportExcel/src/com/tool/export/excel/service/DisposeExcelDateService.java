package com.tool.export.excel.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DisposeExcelDateService {

	/**
	 * 台帐的列名（写死的）
	 */
	public Map<String, Map<String, Object>> getTZColName(){
		Map<String, Map<String, Object>> colNameDetails = new HashMap<String, Map<String,Object>>();

		Map<String,Object> box1 = new HashMap<String, Object>();
		box1.put("showName","序号");
		box1.put("trueName","num");
		box1.put("width",10);
		colNameDetails.put("1",box1);

		Map<String,Object> box2 = new HashMap<String, Object>();
		box2.put("showName","送检单位");
		box2.put("trueName","sjdw");
		box2.put("width",50);
		colNameDetails.put("2",box2);

		Map<String,Object> box3 = new HashMap<String, Object>();
		box3.put("showName","制造厂");
		box3.put("trueName","zzc");
		box3.put("width",50);
		colNameDetails.put("3",box3);

		Map<String,Object> box4 = new HashMap<String, Object>();
		box4.put("showName","型号规格");
		box4.put("trueName","xhgg");
		box4.put("width",50);
		colNameDetails.put("4",box4);

		Map<String,Object> box5 = new HashMap<String, Object>();
		box5.put("showName","出场编号");
		box5.put("trueName","ccbh");
		box5.put("width",30);
		colNameDetails.put("5",box5);

		Map<String,Object> box6 = new HashMap<String, Object>();
		box6.put("showName","准确度等级");
		box6.put("trueName","jqddj");
		box6.put("width",20);
		colNameDetails.put("6",box6);

		Map<String,Object> box7 = new HashMap<String, Object>();
		box7.put("showName","器具名称");
		box7.put("trueName","qjmc");
		box7.put("width",50);
		colNameDetails.put("7",box7);

		Map<String,Object> box8 = new HashMap<String, Object>();
		box8.put("showName","最大称量");
		box8.put("trueName","zdcl");
		box8.put("width",20);
		colNameDetails.put("8",box8);

		Map<String,Object> box9 = new HashMap<String, Object>();
		box9.put("showName","最小称量");
		box9.put("trueName","zxcl");
		box9.put("width",20);
		colNameDetails.put("9",box9);

		Map<String,Object> box10 = new HashMap<String, Object>();
		box10.put("showName","检定分值度");
		box10.put("trueName","jdfzd");
		box10.put("width",20);
		colNameDetails.put("10",box10);

		Map<String,Object> box11 = new HashMap<String, Object>();
		box11.put("showName","检定日期");
		box11.put("trueName","jdrq");
		box11.put("width",30);
		colNameDetails.put("11",box11);

		Map<String,Object> box12 = new HashMap<String, Object>();
		box12.put("showName","备注");
		box12.put("trueName","bz");
		box12.put("width",50);
		colNameDetails.put("12",box12);

		return colNameDetails;
	}

	/**
	 * 根据查询得到的数据生成相应的二级标题
	 */
	public Map<String, Map<String, Object>> getColNameByDate(Map<String,String> data){


		return null;
	}

}
