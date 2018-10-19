package com.indihx.system.service.impl;

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
import com.indihx.AbstractBaseService;
import com.indihx.comm.util.DateUtil;
import com.indihx.system.dao.BtnInfoMapper;
import com.indihx.system.entity.BtnInfo;
import com.indihx.system.service.IBtnInfoService;
import com.indihx.system.vo.BtnInfoVo;
import com.indihx.util.EntityVoConverter;

/**
 * 
 * <p>
 * 描 述: 按键信息管理
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年7月25日
 * </p>
 * 
 * @author 严蒙蒙
 */
@Service
public class BtnInfoServiceImpl extends AbstractBaseService  implements IBtnInfoService {
	private static Logger logger = LoggerFactory.getLogger(BtnInfoServiceImpl.class);

	@Autowired
	private BtnInfoMapper btnInfoMapper;
	
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryBtnInfoList(BtnInfoVo btnInfoVo) {
		BtnInfo record = new BtnInfo();
	    EntityVoConverter.Convert(btnInfoVo, record);
		Map<String, Object> map = new HashMap<String, Object>();
		// 第几页、每页显示条数，是否汇总
		PageHelper.startPage(btnInfoVo.getPages(), btnInfoVo.getRows(), true);
		List<BtnInfo> listInfo = btnInfoMapper.qryBtnInfoAll(record);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryRoleBtnInfoList(BtnInfoVo btnInfoVo) {
		BtnInfo record = new BtnInfo();
	    EntityVoConverter.Convert(btnInfoVo, record);
		Map<String, Object> map = new HashMap<String, Object>();
		// 第几页、每页显示条数，是否汇总
		PageHelper.startPage(btnInfoVo.getPages(), btnInfoVo.getRows(), true);
		List<BtnInfo> listInfo = btnInfoMapper.qryRoleBtnAll(record);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addBtnInfo(BtnInfoVo btnInfoVo) {
		BtnInfo record = new BtnInfo();
		EntityVoConverter.Convert(btnInfoVo, record);
		record.setTmSmp(DateUtil.getSysDate());
		int num = btnInfoMapper.insertSelective(record);
		if (num > 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public BtnInfo qryBtnInfoById(BtnInfoVo btnInfoVo) {
		return btnInfoMapper.selectByPrimaryKey(btnInfoVo.getBtnId());
	}
	
	//通过主键修改字典信息
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updBtnInfoById(BtnInfoVo btnInfoVo) {
		try {
			BtnInfo record = new BtnInfo();
			EntityVoConverter.Convert(btnInfoVo, record);
			record.setTmSmp(DateUtil.getSysDate());
			int cnt = btnInfoMapper.updateByPrimaryKeySelective(record);
			if (cnt < 1) {
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
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteBtnInfo(BtnInfoVo btnInfoVo) {
		int num=0;
		String []btnIds = btnInfoVo.getBtnId().split(",");
		for(int i=0;i<btnIds.length;i++){
			if (!StringUtils.isEmpty(btnIds[i])) {
				num=btnInfoMapper.deleteByPrimaryKey(btnIds[i]);
			}
		}
		return (num>0);
	}


	@Override
	public String qryBtnPrimary(String btnId) {
		List<BtnInfo> listInfo = btnInfoMapper.qryBtnInfoAll(new BtnInfo());
		for(BtnInfo btnInfo:listInfo){
			if (btnInfo.getBtnId().equals(btnId)){
				return "1";
			}
		}
		return "0";
	}
}
