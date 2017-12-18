package com.tool.export.excel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tool.export.excel.dao.TzDAO;
import com.tool.export.excel.dto.TzDTO;

public class TzService {

	TzDAO tzDAO;

	public TzService(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		tzDAO = context.getBean("tzDAO",TzDAO.class);
	}

	public List<Map<String,Object>> selectByDate(String beginTime,String endTime){
		TzDTO paramDTO = new TzDTO();
		paramDTO.setBeginTime(beginTime);
		paramDTO.setEndTime(endTime);
		List<TzDTO> selectResult = tzDAO.findByParam(paramDTO);
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		if(selectResult!=null && selectResult.size()>0){
			for(TzDTO dto : selectResult){
				returnList.add(dto.toMap());
			}
		}

		return returnList;
	}

}
