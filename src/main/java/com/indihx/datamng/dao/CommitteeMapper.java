package com.indihx.datamng.dao;

import java.util.List;

import com.indihx.datamng.entity.Wy_Sq;

public interface CommitteeMapper {
	/*根据主键删除行政区域信息*/
    int deleteByPrimaryKey(Long cmdId);
    /*插入一条数据，有值的话则加入，没值则不加入*/
    int insertSelective(Wy_Sq record);
    /*查询行政区域信息*/
    Wy_Sq selectByPrimaryKey(Long sqid);
    /*更新字段非空的区域信息*/
    int updateByPrimaryKeySelective(Wy_Sq info);
    /*查询社区所有列表信息*/
    List<Wy_Sq> qryAqCommitteeAll(Wy_Sq aqCommittee);
    /*查询上级区域信息*/
    Wy_Sq qryStreetInfo(Long jdid);
    /*查询街道列表*/
	List<Wy_Sq> qryStreetList(Wy_Sq aqCommittee);
	 /*根据机构ID查询社区*/
	Long getOrgRefSqId(Long orgNo);
}