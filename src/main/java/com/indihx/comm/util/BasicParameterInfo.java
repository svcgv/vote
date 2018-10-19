package com.indihx.comm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.util.EntityVoConverter;

/***
 * 
 * <p>
 * 描 述: 基本参数信息包括：数据字典、参数表
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年4月12日
 * </p>
 * 
 * @author 谢追辉
 */
public class BasicParameterInfo {

	public static String version = "1.0"; // 版本
	private static Map<String, List<CodeData>> codeMap = new ConcurrentHashMap<String, List<CodeData>>(); // 数据字典信息
	private static Map<String, ParamsInfo> parmMap = new ConcurrentHashMap<String, ParamsInfo>(); // 参数信息
	private static Map<String, List<Wy_Hpb>> regionMap = new ConcurrentHashMap<String, List<Wy_Hpb>>(); // 分区区域信息
	private static String loginPublicKey = "";  //登录的公钥key
	private static String loginPrimaryKey = "";  //登录的私钥key
	
	
	/***
	 * 获取登录公钥key
	 * @return 返回公钥key
	 */
	public static String getLoginPublicKey() {
		return loginPublicKey;
	}
	
	/***
	 * 设置登录公钥key
	 * @param loginPublicKey 公钥
	 */
	public static void setLoginPublicKey(String loginPublicKey) {
		BasicParameterInfo.loginPublicKey = loginPublicKey;
	}
	
	/***
	 * 获取私钥key
	 * @return 私钥
	 */
	public static String getLoginPrimaryKey() {
		return loginPrimaryKey;
	}
	
	/***
	 * 设置私钥key
	 * @param loginPrimaryKey
	 */
	public static void setLoginPrimaryKey(String loginPrimaryKey) {
		BasicParameterInfo.loginPrimaryKey = loginPrimaryKey;
	}
	
	/**
	 * 设置数据字段的值
	 * 
	 * @param map
	 */
	public static void setCodeMap(Map<String, List<CodeData>> map) {
		codeMap.clear(); // 清空，重新加载
		BasicParameterInfo.codeMap = map;
	}

	/***
	 * 从数据字段缓存中取数据
	 * 
	 * @param codeNo
	 * @return
	 */
	public static List<CodeData> getCodeByCodeNo(String codeNo) {
		List<CodeData> list = new ArrayList<CodeData>();
		List<CodeData> codeList = codeMap.get(codeNo);
		for (CodeData codeData : codeList) {
			CodeData cData = new CodeData();
			EntityVoConverter.Convert(codeData, cData);
			list.add(cData);
		}
		return list;
	}
	
	/***
	 * 根据数据项的值得到key，用于数据导入
	 * @param codeNo  字典
	 * @param codeVal  字典项值
	 * @return  字典项key
	 */
	public static String getCodeKeyByCodeVal(String codeNo,String codeVal){
		List<CodeData> listCode = codeMap.get(codeNo);
		for (CodeData codeData : listCode) {
			if(codeVal.equals(codeData.getCodeVal())){
				return codeData.getCodeKey();
			}
		}
		return "";
	}

	/***
	 * 根据字典项和字典值得到value
	 * 当不存在时，则返回null
	 * @param codeNo
	 *            数据字典名称
	 * @param codeKey
	 *            字典项key
	 * @return 字典项values
	 */
	public static String getCodeVal(String codeNo, String codeKey) {
		List<CodeData> listCode = codeMap.get(codeNo);
		for (CodeData codeData : listCode) {
			if (codeData.getCodeKey().equals(codeKey)) {
				return codeData.getCodeVal();
			}
		}
		return "";
	}
	
	/***
	 * 存入参数信息
	 * @param map 参数对象
	 */
	public static void setParmsMap(Map<String, ParamsInfo> map) {
		parmMap.clear(); // 清空，重新加载
		parmMap = map;
	}
	
	/***
	 * 根据参数编号得到参数信息
	 * @param key  参数key
	 * @return  参数信息
	 */
	public static ParamsInfo getParmsMap(String key) {
		return parmMap.get(key);
	}
	
	
	/***
	 * 存入分区信息
	 * @param map 参数对象
	 */
	public static void setRegionMap(Map<String, List<Wy_Hpb>> map) {
		regionMap.clear(); // 清空，重新加载
		regionMap = map;
	}
	
	/***
	 * 根据类型得到各种分区信息
	 * @param key  参数key
	 * @return  参数信息
	 */
	public static List<Wy_Hpb> getRegionMap(String key) {
		return regionMap.get(key);
	}
}
