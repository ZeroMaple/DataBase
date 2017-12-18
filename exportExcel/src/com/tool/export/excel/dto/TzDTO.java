package com.tool.export.excel.dto;

import java.util.HashMap;
import java.util.Map;

public class TzDTO {

	//主键
	private String id;
	//送检单位
	private String sjdw;
	//制造厂
	private String zzc;
	//型号规格
	private String xhgg;
	//出场编号
	private String ccbh;
	//准确度等级
	private String jqddj;
	//器具名称
	private String qjmc;
	//最大称量(单位克)
	private String zdcl;
	//最小称量(单位克)
	private String zxcl;
	//检定分值度
	private String jdfzd;
	//检定日期
	private String jdrq;
	//备注
	private String bz;


	/** 表外字段*/
	private String beginTime;
	private String endTime;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSjdw() {
		return sjdw;
	}
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	public String getZzc() {
		return zzc;
	}
	public void setZzc(String zzc) {
		this.zzc = zzc;
	}
	public String getXhgg() {
		return xhgg;
	}
	public void setXhgg(String xhgg) {
		this.xhgg = xhgg;
	}
	public String getCcbh() {
		return ccbh;
	}
	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	public String getJqddj() {
		return jqddj;
	}
	public void setJqddj(String jqddj) {
		this.jqddj = jqddj;
	}
	public String getQjmc() {
		return qjmc;
	}
	public void setQjmc(String qjmc) {
		this.qjmc = qjmc;
	}
	public String getZdcl() {
		return zdcl;
	}
	public void setZdcl(String zdcl) {
		this.zdcl = zdcl;
	}
	public String getZxcl() {
		return zxcl;
	}
	public void setZxcl(String zxcl) {
		this.zxcl = zxcl;
	}
	public String getJdfzd() {
		return jdfzd;
	}
	public void setJdfzd(String jdfzd) {
		this.jdfzd = jdfzd;
	}
	public String getJdrq() {
		if(jdrq.length()>10){
			return jdrq.substring(0,10);
		}
		return jdrq;
	}
	public void setJdrq(String jdrq) {
		this.jdrq = jdrq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}



	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public Map<String,Object> toMap(){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		returnMap.put("id",this.id);
		returnMap.put("sjdw",this.sjdw);
		returnMap.put("zzc",this.zzc);
		returnMap.put("xhgg",this.xhgg);
		returnMap.put("ccbh",this.ccbh);
		returnMap.put("jqddj",this.jqddj);
		returnMap.put("qjmc",this.qjmc);
		returnMap.put("zdcl",this.zdcl);
		returnMap.put("zxcl",this.zxcl);
		returnMap.put("jdfzd",this.jdfzd);
		returnMap.put("jdrq",this.jdrq.length()>10 ? this.jdrq.substring(0,10) : this.jdrq);
		returnMap.put("bz",this.bz);
		return returnMap;
	}

}
