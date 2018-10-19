package com.indihx.system.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.system.entity.BaseOrgRel;
import com.indihx.system.entity.OrgInfo;
import com.indihx.system.entity.UsrInfo;
public interface OrgInfoMapper {
	    int deleteByPrimaryKey(Long org_no);

	    int insert(OrgInfo record);

	    int insertSelective(OrgInfo record);

	    OrgInfo selectByPrimaryKey(Long org_no);

	    int updateByPrimaryKeySelective(OrgInfo record);

	    int updateByPrimaryKey(OrgInfo record);
	    
	    List<OrgInfo>  qryOrgInfoInfoAll(OrgInfo record);
	    
	    //查询新增页面父级菜单信息
	    List<OrgInfo>  qryparentOrgNo(Long parentOrgNo);

		List<OrgInfo> qryOrgInfoAll(@Param("org_type")String[] org_type,@Param("org") OrgInfo info);

		List<Wy_Jd> qryParentOrgInfoInfoAll(Wy_Jd streetinfo);

		List<Wy_Hpb> qryParentOrgHpbInfoAll(Wy_Hpb hpbinfo);

		int insertAqBaseOrgRel(BaseOrgRel abor);

		List<OrgInfo> qryParentId();

		List<OrgInfo> qryParentName();

		List<OrgInfo> qryStreetOrgId(String hpbid);

		List<OrgInfo> qryStreetOrgName(String hpbid);

		int openStaOrgInfo(Long orgId);

		int closeStaOrgInfo(Long orgId);

		List<UsrInfo> qryFindUsrInfo(String orgId);

		Wy_Jd selectStreetNameByPrimaryKey(Long jdid);

		Wy_Hpb selectHpbNameByPrimaryKey(Long hpbid);

		OrgInfo qryparOrgNameInfo();

		OrgInfo qryparOrgNameInfo2();

		List<OrgInfo> qryStreetOrgNameInfo(String hpbid);

		List<Wy_Hpb> qryIsStreetHpbOrgNameInfo(String hpbid);
}
