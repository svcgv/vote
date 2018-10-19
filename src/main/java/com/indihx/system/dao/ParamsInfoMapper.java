package com.indihx.system.dao;

import java.util.List;

import com.indihx.system.entity.ParamsInfo;


public interface ParamsInfoMapper {
    int deleteByPrimaryKey(String paramsNo);

    int insert(ParamsInfo record);

    int insertSelective(ParamsInfo record);

    ParamsInfo selectByPrimaryKey(String paramsNo);

    int updateByPrimaryKeySelective(ParamsInfo record);

    int updateByPrimaryKey(ParamsInfo record);
    
    List<ParamsInfo>  qryParamsInfoAll(ParamsInfo params);
}