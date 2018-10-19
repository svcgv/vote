package com.indihx.system.dao.leave;

import java.util.List;

import com.indihx.system.entity.leave.Vol;

public interface VolMapper {
    int deleteByPrimaryKey(Long voaId);

    int insert(Vol record);

    int insertSelective(Vol record);

    Vol selectByPrimaryKey(Long voaId);

    int updateByPrimaryKeySelective(Vol record);

    int updateByPrimaryKey(Vol record);
    
    /***
     * 查询所有的请假记录
     * @return 请假记录
     */
    List<Vol> qryAllVol();
}