package com.indihx.system.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.system.dao.MenuInfoMapper;
import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.MenuInfo;
import com.indihx.system.service.IMenuInfoService;
import com.indihx.system.vo.MenuVo;
import com.indihx.util.ConstantStatic;
@Service
public class MenuInfoServiceImpl  extends AbstractBaseService implements IMenuInfoService{
	private static Logger logger = LoggerFactory.getLogger(MenuInfoServiceImpl.class);
	@Autowired
    private  MenuInfoMapper infomapper;
    @Autowired
	private CommUtilMapper mapper;
    
    //查询菜单首页列表、分页信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryMenuInfo(MenuVo infovo) {
		MenuInfo mv=new MenuInfo();
		mv.setMenuLevel(infovo.getMenuLevel());
		mv.setMenuName(infovo.getMenuName());
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(infovo.getPages(), infovo.getRows(), true);
		 List<MenuVo> listInfo=infomapper.qryMuneInfoAll(mv);
		PageInfo pageInfo = new PageInfo(listInfo);
	    map.put("listInfo", listInfo);
	    map.put("pageInfo", pageInfo);
		return map;
	}
	
	public Map<String, Object> qryLevelRage() {
		// 查询菜单级别
		String levelHtml = ConstantStatic.createHtmlByCode("MENU_LIST");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("levelHtml", levelHtml);
	
		return map;
	}
	
	
   //新增菜单信息的保存
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addMenuInfo(MenuVo infovo) {
		MenuInfo menu = new MenuInfo();
		menu.setMenuLevel(infovo.getMenuLevel());
		menu.setMenuName(infovo.getMenuName());
		menu.setMenuUrl(infovo.getMenuUrl());
		menu.setCssIcon(ObjectUtil.isEmpty(infovo.getCssIcon())?"icon-glass":infovo.getCssIcon());
		menu.setSortNum(infovo.getSortNum());
		menu.setOperUser(Long.valueOf( infovo.getOperUser())  );
		String parentid=infovo.getParentId();
		if(infovo.getParentId()==null||"".equals(infovo.getParentId())){
			parentid="0";
		}
		menu.setParentId(parentid);
		Date now=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String timedate=format.format(now).replaceAll("-", "").trim();
		menu.setTmSmp(new Long(timedate));
		menu.setMenuId(new Long(mapper.getMenuId()));
		if(ObjectUtil.isEmpty(infovo.getMenuName())){
			throw new BusinessException("功能菜单名称不能为空!");
		}
		int num = infomapper.insertSelective(menu);
		if (num > 0) {
			return true;
		}
		return false;
	}
	

	//根据菜单id查询菜单信息
	@Override
	public MenuInfo qryMenuInfoById(MenuVo infovo) {
		Long menuid=Long.valueOf(infovo.getMenuId().replaceAll(",", "").trim());
		return infomapper.selectByPrimaryKey(menuid);
	}
	
	//新增菜单页面父级菜单的显示
	@Override
	public String qryParentId(MenuVo infovo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MenuInfo> parentId=infomapper.qryParentId(new Long("0"));
		map.put("parentId", parentId);
		String options="<option value=''>-请选择-</option>\n";
		for(int i=0;i<parentId.size();i++){
			MenuInfo vo=parentId.get(i);
			String parentid="";
			if(infovo!=null&&infovo.getParentIdBy()!=null&&!infovo.getParentIdBy().equals("")){
				parentid=infovo.getParentIdBy().replaceAll(",", "").trim();
			}
			String selected = vo.getMenuId().toString().equals(parentid)?" selected ":" ";
			options=options + "<option value=\"" + vo.getMenuId() + "\"" +    selected   + ">" + vo.getMenuName() + "</option>\n";
		}
		return options;
	}
	
	
	//查询菜单级别的代码项信息
	@Override
	public String qryMenuRage (MenuVo infovo){
		String menu_list="MENU_LIST";
		List<CodeData> menuRage=infomapper.qryMenuRage(menu_list);
		String options="<option value=''>-请选择-</option>\n";
		for(int i=0;i<menuRage.size();i++){
			CodeData vo=menuRage.get(i);
			String codekey=infovo.getMenuLevel();
			String selected = vo.getCodeKey().toString().equals(codekey)?" selected ":" ";
			options=options + "<option value=\"" + vo.getCodeKey() + "\"" +    selected   + ">" + vo.getCodeVal() + "</option>\n";
		}
		return options;
	}
	
	
	//编辑菜单信息保存
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updMenuInfo(MenuVo infovo) {
		try {
			MenuInfo menu = new MenuInfo();
			menu.setMenuLevel(infovo.getMenuLevel());
			menu.setMenuName(infovo.getMenuName());
			menu.setMenuUrl(infovo.getMenuUrl());
			menu.setOperUser(Long.valueOf( infovo.getOperUser())  );
			menu.setParentId(infovo.getParentId());
			menu.setCssIcon(infovo.getCssIcon());
			menu.setSortNum(infovo.getSortNum());
			Date now=new Date();
			DateFormat format=new SimpleDateFormat("yyyyMMdd");
			menu.setTmSmp(format.format(now));
			String id=infovo.getMenuId().replaceAll(",", "").trim();
			menu.setMenuId(new Long(id));
			int cnt = infomapper.updateByPrimaryKeySelective(menu);
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
	
	//删除菜单信息
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleMenuInfo(MenuVo infovo) {
		int num=0;
		String menuIdList = infovo.getMenuId();
		String[] str = menuIdList.split(",");
		for (int i = 0; i < str.length; i++) {
			if (!StringUtils.isEmpty(str[i])) {
			num=infomapper.deleteByPrimaryKey(new Long(str[i]));
			}
		}
		return num>0;
	}

}
