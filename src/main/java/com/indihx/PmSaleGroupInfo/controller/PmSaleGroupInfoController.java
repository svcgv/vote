package com.indihx.PmSaleGroupInfo.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
import com.indihx.PmSaleGroupInfo.entity.PmSaleMemberInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleGroupInfoService;
import com.indihx.PmSaleGroupInfo.service.PmSaleMemberInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.RandomUtil;
import com.indihx.system.entity.UsrInfo;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @date 2018-11-02 20:19:45
 */
@Controller
@RequestMapping("/pmsalegroupinfo")
public class PmSaleGroupInfoController {
    @Autowired
    private PmSaleGroupInfoService pmSaleGroupInfoService;
    @Autowired
    private PmSaleMemberInfoService pmSaleMemberInfoService;

    
    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){
    	
    	Map<String, Object> param = (Map<String, Object>)JSON.parse((String) params.get("queryStr"));
    	params.putAll(param);
		List<PmSaleGroupInfoEntity> pmSaleGroupInfo = pmSaleGroupInfoService.queryList(params);
        return R.ok().put("page", pmSaleGroupInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("groupId") long groupId,HttpSession session){
		PmSaleGroupInfoEntity entity = pmSaleGroupInfoService.queryObject(groupId);
        return R.ok().put("pmSaleGroupInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody Map<String,Object> map ,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	PmSaleGroupInfoEntity pmSaleGroupInfo = new PmSaleGroupInfoEntity();
    	pmSaleGroupInfo.setGroupName((String)map.get("groupName"));
    	pmSaleGroupInfo.setCreatorId(user.getUsrId());
    	pmSaleGroupInfo.setCreateTime(DateUtil.getCurrentTimeMill());
    	String code = "XS"+DateUtil.getSysDate()+RandomUtil.generateString(4);
    	pmSaleGroupInfo.setGroupCode(code);
        pmSaleGroupInfoService.insert(pmSaleGroupInfo);
        //获取团队成员管理
        String monitorId = (String) map.get("monitorId");
        if(monitorId!=null) {
        	if(!"".equals(monitorId)) {
        		long id = Long.parseLong(monitorId);
        		PmSaleMemberInfoEntity entity = new PmSaleMemberInfoEntity();
            	entity.setCreatorId(user.getUsrId());
            	entity.setCreateTime(DateUtil.getCurrentTimeMill());
            	entity.setGroupCode(code);
            	entity.setIsDelete("00");
            	entity.setMemberType("01");
            	entity.setMenberUsrId(id);
            	pmSaleMemberInfoService.insert(entity);
            	
        	}
        }
        //获取团队成员列表，若列表不为空则遍历插入
        List<Long> userIds = (List<Long>) map.get("userCodes");
        if(userIds!=null&&(!userIds.isEmpty())) {
        	PmSaleMemberInfoEntity entity = new PmSaleMemberInfoEntity();
        	entity.setCreatorId(user.getUsrId());
        	entity.setCreateTime(DateUtil.getCurrentTimeMill());
        	entity.setGroupCode(code);
        	entity.setIsDelete("00");
        	entity.setMemberType("00");
        	for(int i = 0;i<userIds.size();i++) {
        		entity.setMenberUsrId(userIds.get(i));
        		
        	}
        	pmSaleMemberInfoService.insert(entity);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmSaleGroupInfoEntity pmSaleGroupInfo,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	pmSaleGroupInfo.setModifier(user.getUsrId());
    	pmSaleGroupInfo.setModifyTime(DateUtil.getCurrentTimeMill());
        pmSaleGroupInfoService.update(pmSaleGroupInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long groupId,HttpSession session){
        pmSaleGroupInfoService.delete(groupId);
        return R.ok();
    }

}
