package com.indihx.util;

import java.util.List;

import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.system.entity.CodeData;

/**
 * 
 * <p>描 述: 初始化常量信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月20日</p>
 * @author 谢追辉
 */
public class ConstantStatic {
	public static int ROWS = 10; //每页显示10条
	public static int PAGES = 1; //当前页数
	/**默认流程任务的领取人**/
	public static String CALIMTASK="SYSTEM";
	
	/**代办任务URL的key值*/
	public static String URL="URL"; 
	
	public static Long strToLong(String str){
		if(StringUtils.isEmpty(str))
			return null;
		else return new Long(str);
	}
	
	/***
	 * 根据数据字段创建option代码
	 * @param String  字典项名称
	 * @return   返回一串string的
	 */
	public static String createHtmlByCode(String codeNo){
		List<CodeData> list = BasicParameterInfo.getCodeByCodeNo(codeNo);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' select>--请选择--</option>");
		for (CodeData codeData : list) {
			buffer.append("<option value='"+codeData.getCodeKey()+"'>"+codeData.getCodeVal()+"</option>");
		}
		return buffer.toString();
	}
	
	/***
	 * 根据数据字段创建option代码 ,设置默认选中项
	 * @param String  字典项名称
	 * @param codeKey 选中项的值
	 * @return  返回一串string的
	 */
	public static String createHtmlByCode(String codeNo,String codeKey){
		List<CodeData> list = BasicParameterInfo.getCodeByCodeNo(codeNo);
		if(StringUtils.isEmpty(codeKey)){
			return createHtmlByCode(codeNo);
		}
		StringBuffer buffer = new StringBuffer();
		for (CodeData codeData : list) {
			buffer.append("<option value='"+codeData.getCodeKey()+"' ");
			if(!StringUtils.isEmpty(codeKey)&&codeData.getCodeKey().equals(codeKey))
			{
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+codeData.getCodeVal()+"</option> ");
		}
		return buffer.toString();
	}
	
	
	/***
	 * 创建区的县的下拉框
	 * @param String  字典项名称
	 * @param codeKey 选中项的值
	 * @return  返回一串string的
	 */
	public static String createRegionHtml(List<Wy_Hpb> list,String key){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' >--请选择--</option>");
		for (Wy_Hpb hpb : list) {
			buffer.append("<option value='"+hpb.getHpbid()+"' ");
			if(!StringUtils.isEmpty(key)&&hpb.getHpbid().equals(key))
			{
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+hpb.getHpbmc()+"</option> ");
		}
		return buffer.toString();
	}
	
	/***
	 * 创建单选框组
	 * @param codeNo 字典
	 * @param codeKey key值
	 * @param name  radio的name名称
	 * @return
	 */
	public static String createRadioHtml(String codeNo,String codeKey,String name){
		List<CodeData> list = BasicParameterInfo.getCodeByCodeNo(codeNo);
		StringBuffer radioBuffer = new StringBuffer();
		for (CodeData codeData : list) {
			radioBuffer.append("<label class='radio-inline'> <input type='radio' ");
			radioBuffer.append("name='");
			radioBuffer.append(name);
			radioBuffer.append("' value='");
			radioBuffer.append(codeData.getCodeKey());
			radioBuffer.append("' ");
			if (!StringUtils.isEmpty(codeKey) && codeData.getCodeKey().equals(codeKey)) { //当默认值为空
				radioBuffer.append(" checked ");
			}
			radioBuffer.append(">");
			radioBuffer.append(codeData.getCodeVal());
			radioBuffer.append("</label>");
		}
		return radioBuffer.toString();
	}
	
}
