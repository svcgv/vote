package com.indihx.PmContractInfo.service.impl;

import com.indihx.PmContractProjectRelation.dao.PmContractProjectRelationMapper;
import com.indihx.PmContractProjectRelation.entity.PmContractProjectRelationEntity;
import com.indihx.PmPaymentPoint.dao.PmPaymentPointMapper;
import com.indihx.PmPaymentPoint.entity.PmPaymentPointEntity;
import com.indihx.PmProjectInfo.dao.PmProjectInfoMapper;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmContractInfo.dao.PmContractInfoMapper;
import com.indihx.PmContractInfo.entity.PmContractInfoEntity;
import com.indihx.PmContractInfo.service.PmContractInfoService;


@Service("pmContractInfoService")
public class PmContractInfoServiceImpl implements PmContractInfoService {
	@Resource
   	PmContractInfoMapper pmContractInfoMapper;
	@Resource
	PmPaymentPointMapper pmPaymentPointMapper;
	@Resource
	PmContractProjectRelationMapper pmContractProjectRelationMapper;
	@Resource
	PmProjectInfoMapper pmProjectInfoMapper;
   
   	public PmContractInfoEntity queryObject(long id){
   		return pmContractInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmContractInfoEntity entity){
   		pmContractInfoMapper.insert(entity);
		for(Long projectId:entity.getProjectIds()) {
			PmContractProjectRelationEntity relationEntity = new PmContractProjectRelationEntity();
			PmProjectInfoEntity projectEntity = pmProjectInfoMapper.queryObject(projectId);
			relationEntity.setWbs(projectEntity.getWbs());
			relationEntity.setContractId(entity.getContractId());
			relationEntity.setProjectId(projectId);
			pmContractProjectRelationMapper.insert(relationEntity);
		}
   		List<PmPaymentPointEntity> paymentPointEntityList= entity.getPaymentPoint();
   		if(paymentPointEntityList !=null) {
   	   		for (PmPaymentPointEntity paymentPointEntity :paymentPointEntityList){
   				paymentPointEntity.setPaymentForeignId(entity.getContractId());
   				paymentPointEntity.setPaymentType("00");
//   				String payId = paymentPointEntity.getPaymentId()+"_"+ RandomUtil.generateString(4);
   				pmPaymentPointMapper.insert(paymentPointEntity);
   			}
   		}

   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmContractInfoEntity entity){
		pmContractInfoMapper.update(entity);
		pmPaymentPointMapper.deleteByForeignId(entity.getContractId());
		List<PmPaymentPointEntity> paymentPointEntityList= entity.getPaymentPoint();
		if(paymentPointEntityList !=null) {
			for (PmPaymentPointEntity paymentPointEntity :paymentPointEntityList){
				paymentPointEntity.setPaymentForeignId(entity.getContractId());
				paymentPointEntity.setPaymentType("00");
				pmPaymentPointMapper.insert(paymentPointEntity);
			}
		}
   	}

	public void delete(long contractId){
   		pmContractInfoMapper.delete( contractId);
   	}

	public int queryTotal(){
   		return pmContractInfoMapper.queryTotal();
   	}

   	public List<PmContractInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmContractInfoMapper.queryList(entity);
   	}
}
