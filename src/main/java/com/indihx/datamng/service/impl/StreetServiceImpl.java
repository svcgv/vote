package com.indihx.datamng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.indihx.AbstractBaseService;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.comm.util.DateUtil;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.dao.StreetMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.service.IStreetService;
import com.indihx.datamng.vo.StreetInfoVo;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.system.entity.CodeData;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;
/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: StreetServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 上午10:27:23</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>StreetServiceImpl.java</p>
 * <p></p>
 */
@Service
public class StreetServiceImpl extends AbstractBaseService implements IStreetService {

	private static Logger logger = LoggerFactory.getLogger(StreetServiceImpl.class);
	
	@Resource
	private   HpbMapper hpbMapper;
	
	@Resource
	private   StreetMapper streetMapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	
	@Override
	public Map<String, Object> qryStreetList(StreetInfoVo streetVo) {
		Wy_Jd street = new Wy_Jd();
		EntityVoConverter.Convert(streetVo, street);
		PageHelper.startPage(streetVo.getPages(), streetVo.getRows());
		List<Wy_Jd> listInfo = streetMapper.qryStreetAll(street);
		PageInfo<Wy_Jd> pageInfo = new PageInfo<Wy_Jd>(listInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addStreetInfo(StreetInfoVo streetVo) {
		try{
			Assert.hasLength(streetVo.getJdmc(), "街道名称不能为空!");
			Assert.hasLength(streetVo.getHpbid(), "区ID不能为空!");
			
			String streetId = commMapper.getUserId();
			Wy_Jd street = new Wy_Jd();
			logger.info(streetId);
			street.setJdid(new Long(streetId));
			street.setHpbid(streetVo.getHpbid());
			Wy_Hpb hpb= hpbMapper.getHpbInfo(Long.valueOf(streetVo.getHpbid()));
			street.setHpbmc(hpb.getHpbmc());
			street.setCjrq(DateUtil.formatFromDB(DateUtil.getSysDate()));
			EntityVoConverter.Convert(streetVo, street);//前有值，复制给后
			int num = streetMapper.insertSelective(street);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BusinessException("街道信息修改失败:"+e.getMessage()); 
		}
	}
	@Override
	public Wy_Jd qryStreetById(String streetId) {
		String strId = streetId.replaceAll(",", "").trim();
		Wy_Jd street = streetMapper.selectByPrimaryKey(new Long(strId));
		return street;
	}
	@Override
	public Wy_Hpb selectByStreetIdKey(String streetId) {
		String strId = streetId.replaceAll(",", "").trim();
		Wy_Hpb street = streetMapper.selectByStreetIdKey(new Long(strId));
		return street;
	}
	
	@Override
	public String qryParentId(HpbInfoVo regionVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Wy_Hpb> parentId=streetMapper.selectParentId();
		map.put("parentId", parentId);
		String options="<option value=''>-请选择-</option>\n";
		for(int i=0;i<parentId.size();i++){
			Wy_Hpb vo=parentId.get(i);
			String admid="";
			String selected="";
			if(regionVo!=null&&regionVo.getSjid()!=null&&!regionVo.getSjid().equals("")){
				admid=regionVo.getSjid().replaceAll(",", "").trim();
			    selected = vo.getHpbid().toString().equals(admid)?" selected ":" ";
			}
			String patentId=vo.getHpbid()==null?"":vo.getHpbid().toString();
			options=options + "<option value=\"" + patentId + "\"" +    selected   + ">" + vo.getHpbmc() + "</option>\n";
		}
		return options;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> selectByRegionType(HpbInfoVo adminRegionVo) {
		Wy_Hpb region1 = new Wy_Hpb();
		region1.setHpblx(adminRegionVo.getHpblx());
		region1.setHpbmc(adminRegionVo.getHpbmc());
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(adminRegionVo.getPages(), adminRegionVo.getRows(), true);
		List<Wy_Hpb> listInfo = streetMapper.selectByRegionType(region1);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}
	/**
	 * 查询选择区县
	 * @param aqCommitteeVo
	 * @return
	 */
	public Map<String, Object> qryRegionList(StreetInfoVo streetVo) {
		StreetInfoVo street = new StreetInfoVo();
		EntityVoConverter.Convert(streetVo, street);
		List<Wy_Jd> listInfo = streetMapper.qryRegionList(streetVo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listInfo", listInfo);
		return map;
	}
	/**
	 * 编辑保存
	 * @param InfoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean editStreetInfo(StreetInfoVo infoVo) {
		try {
			Assert.notNull(infoVo.getJdid(),"街道ID不能为空!");
			Long streetId=infoVo.getJdid();
			Wy_Jd info=new Wy_Jd();
			info.setJdid(streetId);
			info.setHpbid(infoVo.getHpbid());
			Wy_Hpb hpb= hpbMapper.getHpbInfo(Long.valueOf(infoVo.getHpbid()));
			info.setHpbmc(hpb.getHpbmc());
			info.setJdmc(infoVo.getJdmc());
			info.setJdbm(infoVo.getJdbm());
			info.setBz(infoVo.getBz());
			int num=streetMapper.updateByPrimaryKeySelective(info);
			if(num<0){
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new BusinessException("街道信息修改失败:"+e.getMessage()); 
		}
	}
	
	/**
	 * 删除
	 * @param InfoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteStreetInfo(StreetInfoVo infoVo) {
		try{
			Assert.notNull(infoVo.getJdid(),"街道ID不能为空!");
			Long streetId=infoVo.getJdid();
			String arr[]=streetId.toString().split(",");
			for(int i=0;i<arr.length;i++){
				if(!StringUtil.isEmpty(arr[i])){
					streetMapper.deleteByPrimaryKey(new Long(arr[i]));
				}
			}
			return true;
		}catch(Exception ex){
			throw new BusinessException("街道信息删除失败："+ex.getMessage());
		}
	}
	@Override
	public Wy_Jd openAqstreetUserList(String streetId) {
			return streetMapper.openAqstreetUserList(streetId);
		}
	
	@Override
	public Map<String, Object> qryAddInfo() {
		// 查询预警主体
		String ewTypeHtml = StreetServiceImpl.createHtmlByCode("ORG_TYPE");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ewTypeHtml", ewTypeHtml);
		return map;
	}
	public static String createHtmlByCode(String codeNo){
		List<CodeData> list = BasicParameterInfo.getCodeByCodeNo(codeNo);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' select>--请选择--</option>");
		for (CodeData codeData : list) {
			if("03".equals(codeData.getCodeKey())){
			buffer.append("<option value='"+codeData.getCodeKey()+"'>"+codeData.getCodeVal()+"</option>");
			}
		}
		return buffer.toString();
	}
	@Override
	public Map<String, Object> qryEditInfo() {
		// 查询预警主体
		String ewTypeHtml = StreetServiceImpl.createHtmlByCode2("ORG_TYPE","03");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ewTypeHtml", ewTypeHtml);
		return map;
	}
	public static String createHtmlByCode2(String codeNo,String codeKey){
		List<CodeData> list = BasicParameterInfo.getCodeByCodeNo(codeNo);
		if(StringUtils.isEmpty(codeKey)){
			return createHtmlByCode(codeNo);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' >--请选择--</option>");
		for (CodeData codeData : list) {
			if("03".equals(codeData.getCodeKey())){
			buffer.append("<option value='"+codeData.getCodeKey()+"' ");
			if(codeData.getCodeKey().equals(codeKey))
			{
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+codeData.getCodeVal()+"</option> ");
			}
		}
		return buffer.toString();
	}

}
