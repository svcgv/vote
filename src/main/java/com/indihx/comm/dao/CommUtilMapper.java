package com.indihx.comm.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface CommUtilMapper {

	String getUserId();
	// 菜单id
	String getMenuId();
	
	//得到主键
	String getPrimaryKey();
	//获取业务档案类别ID
	String getPrimaryFileKey();
	
	String getTableTotole(String tab_name);
	
	String getOrgNo();
	
	String getInfoCodeSequenceVal();
	
	String getQuotaCodeSequenceVal();
	String getCreditCodeSequenceVal();
	
	String getKeyBySequenceName(@Param("seqName")String seqName);
}