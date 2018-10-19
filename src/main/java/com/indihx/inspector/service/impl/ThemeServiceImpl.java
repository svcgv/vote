package com.indihx.inspector.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.activiti.ActivitiBusiness;
import com.indihx.activiti.entity.Application;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.comm.util.StringUtil;
import com.indihx.datamng.dao.SectMapper;
import com.indihx.datamng.entity.Wy_Sect;
import com.indihx.inspector.dao.CheckThemeMapper;
import com.indihx.inspector.dao.QuotaMapper;
import com.indihx.inspector.entity.DC_CKSECT;
import com.indihx.inspector.entity.DC_CKZB;
import com.indihx.inspector.entity.DC_THEME;
import com.indihx.inspector.entity.Dc_Khzb;
import com.indihx.inspector.service.IThemeService;
import com.indihx.inspector.vo.ThemeVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;
@Service
public class ThemeServiceImpl extends ActivitiBusiness implements  IThemeService {

	@Resource
	private  CheckThemeMapper mapper; 
	@Resource
	private QuotaMapper quotaMapper;
	@Resource
	private  SectMapper sectmapper; 
	@Autowired
	private PrimaryKey primaryKey;
	
	@Override
	public Map<String, Object> qryThemeList(ThemeVo vo) {
		DC_THEME theme = new DC_THEME();
		EntityVoConverter.Convert(vo, theme);
		PageHelper.startPage(vo.getPages(), vo.getRows());
		List<DC_THEME> listInfo = mapper.qryThemeList(theme);
		PageInfo<DC_THEME> pageInfo = new PageInfo<DC_THEME>(listInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}
	 
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean saveCheckTheme(UsrInfo usrInfo,ThemeVo vo) {
	  try{
		 //保存检查主题表
		DC_THEME theme = new DC_THEME();
		EntityVoConverter.Convert(vo, theme);
		String theme_id=primaryKey.getPrimaryKey(); //主题ID
		theme.setTheme_id(theme_id);
		theme.setCrt_date(DateUtil.getSysDate());
		theme.setCrt_time(DateUtil.getSysTime());
		theme.setCrt_org(usrInfo.getOrgNo().toString());
		theme.setCrt_user(usrInfo.getUsrId().toString());
		int checktheme=mapper.insertTheme(theme);
		
		//新增检查指标
		if(!ObjectUtil.isEmpty(vo.getQuota_seq())) {
			
			String quotaseqId=vo.getQuota_seq();
			String[] quota_seq = quotaseqId.split("\\|");
			//String quota_seq[]=StringUtil.split(vo.getQuota_seq(), ",") ;
			for (int i = 0; i < quota_seq.length; i++) {
				DC_CKZB ckzb=new DC_CKZB();
				ckzb.setTheme_id(theme_id);
				ckzb.setCheck_seq(Long.valueOf(quota_seq[i]));
				int qutota=mapper.insertCKZB(ckzb);
				if(qutota!=1) {
					return false;
				}
			}
		}
		//指定检查小区表
		if(!ObjectUtil.isEmpty(vo.getSect_id())) {
			String sect_id[]=StringUtil.split(vo.getSect_id(), ",") ;//小区iD
			for (int i = 0; i < sect_id.length; i++) {
				DC_CKSECT cksect=new DC_CKSECT();
				cksect.setTheme_id(theme_id);
				cksect.setSect_id(sect_id[i]);
				int sect=mapper.insertCKSECT(cksect);
				if(sect!=1) {
					return false;
				}
			}
		}
		if(checktheme>0){
			return true; 
		}
		return false;
	  }catch(Exception e){
		 throw new BusinessException("操作失败："+e.getMessage());
	  }
	}

	@Override
	public DC_THEME getCheckTheme(String theme_id) {
		return mapper.getCheckTheme(theme_id);
	}
	
	@Override
	public Map<String,Object> getCheckSect(String theme_id) {
		Map<String,Object> map=new HashMap<>();
		List<DC_CKSECT> listInfo= mapper.getCheckSect(theme_id);
		String sectName="";
		String sectId="";
		for (int i = 0; i < listInfo.size(); i++) {
			if(i==1) {sectName+=",";sectId+=",";}
			DC_CKSECT secta=listInfo.get(i);
			Wy_Sect sect=sectmapper.getSectInfoById(secta.getSect_id());
			sectName+=sect.getXmmc();
			sectId+=sect.getXmid();
		}
		map.put("sectName",sectName);
		map.put("sectId",sectId);
		return map;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateCheckTheme(ThemeVo vo) {
		String theme_id=vo.getTheme_id();
		Assert.hasText(theme_id, "请选择需要变更的信息！");
		try{
			 //保存检查主题表
			DC_THEME theme = new DC_THEME();
			EntityVoConverter.Convert(vo, theme);
			theme.setTheme_id(theme_id);
			theme.setStatus("02");//是否发布-02否 01是
			int checktheme=mapper.updateTheme(theme);
			
			if(!ObjectUtil.isEmpty(vo.getQuota_seq())) {
				String quotaseqId=vo.getQuota_seq();
				String[] quota_seq = quotaseqId.split("\\|");
				//删除检查指标
				mapper.deleteCKZB(theme_id);
				for (int i = 0; i < quota_seq.length; i++) {
					//新增检查指标
					DC_CKZB ckzb=new DC_CKZB();
					ckzb.setTheme_id(theme_id);
					ckzb.setCheck_seq(Long.valueOf(quota_seq[i]));
					int qutota=mapper.insertCKZB(ckzb);
					if(qutota!=1) {
						return false;
					}
				}
			}
			if(!ObjectUtil.isEmpty(vo.getSect_id())) {
				//删除主题制定检查小区
				mapper.deleteCKSECT(theme_id);
				String sect_id[]=StringUtil.split(vo.getSect_id(),",");//小区iD
				for (int i = 0; i < sect_id.length; i++) {
					//新增指定检查小区表
					DC_CKSECT cksect=new DC_CKSECT();
					cksect.setTheme_id(theme_id);
					cksect.setSect_id(sect_id[i]);
					int sect=mapper.insertCKSECT(cksect);
					if(sect!=1) {
						return false;
					}
				}
			}
			if(checktheme>0){
				return true; 
			}
			return false;
		  }catch(Exception e){
			 throw new BusinessException("操作失败："+e.getMessage());
		  }
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delCheckTheme(String theme_id) {
		Assert.hasText(theme_id, "请选择需要变更的信息！");
		try{
			int delcheck=mapper.deleteTheme(theme_id);
			mapper.deleteCKZB(theme_id);
			mapper.deleteCKSECT(theme_id);
			if(delcheck>0){
				return true; 
			}
			return false;
		}catch(Exception e){
			 throw new BusinessException("操作失败："+e.getMessage());
		  }
	}

	@Override
	public void handCopy(Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEnd(Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> handCommitFirst(Application app, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rejectProcess(Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> queryToDoInfo(Application app) {
		// TODO Auto-generated method stub
		return null;
	}

	/***
	 * 查询首页菜单信息
	 * @return 菜单集合
	 */

	public Map<String, Object> qryQuotaJson(String themeId){
		DC_CKZB quota=new DC_CKZB();
		//根据主题查看选中对应的检查指标
		quota.setTheme_id(themeId);
		List<DC_CKZB> quotaList = mapper.getChecQuotaList(quota);
		List<Object> listQuotaId = new ArrayList<Object>();
		for (DC_CKZB khzb :   quotaList) {
			listQuotaId.add(khzb.getCheck_seq());
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		Dc_Khzb qryEntity=new Dc_Khzb();
		//首先查询一级指标
		qryEntity.setZbcj("1");
		qryEntity.setStatus("0");
		List<Dc_Khzb> list = quotaMapper.qryQuotaList(qryEntity);
		List<Map<String, Object>>  listQuota= new ArrayList<Map<String, Object>>();
		for (Dc_Khzb quotaInfo : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", quotaInfo.getZbmc());
			map.put("quota_seq", quotaInfo.getCheck_seq());
			if(listQuotaId.contains(quotaInfo.getCheck_seq())){
				Map<String, Object> status = new HashMap<String, Object>();
				status.put("selected", "true");
				map.put("state", status);
			}
			//查询一级指标下的二级指标
			qryEntity.setZbcj("2");
			qryEntity.setStatus("0");
			qryEntity.setSuper_check_seq(quotaInfo.getCheck_seq());
			List<Dc_Khzb> list_ = quotaMapper.qryQuotaList(qryEntity); //二级指标查出entity集合
			if(list_.size() > 0 ){
				List<Map<String, Object>> _list_2= new ArrayList<Map<String, Object>>(); //二级指标集合
				for (int i = 0; i < list_.size(); i++) {
					Map<String, Object> map2= new HashMap<String, Object>();
					Dc_Khzb childzb = list_.get(i);
					map2.put("text", childzb.getZbmc());
					map2.put("quota_seq", childzb.getCheck_seq());
					if(listQuotaId.contains(childzb.getCheck_seq())){  //当当前指标存在主题集合中，则默认选中
						Map<String, Object> status = new HashMap<String, Object>();
						status.put("selected", "true");
						map2.put("state", status);
					}
					_list_2.add(map2);
				}
				map.put("nodes", _list_2);
			}
			listQuota.add(map);
		}
		resMap.put("mean", listQuota);
		return resMap;
	}

	/**
	 * 发布检查主题
	 * @return themeId 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updatePublishTheme(String theme_id) {
		Assert.hasText(theme_id, "请选择需要变更的信息！");
		try{
			 //保存检查主题表
			DC_THEME theme=new DC_THEME();
			theme.setTheme_id(theme_id);
			theme.setStatus("01");//是否发布-02否  01是
			theme.setIssue_date(DateUtil.getSysDate());//创建时间
			theme.setIssue_time(DateUtil.getSysTime());//创建单位
			int checktheme=mapper.updatePublishTheme(theme);
			if(checktheme>0){
				return true; 
			}
			return false;
		  }catch(Exception e){
			 throw new BusinessException("操作失败："+e.getMessage());
		  }
	}	

}
