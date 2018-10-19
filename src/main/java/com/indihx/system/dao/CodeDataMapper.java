package com.indihx.system.dao;

import java.util.List;

import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.CodeDataKey;

public interface CodeDataMapper {
	//通过主键删除字典信息
    int deleteByPrimaryKey(CodeDataKey key);
    //新增数据字典信息
    int insert(CodeData record);
    //新增字典（根据字段值的有无新增信息）
    int insertSelective(CodeData record);
    //主键查询
    CodeData selectByPrimaryKey(CodeDataKey key);
    //更新有值的字段值
    int updateByPrimaryKeySelective(CodeData record);
    //根据主键更新所有字段值
    int updateByPrimaryKey(CodeData record);
    //根据筛选条件查询所有字典信息
    List<CodeData>  qryCodeDataAll(CodeData record);
    //初始化分组查询数据字段项
    List<String>  qryCodeNo();
    
    
}