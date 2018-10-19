package com.indihx.comm.service;

/***
 * 
 * <p>描 述: 类描述</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月12日</p>
 * @author 谢追辉
 */
public interface IInitSysBasicService {
	
	/***
	 * 查询数据字典
	 * @return 集合
	 */
	void initCodeDataAll();
	
	/***
	 * 初始化参数管理
	 */
	void initParamsInfo();

	/***
	 * 初始化分区区域
	 */
	void initRegion();
}
