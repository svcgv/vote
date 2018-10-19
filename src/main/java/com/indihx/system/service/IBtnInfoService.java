package com.indihx.system.service;

import java.util.Map;

import com.indihx.system.entity.BtnInfo;
import com.indihx.system.vo.BtnInfoVo;

/***
 * 
 * <p>描 述: 按键信息管理接口</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年7月25日</p>
 * @author 严蒙蒙
 */
public interface IBtnInfoService  {
	
	/**
	 * 按钮信息首页查询
	 * @param btnInfoVo  按钮实体vo
	 * @return
	 */
	public  Map<String, Object> qryBtnInfoList(BtnInfoVo btnInfoVo);
	 
	 /**
	   *  按钮信息新增
		 * @param  btnInfoVo 按钮实体vo          
		 * @return 
		 */
	
	public boolean addBtnInfo(BtnInfoVo btnInfoVo);
	 
	 /***
		 * 根据id查询按钮信息
		 * @param  btnInfoVo
		 * @return
		 */
	public BtnInfo qryBtnInfoById(BtnInfoVo btnInfoVo);
	 
	 /***
		 * 修改按钮信息
		 * @param  btnInfoVo
		 * @return
		 */
	 boolean updBtnInfoById(BtnInfoVo btnInfoVo);
	 
	 /***
	 * 删除按钮信息
	 * @param  btnInfoVo
	 * @return
	 */
	 public boolean deleteBtnInfo(BtnInfoVo btnInfoVo);
	 /**
	 * 查询所有主键集合
	 * @param 
	 * @return
	 */
	public String qryBtnPrimary(String btnId);

	/**
	 * @param btnInfoVo
	 * @return
	 */
	Map<String, Object> qryRoleBtnInfoList(BtnInfoVo btnInfoVo);
}
