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
import com.indihx.comm.util.DateUtil;
import com.indihx.org.dao.HocMapper;
import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_HOC_STAFF;
import com.indihx.org.entity.WY_YWH;
import com.indihx.org.service.IHocService;
import com.indihx.org.vo.HocVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;

@Service
public class HocServiceImpl extends AbstractBaseService  implements IHocService {

	@Resource
	private   HocMapper hocMapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	@Override
	public Map<String, Object> hocList(UsrInfo user,HocVo hocvo) {
		WY_YWH ywh = new WY_YWH();
		EntityVoConverter.Convert(hocvo, ywh);
		
		String orgType = user.getOrgType();
		if(orgType.equals(InitSysConstants.ORGTYPE_QUJU)) {
			ywh.setHpbid(user.getHpbBaseId());
		}
		PageHelper.startPage(hocvo.getPages(), hocvo.getRows());
		List<WY_YWH> listInfo = hocMapper.hocListAll(ywh);
		PageInfo<WY_YWH> pageInfo = new PageInfo<WY_YWH>(listInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addHocInfo(UsrInfo usrInfo,HocVo hocvo) throws BusinessException {
		Assert.hasText(hocvo.getYwhdm(), "业委会代码不能为空!");
		Assert.hasText(hocvo.getYwhmc(), "业委会名称不能为空!");
		
		String ywhid=commMapper.getPrimaryKey();
		WY_YWH ywh =new WY_YWH();
		ywh.setYwhid(ywhid);//业委会信息ID
		EntityVoConverter.Convert(hocvo, ywh);
		 
		//验证企业是否已经存在
		List<WY_YWH> listInfo = hocMapper.validHocIsExists(ywh);
		if(!listInfo.isEmpty()){
			throw new BusinessException(ywh.getYwhmc()+"名称或编码已经存在，不能重复录入!");
		}
		int num=hocMapper.insertHoc(ywh); 
		
		
		//保存组织机构信息表
		ORG_INFO oi=new ORG_INFO();
		oi.setParent_org_no(usrInfo.getOrgNo().toString());//上级单位ID
		oi.setParent_org_name(usrInfo.getOrgName());//上级单位名称
		oi.setOrg_no(ywhid);//单位ID
		oi.setOrg_name(hocvo.getYwhmc());//单位名称
		oi.setOrg_type("06");//单位类别  01 市局   02区(市/县)  03街道    04 居委会   05 物业服务企业 06 业主委员会 07 小区管理处
		oi.setAddres("");//办公地址
		oi.setTel_no(hocvo.getLxrdh());//联系电话
		oi.setOper_usr(usrInfo.getUsrId().toString());//创建人
		oi.setTm_smp(DateUtil.getSysDate());//创建日期
		oi.setOrg_status("0"); //单位状态  /** 0：正常、1：禁用、2：注销  */
		oi.setLink_man(hocvo.getLxr());;//联系人
		oi.setEmail("");//EMAIL地址
		oi.setPost_code("");//邮政编码
		int org=hocMapper.insertOrgInfo(oi);//保存机构信息
				 
				 
		//业委会成员=主任
		WY_HOC_STAFF zr=new WY_HOC_STAFF();
		String zrid=commMapper.getPrimaryKey();
		zr.setYwhryid(zrid); ;//人员ID
		zr.setYwhid(ywhid); ;//业委会ID
		zr.setRylx("1");//人员类型-主任
		zr.setStatus("0");;//数据状态-正常
		zr.setRyxm(hocvo.getZrryxm());//姓名
		zr.setRyxb(hocvo.getZrryxb());//性别
		zr.setCsrq(hocvo.getZrcsrq());//出生日期
		zr.setZjlx(hocvo.getZrzjlx());//证件类型
		zr.setZjhm(hocvo.getZrzjhm());//证件号码
		zr.setWhcd(hocvo.getZrwhcd());//文化程度
		zr.setRyyx(hocvo.getZrryyx());//邮箱Email
		zr.setLxdh(hocvo.getZrlxdh());//联系电话
		zr.setRzrq(hocvo.getZrrzrq());//上任日期
		int zrnum=hocMapper.insertHocStaff(zr);
		 
		
		//业委会成员=预留印玺副主任
		WY_HOC_STAFF yl=new WY_HOC_STAFF();
		String ylid=commMapper.getPrimaryKey();
		yl.setYwhryid(ylid); ;//人员ID
		yl.setYwhid(ywhid); ;//业委会ID
		yl.setRylx("2");//人员类型-预留印玺副主任
		yl.setStatus("0");;//数据状态-正常
		yl.setRyxm(hocvo.getYlryxm());//姓名
		yl.setRyxb(hocvo.getYlryxb());//性别
		yl.setCsrq(hocvo.getYlcsrq());//出生日期
		yl.setZjlx(hocvo.getYlzjlx());//证件类型
		yl.setZjhm(hocvo.getYlzjhm());//证件号码
		yl.setWhcd(hocvo.getYlwhcd());//文化程度
		yl.setRyyx(hocvo.getYlryyx());//邮箱Email
		yl.setLxdh(hocvo.getYllxdh());//联系电话
		yl.setRzrq(hocvo.getYlrzrq());//上任日期
		int ylnum=hocMapper.insertHocStaff(yl);
		
		
		//业委会成员=主任印玺副主任
		WY_HOC_STAFF fzr=new WY_HOC_STAFF();
		String fzrid=commMapper.getPrimaryKey();
		fzr.setYwhryid(fzrid); ;//人员ID
		fzr.setYwhid(ywhid); ;//业委会ID
		fzr.setRylx("3");//人员类型
		fzr.setStatus("0");;//数据状态-正常
		fzr.setRyxm(hocvo.getFzrryxm());//姓名
		fzr.setRyxb(hocvo.getFzrryxb());//性别
		fzr.setCsrq(hocvo.getFzrcsrq());//出生日期
		fzr.setZjlx(hocvo.getFzrzjlx());//证件类型
		fzr.setZjhm(hocvo.getFzrzjhm());//证件号码
		fzr.setWhcd(hocvo.getFzrwhcd());//文化程度
		fzr.setRyyx(hocvo.getFzrryyx());//邮箱Email
		fzr.setLxdh(hocvo.getFzrlxdh());//联系电话
		fzr.setRzrq(hocvo.getFzrrzrq());//上任日期
		int fzrnum=hocMapper.insertHocStaff(fzr);
		if(num>0&&org>0&&zrnum>0&&ylnum>0&&fzrnum>0){
			return true;
		}
		return false;
	}

	@Override
	public WY_YWH qrHocInfoById(String ywhId) {
		return hocMapper.qrHocInfoById(new Long(ywhId));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean editHocInfo(HocVo hocvo) throws BusinessException {
		Assert.hasText(hocvo.getYwhdm(), "业委会代码不能为空!");
		Assert.hasText(hocvo.getYwhmc(), "业委会名称不能为空!");
		
		String ywhid=hocvo.getYwhid();
		WY_YWH ywh =new WY_YWH();
		ywh.setYwhid(ywhid); 
		EntityVoConverter.Convert(hocvo, ywh);
		
		//验证企业是否已经存在
		List<WY_YWH> listInfo = hocMapper.validHocIsExists(ywh);
		if(!listInfo.isEmpty()){
			throw new BusinessException(ywh.getYwhmc()+"名称或编码已经存在，不能重复录入!");
		}
		int num=hocMapper.updateHoc(ywh);
		
		
		//更新组织机构信息表
		ORG_INFO oi=new ORG_INFO();
		oi.setOrg_no(ywhid);//单位ID
		oi.setOrg_name(ywh.getYwhmc());//单位名称
		oi.setTel_no(hocvo.getLxrdh());//联系电话
		oi.setLink_man(hocvo.getLxr());//联系人
		int org=hocMapper.updateOrgInfo(oi);//更新机构信息
		
		//业委会成员=主任
		WY_HOC_STAFF zr=new WY_HOC_STAFF();
		zr.setYwhryid(hocvo.getZrywhryid()); ;//人员ID-主任 
		zr.setYwhid(ywhid);
		zr.setRyxm(hocvo.getZrryxm());//姓名
		zr.setRyxb(hocvo.getZrryxb());//性别
		zr.setCsrq(hocvo.getZrcsrq());//出生日期
		zr.setZjlx(hocvo.getZrzjlx());//证件类型
		zr.setZjhm(hocvo.getZrzjhm());//证件号码
		zr.setWhcd(hocvo.getZrwhcd());//文化程度
		zr.setRyyx(hocvo.getZrryyx());//邮箱Email
		zr.setLxdh(hocvo.getZrlxdh());//联系电话
		zr.setRzrq(hocvo.getZrrzrq());//上任日期
		int zrnum=hocMapper.updateHocStaff(zr);
		
		
		//业委会成员=预留印玺副主任
		WY_HOC_STAFF yl=new WY_HOC_STAFF();
		yl.setYwhryid(hocvo.getYlywhryid()); ;//人员ID 
		yl.setYwhid(ywhid);
		yl.setRyxm(hocvo.getYlryxm());//姓名
		yl.setRyxb(hocvo.getYlryxb());//性别
		yl.setCsrq(hocvo.getYlcsrq());//出生日期
		yl.setZjlx(hocvo.getYlzjlx());//证件类型
		yl.setZjhm(hocvo.getYlzjhm());//证件号码
		yl.setWhcd(hocvo.getYlwhcd());//文化程度
		yl.setRyyx(hocvo.getYlryyx());//邮箱Email
		yl.setLxdh(hocvo.getYllxdh());//联系电话
		yl.setRzrq(hocvo.getYlrzrq());//上任日期
		int ylnum=hocMapper.updateHocStaff(yl);
		
		
		//业委会成员=主任印玺副主任
		WY_HOC_STAFF fzr=new WY_HOC_STAFF();
		fzr.setYwhryid(hocvo.getFzrywhryid()); ;//人员ID 
		fzr.setYwhid(ywhid);
		fzr.setRyxm(hocvo.getFzrryxm());//姓名
		fzr.setRyxb(hocvo.getFzrryxb());//性别
		fzr.setCsrq(hocvo.getFzrcsrq());//出生日期
		fzr.setZjlx(hocvo.getFzrzjlx());//证件类型
		fzr.setZjhm(hocvo.getFzrzjhm());//证件号码
		fzr.setWhcd(hocvo.getFzrwhcd());//文化程度
		fzr.setRyyx(hocvo.getFzrryyx());//邮箱Email
		fzr.setLxdh(hocvo.getFzrlxdh());//联系电话
		fzr.setRzrq(hocvo.getFzrrzrq());//上任日期
		int fzrnum=hocMapper.updateHocStaff(fzr);
		if(num>0&&org>0&&zrnum>0&&ylnum>0&&fzrnum>0){
			return true;
		}
		return false;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delHocInfo(String[] ywhIdList) {
		for(int i=0;i<ywhIdList.length;i++){
			if(!StringUtil.isEmpty(ywhIdList[i])){
				hocMapper.deleteHoc(new Long(ywhIdList[i]));//删除业委会
				hocMapper.deleteHocStaff(new Long(ywhIdList[i]));//删除业委会人员
				hocMapper.deleteOrgInfo(ywhIdList[i]);
			}
		}
		return true;
	}

	@Override
	public WY_HOC_STAFF qrHocStaffInfoById(Long ywhryid,Long rylx) {
		WY_HOC_STAFF staff= hocMapper.qrHocStaffInfoById(ywhryid,rylx);
		return staff;
	}

 
 
}
