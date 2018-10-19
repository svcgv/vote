package com.indihx.system.service;
import java.util.Map;

import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.vo.StreetInfoVo;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.system.vo.OrgInfoVo;
/**
 * 
 * @author ZSS
 *
 */
public interface IOrgInfoService {
	/**
	 * 
	 * @param infovo
	 * @return
	 */
	public Map<String,Object> qryOrgInfo(OrgInfoVo infovo);
	/**
	 * 
	 * @param infovo
	 * @return
	 */
	public 	OrgInfoVo qryOrginfoList(String orgNo);
	/***
	 * 打开新增页面，查询数据字典信息
	 * @return  返回所需的数据字典信息
	 */
	Map<String,Object> qryAddInfo();
	/**
	 * 
	 * @param infovo
	 * @return
	 */
	boolean addOrgInfo(OrgInfoVo infovo, String streetId, String hpbId);
	/**
	 * 
	 * @param infovo
	 * @return
	 */
	boolean updateOrgInfo(OrgInfoVo infovo);
	/**
	 * 
	 * @param infovo
	 * @return
	 */
	public boolean delectOrgInfo(OrgInfoVo infovo);
	Map<String, Object> qryParentOrgStreetInfo(StreetInfoVo streetVo);
	Map<String, Object> qryParentOrgInfo(HpbInfoVo hpbVo);
	String qryParentId(OrgInfoVo infovo);
	String qryParentName(OrgInfoVo infovo);
	String qryStreetOrgId(OrgInfoVo infovo, String hpbId);
	String qryStreetOrgName(OrgInfoVo infovo, String hpbId);
	boolean openStaOrgInfo(OrgInfoVo infovo);
	boolean closeStaOrgInfo(OrgInfoVo infovo);
	String qryFindUsrInfo(OrgInfoVo infovo);
	Wy_Jd qryStreetinfoList(String orgNo);
	Wy_Hpb qryHpbinfoList(String orgNo);
	Map<String, Object> qryparOrgNameInfo();
	Map<String, Object> qryparOrgNameInfo2();
	Map<String, Object> qryStreetOrgNameInfo(String hpbId);
	
	


}
