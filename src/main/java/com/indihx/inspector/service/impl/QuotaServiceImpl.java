package com.indihx.inspector.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.inspector.dao.QuotaMapper;
import com.indihx.inspector.entity.Dc_Khzb;
import com.indihx.inspector.service.IQuotaService;
import com.indihx.inspector.vo.KhzbVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.EntityVoConverter;

@Service
public class QuotaServiceImpl implements IQuotaService {
	
	@Resource
	QuotaMapper quotaMapper;
	@Resource
	CommUtilMapper commMapper;
	@Autowired
	PrimaryKey primaryKey;
	
	/**
	 * 查询指标列表
	 * @param dc_khzb
	 * @return
	 */
	@Override
	public Map<String, Object> queryKhzbList(KhzbVo vo){
		//获取页面传入参数，并将其转换为实体对象
		Dc_Khzb dc_khzb = new Dc_Khzb();
		EntityVoConverter.Convert(vo,dc_khzb);
		dc_khzb.setStatus("0");
		//设置分页
		PageHelper.startPage(vo.getPages(), vo.getRows());
		
		//调用Mapper获取指标列表
		List<Dc_Khzb> listInfo = quotaMapper.qryQuotaList(dc_khzb);
		PageInfo<Dc_Khzb> pageInfo = new PageInfo<Dc_Khzb>(listInfo);
		
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	
	
	/**
	 * 查询一级指标
	 * @param aqCommitteeVo
	 * @return
	 */
	@Override
	public Map<String, Object> qryOneQuotaList(KhzbVo vo) {
		Dc_Khzb dc_khzb = new Dc_Khzb();
		EntityVoConverter.Convert(vo, dc_khzb);
		dc_khzb.setStatus("0");//状态0 正常
		dc_khzb.setZbcj("1");//指标层级-一级
		List<Dc_Khzb> listInfo = quotaMapper.qryQuotaList(dc_khzb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listInfo", listInfo);
		return map;
	}
	
	
	/**
	 * 新增一个考核指标
	 * @param dc_khzb
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean insertOneKhzb(UsrInfo usrInfo,KhzbVo vo) throws BusinessException{
		Assert.hasText(vo.getZblb(),"指标类别不能为空");
		Assert.hasText(vo.getZbcj(),"指标层级不能为空");
		Assert.hasText(vo.getZbmc(),"指标名称不能为空");
		try{ 
			//生产指标编码和指标流水号
			String zbbm = commMapper.getQuotaCodeSequenceVal();
			String check_seq = primaryKey.getPrimaryKey();;
			
			//将传入参数转换为实体
			Dc_Khzb dc_khzb = new Dc_Khzb();
			EntityVoConverter.Convert(vo,dc_khzb);
			dc_khzb.setCheck_seq(new Long(check_seq));
			dc_khzb.setZbbm(zbbm);
			dc_khzb.setStatus("0");//状态，0待启用 1正常 2已注销
			dc_khzb.setCjrq(DateUtil.getSysDate());//创建日期
			dc_khzb.setCjsj(DateUtil.getSysTime());//创建时间
			dc_khzb.setOrg_no(usrInfo.getOrgNo());//创建单位ID
			dc_khzb.setCzybh(usrInfo.getUsrId());//操作员编号
			if(dc_khzb.getZbcj().equals("2")) {
				dc_khzb.setSuper_check_seq(new Long(vo.getSuper_check_seq()));
			}
			int quota=quotaMapper.insertQuota(dc_khzb);
			if(quota>0){
				return true; 
			}
			return false;
		}catch(Exception e){
			 throw new BusinessException("操作失败："+e.getMessage());
		}
	}
	
	/**
	 * 根据主键更新一个考核指标
	 * @param dc_khzb
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateQuota(KhzbVo vo){
		String check_seq =  vo.getCheck_seq()+"";
		Assert.hasText(check_seq,"请选择需要变更指标！");
		Assert.hasText(vo.getZblb(),"指标类别不能为空");
		Assert.hasText(vo.getZbcj(),"指标层级不能为空");
		Assert.hasText(vo.getZbmc(),"指标名称不能为空");
		try{ 
			//将传入参数转换为实体
			Dc_Khzb dc_khzb = new Dc_Khzb();
			EntityVoConverter.Convert(vo,dc_khzb);
			dc_khzb.setCheck_seq(new Long(check_seq));
			int updatequota=quotaMapper.updateQuota(dc_khzb);
			if(updatequota>0){
				return true; 
			}
			return false;
		}catch(Exception e){
			 throw new BusinessException("操作失败："+e.getMessage());
		}
	}
	
	/**
	 * 根据检查指标
	 * @param check_seq
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean deleteOneKhzb(String check_seq){
		Assert.hasText(check_seq, "请选择需要删除的指标！");
		try{
			int delquota=quotaMapper.deleteQuota(new Long(check_seq));
			if(delquota>0){
				return true; 
			}
			return false;
		}catch(Exception e){
			 throw new BusinessException("操作失败："+e.getMessage());
		  }
	}


	@Override
	public Dc_Khzb getQuota(String check_seq) {
		return quotaMapper.getQuota(check_seq);
	}
}
