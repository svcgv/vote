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
import com.indihx.system.dao.CodeDataMapper;
import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.CodeDataKey;
import com.indihx.system.service.ICodeDataService;
import com.indihx.system.vo.CodeDataVo;

/**
 * 
 * <p>
 * 描 述: 字典信息管理
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年3月14日
 * </p>
 * 
 * @author 严蒙蒙
 */
@Service
public class CodeDataServiceImpl extends AbstractBaseService  implements ICodeDataService {
	private static Logger logger = LoggerFactory.getLogger(CodeDataServiceImpl.class);

	@Autowired
	private CodeDataMapper dataMapper;
	
    //首页字典列表信息显示
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryCodeDataList(CodeDataVo dataVo) {
		CodeData record = new CodeData();
		record.setCodeVal(dataVo.getCodeVal());
		record.setCodeKey(dataVo.getCodeKey());
		record.setCodeName(dataVo.getCodeName());
		record.setCodeNo(dataVo.getCodeNo());
		Map<String, Object> map = new HashMap<String, Object>();
		// 第几页、每页显示条数，是否汇总
		PageHelper.startPage(dataVo.getPages(), dataVo.getRows(), true);
		List<CodeData> listInfo = dataMapper.qryCodeDataAll(record);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	//新增数据字典信息
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addCodeData(CodeDataVo dataVo) {
		CodeData record = new CodeData();
		record.setCodeName(dataVo.getCodeName());
		record.setCodeKey(dataVo.getCodeKey());
		record.setCodeNo(dataVo.getCodeNo());
		record.setCodeVal(dataVo.getCodeVal());
		record.setFieldName(dataVo.getFieldName());
		Date now=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		record.setTmSmp(format.format(now));
		int num = dataMapper.insertSelective(record);
		if (num > 0) {
			return true;
		}
		return false;
	}
	
	//通过主键查询字典
	@Override
	public CodeData qryCodeDataById(CodeDataVo dataVo) {
		CodeDataKey key=new CodeDataKey();
		key.setCodeKey(dataVo.getCodeKeyBy().replaceAll(",", "").trim());
		key.setCodeNo(dataVo.getCodeNoBy().replaceAll(",", "").trim());
		return dataMapper.selectByPrimaryKey(key);
	}
	
	//通过主键修改字典信息
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updCodeDataById(CodeDataVo dataVo) {
		try {
			CodeData record = new CodeData();
		
			record.setCodeKey(dataVo.getCodeKeyBy().replaceAll(",", "").trim());
			record.setCodeNo(dataVo.getCodeNoBy().replaceAll(",", "").trim());
			record.setCodeName(dataVo.getCodeName());
			record.setCodeVal(dataVo.getCodeVal());
			record.setFieldName(dataVo.getFieldName());
			Date now=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			record.setTmSmp(format.format(now));
			int cnt = dataMapper.updateByPrimaryKeySelective(record);
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
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteCodeData(CodeDataVo dataVo) {
		int num=0;
		String []codeKeyList = dataVo.getCodeKeyBy().split(",");
		String []codeNoList = dataVo.getCodeNo().split(",");
		for(int i=0;i<codeKeyList.length;i++){
			if (!StringUtils.isEmpty(codeKeyList[i])&&!StringUtils.isEmpty(codeNoList[i])) {
				CodeDataKey key=new CodeDataKey();
				key.setCodeKey(codeKeyList[i]);
				key.setCodeNo(codeNoList[i]);
				num=dataMapper.deleteByPrimaryKey(key);
			}
		}
		
		return (num>0);
	}
}
