package com.tool.export.excel.dao;

import java.util.List;

import com.tool.export.excel.dto.TzDTO;

public interface TzDAO {
	public void create(TzDTO newDTO);
	public void delete(TzDTO paramDTO);
	public TzDTO findById(TzDTO paramDTO);
	public List<TzDTO> findByParam(TzDTO paramDTO);
}
