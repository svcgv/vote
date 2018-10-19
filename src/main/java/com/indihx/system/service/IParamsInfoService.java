package com.indihx.system.service;

import java.util.Map;

import com.indihx.system.entity.ParamsInfo;
import com.indihx.system.vo.ParamsInfoVo;

/***
 * 
 * <p>描 述:参数信息管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月7日</p>
 * @author zss
 */
public interface IParamsInfoService {
	/**
	 * 参数信息分页查询
	 * @param infovo
	 * @return
	 */
 public Map<String, Object> qryParamsInfo(ParamsInfoVo infovo);
 /**
  * 参数信息新增
  * @param infovo
  * @return
  */
 public boolean addParamsInfo(ParamsInfoVo infovo);
 /**
  * 参数信息
  * @param infovo
  * @return
  */
 public  ParamsInfo qryParamsInfoList(String paramsNo);
 /**
  * 编辑参数信息
  * @param infovo
  * @return
  */
 public boolean updParamsInfo(ParamsInfoVo infovo);
 /**
  * 删除参数信息
  * @param infovo
  * @return
  */
 public boolean deleParamsInfo(ParamsInfoVo infovo);
 
Map<String, Object> qryAddInfo();
ParamsInfo qryParamsInfoList1(String paramsNo);

 
}
