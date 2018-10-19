package com.indihx.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.AbstractBaseService;
import com.indihx.comm.util.DateUtil;
import com.indihx.system.dao.BtnRoleMapper;
import com.indihx.system.entity.BtnRole;
import com.indihx.system.service.IBtnRoleService;
import com.indihx.system.vo.BtnRoleVo;

/**
 * 
 * <p>
 * 描 述: 角色按键信息管理
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年7月26日
 * </p>
 * 
 * @author 严蒙蒙
 */
@Service
public class BtnRoleServiceImpl extends AbstractBaseService  implements IBtnRoleService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(BtnRoleServiceImpl.class);

	@Autowired
	private BtnRoleMapper btnRoleMapper;
	
	@Override
	public List<BtnRole> qryBtnRoleAll(String roleId) {
		 List<BtnRole> listBtnRole=btnRoleMapper.qryBtnRoleAll(roleId);
		return listBtnRole;
	}
	

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addBtnRole(BtnRoleVo btnRoleVo) {
		boolean flag=deleteBtnRole(btnRoleVo.getRoleId());
		if (flag){
			BtnRole record = new BtnRole();
			if(btnRoleVo.getBtnId()!=null&&!"".equals(btnRoleVo.getBtnId())){
				String btnIds[]=btnRoleVo.getBtnId().split(",");
				int num =0;
				for(int i=0;i<btnIds.length;i++){
					record.setBtnId(btnIds[i]);
					record.setRoleId(btnRoleVo.getRoleId());
					record.setTmSmp(DateUtil.getSysDate());
					num=btnRoleMapper.insertSelective(record);
				}
				if (num > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	@Override
	public BtnRole qryBtnRoleById(BtnRoleVo btnRoleVo) {
		return btnRoleMapper.selectByPrimaryKey(btnRoleVo.getBtnId(),btnRoleVo.getRoleId());
	}
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	 public boolean deleteBtnRole(String roleId) {
		try {
			btnRoleMapper.deleteAll(roleId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	


	
}
