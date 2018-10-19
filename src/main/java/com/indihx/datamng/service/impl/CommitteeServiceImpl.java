package com.indihx.datamng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.indihx.AbstractBaseService;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.datamng.dao.CommitteeMapper;
import com.indihx.datamng.entity.Wy_Sq;
import com.indihx.datamng.service.ICommitteeService;
import com.indihx.datamng.vo.CommitteeVo;
import com.indihx.util.EntityVoConverter;

@Service
public class CommitteeServiceImpl extends AbstractBaseService  implements ICommitteeService {

	@Resource
	private   CommitteeMapper committeeMapper;
	
	@Resource
	private CommUtilMapper commMapper;
	
	
	@Override
	public Map<String, Object> qryCommitteeList(CommitteeVo vo) {
		Wy_Sq sq = new Wy_Sq();
		EntityVoConverter.Convert(vo, sq);
		PageHelper.startPage(vo.getPages(), vo.getRows());
		List<Wy_Sq> listInfo = committeeMapper.qryAqCommitteeAll(sq);
		PageInfo<Wy_Sq> pageInfo = new PageInfo<Wy_Sq>(listInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		return map;
	}
	
	/**
	 * 查询选择街道
	 * @param aqCommitteeVo
	 * @return
	 */
	public Map<String, Object> qryStreetList(CommitteeVo vo) {
		Wy_Sq aqCommittee = new Wy_Sq();
		EntityVoConverter.Convert(vo, aqCommittee);
		List<Wy_Sq> listInfo = committeeMapper.qryStreetList(aqCommittee);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listInfo", listInfo);
		return map;
	}
	
	 /**
     * 保存
     * @param InfoVo
     * @return
     */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addCommitteeInfo(CommitteeVo infoVo) {
		try {
			String id=commMapper.getPrimaryKey();
			Wy_Sq info=new Wy_Sq();
			info.setSqid(id);
			info.setCjrq(DateUtil.formatFromDB(DateUtil.getSysDate()));
			Wy_Sq temp=committeeMapper.qryStreetInfo(Long.valueOf(infoVo.getJdid()));
			info.setHpbid(temp.getHpbid());
			info.setHpbmc(temp.getHpbmc());
			info.setJdmc(temp.getJdmc());
			EntityVoConverter.Convert(infoVo, info);
			int num=committeeMapper.insertSelective(info);
			if(num>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BusinessException("社区信息修改失败："+e.getMessage());
		}
	}
	
	/**
	 * 编辑页面
	 * @param InfoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Wy_Sq qryCommitteeInfoById(String sqid) {
		
		return committeeMapper.selectByPrimaryKey(new Long(sqid));
	}
	
	/**
	 * 编辑保存
	 * @param InfoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean editCommitteeInfo(CommitteeVo infoVo) {
		try {
			String id=infoVo.getSqid().replaceAll(",", "").trim();
			Wy_Sq info=new Wy_Sq();
			info.setSqid(id);
			EntityVoConverter.Convert(infoVo, info);
			Wy_Sq temp=committeeMapper.qryStreetInfo(Long.valueOf(infoVo.getJdid()));
			info.setHpbid(temp.getHpbid());
			info.setHpbmc(temp.getHpbmc());
			info.setJdmc(temp.getJdmc());
			int num=committeeMapper.updateByPrimaryKeySelective(info);
			if(num<0){
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new BusinessException("社区信息修改失败："+e.getMessage());
		}
	}
	
	/**
	 * 删除
	 * @param InfoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteCommitteeInfo(CommitteeVo infoVo) {
		try {
			String id=infoVo.getSqid();
			String arr[]=id.split(",");
			for(int i=0;i<arr.length;i++){
				if(!StringUtil.isEmpty(arr[i])){
					committeeMapper.deleteByPrimaryKey(new Long(arr[i]));
				}
			}
			return true;
		} catch (Exception e) {
			throw new BusinessException("社区信息修改失败："+e.getMessage());
		}
	}

}
