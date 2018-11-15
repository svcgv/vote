package com.indihx.baseTableUtil.controller;


import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.BeanUtils;
import com.indihx.util.UserUtil;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.baseTableUtil.entity.QueryUsrInfoEntity;
import com.indihx.baseTableUtil.service.QueryUsrInfoService;
import com.indihx.baseTableUtil.service.impl.QueryOrgInfoServiceImpl;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-04 18:32:49
 */
@Controller
@RequestMapping("/queryusrinfo")
public class QueryUsrInfoController {
    @Autowired
    private QueryUsrInfoService usrInfoService;
    @Autowired
    private QueryOrgInfoServiceImpl queryOrgInfoServiceImpl;
    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<QueryUsrInfoEntity> usrInfo = usrInfoService.queryList(params);
        return R.ok().put("page", usrInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("usrId") long usrId,HttpSession session){

		QueryUsrInfoEntity entity = usrInfoService.queryObject(usrId);
        return R.ok().put("usrInfo", entity);
    }

    /**
     * 通过roleCode和orgNo查询对应的用户
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * param{orgNo:,roleCode:''}
     */
    @RequestMapping(value="/queryUserByRoleCodeAndOrgNo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> queryUserByRoleCodeAndOrgNo(@RequestBody Map<String, Object> param,HttpSession session) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub
    	List<QueryUsrInfoEntity> res = usrInfoService.queryUserByRoleCodeAndOrgNo(param);
    	
    	/*List<QueryUsrInfoEntity> list = new ArrayList<QueryUsrInfoEntity>();
    	if(res.isEmpty()) {
    		return R.error();
    	}
    	for(int i =0;i<res.size();i++) {
    		Map<String,Object> map = res.get(i);
    		list.add(BeanUtils.Map2Bean(map, QueryUsrInfoEntity.class));
    	}
    	*/
		return R.ok().put("page", res);
	}
    
    /**
     * 查机构下的所有用户
     * @param param
     * @param session
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    @RequestMapping(value="/queryUserByRoleCodeUnderOrgNo",method=RequestMethod.POST)
 	public @ResponseBody Map<String,Object> queryUserByRoleCodeUnderOrgNo(@RequestBody Map<String, Object> param,HttpSession session) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
 		BigDecimal bd = new BigDecimal( Integer.parseInt(param.get("orgNo").toString()));
    	if(bd==null) {
    		return R.error("请选择机构");
    	}
    	List<Map<String,Object>> children = queryOrgInfoServiceImpl.queryAllChildrenOrgList(bd);
    	List<BigDecimal> list = new ArrayList<BigDecimal>();
    	list.add(bd);
    	if(children!=null && !children.isEmpty()) {
    		for(int i = 0;i<children.size();i++) {
    			Map<String,Object> map = children.get(i);
    			list.add(new BigDecimal( Integer.parseInt(map.get("orgId").toString())));
    		}
    	}
     	List<QueryUsrInfoEntity> res = usrInfoService.queryUserByRoleCodeUnderOrgNo(list);
     	
     	
 		return R.ok().put("page", res);
 	}
    
    
}
