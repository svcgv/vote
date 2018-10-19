package com.indihx.system.dao.leave;

import com.indihx.system.entity.leave.VolHis;

public interface VolHisMapper {
    int deleteByPrimaryKey(Long hisId);

    int insert(VolHis record);

    int insertSelective(VolHis record);

    VolHis selectByPrimaryKey(Long hisId);

    int updateByPrimaryKeySelective(VolHis record);

    int updateByPrimaryKey(VolHis record);
}