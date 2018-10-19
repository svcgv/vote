package com.indihx.comm.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indihx.AbstractBaseController;
import com.indihx.comm.util.BasicParameterInfo;
/***
 * 
 * <p>描 述: 异步请求数据字典，时间转换和翻译</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年6月2日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping("/basic")
public class BasicParameteController extends AbstractBaseController {
	
	/***
	 * 查询数据字典项的至
	 * @param codeNo
	 * @param codeKey
	 * @return
	 */
	@RequestMapping(value="/getCodeVal",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCodeVal(@RequestBody Map<String, Object> map){
		String codeNo = map.get("codeNo").toString();
		String codeKey = map.get("codeKey").toString();
		String keyVal = BasicParameterInfo.getCodeVal(codeNo,codeKey);
		map.put("keyVal", keyVal);
		return map;	
	}
	
	/***
	 * 获取加密KEY
	 * @return key
	 */
	@RequestMapping(value="/getLoginPublicKey.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getLoginPublicKey(@RequestBody Map<String, Object> map){
		map.put("publicKey", BasicParameterInfo.getLoginPublicKey());
		return map;
	}
	
}
