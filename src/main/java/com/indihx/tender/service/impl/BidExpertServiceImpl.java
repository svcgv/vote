package com.indihx.tender.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;


import com.indihx.util.ImportExcelUtil;
import com.indihx.system.entity.UsrInfo;
import com.indihx.tender.dao.BidExpertMapper;
import com.indihx.tender.entity.ZTB_EXPERT;
import com.indihx.tender.service.IBidExpertService;
import com.indihx.tender.vo.BidExpertVo;



/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspSectServiceImp.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日 下午6:52:35</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspSectServiceImp.java</p>
 * <p></p>
 */

@Service
public class BidExpertServiceImpl  implements IBidExpertService {
	@Resource
	private BidExpertMapper bidExpertMapper;
	
	@Resource
	private HpbMapper hpbMapper;
	
	@Autowired
	private PrimaryKey primaryKey;
	
	/**
	 * 首页
	 * @see com.indihx.tender.service.IBidExpertService#getExList(com.indihx.tender.vo.BidExpertVo)
	 */
	@Override
	public Map<String, Object> getExList(UsrInfo user,BidExpertVo bidExpertVo) {
		//获取页面输入参数，并将其转为实体对象
		ZTB_EXPERT expert = new ZTB_EXPERT();
		EntityVoConverter.Convert(bidExpertVo, expert);
		//设置分页
		PageHelper.startPage(bidExpertVo.getPages(), bidExpertVo.getRows());
		//调用Mapper根据参数获取区县列表信息
		List<ZTB_EXPERT> listInfo = bidExpertMapper.getExList(expert);
		PageInfo<ZTB_EXPERT> pageInfo = new PageInfo<ZTB_EXPERT>(listInfo);
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}
	
	/**
	 * 新增保存
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addExInfo(UsrInfo usrInfo,BidExpertVo bidExpertVo) throws BusinessException {
		try{
			Assert.hasText(bidExpertVo.getExpert_name(), "专家姓名不能为空!");
			Assert.hasText(bidExpertVo.getExpert_six(), "专家性别不能为空!");
			Assert.hasText(bidExpertVo.getCert_type(), "证件类型不能为空!");
			Assert.hasText(bidExpertVo.getCert_code(), "证件号码不能为空!");
			Assert.hasText(bidExpertVo.getCompany_name(), "公司名称不能为空!");
			Assert.hasText(bidExpertVo.getCompany_addr(), "公司地址不能为空!");
			Assert.hasText(bidExpertVo.getContact_tel(), "联系电话不能为空!");
			Assert.hasText(bidExpertVo.getContact_addr(), "联系地址不能为空!");
			Assert.hasText(bidExpertVo.getHpbid(), "所在区域id不能为空!");
			
			String id = primaryKey.getPrimaryKey();
			bidExpertVo.setExpert_id(id);
			Wy_Hpb hpb=hpbMapper.getHpbInfo(Long.valueOf(bidExpertVo.getHpbid()));
			bidExpertVo.setHpbmc(hpb.getHpbmc());
			bidExpertVo.setCreate_date(DateUtil.formatFromDB(DateUtil.getSysDate()));
			bidExpertVo.setCreate_time(DateUtil.formatTimeFromDB(DateUtil.getSysTime()));
			ZTB_EXPERT expert = new ZTB_EXPERT();
			EntityVoConverter.Convert(bidExpertVo, expert);
			//验证人员是否存在
			List<ZTB_EXPERT> listInfo = bidExpertMapper.validExIsExists(expert);
			if(!listInfo.isEmpty()){
				throw new BusinessException("评标专家:"+expert.getExpert_name()+" 已经存在，不能重复录入!");
			}
			int num=bidExpertMapper.insertEx(expert);
			
			if(num>0){
				return true;
			}
			return false;
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}
	
	/**
	 * 编辑保存
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean editExInfo(BidExpertVo bidExpertVo) throws BusinessException {
		try{
			Assert.hasText(bidExpertVo.getExpert_id(), "未查询到该专家信息!");
			Assert.hasText(bidExpertVo.getExpert_name(), "专家姓名不能为空!");
			Assert.hasText(bidExpertVo.getExpert_six(), "专家性别不能为空!");
			Assert.hasText(bidExpertVo.getCert_type(), "证件类型不能为空!");
			Assert.hasText(bidExpertVo.getCert_code(), "证件号码不能为空!");
			Assert.hasText(bidExpertVo.getCompany_name(), "公司名称不能为空!");
			Assert.hasText(bidExpertVo.getCompany_addr(), "公司地址不能为空!");
			Assert.hasText(bidExpertVo.getContact_tel(), "联系电话不能为空!");
			Assert.hasText(bidExpertVo.getContact_addr(), "联系地址不能为空!");
			Assert.hasText(bidExpertVo.getHpbid(), "所在区域id不能为空!");
			
			String id = bidExpertVo.getExpert_id();
			bidExpertVo.setExpert_id(id);
			Wy_Hpb hpb=hpbMapper.getHpbInfo(Long.valueOf(bidExpertVo.getHpbid()));
			bidExpertVo.setHpbmc(hpb.getHpbmc());
			ZTB_EXPERT expert = new ZTB_EXPERT();
			EntityVoConverter.Convert(bidExpertVo, expert);
			
			//验证人员是否存在
			List<ZTB_EXPERT> listInfo = bidExpertMapper.validExIsExists(expert);
			if(!listInfo.isEmpty()){
				throw new BusinessException("评标专家:"+expert.getExpert_name()+" 已经存在，不能重复录入!");
			}
			int num=bidExpertMapper.updateEx(expert);
			
			if(num>0){
				return true;
			}
			return false;
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}
	
	/**
	 * 删除
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delExInfo(String[] expert_id_list) {
		for(int i=0;i<expert_id_list.length;i++){
			if(!StringUtil.isEmpty(expert_id_list[i])){
				bidExpertMapper.deleteEx(new Long(expert_id_list[i]));
			}
		}
		return true;
	}
	
	/**
	 * 查看详情
	 */
	@Override
	public ZTB_EXPERT getExInfo(String expert_id) {
		return bidExpertMapper.getExInfo(new Long(expert_id));
	}

