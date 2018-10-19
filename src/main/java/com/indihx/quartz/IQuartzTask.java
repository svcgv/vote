package com.indihx.quartz;
/***
 * 
 * <p>描 述: 定时器接口</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月3日</p>
 * @author 谢追辉
 */
public interface IQuartzTask {
	/***
	 * 定时器需要做的事情，请实现接口
	 */
	void excute();
}
