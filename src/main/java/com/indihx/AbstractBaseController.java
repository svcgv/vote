package com.indihx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.indihx.comm.InitSysConstants;
import com.indihx.system.entity.UsrInfo;
/***
 * 
 * <p>描 述: 所有controller</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月24日</p>
 * @author 谢追辉
 */
public abstract class AbstractBaseController {
	
	/***
	 * 得到session中的用户信息
	 * @param http
	 * @return
	 */
	public UsrInfo getUser(HttpSession session){
		UsrInfo usrInfo = (UsrInfo) session.getAttribute(InitSysConstants.USER_SESSION);
		return usrInfo;
	}
	
	/***
	 * 返回jsp页面
	 * @param map 返回页面的数据
	 * @param url 地址
	 * @return MODELANDVIEW
	 */
	public ModelAndView returnModel(Map<String, Object> map,String url){
		ModelAndView view = new ModelAndView();
		if(map!= null && !map.isEmpty()){
			for (String str : map.keySet()) {
				view.addObject(str, map.get(str));
			}
		}
		view.setViewName(url);
		return view;
	}
	
	/***
	 * 返回jsp页面
	 * @param list 返回集合
	 * @param url 地址
	 * @return MODELANDVIEW
	 */
	public ModelAndView returnModel(List<?> list,String url){
		ModelAndView view = new ModelAndView();
		view.addObject("list", list);
		view.setViewName(url);
		return view;
	}
	
	/***
	 * 返回JSP页面 ，页面通过obj取值
	 * @param obj 任意对象
	 * @param url 地址
	 * @return
	 */
	public ModelAndView returnModel(Object obj,String url){
		ModelAndView view = new ModelAndView();
		view.addObject("obj", obj);
		view.setViewName(url);
		return view;
	}
}
