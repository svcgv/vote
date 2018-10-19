package com.indihx.org.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.indihx.AbstractBaseService;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.org.dao.CommunityMapper;
import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_JWH;
import com.indihx.org.service.ICommunityService;
import com.indihx.org.vo.CommunityVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;
@Service
public class CommunityServiceImpl extends AbstractBaseService implements ICommunityService {

	@Resource
	private  CommunityMapper communitymapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	@Override
	public Map<String, Object> communityList(CommunityVo CommunityVo) {
		WY_JWH jwh = new WY_JWH();
		EntityVoConverter.Convert(CommunityVo, jwh);
		PageHelper.startPage(CommunityVo.getPages(),CommunityVo.getRows());
		List<WY_JWH> listInfo = communitymapper.communityListAll(jwh);
		PageInfo<WY_JWH> pageInfo = new PageInfo<WY_JWH>(listInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addCommunityInfo(UsrInfo usrInfo,CommunityVo vo) throws BusinessException {
		Assert.hasText(vo.getJwhbm(), "居委会编码不能为空!");
		Assert.hasText(vo.getJwhmc(), "居委会名称不能为空!");
		
		String jwhid=commMapper.getPrimaryKey();
		WY_JWH jwh =new WY_JWH();
		jwh.setJwhid(jwhid);;//居委会信息ID 
		EntityVoConverter.Convert(vo, jwh);
		
		//验证企业是否已经存在
		List<WY_JWH> listInfo = communitymapper.validCommunityIsExists(jwh);
		if(!listInfo.isEmpty()){
			throw new BusinessException(jwh.getJwhmc()+"名称或编码已经存在，不能重复录入!");
		}
		int num=communitymapper.insertCommunity(jwh); 
		
		//保存组织机构信息表
		ORG_INFO oi=new ORG_INFO();
		oi.setParent_org_no(usrInfo.getOrgNo().toString());//上级单位ID
		oi.setParent_org_name(usrInfo.getOrgName());//上级单位名称
		oi.setOrg_no(jwhid);//单位ID
		oi.setOrg_name(vo.getJwhmc());//单位名称
		oi.setOrg_type("04");//单位类别  01 市局   02区(市/县)  03街道    04 居委会   05 物业服务企业 06 业主委员会 07 小区管理处
		oi.setAddres(vo.getJwhdz());//办公地址
		oi.setTel_no(vo.getJwzrdh());//联系电话
		oi.setOper_usr(usrInfo.getUsrId().toString());//创建人
		oi.setTm_smp(DateUtil.getSysDate());//创建日期
		oi.setOrg_status("0"); //单位状态  /** 0：正常、1：禁用、2：注销  */
		oi.setLink_man(vo.getJwzr());;//联系人
		oi.setEmail("");//EMAIL地址
		oi.setPost_code("");//邮政编码
		int org=communitymapper.insertOrgInfo(oi);//保存机构信息
		if(num>0&&org>0){
			return true;
		}
		return false;
	}

	@Override
	public WY_JWH qrCommunityInfoById(String jwhId) {
		return communitymapper.qrCommunityInfoById(new Long(jwhId));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean editCommunityInfo(CommunityVo vo) throws BusinessException {
		Assert.hasText(vo.getJwhid(), "居委会ID不能为空!");
		Assert.hasText(vo.getJwhbm(), "居委会编码不能为空!");
		Assert.hasText(vo.getJwhmc(), "居委会名称不能为空!");
		
		WY_JWH jwh =new WY_JWH();
		EntityVoConverter.Convert(vo, jwh);
		jwh.setJwhid(vo.getJwhid());;//居委会信息ID
		
		//验证企业是否已经存在
		List<WY_JWH> listInfo = communitymapper.validCommunityIsExists(jwh);
		if(!listInfo.isEmpty()){
			throw new BusinessException(jwh.getJwhmc()+"名称或编码已经存在，不能重复录入!");
		}
		int num=communitymapper.updateCommunity(jwh); 
		
		//更新组织机构信息表
		ORG_INFO oi=new ORG_INFO();
		oi.setOrg_no(vo.getJwhid());//单位ID
		oi.setOrg_name(vo.getJwhmc());//单位名称
		oi.setAddres(vo.getJwhdz());//办公地址
		oi.setTel_no(vo.getJwzr());//联系电话
		oi.setLink_man(vo.getJwzrdh());//联系人
		int org=communitymapper.updateOrgInfo(oi);//更新机构信息
		if(num>0&&org>0){
			return true;
		}
		return false;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delCommunityInfo(String[] jwhIdList) {
		for(int i=0;i<jwhIdList.length;i++){
			if(!StringUtil.isEmpty(jwhIdList[i])){
				communitymapper.deleteCommunity(new Long(jwhIdList[i]));//删除居委会
				communitymapper.deleteOrgInfo(jwhIdList[i]);
			}
		}
		return true;
	}
}
