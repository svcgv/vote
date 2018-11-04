package com.indihx.baseTableUtil.controller;


import java.util.Map;
import java.math.BigDecimal;
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
import com.indihx.util.UserUtil;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.baseTableUtil.entity.QueryOrgInfoEntity;
import com.indihx.baseTableUtil.service.QueryOrgInfoService;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-04 18:32:49
 */
@Controller
@RequestMapping("/queryorginfo")
public class QueryOrgInfoController {
    @Autowired
    private QueryOrgInfoService orgInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<QueryOrgInfoEntity> orgInfo = orgInfoService.queryList(params);
        return R.ok().put("page", orgInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("orgNo") long orgNo,HttpSession session){

		QueryOrgInfoEntity entity = orgInfoService.queryObject(new BigDecimal(orgNo));
        return R.ok().put("orgInfo", entity);
    }
    
    @RequestMapping(value="/getOrgTree",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> trees(@RequestBody Map<String, Object> params,HttpSession session){
    	
		Map<String,Object> entity = orgInfoService.getFullChildrenTree(new BigDecimal(1000));
        return R.ok().put("Tree", entity);
    }

    

}
