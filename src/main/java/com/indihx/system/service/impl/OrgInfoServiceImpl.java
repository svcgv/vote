package com.indihx.system.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.comm.util.DateUtil;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.datamng.vo.StreetInfoVo;
import com.indihx.system.dao.OrgInfoMapper;
import com.indihx.system.entity.BaseOrgRel;
import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.OrgInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.IOrgInfoService;
import com.indihx.system.vo.OrgInfoVo;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoServiceImpl 
 * 类描述： 机构管理
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午2:24:16 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
@Service
public class OrgInfoServiceImpl implements IOrgInfoService{
	private static Logger logger = LoggerFactory.getLogger(OrgInfoServiceImpl.class);
    @Autowired
    private OrgInfoMapper orgmapper;
	@Autowired
	private CommUtilMapper mapper;
	/**
	 * 主页面
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryOrgInfo(OrgInfoVo infovo) {
		OrgInfo info=new OrgInfo();
		info.setOrgType(infovo.getOrgType());
		info.setOrgName(infovo.getOrgName());
		Map<String,Object> map=new HashMap<String,Object>();
		PageHelper.startPage(infovo.getPages(), infovo.getRows(), true);
		List<OrgInfo>  listInfo=orgmapper.qryOrgInfoInfoAll(info);
		List<OrgInfoVo> listVo = new ArrayList<OrgInfoVo>();
		for (OrgInfo ruleInfo2 : listInfo) {
			OrgInfoVo infoVo = new OrgInfoVo();
			EntityVoConverter.Convert(ruleInfo2, infoVo);
			infoVo.setOrgType(BasicParameterInfo.getCodeVal("ORG_TYPE", ruleInfo2.getOrgType()));
			infoVo.setOrgStatus(BasicParameterInfo.getCodeVal("ORG_STATUS", ruleInfo2.getOrgStatus()));
			listVo.add(infoVo);
		}
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listVo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	@Override
	public Map<String, Object> qryAddInfo() {
		// 查询预警主体
		String ewTypeHtml = OrgInfoServiceImpl.createHtmlByCode("ORG_TYPE");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ewTypeHtml", ewTypeHtml);
		return map;
	}
	public static String createHtmlByCode(String codeNo){
		List<CodeData> list = BasicParameterInfo.getCodeByCodeNo(codeNo);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' select>--请选择--</option>");
		for (CodeData codeData : list) {
			if("01".equals(codeData.getCodeKey())){
			}else{
			buffer.append("<option value='"+codeData.getCodeKey()+"'>"+codeData.getCodeVal()+"</option>");}
		}
		return buffer.toString();
	}
	
	@Override
	public Map<String, Object> qryparOrgNameInfo() {
		// 查询预警主体
		String parOrgName;
		OrgInfo info = orgmapper.qryparOrgNameInfo();
		parOrgName = info.getOrgName();
		String parOrgNo = info.getOrgNo().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parOrgName", parOrgName);
		map.put("parOrgNo", parOrgNo);
		return map;
	}
	//市房管局
	@Override
	public Map<String, Object> qryparOrgNameInfo2() {
		// 查询预警主体
		String parOrgName;
		OrgInfo info = orgmapper.qryparOrgNameInfo2();
		parOrgName = info.getOrgName();
		String parOrgNo = info.getOrgNo().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parOrgName", parOrgName);
		map.put("parOrgNo", parOrgNo);
		return map;
	}
	//街道的上一级机构
	@Override
	public Map<String, Object> qryStreetOrgNameInfo(String hpbId) {
		List<OrgInfo> info = orgmapper.qryStreetOrgNameInfo(hpbId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", info);
		return map;
	}
	/**
	 * 查询
	 * @param infovo
	 * @return
	 */
	@Override
	public OrgInfoVo qryOrginfoList(String orgNo) {
		String orgId = orgNo.replaceAll(",", "").trim();
		OrgInfo info = orgmapper.selectByPrimaryKey(new Long(orgId));
		OrgInfoVo infoVo = new OrgInfoVo();
		EntityVoConverter.Convert(info, infoVo);
		// 查询预警主体
		String ewTypeHtml = ConstantStatic.createHtmlByCode("ORG_TYPE",infoVo.getOrgType());
		infoVo.setEwTypeHtml(ewTypeHtml);
		return infoVo;
	 
	}
	@Override
	public Wy_Jd qryStreetinfoList(String orgNo) {
		String orgId = orgNo.replaceAll(",", "").trim();
		Wy_Jd info = orgmapper.selectStreetNameByPrimaryKey(new Long(orgId));
		return info;
	}
	@Override
	public Wy_Hpb qryHpbinfoList(String orgNo) {
		String orgId = orgNo.replaceAll(",", "").trim();
		Wy_Hpb info = orgmapper.selectHpbNameByPrimaryKey(new Long(orgId));
		return info;
	}
	/**
	 * 查询上级机构
	 * @param streetVo 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryParentOrgInfo(HpbInfoVo hpbVo) {
			Wy_Hpb hpbinfo = new Wy_Hpb();
			EntityVoConverter.Convert(hpbVo, hpbinfo);
			Map<String,Object> map=new HashMap<String,Object>();
			PageHelper.startPage(hpbVo.getPages(), hpbVo.getRows(), true);
			List<Wy_Hpb> listInfo=orgmapper.qryParentOrgHpbInfoAll(hpbinfo);
			PageInfo pageInfo = new PageInfo(listInfo);
			map.put("listInfo", listInfo);
			map.put("pageInfo", pageInfo);
			return map;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryParentOrgStreetInfo(StreetInfoVo streetVo) {
		Wy_Jd streetinfo = new Wy_Jd();
		EntityVoConverter.Convert(streetVo, streetinfo);
	    Map<String,Object> map=new HashMap<String,Object>();
		PageHelper.startPage(streetVo.getPages(), streetVo.getRows(), true);
		List<Wy_Jd>  listInfo=orgmapper.qryParentOrgInfoInfoAll(streetinfo);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
		
	}
	/**
	 * 新增保存
	 * @param hpbId 
	 * @param streetId 
	 * @param infoVo
	 * @return
	 */
	@Override
	public boolean addOrgInfo(OrgInfoVo infovo, String streetId, String hpbId) {
		OrgInfo info=new OrgInfo();
		String orgNo =mapper.getOrgNo();
		info.setOrgNo(orgNo);
		info.setOperUsr(infovo.getOperUsr());
		info.setOrgName(infovo.getOrgName());
		info.setAddres(infovo.getAddres());
		info.setTelNo(infovo.getTelNo());
		info.setOperUsr(infovo.getOperUsr());
		info.setOrgType(infovo.getOrgType());
		info.setTmSmp(DateUtil.getSysDate());
		info.setParentOrgNo(infovo.getParentOrgNo());
		info.setParentOrgName(infovo.getParentOrgName());
		info.setOrgStatus("0");
		info.setLinkMan(infovo.getLinkMan());
		info.setPostCode(infovo.getPostCode());
		info.setEmail(infovo.getEmail());
		info.setRemark(infovo.getRemark());
		int num=orgmapper.insertSelective(info);
		int num1=0;
		if(!"".equals(streetId)){
			BaseOrgRel abor = new BaseOrgRel();
			abor.setRelId(new Long(mapper.getMenuId()));
			abor.setOrgNo(orgNo);
			abor.setPkId(streetId);
			abor.setPkTable("WY_JD");
			num1=orgmapper.insertAqBaseOrgRel(abor);
		}
		if(!"".equals(hpbId)){
			BaseOrgRel abor = new BaseOrgRel();
			abor.setRelId(new Long(mapper.getMenuId()));
			abor.setOrgNo(orgNo);
			abor.setPkId(hpbId);
			abor.setPkTable("WY_HPB");
			num1=orgmapper.insertAqBaseOrgRel(abor);
		}else{
			num1=1;
		}
		if(num>0&&num1>0){
			return true;
		}
		return false;
	}
	/**
	 * 编辑保存
	 * @param hpbId 
	 * @param streetId 
	 * @param infoVo
	 * @return
	 */
	@Override
	public boolean updateOrgInfo(OrgInfoVo infovo) {
		try {
			OrgInfo info=new OrgInfo();
			String id=infovo.getOrgNo().replaceAll(",", "").trim();
			info.setOrgNo(id);
			info.setOperUsr(infovo.getOperUsr());
			info.setOrgName(infovo.getOrgName());
			info.setAddres(infovo.getAddres());
			info.setOperUsr(infovo.getOperUsr());
			info.setOrgType(infovo.getOrgType());
			info.setTmSmp(DateUtil.getSysDate());
			info.setTelNo(infovo.getTelNo());
			info.setParentOrgName(infovo.getParentOrgName());
			info.setParentOrgNo(infovo.getParentOrgNo());
			info.setLinkMan(infovo.getLinkMan());
			info.setPostCode(infovo.getPostCode());
			info.setEmail(infovo.getEmail());
			info.setRemark(infovo.getRemark());
			int num=orgmapper.updateByPrimaryKeySelective(info);
			if(num<1) {
				return false;	
			}
		} catch (Exception e) {
			logger.info("系统异常！！");
			e.printStackTrace();
			//使用trycatch时，必须手动加上事务回滚，spring默认只会回滚runtimeException异常才回回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();      
		}
		return true;
	} 
	 /**
	   * 删除操作
	   * @param infovo
	   * @return
	   */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean delectOrgInfo(OrgInfoVo infovo) {
		  String orgId = infovo.getOrgNo();
		  String[] str = orgId.split(",");
		  for (int i = 0; i < str.length; i++) {
		  if (!StringUtils.isEmpty(str[i])) {
			orgmapper.deleteByPrimaryKey(new Long(str[i]));
	      }
		}
		return true;
	}
	//新增菜单页面父级菜单的显示
	@Override
	public String qryParentId(OrgInfoVo infovo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrgInfo> parentId=orgmapper.qryParentId();
		map.put("parentId", parentId);
		String parentOrgNo="";
		for(int i=0;i<parentId.size();i++){
			OrgInfo org = parentId.get(i);
			parentOrgNo =org.getOrgNo().toString();
		}
		return parentOrgNo;
	}@Override
	public String qryParentName(OrgInfoVo infovo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrgInfo> parentName=orgmapper.qryParentName();
		map.put("parentName", parentName);
		String parentOrgName="";
		for(int i=0;i<parentName.size();i++){
			OrgInfo org = parentName.get(i);
			parentOrgName =org.getOrgName();
		}
		return parentOrgName;
	}
	//新增菜单页面父级菜单的显示
	@Override
	public String qryStreetOrgId(OrgInfoVo infovo, String hpbid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrgInfo> parentId=orgmapper.qryStreetOrgId(hpbid);
		map.put("parentId", parentId);
		String parentOrgNo="";
		for(int i=0;i<parentId.size();i++){
			OrgInfo org = parentId.get(i);
			parentOrgNo =org.getOrgNo().toString();
		}
		return parentOrgNo;
	}
	@Override
	public String qryStreetOrgName(OrgInfoVo infovo, String hpbId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrgInfo> parentName=orgmapper.qryStreetOrgName(hpbId);
		map.put("parentName", parentName);
		String parentOrgName="";
		for(int i=0;i<parentName.size();i++){
			OrgInfo org = parentName.get(i);
			parentOrgName =org.getOrgName();
		}
		return parentOrgName;
	}
		@Transactional(propagation = Propagation.REQUIRED)
		@Override
		public boolean openStaOrgInfo(OrgInfoVo infovo) {
			  String orgId = infovo.getOrgNo();
				int num = orgmapper.openStaOrgInfo(new Long(orgId));
				if(num<1){
					throw new RuntimeException("启用操作失败！");
				}
			return true;
		}
		@Transactional(propagation = Propagation.REQUIRED)
		@Override
		public boolean closeStaOrgInfo(OrgInfoVo infovo) {
			  String orgId = infovo.getOrgNo();
				int num = orgmapper.closeStaOrgInfo(new Long(orgId));
				if(num<1){
					throw new RuntimeException("关闭操作失败！");
				}
			return true;
		}
		@Override
		public String qryFindUsrInfo(OrgInfoVo infovo) {
			String orgId = infovo.getOrgNo();
			Map<String, Object> map = new HashMap<String, Object>();
			List<UsrInfo>  user =orgmapper.qryFindUsrInfo(orgId);
			String userNum=String.valueOf(user.size());
			map.put("userNum", userNum);
			return userNum;
		}

}
