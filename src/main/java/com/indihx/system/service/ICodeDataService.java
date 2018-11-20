package com.indihx.system.service;

import java.util.List;
import java.util.Map;

import com.indihx.system.entity.CodeData;
import com.indihx.system.vo.CodeDataVo;

/***
 * 
 * <p>描 述: 数据字典管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月14日</p>
 * @author 严蒙蒙
 */
public interface ICodeDataService  {
	
	/**
	 * 字典信息首页查询（查询条件、分页信息）
	 * 
	 * @param pagesInfo
	 * @return
	 */
	public  Map<String, Object> qryCodeDataList(CodeDataVo dataVo);
	 
	 /***
		 *  字典信息新增
		 * 
		 * @param  dataVo
		 *            字典实体
		 * @return true增加成功 否则失败
		 */
	
	public boolean addCodeData(CodeDataVo dataVo);
	 
	 /***
		 * 根据id查询字典信息
		 *
		 * @param  dataVo
		 * @return
		 */
	public CodeData qryCodeDataById(CodeDataVo dataVo);

	/**
	 * 根据NO查出数据字典字段
	 * @return
	 */
	public List<CodeData> getByCodeNo(String codeNo	);

	 /***
		 * 修改字典信息
		 *
		 * @param  dataVo
		 * @return
		 */
	 boolean updCodeDataById(CodeDataVo dataVo);

	 /***
		 * 删除字典信息
		 * 
		 * @param  dataVo
		 * @return
		 */
	 public boolean deleteCodeData(CodeDataVo dataVo);
}
