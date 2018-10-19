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
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.org.dao.CspMapper;
import com.indihx.org.dao.CspSectMapper;
import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_GLC;
import com.indihx.org.entity.WY_WYGS;
import com.indihx.org.service.ICspSectService;
import com.indihx.org.vo.CspSectVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspSectServiceImp.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日 下午6:52:35</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspSectServiceImp.java</p>
 * <p></p>
 */

@Service
public class CspSectServiceImpl  implements ICspSectService {
	
	@Resource
	private CspSectMapper cspSectMapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	@Resource
	private   CspMapper cspinfoMapper;
	
	/* 根据条件查询全市的管理处列表
	 * @see com.indihx.org.service.ICspSectService#getCsList(com.indihx.org.vo.CspSectVo)
	 */
	@Override
	public Map<String, Object> getCsList(UsrInfo user,CspSectVo cspSectVo) {
		//获取页面输入参数，并将其转为实体对象
		WY_GLC glc = new WY_GLC();
		EntityVoConverter.Convert(cspSectVo, glc);
		
		String orgType = user.getOrgType();
		if(orgType.equals(InitSysConstants.ORGTYPE_WYGS)) {
			glc.setWygsid(user.getOrgNo().toString());
		} 
		//设置分页
		PageHelper.startPage(cspSectVo.getPages(), cspSectVo.getRows());
		//调用Mapper根据参数获取区县列表信息
		List<WY_GLC> listInfo = cspSectMapper.getCsList(glc);
		PageInfo<WY_GLC> pageInfo = new PageInfo<WY_GLC>(listInfo);
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addCsInfo(UsrInfo usrInfo,CspSectVo cspSectVo) throws BusinessException {
		try{
			Assert.hasText(cspSectVo.getWygsid(), "物业公司ID不能为空!");
			Assert.hasText(cspSectVo.getGlcmc(), "项目管理处名称不能为空!");
			Assert.hasText(cspSectVo.getGlcdz(), "项目管理处地址不能为空!");
			Assert.hasText(cspSectVo.getGlclxr(), "管理处联系人不能为空!");
			Assert.hasText(cspSectVo.getGlclxdh(), "管理处联系电话不能为空!");
			Assert.hasText(cspSectVo.getZbdh(), "值班电话不能为空!");
			Assert.hasText(cspSectVo.getBxdh(), "保修电话不能为空!");
			 
			String id=commMapper.getPrimaryKey();
			
			WY_GLC glc=new WY_GLC();
			EntityVoConverter.Convert(cspSectVo, glc);
			glc.setStatus("0");						//管理处状态
			//验证企业是否已经存在
			List<WY_GLC> listInfo = cspSectMapper.validCsIsExists(glc);
			if(!listInfo.isEmpty()){
				throw new BusinessException(glc.getGlcmc()+"名称已经存在，不能重复录入!");
			}
			glc.setGlcid(id);						//管理处ID
			int num=cspSectMapper.insertCs(glc);
			
			String wygsid=cspSectVo.getWygsid();
			WY_WYGS wy= cspinfoMapper.qrCspInfoById(wygsid);
			
			//保存组织机构信息表
			ORG_INFO oi=new ORG_INFO();
			oi.setParent_org_no(wygsid);//上级单位ID
			oi.setParent_org_name(wy.getWygsmc());//上级单位名称
			oi.setOrg_no(id);//单位ID
			oi.setOrg_name(cspSectVo.getGlcmc());//单位名称
			oi.setOrg_type("07");//单位类别  01 市局   02区(市/县)  03街道    04 居委会   05 物业服务企业 06 业主委员会 07 小区管理处
			oi.setAddres(cspSectVo.getGlcdz());//办公地址
			oi.setTel_no(cspSectVo.getGlclxdh());//联系电话
			oi.setOper_usr(usrInfo.getUsrId().toString());//创建人
			oi.setTm_smp(DateUtil.getSysDate());//创建日期
			oi.setOrg_status("0"); //单位状态  /** 0：正常、1：禁用、2：注销  */
			oi.setLink_man(cspSectVo.getGlclxr());;//联系人
			oi.setEmail("");//EMAIL地址
			oi.setPost_code("");//邮政编码
			int org=cspSectMapper.insertOrgInfo(oi);//保存机构信息
			
			if(num>0&&org>0){
				return true;
			}
			return false;
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delCsInfo(String[] glcList) {
		for(int i=0;i<glcList.length;i++){
			if(!StringUtil.isEmpty(glcList[i])){
				cspSectMapper.deleteCs(new Long(glcList[i]));
				cspSectMapper.deleteOrgInfo(glcList[i]);
			}
		}
		return true;
	}
	
	@Override
	public WY_GLC qrCsInfoById(String glcid) {
		return cspSectMapper.qrCsInfoById(new Long(glcid));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean editCsInfo(CspSectVo cspSectVo) throws BusinessException {
		Assert.hasText(cspSectVo.getGlcid(), "管理处ID不能为空!");
		Assert.hasText(cspSectVo.getWygsid(), "物业公司ID不能为空!");
		Assert.hasText(cspSectVo.getGlcmc(), "项目管理处名称不能为空!");
		Assert.hasText(cspSectVo.getGlcdz(), "项目管理处地址不能为空!");
		Assert.hasText(cspSectVo.getGlclxr(), "管理处联系人不能为空!");
		Assert.hasText(cspSectVo.getGlclxdh(), "管理处联系电话不能为空!");
		Assert.hasText(cspSectVo.getZbdh(), "值班电话不能为空!");
		Assert.hasText(cspSectVo.getBxdh(), "保修电话不能为空!");
		 
		WY_GLC glc=new WY_GLC();
		EntityVoConverter.Convert(cspSectVo, glc);
		glc.setGlcid(cspSectVo.getGlcid());		//管理处ID 
		//验证企业是否已经存在
		List<WY_GLC> listInfo = cspSectMapper.validCsIsExists(glc);
		if(!listInfo.isEmpty()){
			throw new BusinessException(glc.getGlcmc()+"名称已经存在，不能重复录入!");
		}
		int num=cspSectMapper.updateCs(glc);
		 
		//更新组织机构信息表
		ORG_INFO oi=new ORG_INFO();
		oi.setOrg_no(cspSectVo.getGlcid());//单位ID
		oi.setOrg_name(cspSectVo.getGlcmc());//单位名称
		oi.setAddres(cspSectVo.getGlcdz());//办公地址
		oi.setTel_no(cspSectVo.getGlclxdh());//联系电话
		oi.setLink_man(cspSectVo.getGlclxr());//联系人
		int org=cspSectMapper.updateOrgInfo(oi);//更新机构信息
		if(num>0&&org>0){
			return true;
		}
		return false;
	}
	

}
