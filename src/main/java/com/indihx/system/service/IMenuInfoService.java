package com.indihx.system.service;
import java.util.Map;

import com.indihx.system.entity.MenuInfo;
import com.indihx.system.vo.MenuVo;
public interface IMenuInfoService {
 
	public Map<String, Object> qryMenuInfo(MenuVo infovo);
	 /**
	  * 菜单信息新增
	  * @param infovo
	  * @return
	  */
	 public boolean addMenuInfo(MenuVo infovo);
	 /**
	  * 菜单信息查询
	  * @param infovo
	  * @return
	  */
	 public  MenuInfo qryMenuInfoById(MenuVo infovo);
	 /**
	  * 编辑菜单信息
	  * @param infovo
	  * @return
	  */
	 public boolean updMenuInfo(MenuVo infovo);
	 /**
	  * 删除菜单信息
	  * @param infovo
	  * @return
	  */
	 public boolean deleMenuInfo(MenuVo infovo);
	 
	 
	 /**
	  *新增页面父菜单显示
	  * 
	  * @return
	  */
	public String qryParentId(MenuVo infovo);
	 
	 /**
	  *新增页面菜单下拉框的信息显示
	  * 
	  * @return
	  */
	public String qryMenuRage(MenuVo infovo);
	 /**
	  *首页下拉框的信息显示
	  * 
	  * @return
	  */
	
	public Map<String, Object> qryLevelRage();
}
