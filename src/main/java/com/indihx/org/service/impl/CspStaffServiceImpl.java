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
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.org.dao.CspStaffMapper;
import com.indihx.org.entity.WY_WYGS_STAFF;
import com.indihx.org.service.ICspStaffService;
import com.indihx.org.vo.CspStaffVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspStaffServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月9日 上午11:34:13</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspStaffServiceImpl.java</p>
 * <p></p>
 */

@Service
public class CspStaffServiceImpl extends AbstractBaseService implements ICspStaffService {

	@Resource
	private CspStaffMapper cspStaffMapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	@Override
	public Map<String, Object> getStaffList(CspStaffVo cspStaffVo,UsrInfo usrInfo) {
		//获取页面输入参数，并将其转为实体对象
		WY_WYGS_STAFF staff = new WY_WYGS_STAFF();
		EntityVoConverter.Convert(cspStaffVo, staff);
		
		String orgType = usrInfo.getOrgType();
		if(orgType.equals(InitSysConstants.ORGTYPE_WYGS)) {
			staff.setWygsid(usrInfo.getOrgNo());
		}
		//设置分页
		PageHelper.startPage(cspStaffVo.getPages(), cspStaffVo.getRows());
		//调用Mapper根据参数获取人员列表信息
		List<WY_WYGS_STAFF> listInfo = cspStaffMapper.getStaffList(staff);
		PageInfo<WY_WYGS_STAFF> pageInfo = new PageInfo<WY_WYGS_STAFF>(listInfo);
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addStaffInfo(CspStaffVo cspStaffVo,UsrInfo usrInfo) throws BusinessException {
		Assert.hasText(cspStaffVo.getRylx(), "人员类型不能为空!");
		Assert.hasText(cspStaffVo.getZjlx(), "证件类型不能为空!");
		Assert.hasText(cspStaffVo.getWhcd(), "文化程度不能为空!");
		Assert.hasText(cspStaffVo.getRyxb(), "人员性别不能为空!");
		Assert.hasText(cspStaffVo.getRyxm(), "人员姓名不能为空!");
		 
		String id=commMapper.getPrimaryKey();
		WY_WYGS_STAFF staff=new WY_WYGS_STAFF();
		staff.setGsryid(id);						//人员ID
		staff.setWygsid(usrInfo.getOrgNo());		//物业公司ID
		staff.setRylx(cspStaffVo.getRylx());		//人员类型
		staff.setStatus(InitSysConstants.DATA_ZhengChang.toString());	//数据状态
		staff.setZjlx(cspStaffVo.getZjlx());		//证件类型
		staff.setZjhm(cspStaffVo.getZjhm());		//证件号码
		staff.setRyxm(cspStaffVo.getRyxm());		//人员姓名
		staff.setRyxb(cspStaffVo.getRyxb());		//人员性别
		staff.setWhcd(cspStaffVo.getWhcd());		//文化程度
		staff.setDrzw(cspStaffVo.getDrzw());		//职务
		staff.setLxsj(cspStaffVo.getLxsj());		//手机
		staff.setLxdh(cspStaffVo.getLxdh());		//电话
		staff.setLxyx(cspStaffVo.getLxyx());		//邮箱
		staff.setCsrq(cspStaffVo.getCsrq());		//出生日期
		staff.setYzbm(cspStaffVo.getYzbm());		//邮政编码
		staff.setHtksrq(cspStaffVo.getHtksrq());	//劳务合同开始日期
		staff.setHtjzrq(cspStaffVo.getHtjzrq());	//劳务合同截止日期
		staff.setWgnx(cspStaffVo.getWgnx());		//从事物管年限
		staff.setLxdz(cspStaffVo.getLxdz());		//联系地址
		staff.setBz(cspStaffVo.getBz());			//备注
		
		//新增数据
		int num=cspStaffMapper.insertStaff(staff);
		if(num>0){
			return true;
		}
		return false;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delStaffInfo(String[] gsryidList) {
		for(int i=0;i<gsryidList.length;i++){
			if(!StringUtil.isEmpty(gsryidList[i])){
				cspStaffMapper.deleteStaff(new Long(gsryidList[i]));
			}
		}
		return true;
	}
	
	@Override
	public WY_WYGS_STAFF qrStaffInfoById(String gsryid) {
		return cspStaffMapper.qrStaffInfoById(new Long(gsryid));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean editStaffInfo(CspStaffVo cspStaffVo) throws BusinessException {
		Assert.hasText(cspStaffVo.getRylx(), "人员类型不能为空!");
		Assert.hasText(cspStaffVo.getZjlx(), "证件类型不能为空!");
		Assert.hasText(cspStaffVo.getRyxm(), "人员姓名不能为空!");
		 
		WY_WYGS_STAFF staff=new WY_WYGS_STAFF();
		staff.setGsryid(cspStaffVo.getGsryid());	//人员ID
		staff.setRylx(cspStaffVo.getRylx());		//人员类型
		staff.setStatus(cspStaffVo.getStatus());	//数据状态
		staff.setZjlx(cspStaffVo.getZjlx());		//证件类型
		staff.setZjhm(cspStaffVo.getZjhm());		//证件号码
		staff.setRyxm(cspStaffVo.getRyxm());		//人员姓名
		staff.setRyxb(cspStaffVo.getRyxb());		//人员性别
		staff.setWhcd(cspStaffVo.getWhcd());		//文化程度
		staff.setDrzw(cspStaffVo.getDrzw());		//职务
		staff.setLxsj(cspStaffVo.getLxsj());		//手机
		staff.setLxdh(cspStaffVo.getLxdh());		//电话
		staff.setLxyx(cspStaffVo.getLxyx());		//邮箱
		staff.setCsrq(cspStaffVo.getCsrq());		//出生日期
		staff.setYzbm(cspStaffVo.getYzbm());		//邮政编码
		staff.setHtksrq(cspStaffVo.getHtksrq());	//劳务合同开始日期
		staff.setHtjzrq(cspStaffVo.getHtjzrq());	//劳务合同截止日期
		staff.setWgnx(cspStaffVo.getWgnx());		//从事物管年限
		staff.setLxdz(cspStaffVo.getLxdz());		//联系地址
		staff.setBz(cspStaffVo.getBz());			//备注
		
		//新增数据
		int num=cspStaffMapper.updateStaff(staff);
		if(num>0){
			return true;
		}
		return false;
	}
	
	
	
	
	
}
