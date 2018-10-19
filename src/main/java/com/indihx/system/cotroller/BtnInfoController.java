package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.system.entity.BtnInfo;
import com.indihx.system.service.impl.BtnInfoServiceImpl;
import com.indihx.system.vo.BtnInfoVo;

/***
 * 
 * <p>描 述: 按钮信息管理controller </p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年7月25日</p>
 * @author 严蒙蒙
 */
@Controller
@RequestMapping("/btn")  
public class BtnInfoController {
	
	//日志
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(BtnInfoController.class); 
	
	@Autowired
	private BtnInfoServiceImpl btnInfoService;
	
	/***
	 * 查询字典信息，返回首页面列表显示
	 * @return 返回页面模版
	 */
	@RequestMapping(value="/qryBtnInfo.do",method=RequestMethod.GET)  
	public ModelAndView qryCodeData(){
		Map<String, Object> map= btnInfoService.qryBtnInfoList(new BtnInfoVo());
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.setViewName("/btn/qryBtnList");
		return view;
	}
	
	/*分页、筛选条件的查询*/
	@RequestMapping(value="/ajaxQryBtnInfo.do",method=RequestMethod.POST)  
	@ResponseBody
	public  Map<String, Object> ajaxQryCodeData(@RequestBody BtnInfoVo dataVo){
		Map<String, Object> map = btnInfoService.qryBtnInfoList(dataVo);
		return map;
	}
	
	@RequestMapping(value="/qryPrimary.do",method=RequestMethod.POST)  
	public @ResponseBody Map<String, Object> qryPrimary(@RequestBody BtnInfoVo dataVo ){
		Map<String, Object> map = new HashMap<String, Object>();
		String primary = btnInfoService.qryBtnPrimary(dataVo.getBtnId());
		map.put("primary", primary);
		return map;
	}
	
	
	/***
	 * 字典信息保存
	 * @param dataVo
	 * @return 返回成功或失败页
	 */
	@RequestMapping(value="/addSave.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addCodeData(@RequestBody BtnInfoVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = btnInfoService.addBtnInfo(dataVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/***
	 * 根据字典ID查询信息
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/qryBtnInfoById.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryCodeDataById(@RequestBody BtnInfoVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeData", btnInfoService.qryBtnInfoById(dataVo));
		return map;
	}
	
	
	/**
	 * 打开修改页面，根据联合主键查询当前数据字典信息
	 * @param codeNo ,codeKeyBy 联合主键
	 * @return
	 */
	@RequestMapping(value="/openUpdateBtn.do",method=RequestMethod.GET)
	public ModelAndView openUpdateCode(@RequestParam("btnId") String btnId){
		BtnInfoVo dataVo=new BtnInfoVo();
		dataVo.setBtnId(btnId);
		BtnInfo btn = btnInfoService.qryBtnInfoById(dataVo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/btn/editBtnInfo");
		view.addObject("btn", btn);
		return view;
	}
	
	/***
	 * 修改字典信息的保存
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/editSave.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> editSave(@RequestBody BtnInfoVo dataVo){
		Map<String, Object> respMap = new HashMap<String, Object>();
		boolean isSucess = btnInfoService.updBtnInfoById(dataVo);
		respMap.put("success", isSucess);
		return respMap;
	}
	
	/***
	 * 删除数据字典信息
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/delBtnInfo.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> delCodeData(@RequestBody BtnInfoVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = btnInfoService.deleteBtnInfo(dataVo);
		map.put("success", isSucess);
		return map;
	}
	
}
