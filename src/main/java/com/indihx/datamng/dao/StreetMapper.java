package com.indihx.datamng.dao;

import java.util.List;

import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.vo.StreetInfoVo;

public interface StreetMapper {
	/*根据主键删除行政区域信息*/
    int deleteByPrimaryKey(Long jdid);
    /*插入一条数据*/
    int insert(Wy_Jd record);
    /*插入一条数据，有值的话则加入，没值则不加入*/
    int insertSelective(Wy_Jd record);
    /*查询行政区域信息*/
    Wy_Jd selectByPrimaryKey(Long jdid);
    /*更新字段非空的区域信息*/
    int updateByPrimaryKeySelective(Wy_Jd record);
    /*更新信息*/
    int updateByPrimaryKey(Wy_Jd record);
    /*查询行政管理所有列表信息*/
    List<Wy_Jd> qryStreetAll(Wy_Jd street);
    /*查询是否存在子分区*/
    int qryPartition(Wy_Jd record);
    /*根据主键删除分区区域信息*/
    int  deletePartition(Long hpbid);
    /*查询上级区域信息*/
    List<Wy_Hpb>   selectParentId();
    /*查询分区区域信息*/
    List<Wy_Jd>   selectPartitionId();
    /*查询上级和区域的信息*/
    List<Wy_Jd> qryAdPaRegionAll(String hpbid);
    /*根据条件查询父级或分区的信息*/
    List<Wy_Jd> qryRegion(Wy_Jd record);
    /***
     * 根据name查询ID
     * @param record
     * @return
     */
    Wy_Jd qryRegionByNameOrId(Wy_Jd record);
    /**
     * 查询类型为区的行政区域
     * @param record
     * @return
     */
	List<Wy_Jd> qryRegionByRegionType(Wy_Jd record);
	List<Wy_Jd> qryRegionList(StreetInfoVo streetVo);
	List<Wy_Hpb> selectParentId1();
	List<Wy_Hpb> selectByRegionType(Wy_Hpb region1);
	Wy_Hpb selectByStreetIdKey(Long strId);
	Wy_Jd openAqstreetUserList(String streetId);
	/**
	 * 
	 * @param orgNo
	 * @return
	 */
	Long getOrgRefJdId(Long orgNo);
}