package com.indihx.datamng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.AbstractBaseService;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: HpbServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 上午10:26:02</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>HpbServiceImpl.java</p>
 * <p></p>
 */
@Service
public class HpbServiceImpl extends AbstractBaseService  implements IHpbService{

	
	private static Logger logger = LoggerFactory.getLogger(HpbServiceImpl.class);
	@Resource
	private   HpbMapper hpbMapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	/* 
	 * 根据条件查询全市的区县列表
	 * @see com.indihx.datamng.service.IHpbService#getHpbList(com.indihx.datamng.vo.HpbInfoVo)
	 */
	@Override
	public Map<String, Object> getHpbList(HpbInfoVo hpbInfoVo) {
		//获取页面输入参数，并将其转为实体对象
		Wy_Hpb hpb = new Wy_Hpb();
		EntityVoConverter.Convert(hpbInfoVo, hpb);
		//设置分页
		PageHelper.startPage(hpbInfoVo.getPages(), hpbInfoVo.getRows());
		//调用Mapper根据参数获取区县列表信息
		List<Wy_Hpb> listInfo = hpbMapper.getHpbList(hpb);
		PageInfo<Wy_Hpb> pageInfo = new PageInfo<Wy_Hpb>(listInfo);
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}

	/* 
	 * 保存区县信息
	 * @see com.indihx.datamng.service.IHpbService#saveHpbInfo(com.indihx.datamng.vo.HpbInfoVo)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean saveHpbInfo(HpbInfoVo hpbVo) throws Exception{
		try{
			Assert.hasText(hpbVo.getHpbbm(), "区域编码不能为空!");
			Assert.hasText(hpbVo.getHpbmc(), "区域名称不能为空!");
			Assert.hasText(hpbVo.getHpblx(), "区域类型不能为空!");
			
			//设置主键信息
			String hpbId = commMapper.getUserId();
			Wy_Hpb hpb = new Wy_Hpb();
			logger.info(hpbId);
			hpb.setHpbid(hpbId);
			hpb.setCjrq(DateUtil.formatFromDB(DateUtil.getSysDate()));
			//将页面参数转化为实体对象
			EntityVoConverter.Convert(hpbVo, hpb);
			
			//验证区县是否已经存在
			List<Wy_Hpb> listInfo = hpbMapper.validHpbIsExists(hpb);
			if(!listInfo.isEmpty()){
				throw new BusinessException(hpb.getHpbmc()+"的编码或名称已经存在，不能重复录入!");
			}
			
			//保存区县信息
			int num = hpbMapper.insertHpbInfo(hpb);
			if (num !=1) {
				throw new BusinessException("区县信息保存失败!");
			}
			return true;
		}catch(Exception e){
			throw new BusinessException("区县信息保存失败:"+e.getMessage());
		}
	}

	/* 获取区县信息
	 * @see com.indihx.datamng.service.IHpbService#getHpbInfo(com.indihx.datamng.vo.HpbInfoVo)
	 */
	@Override
	public Wy_Hpb getHpbInfo(String hpbid) {
		Assert.hasText(hpbid, "区域ID不能为空!");
		Wy_Hpb hpb = hpbMapper.getHpbInfo(Long.valueOf(hpbid));
		return hpb;
	}

	/* 更新区县信息
	 * @see com.indihx.datamng.service.IHpbService#saveModifyHpbInfo(com.indihx.datamng.vo.HpbInfoVo)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean saveModifyHpbInfo(HpbInfoVo hpbVo) throws Exception {
		try{
			Assert.hasText(hpbVo.getHpbid(), "区域ID不能为空!");
			Assert.hasText(hpbVo.getHpbbm(), "区域编码不能为空!");
			Assert.hasText(hpbVo.getHpbmc(), "区域名称不能为空!");
			Assert.hasText(hpbVo.getHpblx(), "区域类型不能为空!");
			
			Wy_Hpb hpb = new Wy_Hpb();
			//将页面参数转化为实体对象
			EntityVoConverter.Convert(hpbVo, hpb);
			
			//验证区县是否已经存在
			List<Wy_Hpb> listInfo = hpbMapper.validHpbIsExists(hpb);
			if(!listInfo.isEmpty()){
				throw new BusinessException(hpb.getHpbmc()+"的编码或名称已经存在，不能重复录入!");
			}
			
			//保存区县信息
			int num = hpbMapper.updateHpbInfo(hpb);
			if (num != 1) {
				throw new BusinessException("更新区县信息失败！");
			}
			return true;
		}catch(Exception e){
			throw new BusinessException("区县信息修改失败:"+e.getMessage());
		}
	}

	/* 
	 * 删除区县信息
	 * @see com.indihx.datamng.service.IHpbService#deleteHpbInfo(com.indihx.datamng.vo.HpbInfoVo)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteHpbInfo(String hpbid) throws Exception {
		try{
			Assert.hasText(hpbid, "区域ID不能为空!");
			Long id = Long.valueOf(hpbid);
			//删除前检查是否区下存在街道
			List<Wy_Jd> listInfo = hpbMapper.checkLinkIsExists(id);
			if(!listInfo.isEmpty()){
				throw new BusinessException("区县下存在街道信息，不能删除!");
			}
			//删除区县信息
			int num = hpbMapper.deleteHpbInfo(id);
			if(num!=1){
				throw new BusinessException("删除区县信息失败!");
			}
			return true;
		}catch(Exception e){
			throw new BusinessException("区县信息删除失败:"+e.getMessage());
		}
	}

}