	/* (加载导入的数据至页面预览)
	 * @see com.indihx.tender.service.IBidExpertService#loadDataInfo(com.indihx.system.entity.UsrInfo, org.springframework.web.multipart.MultipartFile[], java.lang.String)
	 */
	@Override
	public Map<String, Object> loadDataInfo(UsrInfo usrInfo,
			MultipartFile[] myfiles, String fileTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Assert.notNull(myfiles, "加载数据异常，数据文件为空！");
			List<ZTB_EXPERT> listInfo =loadExcel(myfiles[0]);
			map.put("listInfo", listInfo);
			return map;
		}catch(Exception e){
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/**
	 * 解析Excel数据
	 * @param file 
	 * @param path
	 * @throws Exception 
	 */
	private List<ZTB_EXPERT> loadExcel(MultipartFile file) throws Exception {
		 List<ZTB_EXPERT> list = new ArrayList<ZTB_EXPERT>();
		 InputStream in = null;  
		 List<List<Object>> listob = null;
		 List<Object> obj = null;
		 in = file.getInputStream();  
		 //调用工具类，加载Excel数据，自动过滤第一行Title
		 listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());  
		 ZTB_EXPERT expert = null;
		 for (int i = 0; i < listob.size(); i++) {
			 //获取一行数据
			 obj = listob.get(i);
			 //构造数据集
			 expert = new ZTB_EXPERT();
			 expert.setExpert_name(obj.get(0).toString());
			 expert.setExpert_six(obj.get(1).toString());
			 expert.setHpbmc(obj.get(2).toString());
			 expert.setCompany_name(obj.get(3).toString());
			 expert.setCert_code(obj.get(4).toString());
			 expert.setExpert_type(obj.get(5).toString());
			 expert.setContact_tel(obj.get(6).toString());
			 
			 //TODO保存数据
			 list.add(expert);
		}
		return list;
	}

}
