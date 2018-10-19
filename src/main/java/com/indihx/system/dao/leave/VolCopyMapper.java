package com.indihx.system.dao.leave;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.indihx.system.entity.leave.VolCopy;
@MapperScan
public interface VolCopyMapper {
    int deleteByPrimaryKey(Long voaId);

    int insert(VolCopy record);

    int insertSelective(VolCopy record);

    /***
     * 根据查询条件查询副表的记录
     * @param reqMap
     * @return
     */
    VolCopy selectByPrimaryKey(Map<String, Object> reqMap);

    int updateByPrimaryKeySelective(VolCopy record);

    int updateByPrimaryKey(VolCopy record);
}