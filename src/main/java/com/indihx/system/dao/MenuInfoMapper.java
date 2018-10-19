package com.indihx.system.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.MenuInfo;
import com.indihx.system.entity.MenuQryEntity;
import com.indihx.system.vo.MenuVo;

@MapperScan
public interface MenuInfoMapper {
	//根据主键删除菜单信息
    int deleteByPrimaryKey(Long menuId);
    //新增菜单信息
    int insert(MenuInfo record);
    //根据实体属性值的有无添加菜单信息
    int insertSelective(MenuInfo record);
    //通过主键查询菜单信息
    MenuInfo selectByPrimaryKey(Long menuId);
    //根据实体属性的值进行更新菜单
    int updateByPrimaryKeySelective(MenuInfo record);
    //更新菜单
    int updateByPrimaryKey(MenuInfo record);
    
    List<MenuInfo> qryMenuInfoByUsr(MenuQryEntity menuQry);
    //查询菜单首页信息
/*    List<MenuInfo>  qryMuneInfoAll(MenuInfo record);*/
    List<MenuVo>  qryMuneInfoAll(MenuInfo record);
     //查询新增页面父级菜单信息
    List<MenuInfo>  qryParentId(Long menuId);
    
   /* 查询菜单级别下拉框信息*/
	List<CodeData> qryMenuRage(String menu_list);
	
	List<MenuInfo> qryMenuInfoByRoleUser(MenuQryEntity menuQry);
    
}