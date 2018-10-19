package com.indihx.system.service;

import java.util.List;

import com.indihx.system.entity.BtnRole;
import com.indihx.system.vo.BtnRoleVo;

/***
 * 
 * <p>描 述: 按键角色信息管理接口</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年7月26日</p>
 * @author 严蒙蒙
 */
public interface IBtnRoleService  {
	 /***
	 * 根据角色id查询按钮信息
	 * @param  roleId 角色id
	 * @return
	 */
	List<BtnRole> qryBtnRoleAll(String roleId);
	
	
	 /**
	   *  按钮信息新增
		 * @param  btnRoleVo 按钮实体vo          
		 * @return 
		 */
	
	public boolean addBtnRole(BtnRoleVo btnRoleVo);
	 
	 /***
		 * 根据id查询按钮信息
		 * @param  btnRoleVo
		 * @return
		 */
	public BtnRole qryBtnRoleById(BtnRoleVo btnRoleVo);
	 
	
	 
	 /***
		 * 删除所有角色按钮信息
		 * @param  
		 * @return
		 */
	 public boolean deleteBtnRole(String roleId);
	
		
}
